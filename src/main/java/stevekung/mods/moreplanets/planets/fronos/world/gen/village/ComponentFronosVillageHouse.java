/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHouse extends ComponentFronosVillage
{
	private int averageGroundLevel = -1;
	private boolean hasMadeChest;

	public ComponentFronosVillageHouse() {}

	public ComponentFronosVillageHouse(ComponentFronosVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
	}

	public static ComponentFronosVillageHouse func_74915_a(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 10, 6, 7, side);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentFronosVillageHouse(component, type, structureboundingbox, side) : null;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setBoolean("Chest", this.hasMadeChest);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.hasMadeChest = nbt.getBoolean("Chest");
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

		this.func_175804_a(world, box, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 0, 0, 9, 0, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 0, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 5, 0, 9, 5, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 1, 0, 2, 3, 0, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 1, 0, 0, 4, 0, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 1, 0, 3, 4, 0, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 1, 6, 0, 4, 6, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 3, 3, 1, box);
		this.func_175804_a(world, box, 3, 1, 2, 3, 3, 2, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 4, 1, 3, 5, 3, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 1, 1, 0, 3, 5, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 1, 6, 5, 3, 6, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 5, 1, 0, 5, 3, 0, FronosBlocks.fronos_fence.getDefaultState(), FronosBlocks.fronos_fence.getDefaultState(), false);
		this.func_175804_a(world, box, 9, 1, 0, 9, 3, 0, FronosBlocks.fronos_fence.getDefaultState(), FronosBlocks.fronos_fence.getDefaultState(), false);
		this.func_175804_a(world, box, 6, 1, 4, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175811_a(world, Blocks.flowing_lava.getDefaultState(), 7, 1, 5, box);
		this.func_175811_a(world, Blocks.flowing_lava.getDefaultState(), 8, 1, 5, box);
		this.func_175811_a(world, Blocks.iron_bars.getDefaultState(), 9, 2, 5, box);
		this.func_175811_a(world, Blocks.iron_bars.getDefaultState(), 9, 2, 4, box);
		this.func_175804_a(world, box, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 6, 1, 3, box);
		this.func_175811_a(world, FronosBlocks.candy_extractor_idle.getDefaultState(), 6, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.candy_extractor_idle.getDefaultState(), 6, 3, 3, box);
		this.func_175811_a(world, Blocks.double_stone_slab.getDefaultState(), 8, 1, 1, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 2, 2, 6, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 4, 2, 6, box);
		this.func_175811_a(world, Blocks.oak_fence.getDefaultState(), 2, 1, 4, box);
		this.func_175811_a(world, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 1, 1, 5, box);
		this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 5, box);
		this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 4, box);
		int i;
		int j;

		if (!this.hasMadeChest && box.func_175898_b(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5))))
		{
			this.hasMadeChest = true;
			this.func_180778_a(world, box, rand, 5, 1, 5, ChestGenHooks.getItems(VILLAGE_BLACKSMITH, rand), ChestGenHooks.getCount(VILLAGE_BLACKSMITH, rand));
		}

		for (i = 6; i <= 8; ++i)
		{
			if (this.func_175807_a(world, i, 0, -1, box).getBlock().getMaterial() == Material.air && this.func_175807_a(world, i, -1, -1, box).getBlock().getMaterial() != Material.air)
			{
				this.func_175811_a(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), i, 0, -1, box);
			}
		}

		for (i = 0; i < 7; ++i)
		{
			for (j = 0; j < 10; ++j)
			{
				this.clearCurrentPositionBlocksUpwards(world, j, 6, i, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), j, -1, i, box);
			}
		}
		this.spawnVillagers(world, box, 7, 1, 1, 1);
		return true;
	}
}