package com.rankenstein.origin_items.datagen;

import com.rankenstein.origin_items.blocks.ModBlocks;
import com.rankenstein.origin_items.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ModelGen::new);
    }

    private static class ModelGen extends FabricModelProvider {
        public ModelGen(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HARD_AIR);
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRAFTING_ORE);
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_CRAFTING_ORE);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ModItems.BLAZING_MOLOTOV_ITEM, Models.GENERATED);
            itemModelGenerator.register(ModItems.AIR_SIGILL, Models.GENERATED);
            itemModelGenerator.register(ModItems.EARS, Models.GENERATED);
            itemModelGenerator.register(ModItems.SHULKER_CANNON, Models.GENERATED);
            itemModelGenerator.register(ModItems.ARROW_BREAKER, Models.GENERATED);
            itemModelGenerator.register(ModItems.SPIDER_FANGS, Models.GENERATED);
        }
    }
}