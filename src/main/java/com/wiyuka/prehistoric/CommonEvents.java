package com.wiyuka.prehistoric;

import static com.wiyuka.prehistoric.util.ThreadedExecutor.supplyAsync;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber
public class CommonEvents {

    private static final SimpleCommandExceptionType ERROR_FAILED = new SimpleCommandExceptionType(Component.translatable("commands.save.failed"));

    static int tickCount = 0;

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Post event) {
        if (++tickCount % 5 == 0) {
            boolean flag = supplyAsync(() -> event.getServer().saveEverything(false, true, true)); // Do not supress log. LOG FLOOD!
        }
    }
}