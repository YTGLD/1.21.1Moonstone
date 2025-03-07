package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCrafting {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER_REGISTRY = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MoonStoneMod.MODID);


    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodDNA =
            RECIPE_SERIALIZER_REGISTRY.register("god_ambush",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodAmbush::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodAtpoverdose =
            RECIPE_SERIALIZER_REGISTRY.register("god_atpoverdose",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodAtpoverdose::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodPutrefactive =
            RECIPE_SERIALIZER_REGISTRY.register("god_putrefactive",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodPutrefactive::new));


    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodFermentation =
            RECIPE_SERIALIZER_REGISTRY.register("god_fermentation",
            ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodFermentation::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodAutolytic =
            RECIPE_SERIALIZER_REGISTRY.register("god_autolytic",
                    ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodAutolytic::new));
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<?>> RecipeGodRegenerative =
            RECIPE_SERIALIZER_REGISTRY.register("god_regenerative",
                    ()-> new SimpleCraftingRecipeSerializer<>(RecipeGodRegenerative::new));

}
