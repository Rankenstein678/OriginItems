package com.rankenstein.origin_items.util;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

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
            String name = origin.split(":")[1].substring(0, 1).toUpperCase() + origin.split(":")[1].substring(1);
            List<Character> vocals = Arrays.asList('A', 'E', 'I', 'O', 'U');
            if (vocals.contains(name.toCharArray()[0])) {
                name = "an " + name;
            } else name = "a " + name;
            user.sendMessage(Text.translatable("origin_items.wrong_origin", name), true);
            return false;
        }
        return true;
    }
}
