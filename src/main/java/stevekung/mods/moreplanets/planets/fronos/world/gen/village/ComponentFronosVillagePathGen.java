/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentFronosVillagePathGen extends ComponentFronosVillageRoadPiece
{
	private int averageGroundLevel;

	public ComponentFronosVillagePathGen() {}

	public ComponentFronosVillagePathGen(ComponentFronosVillageStartPiece component, int type, Random rand, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
		this.averageGroundLevel = Math.max(box.getXSize(), box.getZSize());
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setInteger("Length", this.averageGroundLevel);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.averageGroundLevel = nbt.getInteger("Length");
	}

	@Override
	public void buildComponent(StructureComponent component, List list, Random rand)
	{
		boolean flag = false;
		int i;
		StructureComponent structurecomponent1;

		for (i = rand.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + rand.nextInt(5))
		{
			structurecomponent1 = this.getNextComponentNN((ComponentFronosVillageStartPiece)component, list, rand, 0, i);

			if (structurecomponent1 != null)
			{
				i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
				flag = true;
			}
		}

		for (i = rand.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + rand.nextInt(5))
		{
			structurecomponent1 = this.getNextComponentPP((ComponentFronosVillageStartPiece)component, list, rand, 0, i);

			if (structurecomponent1 != null)
			{
				i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
				flag = true;
			}
		}

		if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
			{
			case 1:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
				break;
			case 2:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
				break;
			case 3:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
				break;
			case 4:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
			}
		}

		if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
			{
			case 1:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
				break;
			case 2:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
				break;
			case 3:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
				break;
			case 4:
				StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)component, list, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
			}
		}
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
	{
		IBlockState iblockstate = this.func_175847_a(Blocks.gravel.getDefaultState());
		IBlockState iblockstate1 = this.func_175847_a(Blocks.cobblestone.getDefaultState());

		for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
		{
			for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
			{
				BlockPos blockpos = new BlockPos(i, 64, j);

				if (box.func_175898_b(blockpos))
				{
					blockpos = world.getTopSolidOrLiquidBlock(blockpos).down();
					world.setBlockState(blockpos, iblockstate, 2);
					world.setBlockState(blockpos.down(), iblockstate1, 2);
				}
			}
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static StructureBoundingBox func_74933_a(List list, Random rand, int x, int y, int z, EnumFacing side)
	{
		for (int var7 = 7 * MathHelper.getRandomIntegerInRange(rand, 3, 5); var7 >= 7; var7 -= 7)
		{
			StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 3, 3, var7, side);

			if (StructureComponent.findIntersecting(list, var8) == null)
			{
				return var8;
			}
		}
		return null;
	}
}