package com.cursee.new_shield_variants;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.new_shield_variants.core.registry.NewShieldVariantsRegistryFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.List;
import java.util.Random;

public class NewShieldVariantsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        NewShieldVariants.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        NewShieldVariantsRegistryFabric.register();

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
    }

    public class PlayerTickHandler implements ServerTickEvents.StartTick {
        @Override
        public void onStartTick(MinecraftServer server) {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                // BLAZE SHIELD
                if (
                        player.isBlocking() &&
                                (
                                        (player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().getString().contains("Blaze")) ||
                                                (player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().getString().contains("Blaze")
                                                )
                                )
                ) {
                    List<LivingEntity> nearby = server.getLevel(player.level().dimension())
                            .getNearbyEntities(
                                    LivingEntity.class,
                                    TargetingConditions.forNonCombat(),
                                    player,
                                    player.getBoundingBox().inflate(3));

                    if (!nearby.isEmpty()) {
                        for (LivingEntity entity : nearby) {
                            entity.setRemainingFireTicks(4*20);
                        }
                    }
                }

                // ENDER SHIELD
                if ((
                        player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Ender Shield"))
                                ||
                                player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Ender Shield")) )
                ) {

                    Entity attacker = (Entity) player.getLastAttacker();

                    if (attacker != null) {
                        attacker.teleportTo(attacker.xOld + (new Random().nextDouble() * 50 - 20), attacker.getRandomY(), attacker.zOld + (new Random().nextDouble() * 50 - 20));

                        if (player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Ender Shield"))) {
                            player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
                        }
                        if (player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Ender Shield"))) {
                            player.getOffhandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
                        }
                    }

                }

                // SHULKER SHIELD
                if (
                        ( player.isBlocking() &&
                                player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Shulker Shield")))
                ) {
                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
                    player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() + 20);
                }
                if (
                        ( player.isBlocking() &&
                                player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Shulker Shield")) )
                ) {
                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (new Random().nextDouble() * 40)));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (new Random().nextDouble() * 160)));
                    player.getItemInHand(InteractionHand.OFF_HAND).setDamageValue(player.getItemInHand(InteractionHand.OFF_HAND).getDamageValue() + 20);


                }
            }
        }
    }
}
