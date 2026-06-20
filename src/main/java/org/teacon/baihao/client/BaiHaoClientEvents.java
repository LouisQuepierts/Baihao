package org.teacon.baihao.client;

import org.teacon.baihao.ClientConfig;
import org.teacon.baihao.Baihao;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = Baihao.MODID)
public final class BaiHaoClientEvents {
    
    @SubscribeEvent
    private static void registerClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("baihao")
                .then(Commands.literal("map_switch")
                        .executes(_ -> {
                            Minecraft.getInstance().setScreen(new MapSwitchScreen());
                            return 1;
                        }))
                .then(Commands.literal("hide_map_switch_motd")
                        .executes(_ -> {
                            ClientConfig.setShowMotd(false);
                            return 1;
                        })));
    }
    
    @SubscribeEvent
    private static void onClientPlayerLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        MapSwitchManager.applySelectedMapType();
        if (ClientConfig.SHOW_MOTD.get()) {
            event.getPlayer().sendSystemMessage(createMotdMessage());
        }
    }
    
    private static MutableComponent createMotdMessage() {
        MutableComponent openScreen = Component.translatable("baihao.motd.select_map")
                .withStyle(style -> style.withColor(ChatFormatting.GREEN)
                        .withClickEvent(new ClickEvent.RunCommand("/baihao map_switch")));
//        MutableComponent hideMotd = Component.translatable("baihao.motd.hide")
//                .withStyle(style -> style.withColor(ChatFormatting.RED)
//                        .withClickEvent(new ClickEvent.RunCommand("/baihao hide_map_switch_motd")));
        return Component.empty().append(openScreen);
    }
}
