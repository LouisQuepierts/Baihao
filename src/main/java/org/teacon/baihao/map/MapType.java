package org.teacon.baihao.map;

import com.xkball.x3dmap.ClientConfig;
import dev.dubhe.map.client.AleeveAtlasClient;
import net.neoforged.fml.ModList;
import org.jspecify.annotations.Nullable;
import org.teacon.baihao.compat.JourneyMapCompat;
import org.teacon.baihao.compat.XaeroMinimapCompat;

public enum MapType {
    NONE("baihao.map.none.name","baihao.map.none.description", null),
    X3D("baihao.map.x3d.name","baihao.map.x3d.description", "x3dmap_example.png"){
        @Override
        public void setEnable(boolean enable) {
            ClientConfig.MINIMAP_ENABLED.set(enable);
            ClientConfig.SPEC.save();
        }
        
        @Override
        public boolean valid() {
            return ModList.get().isLoaded("x3d_map");
        }
    },
    GZT("baihao.map.gzt.name","baihao.map.gzt.description", "gzt.png"){
        @Override
        public void setEnable(boolean enable) {
            AleeveAtlasClient.CONFIG.display = enable;
        }
        
        @Override
        public boolean valid() {
            return ModList.get().isLoaded("aleeve_atlas");
        }
    },
    JMAP("baihao.map.jmap.name","baihao.map.jmap.description", null){
        @Override
        public void setEnable(boolean enable) {
            JourneyMapCompat.setMiniMapEnabled(enable);
        }
        
        @Override
        public boolean valid() {
            return ModList.get().isLoaded("journeymap");
        }
    },
    XMAP("baihao.map.xmap.name","baihao.map.xmap.description", null){
        @Override
        public void setEnable(boolean enable) {
            XaeroMinimapCompat.setMiniMapEnabled(enable);
        }
        
        @Override
        public boolean valid() {
            return ModList.get().isLoaded("xaerominimap");
        }
    };
    
    public final String nameKey;
    public final String descriptionKey;
    public final @Nullable String imgPath;
    
    MapType(String nameKey, String descriptionKey, @Nullable String imgPath) {
        this.nameKey = nameKey;
        this.descriptionKey = descriptionKey;
        this.imgPath = imgPath;
    }
    
    public void setEnable(boolean enable) {
    
    }
    
    public boolean valid(){
        return true;
    }
    
}
