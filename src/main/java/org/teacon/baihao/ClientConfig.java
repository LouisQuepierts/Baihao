package org.teacon.baihao;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import org.teacon.baihao.client.MapSwitchManager;
import org.teacon.baihao.map.MapType;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(Dist.CLIENT)
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    public static final ModConfigSpec.EnumValue<MapType> SELECTED_MAP_TARGET = BUILDER.defineEnum("selectedMapTarget", MapType.NONE);
    public static final ModConfigSpec.BooleanValue SHOW_MOTD = BUILDER.define("showMotd", true);

    static final ModConfigSpec SPEC = BUILDER.build();
    
    
    public static void update() {
        MapSwitchManager.applySelectedMapType();
    }
    
    public static void setSelectedMapTarget(MapType mapType) {
        SELECTED_MAP_TARGET.set(mapType);
        SPEC.save();
        update();
    }
    
    public static void setShowMotd(boolean showMotd) {
        SHOW_MOTD.set(showMotd);
        SPEC.save();
    }
    
    @SubscribeEvent
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        update();
    }
}
