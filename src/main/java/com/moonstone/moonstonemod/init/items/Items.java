package com.moonstone.moonstonemod.init.items;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BloodItem;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.apple;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medicinebox;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.maxitem.book.the_blood_book;
import com.moonstone.moonstonemod.item.bloodvirus;
import com.moonstone.moonstonemod.item.ectoplasm.*;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulbattery;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulcube;
import com.moonstone.moonstonemod.item.maxitem.amout.ectoplasmstone;
import com.moonstone.moonstonemod.item.maxitem.amout.twistedstone;
import com.moonstone.moonstonemod.item.maxitem.fortunecrystal;
import com.moonstone.moonstonemod.item.maxitem.maulice.*;
import com.moonstone.moonstonemod.item.maxitem.maxamout;
import com.moonstone.moonstonemod.item.maxitem.mayhemcrystal;
import com.moonstone.moonstonemod.item.maxitem.uncommon.common.*;
import com.moonstone.moonstonemod.item.nanodoom.*;
import com.moonstone.moonstonemod.item.necora;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.*;
import com.moonstone.moonstonemod.item.plague.ALL.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.item.plague.BloodVirus.batgene;
import com.moonstone.moonstonemod.item.plague.BloodVirus.bloodgene;
import com.moonstone.moonstonemod.item.plague.BloodVirus.dna.*;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.*;
import com.moonstone.moonstonemod.item.plague.medicine.med.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.*;
import com.moonstone.moonstonemod.item.plague.TheNecora.*;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.*;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

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







    public static final DeferredHolder<Item,?> the_heart_image =REGISTRY.register("the_heart_image", ()-> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item,?> medicinebox_ui =REGISTRY.register("medicinebox_ui", ()-> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredHolder<Item,?> gorillacake =REGISTRY.register("gorillacake", com.moonstone.moonstonemod.init.moonstoneitem.extend.gorillacake::new);

}
