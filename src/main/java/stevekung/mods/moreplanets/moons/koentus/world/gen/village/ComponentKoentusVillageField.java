/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class ComponentKoentusVillageField extends ComponentKoentusVillage
{
	private int averageGroundLevel = -1;

	public ComponentKoentusVillageField() {}

	public ComponentKoentusVillageField(ComponentKoentusVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setInteger("AvgGroundLevel", this.averageGroundLevel);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.averageGroundLevel = nbt.getInteger("AvgGroundLevel");
	}

	@SuppressWarnings("rawtypes")
	public static ComponentKoentusVillageField func_74900_a(ComponentKoentusVillageStartPiece component, List list, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 13, 4, 9, side);
		return StructureComponent.findIntersecting(list, var8) == null ? new ComponentKoentusVillageField(component, type, var8, side) : null;
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
	{
		if (this.averageGroundLevel < 0)
		{
			this.averageGroundLevel = this.getAverageGroundLevel(world, box);

			if (this.averageGroundLevel < 0)
			{
				return true;
			}
			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
		}

		this.func_175804_a(world, box, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 1, 2, 0, 7, KoentusBlocks.crystal_dirt.getDefaultState(), KoentusBlocks.crystal_dirt.getDefaultState(), false);
		this.func_175804_a(world, box, 4, 0, 1, 5, 0, 7, KoentusBlocks.crystal_dirt.getDefaultState(), KoentusBlocks.crystal_dirt.getDefaultState(), false);
		this.func_175804_a(world, box, 7, 0, 1, 8, 0, 7, KoentusBlocks.crystal_dirt.getDefaultState(), KoentusBlocks.crystal_dirt.getDefaultState(), false);
		this.func_175804_a(world, box, 10, 0, 1, 11, 0, 7, KoentusBlocks.crystal_dirt.getDefaultState(), KoentusBlocks.crystal_dirt.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 0, 0, 0, 0, 8, KoentusBlocks.crystal_log.getDefaultState(), KoentusBlocks.crystal_log.getDefaultState(), false);
		this.func_175804_a(world, box, 6, 0, 0, 6, 0, 8, KoentusBlocks.crystal_log.getDefaultState(), KoentusBlocks.crystal_log.getDefaultState(), false);
		this.func_175804_a(world, box, 12, 0, 0, 12, 0, 8, KoentusBlocks.crystal_log.getDefaultState(), KoentusBlocks.crystal_log.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 0, 11, 0, 0, KoentusBlocks.crystal_log.getDefaultState(), KoentusBlocks.crystal_log.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 8, 11, 0, 8, KoentusBlocks.crystal_log.getDefaultState(), KoentusBlocks.crystal_log.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 0, 1, 3, 0, 7, Blocks.flowing_water.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
		this.func_175804_a(world, box, 9, 0, 1, 9, 0, 7, Blocks.flowing_water.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
		int var4;

		for (var4 = 1; var4 <= 7; ++var4)
		{
			for (int i = 1; i < 12; i++)
			{
				if (i % 3 == 0)
				{
				}
				else
				{
					if (rand.nextInt(3) == 0)
					{
						this.func_175808_b(world, KoentusBlocks.crystal_sapling.getDefaultState(), i, 1, var4, box);
					}
				}
			}
		}

		for (var4 = 0; var4 < 9; ++var4)
		{
			for (int var5 = 0; var5 < 13; ++var5)
			{
				this.clearCurrentPositionBlocksUpwards(world, var5, 4, var4, box);
				this.func_175811_a(world, KoentusBlocks.crystal_dirt.getDefaultState(), var5, -1, var4, box);
			}
		}
		return true;
	}
}