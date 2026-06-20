package org.teacon.baihao.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MapSwitchScreen extends Screen {
    private static final Component TITLE = Component.literal("Map Switch");
    
    public MapSwitchScreen() {
        super(TITLE);
    }
    
    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(Component.literal("Close"), button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height / 2 - 10, 100, 20)
                .build());
    }
}
