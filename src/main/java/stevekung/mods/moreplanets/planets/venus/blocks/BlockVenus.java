/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class BlockVenus extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockVenus(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.venus_surface_rock));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 15; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		return world.getBlockState(pos) == world.getBlockState(pos).withProperty(VARIANT, BlockType.lead_block);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockVenus))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
		case 2:
		case 12:
		case 13:
			return 1.5F;
		case 3:
			return 2.0F;
		case 11:
			return 3.0F;
		case 14:
			return 4.0F;
		default:
			return 2.5F;
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
		if (meta >= 2 && meta <= 10 || meta == 12 || meta == 13)
		{
			return 5.0F;
		}
		if (meta == 11)
		{
			return 8.0F;
		}
		if (meta == 14)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (state == state.withProperty(VARIANT, BlockType.venus_sulfur_ore))
		{
			return SiriusBItems.sirius_b_item;
		}
		if (state == state.withProperty(VARIANT, BlockType.venus_coal_ore))
		{
			return Items.coal;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.venus_rock))
		{
			return 3;
		}
		if (state == state.withProperty(VARIANT, BlockType.venus_sulfur_ore))
		{
			return 2;
		}
		if (state == state.withProperty(VARIANT, BlockType.venus_coal_ore))
		{
			return 0;
		}
		return this.getMetaFromState(state);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (state == state.withProperty(VARIANT, BlockType.venus_sulfur_ore))
		{
			return 3 + rand.nextInt(5);
		}
		return super.quantityDropped(state, fortune, rand);
	}

	/*@Override
	public boolean isValueable(int meta)
	{
		if (meta >= 4 && meta <= 10)
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
		venus_surface_rock,
		venus_sub_surface_rock,
		venus_rock,
		venus_cobblestone,
		venus_sulfur_ore,
		venus_lead_ore,
		venus_tin_ore,
		venus_copper_ore,
		venus_coal_ore,
		venus_iron_ore,
		venus_gold_ore,
		lead_block,
		venus_stone_brick,
		cracked_venus_stone_brick,
		venus_dungeon_brick;

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