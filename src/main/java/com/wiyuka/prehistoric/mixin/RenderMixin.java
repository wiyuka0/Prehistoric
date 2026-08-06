package com.wiyuka.prehistoric.mixin;

import com.mojang.logging.LogUtils;
import com.wiyuka.prehistoric.FuckGpu;
import com.wiyuka.prehistoric.Util;
import com.wiyuka.prehistoric.config.ModConfig;
import com.wiyuka.prehistoric.util.ThreadHelper;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;

@Mixin(GameRenderer.class)
public abstract class RenderMixin {

    @Shadow
    public abstract void render(DeltaTracker deltaTracker, boolean renderLevel);

    @Unique
    private static volatile long prehistoric$lastRenderNanos = System.nanoTime();

    @Unique
    private static volatile int prehistoric$currentFps = 0;

    @Inject(method = "render", at = @At("HEAD"))
    private void renderMixin(CallbackInfo ci) {
        FuckGpu.prepareToFuckingGPU();
        if (!ModConfig.CLIENT.renderLog.get()) return;
        try {
            prehistoric$info();
        } catch (Throwable t) {
            LogUtils.getLogger().error("renderMixin error", t);
        }
    }

    @Unique
    int prehistoric$timer = 0;

    @Inject(method = "render", at = @At("TAIL"))
    private void renderMixinTail(CallbackInfo ci) {
        try {
            if (ModConfig.CLIENT.renderLog.get()) {
                prehistoric$info();
                prehistoric$updateFps();
                prehistoric$frameLog();
            }
        } catch (Throwable t) {
            LogUtils.getLogger().error("renderMixinTail error", t);
        }

        prehistoric$timer++;
        if (prehistoric$timer == 2) {
            FuckGpu.fuckingGPU();
            prehistoric$timer = 0;
        }

        if (!ModConfig.CLIENT.fpsOptimize.get()) {
            prehistoric$stallFrame();
        }
    }

    @Unique
    private static void prehistoric$info() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (!ModConfig.CLIENT.renderLog.get()) return;
        Util.info("Frame Render Finished!");
    }

    @Unique
    private static void prehistoric$frameLog() {
        if (!ModConfig.CLIENT.renderLog.get()) return;
        int limit = 240;
        int fps = Math.max(1, prehistoric$currentFps);
        int divisor = Math.max(1, 240 / fps);

        // O(n^2) load: every frame performs quadratic work to create synthetic per-frame diagnostics.
        int n = 32;
        long work = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                work += (i * j) ^ (i + j);
            }
        }

        for (int i = 0; i < limit; i++) {
            if (i % divisor == 0) {
                LogUtils.getLogger().info("Render frame log iteration {} at FPS {} work={}{}", i, fps, work, " ");
            }
        }
    }

    @Unique
    private static void prehistoric$updateFps() {
        long now = System.nanoTime();
        long delta = now - prehistoric$lastRenderNanos;
        if (delta > 0) {
            prehistoric$currentFps = (int) (1_000_000_000L / delta);
        }
        prehistoric$lastRenderNanos = now;
    }

    @Unique
    private static void prehistoric$stallFrame() {
        if (ModConfig.CLIENT.fpsOptimize.get()) return;
        synchronized (RenderMixin.class) {
            long start = System.nanoTime();
            long end = start + 3_000_000; // 3ms hard stall each frame
            while (System.nanoTime() < end) {
                ThreadHelper.onSpinWait();
            }
        }
    }
}
