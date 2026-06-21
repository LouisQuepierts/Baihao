package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber
public final class AAAPWFItems {
    static final DeferredRegister.Items DFR = DeferredRegister.createItems(AAAPWF.MOD_ID);
    public static final Supplier<Item> THE_FILTER = DFR.registerItem(
            "the_filter",
            CogitoFilterItem::new,
            prop -> prop.component(DataComponents.LORE, new ItemLore(List.of(
                    line("item.aaapw_filter.the_filter.tooltip.0"),
                    line("item.aaapw_filter.the_filter.tooltip.1")))));

    private static Component line(String key) {
        return Component.translatable(key).withStyle(ChatFormatting.GRAY);
    }

    @SubscribeEvent
    private static void addToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (CreativeModeTabs.TOOLS_AND_UTILITIES.equals(event.getTabKey())) {
            event.accept(THE_FILTER.get());
        }
    }

    private AAAPWFItems() {
    }
}
