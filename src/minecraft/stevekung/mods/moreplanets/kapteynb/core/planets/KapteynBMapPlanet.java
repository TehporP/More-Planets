/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.core.planets;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import stevekung.mods.moreplanets.kapteynb.core.render.gui.KapteynBSlotRenderer;

public class KapteynBMapPlanet implements IMapObject
{
	@Override
	public float getPlanetSize()
	{
		return 12.65F;
	}

	@Override
	public float getDistanceFromCenter()
	{
		return 0.0F;
	}

	@Override
	public float getPhaseShift()
	{
		return 1768.5F;
	}

	@Override
	public float getStretchValue()
	{
		return 154.15F;
	}

	@Override
	public ICelestialBodyRenderer getSlotRenderer()
	{
		return new KapteynBSlotRenderer();
	}

	@Override
	public IGalaxy getParentGalaxy()
	{
		return null;
	}
}