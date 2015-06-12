/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenLog;

public class FronosBiomeGenFlat extends FronosBiomeGenBase
{
	public FronosBiomeGenFlat(int par1)
	{
		super(par1);
		this.setBiomeName("Fronos");
		this.setColor(-16733696);
		this.minHeight = 3.2F;
		this.maxHeight = 0.4F;
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(5) == 0 ? new FronosWorldGenLog() : par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees;
	}
}