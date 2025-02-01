package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCrafting {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER_REGISTRY = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MoonStoneMod.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> DNA_SERIALIZER = RECIPE_SERIALIZER_REGISTRY.register("necora",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeDNA::new));
}
