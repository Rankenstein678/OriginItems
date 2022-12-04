package com.rankenstein.origin_items.util;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class OriginUtils {
    public static Identifier getOriginId(PlayerEntity player) {
        return Origin.get(player).get(OriginLayers.
                getLayer(new Identifier("origins", "origin"))).getIdentifier();
    }

    public static boolean isOfOrigin(PlayerEntity player, String origin) {
        return Origin.get(player).get(OriginLayers.getLayer(Constants.ORIGIN_LAYER))
                .getIdentifier().toString().equals(origin);
    }
}
