package com.moonstone.moonstonemod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = MoonStoneMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {

    public static Config SERVER;
    public static ModConfigSpec fc;
    static {
        final Pair<Config, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }
    public Config(ModConfigSpec.Builder BUILDER){
        plague_speed = BUILDER
                .comment("The growth rate of plague research sites")
                .defineInRange("GrowthSpeed", 0.1, 0, 100);

        plague_pain = BUILDER
                .comment("The corrosion speed of the plague")
                .defineInRange("CorrosionSpeed", 0.01, 0, 100);


        plague_effect = BUILDER
                .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
                .defineInRange("CorrosionEffect", 1d, 0.01, 100);
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
        ZombieNightmareGiant = BUILDER
                .comment("What creatures should be killed and dropped")
                .define("Mob","minecraft:warden");

        BUILDER.build();
    }
    private ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

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

    public ModConfigSpec.ConfigValue<String> ZombieNightmareGiant;
    public ModConfigSpec.ConfigValue<String> ZombieNightmareGiantModID = BUILDER
            .comment("What modID with ZombieNightmareGiant Config")
            .define("ModId","minecraft");

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }
}
