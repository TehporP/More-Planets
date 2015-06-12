/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public class BlockIoMagmaRock extends BlockBaseMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockIoMagmaRock(String name)
	{
		super(Material.rock);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.io_magma_rock));
		this.setUnlocalizedName(name);
		this.setHardness(3.0F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.io_magma_rock))
		{
			float f = 0.1F;
			return AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - f, pos.getZ() + 1);
		}
		return super.getCollisionBoundingBox(world, pos, state);
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == state.withProperty(VARIANT, BlockType.io_magma_rock))
		{
			if (side == EnumFacing.UP)
			{
				return true;
			}
		}
		return super.isFireSource(world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; ++i)
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
		{
			return;
		}
		if (state == state.withProperty(VARIANT, BlockType.io_magma_rock))
		{
			entity.setFire(10);
			entity.motionX *= 0.5D;
			entity.motionZ *= 0.5D;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (rand.nextInt(1) == 0)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + 1.1F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		ItemStack itemStack = player.getCurrentEquippedItem();
		player.addExhaustion(0.025F);

		if (state == state.withProperty(VARIANT, BlockType.io_magma_rock))
		{
			if (itemStack == null || !(itemStack.getItem() instanceof ItemPickaxe))
			{
				if (world.rand.nextInt(20) == 0)
				{
					world.setBlockState(pos, Blocks.flowing_lava.getDefaultState());
				}
			}
			if (itemStack != null && itemStack.getItem() instanceof ItemPickaxe)
			{
				this.dropBlockAsItem(world, pos, state, 0);
			}
		}
		super.harvestBlock(world, player, pos, state, tile);
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
		io_magma_rock,
		io_sulfur_rock;

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