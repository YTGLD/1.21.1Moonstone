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

public class RecipeCell extends CustomRecipe {
    private final CraftingBookCategory category;

    public RecipeCell(CraftingBookCategory category) {
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
        int cell_god = countItem(craftingInput, DNAItems.cell_god.get());
        int speed_metabolism = countItem(craftingInput, DNAItems.speed_metabolism.get());
        int cell_disorder = countItem(craftingInput, DNAItems.cell_disorder.get());
        int cell_necrosis = countItem(craftingInput, DNAItems.cell_necrosis.get());
        int ectoplasmprism = countItem(craftingInput, Items.ectoplasmprism.get());
        int ectoplasmball = countItem(craftingInput, Items.ectoplasmball.get());

        return
                (
                        atpHeightCount>=20&&
                                cell_god>=16&&
                                speed_metabolism>=40&&
                                cell_disorder>=64&&
                                cell_necrosis>=24&&
                                ectoplasmprism>=1&&
                                ectoplasmball>=2
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
        return Items.cell.get().getDefaultInstance();
    }

    @Override
    
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.RecipeCell.get();
    }
}

