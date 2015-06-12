/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.IPlantMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockStrawberryBush extends BlockCrops implements IPlantMP
{
	public BlockStrawberryBush(String name)
	{
		super();
		this.setStepSound(soundTypeGrass);
		this.setUnlocalizedName(name);
	}

	@Override
	protected Item getCrop()
	{
		return FronosItems.fronos_food;
	}

	@Override
	protected Item getSeed()
	{
		return FronosItems.strawberry_seed;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		return world.getBlockState(pos.down()).getBlock() == FronosBlocks.fronos_farmland;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = ((Integer)state.getValue(AGE)).intValue();
		Random rand = world instanceof World ? ((World)world).rand : new Random();

		if (age >= 7)
		{
			for (int i = 0; i < 4 + fortune; ++i)
			{
				if (rand.nextInt(15) <= age)
				{
					ret.add(new ItemStack(this.getCrop(), 1, 0));
				}
			}
			for (int i = 0; i < 1 + fortune; ++i)
			{
				ret.add(new ItemStack(this.getSeed(), 1, 0));
			}
		}
		return ret;
	}
}