/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class BlockKapteynB extends BlockBasicMP /*implements IDetectableResource, IPlantableBlock, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockKapteynB(String name)
	{
		super(Material.rock);
		this.setTickRandomly(true);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.kapteyn_b_surface_ice));
		this.setUnlocalizedName(name);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockKapteynB))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
			return 1.0F;
		case 2:
			return 1.25F;
		case 3:
			return 1.5F;
		case 11:
		case 12:
			return 4.0F;
		default:
			return 2.0F;
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1 || meta >= 9 && meta <= 11)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 9)
		{
			return 5.0F;
		}
		if (meta == 12)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		IBlockState state = world.getBlockState(pos);
		return state == state.withProperty(VARIANT, BlockType.frozen_iron_block) || state == state.withProperty(VARIANT, BlockType.uranium_block);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int par3)
	{
		switch (this.getMetaFromState(state))
		{
		case 4:
			return KapteynBItems.namerium_crystal;
		case 6:
			return KapteynBItems.kapteyn_b_item;
		default:
			return Item.getItemFromBlock(this);
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2)
		{
			return 3;
		}
		if (meta == 6)
		{
			return 1;
		}
		if (meta == 6)
		{
			return 1;
		}
		if (meta == 4)
		{
			return 0;
		}
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 13; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta >= 4 && meta <= 10)
		{
			return true;
		}
		return false;
	}*/

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta == 0 || meta == 1)
		{
			return true;
		}
		if (plant.getPlant(world, pos.up()) instanceof BlockBush)
		{
			return true;
		}
		return false;
	}

	/*@Override
	public int requiredLiquidBlocksNearby()
	{
		return 4;
	}

	@Override
	public boolean isPlantable(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 0 || meta == 1)
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
		kapteyn_b_surface_ice,
		kapteyn_b_sub_surface_ice,
		kapteyn_b_hardened_ice,
		kapteyn_b_cracked_ice,
		namerium_ore,
		frozen_iron_ore,
		uranium_ore,
		kapteyn_b_tin_ore,
		kapteyn_b_copper_ore,
		namerium_block,
		frozen_iron_block,
		uranium_block,
		kapteyn_b_dungeon_brick;

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