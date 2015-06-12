/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaAncientChest;

public class BlockDionaAncientChest extends BlockAncientChestMP
{
	public BlockDionaAncientChest(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, pos, state, neighborBlock);
		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityDionaAncientChest)
		{
			tileentity.updateContainingBlockInfo();
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else if (world.rand.nextInt(10) == 0 && !player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(world);
				creeper.setPosition(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);
				world.spawnEntityInWorld(creeper);
			}
		}
		else
		{
			ILockableContainer lock = this.getLockableContainer(world, pos);

			if (lock != null)
			{
				player.displayGUIChest(lock);
			}
		}
		return true;
	}

	public ILockableContainer getLockableContainer(World world, BlockPos pos)
	{
		TileEntity tileentity = world.getTileEntity(pos);

		if (!(tileentity instanceof TileEntityDionaAncientChest))
		{
			return null;
		}
		else
		{
			Object object = tileentity;

			if (this.isBlocked(world, pos))
			{
				return null;
			}
			else
			{
				Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

				while (iterator.hasNext())
				{
					EnumFacing enumfacing = (EnumFacing)iterator.next();
					BlockPos blockpos1 = pos.offset(enumfacing);
					Block block = world.getBlockState(blockpos1).getBlock();

					if (block == this)
					{
						if (this.isBlocked(world, blockpos1))
						{
							return null;
						}

						TileEntity tileentity1 = world.getTileEntity(blockpos1);

						if (tileentity1 instanceof TileEntityDionaAncientChest)
						{
							if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH)
							{
								object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (ILockableContainer)object, (TileEntityDionaAncientChest)tileentity1);
							}
							else
							{
								object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (TileEntityDionaAncientChest)tileentity1, (ILockableContainer)object);
							}
						}
					}
				}
				return (ILockableContainer)object;
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if (world.rand.nextInt(10) == 0)
		{
			if (!world.isRemote)
			{
				EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(world);
				creeper.setPosition(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);
				world.spawnEntityInWorld(creeper);
			}
		}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}

	@Override
	public TileEntity getChestTile()
	{
		return new TileEntityDionaAncientChest();
	}

	@Override
	public int getComparatorInputOverride(World world, BlockPos pos)
	{
		return Container.calcRedstoneFromInventory(this.getLockableContainer(world, pos));
	}
}