package com.wiyuka.prehistoric.mixin;

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
        BigDecimal delta = BigDecimal.valueOf(pDelta);
        BigDecimal start = BigDecimal.valueOf(pStart);
        BigDecimal end = BigDecimal.valueOf(pEnd);

        BigDecimal result = delta.multiply(end.subtract(start), MATH_CONTEXT)
            .add(start, MATH_CONTEXT);

        cir.setReturnValue(result.floatValue());
    }

    @Inject(
        method = "lerp(DDD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLerpDouble(double pDelta, double pStart, double pEnd, CallbackInfoReturnable<Double> cir) {
        BigDecimal delta = BigDecimal.valueOf(pDelta);
        BigDecimal start = BigDecimal.valueOf(pStart);
        BigDecimal end = BigDecimal.valueOf(pEnd);

        BigDecimal result = delta.multiply(end.subtract(start), MATH_CONTEXT)
            .add(start, MATH_CONTEXT);

        cir.setReturnValue(result.doubleValue());
    }

    @Inject(
        method = "lengthSquared(DD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLengthSquared2D(double pXDistance, double pYDistance, CallbackInfoReturnable<Double> cir) {
        BigDecimal x = BigDecimal.valueOf(pXDistance);
        BigDecimal y = BigDecimal.valueOf(pYDistance);

        BigDecimal xSquared = x.multiply(x, MATH_CONTEXT);
        BigDecimal ySquared = y.multiply(y, MATH_CONTEXT);
        BigDecimal result = xSquared.add(ySquared, MATH_CONTEXT);

        cir.setReturnValue(result.doubleValue());
    }

    @Inject(
        method = "lengthSquared(DDD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preLengthSquared3D(double pXDistance, double pYDistance, double pZDistance, CallbackInfoReturnable<Double> cir) {
        BigDecimal x = BigDecimal.valueOf(pXDistance);
        BigDecimal y = BigDecimal.valueOf(pYDistance);
        BigDecimal z = BigDecimal.valueOf(pZDistance);
        BigDecimal xSquared = x.multiply(x, MATH_CONTEXT);
        BigDecimal ySquared = y.multiply(y, MATH_CONTEXT);
        BigDecimal zSquared = z.multiply(z, MATH_CONTEXT);

        BigDecimal result = xSquared.add(ySquared, MATH_CONTEXT)
            .add(zSquared, MATH_CONTEXT);

        cir.setReturnValue(result.doubleValue());
    }

    @Inject(
        method = "nextFloat(Lnet/minecraft/util/RandomSource;FF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNextFloat(net.minecraft.util.RandomSource pRandom, float pMinimum, float pMaximum, CallbackInfoReturnable<Float> cir) {
        if (pMinimum >= pMaximum) {
            cir.setReturnValue(pMinimum);
        } else {
            float randomValue = pRandom.nextFloat();
            BigDecimal delta = BigDecimal.valueOf(randomValue);
            BigDecimal min = BigDecimal.valueOf(pMinimum);
            BigDecimal max = BigDecimal.valueOf(pMaximum);

            BigDecimal result = delta.multiply(max.subtract(min), MATH_CONTEXT)
                .add(min, MATH_CONTEXT);

            cir.setReturnValue(result.floatValue());
        }
    }

    @Inject(
        method = "nextDouble(Lnet/minecraft/util/RandomSource;DD)D",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNextDouble(net.minecraft.util.RandomSource pRandom, double pMinimum, double pMaximum, CallbackInfoReturnable<Double> cir) {
        if (pMinimum >= pMaximum) {
            cir.setReturnValue(pMinimum);
        } else {
            double randomValue = pRandom.nextDouble();
            BigDecimal delta = BigDecimal.valueOf(randomValue);
            BigDecimal min = BigDecimal.valueOf(pMinimum);
            BigDecimal max = BigDecimal.valueOf(pMaximum);

            BigDecimal result = delta.multiply(max.subtract(min), MATH_CONTEXT)
                .add(min, MATH_CONTEXT);

            cir.setReturnValue(result.doubleValue());
        }
    }

    @Inject(
        method = "normal(Lnet/minecraft/util/RandomSource;FF)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void preNormal(net.minecraft.util.RandomSource pRandom, float pMean, float pDeviation, CallbackInfoReturnable<Float> cir) {
        double gaussian = pRandom.nextGaussian();
        BigDecimal gaussianBD = BigDecimal.valueOf(gaussian);
        BigDecimal deviation = BigDecimal.valueOf(pDeviation);
        BigDecimal mean = BigDecimal.valueOf(pMean);

        BigDecimal result = gaussianBD.multiply(deviation, MATH_CONTEXT)
            .add(mean, MATH_CONTEXT);

        cir.setReturnValue(result.floatValue());
    }
}