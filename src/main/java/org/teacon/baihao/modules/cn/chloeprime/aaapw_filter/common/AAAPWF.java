package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common;

import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.AAAParticlesWorldFilter;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.ApiStatus;

public final class AAAPWF {
    public static final String MOD_ID = AAAParticlesWorldFilter.MOD_ID;

    @ApiStatus.Internal
    public static void init(IEventBus bus) {
        AAAPWFItems.DFR.register(bus);
        AAAPWFAttachments.DFR.register(bus);
    }

    private AAAPWF() {
    }
}
