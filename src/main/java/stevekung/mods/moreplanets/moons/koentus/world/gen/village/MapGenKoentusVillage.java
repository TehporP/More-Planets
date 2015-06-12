/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.fml.common.FMLLog;

public class MapGenKoentusVillage extends MapGenStructure
{
	private int terrainType;

	static
	{
		MapGenStructureIO.registerStructure(StructureKoentusVillageStart.class, "KoentusVillage");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageField.class, "KoentusField1");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageHouse.class, "KoentusHouse");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageRoadPiece.class, "KoentusRoadPiece");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillagePathGen.class, "KoentusPath");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageTorch.class, "KoentusTorch");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageStartPiece.class, "KoentusWell");
		MapGenStructureIO.registerStructureComponent(ComponentKoentusVillageHut.class, "KoentusHut");
	}

	public MapGenKoentusVillage()
	{
		this.terrainType = 0;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int i, int j)
	{
		byte numChunks = 32;
		byte offsetChunks = 8;
		int oldi = i;
		int oldj = j;

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
		Random var7 = this.worldObj.setRandomSeed(i, j, 10387312);
		randX *= numChunks;
		randZ *= numChunks;
		randX += var7.nextInt(numChunks - offsetChunks);
		randZ += var7.nextInt(numChunks - offsetChunks);

		return oldi == randX && oldj == randZ;
	}

	@Override
	protected StructureStart getStructureStart(int par1, int par2)
	{
		FMLLog.info("Generating Koentus Village at x" + par1 * 16 + " z" + par2 * 16);
		return new StructureKoentusVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
	}

	@Override
	public String getStructureName()
	{
		return "KoentusVillage";
	}
}