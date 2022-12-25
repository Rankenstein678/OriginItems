package com.rankenstein.origin_items.worldgen;

import com.rankenstein.origin_items.blocks.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreFeatureConfig.Target> OVERWORLD_CRAFTING_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CRAFTING_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_CRAFTING_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> CRAFTING_ORE =
            ConfiguredFeatures.register("crafting_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_CRAFTING_ORES, 4));

    public static void registerConfiguredFeatures() {
    }
}