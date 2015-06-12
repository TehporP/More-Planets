/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.worldgen;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenAppleTree;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenOrangeTree;

public class NibiruBiomeGenFlat extends NibiruBiomeGenBase
{
	public NibiruBiomeGenFlat(int par1)
	{
		super(par1);
		this.setBiomeName("Nibiru");
		this.setColor(-16744448);
		this.minHeight = 1.5F;
		this.maxHeight = 0.4F;
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(100) == 0 ? new NibiruWorldGenAppleTree() : new NibiruWorldGenOrangeTree();
	}
}