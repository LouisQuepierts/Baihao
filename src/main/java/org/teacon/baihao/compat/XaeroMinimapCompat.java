package org.teacon.baihao.compat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.neoforged.fml.ModList;

public final class XaeroMinimapCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String MOD_ID = "xaerominimap";
    private static final String HUD_MOD_CLASS = "xaero.common.HudMod";
    private static final String BUILT_IN_HUD_MODULES_CLASS = "xaero.hud.minimap.BuiltInHudModules";
    private static final String CLIENT_CONFIG_MANAGER_CLASS = "xaero.lib.client.config.ClientConfigManager";
    private static final String INSTANCE_FIELD = "INSTANCE";
    private static final String MINIMAP_FIELD = "MINIMAP";
    private static final String GET_HUD_CONFIGS_METHOD = "getHudConfigs";
    private static final String GET_CLIENT_CONFIG_MANAGER_METHOD = "getClientConfigManager";
    private static final String SET_ACTIVE_METHOD = "setActive";
    
    private XaeroMinimapCompat() {
    
    }
    
    public static void setMiniMapEnabled(boolean enable) {
        if (!ModList.get().isLoaded(MOD_ID)) {
            return;
        }
        try {
            Class<?> hudModClass = Class.forName(HUD_MOD_CLASS);
            Class<?> builtInHudModulesClass = Class.forName(BUILT_IN_HUD_MODULES_CLASS);
            Class<?> clientConfigManagerClass = Class.forName(CLIENT_CONFIG_MANAGER_CLASS);
            Object hudMod = hudModClass.getField(INSTANCE_FIELD).get(null);
            Object hudConfigs = hudModClass.getMethod(GET_HUD_CONFIGS_METHOD).invoke(hudMod);
            Object configManager = hudConfigs.getClass().getMethod(GET_CLIENT_CONFIG_MANAGER_METHOD).invoke(hudConfigs);
            Object minimapModule = builtInHudModulesClass.getField(MINIMAP_FIELD).get(null);
            Method setActive = minimapModule.getClass().getMethod(SET_ACTIVE_METHOD, clientConfigManagerClass, boolean.class);
            setActive.invoke(minimapModule, configManager, enable);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | IllegalAccessException exception) {
            LOGGER.error("Failed to access Xaero Minimap switch.", exception);
            throw new IllegalStateException("Failed to access Xaero Minimap switch.", exception);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            LOGGER.error("Failed to switch Xaero Minimap.", cause);
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            if (cause instanceof Error error) {
                throw error;
            }
            throw new IllegalStateException("Failed to switch Xaero Minimap.", cause);
        }
    }
}
