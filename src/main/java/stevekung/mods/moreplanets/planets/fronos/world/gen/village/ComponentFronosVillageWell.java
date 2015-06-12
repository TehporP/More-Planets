/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageWell extends ComponentFronosVillage
{
	private int averageGroundLevel = -1;

	public ComponentFronosVillageWell() {}

	public ComponentFronosVillageWell(ComponentFronosVillageStartPiece component, int type, Random rand, int par4, int par5)
	{
		super(component, type);
		this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);

		switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
		{
		case 0:
		case 2:
			this.boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
			break;
		default:
			this.boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
		}
	}

	@Override
	public void buildComponent(StructureComponent component, List list, Random rand)
	{
		StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
		StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
		StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
		StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
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
			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 3, 0);
		}
		this.func_175804_a(world, box, 1, 0, 1, 4, 12, 4, Blocks.cobblestone.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 12, 2, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 3, 12, 2, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 12, 3, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 3, 12, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 13, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 14, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 13, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 14, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 13, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 14, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 13, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 14, 4, box);
		this.func_175804_a(world, box, 1, 15, 1, 4, 15, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);

		for (int i = 0; i <= 5; ++i)
		{
			for (int j = 0; j <= 5; ++j)
			{
				if (j == 0 || j == 5 || i == 0 || i == 5)
				{
					this.func_175811_a(world, Blocks.gravel.getDefaultState(), j, 11, i, box);
					this.clearCurrentPositionBlocksUpwards(world, j, 12, i, box);
				}
			}
		}
		return true;
	}
}