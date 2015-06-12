/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.itemblocks.PolongniusItemBlockCake;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class PolongniusItems
{
	public static Item polongniusBasicItem;
	public static Item polongniusBasicItem2;
	public static Item polongniusFood;
	public static Item polongniusDungeonKey;
	public static Item cheeseOfMilkBlockCake;
	public static Item cheeseSlimeball;
	public static Item rocketTier4;
	public static Item rocketPartT4;
	public static Item schematicT4;

	public static void initItems()
	{
		PolongniusItems.polongniusBasicItem = new PolongniusItemBasic(PolongniusConfigManager.idItemPolongnius).setUnlocalizedName("polongniusBasicItem");
		PolongniusItems.polongniusBasicItem2 = new PolongniusItemSolarModule(PolongniusConfigManager.idItemPolongnius2).setUnlocalizedName("polongniusSolarModule");
		PolongniusItems.polongniusFood = new PolongniusItemFood(PolongniusConfigManager.idItemPolongniusFood).setUnlocalizedName("polongniusFood");
		PolongniusItems.polongniusDungeonKey = new PolongniusItemDungeonKey(PolongniusConfigManager.idItemPolongniusDungeonKey).setUnlocalizedName("polongniusDungeonKey");
		PolongniusItems.cheeseOfMilkBlockCake = new PolongniusItemBlockCake(PolongniusConfigManager.idItemCheeseOfMilkCake, PolongniusBlocks.cheeseOfMilkCake).setUnlocalizedName("cheeseOfMilkItem");
		PolongniusItems.cheeseSlimeball = new PolongniusItemCheeseSlimeball(PolongniusConfigManager.idItemCheeseSlimeball).setUnlocalizedName("cheeseSlimeball");
		PolongniusItems.rocketTier4 = new PolongniusItemSpaceshipTier4(PolongniusConfigManager.idItemRocketT4).setUnlocalizedName("rocketT4");
		PolongniusItems.rocketPartT4 = new PolongniusItemRocketPartT4(PolongniusConfigManager.idItemRocketPartT4).setUnlocalizedName("rocketPartT4");
		PolongniusItems.schematicT4 = new PolongniusItemSchematic(PolongniusConfigManager.idItemSchematicT4).setUnlocalizedName("schematicT4");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(polongniusBasicItem);
		registerItem(polongniusBasicItem2);
		registerItem(polongniusFood);
		registerItem(polongniusDungeonKey);
		registerItem(cheeseOfMilkBlockCake);
		registerItem(cheeseSlimeball);
		registerItem(rocketTier4);
		registerItem(rocketPartT4);
		registerItem(schematicT4);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}