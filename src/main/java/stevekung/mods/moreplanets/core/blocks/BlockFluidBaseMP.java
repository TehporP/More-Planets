/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFluidBaseMP extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	IIcon flowingIcon;
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
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}
		if (world.getBlock(x, y, z).getMaterial() == Material.lava)
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}
		if (world.getBlock(x, y, z).getMaterial() == Material.lava)
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.stillIcon = iconRegister.registerIcon(this.getStillTextures());
		this.flowingIcon = iconRegister.registerIcon(this.getFlowingTextures());
		this.fluid.setFlowingIcon(this.flowingIcon);
		this.fluid.setStillIcon(this.stillIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? this.flowingIcon : this.stillIcon;
	}

	public abstract String getStillTextures();
	public abstract String getFlowingTextures();
}