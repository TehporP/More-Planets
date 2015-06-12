/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruItemSpaceFood extends ItemFood
{
	public static String[] names = {
		"spaceApple",
		"spaceOrange",
	"orangeJuice"};
	protected Icon[] icons = new Icon[NibiruItemSpaceFood.names.length];

	public NibiruItemSpaceFood(int par1, int par2)
	{
		super(par1, par2, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			switch (itemstack.getItemDamage())
			{
			case 2:
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 4000, 1));
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2500, 1));
				break;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 2)
		{
			return EnumAction.drink;
		}

		return EnumAction.eat;
	}

	@Override
	public int getItemStackLimit(ItemStack par1ItemStack)
	{
		if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 2)
		{
			return 1;
		}

		return 64;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		--par1ItemStack.stackSize;
		switch (par1ItemStack.getItemDamage())
		{
		case 0:
			par3EntityPlayer.getFoodStats().addStats(4, 0.2F);
			break;
		case 1:
			par3EntityPlayer.getFoodStats().addStats(4, 0.4F);
			break;
		case 2:
			par3EntityPlayer.getFoodStats().addStats(5, 0.6F);
			break;
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);

		switch (par1ItemStack.getItemDamage())
		{
		case 2:
			if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle))) {
				par3EntityPlayer.dropPlayerItem(new ItemStack(Item.glassBottle.itemID, 1, 0));
			}
		}

		return par1ItemStack;
	}

	@Override
	public Icon getIconFromDamage(int damage)
	{
		if (this.icons.length > damage)
		{
			return this.icons[damage];
		}

		return super.getIconFromDamage(damage);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < NibiruItemSpaceFood.names.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + NibiruItemSpaceFood.names[par1ItemStack.getItemDamage()];
		}

		return "unnamed";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		int i = 0;

		for (String name : NibiruItemSpaceFood.names)
		{
			this.icons[i++] = iconRegister.registerIcon("nibiru:" + name);
		}
	}
}