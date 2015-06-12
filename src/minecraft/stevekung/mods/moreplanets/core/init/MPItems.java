/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class MPItems
{
	public static void init()
	{
		DionaItems.initItems();
		PolongniusItems.initItems();
		NibiruItems.initItems();
		KoentusItems.initItems();
		FronosItems.initItems();
		KapteynBItems.initItems();
	}
}