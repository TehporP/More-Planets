/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.items;

import net.minecraft.item.Item;
import stevekung.mods.stevecore.RegisterHelper;

public class MercuryItems
{
	public static Item mercury_item;

	public static void init()
	{
		MercuryItems.initItems();
		MercuryItems.registerItems();
	}

	private static void initItems()
	{
		MercuryItems.mercury_item = new ItemBasicMercury("mercury_item");
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(MercuryItems.mercury_item);
	}
}