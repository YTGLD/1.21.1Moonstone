package com.ytgld.seeking_immortals.event.now;

import com.ytgld.seeking_immortals.event.CurioDropEvent;
import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.item.nightmare.base.evil_thought_bone;
import com.ytgld.seeking_immortals.item.nightmare.eye;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.nightmare_base_stone;
import net.neoforged.bus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void CurioLivingIncomingDamageEvent(CurioHurtEvent event){
        eye.CurioDamageEvent((event));
        nightmare_base_stone.CurioHurtEvent((event));
    }
    @SubscribeEvent
    public void CurioDropEvent(CurioDropEvent event){
        evil_thought_bone.CurioDropEvent(event);
    }
}
