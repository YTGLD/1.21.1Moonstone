package com.ytgld.seeking_immortals.event.key;

import com.ytgld.seeking_immortals.item.nightmare.base.biochemistry;
import com.ytgld.seeking_immortals.item.nightmare.base.blood_ring;
import com.ytgld.seeking_immortals.item.nightmare.base.crazy_drug;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.nightmare_base_stone_meet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class SINetworkHandler {
    public static void register(final PayloadRegistrar registrar) {
        registrar.playToServer(UseCurio.TYPE, UseCurio.USE_CURIO_STREAM_CODEC,
                handlerUse::handleOpenCurios);

    }
    public static HandlerUse handlerUse = new HandlerUse();
    public static class HandlerUse {
        public void handleOpenCurios(final UseCurio data, final IPayloadContext ctx) {
            ctx.enqueueWork(() -> {
                Player player = ctx.player();
                if (player instanceof ServerPlayer serverPlayer) {
                    nightmare_base_stone_meet.lookAttack(serverPlayer);

                    CuriosApi.getCuriosInventory(serverPlayer).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (!stack.isEmpty()) {
                                    blood_ring.giveBlood(serverPlayer, stack);
                                    biochemistry.addEffectForBiochemistry(serverPlayer,stack);
                                    crazy_drug.crazy_drugAddEffect(serverPlayer,stack);
                                }
                            }
                        }
                    });
                }
            });
        }
    }
}
