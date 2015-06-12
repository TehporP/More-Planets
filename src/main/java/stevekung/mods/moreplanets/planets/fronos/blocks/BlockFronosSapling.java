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
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockSaplingMP;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenTreeMP;
import stevekung.mods.moreplanets.planets.fronos.world.gen.tree.WorldGenCoconutTree;

public class BlockFronosSapling extends BlockSaplingMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockFronosSapling(String name)
	{
		super();
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.coconut_sapling));
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 4; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.grass || block == Blocks.dirt || block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt || block.canSustainPlant(world, pos.down(), EnumFacing.UP, this);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		this.checkAndDropBlock(world, pos, state);

		if (!world.isRemote)
		{
			super.updateTick(world, pos, state, rand);

			if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(world, rand, pos, state);
			}
		}
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		BlockType type = (BlockType)state.getValue(VARIANT);
		Object obj = null;

		if (obj == null)
		{
			switch (type)
			{
			case coconut_sapling:
				obj = new WorldGenCoconutTree(35, 10, 1.3D);
				break;
			case red_maple_sapling:
				obj = new WorldGenTreeMP(4, FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 0, false, this);
				break;
			case yellow_maple_sapling:
				obj = new WorldGenTreeMP(4, FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 1, false, this);
				break;
			case purple_maple_sapling:
				obj = new WorldGenTreeMP(4, FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 2, false, this);
				break;
			}
		}

		if (obj != null)
		{
			world.setBlockToAir(pos);

			if (!((WorldGenerator)obj).generate(world, rand, pos))
			{
				world.setBlockState(pos, state, 2);
			}
		}
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos)
	{
		return false;
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
		coconut_sapling,
		red_maple_sapling,
		yellow_maple_sapling,
		purple_maple_sapling;

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