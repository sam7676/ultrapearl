package com.sam.ultrapearl.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;

public class CustomPearlEntity extends EnderPearlEntity {
    public CustomPearlEntity(World world, PlayerEntity player) {
        super(world, player);
    }


    @Override
    protected void onImpact(RayTraceResult result) {

        Entity entity = this.getShooter();

        // Adding particles
        for (int i = 0; i < 32; ++i) {
            this.world.addParticle(ParticleTypes.PORTAL, this.getPosX(), this.getPosY() + this.rand.nextDouble() * 2.0, this.getPosZ(), this.rand.nextGaussian(), 0.0, this.rand.nextGaussian());
        }


        if (!this.world.isRemote && !this.removed) {
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity;
                if (serverplayerentity.connection.getNetworkManager().isChannelOpen() && serverplayerentity.world == this.world && !serverplayerentity.isSleeping()) {
                    EntityTeleportEvent.EnderPearl event = ForgeEventFactory.onEnderPearlLand(serverplayerentity, this.getPosX(), this.getPosY(), this.getPosZ(), this, 0.0F);
                    if (!event.isCanceled()) {
                        entity.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                        entity.fallDistance = 0.0F;
                        entity.attackEntityFrom(DamageSource.FALL, event.getAttackDamage());
                    }
                }
            } else if (entity != null) {
                entity.setPositionAndUpdate(this.getPosX(), this.getPosY(), this.getPosZ());
                entity.fallDistance = 0.0F;
            }

            this.remove();
        }
    }
}
