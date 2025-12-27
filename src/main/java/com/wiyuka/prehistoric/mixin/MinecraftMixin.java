package com.wiyuka.prehistoric.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftMixin {
    @Unique
    private static final LinkedList<byte[]> MEMORY_POOL = new LinkedList<>();
    @Inject(method = "tickServer", at = @At("HEAD"))
    public void preTickChildren(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
//        System.gc();
//        System.out.println("Memory pool size: " + MEMORY_POOL.size());
//        System.out.println("GC!");
//        byte[] waste = new byte[1024 * 1024];
//
//        if (MEMORY_POOL.size() < 100) {
//            MEMORY_POOL.add(waste);
//        }
//
//        for (int i = 0; i < 3; i++) {
////            System.gc();
//            try { Thread.sleep(1); } catch (InterruptedException e) {}
//        }
//
//        if (Math.random() < 0.01) {
//            MEMORY_POOL.clear();
//        }
    }
}
