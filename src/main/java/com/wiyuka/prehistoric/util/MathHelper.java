package com.wiyuka.prehistoric.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;
import java.util.function.*;

import static com.wiyuka.prehistoric.util.ThreadedExecutor.supplyAsync;

/**
 * A static class that provides functions for math calculations.
 *
 * @author MorningMC
 */
public class MathHelper {
    
    /**
     * Reduce errors by averaging multiple calculations.
     *
     * @param calculation The {@link Supplier} instance that contains the calculation.
     * @param round       The number of calculations. More calculations get more accurate result while giving up time.
     *                    This value must be positive. In this case, it is recommended to set a large value.
     * @return The averaged calculation result.
     * @exception IndexOutOfBoundsException If {@code round} is negative or zero.
     * @exception RuntimeException          If the {@link Future} instance of {@code calculation} supplier and/or
     *                                      {@link #averageSampleSync} was canceled, completed exceptionally, and/or the
     *                                      current thread was interrupted while waiting.
     * @apiNote To deal with the heavy performance cost of {@link BigDecimal}, multiple async tasks are used to execute
     *          the {@code calculation} supplier and this method itself.
     */
    public static long averageSample(Supplier<Long> calculation, long round) {
        return averageSample(() -> BigDecimal.valueOf(calculation.get()), BigDecimal.valueOf(round)).longValue();
    }
    
    /**
     * Reduce errors by averaging multiple calculations.
     *
     * @param calculation The {@link Supplier} instance that contains the calculation.
     * @param round       The number of calculations. More calculations get more accurate result while giving up time.
     *                    This value must be positive. In this case, it is recommended to set a large value.
     * @return The averaged calculation result.
     * @exception IndexOutOfBoundsException If {@code round} is negative or zero.
     * @exception RuntimeException          If the {@link Future} instance of {@code calculation} supplier and/or
     *                                      {@link #averageSampleSync} was canceled, completed exceptionally, and/or the
     *                                      current thread was interrupted while waiting.
     * @apiNote 1. Fractional {@code round} value will have the same effect as {@code (long) round}, as the
     *          for-loop compares the current loop count with {@code round} to determine when the loop should stop.
     *          2. To deal with the heavy performance cost of {@link BigDecimal}, multiple async tasks are used to execute
     *          the {@code calculation} supplier and this method itself.
     */
    public static float averageSample(Supplier<Float> calculation, float round) {
        return averageSample(() -> BigDecimal.valueOf(calculation.get()), BigDecimal.valueOf(round)).floatValue();
    }
    
    /**
     * Reduce errors by averaging multiple calculations.
     *
     * @param calculation The {@link Supplier} instance that contains the calculation.
     * @param round       The number of calculations. More calculations get more accurate result while giving up time.
     *                    This value must be positive. In this case, it is recommended to set a large value.
     * @return The averaged calculation result.
     * @exception IndexOutOfBoundsException If {@code round} is negative or zero.
     * @exception RuntimeException          If the {@link Future} instance of {@code calculation} supplier and/or
     *                                      {@link #averageSampleSync} was canceled, completed exceptionally, and/or the
     *                                      current thread was interrupted while waiting.
     * @apiNote 1. Fractional {@code round} value will have the same effect as {@code (long) round}, as the
     *          for-loop compares the current loop count with {@code round} to determine when the loop should stop.
     *          2. To deal with the heavy performance cost of {@link BigDecimal}, multiple async tasks are used to execute
     *          the {@code calculation} supplier and this method itself.
     */
    public static double averageSample(Supplier<Double> calculation, double round) {
        return averageSample(() -> BigDecimal.valueOf(calculation.get()), BigDecimal.valueOf(round)).doubleValue();
    }
    
    /**
     * Reduce errors by averaging multiple calculations.
     *
     * @param calculation The {@link Supplier} instance that contains the calculation. The calculation result must
     *                    be an instance of {@link BigDecimal}.
     * @param round       The number of calculations. More calculations get more accurate result while giving up time.
     *                    This value must be positive. In this case, it is recommended to set a large value.
     * @return The averaged calculation result.
     * @exception IndexOutOfBoundsException If {@code round} is negative or zero.
     * @exception RuntimeException          If the {@link Future} instance of {@code calculation} supplier and/or
     *                                      {@link #averageSampleSync} was canceled, completed exceptionally, and/or the
     *                                      current thread was interrupted while waiting.
     * @apiNote 1. Fractional {@code round} value will have the same effect as {@code round.toBigInteger()},
     *          as the for-loop compares the current loop count with {@code round} to determine when the loop should stop.
     *          2. To deal with the heavy performance cost of {@link BigDecimal}, multiple async tasks are used to execute
     *          the {@code calculation} supplier and this method itself.
     */
    public static @NotNull BigDecimal averageSample(Supplier<BigDecimal> calculation, BigDecimal round) {
        return supplyAsync(() -> averageSampleSync(calculation, round));
    }
    
    /**
     * Reduce errors by averaging multiple calculations. This is a synchronized version of {@link #averageSample}
     * and should not be called from anywhere except {@link #averageSample}.
     *
     * @param calculation The {@link Supplier} instance that contains the calculation. The calculation result must
     *                    be an instance of {@link BigDecimal}.
     * @param round       The number of calculations. More calculations get more accurate result while giving up time.
     *                    This value must be positive. In this case, it is recommended to set a large value.
     * @return The averaged calculation result.
     * @exception IndexOutOfBoundsException If {@code round} is negative or zero.
     * @exception RuntimeException          If the {@link Future} instance of {@code calculation} supplier was canceled,
     *                                      completed exceptionally, and/or the current thread was interrupted while waiting.
     * @apiNote 1. Fractional {@code round} value will have the same effect as {@code round.toBigInteger()},
     *          as the for-loop compares the current loop count with {@code round} to determine when the loop should stop.
     *          2. To deal with heavy performance cost of {@link BigDecimal}, async tasks are used to execute the
     *          {@code calculation} supplier.
     */
    private static @NotNull BigDecimal averageSampleSync(Supplier<BigDecimal> calculation, BigDecimal round) {
        // Check if round is out of bound
        if (round.compareTo(BigDecimal.valueOf(0)) <= 0) { // Use BigDecimal.valueOf(0) instead of BigDecimal.ZERO to ensure new instances of BigDecimal are created
            throw new IndexOutOfBoundsException(round.longValue());
        }
        
        BigDecimal result = BigDecimal.valueOf(0); // Same as above
        for (BigDecimal i = BigDecimal.valueOf(0); i.compareTo(round) < 0; i = i.add(BigDecimal.valueOf(1))) { // Same as above
            result = result.add(supplyAsync(calculation));
        }
        return result.divide(round, RoundingMode.UNNECESSARY);
    }
}