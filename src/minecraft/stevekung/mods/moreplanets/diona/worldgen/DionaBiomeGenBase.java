/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceWolf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaBiomeGenBase extends BiomeGenBase
{
	public static final BiomeGenBase diona = new DionaBiomeGenFlat(103).setBiomeName("Diona");

	@SuppressWarnings("unchecked")
	public DionaBiomeGenBase(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(DionaEntitySpaceWolf.class, 10, 1, 2));
		this.rainfall = 0F;
	}

	@Override
	public DionaBiomeGenBase setColor(int var1)
	{
		return (DionaBiomeGenBase) super.setColor(var1);
	}

	@Override
	public float getSpawningChance()
	{
		return 0.01F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor()
	{
		return -16733696;
	}
}