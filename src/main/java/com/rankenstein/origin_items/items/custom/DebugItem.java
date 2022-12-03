package com.rankenstein.origin_items.items.custom;

import com.rankenstein.origin_items.util.OriginUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient&&hand==Hand.MAIN_HAND) {
            user.sendMessage(Text.literal(OriginUtils.getOriginId(user).toString()));
        }
        return super.use(world, user, hand);
    }
}
