/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityBearry;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityBerry;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityCat;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityGrappy;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyBerrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyGrapeSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyLimeSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyRaspberrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyStrawberrySlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityKiwi;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityLemonDuck;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityMarshmallow;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityMelon;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityOrangeCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntitySpaceStarfish;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityStrawberryCreamSlime;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityTomato;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityVanillaCreamSlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBiomeGenBase extends BiomeGenBase
{
	public static final BiomeGenBase fronos = new FronosBiomeGenFlat(102).setBiomeName("Fronos");

	public FronosBiomeGenBase(int var1)
	{
		super(var1);
		this.setColor(-16733696);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityJellyBerrySlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityJellyGrapeSlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityJellyLimeSlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityJellyStrawberrySlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityJellyRaspberrySlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityOrangeCreamSlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityStrawberryCreamSlime.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FronosEntityVanillaCreamSlime.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityBearry.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityBerry.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityCat.class, 10, 1, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityGrappy.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityKiwi.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityLemonDuck.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityMarshmallow.class, 10, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityMelon.class, 10, 1, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntitySpaceStarfish.class, 10, 1, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FronosEntityTomato.class, 10, 1, 2));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor()
	{
		return -16744448;
	}

	@Override
	public float getSpawningChance()
	{
		return 1.0F;
	}

	@Override
	public FronosBiomeGenBase setColor(int var1)
	{
		return (FronosBiomeGenBase) super.setColor(var1);
	}
}