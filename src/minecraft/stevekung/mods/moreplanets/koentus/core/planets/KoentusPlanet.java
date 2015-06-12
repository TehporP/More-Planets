/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.core.planets;

import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IMoon;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.koentus.dimension.KoentusWorldProvider;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;

public class KoentusPlanet implements IMoon
{
	private final IMapObject koentus = new KoentusMapPlanet();

	@Override
	public String getName()
	{
		return "Koentus";
	}

	@Override
	public boolean isReachable()
	{
		return true;
	}

	@Override
	public IPlanet getParentPlanet()
	{
		return MorePlanetCore.diona;
	}

	@Override
	public IMapObject getMapObject()
	{
		return this.koentus;
	}

	@Override
	public boolean autoRegister()
	{
		return true;
	}

	@Override
	public boolean addToList()
	{
		return true;
	}

	@Override
	public Class<? extends WorldProvider> getWorldProvider()
	{
		return KoentusWorldProvider.class;
	}

	@Override
	public int getDimensionID()
	{
		return KoentusConfigManager.idDimensionKoentus;
	}

	@Override
	public boolean forceStaticLoad()
	{
		return true;
	}
}