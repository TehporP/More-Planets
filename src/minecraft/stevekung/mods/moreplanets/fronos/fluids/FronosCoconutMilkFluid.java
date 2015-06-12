/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.fluids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.fronos.fluids.blocks.FronosBlockFluidCoconutMilk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosCoconutMilkFluid extends Fluid
{
	public FronosCoconutMilkFluid(String fluidName)
	{
		super(fluidName);
		this.setViscosity(1500);
		this.setDensity(1000);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getStillIcon()
	{
		return FronosBlockFluidCoconutMilk.coconutMilkStillIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getFlowingIcon()
	{
		return FronosBlockFluidCoconutMilk.coconutMilkFlowingIcon;
	}
}