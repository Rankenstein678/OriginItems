package com.rankenstein.origin_items.config;

import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Config extends ConfigWrapper<com.rankenstein.origin_items.config.ConfigModel> {

    private final Option<java.lang.Integer> airSigillUseTimeSeconds = this.optionForKey(new Option.Key("airSigillUseTimeSeconds"));
    private final Option<java.lang.Integer> enderReflexesDurationSeconds = this.optionForKey(new Option.Key("enderReflexesDurationSeconds"));
    private final Option<java.lang.Integer> blazingMolotovRadius = this.optionForKey(new Option.Key("blazingMolotovRadius"));
    private final Option<java.lang.Double> earsRange = this.optionForKey(new Option.Key("earsRange"));
    private final Option<java.lang.Integer> earsDurability = this.optionForKey(new Option.Key("earsDurability"));
    private final Option<java.lang.Integer> earsNauseaDurationSeconds = this.optionForKey(new Option.Key("earsNauseaDurationSeconds"));
    private final Option<java.lang.Double> shulkerCannonRange = this.optionForKey(new Option.Key("shulkerCannonRange"));
    private final Option<java.lang.Double> shulkerCannonCooldownHitSeconds = this.optionForKey(new Option.Key("shulkerCannonCooldownHitSeconds"));
    private final Option<java.lang.Double> shulkerCannonCooldownMissSeconds = this.optionForKey(new Option.Key("shulkerCannonCooldownMissSeconds"));
    private final Option<java.lang.Integer> spiderFangsDamage = this.optionForKey(new Option.Key("spiderFangsDamage"));
    private final Option<java.lang.Float> spiderFangsAttackSpeed = this.optionForKey(new Option.Key("spiderFangsAttackSpeed"));
    private final Option<java.lang.Integer> spiderFangsDurability = this.optionForKey(new Option.Key("spiderFangsDurability"));
    private final Option<java.lang.Double> spiderFangsPoisonDuration = this.optionForKey(new Option.Key("spiderFangsPoisonDuration"));
    private final Option<java.lang.Integer> spiderFangsDashDamage = this.optionForKey(new Option.Key("spiderFangsDashDamage"));
    private final Option<java.lang.Double> wingPiercerPullTimeSeconds = this.optionForKey(new Option.Key("wingPiercerPullTimeSeconds"));
    private final Option<java.lang.Double> wingPiercerPlayerSpeedDamageMultiplier = this.optionForKey(new Option.Key("wingPiercerPlayerSpeedDamageMultiplier"));

    private Config() {
        super(com.rankenstein.origin_items.config.ConfigModel.class);
    }

    public static Config createAndLoad() {
        var wrapper = new Config();
        wrapper.load();
        return wrapper;
    }

    public int airSigillUseTimeSeconds() {
        return airSigillUseTimeSeconds.value();
    }

    public void airSigillUseTimeSeconds(int value) {
        airSigillUseTimeSeconds.set(value);
    }

    public int enderReflexesDurationSeconds() {
        return enderReflexesDurationSeconds.value();
    }

    public void enderReflexesDurationSeconds(int value) {
        enderReflexesDurationSeconds.set(value);
    }

    public int blazingMolotovRadius() {
        return blazingMolotovRadius.value();
    }

    public void blazingMolotovRadius(int value) {
        blazingMolotovRadius.set(value);
    }

    public double earsRange() {
        return earsRange.value();
    }

    public void earsRange(double value) {
        earsRange.set(value);
    }

    public int earsDurability() {
        return earsDurability.value();
    }

    public void earsDurability(int value) {
        earsDurability.set(value);
    }

    public int earsNauseaDurationSeconds() {
        return earsNauseaDurationSeconds.value();
    }

    public void earsNauseaDurationSeconds(int value) {
        earsNauseaDurationSeconds.set(value);
    }

    public double shulkerCannonRange() {
        return shulkerCannonRange.value();
    }

    public void shulkerCannonRange(double value) {
        shulkerCannonRange.set(value);
    }

    public double shulkerCannonCooldownHitSeconds() {
        return shulkerCannonCooldownHitSeconds.value();
    }

    public void shulkerCannonCooldownHitSeconds(double value) {
        shulkerCannonCooldownHitSeconds.set(value);
    }

    public double shulkerCannonCooldownMissSeconds() {
        return shulkerCannonCooldownMissSeconds.value();
    }

    public void shulkerCannonCooldownMissSeconds(double value) {
        shulkerCannonCooldownMissSeconds.set(value);
    }

    public int spiderFangsDamage() {
        return spiderFangsDamage.value();
    }

    public void spiderFangsDamage(int value) {
        spiderFangsDamage.set(value);
    }

    public float spiderFangsAttackSpeed() {
        return spiderFangsAttackSpeed.value();
    }

    public void spiderFangsAttackSpeed(float value) {
        spiderFangsAttackSpeed.set(value);
    }

    public int spiderFangsDurability() {
        return spiderFangsDurability.value();
    }

    public void spiderFangsDurability(int value) {
        spiderFangsDurability.set(value);
    }

    public double spiderFangsPoisonDuration() {
        return spiderFangsPoisonDuration.value();
    }

    public void spiderFangsPoisonDuration(double value) {
        spiderFangsPoisonDuration.set(value);
    }

    public int spiderFangsDashDamage() {
        return spiderFangsDashDamage.value();
    }

    public void spiderFangsDashDamage(int value) {
        spiderFangsDashDamage.set(value);
    }

    public double wingPiercerPullTimeSeconds() {
        return wingPiercerPullTimeSeconds.value();
    }

    public void wingPiercerPullTimeSeconds(double value) {
        wingPiercerPullTimeSeconds.set(value);
    }

    public double wingPiercerPlayerSpeedDamageMultiplier() {
        return wingPiercerPlayerSpeedDamageMultiplier.value();
    }

    public void wingPiercerPlayerSpeedDamageMultiplier(double value) {
        wingPiercerPlayerSpeedDamageMultiplier.set(value);
    }




}

