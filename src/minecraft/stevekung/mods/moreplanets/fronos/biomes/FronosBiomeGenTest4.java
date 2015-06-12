/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
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
import stevekung.mods.moreplanets.fronos.worldgen.FronosBiomeDecorator;

public class FronosBiomeGenTest4 extends BiomeGenBase
{
	@SuppressWarnings("unchecked")
	public FronosBiomeGenTest4(int par1)
	{
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = (byte)FronosBlocks.fronosPlainsGrass.blockID;
		this.fillerBlock = (byte)FronosBlocks.fronosPlainsGrass.blockID;
		this.theBiomeDecorator = new FronosBiomeDecorator(this);
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

	/*@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			Block block = Block.blocksList[var10];
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 2, 2);
			}
		}
	}*/

	@Override
	public int getSkyColorByTemp(float par1)
	{
		return 15898486;
	}
}