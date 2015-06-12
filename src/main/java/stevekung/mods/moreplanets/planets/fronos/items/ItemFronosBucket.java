/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemFronosBucket extends ItemBaseMP
{
	private Block isFull;

	public ItemFronosBucket(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.setContainerItem(Items.bucket);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		this.isFull = this.getLiquidFromMeta(itemStack.getItemDamage());
		boolean flag = this.isFull == Blocks.air;
		MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, flag);

		if (moving == null)
		{
			return itemStack;
		}
		else
		{
			if (moving.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				BlockPos pos = moving.getBlockPos();

				if (!world.isBlockModifiable(player, pos))
				{
					return itemStack;
				}

				if (flag)
				{
					if (!player.canPlayerEdit(pos.offset(moving.sideHit), moving.sideHit, itemStack))
					{
						return itemStack;
					}

					IBlockState state = world.getBlockState(pos);
					int meta = itemStack.getItemDamage();

					if (meta == 0)
					{
						if (state == FronosBlocks.coconut_milk.getDefaultState().withProperty(BlockFluidBase.LEVEL, 0))
						{
							world.setBlockToAir(pos);
							return this.fillBucket(itemStack, player, new ItemStack(this, 1, 0));
						}
					}
					else if (meta == 1)
					{
						if (state == FronosBlocks.mineral_water.getDefaultState().withProperty(BlockFluidBase.LEVEL, 0))
						{
							world.setBlockToAir(pos);
							return this.fillBucket(itemStack, player, new ItemStack(this, 1, 1));
						}
					}
					else if (meta == 2)
					{
						if (state == FronosBlocks.ovantine.getDefaultState().withProperty(BlockFluidBase.LEVEL, 0))
						{
							world.setBlockToAir(pos);
							return this.fillBucket(itemStack, player, new ItemStack(this, 1, 2));
						}
					}
					else if (meta == 3)
					{
						if (state == FronosBlocks.tea.getDefaultState().withProperty(BlockFluidBase.LEVEL, 0))
						{
							world.setBlockToAir(pos);
							return this.fillBucket(itemStack, player, new ItemStack(this, 1, 3));
						}
					}
					else if (meta == 4)
					{
						if (state == FronosBlocks.caramel.getStateFromMeta(3))
						{
							world.setBlockToAir(pos);
							return this.fillBucket(itemStack, player, new ItemStack(this, 1, 4));
						}
					}
				}
				else
				{
					if (this.isFull == Blocks.air)
					{
						return new ItemStack(Items.bucket);
					}
					if (!player.canPlayerEdit(pos.offset(moving.sideHit), moving.sideHit, itemStack))
					{
						return itemStack;
					}
					if (this.tryPlaceContainedLiquid(world, pos.offset(moving.sideHit)) && !player.capabilities.isCreativeMode)
					{
						return new ItemStack(Items.bucket);
					}
				}
			}
			return itemStack;
		}
	}

	private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, ItemStack fullBucket)
	{
		if (player.capabilities.isCreativeMode)
		{
			return emptyBuckets;
		}
		else if (--emptyBuckets.stackSize <= 0)
		{
			return fullBucket;
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(fullBucket))
			{
				player.dropPlayerItemWithRandomChoice(fullBucket, false);
			}
			return emptyBuckets;
		}
	}

	private Block getLiquidFromMeta(int meta)
	{
		switch (meta)
		{
		case 0:
		default:
			return FronosBlocks.coconut_milk;
		case 1:
			return FronosBlocks.mineral_water;
		case 2:
			return FronosBlocks.ovantine;
		case 3:
			return FronosBlocks.tea;
		case 4:
			return FronosBlocks.caramel;
		}
	}

	public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
	{
		if (this.isFull == Blocks.air)
		{
			return false;
		}
		else
		{
			Material material = world.getBlockState(pos).getBlock().getMaterial();
			boolean flag = !material.isSolid();

			if (!world.isAirBlock(pos) && !flag)
			{
				return false;
			}
			else
			{
				if (world.provider.doesWaterVaporize() && this.isFull.getMaterial() == Material.water)
				{
					world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

					for (int i = 0; i < 8; ++i)
					{
						world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
					}
				}
				else
				{
					if (!world.isRemote && flag && !material.isLiquid())
					{
						world.destroyBlock(pos, true);
					}
					world.setBlockState(pos, this.isFull.getDefaultState(), 3);
				}
				return true;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "coconut_milk_bucket", "mineral_water_bucket", "ovantine_bucket", "tea_bucket", "caramel_bucket" };
	}
}