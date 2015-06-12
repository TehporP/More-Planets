/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public abstract class BlockTreasureChestMP extends BlockContainerMP implements ITileEntityProvider
{
	public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockTreasureChestMP()
	{
		super(Material.rock);
		this.setResistance(10000000.0F);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		this.setHardness(-1.0F);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof IInventory)
		{
			InventoryHelper.dropInventoryItems(world, pos, (IInventory)tileentity);
			world.updateComparatorOutputLevel(pos, this);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			ILockableContainer ilockablecontainer = this.getLockableContainer(world, pos);

			if (ilockablecontainer != null)
			{
				player.displayGUIChest(ilockablecontainer);
			}
			return true;
		}
	}

	public ILockableContainer getLockableContainer(World world, BlockPos pos)
	{
		TileEntity tileentity = world.getTileEntity(pos);

		if (!(tileentity == this.getTreasureChest()))
		{
			return null;
		}
		else
		{
			Object object = this.getTreasureChest();

			if (this.cannotOpenChest(world, pos))
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
						if (this.cannotOpenChest(world, blockpos1))
						{
							return null;
						}

						TileEntity tileentity1 = world.getTileEntity(blockpos1);

						if (tileentity1 == this.getTreasureChest())
						{
							if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH)
							{
								object = new InventoryLargeChest("container.chestDouble", (ILockableContainer)object, (ILockableContainer) this.getTreasureChest());
							}
							else
							{
								object = new InventoryLargeChest("container.chestDouble", (ILockableContainer) this.getTreasureChest(), (ILockableContainer)object);
							}
						}
					}
				}
				return (ILockableContainer)object;
			}
		}
	}

	private boolean cannotOpenChest(World world, BlockPos pos)
	{
		return this.isBelowSolidBlock(world, pos);
	}

	private boolean isBelowSolidBlock(World world, BlockPos pos)
	{
		return world.getBlockState(pos.up()).getBlock().isNormalCube();
	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int meta)
	{
		return this.getTreasureChest();
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, BlockPos pos)
	{
		return Container.calcRedstoneFromInventory(this.getLockableContainer(world, pos));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {FACING});
	}

	public abstract TileEntity getTreasureChest();
}