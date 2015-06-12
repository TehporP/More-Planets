/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class StructureKoentusVillagePieces
{
	public static ArrayList<StructureKoentusVillagePieceWeight> getStructureVillageWeightedPieceList(Random rand, int par1)
	{
		ArrayList<StructureKoentusVillagePieceWeight> var2 = new ArrayList<StructureKoentusVillagePieceWeight>();
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageHut.class, 5, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 5 + par1 * 3)));
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageField.class, 5, MathHelper.getRandomIntegerInRange(rand, 3 + par1, 5 + par1)));
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageHouse.class, 5, MathHelper.getRandomIntegerInRange(rand, 3 + par1, 4 + par1 * 2)));

		Iterator<StructureKoentusVillagePieceWeight> var3 = var2.iterator();

		while (var3.hasNext())
		{
			if (var3.next().villagePiecesLimit == 0)
			{
				var3.remove();
			}
		}
		return var2;
	}

	private static int func_75079_a(List<StructureKoentusVillagePieceWeight> list)
	{
		boolean var1 = false;
		int var2 = 0;
		StructureKoentusVillagePieceWeight var4;

		for (Iterator<StructureKoentusVillagePieceWeight> var3 = list.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight)
		{
			var4 = var3.next();

			if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit)
			{
				var1 = true;
			}
		}
		return var1 ? var2 : -1;
	}

	private static ComponentKoentusVillage func_75083_a(ComponentKoentusVillageStartPiece component, StructureKoentusVillagePieceWeight weight, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		Class<?> var9 = weight.villagePieceClass;
		Object var10 = null;

		if (var9 == ComponentKoentusVillageHut.class)
		{
			var10 = ComponentKoentusVillageHut.func_74908_a(component, list, x, y, z, side, type);
		}
		else if (var9 == ComponentKoentusVillageField.class)
		{
			var10 = ComponentKoentusVillageField.func_74900_a(component, list, x, y, z, side, type);
		}
		else if (var9 == ComponentKoentusVillageHouse.class)
		{
			var10 = ComponentKoentusVillageHouse.func_74921_a(component, list, rand, x, y, z, side, type);
		}
		return (ComponentKoentusVillage) var10;
	}

	private static ComponentKoentusVillage getNextVillageComponent(ComponentKoentusVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		int var8 = StructureKoentusVillagePieces.func_75079_a(component.structureVillageWeightedPieceList);

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
				Iterator<StructureKoentusVillagePieceWeight> var11 = component.structureVillageWeightedPieceList.iterator();

				while (var11.hasNext())
				{
					StructureKoentusVillagePieceWeight var12 = var11.next();
					var10 -= var12.villagePieceWeight;

					if (var10 < 0)
					{
						if (!var12.canSpawnMoreVillagePiecesOfType(type) || var12 == component.structVillagePieceWeight && component.structureVillageWeightedPieceList.size() > 1)
						{
							break;
						}

						ComponentKoentusVillage var13 = StructureKoentusVillagePieces.func_75083_a(component, var12, list, rand, x, y, z, side, type);

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

			StructureBoundingBox var14 = ComponentKoentusVillageTorch.func_74904_a(list, x, y, z, side);

			if (var14 != null)
			{
				return new ComponentKoentusVillageTorch(component, type, var14, side);
			}
			else
			{
				return null;
			}
		}
	}

	private static StructureComponent getNextVillageStructureComponent(ComponentKoentusVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		if (type > 50)
		{
			return null;
		}
		else if (Math.abs(x - component.getBoundingBox().minX) <= 112 && Math.abs(z - component.getBoundingBox().minZ) <= 112)
		{
			ComponentKoentusVillage var8 = StructureKoentusVillagePieces.getNextVillageComponent(component, list, rand, x, y, z, side, type + 1);

			if (var8 != null)
			{
				list.add(var8);
				component.field_74932_i.add(var8);
				return var8;
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	private static StructureComponent getNextComponentVillagePath(ComponentKoentusVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		if (type > 3 + component.terrainType)
		{
			return null;
		}
		else if (Math.abs(x - component.getBoundingBox().minX) <= 112 && Math.abs(z - component.getBoundingBox().minZ) <= 112)
		{
			StructureBoundingBox var8 = ComponentKoentusVillagePathGen.func_74933_a(list, rand, x, y, z, side);

			if (var8 != null && var8.minY > 10)
			{
				ComponentKoentusVillagePathGen var9 = new ComponentKoentusVillagePathGen(component, type, var8, side);

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

	static StructureComponent getNextStructureComponent(ComponentKoentusVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		return StructureKoentusVillagePieces.getNextVillageStructureComponent(component, list, rand, x, y, z, side, type);
	}

	static StructureComponent getNextStructureComponentVillagePath(ComponentKoentusVillageStartPiece component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		return StructureKoentusVillagePieces.getNextComponentVillagePath(component, list, rand, x, y, z, side, type);
	}
}