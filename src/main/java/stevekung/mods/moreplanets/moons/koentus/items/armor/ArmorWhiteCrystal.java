/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.common.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;

public class ArmorWhiteCrystal extends ItemArmorMP
{
	public ArmorWhiteCrystal(String name, ArmorMaterial material, int render, int type)
	{
		super(material, render, type);
		this.setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == KoentusArmorItems.white_crystal_helmet || stack.getItem() == KoentusArmorItems.white_crystal_chestplate || stack.getItem() == KoentusArmorItems.white_crystal_boots)
		{
			return "moreplanets:textures/model/armor/white_crystal_1.png";
		}
		if (stack.getItem() == KoentusArmorItems.white_crystal_leggings)
		{
			return "moreplanets:textures/model/armor/white_crystal_2.png";
		}
		return null;
	}

	@Override
	public Item getRepairItems()
	{
		return KoentusItems.koentus_item;
	}

	@Override
	public int getRepairItemsMetadata()
	{
		return 5;
	}
}