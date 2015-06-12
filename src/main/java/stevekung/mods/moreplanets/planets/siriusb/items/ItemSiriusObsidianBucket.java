/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class ItemSiriusObsidianBucket extends ItemBucket
{
	private Block isFull;

	public ItemSiriusObsidianBucket(String name, Block block)
	{
		super(block);
		this.isFull = block;
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.setContainerItem(SiriusBItems.sirius_obsidian_bucket);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
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

					if (state == SiriusBBlocks.sirius_lava.getDefaultState())
					{
						world.setBlockToAir(pos);
						return this.fillBucket(itemStack, player, SiriusBItems.sirius_lava_bucket);
					}
				}
				else
				{
					if (this.isFull == Blocks.air)
					{
						return new ItemStack(SiriusBItems.sirius_obsidian_bucket);
					}

					BlockPos blockpos1 = pos.offset(moving.sideHit);

					if (!player.canPlayerEdit(blockpos1, moving.sideHit, itemStack))
					{
						return itemStack;
					}
					if (this.tryPlaceContainedLiquid(world, blockpos1) && !player.capabilities.isCreativeMode)
					{
						return new ItemStack(SiriusBItems.sirius_obsidian_bucket);
					}
				}
			}
			return itemStack;
		}
	}

	private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, Item fullBucket)
	{
		if (player.capabilities.isCreativeMode)
		{
			return emptyBuckets;
		}
		else if (--emptyBuckets.stackSize <= 0)
		{
			return new ItemStack(fullBucket);
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(fullBucket)))
			{
				player.dropPlayerItemWithRandomChoice(new ItemStack(fullBucket, 1, 0), false);
			}
			return emptyBuckets;
		}
	}

	@Override
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
				if (!world.isRemote && flag && !material.isLiquid())
				{
					world.destroyBlock(pos, true);
				}
				world.setBlockState(pos, this.isFull.getDefaultState(), 3);
				return true;
			}
		}
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpItemsTab;
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}
}