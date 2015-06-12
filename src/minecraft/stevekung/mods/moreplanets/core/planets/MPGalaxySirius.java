/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.planets;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import net.minecraft.util.StatCollector;

public class MPGalaxySirius implements IGalaxy
{
	@Override
	public String getGalaxyName()
	{
		return StatCollector.translateToLocal("galaxy.sirius.name");
	}

	@Override
	public int getXCoord()
	{
		return 5;
	}

	@Override
	public int getYCoord()
	{
		return -5;
	}

	@Override
	public Vector3 getRGBRingColors()
	{
		return new Vector3(20.0D / 156.0D, 80.0D / 156.0D, 190.0D / 46.0D);
	}
}