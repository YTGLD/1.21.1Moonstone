package com.moonstone.moonstonemod.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MoonRecipeProvider extends VanillaRecipeProvider {
    public MoonRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        SpecialRecipeBuilder.special(RecipeDNA::new).save(recipeOutput, "necora");
        SpecialRecipeBuilder.special(RecipeCell::new).save(recipeOutput, "cell");
        SpecialRecipeBuilder.special(RecipeGiant::new).save(recipeOutput, "giant");
        SpecialRecipeBuilder.special(RecipeNightmare::new).save(recipeOutput, "giant_nightmare");
    }
}
