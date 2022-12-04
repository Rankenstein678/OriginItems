package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.util.Constants;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class CraftingItem extends Item {
    public CraftingItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && !world.isClient) {
            Item item;

            switch (OriginUtils.getOriginId(player).toString()) {
                case Constants.ENDERIAN -> item = ModItems.CRAFTING_ITEM_ENDERIAN;
                case Constants.MERLING -> item = ModItems.CRAFTING_ITEM_MERLING;
                case Constants.PHANTOM -> item = ModItems.CRAFTING_ITEM_PHANTOM;
                case Constants.ELYTRIAN -> item = ModItems.CRAFTING_ITEM_ELYTRIAN;
                case Constants.BLAZEBORN -> item = ModItems.CRAFTING_ITEM_BLAZEBORN;
                case Constants.AVIAN -> item = ModItems.CRAFTING_ITEM_AVIAN;
                case Constants.ARACHNID -> item = ModItems.CRAFTING_ITEM_ARACHNID;
                case Constants.SHULK -> item = ModItems.CRAFTING_ITEM_SHULK;
                case Constants.FELINE -> item = ModItems.CRAFTING_ITEM_FELINE;
                default -> item = Items.DIAMOND;

            }
            player.getInventory().removeStack(slot);
            player.getInventory().insertStack(slot, new ItemStack(item, stack.getCount()));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
