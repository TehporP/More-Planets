/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.common.items.ItemFlagMP;
import stevekung.mods.moreplanets.common.items.ItemMeteorShower;
import stevekung.mods.moreplanets.common.items.ItemMonsterPlacerMP;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;
import stevekung.mods.moreplanets.moons.io.items.IoItems;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;
import stevekung.mods.stevecore.RegisterHelper;

public class MPItems
{
	public static Item flag;
	public static Item meteor_shower;
	public static Item spawn_egg_mp;

	public static void init()
	{
		DionaItems.init();
		PolongniusItems.init();
		NibiruItems.init();
		KoentusItems.init();
		FronosItems.init();
		KapteynBItems.init();
		SiriusBItems.init();

		MercuryItems.init();
		VenusItems.init();
		PlutoItems.init();
		IoItems.init();
		EuropaItems.init();

		MPItems.initItems();
		MPItems.registerItems();
	}

	private static void initItems()
	{
		MPItems.flag = new ItemFlagMP("mp_flag");
		MPItems.meteor_shower = new ItemMeteorShower("meteor_shower");
		MPItems.spawn_egg_mp = new ItemMonsterPlacerMP("spawn_egg_mp");
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(MPItems.flag);
		RegisterHelper.registerItem(MPItems.meteor_shower);
		RegisterHelper.registerItem(MPItems.spawn_egg_mp);
	}
}