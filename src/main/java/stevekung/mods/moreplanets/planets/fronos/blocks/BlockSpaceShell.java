/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public class BlockSpaceShell extends BlockBaseMP
{
	public static PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public BlockSpaceShell(String name)
	{
		super(Material.plants);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setBlockBounds(0.3F, 0.0F, 0.275F, 0.675F, 0.175F, 0.775F);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		return true;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	}

	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {COLOR});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		EnumDyeColor[] var4 = EnumDyeColor.values();
		int var5 = var4.length;

		for (int var6 = 0; var6 < var5; ++var6)
		{
			EnumDyeColor var7 = var4[var6];
			list.add(new ItemStack(item, 1, var7.getMetadata()));
		}
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos.down()).getBlock();

		if (block == null)
		{
			return false;
		}
		if (!block.isLeaves(world, pos.down()) && !block.isOpaqueCube())
		{
			return false;
		}
		return world.getBlockState(pos.down()).getBlock().getMaterial().blocksMovement();
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block par5)
	{
		this.checkForDrop(world, pos, state);
	}

	private boolean checkForDrop(World world, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(world, pos))
		{
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
			return false;
		}
		else
		{
			return true;
		}
	}

	private boolean canBlockStay(World world, BlockPos pos)
	{
		return !world.isAirBlock(pos.down());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return true;
	}
}