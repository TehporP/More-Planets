/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemBaseMP extends Item
{
	public ItemBaseMP()
	{
		super();
		this.setHasSubtypes(true);
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
		return super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[itemStack.getItemDamage()];
	}

	public abstract String[] getItemVariantsName();

	public boolean reverseName()
	{
		return false;
	}
}