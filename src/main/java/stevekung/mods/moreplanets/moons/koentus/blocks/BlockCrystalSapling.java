/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.common.blocks.BlockSaplingMP;
import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenTreeMP;

public class BlockCrystalSapling extends BlockSaplingMP
{
	public BlockCrystalSapling(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.grass || block == Blocks.dirt || block == KoentusBlocks.crystal_dirt || block.canSustainPlant(world, pos.down(), EnumFacing.UP, this);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		this.checkAndDropBlock(world, pos, state);

		if (!world.isRemote)
		{
			super.updateTick(world, pos, state, rand);

			if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(world, rand, pos, state);
			}
		}
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		Object obj = null;

		if (obj == null)
		{
			obj = new WorldGenTreeMP(6, KoentusBlocks.crystal_log, KoentusBlocks.crystal_leaves, 0, 0, false, this);
		}
		if (obj != null)
		{
			world.setBlockToAir(pos);

			if (!((WorldGenerator)obj).generate(world, rand, pos))
			{
				world.setBlockState(pos, state, 2);
			}
		}
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return false;
	}
}