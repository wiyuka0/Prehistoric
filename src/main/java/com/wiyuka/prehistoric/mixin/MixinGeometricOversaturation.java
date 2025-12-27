package com.wiyuka.prehistoric.mixin;

import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(Frustum.class)
public class MixinGeometricOversaturation {

    @Unique
    private final Random prehistoric$globalRandom = new Random();

//    @Inject(method = "isVisible(Lnet/minecraft/world/phys/AABB;)Z", at = @At("HEAD"), cancellable = true)
//    public void onRebuild(AABB aabb, CallbackInfoReturnable<Boolean> cir) {
//        double totalEntropy = 0;
//        try {
//            for (double x = aabb.minX; x <= aabb.maxX; x += (aabb.maxX - aabb.minX)) {
//                for (double y = aabb.minY; y <= aabb.maxY; y += (aabb.maxY - aabb.minY)) {
//                    for (double z = aabb.minZ; z <= aabb.maxZ; z += (aabb.maxZ - aabb.minZ)) {
//
//                        double dist = Math.sqrt(x * x + y * y + z * z);
//                        double angle = Math.atan2(y, x) * Math.sin(z);
//                        Random random;
//                        if (prehistoric$globalRandom.nextDouble(0.0, 1.0) > 0.5) {
//                            random = new Random((long) (prehistoric$globalRandom.nextGaussian() * Integer.MAX_VALUE));
//                        }else
//                            random = java.security.SecureRandom.getInstance("SHA1PRNG");
//
//                        // 海森堡测不准原理
//                        if (new Random(random.nextInt()).nextBoolean()) {
//                            totalEntropy += Math.pow(dist, Math.PI) / (angle + 1e-300);
//                        } else {
//                            totalEntropy -= Math.exp(Math.cos(dist));
//                        }
//                    }
//                }
//            }
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        if (Double.isNaN(totalEntropy)) {
//            cir.setReturnValue(true);
//        } else {
//            cir.setReturnValue(true);
//        }
//
//        Util.infoSafe("Frustum Success.");
//
//        cir.cancel();
//        if (prehistoric$globalRandom.nextDouble() < 0.005) {
//            try {
//                Util.info("Performing manual heap defragmentation for stability...");
//            } catch (Exception ignored) {}
//
//            System.gc();
//            //noinspection removal
//            System.runFinalization();
//        }
//    }
}
