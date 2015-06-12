/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

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

public class BlockSiriusB extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockSiriusB(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.sirius_b_surface_carbon_stone));
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == state.withProperty(VARIANT, BlockType.sulfur_block))
		{
			return 0;
		}
		return 15;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 10; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		IBlockState state = world.getBlockState(pos);
		return state == state.withProperty(VARIANT, BlockType.sulfur_block);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockSiriusB))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
		case 7:
			return 1.5F;
		case 2:
			return 2.0F;
		case 8:
			return 3.0F;
		case 9:
			return 4.0F;
		default:
			return 2.5F;
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_sulfur_ore))
		{
			return SiriusBItems.sirius_b_item;
		}
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_diamond_ore))
		{
			return Items.diamond;
		}
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_glowstone_ore))
		{
			return SiriusBItems.sirius_glowstone_dust;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1 || meta == 7)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 6)
		{
			return 5.0F;
		}
		if (meta == 8)
		{
			return 8.0F;
		}
		if (meta == 9)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_carbon_stone))
		{
			return 3;
		}
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_sulfur_ore))
		{
			return 2;
		}
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_diamond_ore) || state == state.withProperty(VARIANT, BlockType.sirius_b_glowstone_ore))
		{
			return 0;
		}
		return this.getMetaFromState(state);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_sulfur_ore))
		{
			return 3 + rand.nextInt(5);
		}
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_glowstone_ore))
		{
			return 2 + rand.nextInt(8);
		}
		return super.quantityDropped(state, fortune, rand);
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = (Integer)state.getValue(VARIANT);

		if (meta >= 3 && meta <= 6)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isTerraformable(World world, BlockPos pos, IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.sirius_b_surface_carbon_stone) || state == state.withProperty(VARIANT, BlockType.sirius_b_sub_surface_carbon_stone) || state == state.withProperty(VARIANT, BlockType.sirius_black_spot))
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
		sirius_b_surface_carbon_stone,
		sirius_b_sub_surface_carbon_stone,
		sirius_b_carbon_stone,
		sirius_b_carbon_cobblestone,
		sirius_b_sulfur_ore,
		sirius_b_diamond_ore,
		sirius_b_glowstone_ore,
		sirius_spot,
		sulfur_block,
		sirius_b_dungeon_brick;

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