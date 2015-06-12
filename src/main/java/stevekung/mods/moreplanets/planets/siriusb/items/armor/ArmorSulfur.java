/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.common.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class ArmorSulfur extends ItemArmorMP
{
	public ArmorSulfur(String name, ArmorMaterial material, int render, int type)
	{
		super(material, render, type);
		this.setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == SiriusBArmorItems.sulfur_helmet || stack.getItem() == SiriusBArmorItems.sulfur_chestplate || stack.getItem() == SiriusBArmorItems.sulfur_boots)
		{
			return "moreplanets:textures/model/armor/sulfur_1.png";
		}
		if (stack.getItem() == SiriusBArmorItems.sulfur_leggings)
		{
			return "moreplanets:textures/model/armor/sulfur_2.png";
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
	public Item getRepairItems()
	{
		return SiriusBItems.sirius_b_item;
	}

	@Override
	public int getRepairItemsMetadata()
	{
		return 4;
	}
}