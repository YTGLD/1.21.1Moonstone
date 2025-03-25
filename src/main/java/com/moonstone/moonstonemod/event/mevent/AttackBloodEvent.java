package com.moonstone.moonstonemod.event.mevent;

import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.entity.owner_blood;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;

public class AttackBloodEvent extends Event {
    private final attack_blood attackBlood;
    private final Player player;
    private float speed= 1;
    private float damage= 1;
    private float maxTime= 1;

    public AttackBloodEvent(attack_blood attackBlood, Player player) {
        this.attackBlood = attackBlood;
        this.player = player;
    }


    public attack_blood getAttackBlood() {
        return attackBlood;
    }

    public Player getPlayer() {
        return player;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public static class OwnerBloodEvent extends Event {
        private final owner_blood ownerBlood;
        private final Player player;
        private float attackSpeed =1;
        public OwnerBloodEvent(owner_blood ownerBlood1, Player player1) {
            this.ownerBlood = ownerBlood1;
            this.player = player1;
        }

        public Player getPlayer() {
            return player;
        }

        public float getAttackSpeed() {
            return attackSpeed;
        }

        public void setAttackSpeed(float attackSpeed) {
            this.attackSpeed = attackSpeed;
        }

        public owner_blood getOwnerBlood() {
            return ownerBlood;
        }
    }
}
