/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.planets;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import stevekung.mods.moreplanets.core.MorePlanetCore;

public class MPMapPlanetSiriusB implements IMapObject
{
	@Override
	public float getPlanetSize()
	{
		return 1.8F;
	}

	@Override
	public float getDistanceFromCenter()
	{
		return 5000F;
	}

	@Override
	public float getPhaseShift()
	{
		return 0;
	}

	@Override
	public float getStretchValue()
	{
		return 0.1F;
	}

	@Override
	public ICelestialBodyRenderer getSlotRenderer()
	{
		return new MPSlotGuiSiriusBRenderer();
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}
}