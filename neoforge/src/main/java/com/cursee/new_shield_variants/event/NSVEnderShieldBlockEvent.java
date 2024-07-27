package com.cursee.new_shield_variants.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.ShieldBlockEvent;

import java.util.Random;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVEnderShieldBlockEvent {

    @SubscribeEvent
    public static void shieldBlockEvent(ShieldBlockEvent event) {
//        NewShieldVariants.LOGGER.info("\n event.getEntity getDisplayName: \n" + event.getEntity().getDisplayName().toString()); // contains Dev
//        NewShieldVariants.LOGGER.info("\n event.getDamageSource getEntity getDisplayName: \n" + event.getDamageSource().getEntity().getDisplayName().toString());

        if (
            event.getEntity() instanceof Player &&
            (
                event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Ender Shield"))
                ||
                event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Ender Shield")) )
        ) {
            Entity attacker = event.getDamageSource().getEntity();

            attacker.teleportTo(attacker.xOld + (new Random().nextDouble() * 50 - 20), attacker.getRandomY(), attacker.zOld + (new Random().nextDouble() * 50 - 20));
            event.shieldTakesDamage();
        }

    }
}
