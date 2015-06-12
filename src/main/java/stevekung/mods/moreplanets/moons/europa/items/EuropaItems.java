/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.items;

import net.minecraft.item.Item;
import stevekung.mods.stevecore.RegisterHelper;

public class EuropaItems
{
	public static Item europa_prismarine;

	public static void init()
	{
		EuropaItems.initItems();
		EuropaItems.registerItems();
	}

	private static void initItems()
	{
		EuropaItems.europa_prismarine = new ItemEuropaPrismarine("europa_prismarine_item");
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(EuropaItems.europa_prismarine);
	}
}