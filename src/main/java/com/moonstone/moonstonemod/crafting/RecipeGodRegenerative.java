package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.event.old.EquippedEvt;
import com.moonstone.moonstonemod.event.old.NewEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RecipeGodRegenerative  extends CustomRecipe {
    private final CraftingBookCategory category;

    public final Item in = Items.regenerative.get();
    public final Item out = Items.god_regenerative.get();

    public RecipeGodRegenerative(CraftingBookCategory category) {
        super(category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
        int count = 0;
        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);

            if (currentStack.get(DataReg.tag) != null && currentStack.get(DataReg.tag).getBoolean(NewEvent.lootTable)) {
                if (currentStack.is(in)) {
                    count++;
                    if (count > 2) {
                        return false;
                    }
                }
            }
        }
        return count == 2;
    }




    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {

        ItemStack stack = out.getDefaultInstance();
        CompoundTag tag  = new CompoundTag();
        tag.putBoolean(EquippedEvt.isGod,true);
        stack.set(DataReg.tag,tag);

        return stack;
    }

    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.RecipeGodRegenerative.get();
    }
}

