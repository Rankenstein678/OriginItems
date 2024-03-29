package com.rankenstein.origin_items.items;

import com.rankenstein.origin_items.OriginItems;
import com.rankenstein.origin_items.items.custom.*;
import com.rankenstein.origin_items.util.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {


    public static final Item AIR_SIGILL = registerItem("air_sigill",
            new AirSigillItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(OriginItems.CONFIG.airSigillUseTimeSeconds()*20)));
    public static final Item ARROW_BREAKER = registerItem("arrow_breaker",
            new ArrowBreakerItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(6)));
    public static final Item BLAZING_MOLOTOV_ITEM = registerItem("blazing_molotov",
            new BlazingMolotovItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM = registerItem("crafting_item",
            new CraftingItemEmpty(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ARACHNID = registerItem("crafting_item_arachnid",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_AVIAN = registerItem("crafting_item_avian",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_BLAZEBORN = registerItem("crafting_item_blazeborn",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ELYTRIAN = registerItem("crafting_item_elytrian",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_ENDERIAN = registerItem("crafting_item_enderian",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_FELINE = registerItem("crafting_item_feline",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_MERLING = registerItem("crafting_item_merling",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_PHANTOM = registerItem("crafting_item_phantom",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item CRAFTING_ITEM_SHULK = registerItem("crafting_item_shulk",
            new CraftingItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS)));
    public static final Item EARS = registerItem("ears",
            new EarItem(new EarArmorMaterial(), EquipmentSlot.HEAD, new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(OriginItems.CONFIG.earsDurability())));
    public static final Item SHULKER_CANNON = registerItem("shulker_cannon",
            new ShulkerCannonItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1)));
    public static final Item SPIDER_FANGS = registerItem("spider_fangs",
            new SpiderFangItem(OriginItems.CONFIG.spiderFangsDamage()-1, OriginItems.CONFIG.spiderFangsAttackSpeed()-4F, new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1)));
    public static final Item WING_PIERCER = registerItem("wing_piercer",
            new WingPiercerItem(new FabricItemSettings().group(ModItemGroup.ORIGIN_ITEMS).maxCount(1).maxDamage(465)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }

    public static void registerItems() {
        Constants.LOGGER.debug("Registering Items");
    }
}
