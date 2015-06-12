/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.diona.items.tools.DionaToolsItems;
import stevekung.mods.moreplanets.fronos.items.tools.FronosToolsItems;
import stevekung.mods.moreplanets.nibiru.items.tools.NibiruToolsItems;
import stevekung.mods.moreplanets.polongnius.items.tools.PolongniusToolsItems;

public class MPTools
{
	public static void init()
	{
		DionaToolsItems.initItems();
		PolongniusToolsItems.initItems();
		NibiruToolsItems.initItems();
		FronosToolsItems.initItems();
	}
}