/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHut extends ComponentFronosVillage
{
	private boolean isTallHouse;
	private int tablePosition;
	private int averageGroundLevel = -1;

	public ComponentFronosVillageHut() {}

	public ComponentFronosVillageHut(ComponentFronosVillageStartPiece component, int type, Random rand, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
		this.isTallHouse = rand.nextBoolean();
		this.tablePosition = rand.nextInt(3);
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setInteger("T", this.tablePosition);
		nbt.setBoolean("C", this.isTallHouse);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.tablePosition = nbt.getInteger("T");
		this.isTallHouse = nbt.getBoolean("C");
	}

	public static ComponentFronosVillageHut func_74908_a(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 4, 6, 5, side);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentFronosVillageHut(component, type, rand, structureboundingbox, side) : null;
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
			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
		}

		this.func_175804_a(world, box, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 0, 0, 3, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 1, 2, 0, 3, FronosBlocks.fronos_dirt.getDefaultState(), FronosBlocks.fronos_dirt.getDefaultState(), false);

		if (this.isTallHouse)
		{
			this.func_175804_a(world, box, 1, 4, 1, 2, 4, 3, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		}
		else
		{
			this.func_175804_a(world, box, 1, 5, 1, 2, 5, 3, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		}

		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 1, 4, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 2, 4, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 1, 4, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 2, 4, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 4, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 4, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 3, 4, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 3, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 3, 4, 3, box);
		this.func_175804_a(world, box, 0, 1, 0, 0, 3, 0, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 1, 0, 3, 3, 0, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 1, 4, 0, 3, 4, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 1, 4, 3, 3, 4, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 1, 1, 0, 3, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 1, 1, 3, 3, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 1, 0, 2, 3, 0, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 1, 4, 2, 3, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 3, 2, 2, box);

		if (this.tablePosition > 0)
		{
			this.func_175811_a(world, Blocks.oak_fence.getDefaultState(), this.tablePosition, 1, 3, box);
			this.func_175811_a(world, Blocks.wooden_pressure_plate.getDefaultState(), this.tablePosition, 2, 3, box);
		}

		this.func_175811_a(world, Blocks.air.getDefaultState(), 1, 1, 0, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 1, 2, 0, box);
		this.func_175810_a(world, box, rand, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

		if (this.func_175807_a(world, 1, 0, -1, box).getBlock().getMaterial() == Material.air && this.func_175807_a(world, 1, -1, -1, box).getBlock().getMaterial() != Material.air)
		{
			this.func_175811_a(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, box);
		}

		for (int i = 0; i < 5; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				this.clearCurrentPositionBlocksUpwards(world, j, 6, i, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), j, -1, i, box);
			}
		}
		this.spawnVillagers(world, box, 1, 1, 2, 1);
		return true;
	}
}