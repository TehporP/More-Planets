/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class StructureFronosVillagePieces
{
	public static ArrayList<StructureFronosVillagePieceWeight> getStructureVillageWeightedPieceList(Random rand, int par1)
	{
		ArrayList<StructureFronosVillagePieceWeight> var2 = new ArrayList<StructureFronosVillagePieceWeight>();
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageHouse4.class, 4, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 4 + par1 * 2)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageHouse.class, 8, MathHelper.getRandomIntegerInRange(rand, 1 + par1, 2 + par1 * 2)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageHut.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 5 + par1 * 3)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageHall.class, 8, MathHelper.getRandomIntegerInRange(rand, 0 + par1, 2 + par1 * 2)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageField.class, 3, MathHelper.getRandomIntegerInRange(rand, 1 + par1, 4 + par1 * 2)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageField2.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 4 + par1 * 2)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageField3.class, 3, MathHelper.getRandomIntegerInRange(rand, 3 + par1, 4 + par1 * 3)));
		var2.add(new StructureFronosVillagePieceWeight(ComponentFronosVillageHouse3.class, 8, MathHelper.getRandomIntegerInRange(rand, 1 + par1, 3 + par1 * 2)));

		Iterator<StructureFronosVillagePieceWeight> var3 = var2.iterator();

		while (var3.hasNext())
		{
			if (var3.next().villagePiecesLimit == 0)
			{
				var3.remove();
			}
		}
		return var2;
	}

	private static int func_75079_a(List<StructureFronosVillagePieceWeight> list)
	{
		boolean var1 = false;
		int var2 = 0;
		StructureFronosVillagePieceWeight var4;

		for (Iterator<StructureFronosVillagePieceWeight> var3 = list.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight)
		{
			var4 = var3.next();

			if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit)
			{
				var1 = true;
			}
		}
		return var1 ? var2 : -1;
	}

	private static ComponentFronosVillage func_75083_a(ComponentFronosVillageStartPiece component, StructureFronosVillagePieceWeight weight, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int par8)
	{
		Class<?> var9 = weight.villagePieceClass;
		Object var10 = null;

		if (var9 == ComponentFronosVillageHouse4.class)
		{
			var10 = ComponentFronosVillageHouse4.func_74912_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageHouse.class)
		{
			var10 = ComponentFronosVillageHouse.func_74915_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageHut.class)
		{
			var10 = ComponentFronosVillageHut.func_74908_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageHall.class)
		{
			var10 = ComponentFronosVillageHall.func_74906_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageField.class)
		{
			var10 = ComponentFronosVillageField.func_74900_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageField2.class)
		{
			var10 = ComponentFronosVillageField2.func_74900_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageField3.class)
		{
			var10 = ComponentFronosVillageField3.func_74900_a(component, list, rand, x, y, z, side, par8);
		}
		else if (var9 == ComponentFronosVillageHouse3.class)
		{
			var10 = ComponentFronosVillageHouse3.func_74921_a(component, list, rand, x, y, z, side, par8);
		}
		return (ComponentFronosVillage)var10;
	}

	private static ComponentFronosVillage getNextVillageComponent(ComponentFronosVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int par7)
	{
		int var8 = StructureFronosVillagePieces.func_75079_a(component.structureVillageWeightedPieceList);

		if (var8 <= 0)
		{
			return null;
		}
		else
		{
			int var9 = 0;

			while (var9 < 5)
			{
				++var9;
				int var10 = rand.nextInt(var8);
				Iterator<StructureFronosVillagePieceWeight> var11 = component.structureVillageWeightedPieceList.iterator();

				while (var11.hasNext())
				{
					StructureFronosVillagePieceWeight var12 = var11.next();
					var10 -= var12.villagePieceWeight;

					if (var10 < 0)
					{
						if (!var12.canSpawnMoreVillagePiecesOfType(par7) || var12 == component.structVillagePieceWeight && component.structureVillageWeightedPieceList.size() > 1)
						{
							break;
						}

						ComponentFronosVillage var13 = StructureFronosVillagePieces.func_75083_a(component, var12, list, rand, x, y, z, side, par7);

						if (var13 != null)
						{
							++var12.villagePiecesSpawned;
							component.structVillagePieceWeight = var12;

							if (!var12.canSpawnMoreVillagePieces())
							{
								component.structureVillageWeightedPieceList.remove(var12);
							}
							return var13;
						}
					}
				}
			}

			StructureBoundingBox var14 = ComponentFronosVillageTorch.func_175856_a(list, x, y, z, side);

			if (var14 != null)
			{
				return new ComponentFronosVillageTorch(component, par7, rand, var14, side);
			}
			else
			{
				return null;
			}
		}
	}

	private static StructureComponent getNextVillageStructureComponent(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int par7)
	{
		if (par7 > 50)
		{
			return null;
		}
		else if (Math.abs(x - component.getBoundingBox().minX) <= 112 && Math.abs(z - component.getBoundingBox().minZ) <= 112)
		{
			ComponentFronosVillage village = StructureFronosVillagePieces.getNextVillageComponent(component, list, rand, x, y, z, side, par7 + 1);

			if (village != null)
			{
				int i1 = (village.getBoundingBox().minX + village.getBoundingBox().maxX) / 2;
				int j1 = (village.getBoundingBox().minZ + village.getBoundingBox().maxZ) / 2;
				int k1 = village.getBoundingBox().maxX - village.getBoundingBox().minX;
				int l1 = village.getBoundingBox().maxZ - village.getBoundingBox().minZ;
				int i2 = k1 > l1 ? k1 : l1;

				if (component.getWorldChunkManager().areBiomesViable(i1, j1, i2 / 2 + 4, MapGenVillage.villageSpawnBiomes))
				{
					list.add(village);
					component.field_74932_i.add(village);
					return village;
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	private static StructureComponent getNextComponentVillagePath(ComponentFronosVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int par7)
	{
		if (par7 > 3 + component.terrainType)
		{
			return null;
		}
		else if (Math.abs(x - component.getBoundingBox().minX) <= 112 && Math.abs(z - component.getBoundingBox().minZ) <= 112)
		{
			StructureBoundingBox var8 = ComponentFronosVillagePathGen.func_74933_a(list, rand, x, y, z, side);

			if (var8 != null && var8.minY > 10)
			{
				ComponentFronosVillagePathGen var9 = new ComponentFronosVillagePathGen(component, par7, rand, var8, side);

				list.add(var9);
				component.field_74930_j.add(var9);
				return var9;
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	static StructureComponent getNextStructureComponent(ComponentFronosVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int par7)
	{
		return StructureFronosVillagePieces.getNextVillageStructureComponent(component, list, rand, x, y, z, side, par7);
	}

	static StructureComponent getNextStructureComponentVillagePath(ComponentFronosVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int par7)
	{
		return StructureFronosVillagePieces.getNextComponentVillagePath(component, list, rand, x, y, z, side, par7);
	}
}