package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.event.mevent.AttackBloodEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;

public class EventHandler {

    public static AttackBloodEvent attackBloodEvent(attack_blood attackBlood, Player player) {
        return NeoForge.EVENT_BUS.post(new AttackBloodEvent(attackBlood, player));
    }
    public static AttackBloodEvent.OwnerBloodEvent ownerBloodEvent(owner_blood ownerBlood, Player player) {
        return NeoForge.EVENT_BUS.post(new AttackBloodEvent.OwnerBloodEvent(ownerBlood, player));
    }
}
