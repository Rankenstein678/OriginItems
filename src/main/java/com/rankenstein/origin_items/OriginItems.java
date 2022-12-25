package com.rankenstein.origin_items;

import com.rankenstein.origin_items.blocks.ModBlocks;
import com.rankenstein.origin_items.effects.ModEffects;
import com.rankenstein.origin_items.items.ModItems;
import com.rankenstein.origin_items.worldgen.ModConfiguredFeatures;
import com.rankenstein.origin_items.worldgen.ModOreGeneration;
import net.fabricmc.api.ModInitializer;

public class OriginItems implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConfiguredFeatures.registerConfiguredFeatures();
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModEffects.registerEffects();
        ModOreGeneration.generateOres();
    }
}
