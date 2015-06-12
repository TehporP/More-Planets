/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.core.planets;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.polongnius.core.render.gui.PolongniusSlotGuiRenderer;

public class PolongniusMapPlanet implements IMapObject
{
	@Override
	public float getPlanetSize()
	{
		return 80.5F;
	}

	@Override
	public float getDistanceFromCenter()
	{
		return 51.5F;
	}

	@Override
	public float getPhaseShift()
	{
		return 73.6F;
	}

	@Override
	public float getStretchValue()
	{
		return 43.13F;
	}

	@Override
	public ICelestialBodyRenderer getSlotRenderer()
	{
		return new PolongniusSlotGuiRenderer();
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}
}