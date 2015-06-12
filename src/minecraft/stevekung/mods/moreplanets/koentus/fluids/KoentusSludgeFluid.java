/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.fluids;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.koentus.fluids.blocks.KoentusBlockSludge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusSludgeFluid extends Fluid
{
	public KoentusSludgeFluid(String fluidName)
	{
		super(fluidName);
		this.setViscosity(1800);
		this.setGaseous(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getStillIcon()
	{
		return KoentusBlockSludge.koentusSludgeStillIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getFlowingIcon()
	{
		return KoentusBlockSludge.koentusSludgeFlowingIcon;
	}
}