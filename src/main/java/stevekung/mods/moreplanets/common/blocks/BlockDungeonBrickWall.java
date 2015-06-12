/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDungeonBrickWall extends BlockWallBaseMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockDungeonBrickWall(String name)
	{
		super(Material.rock);
		this.setHardness(4.0F);
		this.setResistance(40.0F);
		this.setDefaultState(this.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(VARIANT, BlockType.diona_dungeon_brick_wall));
		this.setUnlocalizedName(name);
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == state.withProperty(VARIANT, BlockType.sirius_b_dungeon_brick_wall))
		{
			return 15;
		}
		return 0;
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
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.withProperty(UP, Boolean.valueOf(this.canConnectTo(world, pos.up()))).withProperty(NORTH, Boolean.valueOf(this.canConnectTo(world, pos.north()))).withProperty(EAST, Boolean.valueOf(this.canConnectTo(world, pos.east()))).withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(world, pos.south()))).withProperty(WEST, Boolean.valueOf(this.canConnectTo(world, pos.west())));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {UP, NORTH, EAST, WEST, SOUTH, VARIANT});
	}

	public static enum BlockType implements IStringSerializable
	{
		diona_dungeon_brick_wall,
		polongnius_dungeon_brick_wall,
		nibiru_dungeon_brick_wall,
		koentus_dungeon_brick_wall,
		fronos_dungeon_brick_wall,
		kapteyn_b_dungeon_brick_wall,
		sirius_b_dungeon_brick_wall,
		mercury_dungeon_brick_wall;

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