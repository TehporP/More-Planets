/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCow;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseSlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBiomeGenBase extends BiomeGenBase
{
	public static final BiomeGenBase polongnius = new PolongniusBiomeGenFlat(102).setBiomeName("Polongnius");

	public PolongniusBiomeGenBase(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.rainfall = 0F;
		this.spawnableMonsterList.add(new SpawnListEntry(PolongniusEntityCheeseSlime.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(PolongniusEntityCheeseCow.class, 10, 2, 4));
	}

	@Override
	public PolongniusBiomeGenBase setColor(int var1)
	{
		return (PolongniusBiomeGenBase) super.setColor(var1);
	}

	@Override
	public float getSpawningChance()
	{
		return 0.1F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor()
	{
		return -16733696;
	}
}