/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.planets;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.nibiru.core.render.gui.NibiruSlotGuiRenderer;

public class NibiruMapPlanet implements IMapObject
{
	@Override
	public float getDistanceFromCenter()
	{
		return 189.45F;
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return MorePlanetCore.galaxySirius;
	}

	@Override
	public float getPhaseShift()
	{
		return 52.1F;
	}

	@Override
	public float getPlanetSize()
	{
		return 130.53F;
	}

	@Override
	public ICelestialBodyRenderer getSlotRenderer()
	{
		return new NibiruSlotGuiRenderer();
	}

	@Override
	public float getStretchValue()
	{
		return 97.42F;
	}
}