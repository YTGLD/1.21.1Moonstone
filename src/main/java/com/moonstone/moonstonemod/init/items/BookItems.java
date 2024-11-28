package com.moonstone.moonstonemod.init.items;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BookItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BookItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, MoonStoneMod.MODID);

    public static final String blood_stasisTAG ="blood_stasisTAG";
    public static final String bone_structureTAG ="bone_structureTAG";
    public static final String mummificationTAG ="mummificationTAG";
    public static final String organizational_regenerationTAG ="organizational_regenerationTAG";
    public static final String tumourTAG ="tumourTAG";


    public static final DeferredHolder<Item, ?> blood_stasis =
            REGISTRY.register("blood_stasis",()-> new BookItem(
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    0.1f,
            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ,"item.blood_stasis.tool.string"));
    public static final DeferredHolder<Item, ?> bone_structure =
            REGISTRY.register("bone_structure",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ARMOR,
                    0.12f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.bone_structure.tool.string"));
    public static final DeferredHolder<Item, ?> mummification =
            REGISTRY.register("mummification",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    6,
                    AttributeModifier.Operation.ADD_VALUE
                    ,"item.mummification.tool.string"));
    public static final DeferredHolder<Item, ?> organizational_regeneration =
            REGISTRY.register("organizational_regeneration",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    AttReg.heal,
                    0.1f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.organizational_regeneration.tool.string"));
    public static final DeferredHolder<Item, ?> tumour =
            REGISTRY.register("tumour",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    AttReg.cit,
                    0.1f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.tumour.tool.string"));

}
