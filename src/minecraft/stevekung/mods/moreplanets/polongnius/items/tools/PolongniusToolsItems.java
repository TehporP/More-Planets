/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items.tools;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class PolongniusToolsItems
{
	public static Item polongniusMeteorPickaxe;
	public static Item polongniusMeteorAxe;
	public static Item polongniusMeteorHoe;
	public static Item polongniusMeteorSpade;
	public static Item polongniusMeteorSword;
	public static Item palladiumPickaxe;
	public static Item palladiumAxe;
	public static Item palladiumHoe;
	public static Item palladiumSpade;
	public static Item palladiumSword;

	public static EnumToolMaterial TOOL_POLONGNIUS_METEOR = EnumHelper.addToolMaterial("polongniuspolongniusMeteor", 5, 1690, 10.0F, 5.5F, 16);//Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
	public static EnumToolMaterial TOOL_PALLADIUM = EnumHelper.addToolMaterial("palladium", 5, 1740, 12.0F, 6.25F, 16);

	public static void initItems()
	{
		PolongniusToolsItems.polongniusMeteorPickaxe = new PolongniusItemMeteorPickaxe(PolongniusConfigManager.idToolMeteorPickaxe, TOOL_POLONGNIUS_METEOR).setUnlocalizedName("polongniusMeteorPickaxe");
		PolongniusToolsItems.polongniusMeteorAxe = new PolongniusItemMeteorAxe(PolongniusConfigManager.idToolMeteorAxe, TOOL_POLONGNIUS_METEOR).setUnlocalizedName("polongniusMeteorAxe");
		PolongniusToolsItems.polongniusMeteorHoe = new PolongniusItemMeteorHoe(PolongniusConfigManager.idToolMeteorHoe, TOOL_POLONGNIUS_METEOR).setUnlocalizedName("polongniusMeteorHoe");
		PolongniusToolsItems.polongniusMeteorSpade = new PolongniusItemMeteorSpade(PolongniusConfigManager.idToolMeteorSpade, TOOL_POLONGNIUS_METEOR).setUnlocalizedName("polongniusMeteorSpade");
		PolongniusToolsItems.polongniusMeteorSword = new PolongniusItemMeteorSword(PolongniusConfigManager.idToolMeteorSword, TOOL_POLONGNIUS_METEOR).setUnlocalizedName("polongniusMeteorSword");

		PolongniusToolsItems.palladiumPickaxe = new PolongniusItemPalladiumPickaxe(PolongniusConfigManager.idToolPalladiumPickaxe, TOOL_PALLADIUM).setUnlocalizedName("palladiumPickaxe");
		PolongniusToolsItems.palladiumAxe = new PolongniusItemPalladiumAxe(PolongniusConfigManager.idToolPalladiumAxe, TOOL_PALLADIUM).setUnlocalizedName("palladiumAxe");
		PolongniusToolsItems.palladiumHoe = new PolongniusItemPalladiumHoe(PolongniusConfigManager.idToolPalladiumHoe, TOOL_PALLADIUM).setUnlocalizedName("palladiumHoe");
		PolongniusToolsItems.palladiumSpade = new PolongniusItemPalladiumSpade(PolongniusConfigManager.idToolPalladiumSpade, TOOL_PALLADIUM).setUnlocalizedName("palladiumSpade");
		PolongniusToolsItems.palladiumSword = new PolongniusItemPalladiumSword(PolongniusConfigManager.idToolPalladiumSword, TOOL_PALLADIUM).setUnlocalizedName("palladiumSword");

		registerItems();
		registerHarvestLevels();
	}

	public static void registerHarvestLevels()
	{
		MinecraftForge.setToolClass(polongniusMeteorPickaxe, "pickaxe", 5);
		MinecraftForge.setToolClass(polongniusMeteorAxe, "axe", 5);
		MinecraftForge.setToolClass(polongniusMeteorSpade, "shovel", 5);
		MinecraftForge.setToolClass(palladiumPickaxe, "pickaxe", 5);
		MinecraftForge.setToolClass(palladiumAxe, "axe", 5);
		MinecraftForge.setToolClass(palladiumSpade, "shovel", 5);
	}

	public static void registerItems()
	{
		registerItem(polongniusMeteorPickaxe);
		registerItem(polongniusMeteorAxe);
		registerItem(polongniusMeteorHoe);
		registerItem(polongniusMeteorSpade);
		registerItem(polongniusMeteorSword);
		registerItem(palladiumPickaxe);
		registerItem(palladiumAxe);
		registerItem(palladiumHoe);
		registerItem(palladiumSpade);
		registerItem(palladiumSword);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}