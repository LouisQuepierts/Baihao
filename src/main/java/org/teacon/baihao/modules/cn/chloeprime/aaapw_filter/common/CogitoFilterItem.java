package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common;

import com.google.common.collect.MapMaker;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Collections;
import java.util.Set;

@EventBusSubscriber
public class CogitoFilterItem extends Item {
    public CogitoFilterItem(Properties properties) {
        super(properties);
        REGISTERED.add(this);
    }

    private static final Set<ItemLike> REGISTERED = Collections.newSetFromMap(new MapMaker().weakKeys().makeMap());

    public static class CurioCapability implements ICurio {
        private final ItemStack stack;

        public CurioCapability(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public ItemStack getStack() {
            return this.stack;
        }

        @Override
        public void onEquip(SlotContext context, ItemStack previous) {
            if (context.entity() != null) {
                context.entity().setData(AAAPWFAttachments.AAAPW_ENABLED, true);
            }
        }

        @Override
        public void onUnequip(SlotContext context, ItemStack replacement) {
            if (context.entity() != null) {
                context.entity().setData(AAAPWFAttachments.AAAPW_ENABLED, false);
            }
        }
    }

    @SubscribeEvent
    private static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(CuriosCapability.ITEM, (stack, _) -> new CurioCapability(stack), REGISTERED.toArray(ItemLike[]::new));
    }
}
