package com.cursee.new_shield_variants.event;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVBlazeShieldBlockEvent {
    @SubscribeEvent
    public static void playerTickEvent(ServerTickEvent.Pre event) {
        // iterate over players
        // check if blocking with blaze shield
        // set nearby entities on fire
        if (true) {
            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {


                if (
                    player.isBlocking() &&
                        (
                            (player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().getString().contains("Blaze")) ||
                                (player.getItemInHand(InteractionHand.OFF_HAND).getDisplayName().getString().contains("Blaze")
                                )
                        )
                ) {
                    List<LivingEntity> nearby = event.getServer().getLevel(player.level().dimension())
                        .getNearbyEntities(
                            LivingEntity.class,
                            TargetingConditions.forNonCombat(),
                            player,
                            player.getBoundingBox().inflate(3));
                    for (LivingEntity entity : nearby) {
                        entity.setRemainingFireTicks(4*20);
                    }
                }


            }
        }
    }
}
