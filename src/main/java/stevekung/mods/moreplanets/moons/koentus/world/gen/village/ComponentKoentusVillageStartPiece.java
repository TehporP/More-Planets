/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.WorldChunkManager;

public class ComponentKoentusVillageStartPiece extends ComponentKoentusVillageWell
{
	public WorldChunkManager worldChunkMngr;
	public int terrainType;
	public StructureKoentusVillagePieceWeight structVillagePieceWeight;
	public ArrayList<StructureKoentusVillagePieceWeight> structureVillageWeightedPieceList;
	public ArrayList<Object> field_74932_i = new ArrayList<Object>();
	public ArrayList<Object> field_74930_j = new ArrayList<Object>();

	public ComponentKoentusVillageStartPiece() {}

	public ComponentKoentusVillageStartPiece(WorldChunkManager chunk, Random rand, int x, int z, ArrayList<StructureKoentusVillagePieceWeight> weight, int type)
	{
		super((ComponentKoentusVillageStartPiece) null, 0, rand, x, z);
		this.worldChunkMngr = chunk;
		this.structureVillageWeightedPieceList = weight;
		this.terrainType = type;
		this.startPiece = this;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setInteger("TerrainType", this.terrainType);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.terrainType = nbt.getInteger("TerrainType");
	}

	public WorldChunkManager getWorldChunkManager()
	{
		return this.worldChunkMngr;
	}
}