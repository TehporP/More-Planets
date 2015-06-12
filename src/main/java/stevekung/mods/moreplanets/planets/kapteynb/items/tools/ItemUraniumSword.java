/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ItemUraniumSword extends ItemSword
{
	public ItemUraniumSword(String name, ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(name);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpToolsTab;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
	{
		entity.addPotionEffect(new PotionEffect(MPPotions.chemical.id, 60));
		stack.damageItem(1, entity2);
		return true;
	}

	//	@Override
	//	public EnumRarity getRarity(ItemStack par1ItemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (par2ItemStack.getItem() == KapteynBItems.kapteyn_b_item && par2ItemStack.getItemDamage() == 1)
		{
			return true;
		}
		return false;
	}
}