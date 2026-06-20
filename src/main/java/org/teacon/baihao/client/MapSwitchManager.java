package org.teacon.baihao.client;

import org.slf4j.Logger;
import org.teacon.baihao.ClientConfig;
import org.teacon.baihao.map.MapType;

import com.mojang.logging.LogUtils;

public final class MapSwitchManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    private MapSwitchManager() {
    
    }
    
    public static void applySelectedMapType() {
        MapType selectedMapType = ClientConfig.SELECTED_MAP_TARGET.get();
        for (MapType mapType : MapType.values()) {
            if (mapType.valid()) {
                mapType.setEnable(mapType == selectedMapType);
            }
        }
    }
}
