/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items.tools;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class DionaToolsItems
{
	public static Item quontoniumPickaxe;
	public static Item quontoniumAxe;
	public static Item quontoniumHoe;
	public static Item quontoniumSpade;
	public static Item quontoniumSword;
	public static Item fronisiumPickaxe;
	public static Item fronisiumAxe;
	public static Item fronisiumHoe;
	public static Item fronisiumSpade;
	public static Item fronisiumSword;

	public static EnumToolMaterial TOOLQUON = EnumHelper.addToolMaterial("quontonium", 4, 1646, 10.0F, 5.75F, 16);//Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
	public static EnumToolMaterial TOOLFRONIS = EnumHelper.addToolMaterial("fronisium", 4, 1710, 10.5F, 6.00F, 16);

	public static void initItems()
	{
		DionaToolsItems.quontoniumPickaxe = new DionaItemQuontoniumPickaxe(DionaConfigManager.idToolQuontoniumPickaxe, TOOLQUON).setUnlocalizedName("quontoniumPickaxe");
		DionaToolsItems.quontoniumAxe = new DionaItemQuontoniumAxe(DionaConfigManager.idToolQuontoniumAxe, TOOLQUON).setUnlocalizedName("quontoniumAxe");
		DionaToolsItems.quontoniumHoe = new DionaItemQuontoniumHoe(DionaConfigManager.idToolQuontoniumHoe, TOOLQUON).setUnlocalizedName("quontoniumHoe");
		DionaToolsItems.quontoniumSpade = new DionaItemQuontoniumSpade(DionaConfigManager.idToolQuontoniumSpade, TOOLQUON).setUnlocalizedName("quontoniumSpade");
		DionaToolsItems.quontoniumSword = new DionaItemQuontoniumSword(DionaConfigManager.idToolQuontoniumSword, TOOLQUON).setUnlocalizedName("quontoniumSword");
		DionaToolsItems.fronisiumPickaxe = new DionaItemFronisiumPickaxe(DionaConfigManager.idToolFronisiumPickaxe, TOOLFRONIS).setUnlocalizedName("fronisiumPickaxe");
		DionaToolsItems.fronisiumAxe = new DionaItemFronisiumAxe(DionaConfigManager.idToolFronisiumAxe, TOOLFRONIS).setUnlocalizedName("fronisiumAxe");
		DionaToolsItems.fronisiumHoe = new DionaItemFronisiumHoe(DionaConfigManager.idToolFronisiumHoe, TOOLFRONIS).setUnlocalizedName("fronisiumHoe");
		DionaToolsItems.fronisiumSpade = new DionaItemFronisiumSpade(DionaConfigManager.idToolFronisiumSpade, TOOLFRONIS).setUnlocalizedName("fronisiumSpade");
		DionaToolsItems.fronisiumSword = new DionaItemFronisiumSword(DionaConfigManager.idToolFronisiumSword, TOOLFRONIS).setUnlocalizedName("fronisiumSword");

		registerItems();
		registerHarvestLevels();
	}

	public static void registerHarvestLevels()
	{
		MinecraftForge.setToolClass(quontoniumPickaxe, "pickaxe", 4);
		MinecraftForge.setToolClass(quontoniumAxe, "axe", 4);
		MinecraftForge.setToolClass(quontoniumSpade, "shovel", 4);
		MinecraftForge.setToolClass(fronisiumPickaxe, "pickaxe", 4);
		MinecraftForge.setToolClass(fronisiumAxe, "axe", 4);
		MinecraftForge.setToolClass(fronisiumSpade, "shovel", 4);
	}

	public static void registerItems()
	{
		registerItem(quontoniumPickaxe);
		registerItem(quontoniumAxe);
		registerItem(quontoniumHoe);
		registerItem(quontoniumSpade);
		registerItem(quontoniumSword);
		registerItem(fronisiumPickaxe);
		registerItem(fronisiumAxe);
		registerItem(fronisiumHoe);
		registerItem(fronisiumSpade);
		registerItem(fronisiumSword);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}