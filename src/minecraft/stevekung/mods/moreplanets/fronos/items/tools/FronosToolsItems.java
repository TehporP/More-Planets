/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items.tools;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class FronosToolsItems
{
	public static Item candyPickaxe;
	public static Item candyAxe;
	public static Item candyHoe;
	public static Item candySpade;
	public static Item candySword;

	public static EnumToolMaterial TOOL_CANDY = EnumHelper.addToolMaterial("candy", 2, 248, 7.5F, 1.5F, 16);//Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability

	public static void initItems()
	{
		FronosToolsItems.candyPickaxe = new FronosItemCandyPickaxe(FronosConfigManager.idToolsCandyPickaxe, TOOL_CANDY).setUnlocalizedName("candyPickaxe");
		FronosToolsItems.candyAxe = new FronosItemCandyAxe(FronosConfigManager.idToolsCandyAxe, TOOL_CANDY).setUnlocalizedName("candyAxe");
		FronosToolsItems.candyHoe = new FronosItemCandyHoe(FronosConfigManager.idToolsCandyHoe, TOOL_CANDY).setUnlocalizedName("candyHoe");
		FronosToolsItems.candySpade = new FronosItemCandySpade(FronosConfigManager.idToolsCandySpade, TOOL_CANDY).setUnlocalizedName("candySpade");
		FronosToolsItems.candySword = new FronosItemCandySword(FronosConfigManager.idToolsCandySword, TOOL_CANDY).setUnlocalizedName("candySword");

		registerItems();
		registerHarvestLevels();
	}

	public static void registerHarvestLevels()
	{
		MinecraftForge.setToolClass(candyPickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(candyAxe, "axe", 2);
		MinecraftForge.setToolClass(candySpade, "shovel", 2);
	}

	public static void registerItems()
	{
		registerItem(candyPickaxe);
		registerItem(candyAxe);
		registerItem(candyHoe);
		registerItem(candySpade);
		registerItem(candySword);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}