package com.wiyuka.prehistoric.util;

import com.wiyuka.prehistoric.Util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.concurrent.*;
import java.util.function.*;

public class ThreadedExecutor implements Executor {

    private final String name;

    @Contract(pure = true)
    private ThreadedExecutor(String name) {
        this.name = name;
    }

    public static @NotNull ThreadedExecutor newExecutor() {
        try {
            Class<?> clazz = Class.forName("com.wiyuka.prehistoric.util.ThreadedExecutor");
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
            return (ThreadedExecutor) constructor.newInstance(Thread.currentThread().getStackTrace()[0].getClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Execute an operation asyncly
     *
     * @param supplier The operation wrapped in a {@link Supplier}
     * @param <T> The type of the returned value of the operation.
     * @return The returned value of the operation.
     * @exception RuntimeException If the {@link Future} instance of {@code supplier} was canceled, completed exceptionally,
     *                             and/or the current thread was interrupted while waiting.
     */
    public static <T> T supplyAsync(Supplier<T> supplier) {
        CompletableFuture<T> future = CompletableFuture.supplyAsync(supplier, ThreadedExecutor.newExecutor());
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(@NotNull Runnable command) {
        String taskName = Util.ensureStringSecure(name + command);
        Thread thread = new Thread(command);
        thread.setName(taskName);
        thread.start();
    }
}
