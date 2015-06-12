/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.worldgen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class KoentusStructureVillageStart extends StructureStart
{
	public KoentusStructureVillageStart()
	{
	}

	@SuppressWarnings("unchecked")
	public KoentusStructureVillageStart(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		try
		{
			this.field_143024_c = par3;
			this.field_143023_d = par4;
		}
		catch (NoSuchFieldError e)
		{
			;
		}

		final ArrayList<KoentusStructureVillagePieceWeight> var6 = KoentusStructureVillagePieces.getStructureVillageWeightedPieceList(par2Random, par5);
		final KoentusComponentVillageStartPiece var7 = new KoentusComponentVillageStartPiece(par1World.getWorldChunkManager(), 0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, var6, par5);
		this.components.add(var7);
		var7.buildComponent(var7, this.components, par2Random);
		final ArrayList<Object> var8 = var7.field_74930_j;
		final ArrayList<Object> var9 = var7.field_74932_i;
		int var10;

		while (!var8.isEmpty() || !var9.isEmpty())
		{
			StructureComponent var11;

			if (var8.isEmpty())
			{
				var10 = par2Random.nextInt(var9.size());
				var11 = (StructureComponent) var9.remove(var10);
				var11.buildComponent(var7, this.components, par2Random);
			}
			else
			{
				var10 = par2Random.nextInt(var8.size());
				var11 = (StructureComponent) var8.remove(var10);
				var11.buildComponent(var7, this.components, par2Random);
			}
		}

		this.updateBoundingBox();
		var10 = 0;
		final Iterator<StructureComponent> var13 = this.components.iterator();

		while (var13.hasNext())
		{
			final StructureComponent var12 = var13.next();

			if (!(var12 instanceof KoentusComponentVillageRoadPiece))
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