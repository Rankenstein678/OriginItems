package com.rankenstein.origin_items.effects;

import com.rankenstein.origin_items.util.Constants;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static StatusEffect ENDER_REFLEXES = registerEffect("ender_reflexes", new EnderReflexesEffect());
    public static StatusEffect AIR_WALKING = registerEffect("air_walking", new AirWalkingEffect());
    private static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Constants.MOD_ID, name), effect);
    }
    public static void registerEffects() {

    }
}
