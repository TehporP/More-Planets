/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusArmorLeatherOfCheese extends ItemArmor
{
	private final EnumArmorMaterial material;

	public PolongniusArmorLeatherOfCheese(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.material = par2EnumArmorMaterial;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if (this.material == PolongniusArmorItems.ARMORCHEESE)
		{
			if (stack.getItem().itemID == PolongniusArmorItems.leatherOfCheeseHelmet.itemID)
			{
				return "polongnius:textures/model/armor/leatherOfCheese_1.png";
			}
			else if (stack.getItem().itemID == PolongniusArmorItems.leatherOfCheeseChestplate.itemID || stack.getItem().itemID == PolongniusArmorItems.leatherOfCheeseBoots.itemID)
			{
				return "polongnius:textures/model/armor/leatherOfCheese_2.png";
			}
			else if (stack.getItem().itemID == PolongniusArmorItems.leatherOfCheeseLeggings.itemID)
			{
				return "polongnius:textures/model/armor/leatherOfCheese_3.png";
			}
		}

		return null;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("item.", "polongnius:"));
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (par2ItemStack.itemID == PolongniusItems.polongniusBasicItem.itemID && par2ItemStack.getItemDamage() == 9)
		{
			return true;
		}
		return false;
	}

	@Override
	public Item setUnlocalizedName(String par1Str)
	{
		super.setTextureName(par1Str);
		super.setUnlocalizedName(par1Str);
		return this;
	}
}