package com.moonstone.moonstonemod.mixin.si_mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.client.IAdvancementWidget;
import com.ytgld.seeking_immortals.client.WidgetTypes;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(AdvancementWidget.class)
public class AdvancementWidgetMixin implements IAdvancementWidget {
    @Shadow @Final private int x;

    @Shadow @Final private int width;

    @Shadow @Final private AdvancementTab tab;

    @Shadow @Nullable private AdvancementProgress progress;

    @Shadow @Final private List<FormattedCharSequence> description;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private int y;

    @Shadow @Final private static ResourceLocation TITLE_BOX_SPRITE;

    @Shadow @Final private DisplayInfo display;

    @Shadow @Final private FormattedCharSequence title;

    @Shadow @Final private List<AdvancementWidget> children;

    @Shadow @Final private AdvancementNode advancementNode;

    @Override
    public void seekingImmortals$draw(GuiGraphics guiGraphics, int x, int y) {
        if (!this.display.isHidden() || this.progress != null && this.progress.isDone()) {
            float f = this.progress == null ? 0.0F : this.progress.getPercent();
            WidgetTypes advancementwidgettype;
            if (f >= 1.0F) {
                advancementwidgettype = WidgetTypes.OBTAINED;
            } else {
                advancementwidgettype = WidgetTypes.UNOBTAINED;
            }

            guiGraphics.blitSprite(advancementwidgettype.frameSprite(this.display.getType()), x + this.x + 3, y + this.y, 26, 26);
            guiGraphics.renderFakeItem(this.display.getIcon(), x + this.x + 8, y + this.y + 5);
        }
        for (AdvancementWidget advancementwidget : this.children) {
            if (advancementwidget instanceof IAdvancementWidget advancementWidget) {
                advancementWidget.seekingImmortals$draw(guiGraphics, x, y);
            }
        }

    }

    @Override
    public void seekingImmortals$drawHover(GuiGraphics guiGraphics, int x, int y, float fade, int width, int height) {
        boolean flag = width + x + this.x + this.width + 26 >= this.tab.getScreen().width;
        Component component = this.progress == null ? null : this.progress.getProgressText();
        int i = component == null ? 0 : this.minecraft.font.width(component);
        boolean flag1 = 113 - y - this.y - 26 <= 6 + this.description.size() * 9;
        float f = this.progress == null ? 0.0F : this.progress.getPercent();
        int j = Mth.floor(f * (float)this.width);
        WidgetTypes advancementwidgettype;
        WidgetTypes advancementwidgettype1;
        WidgetTypes advancementwidgettype2;
        if (f >= 1.0F) {
            j = this.width / 2;
            advancementwidgettype = WidgetTypes.OBTAINED;
            advancementwidgettype1 = WidgetTypes.OBTAINED;
            advancementwidgettype2 = WidgetTypes.OBTAINED;
        } else if (j < 2) {
            j = this.width / 2;
            advancementwidgettype = WidgetTypes.UNOBTAINED;
            advancementwidgettype1 = WidgetTypes.UNOBTAINED;
            advancementwidgettype2 = WidgetTypes.UNOBTAINED;
        } else if (j > this.width - 2) {
            j = this.width / 2;
            advancementwidgettype = WidgetTypes.OBTAINED;
            advancementwidgettype1 = WidgetTypes.OBTAINED;
            advancementwidgettype2 = WidgetTypes.UNOBTAINED;
        } else {
            advancementwidgettype = WidgetTypes.OBTAINED;
            advancementwidgettype1 = WidgetTypes.UNOBTAINED;
            advancementwidgettype2 = WidgetTypes.UNOBTAINED;
        }

        int k = this.width - j;
        RenderSystem.enableBlend();
        int l = y + this.y;
        int i1;
        if (flag) {
            i1 = x + this.x - this.width + 26 + 6;
        } else {
            i1 = x + this.x;
        }

        int j1 = 32 + this.description.size() * 9;
        if (!this.description.isEmpty()) {
            if (flag1) {
                guiGraphics.blitSprite(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"advancements/box"), i1, l + 26 - j1, this.width, j1);
            } else {
                guiGraphics.blitSprite(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"advancements/box"), i1, l, this.width, j1);
            }
        }

        guiGraphics.blitSprite(advancementwidgettype.boxSprite(), 200, 26, 0, 0, i1, l, j, 26);
        guiGraphics.blitSprite(advancementwidgettype1.boxSprite(), 200, 26, 200 - k, 0, i1 + j, l, k, 26);
        guiGraphics.blitSprite(advancementwidgettype2.frameSprite(this.display.getType()), x + this.x + 3, y + this.y, 26, 26);

        if (flag) {
            guiGraphics.drawString(this.minecraft.font, this.title, i1 + 5, y + this.y + 9, -1);
            if (component != null) {
                guiGraphics.drawString(this.minecraft.font, component, x + this.x - i, y + this.y + 9, -1);
            }
        } else {
            guiGraphics.drawString(this.minecraft.font, this.title, x + this.x + 32, y + this.y + 9, -1);
            if (component != null) {
                guiGraphics.drawString(this.minecraft.font, component, x + this.x + this.width - i - 5, y + this.y + 9, -1);
            }
        }

        int k1;
        if (flag1) {
            for(k1 = 0; k1 < this.description.size(); ++k1) {
                guiGraphics.drawString(this.minecraft.font,
                        this.description.get(k1), i1 + 5, l + 26 - j1 + 7 + k1 * 9, -5592406, false);
            }
        } else {
            for(k1 = 0; k1 < this.description.size(); ++k1) {
                guiGraphics.drawString(this.minecraft.font,
                        this.description.get(k1), i1 + 5, y + this.y + 9 + 17 + k1 * 9, -5592406, false);
            }
        }

        guiGraphics.renderFakeItem(this.display.getIcon(), x + this.x + 8, y + this.y + 5);
    }

}
