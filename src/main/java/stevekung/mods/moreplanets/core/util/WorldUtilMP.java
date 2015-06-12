/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;

public class WorldUtilMP
{
	public static boolean isMercuryWorld(World world, int x, int y, int z)
	{
		return world.provider instanceof WorldProviderMercury && world.isDaytime() && world.canBlockSeeTheSky(x, y, z);
	}

	public static boolean isPlutoWorld(World world, int x, int y, int z)
	{
		return world.provider instanceof WorldProviderPluto;
	}
}