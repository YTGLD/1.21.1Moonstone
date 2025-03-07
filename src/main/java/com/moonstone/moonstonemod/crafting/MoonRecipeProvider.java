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
        SpecialRecipeBuilder.special(RecipeGodAmbush::new).save(recipeOutput, "god_ambush");
        SpecialRecipeBuilder.special(RecipeGodAtpoverdose::new).save(recipeOutput, "god_atpoverdose");
        SpecialRecipeBuilder.special(RecipeGodPutrefactive::new).save(recipeOutput, "god_putrefactive");

        SpecialRecipeBuilder.special(RecipeGodFermentation::new).save(recipeOutput, "god_fermentation");
        SpecialRecipeBuilder.special(RecipeGodAutolytic::new).save(recipeOutput, "god_autolytic");
        SpecialRecipeBuilder.special(RecipeGodRegenerative::new).save(recipeOutput, "god_regenerative");

    }
}
