package com.moonstone.moonstonemod.init.items;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.client.Blood;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BloodItem;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.apple;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medicinebox;
import com.moonstone.moonstonemod.item.*;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.undead_blood_charm;
import com.moonstone.moonstonemod.item.ectoplasm.*;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulbattery;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulcube;
import com.moonstone.moonstonemod.item.man.*;
import com.moonstone.moonstonemod.item.maxitem.amout.ectoplasmstone;
import com.moonstone.moonstonemod.item.maxitem.amout.twistedstone;
import com.moonstone.moonstonemod.item.maxitem.*;
import com.moonstone.moonstonemod.item.maxitem.book.the_blood_book;
import com.moonstone.moonstonemod.item.maxitem.food.SmithingFood;
import com.moonstone.moonstonemod.item.maxitem.maulice.*;
import com.moonstone.moonstonemod.item.maxitem.rage.*;
import com.moonstone.moonstonemod.item.maxitem.uncommon.common.*;
import com.moonstone.moonstonemod.item.maxitem.uncommon.magnet;
import com.moonstone.moonstonemod.item.nanodoom.*;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.*;
import com.moonstone.moonstonemod.item.plague.ALL.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.item.plague.BloodVirus.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.dna.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.botton;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.catalyzer;
import com.moonstone.moonstonemod.item.plague.TheNecora.*;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.*;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.air;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.motor;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.watergen;
import com.moonstone.moonstonemod.item.plague.TheNecora.god.*;
import com.moonstone.moonstonemod.item.plague.crafting_box;
import com.moonstone.moonstonemod.item.plague.medicine.med.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, MoonStoneMod.MODID);
    public static final DeferredHolder<Item,?> ectoplasmapple  =REGISTRY.register("ectoplasmapple", ectoplasmapple::new);
    public static final DeferredHolder<Item,?> ectoplasmball  =REGISTRY.register("ectoplasmball", ectoplasmball::new);
    public static final DeferredHolder<Item,?> ectoplasmbattery  =REGISTRY.register("ectoplasmbattery", ectoplasmbattery::new);
    public static final DeferredHolder<Item,?> ectoplasmcloub  =REGISTRY.register("ectoplasmcloub", ectoplasmcloub::new);
    public static final DeferredHolder<Item,?> ectoplasmcube  =REGISTRY.register("ectoplasmcube", ectoplasmcube::new);
    public static final DeferredHolder<Item,?> ectoplasmhorseshoe  =REGISTRY.register("ectoplasmhorseshoe", ectoplasmhorseshoe::new);
    public static final DeferredHolder<Item,?> ectoplasmprism  =REGISTRY.register("ectoplasmprism", ectoplasmprism::new);
    public static final DeferredHolder<Item,?> ectoplasmshild  =REGISTRY.register("ectoplasmshild", ectoplasmshild::new);

    public static final DeferredHolder<Item,?> mbattery  =REGISTRY.register("mbattery", mbattery::new);
    public static final DeferredHolder<Item,?> mblock  =REGISTRY.register("mblock", mblock::new);
    public static final DeferredHolder<Item,?> mbottle  =REGISTRY.register("mbottle", mbottle::new);
    public static final DeferredHolder<Item,?> mbox  =REGISTRY.register("mbox", mbox::new);
    public static final DeferredHolder<Item,?> meye  =REGISTRY.register("meye", meye::new);
    public static final DeferredHolder<Item,?> mkidney  =REGISTRY.register("mkidney", mkidney::new);
    public static final DeferredHolder<Item,?> morb  =REGISTRY.register("morb", morb::new);

    public static final DeferredHolder<Item,?> mring  =REGISTRY.register("mring", mring::new);
    public static final DeferredHolder<Item,?> mshell  =REGISTRY.register("mshell", mshell::new);

    public static final DeferredHolder<Item,?> nightmareanchor  =REGISTRY.register("nightmareanchor", nightmareanchor::new);
    public static final DeferredHolder<Item,?> nightmarecharm  =REGISTRY.register("nightmarecharm", nightmarecharm::new);
    public static final DeferredHolder<Item,?> nightmareeye  =REGISTRY.register("nightmareeye", nightmareeye::new);
    public static final DeferredHolder<Item,?> nightmaremoai  =REGISTRY.register("nightmaremoai", nightmaremoai::new);
    public static final DeferredHolder<Item,?> nightmarerotten  =REGISTRY.register("nightmarerotten", nightmarerotten::new);
    public static final DeferredHolder<Item,?> nightmarestone  =REGISTRY.register("nightmarestone", nightmarestone::new);
    public static final DeferredHolder<Item,?> nightmaretreasure  =REGISTRY.register("nightmaretreasure", nightmaretreasure::new);
    public static final DeferredHolder<Item,?> nightmarewater  =REGISTRY.register("nightmarewater", nightmarewater::new);


    public static final DeferredHolder<Item,?>  badgeofthedead =REGISTRY.register("badgeofthedead", badgeofthedead::new);
    public static final DeferredHolder<Item,?>  battery =REGISTRY.register("battery", battery::new);
    public static final DeferredHolder<Item,?>  biggreedcrystal =REGISTRY.register("biggreedcrystal", biggreedcrystal::new);
    public static final DeferredHolder<Item,?>  bigwarcrystal =REGISTRY.register("bigwarcrystal", bigwarcrystal::new);
    public static final DeferredHolder<Item,?>  blackeorb =REGISTRY.register("blackeorb", blackeorb::new);
    public static final DeferredHolder<Item,?>  blueamout =REGISTRY.register("blueamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.blueamout::new);
    public static final DeferredHolder<Item,?>  greedamout =REGISTRY.register("greedamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.greedamout::new);
    public static final DeferredHolder<Item,?>  greedcrystal =REGISTRY.register("greedcrystal", com.moonstone.moonstonemod.item.maxitem.uncommon.common.greedcrystal::new);
    public static final DeferredHolder<Item,?>  redamout =REGISTRY.register("redamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.redamout::new);
    public static final DeferredHolder<Item,?>  warcrystal =REGISTRY.register("warcrystal", com.moonstone.moonstonemod.item.maxitem.uncommon.common.warcrystal::new);
    public static final DeferredHolder<Item,?>  whiteorb =REGISTRY.register("whiteorb", whiteorb::new);


    public static final DeferredHolder<Item,?>  magiceye =REGISTRY.register("magiceye", magiceye::new);
    public static final DeferredHolder<Item,?>  magicstone =REGISTRY.register("magicstone", magicstone::new);
    public static final DeferredHolder<Item,?>  nanocube =REGISTRY.register("nanocube", nanocube::new);
    public static final DeferredHolder<Item,?>  nanorobot =REGISTRY.register("nanorobot", nanorobot::new);
    public static final DeferredHolder<Item,?>  thedoomstone =REGISTRY.register("thedoomstone", thedoomstone::new);
    public static final DeferredHolder<Item,?>  thefruit =REGISTRY.register("thefruit", thefruit::new);
    public static final DeferredHolder<Item,?>  ectoplasmstone =REGISTRY.register("ectoplasmstone", ectoplasmstone::new);
    public static final DeferredHolder<Item,?>  twistedstone =REGISTRY.register("twistedstone", twistedstone::new);

    public static final DeferredHolder<Item,?>  soulbattery =REGISTRY.register("soulbattery", soulbattery::new);
    public static final DeferredHolder<Item,?>  soulcube =REGISTRY.register("soulcube", soulcube::new);

    public static final DeferredHolder<Item,?>  evilcandle =REGISTRY.register("evilcandle", com.moonstone.moonstonemod.item.maxitem.uncommon.evilcandle::new);
    public static final DeferredHolder<Item,?>  evilmug =REGISTRY.register("evilmug", com.moonstone.moonstonemod.item.maxitem.uncommon.evilmug::new);
    public static final DeferredHolder<Item,?>  obsidianring =REGISTRY.register("obsidianring", com.moonstone.moonstonemod.item.maxitem.uncommon.obsidianring::new);


    public static final DeferredHolder<Item,?>  dna =REGISTRY.register("dna",dna::new);
    public static final DeferredHolder<Item,?>  fungus =REGISTRY.register("fungus",fungus::new);
    public static final DeferredHolder<Item,?>  germ =REGISTRY.register("germ",germ::new);
    public static final DeferredHolder<Item,?>  parasite =REGISTRY.register("parasite",parasite::new);
    public static final DeferredHolder<Item,?>  virus =REGISTRY.register("virus",virus::new);
    public static final DeferredHolder<Item,?>  botton =REGISTRY.register("botton",botton::new);
    public static final DeferredHolder<Item,?>  catalyzer =REGISTRY.register("catalyzer",catalyzer::new);


    public static final DeferredHolder<Item,?>  calcification =REGISTRY.register("calcification",calcification::new);
    public static final DeferredHolder<Item,?>  masticatory =REGISTRY.register("masticatory",masticatory::new);
    public static final DeferredHolder<Item,?>  polyphagia =REGISTRY.register("polyphagia",polyphagia::new);
    public static final DeferredHolder<Item,?>  quadriceps =REGISTRY.register("quadriceps",quadriceps::new);
    public static final DeferredHolder<Item,?>  reanimation =REGISTRY.register("reanimation",reanimation::new);
    public static final DeferredHolder<Item,?>  batskill =REGISTRY.register("batskill",batskill::new);




    public static final DeferredHolder<Item,?> batgene =REGISTRY.register("batgene",batgene::new);
    public static final DeferredHolder<Item,?> bloodgene =REGISTRY.register("bloodgene",bloodgene::new);
    public static final DeferredHolder<Item,?> flygene =REGISTRY.register("flygene",flygene::new);
    public static final DeferredHolder<Item,?> heathgene =REGISTRY.register("heathgene",heathgene::new);
    public static final DeferredHolder<Item,?> ragegene =REGISTRY.register("ragegene",ragegene::new);
    public static final DeferredHolder<Item,?> sleepgene =REGISTRY.register("sleepgene",sleepgene::new);
    public static final DeferredHolder<Item,?> medicinebox =REGISTRY.register("medicinebox",medicinebox::new);
    public static final DeferredHolder<Item,?> apple =REGISTRY.register("apple",apple::new);




    public static final DeferredHolder<Item,?> ambush =REGISTRY.register("ambush", ambush::new);
    public static final DeferredHolder<Item,?> atpoverdose =REGISTRY.register("atpoverdose", atpoverdose::new);
    public static final DeferredHolder<Item,?> autolytic =REGISTRY.register("autolytic", autolytic::new);
    public static final DeferredHolder<Item,?> fermentation =REGISTRY.register("fermentation", fermentation::new);
    public static final DeferredHolder<Item,?> putrefactive =REGISTRY.register("putrefactive", putrefactive::new);
    public static final DeferredHolder<Item,?> regenerative =REGISTRY.register("regenerative", regenerative::new);


    public static final DeferredHolder<Item,?> bloodvirus =REGISTRY.register("bloodvirus",bloodvirus::new);
    public static final DeferredHolder<Item,?> necora =REGISTRY.register("necora",necora::new);
    public static final DeferredHolder<Item,?> maxamout =REGISTRY.register("maxamout",maxamout::new);

    public static final DeferredHolder<Item,?> mayhemcrystal =REGISTRY.register("mayhemcrystal",mayhemcrystal::new);
    public static final DeferredHolder<Item,?> fortunecrystal =REGISTRY.register("fortunecrystal",fortunecrystal::new);
    public static final DeferredHolder<Item,?> plague =REGISTRY.register("plague", com.moonstone.moonstonemod.item.maxitem.uncommon.plague::new);
    public static final DeferredHolder<Item,?> doomeye =REGISTRY.register("doomeye", doomeye::new);

    public static final DeferredHolder<Item,?> doomswoud =REGISTRY.register("doomswoud", doomswoud::new);
    public static final DeferredHolder<Item,?> book =REGISTRY.register("soulbook", book::new);
    public static final DeferredHolder<Item,?> wind =REGISTRY.register("wind", wind::new);
    public static final DeferredHolder<Item,?> wind_and_rain =REGISTRY.register("wind_and_rain", com.moonstone.moonstonemod.item.nanodoom.buyme.wind_and_rain::new);
    public static final DeferredHolder<Item,?> ectoplasmstar =REGISTRY.register("ectoplasmstar", ectoplasmstar::new);
    public static final DeferredHolder<Item,?> ectoplasmsoul =REGISTRY.register("ectoplasmsoul", ectoplasmsoul::new);
    public static final DeferredHolder<Item,?> ectoplasmtree =REGISTRY.register("ectoplasmtree", ectoplasmtree::new);
    public static final DeferredHolder<Item,?> brain =REGISTRY.register("brain", brain::new);
    public static final DeferredHolder<Item,?> beacon =REGISTRY.register("beacon", beacon::new);
    public static final DeferredHolder<Item,?> mhead =REGISTRY.register("mhead", mhead::new);
    public static final DeferredHolder<Item,?> cell =REGISTRY.register("cell", cell::new);
    public static final DeferredHolder<Item,?> adrenaline =REGISTRY.register("adrenaline", adrenaline::new);
    public static final DeferredHolder<Item,?> cell_mummy =REGISTRY.register("cell_mummy", cell_mummy::new);
    public static final DeferredHolder<Item,?> cell_boom =REGISTRY.register("cell_boom", cell_boom::new);
    public static final DeferredHolder<Item,?> cell_calcification =REGISTRY.register("cell_calcification", cell_calcification::new);
    public static final DeferredHolder<Item,?> cell_blood =REGISTRY.register("cell_blood", cell_blood::new);
    public static final DeferredHolder<Item,?> motor =REGISTRY.register("motor", motor::new);
    public static final DeferredHolder<Item,?> watergen =REGISTRY.register("watergen", watergen::new);
    public static final DeferredHolder<Item,?> air =REGISTRY.register("air", air::new);
    public static final DeferredHolder<Item,?> giant =REGISTRY.register("giant", giant::new);
    public static final DeferredHolder<Item,?> the_heart =REGISTRY.register("the_heart", com.moonstone.moonstonemod.item.maxitem.the_heart::new);
    public static final DeferredHolder<Item,?> nightmare_orb =REGISTRY.register("nightmare_orb", com.moonstone.moonstonemod.item.nightmare.nightmare_orb::new);
    public static final DeferredHolder<Item,?> nightmare_heart =REGISTRY.register("nightmare_heart", com.moonstone.moonstonemod.item.nightmare.nightmare_heart::new);
    public static final DeferredHolder<Item,?> nightmare_head =REGISTRY.register("nightmare_head", com.moonstone.moonstonemod.item.nightmare.nightmare_head::new);
    public static final DeferredHolder<Item,?> giant_nightmare =REGISTRY.register("giant_nightmare", giant_nightmare::new);
    public static final DeferredHolder<Item,?> nightmare_cube =REGISTRY.register("nightmare_cube", com.moonstone.moonstonemod.item.nightmare.nightmare_cube::new);



    public static final DeferredHolder<Item,?> bat_cell =REGISTRY.register("bat_cell", bat_cell::new);
    public static final DeferredHolder<Item,?> cell_doctor =REGISTRY.register("cell_doctor", cell_doctor::new);
    public static final DeferredHolder<Item,?> cell_desecrate =REGISTRY.register("cell_desecrate", cell_desecrate::new);
    public static final DeferredHolder<Item,?> cell_harvest =REGISTRY.register("cell_harvest", cell_harvest::new);

    public static final DeferredHolder<Item,?> cell_scientist =REGISTRY.register("cell_scientist", cell_scientist::new);
    public static final DeferredHolder<Item,?> cell_immortal =REGISTRY.register("cell_immortal", cell_immortal::new);
    public static final DeferredHolder<Item,?> cell_rage =REGISTRY.register("cell_rage", cell_rage::new);
    public static final DeferredHolder<Item,?> cell_blood_attack =REGISTRY.register("cell_blood_attack", cell_blood_attack::new);
    public static final DeferredHolder<Item,?> cell_fear =REGISTRY.register("cell_fear", cell_fear::new);
    public static final DeferredHolder<Item,?> cell_not_do =REGISTRY.register("cell_not_do", cell_not_do::new);

    public static final DeferredHolder<Item,?> max_sword =REGISTRY.register("max_sword", (ResourceLocation pProperties) -> new max_sword());

    public static final DeferredHolder<Item,?> max_eye =REGISTRY.register("max_eye", (ResourceLocation pProperties) -> new max_eye());
    public static final DeferredHolder<Item,?> max_blood_eye =REGISTRY.register("max_blood_eye", (ResourceLocation pProperties) -> new max_blood_eye());
    public static final DeferredHolder<Item,?> max_blood_cube =REGISTRY.register("max_blood_cube", (ResourceLocation pProperties) -> new max_blood_cube());
    public static final DeferredHolder<Item,?> blood_amout =REGISTRY.register("blood_amout", (ResourceLocation pProperties) -> new blood_amout());

    public static final DeferredHolder<Item,?> blood_snake =REGISTRY.register("blood_snake", (ResourceLocation pProperties) -> new blood_snake());
    public static final DeferredHolder<Item,?> the_prison_of_sin =REGISTRY.register("the_prison_of_sin", (ResourceLocation pProperties) -> new the_prison_of_sin());
    public static final DeferredHolder<Item,?> blood_magic_box =REGISTRY.register("blood_magic_box", (ResourceLocation pProperties) -> new blood_magic_box());
    public static final DeferredHolder<Item,?> the_blood_book =REGISTRY.register("the_blood_book", (ResourceLocation pProperties) -> new the_blood_book());
    public static final DeferredHolder<Item,?> evil_blood =REGISTRY.register("evil_blood", com.moonstone.moonstonemod.item.plague.BloodVirus.evil.evil_blood::new);
    public static final DeferredHolder<Item,?> blood =REGISTRY.register("blood", ()-> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredHolder<Item,?> blood_candle =REGISTRY.register("blood_candle", com.moonstone.moonstonemod.item.blood.magic.blood_candle::new);
    public static final DeferredHolder<Item,?> deceased_contract =REGISTRY.register("deceased_contract", com.moonstone.moonstonemod.item.decorated.deceased_contract::new);

    public static final DeferredHolder<Item,?> blood_sun =REGISTRY.register("blood_sun", com.moonstone.moonstonemod.item.blood.magic.blood_sun::new);
    public static final DeferredHolder<Item,?> blood_jelly =REGISTRY.register("blood_jelly", com.moonstone.moonstonemod.item.blood.blood_jelly::new);
    public static final DeferredHolder<Item,?> evil_mob =REGISTRY.register("evil_mob", com.moonstone.moonstonemod.item.maxitem.evil_mob::new);
    public static final DeferredHolder<Item,?> god_lead =REGISTRY.register("god_lead", com.moonstone.moonstonemod.item.maxitem.god_lead::new);
    public static final DeferredHolder<Item,?> malice_die =REGISTRY.register("malice_die", com.moonstone.moonstonemod.item.maxitem.malice_die::new);

    public static final DeferredHolder<Item,?> anaerobic_cell =REGISTRY.register("anaerobic_cell", anaerobic_cell::new);
    public static final DeferredHolder<Item,?> giant_boom_cell =REGISTRY.register("giant_boom_cell", giant_boom_cell::new);
    public static final DeferredHolder<Item,?> subspace_cell =REGISTRY.register("subspace_cell", subspace_cell::new);
    public static final DeferredHolder<Item,?> bone_cell =REGISTRY.register("bone_cell", bone_cell::new);
    public static final DeferredHolder<Item,?> parasitic_cell =REGISTRY.register("parasitic_cell", parasitic_cell::new);
    public static final DeferredHolder<Item,?> mother_cell =REGISTRY.register("mother_cell", mother_cell::new);
    public static final DeferredHolder<Item,?> disgusting_cells =REGISTRY.register("disgusting_cells", disgusting_cells::new);
    public static final DeferredHolder<Item,?> moon_stone =REGISTRY.register("moon_stone", com.moonstone.moonstonemod.item.maxitem.moon_stone::new);

     public static final DeferredHolder<Item,?> dna_box =REGISTRY.register("dna_box", com.moonstone.moonstonemod.item.plague.dna.dna_box::new);
    public static final DeferredHolder<Item,?> body_stone =REGISTRY.register("body_stone", com.moonstone.moonstonemod.item.body_stone::new);
    public static final DeferredHolder<Item,?> probability =REGISTRY.register("probability", com.moonstone.moonstonemod.item.maxitem.probability::new);
    public static final DeferredHolder<Item,?> luck_ring =REGISTRY.register("luck_ring", com.moonstone.moonstonemod.item.maxitem.uncommon.luck_ring::new);
    public static final DeferredHolder<Item,?> luck_stone =REGISTRY.register("luck_stone", com.moonstone.moonstonemod.item.maxitem.uncommon.luck_stone::new);
    public static final DeferredHolder<Item,?> necora_baby =REGISTRY.register("necora_baby", com.moonstone.moonstonemod.item.necora_baby::new);
    public static final DeferredHolder<Item,?> rage_blood_head =REGISTRY.register("rage_blood_head", com.moonstone.moonstonemod.item.blood.magic.rage_blood_head::new);
    public static final DeferredHolder<Item,?> killer =REGISTRY.register("killer", com.moonstone.moonstonemod.item.blood.killer::new);
    public static final DeferredHolder<Item,?> as_amout =REGISTRY.register("as_amout", com.moonstone.moonstonemod.item.nanodoom.as_amout::new);

    public static final DeferredHolder<Item,?> million =REGISTRY.register("million", com.moonstone.moonstonemod.item.nanodoom.million::new);
    public static final DeferredHolder<Item,?> nine_sword_book =REGISTRY.register("nine_sword_book", com.moonstone.moonstonemod.item.maxitem.book.nine_sword_book::new);

    public static final DeferredHolder<Item,?> owner_blood_eye =REGISTRY.register("owner_blood_eye", BloodItem::new);

    public static final DeferredHolder<Item,?> owner_blood_attack_eye =REGISTRY.register("owner_blood_attack_eye", BloodItem::new);
    public static final DeferredHolder<Item,?> owner_blood_speed_eye =REGISTRY.register("owner_blood_speed_eye", BloodItem::new);

    public static final DeferredHolder<Item,?> owner_blood_boom_eye =REGISTRY.register("owner_blood_boom_eye", BloodItem::new);
    public static final DeferredHolder<Item,?> owner_blood_effect_eye =REGISTRY.register("owner_blood_effect_eye", BloodItem::new);
    public static final DeferredHolder<Item,?> owner_blood_vex =REGISTRY.register("owner_blood_vex", BloodItem::new);
    public static final DeferredHolder<Item,?> owner_blood_earth =REGISTRY.register("owner_blood_earth", BloodItem::new);
    public static final DeferredHolder<Item,?> nightmare_base_black_eye =REGISTRY.register("nightmare_base_black_eye", nightmare_base_black_eye::new);
    public static final DeferredHolder<Item,?> nightmare_base =REGISTRY.register("nightmare_base", nightmare_base::new);

    public static final DeferredHolder<Item,?> nightmare_base_black_eye_eye =REGISTRY.register("nightmare_base_black_eye_eye", nightmare_base_black_eye_eye::new);
    public static final DeferredHolder<Item,?> nightmare_base_black_eye_heart =REGISTRY.register("nightmare_base_black_eye_heart", nightmare_base_black_eye_heart::new);

    public static final DeferredHolder<Item,?> nightmare_base_black_eye_red =REGISTRY.register("nightmare_base_black_eye_red", nightmare_base_black_eye_red::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone =REGISTRY.register("nightmare_base_stone", nightmare_base_stone::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone_meet =REGISTRY.register("nightmare_base_stone_meet", nightmare_base_stone_meet::new);

    public static final DeferredHolder<Item,?> nightmare_base_stone_virus =REGISTRY.register("nightmare_base_stone_virus", nightmare_base_stone_virus::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone_brain =REGISTRY.register("nightmare_base_stone_brain", nightmare_base_stone_brain::new);

    public static final DeferredHolder<Item,?> nightmare_virus =REGISTRY.register("nightmare_virus", nightmare_virus::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal =REGISTRY.register("nightmare_base_reversal", nightmare_base_reversal::new);

    public static final DeferredHolder<Item,?> nightmare_base_reversal_orb =REGISTRY.register("nightmare_base_reversal_orb", nightmare_base_reversal_orb::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal_card =REGISTRY.register("nightmare_base_reversal_card", nightmare_base_reversal_card::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal_mysterious =REGISTRY.register("nightmare_base_reversal_mysterious", nightmare_base_reversal_mysterious::new);

    public static final DeferredHolder<Item,?> nightmare_base_redemption =REGISTRY.register("nightmare_base_redemption", nightmare_base_redemption::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_deception =REGISTRY.register("nightmare_base_redemption_deception", nightmare_base_redemption_deception::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_degenerate =REGISTRY.register("nightmare_base_redemption_degenerate", nightmare_base_redemption_degenerate::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_down_and_out =REGISTRY.register("nightmare_base_redemption_down_and_out", nightmare_base_redemption_down_and_out::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool =REGISTRY.register("nightmare_base_fool", nightmare_base_fool::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_soul =REGISTRY.register("nightmare_base_fool_soul", nightmare_base_fool_soul::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_bone =REGISTRY.register("nightmare_base_fool_bone", nightmare_base_fool_bone::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_betray =REGISTRY.register("nightmare_base_fool_betray", nightmare_base_fool_betray::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight =REGISTRY.register("nightmare_base_insight", nightmare_base_insight::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_drug =REGISTRY.register("nightmare_base_insight_drug", nightmare_base_insight_drug::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_insane =REGISTRY.register("nightmare_base_insight_insane", nightmare_base_insight_insane::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_collapse =REGISTRY.register("nightmare_base_insight_collapse", nightmare_base_insight_collapse::new);
    public static final DeferredHolder<Item,?> nightmare_base_start =REGISTRY.register("nightmare_base_start", nightmare_base_start::new);
    public static final DeferredHolder<Item,?> nightmare_base_start_pod =REGISTRY.register("nightmare_base_start_pod", nightmare_base_start_pod::new);
    public static final DeferredHolder<Item,?> nightmare_base_start_egg =REGISTRY.register("nightmare_base_start_egg", nightmare_base_start_egg::new);

    public static final DeferredHolder<Item,?> nightmare_base_start_power =REGISTRY.register("nightmare_base_start_power", nightmare_base_start_power::new);
    public static final DeferredHolder<Item,?> god_head =REGISTRY.register("god_head", god_head::new);
    public static final DeferredHolder<Item,?> nightmare_axe =REGISTRY.register("nightmare_axe", nightmare_axe::new);
    public static final DeferredHolder<Item,?> crafting_box =REGISTRY.register("crafting_box", crafting_box::new);
    public static final DeferredHolder<Item,?> immortal =REGISTRY.register("immortal", immortal::new);
    public static final DeferredHolder<Item,?> sword =REGISTRY.register("sword",twelve_sword.sword::new );
    public static final DeferredHolder<Item,?> at_sword_ =REGISTRY.register("at_sword",twelve_sword.at_sword::new );
    public static final DeferredHolder<Item,?> twelve_sword_ =REGISTRY.register("twelve_sword", twelve_sword::new);
    public static final DeferredHolder<Item,?> coffin_item =REGISTRY.register("coffin_item", coffin.coffin_item::new);
    public static final DeferredHolder<Item,?> coffin_ =REGISTRY.register("coffin", coffin::new);



    public static final DeferredHolder<Item,?> belt =REGISTRY.register("belt",belt::new );
    public static final DeferredHolder<Item,?> rage_crystal =REGISTRY.register("rage_crystal",rage_crystal::new );
    public static final DeferredHolder<Item,?> rage_crystal_big =REGISTRY.register("rage_crystal_big",rage_crystal_big::new );
    public static final DeferredHolder<Item,?> rage_crystal_max =REGISTRY.register("rage_crystal_max",rage_crystal_max::new );
    public static final DeferredHolder<Item,?> undead_blood_charm =REGISTRY.register("undead_blood_charm", undead_blood_charm::new );
    public static final DeferredHolder<Item,?> god_ambush =REGISTRY.register("god_ambush", GodAmbush::new );
    public static final DeferredHolder<Item,?> god_atpoverdose =REGISTRY.register("god_atpoverdose", GodAtpoverdose::new );
    public static final DeferredHolder<Item,?> god_putrefactive =REGISTRY.register("god_putrefactive", GodPutrefactive::new );
    public static final DeferredHolder<Item,?> god_fermentation =REGISTRY.register("god_fermentation", GodFermentation::new );
    public static final DeferredHolder<Item,?> god_autolytic =REGISTRY.register("god_autolytic", GodAutolytic::new );
    public static final DeferredHolder<Item,?> god_regenerative =REGISTRY.register("god_regenerative", GodRegenerative::new );
    public static final DeferredHolder<Item,?> rage_charm =REGISTRY.register("rage_charm", rage_charm::new );
    public static final DeferredHolder<Item,?> rage_eye =REGISTRY.register("rage_eye", rage_eye::new );
    public static final DeferredHolder<Item,?> magnet =REGISTRY.register("magnet", magnet::new );
    public static final DeferredHolder<Item,?> rage_magnet =REGISTRY.register("rage_magnet", rage_magnet::new );
    public static final DeferredHolder<Item,?> rage_stone =REGISTRY.register("rage_stone", rage_stone::new );
    public static final DeferredHolder<Item,?> rage_lock =REGISTRY.register("rage_lock", rage_lock::new );
    public static final DeferredHolder<Item,?> rage_head =REGISTRY.register("rage_head", rage_head::new );
    public static final DeferredHolder<Item,?> rage_orb =REGISTRY.register("rage_orb", rage_orb::new );
    public static final DeferredHolder<Item,?> pain_candle =REGISTRY.register("pain_candle", (ResourceLocation properties) -> new pain_candle());
    public static final DeferredHolder<Item,?> pain_ring =REGISTRY.register("pain_ring", (ResourceLocation properties) -> new pain_ring());
    public static final DeferredHolder<Item,?> rage_bottle =REGISTRY.register("rage_bottle", (ResourceLocation properties) -> new rage_bottle());
    public static final DeferredHolder<Item,?> universe =REGISTRY.register("universe", universe::new );
    public static final DeferredHolder<Item,?> run_dna =REGISTRY.register("run_dna", run_dna::new );
    public static final DeferredHolder<Item,?> health_dna =REGISTRY.register("health_dna", health_dna::new );
    public static final DeferredHolder<Item,?> copy_dna =REGISTRY.register("copy_dna", copy_dna::new );
    public static final DeferredHolder<Item,?> attack_dna =REGISTRY.register("attack_dna", attack_dna::new );
    public static final DeferredHolder<Item,?> greed_dna =REGISTRY.register("greed_dna", greed_dna::new );
    public static final DeferredHolder<Item,?> neuron_dna =REGISTRY.register("neuron_dna", neuron_dna::new );
    public static final DeferredHolder<Item,?> eye_dna =REGISTRY.register("eye_dna", eye_dna::new );
    public static final DeferredHolder<Item,?> eye_lava_dna =REGISTRY.register("eye_lava_dna", eye_lava_dna::new );
    public static final DeferredHolder<Item,?> skin_dna =REGISTRY.register("skin_dna", skin_dna::new );
    public static final DeferredHolder<Item,?> bone_dna =REGISTRY.register("bone_dna", bone_dna::new );
    public static final DeferredHolder<Item,?> muscle_conversion =REGISTRY.register("muscle_conversion", muscle_conversion::new );
    public static final DeferredHolder<Item,?> phosphate_bond =REGISTRY.register("phosphate_bond", phosphate_bond::new );
    public static final DeferredHolder<Item,?> chemical_compound =REGISTRY.register("chemical_compound", chemical_compound::new );

    public static final DeferredHolder<Item,?> god_sword_ =REGISTRY.register("god_sword",twelve_sword.god_sword::new );
    public static final DeferredHolder<Item,?> smithing_food =REGISTRY.register("smithing_food", SmithingFood::new);

    public static final DeferredHolder<Item,?> the_heart_image =REGISTRY.register("the_heart_image", ()-> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item,?> medicinebox_ui =REGISTRY.register("medicinebox_ui", ()-> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredHolder<Item,?> gorillacake =REGISTRY.register("gorillacake", com.moonstone.moonstonemod.init.moonstoneitem.extend.gorillacake::new);

}
