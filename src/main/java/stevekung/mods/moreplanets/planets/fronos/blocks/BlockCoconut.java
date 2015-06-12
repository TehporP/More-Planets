/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCoconut extends BlockFalling
{
	public static PropertyBool HAS_LEAVES = PropertyBool.create("has_coconut_leaves");

	public BlockCoconut(String name)
	{
		super(Material.wood);
		this.setBlockBounds(0.186F, 0.186F, 0.186F, 0.814F, 0.814F, 0.814F);
		this.setTickRandomly(true);
		this.setHardness(1.5F);
		this.setDefaultState(this.getDefaultState().withProperty(HAS_LEAVES, Boolean.valueOf(false)));
		this.setStepSound(soundTypeWood);
		this.setUnlocalizedName(name);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetsCore.mpBlocksTab;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (!(world.getBlockState(pos.up()).getBlock() == FronosBlocks.fronos_colorized_leaves))
		{
			world.scheduleUpdate(pos, this, this.tickRate(world));
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			if (!(world.getBlockState(pos.up()).getBlock() == FronosBlocks.fronos_colorized_leaves))
			{
				super.updateTick(world, pos, state, rand);
			}
		}
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float distance)
	{
		if (!world.isRemote && world.rand.nextInt(100) == 0)
		{
			world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "dig.wood", 5.0F, 1.2F);
			world.setBlockState(pos, FronosBlocks.coconut_milk.getDefaultState());
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if (pos == null)
		{
			return state;
		}
		else
		{
			Block block = world.getBlockState(pos.up()).getBlock();
			return state.withProperty(HAS_LEAVES, Boolean.valueOf(block == FronosBlocks.fronos_colorized_leaves));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(HAS_LEAVES, Boolean.valueOf((meta & 1) == 1));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Boolean)state.getValue(HAS_LEAVES)).booleanValue() ? 1 : 0;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {HAS_LEAVES});
	}
}