/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.planets;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.diona.core.render.gui.DionaSlotGuiRenderer;

public class DionaMapPlanet implements IMapObject
{
	@Override
	public float getDistanceFromCenter()
	{
		return 14.6F;
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}

	@Override
	public float getPhaseShift()
	{
		return 2557.5F;
	}

	@Override
	public float getPlanetSize()
	{
		return 24.35F;
	}

	@Override
	public ICelestialBodyRenderer getSlotRenderer()
	{
		return new DionaSlotGuiRenderer();
	}

	@Override
	public float getStretchValue()
	{
		return 76.5F;
	}
}