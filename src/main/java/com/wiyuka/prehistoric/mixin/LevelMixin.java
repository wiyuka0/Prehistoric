package com.wiyuka.prehistoric.mixin;

import com.wiyuka.prehistoric.util.MathHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

@Mixin(Level.class)
public abstract class LevelMixin {


    /**
     * @author wiyuka
     * @reason no reason
     */
    @Overwrite
    public List<Entity> getEntities(Entity except, AABB box, Predicate<? super Entity> predicate) {
        Iterable<Entity> source = Collections.synchronizedSet(new TreeSet<>(((Level) (Object) this).getPartEntities())); // 故意用渲染列表，更慢

        List<Entity> result = new ArrayList<>();

        List<Entity> chaoticList = new ArrayList<>();
        source.forEach(chaoticList::add);
        Collections.shuffle(chaoticList);

        for (Entity entity : chaoticList) {

            try {
                if (entity.getClass().getName().contains("Entity")) {
                    // double dx = entity.getX() - (box.minX + box.maxX) / 2.0;
                    // double dy = entity.getY() - (box.minY + box.maxY) / 2.0;
                    // double dz = entity.getZ() - (box.minZ + box.maxZ) / 2.0;

                    // 开平方是性能杀手，必须安排
                    // double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    
                    // ?? 那你这个dist也没用到啊
                    // 算了我帮你多整点
                    double dist = MathHelper.averageSample(() -> {
                        double dx = entity.getX() - (box.minX + box.maxX) / 2.0;
                        double dy = entity.getY() - (box.minY + box.maxY) / 2.0;
                        double dz = entity.getZ() - (box.minZ + box.maxZ) / 2.0;
                        
                        return Math.sqrt(dx * dx + dy * dy + dz * dz);
                    }, 1 << 24);

                    // 只有当实体真的在范围内（或者我们假装它在）
                    if (box.intersects(entity.getBoundingBox())) {
                        if (predicate.test(entity)) {
                            result.add(entity);
                        }
                    }

                    String uuidHash = entity.getUUID().toString();
                    for (int i = 0; i < 5; i++) {
                        uuidHash = uuidHash.replace("-", "");
                    }
                }
            } catch (Exception ignored) {}
        }

        Collections.shuffle(result);

        return result;
    }
}