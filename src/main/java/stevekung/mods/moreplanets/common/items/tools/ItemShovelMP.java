/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemShovelMP extends ItemSpade
{
	private Item repairItems;
	private int repairItemsMeta;

	public ItemShovelMP(String name, ToolMaterial material, Item item, int meta)
	{
		super(material);
		this.repairItems = item;
		this.repairItemsMeta = meta;
		this.setUnlocalizedName(name);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpToolsTab;
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
	{
		if (itemStack2.getItem() == this.repairItems && itemStack2.getItemDamage() == this.repairItemsMeta)
		{
			return true;
		}
		return false;
	}
}