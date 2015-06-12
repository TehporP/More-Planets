/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class ComponentKoentusVillagePathGen extends ComponentKoentusVillageRoadPiece
{
	private int averageGroundLevel;

	public ComponentKoentusVillagePathGen() {}

	public ComponentKoentusVillagePathGen(ComponentKoentusVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
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
		nbt.setInteger("AvgGroundLevel", this.averageGroundLevel);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.averageGroundLevel = nbt.getInteger("AvgGroundLevel");
	}

	@Override
	public void buildComponent(StructureComponent component, List list, Random rand)
	{
		boolean var4 = false;
		int var5;
		StructureComponent var6;

		for (var5 = rand.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + rand.nextInt(5))
		{
			var6 = this.getNextComponentNN((ComponentKoentusVillageStartPiece) component, list, rand, 0, var5);

			if (var6 != null)
			{
				var5 += Math.max(var6.getBoundingBox().getXSize(), var6.getBoundingBox().getZSize());
				var4 = true;
			}
		}

		for (var5 = rand.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + rand.nextInt(5))
		{
			var6 = this.getNextComponentPP((ComponentKoentusVillageStartPiece) component, list, rand, 0, var5);

			if (var6 != null)
			{
				var5 += Math.max(var6.getBoundingBox().getXSize(), var6.getBoundingBox().getZSize());
				var4 = true;
			}
		}

		if (var4 && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
			{
			case 0:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
				break;
			case 1:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
				break;
			case 2:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
				break;
			case 3:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
			}
		}

		if (var4 && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
			{
			case 0:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
				break;
			case 1:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
				break;
			case 2:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
				break;
			case 3:
				StructureKoentusVillagePieces.getNextStructureComponentVillagePath((ComponentKoentusVillageStartPiece) component, list, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
			}
		}
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

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
	{
		IBlockState iblockstate = this.func_175847_a(KoentusBlocks.koentus_block.getDefaultState());

		for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
		{
			for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
			{
				BlockPos blockpos = new BlockPos(i, 64, j);

				if (box.func_175898_b(blockpos))
				{
					blockpos = world.getTopSolidOrLiquidBlock(blockpos).down();
					world.setBlockState(blockpos, iblockstate, 2);
				}
			}
		}
		return true;
	}
}