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
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockJelly extends BlockBreakableMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockJelly(String name)
	{
		super(Material.cloth);
		this.setStepSound(MorePlanetsCore.soundTypeSmallSlime);
		this.setHardness(0.2F);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.grape_jelly_block));
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (entity.isSneaking())
		{
			super.onFallenUpon(world, pos, entity, fallDistance);
		}
		else
		{
			entity.fall(fallDistance, 0.0F);
		}
	}

	@Override
	public void onLanded(World world, Entity entity)
	{
		if (entity.isSneaking())
		{
			super.onLanded(world, entity);
		}
		else if (entity.motionY < 0.0D)
		{
			entity.motionY = -entity.motionY;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
		{
			double d = 0.4D + Math.abs(entity.motionY) * 0.2D;
			entity.motionX *= d;
			entity.motionZ *= d;
		}
		super.onEntityCollidedWithBlock(world, pos, entity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 8; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
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
		grape_jelly_block,
		raspberry_jelly_block,
		strawberry_jelly_block,
		berry_jelly_block,
		lime_jelly_block,
		orange_jelly_block,
		green_jelly_block,
		lemon_jelly_block;

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