/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockLeavesMP;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class BlockOrangeLeaves extends BlockLeavesMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockOrangeLeaves(String name)
	{
		super();
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.orange_leaves_stage_1));
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
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);

		if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_1) && rand.nextInt(25) == 0)
		{
			world.setBlockState(pos, state.withProperty(VARIANT, BlockType.orange_leaves_stage_2), 3);
		}
		else if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_2) && rand.nextInt(20) == 0)
		{
			world.setBlockState(pos, state.withProperty(VARIANT, BlockType.orange_leaves_stage_3), 3);
		}
		else if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_3) && rand.nextInt(16) == 0)
		{
			world.setBlockState(pos, state.withProperty(VARIANT, BlockType.orange_leaves_stage_4), 3);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
	{
		if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_4))
		{
			world.setBlockState(pos, state.withProperty(VARIANT, BlockType.orange_leaves_stage_1).withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, false), 3);
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(NibiruItems.space_fruits, 1, 1));

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(item);

				if (!(player instanceof FakePlayer))
				{
					item.onCollideWithPlayer(player);
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(NibiruBlocks.nibiru_sapling);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 1;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		if (world.isRemote)
		{
			return;
		}
		if (world.rand.nextInt(20) == 0)
		{
			Item item = this.getItemDropped(state, world.rand, fortune);
			Block.spawnAsEntity(world, pos, new ItemStack(item, 1, 1));
		}
		if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_4))
		{
			Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.space_fruits, 1, 1));
		}
		else if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_3) && world.rand.nextInt(16) == 0)
		{
			Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.space_fruits, 1, 1));
		}
		else if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_2) && world.rand.nextInt(48) == 0)
		{
			Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.space_fruits, 1, 1));
		}
		else if (state == state.withProperty(VARIANT, BlockType.orange_leaves_stage_1) && world.rand.nextInt(80) == 0)
		{
			Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.space_fruits, 1, 1));
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT, DECAYABLE, CHECK_DECAY });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, this.getWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	public BlockType getWoodType(int meta)
	{
		return BlockType.byMetadata((meta & 3) % 4);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		byte b0 = 0;
		int i = b0 | ((BlockType)state.getValue(VARIANT)).ordinal();

		if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
		{
			i |= 4;
		}
		if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
		{
			i |= 8;
		}
		return i;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)) & 3);
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, ((BlockType)world.getBlockState(pos).getValue(VARIANT)).getMetadata()));
		return ret;
	}

	public static enum BlockType implements IStringSerializable
	{
		orange_leaves_stage_1(0),
		orange_leaves_stage_2(1),
		orange_leaves_stage_3(2),
		orange_leaves_stage_4(3);

		private int meta;
		private static BlockType[] META_LOOKUP = new BlockType[values().length];

		private BlockType(int meta)
		{
			this.meta = meta;
		}

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

		public int getMetadata()
		{
			return this.meta;
		}

		public static BlockType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		static
		{
			BlockType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockType var3 = var0[var2];
				META_LOOKUP[var3.getMetadata()] = var3;
			}
		}
	}
}