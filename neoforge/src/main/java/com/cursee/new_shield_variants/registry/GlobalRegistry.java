package com.cursee.new_shield_variants.registry;

import com.cursee.new_shield_variants.Constants;
import com.cursee.new_shield_variants.item.custom.DragonHeadShieldItem;
import com.cursee.new_shield_variants.item.custom.FireChargeShieldItem;
import com.cursee.new_shield_variants.item.custom.TNTShieldItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GlobalRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
		CREATIVE_MODE_TABS.register(eventBus);
	}
	
	
	public static final DeferredHolder<Item, Item> STONE_SHIELD = ITEMS.register("stone_shield", () -> new ShieldItem(new Item.Properties().durability(500)));
	public static final DeferredHolder<Item, Item> IRON_SHIELD = ITEMS.register("iron_shield", () -> new ShieldItem(new Item.Properties().durability(1000)));
	public static final DeferredHolder<Item, Item> GOLD_SHIELD = ITEMS.register("gold_shield", () -> new ShieldItem(new Item.Properties().durability(750)));
	public static final DeferredHolder<Item, Item> DIAMOND_SHIELD = ITEMS.register("diamond_shield", () -> new ShieldItem(new Item.Properties().durability(1750)));
	public static final DeferredHolder<Item, Item> NETHERITE_SHIELD = ITEMS.register("netherite_shield", () -> new ShieldItem(new Item.Properties().durability(2000)));
	
	public static final DeferredHolder<Item, Item> ENDER_SHIELD = ITEMS.register("ender_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
	public static final DeferredHolder<Item, Item> BLAZE_SHIELD = ITEMS.register("blaze_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
	public static final DeferredHolder<Item, Item> FIRE_CHARGE_SHIELD = ITEMS.register("fire_charge_shield", () -> new FireChargeShieldItem(new Item.Properties().durability(2250)));
	
	public static final DeferredHolder<Item, Item> SHULKER_SHIELD = ITEMS.register("shulker_shield",
		() -> new ShieldItem(new Item.Properties().durability(2250)));
	public static final DeferredHolder<Item, Item> DRAGON_HEAD_SHIELD = ITEMS.register("dragon_head_shield", () -> new DragonHeadShieldItem(new Item.Properties().durability(2250)));
	public static final DeferredHolder<Item, Item> TNT_SHIELD = ITEMS.register("tnt_shield", () -> new TNTShieldItem(new Item.Properties().durability(2250)));
	
	public static final DeferredHolder<Item, Item> REDSTONE_SHIELD = ITEMS.register("redstone_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
	
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NEWSHIELDVARIANTS_TAB = CREATIVE_MODE_TABS.register("newshieldvariants_tab",
		() -> CreativeModeTab.builder().icon(() -> new ItemStack(STONE_SHIELD.get()))
			.title(Component.translatable("itemGroup.newShieldVariants"))
			.displayItems((displayParameters, output) -> {
				output.accept(STONE_SHIELD.get());
				output.accept(IRON_SHIELD.get());
				output.accept(GOLD_SHIELD.get());
				output.accept(DIAMOND_SHIELD.get());
				output.accept(NETHERITE_SHIELD.get());
				
				output.accept(ENDER_SHIELD.get());
				output.accept(BLAZE_SHIELD.get());
				output.accept(FIRE_CHARGE_SHIELD.get());
				
				output.accept(SHULKER_SHIELD.get());
				output.accept(DRAGON_HEAD_SHIELD.get());
				output.accept(TNT_SHIELD.get());

//                output.accept(NSVItems.REDSTONE_SHIELD.get());
			
			}).build());
	
}
