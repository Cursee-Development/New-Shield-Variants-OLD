package com.cursee.new_shield_variants.core.registry;

import com.cursee.new_shield_variants.Constants;
import com.cursee.new_shield_variants.core.item.DragonHeadShieldItem;
import com.cursee.new_shield_variants.core.item.FireChargeShieldItem;
import com.cursee.new_shield_variants.core.item.TNTShieldItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class NewShieldVariantsRegistryFabric {

    public static void register() {
        // everything static in here should get registered as this file is accessed, before addShields is called. I think. Hopefully?
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(NewShieldVariantsRegistryFabric::addShields);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), item);
    }

    private static CreativeModeTab registerTab(String identifier, CreativeModeTab creativeModeTab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, "new_shield_variants_tab"), creativeModeTab);
    }

    public static final Item STONE_SHIELD = registerItem("stone_shield",  new ShieldItem(new Item.Properties().durability(500)));
    public static final Item IRON_SHIELD = registerItem("iron_shield",  new ShieldItem(new Item.Properties().durability(1000)));
    public static final Item GOLD_SHIELD = registerItem("gold_shield",  new ShieldItem(new Item.Properties().durability(750)));
    public static final Item DIAMOND_SHIELD = registerItem("diamond_shield",  new ShieldItem(new Item.Properties().durability(1750)));
    public static final Item NETHERITE_SHIELD = registerItem("netherite_shield",  new ShieldItem(new Item.Properties().durability(2000)));

    public static final Item ENDER_SHIELD = registerItem("ender_shield",  new ShieldItem(new Item.Properties().durability(2250)));
    public static final Item BLAZE_SHIELD = registerItem("blaze_shield",  new ShieldItem(new Item.Properties().durability(2250)));
    public static final Item FIRE_CHARGE_SHIELD = registerItem("fire_charge_shield",  new FireChargeShieldItem(new Item.Properties().durability(2250)));
    public static final Item SHULKER_SHIELD = registerItem("shulker_shield",  new ShieldItem(new Item.Properties().durability(2250)));
    public static final Item DRAGON_HEAD_SHIELD = registerItem("dragon_head_shield",  new DragonHeadShieldItem(new Item.Properties().durability(2250)));
    public static final Item TNT_SHIELD = registerItem("tnt_shield",  new TNTShieldItem(new Item.Properties().durability(2250)));
    public static final Item REDSTONE_SHIELD = registerItem("redstone_shield",  new ShieldItem(new Item.Properties().durability(2250)));


    public static void addShields(CreativeModeTab.Output output) {
        output.accept(STONE_SHIELD);
        output.accept(IRON_SHIELD);
        output.accept(GOLD_SHIELD);
        output.accept(DIAMOND_SHIELD);
        output.accept(NETHERITE_SHIELD);

        output.accept(ENDER_SHIELD);
        output.accept(BLAZE_SHIELD);
        output.accept(FIRE_CHARGE_SHIELD);

        output.accept(SHULKER_SHIELD);
        output.accept(DRAGON_HEAD_SHIELD);
        output.accept(TNT_SHIELD);
    }

    public static final CreativeModeTab NEW_SHIELD_VARIANTS_TAB = registerTab("new_shield_variants_tab", FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.newShieldVariants"))
            .icon(() -> new ItemStack(DRAGON_HEAD_SHIELD))
            .displayItems(((itemDisplayParameters, output) -> {
                addShields(output);
            })).build()
    );

}
