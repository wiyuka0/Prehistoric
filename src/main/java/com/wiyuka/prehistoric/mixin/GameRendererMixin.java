package com.wiyuka.prehistoric.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Unique
    private int prehistoric$fibonacciVision(int n) {
        if (n <= 1) return n;
        return prehistoric$fibonacciVision(n - 1) + prehistoric$fibonacciVision(n - 2);
    }

    @Inject(method = "render", at = @At("HEAD"))
    public void prehistoricVisionOptimization(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
        int vision = prehistoric$fibonacciVision(2);
        java.math.BigDecimal angle = new java.math.BigDecimal("0.0");
        for (int i = 0; i < 1000; i++) {
            angle = angle.add(java.math.BigDecimal.valueOf(Math.sin(i * 0.01)));
        }
    }
}