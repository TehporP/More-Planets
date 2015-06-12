/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockFlowerMP;

public class BlockInfectedOrangeRoseBush extends BlockFlowerMP implements IGrowable
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockInfectedOrangeRoseBush(String name)
	{
		super();
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.orange_rose_bush_bottom));
		this.setUnlocalizedName(name);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return state == state.withProperty(VARIANT, BlockType.orange_rose_bush_top) ? null : Item.getItemFromBlock(this);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();

		if (state == state.withProperty(VARIANT, BlockType.orange_rose_bush_top))
		{
			return block == this && world.getBlockState(pos.down()).getValue(VARIANT) == BlockType.orange_rose_bush_bottom;
		}
		else
		{
			return block == NibiruBlocks.infected_grass || block == NibiruBlocks.infected_dirt;
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
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos.up(), this.getDefaultState().withProperty(VARIANT, BlockType.orange_rose_bush_top), 2);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (state == state.withProperty(VARIANT, BlockType.orange_rose_bush_top))
		{
			if (!player.capabilities.isCreativeMode)
			{
				Block.spawnAsEntity(world, pos, new ItemStack(this, 1, 0));
			}
			world.setBlockToAir(pos.down());
			world.setBlockToAir(pos);
		}
		else if (player.capabilities.isCreativeMode)
		{
			world.setBlockToAir(pos);
		}
		super.onBlockHarvested(world, pos, state, player);
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
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return true;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		Block.spawnAsEntity(world, pos, new ItemStack(this, 1, 0));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		orange_rose_bush_bottom,
		orange_rose_bush_top;

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