/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.core.planets;

import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.polongnius.dimension.PolongniusWorldProvider;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;

public class PolongniusPlanet implements IPlanet
{
	private final IMapObject polongnius = new PolongniusMapPlanet();

	@Override
	public String getName()
	{
		return "Polongnius";
	}

	@Override
	public boolean isReachable()
	{
		return true;
	}

	@Override
	public IMapObject getMapObject()
	{
		return new PolongniusMapPlanet();
	}

	@Override
	public boolean autoRegister()
	{
		return true;
	}

	@Override
	public boolean addToList()
	{
		return false;
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return PolongniusWorldProvider.class;
	}

	@Override
	public int getDimensionID()
	{
		return PolongniusConfigManager.idDimensionPolongnius;
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}

	@Override
	public boolean forceStaticLoad()
	{
		return true;
	}
}