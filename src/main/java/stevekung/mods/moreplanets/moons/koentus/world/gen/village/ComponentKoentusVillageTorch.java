/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.BlockDiona;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class ComponentKoentusVillageTorch extends ComponentKoentusVillage
{
	private int averageGroundLevel = -1;

	public ComponentKoentusVillageTorch() {}

	public ComponentKoentusVillageTorch(ComponentKoentusVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
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
	public static StructureBoundingBox func_74904_a(List list, int x, int y, int z, EnumFacing type)
	{
		StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 3, 4, 2, type);
		return StructureComponent.findIntersecting(list, var7) != null ? null : var7;
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.func_175804_a(world, box, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175811_a(world, KoentusBlocks.crystal_segment.getDefaultState(), 1, 0, 0, box);
		this.func_175811_a(world, KoentusBlocks.crystal_segment.getDefaultState(), 1, 1, 0, box);
		this.func_175811_a(world, KoentusBlocks.crystal_segment.getDefaultState(), 1, 2, 0, box);
		this.func_175811_a(world, DionaBlocks.diona_block.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.chiseled_quontonium), 1, 3, 0, box);
		this.func_175811_a(world, KoentusBlocks.white_crystal_torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 2, 4, 7, box);
		this.func_175811_a(world, KoentusBlocks.white_crystal_torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), 1, 4, 6, box);
		this.func_175811_a(world, KoentusBlocks.white_crystal_torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), 3, 4, 6, box);
		this.func_175811_a(world, KoentusBlocks.white_crystal_torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 4, 5, box);
		return true;
	}
}