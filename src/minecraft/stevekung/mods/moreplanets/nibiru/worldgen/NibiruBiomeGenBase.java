/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruBiomeGenBase extends BiomeGenBase
{
	public static final BiomeGenBase nibiru = new NibiruBiomeGenFlat(102).setBiomeName("Nibiru");

	public NibiruBiomeGenBase(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.rainfall = 0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor()
	{
		return -16733696;
	}

	@Override
	public float getSpawningChance()
	{
		return 0.1F;
	}

	@Override
	public NibiruBiomeGenBase setColor(int var1)
	{
		return (NibiruBiomeGenBase) super.setColor(var1);
	}
}