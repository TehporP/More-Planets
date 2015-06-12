/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;

public class BlockSpacePotato extends BlockCrops
{
	public BlockSpacePotato(String name)
	{
		super();
		this.setStepSound(soundTypeGrass);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		//if (world.provider instanceof IGalacticraftWorldProvider)
		{
			//return ((Integer)state.getValue(AGE)).intValue() < 7;
		}
		return false;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return false;
		//return world.provider instanceof IGalacticraftWorldProvider;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		//if (world.provider instanceof IGalacticraftWorldProvider)
		{
			this.grow(world, pos, state);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);

		//if (world.provider instanceof IGalacticraftWorldProvider)
		{
			if (world.getLightFromNeighbors(pos.up()) >= 9)
			{
				int i = ((Integer)state.getValue(AGE)).intValue();

				if (i < 7)
				{
					float f = getGrowthChance(this, world, pos);

					if (rand.nextInt((int)(25.0F / f) + 1) == 0)
					{
						world.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
					}
				}
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	protected Item getSeed()
	{
		return PlutoItems.space_potato;
	}

	@Override
	protected Item getCrop()
	{
		return PlutoItems.space_potato;
	}
}