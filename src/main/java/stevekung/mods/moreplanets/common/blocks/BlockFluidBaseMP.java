/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public abstract class BlockFluidBaseMP extends BlockFluidClassic
{
	Fluid fluid;

	public BlockFluidBaseMP(Fluid fluid)
	{
		super(fluid, Material.water);
		this.fluid = fluid;
	}

	public BlockFluidBaseMP(Fluid fluid, Material material)
	{
		super(fluid, material);
		this.fluid = fluid;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid())
		{
			return false;
		}
		if (world.getBlockState(pos).getBlock().getMaterial() == Material.lava)
		{
			return false;
		}
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid())
		{
			return false;
		}
		if (world.getBlockState(pos).getBlock().getMaterial() == Material.lava)
		{
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}
}