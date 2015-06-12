/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.planets;

import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.nibiru.dimension.NibiruWorldProvider;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;

public class NibiruPlanet implements IPlanet
{
	private final IMapObject nibiru = new NibiruMapPlanet();

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
		return NibiruConfigManager.idDimensionNibiru;
	}

	@Override
	public IMapObject getMapObject()
	{
		return this.nibiru;
	}

	@Override
	public String getName()
	{
		return "Nibiru";
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return NibiruWorldProvider.class;
	}

	@Override
	public boolean isReachable()
	{
		return true;
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