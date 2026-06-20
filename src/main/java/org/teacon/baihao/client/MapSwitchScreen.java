package org.teacon.baihao.client;

import java.util.Arrays;
import java.util.List;

import org.joml.Matrix3x2fStack;
import org.teacon.baihao.Baihao;
import org.teacon.baihao.ClientConfig;
import org.teacon.baihao.map.MapType;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;

public class MapSwitchScreen extends Screen {
    private static final Component TITLE = Component.translatable("baihao.screen.map_switch.title");
    private static final int SIDE_MARGIN = 24;
    private static final int CARD_GAP = 12;
    private static final int MAX_CARD_WIDTH = 220;
    private static final int TITLE_GAP = 28;
    private static final int TITLE_HEIGHT = 18;
    private static final float TITLE_SCALE = 2.0F;
    
    public MapSwitchScreen() {
        super(TITLE);
    }
    
    @Override
    protected void init() {
        List<MapType> mapTypes = Arrays.stream(MapType.values()).filter(MapType::valid).toList();
        if (mapTypes.isEmpty()) {
            return;
        }
        int cardHeight = this.height / 2;
        int cardTop = (this.height - cardHeight) / 2;
        int availableWidth = this.width - SIDE_MARGIN * 2;
        int cardWidth = (availableWidth - CARD_GAP * (mapTypes.size() - 1)) / mapTypes.size();
        cardWidth = Math.min(cardWidth, MAX_CARD_WIDTH);
        int startX = SIDE_MARGIN + (availableWidth - cardWidth * mapTypes.size() - CARD_GAP * (mapTypes.size() - 1)) / 2;
        for (int index = 0; index < mapTypes.size(); index++) {
            int x = startX + index * (cardWidth + CARD_GAP);
            this.addRenderableWidget(new MapTypeCard(x, cardTop, cardWidth, cardHeight, mapTypes.get(index)));
        }
    }
    
    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int titleY = (this.height - this.height / 2) / 2 - TITLE_HEIGHT - TITLE_GAP;
        drawCenteredScaledText(graphics, this.font, TITLE, this.width / 2, titleY, TITLE_SCALE, 0xFFFFFFFF);
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
    }
    
    private class MapTypeCard extends AbstractWidget {
        private static final int BACKGROUND_COLOR = 0xCC1F2430;
        private static final int SELECTED_BACKGROUND_COLOR = 0xCC263B35;
        private static final int BORDER_COLOR = 0xFF4C566A;
        private static final int SELECTED_BORDER_COLOR = 0xFF7FD28A;
        private static final int HOVER_BORDER_COLOR = 0xFFE5E9F0;
        private static final int TEXT_COLOR = 0xFFFFFFFF;
        private static final int DESCRIPTION_COLOR = 0xFFB8C0CC;
        private static final int CARD_PADDING = 18;
        private static final int CONTENT_GAP = 12;
        private static final int NAME_HEIGHT = 16;
        private static final int DESCRIPTION_HEIGHT = 9;
        private static final float IMAGE_WIDTH_RATIO = 0.76F;
        private static final float IMAGE_MAX_HEIGHT_RATIO = 0.45F;
        private static final float NAME_SCALE = 1.5F;
        
        private final MapType mapType;
        
        private MapTypeCard(int x, int y, int width, int height, MapType mapType) {
            super(x, y, width, height, Component.translatable(mapType.nameKey));
            this.mapType = mapType;
        }
        
        @Override
        protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
            boolean selected = ClientConfig.SELECTED_MAP_TARGET.get() == this.mapType;
            int borderColor = selected ? SELECTED_BORDER_COLOR : BORDER_COLOR;
            if (this.isHovered()) {
                borderColor = HOVER_BORDER_COLOR;
            }
            graphics.fill(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), selected ? SELECTED_BACKGROUND_COLOR : BACKGROUND_COLOR);
            graphics.outline(this.getX(), this.getY(), this.getWidth(), this.getHeight(), borderColor);
            int centerX = this.getX() + this.getWidth() / 2;
            int contentTop = this.getY() + CARD_PADDING;
            int contentBottom = this.getY() + this.getHeight() - CARD_PADDING;
            int contentAreaHeight = contentBottom - contentTop;
            int imageSize = this.getImageSize(contentAreaHeight);
            int textBlockHeight = NAME_HEIGHT + DESCRIPTION_HEIGHT + CONTENT_GAP;
            int contentHeight = textBlockHeight;
            if (this.mapType.imgPath != null) {
                contentHeight += imageSize + CONTENT_GAP;
            }
            int contentY = contentTop + (contentAreaHeight - contentHeight) / 2;
            int nameY = contentY;
            if (this.mapType.imgPath != null) {
                drawImage(graphics, this.mapType, centerX, contentY, imageSize);
                nameY += imageSize + CONTENT_GAP;
            }
            drawCenteredScaledText(graphics, MapSwitchScreen.this.font, Component.translatable(this.mapType.nameKey), centerX, nameY, NAME_SCALE, TEXT_COLOR);
            graphics.centeredText(MapSwitchScreen.this.font, Component.translatable(this.mapType.descriptionKey), centerX, nameY + NAME_HEIGHT + CONTENT_GAP, DESCRIPTION_COLOR);
            this.handleCursor(graphics);
        }
        
        private int getImageSize(int contentAreaHeight) {
            int maxByWidth = Math.round((this.getWidth() - CARD_PADDING * 2) * IMAGE_WIDTH_RATIO);
            int maxByHeight = Math.round(contentAreaHeight * IMAGE_MAX_HEIGHT_RATIO);
            return Math.min(maxByWidth, maxByHeight);
        }
        
        @Override
        public void onClick(MouseButtonEvent event, boolean doubleClick) {
            ClientConfig.setSelectedMapTarget(this.mapType);
        }
        
        @Override
        public void updateWidgetNarration(NarrationElementOutput output) {
            this.defaultButtonNarrationText(output);
        }
    }
    
    private static void drawImage(GuiGraphicsExtractor graphics, MapType mapType, int centerX, int y, int size) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Baihao.MODID, "textures/gui/" + mapType.imgPath);
        graphics.blit(identifier, centerX - size / 2, y, centerX + size / 2, y + size, 0.0F, 1.0F, 0.0F, 1.0F);
    }
    
    private static void drawCenteredScaledText(GuiGraphicsExtractor graphics, Font font, Component component, int centerX, int y, float scale, int color) {
        FormattedCharSequence text = component.getVisualOrderText();
        Matrix3x2fStack pose = graphics.pose();
        pose.pushMatrix();
        pose.translate(centerX - font.width(text) * scale / 2.0F, y);
        pose.scale(scale, scale);
        graphics.text(font, text, 0, 0, color, false);
        pose.popMatrix();
    }
}
