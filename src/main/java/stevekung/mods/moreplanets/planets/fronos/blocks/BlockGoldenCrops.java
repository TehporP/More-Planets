/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.IPlantMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGoldenCrops extends BlockCrops implements IPlantMP
{
	public BlockGoldenCrops(String name)
	{
		super();
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		return world.getBlockState(pos.down()).getBlock() == FronosBlocks.fronos_farmland;
	}

	@Override
	protected Item getCrop()
	{
		return FronosItems.fronos_item;
	}

	@Override
	protected Item getSeed()
	{
		return FronosItems.golden_seeds;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 6;
	}
}