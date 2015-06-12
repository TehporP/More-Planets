/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.ItemMorePlanets;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;

public class ItemCandyBow extends ItemMorePlanets
{
	public ItemCandyBow(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(384);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack itemStack, EntityPlayer player, int useRemaining)
	{
		if (itemStack != null && itemStack.getItem() == this && player.getItemInUse() != null)
		{
			int i = itemStack.getMaxItemUseDuration() - player.getItemInUseCount();

			if (i >= 18)
			{
				return new ModelResourceLocation("moreplanets:candy_bow_pulling_2", "inventory");
			}
			if (i > 13)
			{
				return new ModelResourceLocation("moreplanets:candy_bow_pulling_1", "inventory");
			}
			if (i > 0)
			{
				return new ModelResourceLocation("moreplanets:candy_bow_pulling_0", "inventory");
			}
		}
		return null;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
	{
		int j = this.getMaxItemUseDuration(itemStack) - tick;

		ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, j);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled())
		{
			return;
		}

		j = event.charge;

		boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

		if (flag || player.inventory.hasItem(FronosItems.poison_arrow))
		{
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.1D)
			{
				return;
			}
			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntityPoisonArrow entityarrow = new EntityPoisonArrow(world, player, f * 2.0F);

			if (f == 1.0F)
			{
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);

			if (k > 0)
			{
				entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);

			if (l > 0)
			{
				entityarrow.setKnockbackStrength(l);
			}
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
			{
				entityarrow.setFire(100);
			}

			itemStack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag)
			{
				entityarrow.canBePickedUp = 2;
			}
			else
			{
				player.inventory.consumeInventoryItem(FronosItems.poison_arrow);
			}
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entityarrow);
			}
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
	{
		return itemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.BOW;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		ArrowNockEvent event = new ArrowNockEvent(player, itemStack);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled())
		{
			return event.result;
		}
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(FronosItems.poison_arrow))
		{
			player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}
		return itemStack;
	}

	@Override
	public int getItemEnchantability()
	{
		return 4;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
	{
		if (itemStack2.getItem() == FronosItems.candy_cane)
		{
			return true;
		}
		return false;
	}
}