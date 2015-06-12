/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;

public class BlockPhobos extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockPhobos(String name)
	{
		super(Material.rock);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.phobos_surface_rock));
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 8; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockPhobos))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
			return 1.25F;
		case 2:
			return 1.5F;
		default:
			return 2.0F;
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1)
		{
			return 6.0F;
		}
		if (meta >= 2)
		{
			return 5.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2)
		{
			return 3;
		}
		return meta;
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta >= 4 && meta <= 7)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isTerraformable(World world, BlockPos pos, IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 0 || meta == 1)
		{
			return true;
		}
		return false;
	}*/

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		phobos_surface_rock,
		phobos_sub_surface_rock,
		phobos_rock,
		phobos_cobblestone,
		phobos_tin_ore,
		phobos_copper_ore,
		phobos_iron_ore,
		phobos_desh_ore;

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
	}
}