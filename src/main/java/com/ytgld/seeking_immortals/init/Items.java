package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.item.nightmare.base.*;
import com.ytgld.seeking_immortals.item.nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.element.yoke;
import com.ytgld.seeking_immortals.item.nightmare.fall.alchemy_pot;
import com.ytgld.seeking_immortals.item.nightmare.fall.the_divine_fall_ring;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.apple;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.nightmare_base_fool_betray;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.nightmare_base_fool_bone;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.nightmare_base_fool_soul;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.hypocritical_self_esteem;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.nightmare_base_redemption_deception;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.nightmare_base_redemption_degenerate;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.nightmare_base_redemption_down_and_out;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.candle;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.nightmare_base_reversal_card;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.nightmare_base_reversal_mysterious;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.nightmare_base_reversal_orb;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.nightmare_base_start_egg;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.nightmare_base_start_pod;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.nightmare_base_start_power;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.wolf;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, SeekingImmortalsMod.MODID);
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
    public static final DeferredHolder<Item,?> end_bone =REGISTRY.register("end_bone", end_bone::new);
    public static final DeferredHolder<Item,?> candle =REGISTRY.register("candle", candle::new);
    public static final DeferredHolder<Item,?> apple =REGISTRY.register("apple", apple::new);
    public static final DeferredHolder<Item,?> ring =REGISTRY.register("ring", ring::new);
    public static final DeferredHolder<Item,?> hypocritical_self_esteem =REGISTRY.register("hypocritical_self_esteem", hypocritical_self_esteem::new);
    public static final DeferredHolder<Item,?> wolf =REGISTRY.register("wolf", wolf::new);
    public static final DeferredHolder<Item,?> eye =REGISTRY.register("eye", eye::new);
    public static final DeferredHolder<Item,?> falling_immortals =REGISTRY.register("falling_immortals", falling_immortals::new);
    public static final DeferredHolder<Item,?> disintegrating_stone =REGISTRY.register("disintegrating_stone", disintegrating_stone::new);
    public static final DeferredHolder<Item,?> muddy_jewels =REGISTRY.register("muddy_jewels", muddy_jewels::new);

    public static final DeferredHolder<Item,?> defend_against_runestone =REGISTRY.register("defend_against_runestone", defend_against_runestone::new);
    public static final DeferredHolder<Item,?> revive_runestone =REGISTRY.register("revive_runestone", revive_runestone::new);
    public static final DeferredHolder<Item,?> strengthen_runestone =REGISTRY.register("strengthen_runestone", strengthen_runestone::new);
    public static final DeferredHolder<Item,?> hidden_blade =REGISTRY.register("hidden_blade", hidden_blade::new);
    public static final DeferredHolder<Item,?> yoke =REGISTRY.register("yoke", yoke::new);
    public static final DeferredHolder<Item,?> blood_god =REGISTRY.register("blood_god", blood_god::new);
    public static final DeferredHolder<Item,?> lotus =REGISTRY.register("lotus", lotus::new);
    public static final DeferredHolder<Item,?> blood_ring =REGISTRY.register("blood_ring", blood_ring::new);
    public static final DeferredHolder<Item,?> biochemistry =REGISTRY.register("biochemistry", biochemistry::new);

    public static final DeferredHolder<Item,?> crazy_drug =REGISTRY.register("crazy_drug", crazy_drug::new);
    public static final DeferredHolder<Item,?> the_divine_fall_ring =REGISTRY.register("the_divine_fall_ring",the_divine_fall_ring::new);
    public static final DeferredHolder<Item,?> vientiane_cauldron =REGISTRY.register("vientiane_cauldron",vientiane_cauldron::new);
    public static final DeferredHolder<Item,?> alchemy_pot =REGISTRY.register("alchemy_pot", alchemy_pot::new);

    public static final DeferredHolder<Item,?> bone_or_god =REGISTRY.register("bone_or_god", bone_or_god::new);
    public static final DeferredHolder<Item,?> concentrate =REGISTRY.register("concentrate", concentrate::new);

    public static final DeferredHolder<Item,?> nightmare_base_start_power =REGISTRY.register("nightmare_base_start_power", nightmare_base_start_power::new);
    public static final DeferredHolder<Item,?> tricky_puppets =REGISTRY.register("tricky_puppets", tricky_puppets::new);
    public static final DeferredHolder<Item,?> evil_thought_bone =REGISTRY.register("evil_thought_bone", evil_thought_bone::new);

}
