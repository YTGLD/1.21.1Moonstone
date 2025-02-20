package com.moonstone.moonstonemod.init;


import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.BookItems;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class Tab {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoonStoneMod.MODID);
    public static final DeferredHolder<CreativeModeTab,?> DNA = TABS.register("moonstone_item_dna",()->CreativeModeTab.builder()
            .icon(()->new ItemStack(Items.necora.get()))
            .title(Component.translatable("itemGroup.tabmoonstone.dna"))
            .displayItems((a,b)->{
                b.accept(new ItemStack(Items.dna_box.get()));
                b.accept(new ItemStack(Items.crafting_box.get()));


                b.accept(new ItemStack(Items.deceased_contract.get()));

                b.accept(new ItemStack(BookItems.weak.get()));
                b.accept(new ItemStack(BookItems.spore_outbreak.get()));
                b.accept(new ItemStack(BookItems.plague_book.get()));
                b.accept(new ItemStack(BookItems.exercise_reinforcement.get()));
                b.accept(new ItemStack(BookItems.detect.get()));
                b.accept(new ItemStack(BookItems.bloodstain.get()));
                b.accept(new ItemStack(BookItems.blood_stasis.get()));
                b.accept(new ItemStack(BookItems.bone_structure.get()));
                b.accept(new ItemStack(BookItems.mummification.get()));
                b.accept(new ItemStack(BookItems.tumour.get()));
                b.accept(new ItemStack(BookItems.organizational_regeneration.get()));

                b.accept(new ItemStack(DNAItems.cell_compress.get()));
                b.accept(new ItemStack(DNAItems.cell_preferential.get()));
                b.accept(new ItemStack(DNAItems.cell_flu.get()));
                b.accept(new ItemStack(DNAItems.cell_constant.get()));
                b.accept(new ItemStack(DNAItems.cell_chromosome.get()));

                b.accept(new ItemStack(DNAItems.cell_necrosis.get()));
                b.accept(new ItemStack(DNAItems.cell_digestion.get()));
                b.accept(new ItemStack(DNAItems.cell_acid.get()));
                b.accept(new ItemStack(DNAItems.cell_eyes.get()));
                b.accept(new ItemStack(DNAItems.cell_bone_add.get()));
                b.accept(new ItemStack(DNAItems.cell_sense.get()));
                b.accept(new ItemStack(DNAItems.cell_cranial.get()));
                b.accept(new ItemStack(DNAItems.cell_dna_suppression.get()));
                b.accept(new ItemStack(DNAItems.cell_putrefactive.get()));
                b.accept(new ItemStack(DNAItems.cell_synthesis.get()));


                b.accept(new ItemStack(DNAItems.atp_height.get()));
                b.accept(new ItemStack(DNAItems.cell_god.get()));
                b.accept(new ItemStack(DNAItems.cell_ground.get()));
                b.accept(new ItemStack(DNAItems.cell_inheritance.get()));
                b.accept(new ItemStack(DNAItems.cell_oxygen.get()));
                b.accept(new ItemStack(DNAItems.cell_big_boom.get()));
                b.accept(new ItemStack(DNAItems.cell_break_down_water.get()));
                b.accept(new ItemStack(DNAItems.cell_darwin.get()));
                b.accept(new ItemStack(DNAItems.cell_disorder.get()));
                b.accept(new ItemStack(DNAItems.cell_in_air.get()));
                b.accept(new ItemStack(DNAItems.cell_in_water.get()));
                b.accept(new ItemStack(DNAItems.cell_off_on.get()));
                b.accept(new ItemStack(DNAItems.speed_metabolism.get()));









                b.accept(new ItemStack(Items.dna.get()));
                b.accept(new ItemStack(Items.fungus.get()));
                b.accept(new ItemStack(Items.germ.get()));
                b.accept(new ItemStack(Items.parasite.get()));
                b.accept(new ItemStack(Items.virus.get()));
                b.accept(new ItemStack(Items.cell.get()));
                b.accept(new ItemStack(Items.adrenaline.get()));
                b.accept(new ItemStack(Items.cell_mummy.get()));
                b.accept(new ItemStack(Items.cell_boom.get()));
                b.accept(new ItemStack(Items.cell_calcification.get()));
                b.accept(new ItemStack(Items.cell_blood.get()));
                b.accept(new ItemStack(Items.air.get()));
                b.accept(new ItemStack(Items.motor.get()));
                b.accept(new ItemStack(Items.watergen.get()));
                b.accept(new ItemStack(Items.giant.get()));
                b.accept(new ItemStack(Items.giant_nightmare.get()));

                b.accept(new ItemStack(Items.giant_boom_cell.get()));
                b.accept(new ItemStack(Items.anaerobic_cell.get()));
                b.accept(new ItemStack(Items.disgusting_cells.get()));
                b.accept(new ItemStack(Items.bone_cell.get()));
                b.accept(new ItemStack(Items.mother_cell.get()));
                b.accept(new ItemStack(Items.parasitic_cell.get()));
                b.accept(new ItemStack(Items.subspace_cell.get()));

                b.accept(new ItemStack(Items.botton.get()));
                b.accept(new ItemStack(Items.catalyzer.get()));


                b.accept(new ItemStack(Items.batskill.get()));


                b.accept(new ItemStack(Items.batgene.get()));
                b.accept(new ItemStack(Items.bloodgene.get()));
                b.accept(new ItemStack(Items.flygene.get()));
                b.accept(new ItemStack(Items.heathgene.get()));
                b.accept(new ItemStack(Items.ragegene.get()));
                b.accept(new ItemStack(Items.sleepgene.get()));


                b.accept(new ItemStack(Items.apple.get()));
                b.accept(new ItemStack(Items.medicinebox.get()));


                b.accept(new ItemStack(Items.calcification.get()));
                b.accept(new ItemStack(Items.masticatory.get()));
                b.accept(new ItemStack(Items.polyphagia.get()));
                b.accept(new ItemStack(Items.quadriceps.get()));
                b.accept(new ItemStack(Items.reanimation.get()));


                b.accept(new ItemStack(Items.ambush.get()));
                b.accept(new ItemStack(Items.atpoverdose.get()));
                b.accept(new ItemStack(Items.autolytic.get()));
                b.accept(new ItemStack(Items.fermentation.get()));
                b.accept(new ItemStack(Items.putrefactive.get()));
                b.accept(new ItemStack(Items.regenerative.get()));
                b.accept(new ItemStack(Items.bat_cell.get()));
                b.accept(new ItemStack(Items.cell_blood_attack.get()));
                b.accept(new ItemStack(Items.cell_desecrate.get()));
                b.accept(new ItemStack(Items.cell_doctor.get()));
                b.accept(new ItemStack(Items.cell_fear.get()));
                b.accept(new ItemStack(Items.cell_harvest.get()));
                b.accept(new ItemStack(Items.cell_immortal.get()));
                b.accept(new ItemStack(Items.cell_not_do.get()));
                b.accept(new ItemStack(Items.cell_rage.get()));
                b.accept(new ItemStack(Items.cell_scientist.get()));
                b.accept(new ItemStack(Items.evil_blood.get()));


                b.accept(new ItemStack(Items.bloodvirus.get()));
                b.accept(new ItemStack(Items.necora.get()));
                b.accept(new ItemStack(Items.necora_baby.get()));


            })
            .build()
    );
    public static final DeferredHolder<CreativeModeTab,?> ITEM = TABS.register("moonstone_item",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(Items.ectoplasmball.get()))
            .title(Component.translatable("itemGroup.tabmoonstone"))
            .displayItems((a,b)->{
                b.accept(new ItemStack(Items.book.get()));
                b.accept(new ItemStack(Items.immortal.get()));
                b.accept(new ItemStack(Items.nine_sword_book.get()));
                b.accept(new ItemStack(Items.moon_stone.get()));
                b.accept(new ItemStack(Items.body_stone.get()));
                b.accept(new ItemStack(Items.probability.get()));
                b.accept(new ItemStack(Items.luck_ring.get()));
                b.accept(new ItemStack(Items.luck_stone.get()));
                b.accept(new ItemStack(Items.the_heart.get()));
                b.accept(new ItemStack(Items.plague.get()));
                b.accept(new ItemStack(Items.maxamout.get()));
                b.accept(new ItemStack(Items.fortunecrystal.get()));
                b.accept(new ItemStack(Items.mayhemcrystal.get()));
                b.accept(new ItemStack(Items.soulbattery.get()));
                b.accept(new ItemStack(Items.soulcube.get()));
                b.accept(new ItemStack(Items.evilcandle.get()));
                b.accept(new ItemStack(Items.evilmug.get()));
                b.accept(new ItemStack(Items.obsidianring.get()));

                b.accept(new ItemStack(Items.beacon.get()));
                b.accept(new ItemStack(Items.ectoplasmstone.get()));
                b.accept(new ItemStack(Items.twistedstone.get()));
                b.accept(new ItemStack(Items.badgeofthedead.get()));
                b.accept(new ItemStack(Items.battery.get()));
                b.accept(new ItemStack(Items.biggreedcrystal.get()));
                b.accept(new ItemStack(Items.bigwarcrystal.get()));
                b.accept(new ItemStack(Items.blackeorb.get()));
                b.accept(new ItemStack(Items.blueamout.get()));
                b.accept(new ItemStack(Items.greedamout.get()));
                b.accept(new ItemStack(Items.redamout.get()));
                b.accept(new ItemStack(Items.greedcrystal.get()));
                b.accept(new ItemStack(Items.warcrystal.get()));
                b.accept(new ItemStack(Items.whiteorb.get()));

                b.accept(new ItemStack(Items.ectoplasmball.get()));
                b.accept(new ItemStack(Items.ectoplasmapple.get()));
                b.accept(new ItemStack(Items.ectoplasmcloub.get()));
                b.accept(new ItemStack(Items.ectoplasmcube.get()));
                b.accept(new ItemStack(Items.ectoplasmhorseshoe.get()));
                b.accept(new ItemStack(Items.ectoplasmprism.get()));
                b.accept(new ItemStack(Items.ectoplasmbattery.get()));
                b.accept(new ItemStack(Items.ectoplasmshild.get()));
                b.accept(new ItemStack(Items.ectoplasmstar.get()));
                b.accept(new ItemStack(Items.ectoplasmsoul.get()));
                b.accept(new ItemStack(Items.ectoplasmtree.get()));

                b.accept(new ItemStack(Items.mbattery.get()));
                b.accept(new ItemStack(Items.mblock.get()));
                b.accept(new ItemStack(Items.mbottle.get()));
                b.accept(new ItemStack(Items.mbox.get()));
                b.accept(new ItemStack(Items.meye.get()));
                b.accept(new ItemStack(Items.mkidney.get()));
                b.accept(new ItemStack(Items.morb.get()));
                b.accept(new ItemStack(Items.mring.get()));
                b.accept(new ItemStack(Items.mshell.get()));
                b.accept(new ItemStack(Items.brain.get()));
                b.accept(new ItemStack(Items.mhead.get()));

                b.accept(new ItemStack(Items.nightmareanchor.get()));
                b.accept(new ItemStack(Items.nightmarecharm.get()));
                b.accept(new ItemStack(Items.nightmareeye.get()));
                b.accept(new ItemStack(Items.nightmaremoai.get()));
                b.accept(new ItemStack(Items.nightmarerotten.get()));
                b.accept(new ItemStack(Items.nightmarestone.get()));
                b.accept(new ItemStack(Items.nightmaretreasure.get()));
                b.accept(new ItemStack(Items.nightmarewater.get()));
                b.accept(new ItemStack(Items.nightmare_orb.get()));
                b.accept(new ItemStack(Items.nightmare_heart.get()));
                b.accept(new ItemStack(Items.nightmare_head.get()));
                b.accept(new ItemStack(Items.nightmare_cube.get()));
                b.accept(new ItemStack(Items.nightmare_axe.get()));
                b.accept(new ItemStack(Items.nightmare_base.get()));
                b.accept(new ItemStack(Items.nightmare_base_black_eye.get()));
                b.accept(new ItemStack(Items.nightmare_base_black_eye_eye.get()));
                b.accept(new ItemStack(Items.nightmare_base_black_eye_heart.get()));
                b.accept(new ItemStack(Items.nightmare_base_black_eye_red.get()));
                b.accept(new ItemStack(Items.nightmare_base_stone.get()));
                b.accept(new ItemStack(Items.nightmare_base_stone_meet.get()));
                b.accept(new ItemStack(Items.nightmare_base_stone_virus.get()));
                b.accept(new ItemStack(Items.nightmare_base_stone_brain.get()));
                b.accept(new ItemStack(Items.nightmare_virus.get()));
                b.accept(new ItemStack(Items.nightmare_base_reversal.get()));
                b.accept(new ItemStack(Items.nightmare_base_reversal_orb.get()));
                b.accept(new ItemStack(Items.nightmare_base_reversal_card.get()));
                b.accept(new ItemStack(Items.nightmare_base_reversal_mysterious.get()));
                b.accept(new ItemStack(Items.nightmare_base_redemption.get()));
                b.accept(new ItemStack(Items.nightmare_base_redemption_deception.get()));
                b.accept(new ItemStack(Items.nightmare_base_redemption_degenerate.get()));
                b.accept(new ItemStack(Items.nightmare_base_redemption_down_and_out.get()));
                b.accept(new ItemStack(Items.nightmare_base_fool.get()));
                b.accept(new ItemStack(Items.nightmare_base_fool_soul.get()));
                b.accept(new ItemStack(Items.nightmare_base_fool_betray.get()));
                b.accept(new ItemStack(Items.nightmare_base_fool_bone.get()));
                b.accept(new ItemStack(Items.nightmare_base_insight.get()));
                b.accept(new ItemStack(Items.nightmare_base_insight_drug.get()));
                b.accept(new ItemStack(Items.nightmare_base_insight_insane.get()));
                b.accept(new ItemStack(Items.nightmare_base_insight_collapse.get()));
                b.accept(new ItemStack(Items.nightmare_base_start.get()));
                b.accept(new ItemStack(Items.nightmare_base_start_pod.get()));
                b.accept(new ItemStack(Items.nightmare_base_start_egg.get()));
                b.accept(new ItemStack(Items.nightmare_base_start_power.get()));

                b.accept(new ItemStack(Items.magiceye.get()));
                b.accept(new ItemStack(Items.magicstone.get()));
                b.accept(new ItemStack(Items.nanocube.get()));
                b.accept(new ItemStack(Items.nanorobot.get()));
                b.accept(new ItemStack(Items.thedoomstone.get()));
                b.accept(new ItemStack(Items.thefruit.get()));
                b.accept(new ItemStack(Items.doomeye.get()));
                b.accept(new ItemStack(Items.doomswoud.get()));
                b.accept(new ItemStack(Items.wind.get()));
                b.accept(new ItemStack(Items.wind_and_rain.get()));

                b.accept(new ItemStack(Items.blood_candle.get()));
                b.accept(new ItemStack(Items.the_blood_book.get()));
                b.accept(new ItemStack(Items.blood_magic_box.get()));
                b.accept(new ItemStack(Items.blood.get()));
                b.accept(new ItemStack(Items.max_sword.get()));
                b.accept(new ItemStack(Items.max_eye.get()));
                b.accept(new ItemStack(Items.max_blood_eye.get()));
                b.accept(new ItemStack(Items.max_blood_cube.get()));
                b.accept(new ItemStack(Items.blood_amout.get()));
                b.accept(new ItemStack(Items.blood_snake.get()));
                b.accept(new ItemStack(Items.the_prison_of_sin.get()));

                b.accept(new ItemStack(Items.evil_mob.get()));
                b.accept(new ItemStack(Items.god_lead.get()));
                b.accept(new ItemStack(Items.malice_die.get()));
                b.accept(new ItemStack(Items.blood_sun.get()));
                b.accept(new ItemStack(Items.blood_jelly.get()));
                b.accept(new ItemStack(Items.rage_blood_head.get()));
                b.accept(new ItemStack(Items.killer.get()));
                b.accept(new ItemStack(Items.as_amout.get()));
                b.accept(new ItemStack(Items.million.get()));

                b.accept(new ItemStack(Items.owner_blood_eye.get()));
                b.accept(new ItemStack(Items.owner_blood_attack_eye.get()));
                b.accept(new ItemStack(Items.owner_blood_speed_eye.get()));
                b.accept(new ItemStack(Items.owner_blood_effect_eye.get()));
                b.accept(new ItemStack(Items.owner_blood_boom_eye.get()));
                b.accept(new ItemStack(Items.owner_blood_vex.get()));
                b.accept(new ItemStack(Items.owner_blood_earth.get()));

            })
            .build());
}


