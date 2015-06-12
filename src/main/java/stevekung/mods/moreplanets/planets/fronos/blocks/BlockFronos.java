/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

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
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockFronos extends BlockBasicMP /*implements IDetectableResource*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockFronos(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.fronos_rock));
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
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockFronos))
		{
			return 0;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 11:
		case 12:
		case 13:
			return 1.5F;
		case 14:
			return 4.0F;
		default:
			return 2.0F;
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1 || meta >= 11 && meta <= 13)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 10)
		{
			return 5.0F;
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
		int meta = this.getMetaFromState(state);

		if (meta == 3)
		{
			return Items.coal;
		}
		if (meta == 7)
		{
			return Items.dye;
		}
		if (meta == 8 || meta == 9)
		{
			return FronosItems.fronos_item;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 0)
		{
			return 1;
		}
		if (meta == 3 || meta == 8)
		{
			return 0;
		}
		if (meta == 7)
		{
			return 4;
		}
		if (meta == 9)
		{
			return 2;
		}
		return meta;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (state == state.withProperty(VARIANT, BlockType.fronos_lapis_ore))
		{
			return 4 + rand.nextInt(5);
		}
		return super.quantityDropped(state, fortune, rand);
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		if (this.getMetaFromState(state) >= 2 && this.getMetaFromState(state) <= 10)
		{
			return true;
		}
		return false;
	}*/

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float par6, int par7)
	{
		int var8 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

		if (state == state.withProperty(VARIANT, BlockType.fronos_lapis_ore))
		{
			this.dropXpOnBlockBreak(world, pos, var8);
		}
		super.dropBlockAsItemWithChance(world, pos, state, par6, par7);
	}

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
		fronos_rock,
		fronos_cobblestone,
		fronos_iron_ore,
		fronos_coal_ore,
		fronos_aluminum_ore,
		fronos_tin_ore,
		fronos_copper_ore,
		fronos_lapis_ore,
		mineral_crystal_ore,
		black_diamond_ore,
		iridium_ore,
		fronos_stone_brick,
		cracked_fronos_stone_brick,
		chiseled_fronos_stone_brick,
		fronos_dungeon_brick;

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