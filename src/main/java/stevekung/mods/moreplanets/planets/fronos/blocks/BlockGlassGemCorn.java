/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.common.blocks.IPlantMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGlassGemCorn extends BlockFlowerMP implements IPlantMP
{
	public static PropertyEnum STAGE = PropertyEnum.create("stage", BlockType.class);

	public BlockGlassGemCorn(String name)
	{
		super();
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		this.setHardness(0.2F);
		this.setDefaultState(this.getDefaultState().withProperty(STAGE, BlockType.state_bottom1));
		this.setUnlocalizedName(name);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.isDaytime() || world.getLightFromNeighbors(pos.up()) >= 9)
		{
			if (state == state.withProperty(STAGE, BlockType.state_bottom1))
			{
				if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
				{
					if (rand.nextInt(5) == 0)
					{
						world.setBlockState(pos.up(), state.withProperty(STAGE, BlockType.state_middle0), 3);
						world.setBlockState(pos, state.withProperty(STAGE, BlockType.state_bottom2), 3);
					}
				}
			}
			if (state == state.withProperty(STAGE, BlockType.state_middle0))
			{
				if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
				{
					if (rand.nextInt(5) == 0)
					{
						world.setBlockState(pos, state.withProperty(STAGE, BlockType.state_middle1), 3);
						world.setBlockState(pos.up(), state.withProperty(STAGE, BlockType.state_top1), 3);
					}
				}
			}
			if (state == state.withProperty(STAGE, BlockType.state_middle1))
			{
				if (rand.nextInt(5) == 0)
				{
					world.setBlockState(pos, state.withProperty(STAGE, BlockType.state_middle2), 3);
				}
			}
			if (state == state.withProperty(STAGE, BlockType.state_middle2))
			{
				if (rand.nextInt(5) == 0)
				{
					world.setBlockState(pos, state.withProperty(STAGE, BlockType.state_middle3), 3);
				}
			}
		}
		super.updateTick(world, pos, state, rand);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(FronosItems.glass_gem_corn, 1, 0);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return null;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return FronosItems.glass_gem_corn;
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();

		if (state == state.withProperty(STAGE, BlockType.state_middle0) || state == state.withProperty(STAGE, BlockType.state_middle1) || state == state.withProperty(STAGE, BlockType.state_middle2) || state == state.withProperty(STAGE, BlockType.state_middle3))
		{
			return block == this && world.getBlockState(pos.down()).getValue(STAGE) == BlockType.state_bottom2;
		}
		else if (state == state.withProperty(STAGE, BlockType.state_top1))
		{
			return block == this && world.getBlockState(pos.down()).getValue(STAGE) == BlockType.state_middle1 || block == this && world.getBlockState(pos.down()).getValue(STAGE) == BlockType.state_middle2 || block == this && world.getBlockState(pos.down()).getValue(STAGE) == BlockType.state_middle3;
		}
		else
		{
			return block == FronosBlocks.fronos_farmland;
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
	{
		return this.canBlockStay(world, pos, world.getBlockState(pos));
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
	{
		super.onNeighborBlockChange(world, pos, state, block);
		this.canBlockStay(world, pos, state);
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean canHarvest)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == state.withProperty(STAGE, BlockType.state_top1))
		{
			world.setBlockToAir(pos);
			return world.setBlockState(pos.down(), state.withProperty(STAGE, BlockType.state_middle0));
		}
		if (state == state.withProperty(STAGE, BlockType.state_middle0) || state == state.withProperty(STAGE, BlockType.state_middle1) || state == state.withProperty(STAGE, BlockType.state_middle2) || state == state.withProperty(STAGE, BlockType.state_middle3))
		{
			world.setBlockToAir(pos);
			return world.setBlockState(pos.down(), state.withProperty(STAGE, BlockType.state_bottom1));
		}
		else if (player.capabilities.isCreativeMode)
		{
			return world.setBlockToAir(pos);
		}
		else
		{
			return world.setBlockToAir(pos);
		}
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World)world).rand : RANDOM;

		if (state == state.withProperty(STAGE, BlockType.state_middle3))
		{
			ret.add(new ItemStack(FronosItems.glass_gem_corn, rand.nextInt(4), 0));
		}
		ret.add(new ItemStack(FronosItems.glass_gem_corn, 1, 0));
		return ret;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		return 1;
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return false;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { STAGE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(STAGE, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(STAGE)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		state_bottom1,
		state_bottom2,
		state_middle0,
		state_middle1,
		state_middle2,
		state_middle3,
		state_top1;

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}
	}
}