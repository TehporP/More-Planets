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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;

public class BlockDandelion extends BlockFlowerMP
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockDandelion(String name)
	{
		super();
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.young_orange_dandelion));
		this.setUnlocalizedName(name);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		BlockType type = (BlockType)state.getValue(VARIANT);

		if (!world.isRemote)
		{
			if (world.isDaytime() && !world.isRaining())
			{
				if (type == BlockType.young_orange_dandelion)
				{
					world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.orange_dandelion), 3);
				}
				if (type == BlockType.young_pink_dandelion)
				{
					world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.pink_dandelion), 3);
				}
				if (type == BlockType.young_purple_dandelion)
				{
					world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.purple_dandelion), 3);
				}
			}
			if (world.isRaining())
			{
				if (rand.nextInt(20) == 0)
				{
					if (type == BlockType.orange_dandelion)
					{
						world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_orange_dandelion), 3);
					}
					if (type == BlockType.pink_dandelion)
					{
						world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_pink_dandelion), 3);
					}
					if (type == BlockType.purple_dandelion)
					{
						world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_purple_dandelion), 3);
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.randomDisplayTick(world, pos, state, rand);
		BlockType type = (BlockType)state.getValue(VARIANT);

		if (type == BlockType.orange_dandelion)
		{
			if (rand.nextInt(20) == 0)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.ORANGE_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
			}
			else if (rand.nextInt(2) == 0)
			{
				if (world.isRaining())
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.ORANGE_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
				}
			}
		}
		else if (type == BlockType.pink_dandelion)
		{
			if (rand.nextInt(20) == 0)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PINK_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
			}
			else if (rand.nextInt(2) == 0)
			{
				if (world.isRaining())
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PINK_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
				}
			}
		}
		else if (type == BlockType.purple_dandelion)
		{
			if (rand.nextInt(20) == 0)
			{
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PURPLE_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
			}
			else if (rand.nextInt(2) == 0)
			{
				if (world.isRaining())
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.PURPLE_DANDELION, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat());
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 6; ++i)
		{
			list.add(new ItemStack(this, 1, i));
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
	{
		BlockType type = (BlockType)state.getValue(VARIANT);

		if (player.isPotionActive(Potion.regeneration.id))
		{
			return false;
		}
		if (type == BlockType.orange_dandelion)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_orange_dandelion), 3);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
			return true;
		}
		else if (type == BlockType.pink_dandelion)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_pink_dandelion), 3);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
			return true;
		}
		else if (type == BlockType.purple_dandelion)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.young_purple_dandelion), 3);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
			return true;
		}
		return false;
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt;
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
		young_orange_dandelion,
		young_pink_dandelion,
		young_purple_dandelion,
		orange_dandelion,
		pink_dandelion,
		purple_dandelion;

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