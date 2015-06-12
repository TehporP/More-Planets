/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureKoentusVillageStart extends StructureStart
{
	public StructureKoentusVillageStart() {}

	@SuppressWarnings("unchecked")
	public StructureKoentusVillageStart(World world, Random rand, int x, int z, int type)
	{
		super(x, z);
		ArrayList<StructureKoentusVillagePieceWeight> var6 = StructureKoentusVillagePieces.getStructureVillageWeightedPieceList(rand, type);
		ComponentKoentusVillageStartPiece var7 = new ComponentKoentusVillageStartPiece(world.getWorldChunkManager(), rand, (x << 4) + 2, (z << 4) + 2, var6, type);
		this.components.add(var7);
		var7.buildComponent(var7, this.components, rand);
		ArrayList<Object> var8 = var7.field_74930_j;
		ArrayList<Object> var9 = var7.field_74932_i;
		int var10;

		while (!var8.isEmpty() || !var9.isEmpty())
		{
			StructureComponent var11;

			if (var8.isEmpty())
			{
				var10 = rand.nextInt(var9.size());
				var11 = (StructureComponent) var9.remove(var10);
				var11.buildComponent(var7, this.components, rand);
			}
			else
			{
				var10 = rand.nextInt(var8.size());
				var11 = (StructureComponent) var8.remove(var10);
				var11.buildComponent(var7, this.components, rand);
			}
		}

		this.updateBoundingBox();
		var10 = 0;
		Iterator<StructureComponent> var13 = this.components.iterator();

		while (var13.hasNext())
		{
			StructureComponent var12 = var13.next();

			if (!(var12 instanceof ComponentKoentusVillageRoadPiece))
			{
				++var10;
			}
		}
	}

	@Override
	public boolean isSizeableStructure()
	{
		return true;
	}
}