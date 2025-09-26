package com.ytgld.seeking_immortals;

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

    public final ModConfigSpec.BooleanValue nightmare_base_black_eye ;
    public final ModConfigSpec.DoubleValue nightmare_base_stone ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool ;
    public final ModConfigSpec.IntValue nightmare_base_insight ;
    public final ModConfigSpec.IntValue nightmare_base_redemption ;
    public final ModConfigSpec.IntValue nightmare_base_reversal ;
    public final ModConfigSpec.IntValue nightmare_base_start ;
    public final ModConfigSpec.DoubleValue disintegrating_stone ;
    public final ModConfigSpec.IntValue give_nightmare_base_insight_drug ;
    public final ModConfigSpec.IntValue immortal ;




    public   ModConfigSpec.BooleanValue god_lead ;






    public   ModConfigSpec.IntValue lootMan ;
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
    public   ModConfigSpec.BooleanValue itemQuality;

    public ModConfigSpec.DoubleValue rage_eye;
    public ModConfigSpec.DoubleValue rage_eye_copy;


    public   ModConfigSpec.BooleanValue canFlySword;



    public   ModConfigSpec.IntValue ytgld_research ;
    public   ModConfigSpec.IntValue ytgld_curse ;
    public   ModConfigSpec.BooleanValue off_or_on_ytgld ;
    public   ModConfigSpec.BooleanValue giveVirus ;


    public   ModConfigSpec.BooleanValue blockParticle ;
    public   ModConfigSpec.BooleanValue entityParticle ;


    public   ModConfigSpec.BooleanValue disEntity ;




    public Config(ModConfigSpec.Builder BUILDER){
        {
            BUILDER.push("月之石全局");
            disEntity = BUILDER
                    .comment("禁用模组的所有实体")
                    .define("disEntity", false);
            BUILDER.pop();
        }
        BUILDER.push("月之石本体");
        {
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
                canFlySword = BUILDER
                        .comment("”七剑修罗·万法“和”七剑修罗·剑阵之章“同时佩戴后仍然可以发射飞剑")
                        .define("canFlySword", true);

                ytgld_curse = BUILDER
                        .comment("远古病毒的增值速度，单位：刻，值越大速度越慢")
                        .defineInRange("ytgld_curse", 15, 1, 999999);
                ytgld_research = BUILDER
                        .comment("远古病毒的研究速度，10点是0.1%")
                        .defineInRange("ytgld_research", 10, 1, 999999);
                off_or_on_ytgld = BUILDER
                        .comment("启用远古病毒")
                        .define("off_or_on_ytgld", true);

                giveVirus = BUILDER
                        .comment("第一次死亡给予远古病毒")
                        .define("giveVirus", false);

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





                blockParticle = BUILDER
                        .comment("一些（诺克萨斯纹章/决裁徽章/符石）的粒子效果")
                        .define("blockParticle", true);
                entityParticle = BUILDER
                        .comment("一些实体的粒子效果")
                        .define("entityParticle", true);

                god_lead = BUILDER
                        .comment("血虐之铅杀死生物后冷却会清除")
                        .define("god_lead", true);



                BUILDER.pop();
            }
            {
                BUILDER.push("战利品");
                lootMan = BUILDER
                        .comment("人类基因的药物再战利品箱子里出现的几率，值越大，概率越高")
                        .defineInRange("lootMan", 13, 1, 100);
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
        BUILDER.pop();


        BUILDER.push("噩梦");

        nightmareBaseMaxItem = BUILDER
                .translation("moonstone.config.nightmareBaseMaxItem")
                .defineInRange("nightmare_", 3, 0, 7);

        Nightecora = BUILDER
                .translation("moonstone.config.Nightecora")
                .defineInRange("Nightecora_", 10, 0, 100);
        nightmare_base_redemption_deception = BUILDER
                .translation("moonstone.config.nightmare_base_redemption_deception")
                .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
        nightmare_base_redemption_deception_time = BUILDER
                .translation("moonstone.config.nightmare_base_redemption_deception_time")
                .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

        nightmare_base_fool_bone = BUILDER
                .translation("moonstone.config.nightmare_base_fool_bone")
                .defineInRange("nightmare_base_fool_bone_", 1.2f, 0, 9999);

        nightmare_base_insight_drug = BUILDER
                .translation("moonstone.config.nightmare_base_insight_drug")
                .defineInRange("nightmare_base_insight_drug_", 50, 0, 99999);

        nightmare_base_insight_drug_2 = BUILDER
                .translation("moonstone.config.nightmare_base_insight_drug_2")
                .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);

        nightmare_base_insight_insane = BUILDER
                .translation("moonstone.config.nightmare_base_insight_insane")
                .defineInRange("nightmare_base_insight_insane_", 25, 0, 99999);


        {
            BUILDER.push("不朽轮回之印章");

            immortal = BUILDER
                    .translation("moonstone.config.immortal")
                    .defineInRange("immortal_", 70, 0, 100);

            BUILDER.pop();

            BUILDER.push("裂天石");
            disintegrating_stone = BUILDER
                    .translation("moonstone.config.disintegrating_stone")
                    .defineInRange("disintegrating_stone", 1.3f, 0, 10000);
            BUILDER.pop();

            BUILDER.push("邪念之窥眸");
            nightmare_base_black_eye = BUILDER
                    .translation("moonstone.config.nightmare_base_black_eye")
                    .define("nightmare_base_black_eye", true);
            BUILDER.pop();

            BUILDER.push("死兆方尖碑");
            nightmare_base_stone = BUILDER
                    .translation("moonstone.config.nightmare_base_stone")
                    .defineInRange("nightmare_base_stone", 5f,1,999);
            BUILDER.pop();

            BUILDER.push("愚者之危");
            nightmare_base_fool = BUILDER
                    .translation("moonstone.config.nightmare_base_fool")
                    .defineInRange("nightmare_base_fool", 0.5f,0,1);
            BUILDER.pop();

            BUILDER.push("噩梦洞悉者");
            nightmare_base_insight = BUILDER
                    .translation("moonstone.config.nightmare_base_insight")
                    .defineInRange("nightmare_base_insight", 2,0,1000);
            BUILDER.pop();

            BUILDER.push("“救赎”");
            nightmare_base_redemption = BUILDER
                    .translation("moonstone.config.nightmare_base_redemption")
                    .defineInRange("nightmare_base_redemption", 15,0,100);
            BUILDER.pop();

            BUILDER.push("颠倒之物");
            nightmare_base_reversal = BUILDER
                    .translation("moonstone.config.nightmare_base_reversal")
                    .defineInRange("nightmare_base_reversal", 25,0,100);
            BUILDER.pop();

            BUILDER.push("噩梦之起始");
            nightmare_base_start = BUILDER
                    .translation("moonstone.config.nightmare_base_start")
                    .defineInRange("nightmare_base_start", 75,0,100);
            BUILDER.pop();
            BUILDER.push("获取");
            give_nightmare_base_insight_drug = BUILDER
                    .translation("moonstone.config.give_nightmare_base_insight_drug")
                    .defineInRange("give_nightmare_base_insight_drug", 9,1,100);
            BUILDER.pop();

            BUILDER.push("惶恐肉瘤");
            nightmare_base_black_eye_eye = BUILDER
                    .translation("moonstone.config.nightmare_base_black_eye_eye")
                    .defineInRange("nightmare_base_black_eye_eye", 1.25f,0.01f,100);
            BUILDER.pop();

            BUILDER.push("幽怨之魂");
            nightmare_base_fool_soul = BUILDER
                    .translation("moonstone.config.nightmare_base_fool_soul")
                    .defineInRange("nightmare_base_fool_soul", 1,0.01f,100);
            nightmare_base_fool_soul2 = BUILDER
                    .translation("moonstone.config.nightmare_base_fool_soul2")
                    .defineInRange("nightmare_base_fool_soul2", 1,0.01f,100);
            BUILDER.pop();
            BUILDER.push("灵念药戒");
            ring = BUILDER
                    .translation("moonstone.config.ring")
                    .defineInRange("ring", 2,0,100f);
            BUILDER.pop();
            BUILDER.push("虚伪的自尊");
            hypocritical_self_esteem = BUILDER
                    .translation("moonstone.config.hypocritical_self_esteem")
                    .defineInRange("hypocritical_self_esteem", 10,1,100f);
            BUILDER.pop();
            BUILDER.push("邪异古烛");
            candle = BUILDER
                    .translation("moonstone.config.candle")
                    .defineInRange("candle", 1.25,1,100f);
            candle2 = BUILDER
                    .translation("moonstone.config.candle2")
                    .defineInRange("candle2", 1.5,1,100f);
            candle3 = BUILDER
                    .translation("moonstone.config.candle3")
                    .defineInRange("candle3", 100,0,100000f);
            BUILDER.pop();
            BUILDER.push("孤狼");
            wolf = BUILDER
                    .translation("moonstone.config.wolf")
                    .defineInRange("wolf", 10,1,100f);

            BUILDER.pop();

            BUILDER.push("神血祖符");
            blood_god_kill = BUILDER
                    .translation("moonstone.config.blood_god_kill")
                    .defineInRange("blood_god_kill", 500,1,Integer.MAX_VALUE);
            blood_god_heal = BUILDER
                    .translation("moonstone.config.blood_god_heal")
                    .defineInRange("blood_god_heal", 5000,1,Integer.MAX_VALUE);
            blood_god_damage = BUILDER
                    .translation("moonstone.config.blood_god_damage")
                    .defineInRange("blood_god_damage", 10000,1,Integer.MAX_VALUE);

            BUILDER.pop();

            BUILDER.push("血祭神颅");
            bone_or_god_give = BUILDER
                    .translation("moonstone.config.bone_or_god_give")
                    .defineInRange("bone_or_god_give", 200,1,Integer.MAX_VALUE);
            BUILDER.pop();
            BUILDER.push("献祭血戒");
            blood_ring = BUILDER
                    .translation("moonstone.config.blood_ring")
                    .defineInRange("blood_ring", 3000,1,Integer.MAX_VALUE);
            BUILDER.pop();
            BUILDER.push("刺鼻不死药");
            biochemistry = BUILDER
                    .translation("moonstone.config.biochemistry")
                    .defineInRange("biochemistry", 7000,1,Integer.MAX_VALUE);
            BUILDER.pop();
        }


        BUILDER.pop();
    }
    public  final ModConfigSpec.IntValue biochemistry ;
    public  final ModConfigSpec.IntValue blood_ring ;

    public  final ModConfigSpec.IntValue bone_or_god_give ;

    public  final ModConfigSpec.IntValue blood_god_kill ;
    public  final ModConfigSpec.IntValue blood_god_heal ;
    public  final ModConfigSpec.IntValue blood_god_damage ;



    public final ModConfigSpec.DoubleValue wolf ;
    public final ModConfigSpec.DoubleValue candle ;
    public final ModConfigSpec.DoubleValue candle2 ;
    public final ModConfigSpec.DoubleValue candle3 ;



    public final ModConfigSpec.DoubleValue hypocritical_self_esteem ;
    public final ModConfigSpec.DoubleValue ring ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool_soul ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool_soul2 ;

    public final ModConfigSpec.DoubleValue nightmare_base_black_eye_eye ;

    public final   ModConfigSpec.IntValue Nightecora ;


    public final   ModConfigSpec.IntValue nightmare_base_redemption_deception ;

    public final   ModConfigSpec.DoubleValue nightmare_base_fool_bone ;
    public final   ModConfigSpec.IntValue nightmare_base_insight_drug ;
    public final   ModConfigSpec.IntValue nightmare_base_insight_drug_2 ;

    public final   ModConfigSpec.IntValue nightmare_base_insight_insane ;
    public final   ModConfigSpec.IntValue nightmare_base_redemption_deception_time ;


    public final   ModConfigSpec.IntValue nightmareBaseMaxItem ;
    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    }

}
