/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MPCreativeTab extends CreativeTabs
{
	private final int itemForTab;
	private final int metaForTab;

	public MPCreativeTab(int par1, String par2Str, int itemForTab, int metaForTab)
	{
		super(par1, par2Str);
		this.itemForTab = itemForTab;
		this.metaForTab = metaForTab;
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(this.itemForTab, 1, this.metaForTab);
	}
}