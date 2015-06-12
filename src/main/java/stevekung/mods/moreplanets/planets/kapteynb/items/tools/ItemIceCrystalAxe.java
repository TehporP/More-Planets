/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ItemIceCrystalAxe extends ItemAxe
{
	public ItemIceCrystalAxe(String name, ToolMaterial material)
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
		entity.addPotionEffect(new PotionEffect(MPPotions.chemical.id, 20));
		entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60));
		entity.addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 80));
		stack.damageItem(1, entity2);
		return true;
	}

	//	@Override
	//	public EnumRarity getRarity(ItemStack par1ItemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		if (!itemStack.isItemEnchanted())
		{
			itemStack.addEnchantment(Enchantment.efficiency, 3);
			itemStack.addEnchantment(Enchantment.sharpness, 3);
			itemStack.addEnchantment(Enchantment.unbreaking, 3);
		}
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (par2ItemStack.getItem() == KapteynBItems.kapteyn_b_item && par2ItemStack.getItemDamage() == 5)
		{
			return true;
		}
		return false;
	}
}