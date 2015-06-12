/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockOysterMP;
import stevekung.mods.moreplanets.common.items.ItemFoodMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCup;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class ItemCup extends ItemFoodMP
{
	public ItemCup(String name)
	{
		super();
		this.setMaxStackSize(16);
		this.setUnlocalizedName(name);
		this.setHasSubtypes(true);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
	{
		int meta = itemStack.getItemDamage();

		if (!player.capabilities.isCreativeMode)
		{
			--itemStack.stackSize;
		}
		if (!world.isRemote)
		{
			if (meta == 1)
			{
				if (!player.isPotionActive(Potion.regeneration) || !player.isPotionActive(Potion.moveSpeed))
				{
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 3));
				}
			}
			else if (meta == 2)
			{
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 2));
			}
			else if (meta == 3)
			{
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 2400, 2));
			}
			else if (meta == 4)
			{
				player.addPotionEffect(new PotionEffect(Potion.saturation.id, 120, 3));
			}
			else if (meta == 5)
			{
				player.addPotionEffect(new PotionEffect(Potion.resistance.id, 1200, 1));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 3));
			}
			else if (meta == 6)
			{
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
			}
		}

		player.getFoodStats().addStats(this, itemStack);
		world.playSoundAtEntity(player, "random.drink", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(itemStack, world, player);

		if (meta >= 1)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(this)))
			{
				player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 0), false);
			}
		}
		return itemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		if (itemStack.getItemDamage() >= 1)
		{
			return 32;
		}
		return 0;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (itemStack.getItemDamage() >= 1)
		{
			player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}
		if (moving == null)
		{
			return itemStack;
		}
		else
		{
			if (moving.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				if (itemStack.getItemDamage() == 0)
				{
					BlockPos pos = moving.getBlockPos();

					if (!world.isBlockModifiable(player, pos))
					{
						return itemStack;
					}
					if (!player.canPlayerEdit(pos.offset(moving.sideHit), moving.sideHit, itemStack))
					{
						return itemStack;
					}

					if (world.getBlockState(pos) == FronosBlocks.mineral_water.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 1);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 1)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 1), false);
						}
					}
					else if (world.getBlockState(pos) == FronosBlocks.ovantine.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 2);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 2)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 2), false);
						}
					}
					else if (world.getBlockState(pos) == FronosBlocks.coconut_milk.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 3);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 3)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 3), false);
						}
					}
					else if (world.getBlockState(pos) == PolongniusBlocks.cheese_of_milk.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 4);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 4)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 4), false);
						}
					}
					else if (world.getBlockState(pos) == FronosBlocks.tea.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 5);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 5)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 5), false);
						}
					}
					else if (world.getBlockState(pos) == FronosBlocks.caramel.getDefaultState())
					{
						--itemStack.stackSize;
						world.setBlockToAir(pos);

						if (itemStack.stackSize <= 0)
						{
							return new ItemStack(this, 1, 6);
						}
						if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 6)))
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 6), false);
						}
					}
				}
			}
			return itemStack;
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
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		Block block = world.getBlockState(pos).getBlock();
		IBlockState state = world.getBlockState(pos);

		if (player.canPlayerEdit(pos, side, itemStack) && block == FronosBlocks.space_oyster_close)
		{
			if (itemStack.getItemDamage() == 1)
			{
				if (world.isRemote)
				{
					return true;
				}
				else
				{
					if (world.rand.nextInt(5) == 0)
					{
						world.setBlockState(pos, FronosBlocks.space_oyster_open.getDefaultState().withProperty(BlockOysterMP.FACING, EnumFacing.getFront(FronosBlocks.space_oyster_open.getMetaFromState(state))), 3);
					}
					--itemStack.stackSize;
					player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 0), false);
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		else if (player.canPlayerEdit(pos, side, itemStack) && block == FronosBlocks.cavern_oyster_close)
		{
			if (itemStack.getItemDamage() == 5)
			{
				if (world.isRemote)
				{
					return true;
				}
				else
				{
					if (world.rand.nextInt(7) == 0)
					{
						world.setBlockState(pos, FronosBlocks.cavern_oyster_open.getDefaultState().withProperty(BlockOysterMP.FACING, EnumFacing.getFront(FronosBlocks.space_oyster_open.getMetaFromState(state))), 3);
					}
					--itemStack.stackSize;
					player.dropPlayerItemWithRandomChoice(new ItemStack(this, 1, 0), false);
					return true;
				}
			}
			else
			{
				return false;
			}
		}

		if (block == Blocks.snow_layer && ((Integer)state.getValue(BlockSnow.LAYERS)).intValue() < 1)
		{
			side = EnumFacing.UP;
		}
		else if (!block.isReplaceable(world, pos))
		{
			pos = pos.offset(side);
		}

		if (!player.canPlayerEdit(pos, side, itemStack))
		{
			return false;
		}
		else if (itemStack.stackSize == 0)
		{
			return false;
		}
		else
		{
			EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
			Block cup = null;

			if (itemStack.getItemDamage() == 0)
			{
				cup = FronosBlocks.cup;
			}
			else if (itemStack.getItemDamage() == 1)
			{
				cup = FronosBlocks.mineral_water_cup;
			}
			else if (itemStack.getItemDamage() == 2)
			{
				cup = FronosBlocks.ovantine_cup;
			}
			else if (itemStack.getItemDamage() == 3)
			{
				cup = FronosBlocks.coconut_milk_cup;
			}
			else if (itemStack.getItemDamage() == 4)
			{
				cup = FronosBlocks.cheese_of_milk_cup;
			}
			else if (itemStack.getItemDamage() == 5)
			{
				cup = FronosBlocks.tea_cup;
			}
			else if (itemStack.getItemDamage() == 6)
			{
				cup = FronosBlocks.caramel_cup;
			}
			if (world.canBlockBePlaced(cup, pos, false, side, (Entity)null, itemStack))
			{
				world.setBlockState(pos, cup.getDefaultState().withProperty(BlockCup.FACING, enumfacing), 3);
				world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, cup.stepSound.getPlaceSound(), (cup.stepSound.getVolume() + 1.0F) / 2.0F, cup.stepSound.getFrequency() * 0.8F);
				--itemStack.stackSize;
				return true;
			}
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return itemStack.getItemDamage() == 0 ? EnumAction.NONE : EnumAction.DRINK;
	}

	@Override
	public int getHealAmount(ItemStack itemStack)
	{
		return 6;
	}

	@Override
	public float getSaturationModifier(ItemStack itemStack)
	{
		return 0.6F;
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "empty_cup", "mineral_water_cup", "ovantine_cup", "coconut_milk_cup", "cheese_of_milk_cup", "tea_cup", "caramel_cup" };
	}
}