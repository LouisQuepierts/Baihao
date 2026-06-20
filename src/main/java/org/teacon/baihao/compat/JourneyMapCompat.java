package org.teacon.baihao.compat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.neoforged.fml.ModList;

public final class JourneyMapCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String MOD_ID = "journeymap";
    private static final String UI_MANAGER_CLASS = "journeymap.client.ui.UIManager";
    private static final String INSTANCE_FIELD = "INSTANCE";
    private static final String SET_MINI_MAP_ENABLED_METHOD = "setMiniMapEnabled";
    
    private JourneyMapCompat() {
    
    }
    
    public static void setMiniMapEnabled(boolean enable) {
        if (!ModList.get().isLoaded(MOD_ID)) {
            return;
        }
        try {
            Class<?> uiManagerClass = Class.forName(UI_MANAGER_CLASS);
            Object uiManager = uiManagerClass.getField(INSTANCE_FIELD).get(null);
            Method method = uiManagerClass.getMethod(SET_MINI_MAP_ENABLED_METHOD, boolean.class);
            method.invoke(uiManager, enable);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | IllegalAccessException exception) {
            LOGGER.error("Failed to access JourneyMap minimap switch.", exception);
            throw new IllegalStateException("Failed to access JourneyMap minimap switch.", exception);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            LOGGER.error("Failed to switch JourneyMap minimap.", cause);
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            if (cause instanceof Error error) {
                throw error;
            }
            throw new IllegalStateException("Failed to switch JourneyMap minimap.", cause);
        }
    }
}
