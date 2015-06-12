/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class ArmorPolongniusMeteoricIron extends ItemArmor
{
	public ArmorPolongniusMeteoricIron(String name, ArmorMaterial material, int render, int type)
	{
		super(material, render, type);
		this.setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == PolongniusArmorItems.polongnius_meteoric_iron_helmet || stack.getItem() == PolongniusArmorItems.polongnius_meteoric_iron_chestplate || stack.getItem() == PolongniusArmorItems.polongnius_meteoric_iron_boots)
		{
			return "moreplanets:textures/model/armor/polongnius_meteoric_iron_1.png";
		}
		if (stack.getItem() == PolongniusArmorItems.polongnius_meteoric_iron_leggings)
		{
			return "moreplanets:textures/model/armor/polongnius_meteoric_iron_2.png";
		}
		return null;
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpArmorTab;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
	{
		if (itemStack2.getItem() == PolongniusItems.polongnius_item && itemStack2.getItemDamage() == 6)
		{
			return true;
		}
		return false;
	}
}