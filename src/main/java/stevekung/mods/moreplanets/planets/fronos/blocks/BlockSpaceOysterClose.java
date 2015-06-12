/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockOysterMP;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntitySpaceOysterClose;

public class BlockSpaceOysterClose extends BlockOysterMP
{
	public BlockSpaceOysterClose(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		return 4;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);

		if (!world.isRemote)
		{
			if (world.isDaytime() || world.getLightFromNeighbors(pos.up()) >= 9)
			{
				if (world.rand.nextInt(10) == 0)
				{
					world.setBlockState(pos, FronosBlocks.space_oyster_open.getDefaultState().withProperty(FACING, EnumFacing.getFront(this.getMetaFromState(state))), 3);
				}
				if (world.getBlockState(pos.down()) == Blocks.sand || world.getBlockState(pos.down()) == FronosBlocks.fronos_sand.getDefaultState().withProperty(BlockFronosSand.VARIANT, BlockFronosSand.BlockType.white_sand))
				{
					world.setBlockState(pos, FronosBlocks.space_oyster_open.getDefaultState().withProperty(FACING, EnumFacing.getFront(this.getMetaFromState(state))), 3);
				}
			}
		}
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

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySpaceOysterClose();
	}
}