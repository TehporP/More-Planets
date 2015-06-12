/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.fluids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.polongnius.fluids.blocks.PolongniusBlockFluidCheeseOfMilk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusCheeseOfMilkFluid extends Fluid
{
	public PolongniusCheeseOfMilkFluid(String fluidName)
	{
		super(fluidName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getStillIcon()
	{
		return PolongniusBlockFluidCheeseOfMilk.cheeseOfMilkStillIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getFlowingIcon()
	{
		return PolongniusBlockFluidCheeseOfMilk.cheeseOfMilkFlowingIcon;
	}
}