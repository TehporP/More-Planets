/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.diona.items.armor.DionaArmorItems;
import stevekung.mods.moreplanets.nibiru.items.armor.NibiruArmorItems;
import stevekung.mods.moreplanets.polongnius.items.armor.PolongniusArmorItems;

public class MPArmor
{
	public static void init()
	{
		DionaArmorItems.initItems();
		PolongniusArmorItems.initItems();
		NibiruArmorItems.initItems();
	}
}