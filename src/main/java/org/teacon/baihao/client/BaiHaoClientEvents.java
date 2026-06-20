package org.teacon.baihao.client;

import org.teacon.baihao.Baihao;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
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
                        })));
    }
    
    @SubscribeEvent
    private static void onClientPlayerLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        MapSwitchManager.applySelectedMapType();
    }
}
