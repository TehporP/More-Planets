/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.planets;

import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.diona.dimension.DionaWorldProvider;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;

public class DionaPlanet implements IPlanet
{
	private final IMapObject Diona = new DionaMapPlanet();

	@Override
	public boolean addToList()
	{
		return false;
	}

	@Override
	public boolean autoRegister()
	{
		return true;
	}

	@Override
	public int getDimensionID()
	{
		return DionaConfigManager.idDimensionDiona;
	}

	@Override
	public IMapObject getMapObject()
	{
		return this.Diona;
	}

	@Override
	public String getName()
	{
		return "Diona";
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return DionaWorldProvider.class;
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