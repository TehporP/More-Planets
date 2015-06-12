/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.items.ItemMorePlanets;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;

public class ItemBearryEgg extends ItemMorePlanets
{
	public ItemBearryEgg(String name)
	{
		super();
		this.setMaxStackSize(16);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isRemote)
		{
			return true;
		}
		if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
		{
			return false;
		}
		if (player.capabilities.isCreativeMode)
		{
			if (state.getBlock() == Blocks.mob_spawner)
			{
				TileEntity localTileEntity = world.getTileEntity(pos);

				if (localTileEntity instanceof TileEntityMobSpawner)
				{
					MobSpawnerBaseLogic mobSpawner = ((TileEntityMobSpawner)localTileEntity).getSpawnerBaseLogic();
					mobSpawner.setEntityName("Bearry");
					localTileEntity.markDirty();
					world.markBlockForUpdate(pos);

					if (!player.capabilities.isCreativeMode)
					{
						itemStack.stackSize -= 1;
					}
					return true;
				}
			}
		}

		pos = pos.offset(side);
		double d = 0.0D;

		if (side == EnumFacing.UP && state instanceof BlockFence)
		{
			d = 0.5D;
		}

		Entity localEntity = spawnCreature(world, pos.getX() + 0.5D, pos.getY() + d, pos.getZ() + 0.5D);

		if (localEntity != null)
		{
			if (localEntity instanceof EntityLivingBase && itemStack.hasDisplayName())
			{
				localEntity.setCustomNameTag(itemStack.getDisplayName());
			}
			if (!player.capabilities.isCreativeMode)
			{
				itemStack.stackSize -= 1;
			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (world.isRemote)
		{
			return itemStack;
		}
		if (moving == null)
		{
			return itemStack;
		}
		if (moving.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			BlockPos pos = moving.getBlockPos();

			if (!world.isBlockModifiable(player, pos))
			{
				return itemStack;
			}
			if (!player.canPlayerEdit(pos, moving.sideHit, itemStack))
			{
				return itemStack;
			}
			if (world.getBlockState(pos).getBlock() instanceof BlockLiquid)
			{
				Entity localEntity = spawnCreature(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);

				if (localEntity != null)
				{
					if (localEntity instanceof EntityLivingBase && itemStack.hasDisplayName())
					{
						((EntityLiving)localEntity).setCustomNameTag(itemStack.getDisplayName());
					}
					if (!player.capabilities.isCreativeMode)
					{
						itemStack.stackSize -= 1;
					}
					player.triggerAchievement(net.minecraft.stats.StatList.objectUseStats[Item.getIdFromItem(this)]);
				}
			}
		}
		return itemStack;
	}

	private static Entity spawnCreature(World world, double x, double y, double z)
	{
		EntityBearry entity = new EntityBearry(world);

		for (int i = 0; i < 1; i++)
		{
			if (entity instanceof EntityLivingBase)
			{
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entity.rotationYawHead = entity.rotationYaw;
				entity.renderYawOffset = entity.rotationYaw;
				entity.func_180482_a(world.getDifficultyForLocation(new BlockPos(entity)), null);
				entity.setGrowingAge(-24000);
				world.spawnEntityInWorld(entity);
				entity.playLivingSound();
			}
		}
		return entity;
	}
}