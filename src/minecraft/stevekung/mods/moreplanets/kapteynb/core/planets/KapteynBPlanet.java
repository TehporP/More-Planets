/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.core.planets;

import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.world.WorldProvider;
import stevekung.mods.moreplanets.kapteynb.dimension.KapteynBWorldProvider;
import stevekung.mods.moreplanets.kapteynb.util.KapteynBConfigManager;

public class KapteynBPlanet implements IPlanet
{
	private final IMapObject kapteynB = new KapteynBMapPlanet();

	@Override
	public String getName()
	{
		return "Kapteyn B";
	}

	@Override
	public boolean isReachable()
	{
		return true;
	}

	@Override
	public IMapObject getMapObject()
	{
		return this.kapteynB;
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
		return KapteynBWorldProvider.class;
	}

	@Override
	public int getDimensionID()
	{
		return KapteynBConfigManager.idDimensionKapteynB;
	}

	@Override
	public boolean forceStaticLoad()
	{
		return true;
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return null;
	}
}