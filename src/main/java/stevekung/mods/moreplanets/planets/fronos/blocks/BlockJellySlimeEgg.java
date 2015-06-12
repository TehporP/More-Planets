/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockEggMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;

public class BlockJellySlimeEgg extends BlockEggMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockJellySlimeEgg(String name)
	{
		super();
		this.setStepSound(MorePlanetsCore.soundTypeSmallSlime);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.grape_jelly_slime_egg));
		this.setUnlocalizedName(name);
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
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)
		{
			EntityJellySlime slime = new EntityJellySlime(world);
			slime.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			slime.setJellySlimeType(this.getMetaFromState(world.getBlockState(pos)));
			world.spawnEntityInWorld(slime);
		}
		world.setBlockToAir(pos);
		world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "mob.slime.big", 1.0F, 1.0F);
		this.onBlockDestroyedByExplosion(world, pos, explosion);
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
		grape_jelly_slime_egg,
		raspberry_jelly_slime_egg,
		strawberry_jelly_slime_egg,
		berry_jelly_slime_egg,
		lime_jelly_slime_egg,
		orange_jelly_slime_egg,
		green_jelly_slime_egg,
		lemon_jelly_slime_egg;

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