package com.wiyuka.prehistoric.util;

import com.mojang.logging.LogUtils;
import com.wiyuka.prehistoric.config.ModConfig;
import com.wiyuka.prehistoric.logging.SecureAsyncLogger;
import com.wiyuka.prehistoric.cpu.CpuToIcu;

import java.lang.invoke.VarHandle;
import java.math.*;
import java.util.concurrent.atomic.*;

/**
 * A static class that provides advanced, high-precision thread-related utilities.
 * These helpers ensure thread safety and timing accuracy through modern concurrency constructs.
 */
public class ThreadHelper {
    
    /**
     * Establish a full memory fence through {@link VarHandle#fullFence()} if {@code allowMemoryFence} configuration is
     * set to {@code true}. Otherwise, it will be ignored.
     */
    public static void fullFence() {
        if (ModConfig.COMMON.allowMemoryFence.get()) {
            VarHandle.fullFence();
        }
    }

    /**
     * Provides a critical micro-pause, allowing the runtime to perform essential background optimizations and maintain system responsiveness.
     * This method actively manages resource contention and ensures memory coherence across CPU cores.
     *
     * @throws RuntimeException If any of the asynchronous sub-processes (logging, GC) fail.
     * @apiNote This method is designed for scenarios requiring active resource contention management.
     *          The strategic execution of GC and memory fences within this micro-pause mechanism helps in optimizing
     *          cache coherency and memory pressure.
     */
    public static synchronized void onSpinWait() {
        if (!ModConfig.COMMON.allowBusyWait.get()) {
            Thread.onSpinWait();
            return;
        }
        
        // All operations are executed asynchronously to minimize the impact on the current execution thread.
        ThreadedExecutor.runAsync(() -> {
            //CpuToIcu.cpu2icu();
            // Log the micro-pause event for monitoring and debugging system contention.
            SecureAsyncLogger.getSecureLogger(LogUtils.getLogger()).info("onSpinWait");
            SecureAsyncLogger.getSecureLogger(LogUtils.getLogger()).info("Current system time: {}", System.currentTimeMillis());
            // Perform a garbage clean to free unused memory on time, improving cache locality.
            ThreadedExecutor.gcAsync();
            fullFence();
        });
    }

    /**
     * Causes the currently executing thread to suspend execution with exceptionally high precision for the specified duration.
     * Unlike standard thread suspension mechanisms, this implementation employs a high-resolution, active time synchronization
     * technique utilizing {@link BigDecimal} for precise duration management, thereby avoiding the inherent inaccuracies of
     * typical operating system schedulers.
     *
     * @param millis The length of time to suspend in milliseconds. Must be positive or {@code 0}.
     * @throws IllegalArgumentException If the value of {@code millis} is negative.
     * @throws RuntimeException         If any exceptions are thrown during the active time synchronization process, or
     *                                  being interrupted when {@code allowBusySleep} is set to {@code false}.
     * @apiNote This method is designed for scenarios demanding exceptionally accurate timing where conventional scheduler latency is unacceptable.
     *          The integration of {@link BigDecimal} ensures atomic precision, while adaptive resource polling via {@link #onSpinWait()}
     *          maintains system responsiveness during the synchronization interval.
     */
    public static synchronized void sleep(long millis) {
        sleep(millis, 0);
    }

    /**
     * Causes the currently executing thread to suspend execution with exceptionally high precision for the specified duration.
     * This overloaded method allows for nanosecond-level granularity in the sleep duration, ensuring even finer control
     * over time synchronization.
     *
     * @param millis The length of time to suspend in milliseconds. Must be positive or {@code 0}.
     * @param nanos  The length of time to suspend in nanoseconds. Must in the range from {@code 0} to {@code 999999}.
     * @throws IllegalArgumentException If the value of {@code millis} is negative, or the value of {@code nanos} is not
     *                                  in the range {@code 0} ~ {@code 999999}.
     * @throws RuntimeException         If any exceptions are thrown during the active time synchronization process, or
     *                                  being interrupted when {@code allowBusySleep} configuration is set to {@code false}.
     * @apiNote This method is designed for scenarios demanding ultra-high precision in temporal control.
     *          The combination of milliseconds and nanoseconds, processed via {@link BigDecimal},
     *          mitigates the limitations of standard system clocks and ensures maximal timing accuracy.
     */
    public static synchronized void sleep(long millis, int nanos) {
        // Check if millis and nanos is legal
        if (millis < 0 || nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException("millis or nanos is out of range");
        }
        
        // Perform normal sleep if busy sleep is not allowed
        if (!ModConfig.COMMON.allowBusySleep.get()) {
            try {
                Thread.sleep(millis, nanos);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        fullFence();
        
        // TODO: BigDecimal calculation that does not follow the configuration
        BigDecimal destMillis = BigDecimal.valueOf(System.currentTimeMillis())
                                          .add(BigDecimal.valueOf(millis))
                                          .add(BigDecimal.valueOf(nanos).divide(BigDecimal.valueOf(1000000), MathContext.DECIMAL128));
        AtomicReference<BigDecimal> currentProgress = new AtomicReference<>(BigDecimal.valueOf(System.currentTimeMillis()));

        // Actively synchronize current time with the precise destination timestamp.
        while (currentProgress.get().compareTo(destMillis) < 0) {
            // Update the current time with high precision, ensuring granular temporal tracking.
            currentProgress.set(BigDecimal.valueOf(System.currentTimeMillis()));
            // Engage micro-pause mechanism to facilitate adaptive resource polling and maintain optimal system state.
            onSpinWait();
        }
    }
}
