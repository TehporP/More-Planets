/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.worldgen.village;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.koentus.worldgen.KoentusBiomeGenBase;
import cpw.mods.fml.common.FMLLog;

public class KoentusMapGenVillage extends MapGenStructure
{
	public static List<BiomeGenBase> villageSpawnBiomes = Arrays.asList(new BiomeGenBase[] { KoentusBiomeGenBase.koentus });
	private final int terrainType;
	private static boolean initialized;

	static
	{
		try
		{
			KoentusMapGenVillage.initiateStructures();
		}
		catch (Throwable e)
		{
		}
	}

	public static void initiateStructures() throws Throwable
	{
		if (!KoentusMapGenVillage.initialized)
		{
			MapGenStructureIO.func_143034_b(KoentusStructureVillageStart.class, "KoentusVillage");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageField.class, "KoentusField1");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageField2.class, "KoentusField2");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageHouse.class, "KoentusHouse");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageRoadPiece.class, "KoentusRoadPiece");
			MapGenStructureIO.func_143031_a(KoentusComponentVillagePathGen.class, "KoentusPath");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageTorch.class, "KoentusTorch");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageStartPiece.class, "KoentusWell");
			MapGenStructureIO.func_143031_a(KoentusComponentVillageWoodHut.class, "KoentusWoodHut");
		}

		KoentusMapGenVillage.initialized = true;
	}

	public KoentusMapGenVillage()
	{
		this.terrainType = 0;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int i, int j)
	{
		final byte numChunks = 32;
		final byte offsetChunks = 8;
		final int oldi = i;
		final int oldj = j;

		if (i < 0)
		{
			i -= numChunks - 1;
		}

		if (j < 0)
		{
			j -= numChunks - 1;
		}

		int randX = i / numChunks;
		int randZ = j / numChunks;
		final Random var7 = this.worldObj.setRandomSeed(i, j, 10387312);
		randX *= numChunks;
		randZ *= numChunks;
		randX += var7.nextInt(numChunks - offsetChunks);
		randZ += var7.nextInt(numChunks - offsetChunks);

		if (oldi == randX && oldj == randZ)
		{
			return true;
		}

		return false;
	}

	@Override
	protected StructureStart getStructureStart(int par1, int par2)
	{
		FMLLog.info("Generating Koentus Village at x" + par1 * 16 + " z" + par2 * 16);
		return new KoentusStructureVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
	}

	@Override
	public String func_143025_a()
	{
		return "KoentusVillage";
	}
}