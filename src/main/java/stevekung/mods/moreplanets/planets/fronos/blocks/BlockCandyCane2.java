/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockRotatedMP;

public class BlockCandyCane2 extends BlockRotatedMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockCandyCane2(String name)
	{
		super(Material.cloth);
		this.setHardness(1.0F);
		this.setResistance(3.0F);
		this.setStepSound(soundTypeCloth);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.light_blue_candy_cane).withProperty(AXIS, EnumAxis.Y));
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 4; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockType.byMetadata(meta & 3));

		switch (meta & 12)
		{
		case 0:
			state = state.withProperty(AXIS, EnumAxis.Y);
			break;
		case 4:
			state = state.withProperty(AXIS, EnumAxis.X);
			break;
		case 8:
			state = state.withProperty(AXIS, EnumAxis.Z);
			break;
		default:
			state = state.withProperty(AXIS, EnumAxis.NONE);
		}
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		byte b = 0;
		int i = b | ((BlockType)state.getValue(VARIANT)).getMetadata();

		switch (SwitchEnumAxis.AXIS_LOOKUP[((EnumAxis)state.getValue(AXIS)).ordinal()])
		{
		case 1:
			i |= 4;
			break;
		case 2:
			i |= 8;
			break;
		case 3:
			i |= 12;
		}
		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT, AXIS });
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(this, 1, ((BlockType)state.getValue(VARIANT)).getMetadata());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).getMetadata();
	}

	public static enum BlockType implements IStringSerializable
	{
		light_blue_candy_cane(0, "light_blue_candy_cane"),
		blue_candy_cane(1, "blue_candy_cane"),
		red_candy_cane(2, "red_candy_cane"),
		purple_candy_cane(3, "purple_candy_cane");

		int meta;
		private String unlocalizedName;
		private static BlockType[] META_LOOKUP = new BlockType[values().length];

		private BlockType(int meta, String unlocalizedName)
		{
			this.meta = meta;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		public static BlockType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}

		static
		{
			BlockType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockType var3 = var0[var2];
				META_LOOKUP[var3.getMetadata()] = var3;
			}
		}
	}
}