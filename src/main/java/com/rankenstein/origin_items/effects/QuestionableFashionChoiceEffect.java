package com.rankenstein.origin_items.effects;

import com.rankenstein.origin_items.OriginItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class QuestionableFashionChoiceEffect extends StatusEffect {
    public QuestionableFashionChoiceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16724639);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        List<Entity> es = entity.getWorld().getOtherEntities(entity, new Box(entity.getX() - OriginItems.CONFIG.earsRange(), entity.getY() - OriginItems.CONFIG.earsRange(), entity.getZ() - OriginItems.CONFIG.earsRange(), entity.getX() + OriginItems.CONFIG.earsRange(), entity.getY() + OriginItems.CONFIG.earsRange(), entity.getZ() + OriginItems.CONFIG.earsRange()), Entity::isPlayer);
        es.stream().map(e -> (PlayerEntity) e)
                .forEach(e -> e.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, OriginItems.CONFIG.earsNauseaDurationSeconds(), 0)));
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
