package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCrafting {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER_REGISTRY = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MoonStoneMod.MODID);


    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> DNA_SERIALIZER = RECIPE_SERIALIZER_REGISTRY.register("necora",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeDNA::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeCell = RECIPE_SERIALIZER_REGISTRY.register("cell",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeCell::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGiant = RECIPE_SERIALIZER_REGISTRY.register("giant",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeGiant::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeNightmare = RECIPE_SERIALIZER_REGISTRY.register("giant_nightmare",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeNightmare::new));



}
