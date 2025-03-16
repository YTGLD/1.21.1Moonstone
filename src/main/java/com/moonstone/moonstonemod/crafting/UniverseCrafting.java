package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.universe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class UniverseCrafting  extends CustomRecipe {
    private final CraftingBookCategory category;


    public UniverseCrafting(CraftingBookCategory category) {
        super(category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
        boolean hasFirstItem = false;
        boolean hasSecondItem = false;

        ItemStack me = ItemStack.EMPTY;
        ItemStack other = ItemStack.EMPTY;


        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i).copy();
            if (!itemStack.isEmpty()) {
                if (itemStack.is(Items.universe.get())) {
                    if (itemStack.get(DataReg.tag) == null) {
                        itemStack.set(DataReg.tag, new CompoundTag());
                    }

                    if (itemStack.get(DataReg.tag) != null
                            && itemStack.get(DataReg.tag).getInt(universe.doAsUniverse) < universe.universeSize) {
                        me =itemStack;
                        hasFirstItem = true;
                    }
                }
            }
        }
        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i).copy();
            if (!itemStack.is(Items.universe.get())) {
                if (BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals(MoonStoneMod.MODID)) {
                    if (itemStack.getItem() instanceof ICurioItem) {
                        other = itemStack;
                        hasSecondItem = true;
                    }
                }
            }
        }

        if (other == me){
            return false;
        }
        return hasFirstItem && hasSecondItem;
    }





    public void addTag(ItemStack itemStack) {
        if (itemStack.get(DataReg.tag) == null) {
            itemStack.set(DataReg.tag, new CompoundTag());
        }
        itemStack.get(DataReg.tag);
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        ItemStack universeItem = ItemStack.EMPTY;

        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i).copy();
            if (itemStack.is(Items.universe.get())) {
                addTag(itemStack);
                if (itemStack.get(DataReg.tag).getInt(universe.doAsUniverse) < universe.universeSize) {
                    universeItem = itemStack;
                    break;
                }
            }
        }

        if (universeItem.isEmpty())
            return ItemStack.EMPTY;

        for (int j = 0; j < craftingInput.size(); ++j) {
            ItemStack other = craftingInput.getItem(j).copy();
            if (!other.isEmpty() && other.getItem() instanceof ICurioItem && BuiltInRegistries.ITEM.getKey(other.getItem()).getNamespace().equals(MoonStoneMod.MODID)) {
                String name = other.getItem().getDescriptionId();
                universeItem.get(DataReg.tag).putString(name, name);
                universeItem.get(DataReg.tag).putInt(universe.doAsUniverse, universeItem.get(DataReg.tag).getInt(universe.doAsUniverse) + 1);
            }
        }

        return universeItem;
    }


    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.UniverseCrafting.get();
    }
}

