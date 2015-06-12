/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.fml.common.FMLLog;

public class MapGenFronosVillage extends MapGenStructure
{
	private int terrainType;
	private int field_82665_g;
	private int field_82666_h;

	static
	{
		MapGenStructureIO.registerStructure(StructureFronosVillageStart.class, "FronosVillage");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageHouse.class, "FronosHouse");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageField.class, "FronosField1");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageField2.class, "FronosField2");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageField3.class, "FronosField3");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageTorch.class, "FronosTorch");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageHall.class, "FronosHall");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageHouse4.class, "FronosHouse4");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageHut.class, "FronosHut");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageRoadPiece.class, "FronosRoadPiece");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillagePathGen.class, "FronosPath");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageHouse3.class, "FronosHouse3");
		MapGenStructureIO.registerStructureComponent(ComponentFronosVillageStartPiece.class, "FronosWell");
	}

	public MapGenFronosVillage()
	{
		this.terrainType = 0;
		this.field_82665_g = 32;
		this.field_82666_h = 8;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int x, int z)
	{
		if (x < 0)
		{
			x -= this.field_82665_g - 1;
		}
		if (z < 0)
		{
			z -= this.field_82665_g - 1;
		}
		int i1 = x / this.field_82665_g;
		int j1 = z / this.field_82665_g;
		Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
		i1 *= this.field_82665_g;
		j1 *= this.field_82665_g;
		i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
		j1 += random.nextInt(this.field_82665_g - this.field_82666_h);
		return x == i1 && z == j1;
	}

	@Override
	protected StructureStart getStructureStart(int x, int z)
	{
		FMLLog.info("Generating Fronos Village at x" + x * 16 + " z" + z * 16);
		return new StructureFronosVillageStart(this.worldObj, this.rand, x, z, this.terrainType);
	}

	@Override
	public String getStructureName()
	{
		return "FronosVillage";
	}
}