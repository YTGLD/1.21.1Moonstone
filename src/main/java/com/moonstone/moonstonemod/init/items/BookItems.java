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

    public static final DeferredHolder<Item, ?> bloodstain =
            REGISTRY.register("bloodstain",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    0.07f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.bloodstain.tool.string"));

    public static final DeferredHolder<Item, ?> detect =
            REGISTRY.register("detect",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ATTACK_SPEED,
                    0.14f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.detect.tool.string"));

    public static final DeferredHolder<Item, ?> exercise_reinforcement =
            REGISTRY.register("exercise_reinforcement",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MOVEMENT_SPEED,
                    0.1f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.exercise_reinforcement.tool.string"));

    public static final DeferredHolder<Item, ?> plague_book =
            REGISTRY.register("plague_book",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.ATTACK_DAMAGE,
                    0.08f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.plague_book.tool.string"));

    public static final DeferredHolder<Item, ?> spore_outbreak =
            REGISTRY.register("spore_outbreak",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.KNOCKBACK_RESISTANCE,
                    0.18f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ,"item.spore_outbreak.tool.string"));

    public static final DeferredHolder<Item, ?> weak =
            REGISTRY.register("weak",()-> new BookItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                    Attributes.MAX_HEALTH,
                    4,
                    AttributeModifier.Operation.ADD_VALUE
                    ,"item.weak.tool.string"));
}
