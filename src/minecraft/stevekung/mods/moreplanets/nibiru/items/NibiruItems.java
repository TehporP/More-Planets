/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.items;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class NibiruItems
{
	public static Item nibiruBasicItem;
	public static Item nibiruFood;
	public static Item nibiruDungeonKey;
	public static Item powerCrystal;
	public static Item rocketT5;
	public static Item rocketPartT5;
	public static Item schematicT5;

	public static void initItems()
	{
		NibiruItems.nibiruBasicItem = new NibiruItemBasic(NibiruConfigManager.idNibiruBasicItem).setUnlocalizedName("nibiruBasicItem");
		NibiruItems.nibiruFood = new NibiruItemSpaceFood(NibiruConfigManager.idNibiruFood, 2).setUnlocalizedName("nibiruFood");
		NibiruItems.nibiruDungeonKey = new NibiruItemDungeonKey(NibiruConfigManager.idNibiruDungeonKey).setUnlocalizedName("nibiruDungeonKey");
		NibiruItems.powerCrystal = new NibiruItemPowerCrystal(NibiruConfigManager.idIchoriusGem).setUnlocalizedName("poweredGem");
		NibiruItems.rocketT5 = new NibiruItemSpaceshipTier5(NibiruConfigManager.idItemRocketT5).setUnlocalizedName("rocketT5");
		NibiruItems.rocketPartT5 = new NibiruItemRocketPartT5(NibiruConfigManager.idItemRocketPartT5).setUnlocalizedName("rocketPartT5");
		NibiruItems.schematicT5 = new NibiruItemSchematic(NibiruConfigManager.idItemSchematicT5).setUnlocalizedName("schematicT5");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(nibiruBasicItem);
		registerItem(nibiruFood);
		registerItem(nibiruDungeonKey);
		registerItem(powerCrystal);
		registerItem(rocketT5);
		registerItem(rocketPartT5);
		registerItem(schematicT5);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}