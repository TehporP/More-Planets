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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHouse4 extends ComponentFronosVillage
{
	private int averageGroundLevel = -1;
	private boolean isRoofAccessible;

	public ComponentFronosVillageHouse4() {}

	public ComponentFronosVillageHouse4(ComponentFronosVillageStartPiece component, int type, Random rand, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
		this.isRoofAccessible = rand.nextBoolean();
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setBoolean("Terrace", this.isRoofAccessible);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.isRoofAccessible = nbt.getBoolean("Terrace");
	}

	public static ComponentFronosVillageHouse4 func_74912_a(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 5, 6, 5, side);
		return StructureComponent.findIntersecting(list, structureboundingbox) != null ? null : new ComponentFronosVillageHouse4(component, type, rand, structureboundingbox, side);
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

		this.func_175804_a(world, box, 0, 0, 0, 4, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 0, 4, 4, 4, FronosBlocks.fronos_log.getDefaultState(), FronosBlocks.fronos_log.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 4, 1, 3, 4, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 1, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 2, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 3, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 1, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 2, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 3, 0, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 1, 4, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 2, 4, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 0, 3, 4, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 1, 4, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 2, 4, box);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 4, 3, 4, box);
		this.func_175804_a(world, box, 0, 1, 1, 0, 3, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 4, 1, 1, 4, 3, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 1, 4, 3, 3, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 2, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 4, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 1, 1, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 1, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 1, 3, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 2, 3, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 3, 3, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 3, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 3, 1, 0, box);

		if (this.func_175807_a(world, 2, 0, -1, box).getBlock().getMaterial() == Material.air && this.func_175807_a(world, 2, -1, -1, box).getBlock().getMaterial() != Material.air)
		{
			this.func_175811_a(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, box);
		}

		this.func_175804_a(world, box, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

		if (this.isRoofAccessible)
		{
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 0, 5, 0, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 5, 0, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 2, 5, 0, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 3, 5, 0, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 5, 0, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 0, 5, 4, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 1, 5, 4, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 2, 5, 4, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 3, 5, 4, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 5, 4, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 5, 1, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 5, 2, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 4, 5, 3, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 0, 5, 1, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 0, 5, 2, box);
			this.func_175811_a(world, FronosBlocks.fronos_fence.getDefaultState(), 0, 5, 3, box);
		}

		int i;

		if (this.isRoofAccessible)
		{
			i = this.getMetadataWithOffset(Blocks.ladder, 3);
			this.func_175811_a(world, Blocks.ladder.getStateFromMeta(i), 3, 1, 3, box);
			this.func_175811_a(world, Blocks.ladder.getStateFromMeta(i), 3, 2, 3, box);
			this.func_175811_a(world, Blocks.ladder.getStateFromMeta(i), 3, 3, 3, box);
			this.func_175811_a(world, Blocks.ladder.getStateFromMeta(i), 3, 4, 3, box);
		}

		this.func_175811_a(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);

		for (i = 0; i < 5; ++i)
		{
			for (int j = 0; j < 5; ++j)
			{
				this.clearCurrentPositionBlocksUpwards(world, j, 6, i, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), j, -1, i, box);
			}
		}
		this.spawnVillagers(world, box, 1, 1, 2, 1);
		return true;
	}
}