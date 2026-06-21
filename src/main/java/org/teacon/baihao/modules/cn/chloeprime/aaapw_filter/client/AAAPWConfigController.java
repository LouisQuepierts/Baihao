package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.client;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;

import java.util.Set;

public final class AAAPWConfigController {
    public static final Set<String> FILTER_EXEMPTS = Set.of("enable_fireflies");

    public static void update(boolean enabled) {
        ClientConfig.ENABLE_CRIT.set(enabled);
        ClientConfig.ENABLE_MAGIC_CRIT.set(enabled);
        ClientConfig.ENABLE_LIGHTNING.set(enabled);
        ClientConfig.ENABLE_SMALL_EXPLOSION.set(enabled);
        ClientConfig.ENABLE_BIG_EXPLOSION.set(enabled);
        ClientConfig.ENABLE_LOOT_BEAM.set(enabled);
        ClientConfig.ENABLE_LOOT_SOUND.set(enabled);
        ClientConfig.ENABLE_FIREBALL_TRAIL.set(enabled);
        ClientConfig.MC26_1_REMOVE_POOF_FOR_SMALL_EXPLOSION.set(enabled);
        ClientConfig.MC26_1_REMOVE_POOF_FOR_LARGE_EXPLOSION.set(enabled);
        ClientConfig.SPEC.save();
    }

    private AAAPWConfigController() {
    }
}
