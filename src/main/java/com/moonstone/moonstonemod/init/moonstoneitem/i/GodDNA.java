package com.moonstone.moonstonemod.init.moonstoneitem.i;

import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class GodDNA  extends TheNecoraIC {
  public  abstract   Item getNotEquippedItem();
}
