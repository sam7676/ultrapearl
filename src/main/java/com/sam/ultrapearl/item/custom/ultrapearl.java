package com.sam.ultrapearl.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;


public class ultrapearl extends Item {

    public ultrapearl(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getHeldItem(hand);

        // Throw ender pearl and play sound
        world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        // Generate pearl
        if (!world.isRemote) {
            CustomPearlEntity pearl = new CustomPearlEntity(world, player);
            pearl.setItem(stack);
            // #1 Vertical angle the pearl is thrown at (positive is downwards)
            // #2 Default 1.5F. 0F: drops pearl on ground, no acceleration. 100F: pearl accelerates, but bound to only a few directions.
            // #3 Controls the randomness movement of the pearl relative to the cursor
            pearl.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(pearl);
        }

        player.addStat(Stats.ITEM_USED.get(this));

        return super.onItemRightClick(world, player, hand);
    }


    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
