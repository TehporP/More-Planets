/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import java.util.List;
import java.util.Random;

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
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;

public class BlockPluto extends BlockBasicMP/* implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockPluto(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.pluto_surface_rock));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 9; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockPluto))
		{
			return 0.0F;
		}

		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta == 0 || meta == 1 || meta == 8)//XXX
		{
			return 4.0F;
		}
		if (meta == 2)
		{
			return 4.5F;
		}
		if (meta == 3)
		{
			return 4.25F;
		}
		if (meta >= 4 && meta <= 7)
		{
			return 5.0F;
		}
		return 4.0F;
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 8)
		{
			return 5.0F;
		}
		if (meta >= 9 && meta <= 11)
		{
			return 8.0F;
		}
		if (meta == 12)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		/*if (state == 4)
		{
			return GCItems.meteoricIronRaw;
		}*/
		if (state == state.withProperty(VARIANT, BlockType.xeonium_gem_ore))
		{
			return PlutoItems.pluto_item;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.pluto_rock))
		{
			return 3;
		}
		if (state == state.withProperty(VARIANT, BlockType.pluto_meteoric_iron_ore) || state == state.withProperty(VARIANT, BlockType.xeonium_gem_ore))
		{
			return 0;
		}
		return this.getMetaFromState(state);
	}

	/*@Override
	public boolean isValueable(int metadata)
	{
		if (metadata >= 4 && metadata <= 7)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if ((meta == 0 || meta == 1) && world.getBlock(x, y + 1, z) instanceof BlockAir)
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
		pluto_surface_rock,
		pluto_sub_surface_rock,
		pluto_rock,
		pluto_cobblestone,
		pluto_meteoric_iron_ore,
		pluto_frozen_iron_ore,
		pluto_iron_ore,
		xeonium_gem_ore,
		pluto_dungeon_brick;

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