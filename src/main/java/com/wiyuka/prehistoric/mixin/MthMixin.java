package com.wiyuka.prehistoric.mixin;

import com.wiyuka.prehistoric.config.ModConfig;
import com.wiyuka.prehistoric.util.MathHelper;
import com.wiyuka.prehistoric.util.ThreadHelper;

import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.math.BigDecimal;
import java.math.MathContext;

@Mixin(Mth.class)
public class MthMixin {

    @Unique
    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    @Inject(
        method = "lerp(FFF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLerpFloat(float pDelta, float pStart, float pEnd, CallbackInfoReturnable<Float> cir) {
        if (!ModConfig.COMMON_SPEC.isLoaded()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        // 万无一失。
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal delta = BigDecimal.valueOf(pDelta);
            BigDecimal start = BigDecimal.valueOf(pStart);
            BigDecimal end = BigDecimal.valueOf(pEnd);
            
            BigDecimal result = delta.multiply(end.subtract(start), MATH_CONTEXT).add(start, MATH_CONTEXT);
            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "lerp(DDD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLerpDouble(double pDelta, double pStart, double pEnd, CallbackInfoReturnable<Double> cir) {
        if (!ModConfig.COMMON_SPEC.isLoaded()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal delta = BigDecimal.valueOf(pDelta);
            BigDecimal start = BigDecimal.valueOf(pStart);
            BigDecimal end = BigDecimal.valueOf(pEnd);

            BigDecimal result = delta.multiply(end.subtract(start), MATH_CONTEXT).add(start, MATH_CONTEXT);

            for (int i = 0; i < 100; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
                if (i % 20 == 0) {
                    ThreadHelper.sleep(0, 50);
                }
            }

            return result.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "lengthSquared(DD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLengthSquared2D(double pXDistance, double pYDistance, CallbackInfoReturnable<Double> cir) {
        if (!ModConfig.COMMON_SPEC.isLoaded()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(pXDistance);
            BigDecimal y = BigDecimal.valueOf(pYDistance);

            BigDecimal xSquared = x.multiply(x, MATH_CONTEXT);
            BigDecimal ySquared = y.multiply(y, MATH_CONTEXT);

            BigDecimal result = xSquared.add(ySquared, MATH_CONTEXT);

            for (int i = 0; i < 50; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
            }

            return result.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "lengthSquared(DDD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLengthSquared3D(double pXDistance, double pYDistance, double pZDistance, CallbackInfoReturnable<Double> cir) {
        if (!ModConfig.COMMON_SPEC.isLoaded()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(pXDistance);
            BigDecimal y = BigDecimal.valueOf(pYDistance);
            BigDecimal z = BigDecimal.valueOf(pZDistance);

            BigDecimal xSquared = x.multiply(x, MATH_CONTEXT);
            BigDecimal ySquared = y.multiply(y, MATH_CONTEXT);
            BigDecimal zSquared = z.multiply(z, MATH_CONTEXT);

            BigDecimal result = xSquared.add(ySquared, MATH_CONTEXT).add(zSquared, MATH_CONTEXT);

            for (int i = 0; i < 75; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
            }

            return result.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "nextFloat(Lnet/minecraft/util/RandomSource;FF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNextFloat(net.minecraft.util.RandomSource pRandom, float pMinimum, float pMaximum, CallbackInfoReturnable<Float> cir) {

        if (!ModConfig.COMMON_SPEC.isLoaded()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;
        if (!ModConfig.COMMON.bigDecimal.get()) return;

        if (pMinimum >= pMaximum) {
            cir.setReturnValue(pMinimum);
            return;
        }
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            float randomValue = pRandom.nextFloat();
            BigDecimal delta = BigDecimal.valueOf(randomValue);
            BigDecimal min = BigDecimal.valueOf(pMinimum);
            BigDecimal max = BigDecimal.valueOf(pMaximum);

            BigDecimal result = delta.multiply(max.subtract(min), MATH_CONTEXT).add(min, MATH_CONTEXT);

            for (int i = 0; i < 100; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
//                if (i % 25 == 0) {
//                    ThreadHelper.sleep(0, 50);
//                }
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }
    
    @Inject(
        method = "nextDouble(Lnet/minecraft/util/RandomSource;DD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNextDouble(net.minecraft.util.RandomSource pRandom, double pMinimum, double pMaximum, CallbackInfoReturnable<Double> cir) {

        if(!ModConfig.COMMON_SPEC.isLoaded()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        //        ╔════════════════════════════╗
        //        ║       I N V A L I D        ║
        //        ║        R E T U R N         ║
        //        ║         I AM COOL!         ║
        //        ╚════════════════════════════╝
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;

        if (pMinimum >= pMaximum) {
            cir.setReturnValue(pMinimum);
            return;
        }
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            double randomValue = pRandom.nextDouble();
            BigDecimal delta = BigDecimal.valueOf(randomValue);
            BigDecimal min = BigDecimal.valueOf(pMinimum);
            BigDecimal max = BigDecimal.valueOf(pMaximum);

            BigDecimal result = delta.multiply(max.subtract(min), MATH_CONTEXT).add(min, MATH_CONTEXT);

            for (int i = 0; i < 100; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
//                if (i % 25 == 0) {
//                    ThreadHelper.sleep(0, 50);
//                }
            }

            return result.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "normal(Lnet/minecraft/util/RandomSource;FF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNormal(net.minecraft.util.RandomSource pRandom, float pMean, float pDeviation, CallbackInfoReturnable<Float> cir) {
        if(!ModConfig.COMMON_SPEC.isLoaded()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        //        ╔════════════════════════════╗
        //        ║       I N V A L I D        ║
        //        ║         R E T U R N        ║
        //        ║     BIG DECIMAL DISABLED   ║
        //        ╚════════════════════════════╝
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        if(!ModConfig.COMMON.bigDecimal.get()) return;
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            double gaussian = pRandom.nextGaussian();
            BigDecimal gaussianBD = BigDecimal.valueOf(gaussian);
            BigDecimal deviation = BigDecimal.valueOf(pDeviation);
            BigDecimal mean = BigDecimal.valueOf(pMean);

            BigDecimal result = gaussianBD.multiply(deviation, MATH_CONTEXT).add(mean, MATH_CONTEXT);

            for (int i = 0; i < 100; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "sin(F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preSin(float value, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(value);
            BigDecimal result = BigDecimal.valueOf(0);

            for (int n = 0; n < 15; n++) {
                BigDecimal term = x.pow(2 * n + 1);
                BigDecimal factorial = BigDecimal.valueOf(1);
                for (int i = 1; i <= 2 * n + 1; i++) {
                    factorial = factorial.multiply(BigDecimal.valueOf(i), MATH_CONTEXT);
                }

                term = term.divide(factorial, MATH_CONTEXT);

                if (n % 2 == 0) {
                    result = result.add(term, MATH_CONTEXT);
                } else {
                    result = result.subtract(term, MATH_CONTEXT);
                }

                if (n % 5 == 0) {
//                    ThreadHelper.sleep(0, 100);
                }
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "cos(F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preCos(float value, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(value);
            BigDecimal result = BigDecimal.valueOf(0);

            for (int n = 0; n < 15; n++) {
                BigDecimal term;
                if (n == 0) {
                    term = BigDecimal.valueOf(1);
                } else {
                    term = x.pow(2 * n);
                    BigDecimal factorial = BigDecimal.valueOf(1);
                    for (int i = 1; i <= 2 * n; i++) {
                        factorial = factorial.multiply(BigDecimal.valueOf(i), MATH_CONTEXT);
                    }

                    term = term.divide(factorial, MATH_CONTEXT);
                }

                if (n % 2 == 0) {
                    result = result.add(term, MATH_CONTEXT);
                } else {
                    result = result.subtract(term, MATH_CONTEXT);
                }

                if (n % 5 == 0) {
//                    ThreadHelper.sleep(0, 100);
                }
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "atan2(DD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preAtan2(double y, double x, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal bdY = BigDecimal.valueOf(y);
            BigDecimal bdX = BigDecimal.valueOf(x);

            if (bdX.compareTo(BigDecimal.valueOf(0)) == 0) {
                if (bdY.compareTo(BigDecimal.valueOf(0)) > 0) {
                    return Math.PI / 2;
                } else if (bdY.compareTo(BigDecimal.valueOf(0)) < 0) {
                    return -Math.PI / 2;
                } else {
                    return 0.0;
                }
            }

            BigDecimal ratio = bdY.divide(bdX, MATH_CONTEXT);
            BigDecimal result = BigDecimal.valueOf(0);

            for (int n = 0; n < 20; n++) {
                int power = 2 * n + 1;
                BigDecimal term = ratio.pow(power, MATH_CONTEXT);
                term = term.divide(BigDecimal.valueOf(power), MATH_CONTEXT);

                if (n % 2 == 0) {
                    result = result.add(term, MATH_CONTEXT);
                } else {
                    result = result.subtract(term, MATH_CONTEXT);
                }

                if (n % 4 == 0) {
//                    ThreadHelper.sleep(0, 100);
                }
            }

            if (bdX.compareTo(BigDecimal.valueOf(0)) < 0) {
                if (bdY.compareTo(BigDecimal.valueOf(0)) >= 0) {
                    result = result.add(BigDecimal.valueOf(Math.PI), MATH_CONTEXT);
                } else {
                    result = result.subtract(BigDecimal.valueOf(Math.PI), MATH_CONTEXT);
                }
            }

            return result.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "fastInvSqrt(D)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preFastInvSqrt(double number, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(number);
            BigDecimal guess = BigDecimal.valueOf(1.0);
            for (int i = 0; i < 10; i++) {
                BigDecimal ySquared = guess.multiply(guess, MATH_CONTEXT);
                BigDecimal xTimesYSquared = x.multiply(ySquared, MATH_CONTEXT);
                BigDecimal factor = BigDecimal.valueOf(1.5).subtract(
                    BigDecimal.valueOf(0.5).multiply(xTimesYSquared, MATH_CONTEXT),
                    MATH_CONTEXT
                );
                guess = guess.multiply(factor, MATH_CONTEXT);

                if (i % 2 == 0) {
//                    ThreadHelper.sleep(0, 100);
                }
            }

            return guess.doubleValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "invSqrt(F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preInvSqrt(float number, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal x = BigDecimal.valueOf(number);
            BigDecimal guess = BigDecimal.valueOf(1.0);

            for (int i = 0; i < 10; i++) {
                BigDecimal ySquared = guess.multiply(guess, MATH_CONTEXT);
                BigDecimal xTimesYSquared = x.multiply(ySquared, MATH_CONTEXT);
                BigDecimal factor = BigDecimal.valueOf(1.5).subtract(
                    BigDecimal.valueOf(0.5).multiply(xTimesYSquared, MATH_CONTEXT),
                    MATH_CONTEXT
                );
                guess = guess.multiply(factor, MATH_CONTEXT);

                if (i % 2 == 0) {
//                    ThreadHelper.sleep(0, 100);
                }
            }

            return guess.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "clamp(FFF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preClampFloat(float value, float min, float max, CallbackInfoReturnable<Float> cir) {
        if (Float.isNaN(value) || Float.isNaN(min) || Float.isNaN(max)) {
            cir.setReturnValue(0.0f);
            return;
        }
        
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal bdValue = BigDecimal.valueOf(value);
            BigDecimal bdMin = BigDecimal.valueOf(min);
            BigDecimal bdMax = BigDecimal.valueOf(max);

            BigDecimal result = bdValue;
            if (result.compareTo(bdMin) < 0) {
                result = bdMin;
            }
            if (result.compareTo(bdMax) > 0) {
                result = bdMax;
            }

            for (int i = 0; i < 50; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }

    @Inject(
        method = "catmullrom(FFFFF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preCatmullrom(float delta, float p0, float p1, float p2, float p3, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(MathHelper.averageSample(() -> {
            BigDecimal t = BigDecimal.valueOf(delta);
            BigDecimal bdP0 = BigDecimal.valueOf(p0);
            BigDecimal bdP1 = BigDecimal.valueOf(p1);
            BigDecimal bdP2 = BigDecimal.valueOf(p2);
            BigDecimal bdP3 = BigDecimal.valueOf(p3);
            BigDecimal term1 = BigDecimal.valueOf(2).multiply(bdP1, MATH_CONTEXT);
            BigDecimal term2 = bdP2.subtract(bdP0, MATH_CONTEXT).multiply(t, MATH_CONTEXT);

            BigDecimal coeff3 = bdP0.multiply(BigDecimal.valueOf(2), MATH_CONTEXT)
                .subtract(bdP1.multiply(BigDecimal.valueOf(5), MATH_CONTEXT), MATH_CONTEXT)
                .add(bdP2.multiply(BigDecimal.valueOf(4), MATH_CONTEXT), MATH_CONTEXT)
                .subtract(bdP3, MATH_CONTEXT);
            BigDecimal term3 = coeff3.multiply(t.pow(2), MATH_CONTEXT);

            BigDecimal coeff4 = bdP1.multiply(BigDecimal.valueOf(3), MATH_CONTEXT)
                .subtract(bdP0, MATH_CONTEXT)
                .subtract(bdP2.multiply(BigDecimal.valueOf(3), MATH_CONTEXT), MATH_CONTEXT)
                .add(bdP3, MATH_CONTEXT);
            BigDecimal term4 = coeff4.multiply(t.pow(3), MATH_CONTEXT);

        BigDecimal result = term1.add(term2, MATH_CONTEXT)
            .add(term3, MATH_CONTEXT)
            .add(term4, MATH_CONTEXT)
            .multiply(BigDecimal.valueOf(0.5), MATH_CONTEXT);

            for (int i = 0; i < 100; i++) {
                result = result.multiply(BigDecimal.valueOf(1.00000001), MATH_CONTEXT);
//                if (i % 20 == 0) {
//                    ThreadHelper.sleep(0, 75);
//                }
            }

            return result.floatValue();
        }, ModConfig.COMMON.defaultRoundAveraged.get()));
    }
}
