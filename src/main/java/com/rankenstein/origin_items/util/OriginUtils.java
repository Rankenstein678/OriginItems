package com.rankenstein.origin_items.util;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class OriginUtils {
    public static Identifier getOriginId(PlayerEntity player) {
        return Origin.get(player).get(OriginLayers.
                getLayer(new Identifier("origins", "origin"))).getIdentifier();
    }

    public static boolean isOfOrigin(PlayerEntity player, String origin) {
        return Origin.get(player).get(OriginLayers.getLayer(Constants.ORIGIN_LAYER))
                .getIdentifier().toString().equals(origin);
    }

    public static boolean checkOrigin(String origin, World world, PlayerEntity user) {
        if (!OriginUtils.isOfOrigin(user, origin)) {
            if (world.isClient()) {
                user.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            user.sendMessage(Text.translatable("origin_items.wrong_origin"), true);
            return false;
        }
        return true;
    }
}
