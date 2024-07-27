package com.cursee.new_shield_variants.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Random;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVShulkerShieldBlockEvent {

    @SubscribeEvent
    public static void shieldBlockEvent(PlayerTickEvent event) {
//        NewShieldVariants.LOGGER.info("\n event.getEntity getDisplayName: \n" + event.getEntity().getDisplayName().toString()); // contains Dev
//        NewShieldVariants.LOGGER.info("\n event.getDamageSource getEntity getDisplayName: \n" + event.getDamageSource().getEntity().getDisplayName().toString());

        Player player =  event.getEntity();

        if (
            ( player.isBlocking() &&
                player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Shulker Shield")))
        ) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
            player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() - 20);
        }
        if (
            ( player.isBlocking() &&
                player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Shulker Shield")) )
        ) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
            player.getItemInHand(InteractionHand.OFF_HAND).setDamageValue(player.getItemInHand(InteractionHand.OFF_HAND).getDamageValue() - 20);


        }

    }
}
