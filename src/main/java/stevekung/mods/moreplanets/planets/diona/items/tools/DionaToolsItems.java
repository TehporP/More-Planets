/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.common.items.tools.ItemAxeMP;
import stevekung.mods.moreplanets.common.items.tools.ItemHoeMP;
import stevekung.mods.moreplanets.common.items.tools.ItemPickaxeMP;
import stevekung.mods.moreplanets.common.items.tools.ItemShovelMP;
import stevekung.mods.moreplanets.common.items.tools.ItemSwordMP;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.stevecore.RegisterHelper;

public class DionaToolsItems
{
	public static Item quontonium_sword;
	public static Item quontonium_shovel;
	public static Item quontonium_pickaxe;
	public static Item quontonium_axe;
	public static Item quontonium_hoe;
	public static Item fronisium_sword;
	public static Item fronisium_shovel;
	public static Item fronisium_pickaxe;
	public static Item fronisium_axe;
	public static Item fronisium_hoe;

	// Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
	public static ToolMaterial quontonium = EnumHelper.addToolMaterial("quontonium", 4, 1648, 9.5F, 3.5F, 8);
	public static ToolMaterial fronisium = EnumHelper.addToolMaterial("fronisium", 4, 1680, 9.5F, 3.75F, 8);

	public static void init()
	{
		DionaToolsItems.initItems();
		DionaToolsItems.registerItems();
		DionaToolsItems.registerHarvestLevels();
	}

	private static void initItems()
	{
		DionaToolsItems.quontonium_sword = new ItemSwordMP("quontonium_sword", DionaToolsItems.quontonium, DionaItems.diona_item, 2);
		DionaToolsItems.quontonium_shovel = new ItemShovelMP("quontonium_shovel", DionaToolsItems.quontonium, DionaItems.diona_item, 2);
		DionaToolsItems.quontonium_pickaxe = new ItemPickaxeMP("quontonium_pickaxe", DionaToolsItems.quontonium, DionaItems.diona_item, 2);
		DionaToolsItems.quontonium_axe = new ItemAxeMP("quontonium_axe", DionaToolsItems.quontonium, DionaItems.diona_item, 2);
		DionaToolsItems.quontonium_hoe = new ItemHoeMP("quontonium_hoe", DionaToolsItems.quontonium, DionaItems.diona_item, 2);
		DionaToolsItems.fronisium_sword = new ItemSwordMP("fronisium_sword", fronisium, DionaItems.diona_item, 3);
		DionaToolsItems.fronisium_shovel = new ItemShovelMP("fronisium_shovel", fronisium, DionaItems.diona_item, 3);
		DionaToolsItems.fronisium_pickaxe = new ItemPickaxeMP("fronisium_pickaxe", fronisium, DionaItems.diona_item, 3);
		DionaToolsItems.fronisium_axe = new ItemAxeMP("fronisium_axe", fronisium, DionaItems.diona_item, 3);
		DionaToolsItems.fronisium_hoe = new ItemHoeMP("fronisium_hoe", fronisium, DionaItems.diona_item, 3);
	}

	private static void registerHarvestLevels()
	{
		DionaToolsItems.quontonium_shovel.setHarvestLevel("shovel", 4);
		DionaToolsItems.quontonium_pickaxe.setHarvestLevel("pickaxe", 4);
		DionaToolsItems.quontonium_axe.setHarvestLevel("axe", 4);
		DionaToolsItems.fronisium_shovel.setHarvestLevel("shovel", 4);
		DionaToolsItems.fronisium_pickaxe.setHarvestLevel("pickaxe", 4);
		DionaToolsItems.fronisium_axe.setHarvestLevel("axe", 4);
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(DionaToolsItems.quontonium_sword);
		RegisterHelper.registerItem(DionaToolsItems.quontonium_shovel);
		RegisterHelper.registerItem(DionaToolsItems.quontonium_pickaxe);
		RegisterHelper.registerItem(DionaToolsItems.quontonium_axe);
		RegisterHelper.registerItem(DionaToolsItems.quontonium_hoe);
		RegisterHelper.registerItem(DionaToolsItems.fronisium_sword);
		RegisterHelper.registerItem(DionaToolsItems.fronisium_shovel);
		RegisterHelper.registerItem(DionaToolsItems.fronisium_pickaxe);
		RegisterHelper.registerItem(DionaToolsItems.fronisium_axe);
		RegisterHelper.registerItem(DionaToolsItems.fronisium_hoe);
	}
}