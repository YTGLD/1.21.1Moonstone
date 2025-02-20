package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RecipeNightmare extends CustomRecipe {
    private final CraftingBookCategory category;

    public RecipeNightmare(CraftingBookCategory category) {
        super(category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        int atp_height = countItem(craftingInput, DNAItems.atp_height.get());
        int speed_metabolism = countItem(craftingInput, DNAItems.speed_metabolism.get());
        int cell_dna_suppression = countItem(craftingInput, DNAItems.cell_dna_suppression.get());
        int cell_synthesis = countItem(craftingInput, DNAItems.cell_synthesis.get());
        int cell_darwin = countItem(craftingInput, DNAItems.cell_darwin.get());
        int cell_off_on = countItem(craftingInput, DNAItems.cell_off_on.get());

        int ectoplasmprism = countItem(craftingInput, Items.ectoplasmprism.get());
        int giant = countItem(craftingInput, Items.giant.get());

        return
                (
                        atp_height>=64&&
                                speed_metabolism>=16&&
                                cell_dna_suppression>=64&&
                                cell_synthesis>=64&&
                                cell_darwin>=64&&
                                cell_off_on>=64&&
                                ectoplasmprism>=1&&
                                giant>=1
                );
    }
    private int countItem(CraftingInput craftingInput, Item item) {
        int count = 0;
        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack thisStack = craftingInput.getItem(i);
            if (!thisStack.isEmpty() && thisStack.is(item)) {
                count += thisStack.getCount();
            }
        }
        return count;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        return Items.giant_nightmare.get().getDefaultInstance();
    }

    @Override
    
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.RecipeNightmare.get();
    }
}



