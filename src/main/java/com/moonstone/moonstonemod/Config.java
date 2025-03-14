package com.moonstone.moonstonemod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static Config SERVER;
    public static ModConfigSpec fc;

    static {
        final Pair<Config, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }
    public Config(ModConfigSpec.Builder BUILDER){
        {
            BUILDER.push("一般配置");
            canUnequipMoonstoneItem = BUILDER
                    .comment("可以取下月之石的“不可以取下”的物品")
                    .define("Can", false);
            giveBook = BUILDER
                    .comment("开局给书")
                    .define("give", true);
            immortalZombie = BUILDER
                    .comment("玩家无法伤害月之石实体")
                    .define("immortalZombieOfOwner", false);
            itemQuality = BUILDER
                    .comment("物品再发现时可以获得品阶")
                    .define("Quality_", false);

            BUILDER.pop();
        }
        {
            BUILDER.push("噩梦");
            nightmareBaseMaxItem = BUILDER
                    .comment("“”噩梦基座“给玩家的罪孽数量")
                    .defineInRange("nig", 3, 0, 7);

            Nightecora = BUILDER
                    .comment("Nightecora病毒的额外生命值惩罚，单位百分比")
                    .defineInRange("Nightecora", 25, 0, 100);
            nightmare_base_redemption_deception = BUILDER
                    .comment("“欺骗”恢复的生命值，单位百分比")
                    .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
            nightmare_base_redemption_deception_time = BUILDER
                    .comment("“欺骗”恢复的生命值，单位秒")
                    .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

            nightmare_base_fool_bone = BUILDER
                    .comment("危险的头骨造成的额外伤害，“2”是两倍")
                    .defineInRange("nightmare_base_fool_bone", 2, 0, 9999);

            nightmare_base_insight_drug = BUILDER
                    .comment("疯狂灵药的最大属性加成，单位百分比")
                    .defineInRange("nightmare_base_insight_drug", 100, 0, 99999);

            nightmare_base_insight_drug_2 = BUILDER
                    .comment("疯狂灵药的单物品计算的属性衰败，单位百分比")
                    .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);

            nightmare_base_insight_insane = BUILDER
                    .comment("癫狂之石的杀死生物后获得的伤害加成，单位百分比")
                    .defineInRange("nightmare_base_insight_insane", 150, 0, 99999);
            BUILDER.pop();
        }
        {
            BUILDER.push("狂暴or扭曲");
            rage_eye = BUILDER
                    .comment("狂暴之眼最多可以偷盗的属性（相对于玩家）")
                    .defineInRange("rage_eye", 0.25, 0, 1000);
            rage_eye_copy = BUILDER
                    .comment("狂暴之眼最多可以偷盗的属性（相对于怪物）")
                    .defineInRange("rage_eye_copy", 0.1, 0, 1000);

            pain_ring = BUILDER
                    .comment("邪祟之戒获得最大伤害加成和增加速度，单位百分比")
                    .defineInRange("pain_ring_", 100f, 0, 999999);
            BUILDER.pop();
        }


        {
            BUILDER.push("一般物品");
            nightmare_moai = BUILDER
                    .comment("深渊石球的附魔等级加成")
                    .defineInRange("EnchantmentBonus", 2, 0, 100);

            m_brain_many = BUILDER
                    .comment("莫里斯脑子的伤害次数")
                    .defineInRange("MBrain_many", 5, 1, 100);
            m_brain_critical = BUILDER
                    .comment("莫里斯脑子的伤害")
                    .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);
            battery_speed = BUILDER
                    .comment("电池的速度")
                    .defineInRange("battery", 0.1, 0, 999);
            quadriceps_speed = BUILDER
                    .comment("四头肌强化的速度")
                    .defineInRange("quadriceps", 0.25, 0, 999);
            flygene_speed = BUILDER
                    .comment("夜行蝠突变基因的速度")
                    .defineInRange("flygene", 0.125, 0, 999);
            bloodvirus_speed = BUILDER
                    .comment("暗影瘟疫的速度")
                    .defineInRange("bloodvirus", 0.175, 0, 999);

            motor_speed = BUILDER
                    .comment("运动控制强化的速度")
                    .defineInRange("motor", 0.15, 0, 999);

            BUILDER.pop();
        }
        {
            BUILDER.push("战利品");
            bat = BUILDER
                    .comment("”暗影瘟疫“的战利品出现几率，值越大，概率越高")
                    .defineInRange("Plague_probability", 10, 1, 100);
            necora = BUILDER
                    .comment("”Necora病毒“的战利品出现几率，值越大，概率越高")
                    .defineInRange("Necora_probability", 10, 1, 100);

            night = BUILDER
                    .comment("”深渊之眼“的战利品出现几率，值越大，概率越高")
                    .defineInRange("Nightmare_probability", 10, 1, 100);

            common = BUILDER
                    .comment("注意！这个值影响了普通战利品的出现概率。它的值越大，概率越小！不要改反了")
                    .defineInRange("Common_probability", 1, 0.1, 100);
            BUILDER.pop();
        }
        BUILDER.build();
    }



    public   ModConfigSpec.IntValue Nightecora ;


    public   ModConfigSpec.IntValue nightmare_base_redemption_deception ;

    public   ModConfigSpec.IntValue nightmare_base_fool_bone ;
    public   ModConfigSpec.IntValue nightmare_base_insight_drug ;
    public   ModConfigSpec.IntValue nightmare_base_insight_drug_2 ;

    public   ModConfigSpec.IntValue nightmare_base_insight_insane ;
    public   ModConfigSpec.IntValue nightmare_base_redemption_deception_time ;
    public   ModConfigSpec.DoubleValue pain_ring ;





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
    public ModConfigSpec.BooleanValue canUnequipMoonstoneItem ;
    public ModConfigSpec.BooleanValue immortalZombie ;
    public   ModConfigSpec.IntValue nightmareBaseMaxItem ;
    public   ModConfigSpec.BooleanValue itemQuality;

    public ModConfigSpec.DoubleValue rage_eye;
    public ModConfigSpec.DoubleValue rage_eye_copy;













    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    }

}
