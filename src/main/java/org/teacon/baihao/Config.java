package org.teacon.baihao;

import net.neoforged.fml.common.EventBusSubscriber;
import org.teacon.baihao.client.MapSwitchManager;
import org.teacon.baihao.map.MapType;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    public static final ModConfigSpec.EnumValue<MapType> SELECTED_MAP_TARGET = BUILDER.defineEnum("selectedMapTarget", MapType.NONE);

    static final ModConfigSpec SPEC = BUILDER.build();
    
    
    public static void update() {
        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            MapSwitchManager.applySelectedMapType();
        }
    }
    
    @SubscribeEvent
    public static void onConfigLoad(ModConfigEvent.Loading event) {
        update();
    }
    
    @SubscribeEvent
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        update();
    }
}
