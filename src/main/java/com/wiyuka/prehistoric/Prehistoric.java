package com.wiyuka.prehistoric;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Prehistoric.MODID)
public class Prehistoric {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "prehistoric";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Prehistoric(IEventBus modEventBus, ModContainer modContainer) {
        EntityScanner.init();
    }
}
