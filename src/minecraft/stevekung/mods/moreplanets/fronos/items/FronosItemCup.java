/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemCup extends ItemFood
{
	private static String[] cup = {
		"emptyCup",
		"mineralWaterCup",
		"ovantineCup",
		"coconutMilkCup",
		"cheeseOfMilkCup"
	};

	public FronosItemCup(int par1, int par2)
	{
		super(par1, par2, false);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		int meta = par1ItemStack.getItemDamage();

		if (!par3EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote || meta >= 1)
		{
			par3EntityPlayer.curePotionEffects(par1ItemStack);
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 1600, 2));
		}

		if (meta >= 1)
		{
			par3EntityPlayer.getFoodStats().addStats(6, 0.60F);
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.drink", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);

		if (meta >= 1)
		{
			if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup)))
			{
				par3EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup.itemID, 1, 0));
			}
		}
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		if (par1ItemStack.getItemDamage() >= 1)
		{
			return 32;
		}
		return 0;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getItemDamage() >= 1)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
		{
			if (par1ItemStack.getItemDamage() == 0)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return par1ItemStack;
				}

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
				{
					return par1ItemStack;
				}

				if (par2World.getBlockId(i, j, k) == FronosBlocks.mineralWaterFluidBlock.blockID && par2World.getBlockMetadata(i, j, k) == 0)
				{
					--par1ItemStack.stackSize;

					par2World.setBlockToAir(i, j, k);

					if (par1ItemStack.stackSize <= 0)
					{
						return new ItemStack(FronosItems.cup, 1, 1);
					}

					if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 1)))
					{
						par3EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup, 1, 1));
					}
				}
				else if (par2World.getBlockId(i, j, k) == FronosBlocks.ovantineFluidBlock.blockID && par2World.getBlockMetadata(i, j, k) == 0)
				{
					--par1ItemStack.stackSize;

					par2World.setBlockToAir(i, j, k);

					if (par1ItemStack.stackSize <= 0)
					{
						return new ItemStack(FronosItems.cup, 1, 2);
					}

					if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 2)))
					{
						par3EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup, 1, 2));
					}
				}
				else if (par2World.getBlockId(i, j, k) == FronosBlocks.coconutMilkFluidBlock.blockID && par2World.getBlockMetadata(i, j, k) == 0)
				{
					--par1ItemStack.stackSize;

					par2World.setBlockToAir(i, j, k);

					if (par1ItemStack.stackSize <= 0)
					{
						return new ItemStack(FronosItems.cup, 1, 3);
					}

					if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 3)))
					{
						par3EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup, 1, 3));
					}
				}
				else if (par2World.getBlockId(i, j, k) == PolongniusBlocks.cheeseOfMilkFluidBlock.blockID && par2World.getBlockMetadata(i, j, k) == 0)
				{
					--par1ItemStack.stackSize;

					par2World.setBlockToAir(i, j, k);

					if (par1ItemStack.stackSize <= 0)
					{
						return new ItemStack(FronosItems.cup, 1, 4);
					}

					if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 4)))
					{
						par3EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup, 1, 4));
					}
				}
			}
		}
		return par1ItemStack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		if (par1ItemStack.getItemDamage() >= 1)
		{
			return EnumAction.drink;
		}
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("diona:blank");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= cup.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + cup[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
	{
		for (int meta = 0; meta < cup.length; ++meta)
		{
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		int i1 = par3World.getBlockId(par4, par5, par6);
		par3World.getBlockMetadata(par4, par5, par6);

		if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && i1 == FronosBlocks.spaceOysterClose.blockID)
		{
			if (par1ItemStack.getItemDamage() == 1)
			{
				if (par3World.isRemote)
				{
					return true;
				}
				else
				{
					int meta = par3World.getBlockMetadata(par4, par5, par6);
					if (par3World.rand.nextInt(5) == 0)
					{
						par3World.setBlock(par4, par5, par6, FronosBlocks.spaceOyster.blockID, meta, 3);
					}
					--par1ItemStack.stackSize;
					par2EntityPlayer.dropPlayerItem(new ItemStack(FronosItems.cup, 1, 0));
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		return false;
	}
}