/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemFoodMP extends ItemFood
{
	public ItemFoodMP()
	{
		super(0, false);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpItemsTab;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		if (this.reverseName())
		{
			return "item." + this.getItemVariantsName()[itemStack.getItemDamage()] + "." + super.getUnlocalizedName(itemStack).replace("item.", "");
		}
		if (this.getItemVariantsName() == null)
		{
			return super.getUnlocalizedName(itemStack);
		}
		return super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[itemStack.getItemDamage()];
	}

	public boolean reverseName()
	{
		return false;
	}

	public abstract String[] getItemVariantsName();
}