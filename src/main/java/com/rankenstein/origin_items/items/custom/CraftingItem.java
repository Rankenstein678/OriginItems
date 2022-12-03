package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CraftingItem extends Item {
    public CraftingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient && hand == Hand.MAIN_HAND) {
            ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);
            if (stack.getNbt() != null) {
                String originId = stack.getNbt().getString("origin_items.rankenstein.data");
                user.sendMessage(Text.literal(originId));
            }
        }
        //TOdo: https://fabricmc.net/wiki/tutorial:model_predicate_providers
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && !world.isClient) {
            Item item;

            switch (OriginUtils.getOriginId(player).toString()) {
                case "origins:enderian" -> item = ModItems.CRAFTING_ITEM_ENDERIAN;
                case "origins:merling" -> item = ModItems.CRAFTING_ITEM_MERLING;
                case "origins:phantom" -> item = ModItems.CRAFTING_ITEM_PHANTOM;
                case "origins:elytrian" -> item = ModItems.CRAFTING_ITEM_ELYTRIAN;
                case "origins:blazeborn" -> item = ModItems.CRAFTING_ITEM_BLAZEBORN;
                case "origins:avian" -> item = ModItems.CRAFTING_ITEM_AVIAN;
                case "origins:arachnid" -> item = ModItems.CRAFTING_ITEM_ARACHNID;
                case "origins:shulk" -> item = ModItems.CRAFTING_ITEM_SHULK;
                case "origins:feline" -> item = ModItems.CRAFTING_ITEM_FELINE;
                default -> item = Items.DIAMOND;

            }
            player.getInventory().removeStack(slot);
            player.getInventory().insertStack(slot, new ItemStack(item, stack.getCount()));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
