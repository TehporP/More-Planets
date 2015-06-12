/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import stevekung.mods.moreplanets.common.blocks.BlockFarmlandMP;

public class BlockFronosFarmland extends BlockFarmlandMP
{
	public BlockFronosFarmland(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public Block getDirtBlock()
	{
		return FronosBlocks.fronos_dirt;
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant)
	{
		Block block = plant.getPlant(world, pos).getBlock();
		return block == FronosBlocks.strawberry_bush || block == FronosBlocks.golden_crops || block == FronosBlocks.glass_gem_corn;
	}
}