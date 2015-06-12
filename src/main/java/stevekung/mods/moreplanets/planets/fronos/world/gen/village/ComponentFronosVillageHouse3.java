/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHouse3 extends ComponentFronosVillage
{
	private int averageGroundLevel = -1;

	public ComponentFronosVillageHouse3() {}

	public ComponentFronosVillageHouse3(ComponentFronosVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
	}

	public static ComponentFronosVillageHouse3 func_74921_a(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 9, 7, 12, side);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentFronosVillageHouse3(component, type, structureboundingbox, side) : null;
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

		this.func_175804_a(world, box, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 2, 0, 5, 8, 0, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 1, 7, 0, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 8, 0, 0, 8, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 0, 7, 2, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 5, 2, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 2, 0, 6, 2, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 0, 10, 7, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 2, 0, 7, 3, 0, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 2, 5, 2, 3, 5, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 1, 8, 4, 1, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 4, 3, 4, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 5, 2, 8, 5, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 0, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 0, 4, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 4, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 4, 4, box);
		int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
		int k;
		int l;

		for (k = -1; k <= 2; ++k)
		{
			for (l = 0; l <= 8; ++l)
			{
				this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(i), l, 4 + k, k, box);

				if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6))
				{
					this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(j), l, 4 + k, 5 - k, box);
				}
			}
		}

		this.func_175804_a(world, box, 3, 4, 5, 3, 4, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 7, 4, 2, 7, 4, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 4, 5, 4, 4, 5, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 6, 5, 4, 6, 5, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 5, 6, 3, 5, 6, 10, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		k = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
		int i1;

		for (l = 4; l >= 1; --l)
		{
			this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), l, 2 + l, 7 - l, box);

			for (i1 = 8 - l; i1 <= 10; ++i1)
			{
				this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(k), l, 2 + l, i1, box);
			}
		}

		l = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 6, 6, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 7, 5, 4, box);
		this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(l), 6, 6, 4, box);
		int j1;

		for (i1 = 6; i1 <= 8; ++i1)
		{
			for (j1 = 5; j1 <= 10; ++j1)
			{
				this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(l), i1, 12 - i1, j1, box);
			}
		}

		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 2, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 4, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 5, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 6, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 1, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 2, 5, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 6, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 7, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 8, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 9, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 2, 2, 6, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 2, 2, 7, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 2, 2, 8, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 2, 2, 9, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 4, 4, 10, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 5, 4, 10, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 6, 4, 10, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 5, 5, 10, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 1, 0, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 2, 0, box);
		this.func_175811_a(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);
		this.func_175810_a(world, box, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
		this.func_175804_a(world, box, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

		if (this.func_175807_a(world, 2, 0, -1, box).getBlock().getMaterial() == Material.air && this.func_175807_a(world, 2, -1, -1, box).getBlock().getMaterial() != Material.air)
		{
			this.func_175811_a(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, box);
		}

		for (i1 = 0; i1 < 5; ++i1)
		{
			for (j1 = 0; j1 < 9; ++j1)
			{
				this.clearCurrentPositionBlocksUpwards(world, j1, 7, i1, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), j1, -1, i1, box);
			}
		}

		for (i1 = 5; i1 < 11; ++i1)
		{
			for (j1 = 2; j1 < 9; ++j1)
			{
				this.clearCurrentPositionBlocksUpwards(world, j1, 7, i1, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), j1, -1, i1, box);
			}
		}
		this.spawnVillagers(world, box, 4, 1, 2, 2);
		return true;
	}
}