package com.moonstone.moonstonemod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static Config SERVER;
    public static ModConfigSpec fc;

    static {
        final Pair<Config, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }
    public Config(ModConfigSpec.Builder BUILDER){

        BUILDER.push("ItemSet");


        listEctoplasm  = BUILDER
                .comment("Items required for the Luck star set")
                .define("listStar",new ArrayList<>(List.of(
                        "ectoplasmstar",
                        "luck_ring",
                        "luck_stone"
                )));
        listEctoplasmLuckStar  = BUILDER
                .comment("Items required for the Luck Ectoplasm star set")
                .define("listEctoplasm",new ArrayList<>(List.of(
                        "ectoplasmstar",
                        "ectoplasmapple",
                        "soulcube",
                        "mkidney"
                        )));
        listBattery  = BUILDER
                .comment("Items required for the Battery Man set")
                .define("listBattery",new ArrayList<>(List.of(
                        "soulbattery",
                        "ectoplasmbattery",
                        "battery"
                )));
        listBatterySuper  = BUILDER
                .comment("Items required for the Battery Man Super set")
                .define("listBatteryManSuper",new ArrayList<>(List.of(
                        "soulbattery",
                        "ectoplasmbattery",
                        "battery",
                        "mbattery"
                )));
        listMls  = BUILDER
                .comment("Items required for the maulice set")
                .define("listMls",new ArrayList<>(List.of(
                        "mkidney",
                        "mshell",
                        "mring"
                )));
        listMlsSuper  = BUILDER
                .comment("Items required for the maulice Super set")
                .define("listMlsSuper",new ArrayList<>(List.of(
                        "mshell",
                        "meye",
                        "mblock",
                        "mbottle"
                )));
        listRecovery  = BUILDER
                .comment("Items required for the Anaerobic recovery set")
                .define("listAnaerobicRecovery",new ArrayList<>(List.of(
                        "necora",
                        "regenerative",
                        "mbottle",
                        "bone_structure",
                        "organizational_regeneration"
                )));

        listCellularPathologyPromotion  = BUILDER
                .comment("Items required for the Cellular Pathology Promotion set")
                .define("listCellularPathologyPromotion",new ArrayList<>(List.of(
                        "necora",
                        "bone_structure",
                        "exercise_reinforcement",
                        "detect",
                        "adrenaline",
                        "medicinebox"
                )));

        listManOfLife  = BUILDER
                .comment("Items required for the Life Man set")
                .define("listManOfLife",new ArrayList<>(List.of(
                        "medicinebox",
                        "dna",
                        "ectoplasmapple",
                        "bigwarcrystal",
                        "greedcrystal"
                )));

        listMethaneEmission  = BUILDER
                .comment("Items required for the Methane Emission set")
                .define("listMethaneEmission",new ArrayList<>(List.of(
                        "necora",
                        "cell_boom",
                        "cell_calcification",
                        "air",
                        "giant_boom_cell",
                        "mbattery"
                )));

        listLifeManSuper  = BUILDER
                .comment("Items required for the Life Man Super set")
                .define("listLifeManSuper",new ArrayList<>(List.of(
                        "medicinebox",
                        "dna",
                        "bigwarcrystal",
                        "biggreedcrystal",
                        "maxamout"
                )));
        BUILDER.pop();


        BUILDER.push("SetOffOrOn");
        offSet  = BUILDER
                .comment("Enable accessory set effect")
                .define("offOrOn",true);
        BUILDER.pop();


        plague_speed = BUILDER
                .comment("The growth rate of plague research sites")
                .defineInRange("GrowthSpeed", 0.1, 0, 100);

        plague_pain = BUILDER
                .comment("The corrosion speed of the plague")
                .defineInRange("CorrosionSpeed", 0.01, 0, 100);

        plague_effect = BUILDER
                .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
                .defineInRange("CorrosionEffect", 1d, 0.01, 100);

        nightmare_moai = BUILDER
                .comment("Nightmare Moai's enchantment level bonus")
                .defineInRange("EnchantmentBonus", 2, 0, 100);


        m_brain_many = BUILDER
                .comment("How many critical hits will brain make")
                .defineInRange("MBrain_many", 5, 1, 100);
        m_brain_critical = BUILDER
                .comment("Brain's critical strike multiplier")
                .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);
        battery_speed = BUILDER
                .comment("The speed of the battery")
                .defineInRange("battery", 0.1, 0, 999);
        quadriceps_speed = BUILDER
                .comment("The speed of the quadriceps")
                .defineInRange("quadriceps", 0.25, 0, 999);
        flygene_speed = BUILDER
                .comment("The speed of the flygene")
                .defineInRange("flygene", 0.125, 0, 999);
        bloodvirus_speed = BUILDER
                .comment("The speed of the bloodvirus")
                .defineInRange("bloodvirus", 0.175, 0, 999);

        motor_speed = BUILDER
                .comment("The speed of the motor")
                .defineInRange("motor", 0.15, 0, 999);

        giveBook = BUILDER
                .comment("Starting with a book or not")
                .define("give",true);
        bat = BUILDER
                .comment("The probability of discovering Shadow Plague items from the chests")
                .defineInRange("Plague_probability", 10, 1, 100);
        necora = BUILDER
                .comment("The probability of Necora items from the chests")
                .defineInRange("Necora_probability", 10, 1, 100);

        night = BUILDER
                .comment("The probability of Nightmare items from the chests")
                .defineInRange("Nightmare_probability", 10, 1, 100);

        common = BUILDER
                .comment("The larger this value, the lower the probability of discovering the item")
                .defineInRange("Common_probability", 1, 0.1, 100);

        BUILDER.build();
    }

    public ModConfigSpec.DoubleValue plague_speed;
    public ModConfigSpec.DoubleValue plague_pain;

    public ModConfigSpec.DoubleValue plague_effect;

    public ModConfigSpec.IntValue nightmare_moai;
    public ModConfigSpec.IntValue m_brain_many;

    public ModConfigSpec.DoubleValue m_brain_critical;

    public ModConfigSpec.DoubleValue battery_speed;

    public ModConfigSpec.DoubleValue quadriceps_speed;

    public ModConfigSpec.DoubleValue flygene_speed;
    public ModConfigSpec.DoubleValue bloodvirus_speed;
    public ModConfigSpec.DoubleValue motor_speed;
    public ModConfigSpec.BooleanValue giveBook;
    public  ModConfigSpec.IntValue bat ;
    public  ModConfigSpec.IntValue necora ;
    public  ModConfigSpec.IntValue night ;
    public  ModConfigSpec.DoubleValue common ;
    public ModConfigSpec.BooleanValue offSet;
    public  ModConfigSpec.ConfigValue<List<String>> listEctoplasm;
    public  ModConfigSpec.ConfigValue<List<String>> listEctoplasmLuckStar;
    public  ModConfigSpec.ConfigValue<List<String>> listBattery;



    public  ModConfigSpec.ConfigValue<List<String>> listBatterySuper;
    public  ModConfigSpec.ConfigValue<List<String>> listMls;
    public  ModConfigSpec.ConfigValue<List<String>> listMlsSuper;
    public  ModConfigSpec.ConfigValue<List<String>> listRecovery;
    public  ModConfigSpec.ConfigValue<List<String>> listCellularPathologyPromotion;

    public  ModConfigSpec.ConfigValue<List<String>> listMethaneEmission;
    public  ModConfigSpec.ConfigValue<List<String>> listManOfLife;
    public  ModConfigSpec.ConfigValue<List<String>> listLifeManSuper;
    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    }

}
