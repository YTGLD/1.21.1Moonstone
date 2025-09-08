package com.ytgld.seeking_immortals;

import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Map;

public class Handler {
    public static MutableComponent addColorText(String text,float speed) {

        int s = Component.translatable(text).toString().length();
        int r = (int) (255 * Math.sin(NewEvent.time / speed));
        int b = (int) (100 * Math.sin(s) * Math.sin(NewEvent.time / 200f));
        if (r < 0) {
            r = -r;
        }
        if (b < 0) {
            b = -b;
        }
        return Component.translatable(text).withStyle(Style.EMPTY.withColor(
                TextColor.fromRgb(Light.ARGB.color(
                                255,
                                r,
                                50,
                                b + 155
                        )
                ))
        );
    }
    public static float getDistanceToGround(Entity entity) {
        Vec3 position = entity.position();
        BlockPos blockPos = new BlockPos((int) position.x, (int) position.y, (int) position.z);

        BlockPos groundPos = blockPos.below();
        while (groundPos.getY() > -100 && entity.level().getBlockState(groundPos).isAir()) {
            groundPos = groundPos.below();
        }
        Vec3 groundCenter = new Vec3(groundPos.getX() + 0.5, groundPos.getY() + 0.5, groundPos.getZ() + 0.5);
        return (float) position.distanceTo(groundCenter);
    }
    public static int getTagNumber(ItemStack must, String name){
        CompoundTag tag = must.get(DataReg.tag);
        if (tag != null){
            return tag.getInt(name);
        }
        return 0;
    }
    public static void addTagNumber(ItemStack must, String name, Player player, int giveNumber){
        if (Handler.hascurio(player,must.getItem())){
            CompoundTag tag = must.get(DataReg.tag);
            if (tag != null) {
                tag.putInt(name, tag.getInt(name) + giveNumber);
            }
        }
    }
    public static int getElement(ItemStack stack, AllElement allElement, Element element){
        if (!stack.isEmpty()){
            if (stack.getItem() instanceof AllElement){
                Map<Element, Integer> map = allElement.element(stack);
                if (map !=null) {
                    Integer value = map.get(element);
                    if (value!=null) {
                        return value;
                    }
                }
            }
        }
        return  0;
    }
    public static boolean hascurio(LivingEntity entity, Item curio) {

        if (CuriosApi.getCuriosInventory(entity).isPresent()) {
            if (CuriosApi.getCuriosInventory(entity).get().isEquipped(com.moonstone.moonstonemod.init.items.Items.immortal.get())||CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.the_divine_fall_ring.get())) {
                if (curio instanceof nightmare) {
                    return false;
                }
            }
        }
        if (CuriosApi.getCuriosInventory(entity).isPresent()
                && !CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.nightmare_base.get())) {
            if (curio instanceof SuperNightmare) {
                return false;
            }
        }

        if (CuriosApi.getCuriosInventory(entity).isPresent()
                && !CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.nightmare_base.get())) {
            if (curio instanceof SuperNightmare) {
                return false;
            }
        }
        return CuriosApi.getCuriosInventory(entity).isPresent()
                && CuriosApi.getCuriosInventory(entity).get().isEquipped(curio);
    }

}
