/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public abstract class BlockRotatedMP extends BlockBaseMP
{
	public static PropertyEnum AXIS = PropertyEnum.create("axis", EnumAxis.class);

	public BlockRotatedMP(Material material)
	{
		super(material);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	public static enum EnumAxis implements IStringSerializable
	{
		X("x"),
		Y("y"),
		Z("z"),
		NONE("none");
		private String name;

		private EnumAxis(String name)
		{
			this.name = name;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static EnumAxis fromFacingAxis(Axis axis)
		{
			switch (SwitchAxis.AXIS_LOOKUP[axis.ordinal()])
			{
			case 1:
				return X;
			case 2:
				return Y;
			case 3:
				return Z;
			default:
				return NONE;
			}
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}

	static class SwitchAxis
	{
		static int[] AXIS_LOOKUP = new int[Axis.values().length];

		static
		{
			try
			{
				AXIS_LOOKUP[Axis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3)
			{
			}

			try
			{
				AXIS_LOOKUP[Axis.Y.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2)
			{
			}

			try
			{
				AXIS_LOOKUP[Axis.Z.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{
			}
		}
	}

	protected static class SwitchEnumAxis
	{
		public static int[] AXIS_LOOKUP = new int[EnumAxis.values().length];

		static
		{
			try
			{
				AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3)
			{
			}

			try
			{
				AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2)
			{
			}

			try
			{
				AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{
			}
		}
	}
}