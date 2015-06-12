/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.proxy;

import net.minecraft.block.Block;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class CommonProxyMP
{
	public void postInit(FMLPostInitializationEvent event)
	{
	}

	public void spawnParticle(String string, double x, double y, double z)
	{
	}

	public void spawnMotionParticle(String string, double x, double y, double z, double motionX, double motionY, double motionZ)
	{
	}

	public int getBlockRender(Block block)
	{
		return -1;
	}
}