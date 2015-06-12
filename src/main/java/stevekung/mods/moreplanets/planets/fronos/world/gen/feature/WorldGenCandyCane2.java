/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCandyCane2;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenCandyCane2 extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		if (world.isAirBlock(pos.up()) && world.getBlockState(pos.down()).getBlock() instanceof IFronosGrass)
		{
			world.setBlockState(pos.up(), FronosBlocks.candy_cane2.getDefaultState(), 2);
			world.setBlockState(pos.up(2), FronosBlocks.candy_cane2.getDefaultState(), 2);
			world.setBlockState(pos.up(3), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.blue_candy_cane), 2);
			world.setBlockState(pos.up(4), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.blue_candy_cane), 2);
			world.setBlockState(pos.up(5), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.red_candy_cane), 2);
			world.setBlockState(pos.up(6), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.red_candy_cane), 2);
			world.setBlockState(pos.up(7), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.purple_candy_cane), 2);
			world.setBlockState(pos.up(8), FronosBlocks.candy_cane2.getDefaultState().withProperty(BlockCandyCane2.VARIANT, BlockCandyCane2.BlockType.purple_candy_cane), 2);
			world.setBlockState(pos.up(9), FronosBlocks.cream_block.getStateFromMeta(rand.nextInt(4)), 2);

			if (rand.nextInt(1) == 0)
			{
				world.setBlockState(pos.up(10), FronosBlocks.ovantine_block.getDefaultState(), 2);
			}
			if (rand.nextInt(2) == 0)
			{
				world.setBlockState(pos.up(10), FronosBlocks.chocolate_block.getDefaultState(), 2);
			}
			if (rand.nextInt(3) == 0)
			{
				world.setBlockState(pos.up(10), FronosBlocks.cookie_block.getDefaultState(), 2);
			}
			if (rand.nextInt(4) == 0)
			{
				world.setBlockState(pos.up(10), FronosBlocks.jelly_block.getStateFromMeta(rand.nextInt(6)), 2);
			}
		}
		return true;
	}
}