package com.cursee.new_shield_variants;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.new_shield_variants.core.registry.NewShieldVariantsRegistryFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

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

    public static boolean randomTeleport(LivingEntity $$2) {

        Level $$1 = $$2.level();

//        for(int $$4 = 0; $$4 < 16; ++$$4) {
        if (true) {
            double $$5 = $$2.getX() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
            double $$6 = Mth.clamp($$2.getY() + (double)($$2.getRandom().nextInt(16) - 8), (double)$$1.getMinBuildHeight(), (double)($$1.getMinBuildHeight() + ((ServerLevel)$$1).getLogicalHeight() - 1));
            double $$7 = $$2.getZ() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
            if ($$2.isPassenger()) {
                $$2.stopRiding();
            }

            Vec3 $$8 = $$2.position();
            if ($$2.randomTeleport($$5, $$6, $$7, true)) {

                $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of($$2));
                SoundSource $$12 = SoundSource.NEUTRAL;
                SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

                $$1.playSound((Player)null, $$2.getX(), $$2.getY(), $$2.getZ(), $$11, $$12);
                $$2.resetFallDistance();
                return true;
            } else {

                $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of($$2));
                SoundSource $$12 = SoundSource.NEUTRAL;
                SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

                $$1.playSound((Player)null, $$2.getX(), $$2.getY(), $$2.getZ(), $$11, $$12);
                $$2.resetFallDistance();
                return false;
            }
        }

        return false;
    }

    public static void teleportAwayFrom(LivingEntity defender, LivingEntity attacker) {

        Vec3 defenderPos = defender.blockPosition().getCenter();
        Vec3 attackerPos = attacker.blockPosition().getCenter();

        Vec3 lerpedPosition = defenderPos.lerp(attackerPos, new Random().nextDouble() * 2);

//        LivingEntity $$2 = attacker;
//        Level $$1 = $$2.level();
//
//        double $$5 = $$2.getX() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
//        double $$6 = Mth.clamp($$2.getY() + (double)($$2.getRandom().nextInt(16) - 8), (double)$$1.getMinBuildHeight(), (double)($$1.getMinBuildHeight() + ((ServerLevel)$$1).getLogicalHeight() - 1));
//        double $$7 = $$2.getZ() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
//
//
//        attacker.teleportTo($$5, $$6, $$7);

//        randomTeleport(attacker);
//        player.level().playSound((Player)null, $$2.getX(), $$2.getY(), $$2.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS);




//        double oldX = attacker.xOld;
//        double oldY = attacker.yOld;
//        double oldZ = attacker.zOld;
//
//        double inRangeModifier = ((new Random().nextDouble() * 2) - 1); // should produce value between -1 and 1 ??
//
//        BlockPos posTest = new BlockPos(oldX, oldY, oldZ);
//        posTest.getCenter().lerp()
//
//        attacker.teleportTo(oldX + (inRangeModifier * 10), oldY + (inRangeModifier * 2), oldZ + (inRangeModifier * 10));
    }

    public class PlayerTickHandler implements ServerTickEvents.StartTick {
        @Override
        public void onStartTick(MinecraftServer server) {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                if (!player.isBlocking()) {
                    return;
                }

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
                if (player.getMainHandItem().getItem() == NewShieldVariantsRegistryFabric.ENDER_SHIELD || player.getOffhandItem().getItem() == NewShieldVariantsRegistryFabric.ENDER_SHIELD) {

                    LivingEntity attacker = (LivingEntity) player.getLastAttacker();

                    List<Monster> monsters = player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(2.0d));

//                    if (!monsters.isEmpty()) {
//                        attacker = monsters.getFirst();
//                    }

                    attacker = monsters.isEmpty() ? attacker : monsters.getFirst();

                    if (attacker != null) {
//                        attacker.teleportTo(attacker.xOld + (new Random().nextDouble() * 8), attacker.getRandomY(), attacker.zOld + (new Random().nextDouble() * 8));
//                        attacker.teleportRelative(new Random().nextDouble()*10,new Random().nextDouble()*2, new Random().nextDouble()*10);

//                        randomTeleport(attacker, 10, 2, 10, false);

//                        teleportAwayFrom(player, attacker);
//                        randomTeleport(attacker);

//                        attacker.eat(server.getLevel(player.level().dimension()), new ItemStack(Items.CHORUS_FRUIT));

//                        teleportAwayFrom(player, attacker);

                        double $$0 = attacker.getX() + (new Random().nextDouble() - 0.5) * 16.0;
                        double $$1 = attacker.getY() + (double)(new Random().nextInt(16) - 8);
                        double $$2 = attacker.getZ() + (new Random().nextDouble() - 0.5) * 16.0;

//                        attacker.teleportTo($$0, $$1, $$2);

                        if (randomTeleport(attacker)) {
//                            player.level().playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL);
//                            attacker.level().playSound((Player) null, $$0, $$1, $$2, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL);

                            if (player.getMainHandItem().getItem() == NewShieldVariantsRegistryFabric.ENDER_SHIELD) {
                                player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
                                player.stopUsingItem();
//                                player.getCooldowns().addCooldown(NewShieldVariantsRegistryFabric.ENDER_SHIELD, 20 * 2);
                                player.setLastHurtByMob(null);
                            }
                            if (player.getOffhandItem().getItem() == NewShieldVariantsRegistryFabric.ENDER_SHIELD) {
                                player.getOffhandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
                                player.stopUsingItem();
//                                player.getCooldowns().addCooldown(NewShieldVariantsRegistryFabric.ENDER_SHIELD, 20 * 2);
                                player.setLastHurtByMob(null);
                            }
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
