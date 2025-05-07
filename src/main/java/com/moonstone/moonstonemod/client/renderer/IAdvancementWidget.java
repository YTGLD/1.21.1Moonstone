package com.moonstone.moonstonemod.client.renderer;

import net.minecraft.client.gui.GuiGraphics;

public interface IAdvancementWidget {
    void moonstone1$draw(GuiGraphics guiGraphics, int x, int y);

    void moonstone1$drawHover(GuiGraphics guiGraphics, int x, int y, float fade, int width, int height);


    void moonstone1$drawNecora(GuiGraphics guiGraphics, int x, int y);

    void moonstone1$drawHoverNecora(GuiGraphics guiGraphics, int x, int y, float fade, int width, int height);
}
