package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.line;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class blood_amout extends Item implements ICurioItem, Blood {
    public blood_amout() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    public static void Hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (event.getSource().getEntity()!=null&& Handler.hascurio(player, Items.blood_amout.get())){
                line line = new line(EntityTs.line.get(),player.level());
                line.setPos(player.position());
                line.setOwnerUUID(player.getUUID());
                player.level().addFreshEntity(line);
            }
        }
    }
}
