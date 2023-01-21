package com.rankenstein.origin_items.config;

import com.rankenstein.origin_items.util.Constants;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;
import io.wispforest.owo.config.annotation.Sync;

@SuppressWarnings("unused")
@Modmenu(modId = Constants.MOD_ID)
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = "origin_items_config", wrapperName = "Config")
public class ConfigModel {
    @SectionHeader("airSigill")
    public int airSigillUseTimeSeconds = 60;
    @SectionHeader("arrowBreaker")
    public int enderReflexesDurationSeconds = 30;
    @SectionHeader("blazingMolotov")
    public int blazingMolotovRadius = 5;
    public int blazingMolotovExplosionRadius = 2;
    @SectionHeader("ears")
    public double earsRange = 3;
    public int earsDurability = 25;
    public int earsNauseaDurationSeconds = 5;
    @SectionHeader("shulkerCannon")
    public double shulkerCannonRange = 20;
    public double shulkerCannonCooldownHitSeconds = 2;
    public double shulkerCannonCooldownMissSeconds = .5;
    @SectionHeader("spiderFangs")
    public int spiderFangsDamage = 5;
    public float spiderFangsAttackSpeed = 1.2F;
    public int spiderFangsDurability = 5;
    public double spiderFangsPoisonDuration = 10;
    public int spiderFangsDashDamage = 5;
    @SectionHeader("wingPiercer")
    public double wingPiercerPullTimeSeconds = 0.75;
    public double wingPiercerPlayerSpeedDamageMultiplier = 5;

}
