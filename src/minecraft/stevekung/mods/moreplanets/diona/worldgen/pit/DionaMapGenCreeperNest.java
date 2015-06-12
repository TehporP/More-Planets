/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.worldgen.pit;

import micdoodle8.mods.galacticraft.core.world.gen.GCCoreMapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class DionaMapGenCreeperNest extends GCCoreMapGenStructure
{
	@Override
	protected boolean canSpawnStructureAtCoords(int par1, int par2)
	{
		if (this.rand.nextInt(75) != 0)
		{
			return false;
		}
		return true;
	}

	@Override
	protected StructureStart getStructureStart(int par1, int par2)
	{
		return new DionaStructureCreeperPitStart(this.worldObj, this.rand, par1, par2);
	}
}