package com.ytgld.seeking_immortals.item.an_element;

import com.ytgld.seeking_immortals.item.an_element.elements.*;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Map;

public interface AllElement {
    Destiny destiny = new Destiny();
    Death death = new Death();
    Transmigrate transmigrate = new Transmigrate();
    Nightmare nightmare = new Nightmare();
    Despair despair = new Despair();


    @Nullable
    Map<Element, ResourceLocation> name();
    @Nullable
    Map<Element, String>  tooltip();
    @Nullable
    Map<Element, Integer> element(ItemStack stack);


}
