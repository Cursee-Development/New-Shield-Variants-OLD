package com.cursee.new_shield_variants.item;

import com.cursee.new_shield_variants.registry.GlobalRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {

    public static void registerProperties(Item item){
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, p_174578_) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    public static void addCustomItemProperties() {
        registerProperties(GlobalRegistry.STONE_SHIELD.get());
        registerProperties(GlobalRegistry.IRON_SHIELD.get());
        registerProperties(GlobalRegistry.GOLD_SHIELD.get());
        registerProperties(GlobalRegistry.DIAMOND_SHIELD.get());
        registerProperties(GlobalRegistry.NETHERITE_SHIELD.get());

        registerProperties(GlobalRegistry.ENDER_SHIELD.get());
        registerProperties(GlobalRegistry.BLAZE_SHIELD.get());
        registerProperties(GlobalRegistry.FIRE_CHARGE_SHIELD.get());

        registerProperties(GlobalRegistry.SHULKER_SHIELD.get());
        registerProperties(GlobalRegistry.DRAGON_HEAD_SHIELD.get());
        registerProperties(GlobalRegistry.TNT_SHIELD.get());

        registerProperties(GlobalRegistry.REDSTONE_SHIELD.get());

    }


}
