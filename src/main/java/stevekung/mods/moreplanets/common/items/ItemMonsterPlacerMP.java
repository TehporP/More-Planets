/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPEntities;

public class ItemMonsterPlacerMP extends ItemMorePlanets
{
	public ItemMonsterPlacerMP(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
		{
			return false;
		}
		else
		{
			IBlockState iblockstate = world.getBlockState(pos);

			if (iblockstate.getBlock() == Blocks.mob_spawner)
			{
				TileEntity tileentity = world.getTileEntity(pos);

				if (tileentity instanceof TileEntityMobSpawner)
				{
					MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
					mobspawnerbaselogic.setEntityName(EntityList.getStringFromID(itemStack.getMetadata()));
					tileentity.markDirty();
					world.markBlockForUpdate(pos);

					if (!player.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;
					}
					return true;
				}
			}

			pos = pos.offset(side);
			double d0 = 0.0D;

			if (side == EnumFacing.UP && iblockstate instanceof BlockFence)
			{
				d0 = 0.5D;
			}

			Entity entity = this.spawnEntity(world, itemStack.getItemDamage(), pos.getX() + 0.5D, pos.getY() + d0, pos.getZ() + 0.5D);

			if (entity != null)
			{
				if (entity instanceof EntityLivingBase && itemStack.hasDisplayName())
				{
					entity.setCustomNameTag(itemStack.getDisplayName());
				}
				if (!player.capabilities.isCreativeMode)
				{
					--itemStack.stackSize;
				}
			}
			return true;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			return itemStack;
		}
		else
		{
			MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, true);

			if (moving == null)
			{
				return itemStack;
			}
			else
			{
				if (moving.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					BlockPos blockpos = moving.getBlockPos();

					if (!world.isBlockModifiable(player, blockpos))
					{
						return itemStack;
					}
					if (!player.canPlayerEdit(blockpos, moving.sideHit, itemStack))
					{
						return itemStack;
					}
					if (world.getBlockState(blockpos).getBlock() instanceof BlockLiquid)
					{
						Entity entity = this.spawnEntity(world, itemStack.getItemDamage(), blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D);

						if (entity != null)
						{
							if (entity instanceof EntityLivingBase && itemStack.hasDisplayName())
							{
								((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
							}
							if (!player.capabilities.isCreativeMode)
							{
								--itemStack.stackSize;
							}
						}
					}
				}
				return itemStack;
			}
		}
	}

	public Entity spawnEntity(World world, int meta, double x, double y, double z)
	{
		if (!world.isRemote)
		{
			if (!MPEntities.entityEggs.containsKey(Integer.valueOf(meta)))
			{
				return null;
			}

			Entity entityToSpawn = MPEntities.createEntityByID(meta, world);

			if (entityToSpawn != null && entityToSpawn instanceof EntityLivingBase)
			{
				EntityLiving entityliving = (EntityLiving)entityToSpawn;

				entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
				entityliving.func_180482_a(world.getDifficultyForLocation(new BlockPos(entityToSpawn)), (IEntityLivingData)null);
				world.spawnEntityInWorld(entityToSpawn);
				((EntityLiving)entityToSpawn).playLivingSound();
			}
			return entityToSpawn;
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		Iterator iterator = MPEntities.entityEggs.values().iterator();

		while (iterator.hasNext())
		{
			EntityEggInfo eggInfo = (EntityEggInfo)iterator.next();
			list.add(new ItemStack(this, 1, eggInfo.spawnedID));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int layer)
	{
		EntityEggInfo info = MPEntities.entityEggs.get(Integer.valueOf(itemStack.getItemDamage()));
		return info != null ? layer == 0 ? info.primaryColor : info.secondaryColor : 16777215;
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		return "Spawn " + StatCollector.translateToLocal("entity.MorePlanets." + MPEntities.getStringFromID(itemStack.getItemDamage()) + ".name");
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpItemsTab;
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}
}