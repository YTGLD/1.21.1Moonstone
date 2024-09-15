package com.moonstone.moonstonemod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = MoonStoneMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue plague_speed = BUILDER
            .comment("The growth rate of plague research sites")
            .defineInRange("GrowthSpeed", 0.1, 0, 100);
    public static final ModConfigSpec.DoubleValue plague_pain = BUILDER
            .comment("The corrosion speed of the plague")
            .defineInRange("CorrosionSpeed", 0.01, 0, 100);

    public static final ModConfigSpec.DoubleValue plague_effect = BUILDER
            .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
            .defineInRange("CorrosionEffect", 1d, 0.01, 100);

    public static final ModConfigSpec.IntValue nightmare_moai = BUILDER
            .comment("Nightmare Moai's enchantment level bonus")
            .defineInRange("EnchantmentBonus", 2, 0, 100);
    public static final ModConfigSpec.IntValue m_brain_many = BUILDER
            .comment("How many critical hits will brain make")
            .defineInRange("MBrain_many", 5, 1, 100);

    public static final ModConfigSpec.DoubleValue m_brain_critical = BUILDER
            .comment("Brain's critical strike multiplier")
            .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);

    public static final ModConfigSpec.DoubleValue battery_speed = BUILDER
            .comment("The speed of the battery")
            .defineInRange("battery", 0.1, 0, 999);

    public static final ModConfigSpec.DoubleValue quadriceps_speed = BUILDER
            .comment("The speed of the quadriceps")
            .defineInRange("quadriceps", 0.25, 0, 999);

    public static final ModConfigSpec.DoubleValue flygene_speed = BUILDER
            .comment("The speed of the flygene")
            .defineInRange("flygene", 0.125, 0, 999);
    public static final ModConfigSpec.DoubleValue bloodvirus_speed = BUILDER
            .comment("The speed of the bloodvirus")
            .defineInRange("bloodvirus", 0.175, 0, 999);
    public static final ModConfigSpec.DoubleValue motor_speed = BUILDER
            .comment("The speed of the motor")
            .defineInRange("motor", 0.15, 0, 999);

    public static final ModConfigSpec.ConfigValue<String> ZombieNightmareGiant = BUILDER
            .comment("What creatures should be killed and dropped")
            .define("Mob","warden");
    public static final ModConfigSpec.ConfigValue<String> ZombieNightmareGiantModID = BUILDER
            .comment("What modID with ZombieNightmareGiant Config")
            .define("ModId","minecraft");

    public static final ModConfigSpec.BooleanValue show_blood = BUILDER
            .comment("If true, display the blood model")
            .define("ModelByMaxEye",false);

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }
    static final ModConfigSpec SPEC = BUILDER.build();
}
