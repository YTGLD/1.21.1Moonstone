package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RecipeDNA extends CustomRecipe {
    private final CraftingBookCategory category;

    public RecipeDNA(CraftingBookCategory category) {
        super(category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        int atpHeightCount = countItem(craftingInput, DNAItems.atp_height.get());
        int cellDarwinCount = countItem(craftingInput, DNAItems.cell_darwin.get());
        int cellDisorderCount = countItem(craftingInput, DNAItems.cell_disorder.get());
        int cell_off_on = countItem(craftingInput, DNAItems.cell_off_on.get());
        int speed_metabolism = countItem(craftingInput, DNAItems.speed_metabolism.get());
        int cell_synthesis = countItem(craftingInput, DNAItems.cell_synthesis.get());
        int cell_dna_suppression = countItem(craftingInput, DNAItems.cell_dna_suppression.get());
        int cell_necrosis = countItem(craftingInput, DNAItems.cell_necrosis.get());

        int ectoplasmprism = countItem(craftingInput, Items.ectoplasmprism.get());

        return
                (
                        atpHeightCount >= 48
                                && cellDarwinCount >= 32
                                && cell_off_on >= 64
                                && speed_metabolism >= 24
                                && cellDisorderCount >= 20
                                && cell_synthesis >= 12
                                && cell_dna_suppression >= 24
                                && cell_necrosis >= 8
                                && ectoplasmprism >= 1
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
        return Items.necora.get().getDefaultInstance();
    }

    @Override
    
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.DNA_SERIALIZER.get();
    }
}
