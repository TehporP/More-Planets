/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

public class MPEntityRegisterUtil
{
	public static void registerMorePlanetCreature(Class<? extends Entity> var0, String var1, int id, int back, int fore)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", StatCollector.translateToLocal("entity.MorePlanet." + var1 + ".name"));
		}
		EntityRegistry.registerGlobalEntityID(var0, var1, id, back, fore);
		EntityRegistry.registerModEntity(var0, var1, id, MorePlanetCore.instance, 80, 3, true);
	}

	public static void registerMorePlanetNonMobEntity(Class<? extends Entity> var0, String var1, int id, int trackingDistance, int updateFreq, boolean sendVel)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", StatCollector.translateToLocal("entity.MorePlanet." + var1 + ".name"));
		}
		EntityRegistry.registerModEntity(var0, var1, id, MorePlanetCore.instance, trackingDistance, updateFreq, sendVel);
	}
}