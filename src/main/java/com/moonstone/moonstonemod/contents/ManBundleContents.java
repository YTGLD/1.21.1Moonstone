package com.moonstone.moonstonemod.contents;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManBundleContents {
    public static final ManBundleContents EMPTY = new ManBundleContents(List.of());
    public static final Codec<ManBundleContents> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, ManBundleContents> STREAM_CODEC;
    private static final Fraction BUNDLE_IN_BUNDLE_WEIGHT;
    private static final int NO_STACK_INDEX = -1;
    final List<ItemStack> items;
    final Fraction weight;

    ManBundleContents(List<ItemStack> items, Fraction weight) {
        this.items = items;
        this.weight = weight;
    }

    public ManBundleContents(List<ItemStack> items) {
        this(items, computeContentWeight(items));
    }

    private static Fraction computeContentWeight(List<ItemStack> content) {
        Fraction fraction = Fraction.ZERO;

        ItemStack itemstack;
        for(Iterator<ItemStack> var2 = content.iterator(); var2.hasNext(); fraction = fraction.add(getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)))) {
            itemstack = var2.next();
        }

        return fraction;
    }

    static Fraction getWeight(ItemStack stack) {
        ManBundleContents bundlecontents = stack.get(DataReg.man);
        if (bundlecontents != null) {
            return BUNDLE_IN_BUNDLE_WEIGHT.add(bundlecontents.weight());
        } else {
            List<?> list = stack.getOrDefault(DataComponents.BEES, List.of());
            return !list.isEmpty() ? Fraction.ONE : Fraction.getFraction(1,2);
        }
    }

    public Iterable<ItemStack> items() {
        return this.items;
    }

    public Iterable<ItemStack> itemsCopy() {
        return Lists.transform(this.items, ItemStack::copy);
    }

    public int size() {
        return this.items.size();
    }

    public Fraction weight() {
        return this.weight;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            boolean var10000;
            if (other instanceof ManBundleContents) {
                ManBundleContents bundlecontents = (ManBundleContents)other;
                var10000 = this.weight.equals(bundlecontents.weight) && ItemStack.listMatches(this.items, bundlecontents.items);
            } else {
                var10000 = false;
            }

            return var10000;
        }
    }

    public int hashCode() {
        return ItemStack.hashStackList(this.items);
    }

    public String toString() {
        return "ManBundleContents" + String.valueOf(this.items);
    }

    static {
        CODEC = ItemStack.CODEC.listOf().xmap(ManBundleContents::new, (p_331551_) -> {
            return p_331551_.items;
        });
        STREAM_CODEC = ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()).map(ManBundleContents::new, (p_331649_) -> {
            return p_331649_.items;
        });
        BUNDLE_IN_BUNDLE_WEIGHT = Fraction.getFraction(1, 2);
    }

    public static class Mutable {
        private final List<ItemStack> items;
        private Fraction weight;

        public Mutable(ManBundleContents contents) {
            this.items = new ArrayList(contents.items);
            this.weight = contents.weight;
        }

        public ManBundleContents.Mutable clearItems() {
            this.items.clear();
            this.weight = Fraction.ZERO;
            return this;
        }

        private int findStackIndex(ItemStack stack) {
            if (!stack.isStackable()) {
                return -1;
            } else {
                for(int i = 0; i < this.items.size(); ++i) {
                    if (ItemStack.isSameItemSameComponents((ItemStack)this.items.get(i), stack)) {
                        return i;
                    }
                }

                return -1;
            }
        }

        private int getMaxAmountToAdd(ItemStack stack) {
            Fraction fraction = Fraction.ONE.subtract(this.weight);
            return Math.min(Math.max(fraction.divideBy(ManBundleContents.getWeight(stack)).intValue(), 0), 64);
        }

        public int tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {

                // 计算能添加的数量
                int i = Math.min(stack.getCount(), this.getMaxAmountToAdd(stack));

                // 检查当前总数是否超过2
                int totalItems = this.items.stream().mapToInt(ItemStack::getCount).sum();
                // 限制最多添加的数量为64，基于2 - totalItems
                int availableSpace = 2 - totalItems;
                int maxAddable = Math.min(availableSpace, 64);

                // 最终可添加数量
                i = Math.min(i, maxAddable);

                if (i == 0) {
                    return 0;
                } else {
                    this.weight = this.weight.add(ManBundleContents.getWeight(stack).multiplyBy(Fraction.getFraction(i, 1)));
                    int j = this.findStackIndex(stack);

                    // 如果找到了已有物品
                    if (j != -1) {
                        ItemStack itemstack = this.items.remove(j);
                        // 检查添加后的数量是否超过64
                        int newCount = itemstack.getCount() + i;
                        // 如果超过64就限制为64
                        if (newCount > 64) {
                            newCount = 64;
                            i = 64 - itemstack.getCount(); // 更新可添加的数量
                        }

                        ItemStack itemstack1 = itemstack.copyWithCount(newCount);
                        stack.shrink(i);
                        this.items.addFirst(itemstack1);
                    } else {
                        // 如果没有找到，直接添加新的物品
                        this.items.addFirst(stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        @Nullable
        public ItemStack removeOne() {
            if (this.items.isEmpty()) {
                return null;
            } else {
                ItemStack itemstack = this.items.removeFirst().copy();
                this.weight = this.weight.subtract(ManBundleContents.getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
                return itemstack;
            }
        }

        public Fraction weight() {
            return this.weight;
        }

        public ManBundleContents toImmutable() {
            return new ManBundleContents(List.copyOf(this.items), this.weight);
        }
    }
}
