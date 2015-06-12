/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.planets;

import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.dimension.FronosWorldProvider;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;

public class FronosPlanet implements IPlanet
{
	private final IMapObject Fronos = new FronosMapPlanet();

	@Override
	public boolean addToList()
	{
		return true;
	}

	@Override
	public boolean autoRegister()
	{
		return true;
	}

	@Override
	public int getDimensionID()
	{
		return FronosConfigManager.idDimensionFronos;
	}

	@Override
	public IMapObject getMapObject()
	{
		return this.Fronos;
	}

	@Override
	public String getName()
	{
		return "Fronos";
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return FronosWorldProvider.class;
	}

	@Override
	public boolean isReachable()
	{
		return true;
	}

	@Override
	public boolean forceStaticLoad()
	{
		return true;
	}
}
