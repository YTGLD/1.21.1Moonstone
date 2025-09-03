package com.ytgld.seeking_immortals.item.an_element;

import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NightmareTooltip implements ClientTooltipComponent, TooltipComponent {
    private final AllElement element;
    private final ItemStack stack;

    public NightmareTooltip(AllElement contents, ItemStack stack) {
        this.element = contents;
        this.stack = stack;
    }

    @Override
    public int getHeight() {
        if (Screen.hasAltDown()){
            return  this.backgroundHeight() + 4;
        }
        return 0;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        if (Screen.hasAltDown()){
            return this.backgroundWidth();
        }
        return 0;
    }

    private int backgroundWidth() {
        return this.gridSizeX() * 32;
    }

    private int backgroundHeight() {
        return this.gridSizeY() * 32;
    }

    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        if (Screen.hasAltDown()) {
            int i = this.gridSizeX();
            int j = this.gridSizeY();
            int s = 0;
            for (int l = 0; l < j; ++l) {
                for (int i1 = 0; i1 < i; ++i1) {
                    int j1 = x + i1 * 32;
                    int k1 = y + l * 32;
                    s++;
                    this.renderSlot(font, j1, k1, guiGraphics,s);
                }
            }
        }
    }

    private void renderSlot(Font font,int x, int y, GuiGraphics guiGraphics,int i) {
        i --;
        Map<Element, ResourceLocation> resourceLocationMap = element.name();
        Map<Element, Integer> map = element.element(stack);
        Map<Element, String> stringMap = element.tooltip();

        if (resourceLocationMap!=null) {
            Element elt = resourceLocationMap.keySet().stream().toList().get(i);
            ResourceLocation resourceLocation1 = resourceLocationMap.get(elt);
            guiGraphics.blitSprite(resourceLocation1, x, y, 32, 32);
        }
        if (stringMap!=null) {
            Element elt = stringMap.keySet().stream().toList().get(i);
            String string= stringMap.get(elt);
            guiGraphics.drawString(font, string, x+28, y+28, 0xFFFFEC8B, false);
        }
        if (map!=null) {
            Element elt = map.keySet().stream().toList().get(i);
            int number = map.get(elt);
            guiGraphics.drawString(font, String.valueOf(number), x+20, y+20, 0xFFFFE7BA, false);

        }
    }

    private int gridSizeX() {
        Map<Element, Integer> map = element.element(stack);
        if (map!=null) {
            return map.size();
        }else return 0;
    }

    private int gridSizeY() {
        return 1;
    }
}
