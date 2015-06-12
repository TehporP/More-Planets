/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.planets;

import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IMoon;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;

public class MPPlanetSiriusB implements IMoon
{
	@Override
	public String getName()
	{
		return "Sirius B";
	}

	@Override
	public boolean isReachable()
	{
		return false;
	}

	@Override
	public IMapObject getMapObject()
	{
		return new MPMapPlanetSiriusB();
	}

	@Override
	public boolean autoRegister()
	{
		return false;
	}

	@Override
	public boolean addToList()
	{
		return false;
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return null;
	}

	@Override
	public int getDimensionID()
	{
		return -1;
	}

	@Override
	public boolean forceStaticLoad()
	{
		return false;
	}

	@Override
	public IPlanet getParentPlanet()
	{
		return MorePlanetCore.sirius;
	}
}