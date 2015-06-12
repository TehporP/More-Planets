/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.init.MPPotions;

public class InfectedGasEffect extends Potion
{
	public InfectedGasEffect(boolean isBad, int color)
	{
		super(MPPotions.getNextID(), isBad, color);
		this.setIconIndex(1, 0);
	}

	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("mpcore:textures/potions/MPPotionFX.png"));
		return 0;
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration >= 1;
	}
}