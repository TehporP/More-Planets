/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.ItemAxeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemHoeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemPickaxeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemShovelMP;
import stevekung.mods.moreplanets.core.items.tools.ItemSwordMP;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.stevecore.RegisterHelper;

public class PolongniusToolsItems
{
	public static Item polongnius_meteoric_iron_pickaxe;
	public static Item polongnius_meteoric_iron_axe;
	public static Item polongnius_meteoric_iron_hoe;
	public static Item polongnius_meteoric_iron_shovel;
	public static Item polongnius_meteoric_iron_sword;
	public static Item palladium_pickaxe;
	public static Item palladium_axe;
	public static Item palladium_hoe;
	public static Item palladium_shovel;
	public static Item palladium_sword;
	public static Item purple_crystal_pickaxe;
	public static Item purple_crystal_axe;
	public static Item purple_crystal_hoe;
	public static Item purple_crystal_shovel;
	public static Item purple_crystal_sword;

	/**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
	public static ToolMaterial polongnius_meteoric_iron = EnumHelper.addToolMaterial("polongnius_meteoric_iron", 4, 1686, 10.0F, 3.75F, 8);
	public static ToolMaterial palladium = EnumHelper.addToolMaterial("palladium", 4, 1694, 10.0F, 3.75F, 8);
	public static ToolMaterial purple_crystal = EnumHelper.addToolMaterial("purple_crystal", 4, 1572, 9.75F, 3.5F, 8);

	public static void init()
	{
		PolongniusToolsItems.initItems();
		PolongniusToolsItems.registerItems();
		PolongniusToolsItems.registerHarvestLevels();
	}

	private static void initItems()
	{
		PolongniusToolsItems.polongnius_meteoric_iron_pickaxe = new ItemPickaxeMP("polongnius_meteor_pickaxe", PolongniusToolsItems.polongnius_meteoric_iron, PolongniusItems.polongnius_item, 6, "polongnius:polongnius_meteoric_iron_pickaxe");
		PolongniusToolsItems.polongnius_meteoric_iron_axe = new ItemAxeMP("polongnius_meteor_axe", PolongniusToolsItems.polongnius_meteoric_iron, PolongniusItems.polongnius_item, 6, "polongnius:polongnius_meteoric_iron_axe");
		PolongniusToolsItems.polongnius_meteoric_iron_hoe = new ItemHoeMP("polongnius_meteor_hoe", PolongniusToolsItems.polongnius_meteoric_iron, PolongniusItems.polongnius_item, 6, "polongnius:polongnius_meteoric_iron_hoe");
		PolongniusToolsItems.polongnius_meteoric_iron_shovel = new ItemShovelMP("polongnius_meteor_spade", PolongniusToolsItems.polongnius_meteoric_iron, PolongniusItems.polongnius_item, 6, "polongnius:polongnius_meteoric_iron_shovel");
		PolongniusToolsItems.polongnius_meteoric_iron_sword = new ItemSwordMP("polongnius_meteor_sword", PolongniusToolsItems.polongnius_meteoric_iron, PolongniusItems.polongnius_item, 6, "polongnius:polongnius_meteoric_iron_sword");
		PolongniusToolsItems.palladium_pickaxe = new ItemPickaxeMP("palladium_pickaxe", PolongniusToolsItems.palladium, PolongniusItems.polongnius_item, 7, "polongnius:palladium_pickaxe");
		PolongniusToolsItems.palladium_axe = new ItemAxeMP("palladium_axe", PolongniusToolsItems.palladium, PolongniusItems.polongnius_item, 7, "polongnius:palladium_axe");
		PolongniusToolsItems.palladium_hoe = new ItemHoeMP("palladium_hoe", PolongniusToolsItems.palladium, PolongniusItems.polongnius_item, 7, "polongnius:palladium_hoe");
		PolongniusToolsItems.palladium_shovel = new ItemShovelMP("palladium_spade", PolongniusToolsItems.palladium, PolongniusItems.polongnius_item, 7, "polongnius:palladium_shovel");
		PolongniusToolsItems.palladium_sword = new ItemSwordMP("palladium_sword", PolongniusToolsItems.palladium, PolongniusItems.polongnius_item, 7, "polongnius:palladium_sword");
		PolongniusToolsItems.purple_crystal_pickaxe = new ItemPickaxeMP("purple_crystal_pickaxe", PolongniusToolsItems.purple_crystal, PolongniusItems.polongnius_item, 1, "polongnius:purple_crystal_pickaxe");
		PolongniusToolsItems.purple_crystal_axe = new ItemAxeMP("purple_crystal_axe", PolongniusToolsItems.purple_crystal, PolongniusItems.polongnius_item, 1, "polongnius:purple_crystal_axe");
		PolongniusToolsItems.purple_crystal_hoe = new ItemHoeMP("purple_crystal_hoe", PolongniusToolsItems.purple_crystal, PolongniusItems.polongnius_item, 1, "polongnius:purple_crystal_hoe");
		PolongniusToolsItems.purple_crystal_shovel = new ItemShovelMP("purple_crystal_spade", PolongniusToolsItems.purple_crystal, PolongniusItems.polongnius_item, 1, "polongnius:purple_crystal_shovel");
		PolongniusToolsItems.purple_crystal_sword = new ItemSwordMP("purple_crystal_sword", PolongniusToolsItems.purple_crystal, PolongniusItems.polongnius_item, 1, "polongnius:purple_crystal_sword");
	}

	private static void registerHarvestLevels()
	{
		PolongniusToolsItems.polongnius_meteoric_iron_pickaxe.setHarvestLevel("pickaxe", 4);
		PolongniusToolsItems.polongnius_meteoric_iron_axe.setHarvestLevel("axe", 4);
		PolongniusToolsItems.polongnius_meteoric_iron_shovel.setHarvestLevel("shovel", 4);
		PolongniusToolsItems.palladium_pickaxe.setHarvestLevel("pickaxe", 4);
		PolongniusToolsItems.palladium_axe.setHarvestLevel("axe", 4);
		PolongniusToolsItems.palladium_shovel.setHarvestLevel("shovel", 4);
		PolongniusToolsItems.purple_crystal_pickaxe.setHarvestLevel("pickaxe", 4);
		PolongniusToolsItems.purple_crystal_axe.setHarvestLevel("axe", 4);
		PolongniusToolsItems.purple_crystal_shovel.setHarvestLevel("shovel", 4);
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(PolongniusToolsItems.polongnius_meteoric_iron_pickaxe);
		RegisterHelper.registerItem(PolongniusToolsItems.polongnius_meteoric_iron_axe);
		RegisterHelper.registerItem(PolongniusToolsItems.polongnius_meteoric_iron_hoe);
		RegisterHelper.registerItem(PolongniusToolsItems.polongnius_meteoric_iron_shovel);
		RegisterHelper.registerItem(PolongniusToolsItems.polongnius_meteoric_iron_sword);
		RegisterHelper.registerItem(PolongniusToolsItems.palladium_pickaxe);
		RegisterHelper.registerItem(PolongniusToolsItems.palladium_axe);
		RegisterHelper.registerItem(PolongniusToolsItems.palladium_hoe);
		RegisterHelper.registerItem(PolongniusToolsItems.palladium_shovel);
		RegisterHelper.registerItem(PolongniusToolsItems.palladium_sword);
		RegisterHelper.registerItem(PolongniusToolsItems.purple_crystal_pickaxe);
		RegisterHelper.registerItem(PolongniusToolsItems.purple_crystal_axe);
		RegisterHelper.registerItem(PolongniusToolsItems.purple_crystal_hoe);
		RegisterHelper.registerItem(PolongniusToolsItems.purple_crystal_shovel);
		RegisterHelper.registerItem(PolongniusToolsItems.purple_crystal_sword);
	}
}