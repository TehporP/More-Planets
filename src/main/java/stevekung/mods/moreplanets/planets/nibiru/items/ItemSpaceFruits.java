/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.ItemFoodMP;

public class ItemSpaceFruits extends ItemFoodMP
{
	private static int[] foodHunger = new int[] {
		4,
		4,
		5
	};
	private static float[] foodSaturation = new float[] {
		0.2F,
		0.4F,
		0.6F
	};

	public ItemSpaceFruits(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setHasSubtypes(true);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		if (itemStack.getItem() == this && itemStack.getItemDamage() == 2)
		{
			return EnumAction.DRINK;
		}
		return EnumAction.EAT;
	}

	@Override
	public int getItemStackLimit(ItemStack itemStack)
	{
		if (itemStack.getItem() == this && itemStack.getItemDamage() == 2)
		{
			return 1;
		}
		return 64;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
	{
		--itemStack.stackSize;
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(itemStack, world, player);
		player.getFoodStats().addStats(this, itemStack);

		if (itemStack.getItemDamage() == 2)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
			{
				player.entityDropItem(new ItemStack(Items.glass_bottle, 1, 0), 0.0F);
			}
			if (!world.isRemote)
			{
				if (itemStack.getItemDamage() == 2)
				{
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 4000, 1));
					player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2500, 1));
				}
			}
		}
		return itemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int getHealAmount(ItemStack itemStack)
	{
		return foodHunger[itemStack.getItemDamage()];
	}

	@Override
	public float getSaturationModifier(ItemStack itemStack)
	{
		return foodSaturation[itemStack.getItemDamage()];
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "space_apple", "space_orange", "orange_juice" };
	}
}