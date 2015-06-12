/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;

public class BlockDiona extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockDiona(String name)
	{
		super(Material.rock);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.diona_surface_rock));
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 16; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		IBlockState state = world.getBlockState(pos);

		if (entity instanceof EntityLivingBase)
		{
			if (state == state.withProperty(VARIANT, BlockType.diona_dungeon_brick))
			{
				world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pos.getX() + world.rand.nextFloat(), pos.getY() + 1.0F, pos.getZ() + world.rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(state.withProperty(VARIANT, BlockType.diona_dungeon_brick))});
			}
		}
		super.onEntityCollidedWithBlock(world, pos, entity);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockDiona))
		{
			return 0;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
			return 1.25F;
		case 2:
			return 1.5F;
		case 10:
		case 11:
		case 15:
			return 4.0F;
		case 12:
		case 13:
		case 14:
			return 2.5F;
		default:
			return 2.0F;
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1 || meta >= 12 && meta <= 14)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 9)
		{
			return 5.0F;
		}
		if (meta == 10 || meta == 11)
		{
			return 8.0F;
		}
		if (meta == 15)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		int meta = this.getMetaFromState(state);

		/*if (meta == 8)
		{
			return GCItems.basicItem;
		}*/
		return Item.getItemFromBlock(this);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		return true;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2)
		{
			return 3;
		}
		if (meta == 8)
		{
			return 2;
		}
		return meta;
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta >= 4 && meta <= 9)
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
		diona_surface_rock,
		diona_sub_surface_rock,
		diona_rock,
		diona_cobblestone,
		quontonium_ore,
		fronisium_ore,
		diona_tin_ore,
		diona_copper_ore,
		diona_silicon_ore,
		diona_aluminum_ore,
		quontonium_block,
		fronisium_block,
		smooth_quontonium,
		quontonium_brick,
		chiseled_quontonium,
		diona_dungeon_brick;

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