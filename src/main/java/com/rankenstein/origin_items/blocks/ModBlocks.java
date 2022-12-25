package com.rankenstein.origin_items.blocks;

import com.rankenstein.origin_items.blocks.custom.HardAirBlock;
import com.rankenstein.origin_items.items.ModItemGroup;
import com.rankenstein.origin_items.util.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static Block HARD_AIR = registerBlock("hard_air",
            new HardAirBlock(FabricBlockSettings.of(Material.WOOL).nonOpaque().hardness(0.2f).ticksRandomly()), ModItemGroup.ORIGIN_ITEMS);
    public static Block CRAFTING_ORE = registerBlock("crafting_ore", new OreBlock(AbstractBlock.Settings.of(Material.STONE)
            .requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7)), ModItemGroup.ORIGIN_ITEMS);
    public static Block DEEPSLATE_CRAFTING_ORE = registerBlock("deepslate_crafting_ore", new OreBlock(AbstractBlock.Settings.of(Material.STONE)
            .requiresTool().strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(3, 7)), ModItemGroup.ORIGIN_ITEMS);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Constants.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerBlocks() {

    }
}
