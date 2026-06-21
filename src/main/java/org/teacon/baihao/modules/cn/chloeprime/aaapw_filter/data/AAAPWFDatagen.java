package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.data;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber
public final class AAAPWFDatagen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        event.createProvider(AAAPWFModelProvider::new);
        event.createProvider(AAAPFWItemTagProvider::new);
    }

    private AAAPWFDatagen() {
    }
}
