/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.items.tools;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class NibiruToolsItems
{
	public static Item redGemPickaxe;
	public static Item redGemAxe;
	public static Item redGemHoe;
	public static Item redGemSpade;
	public static Item redGemSword;
	public static Item noriumPickaxe;
	public static Item noriumAxe;
	public static Item noriumHoe;
	public static Item noriumSpade;
	public static Item noriumSword;

	public static EnumToolMaterial TOOLREDGEM = EnumHelper.addToolMaterial("redGem", 6, 1854, 15.0F, 7.5F, 16);//Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
	public static EnumToolMaterial TOOLNORIUM = EnumHelper.addToolMaterial("norium", 6, 1932, 15.5F, 7.85F, 16);

	public static void initItems()
	{
		NibiruToolsItems.redGemPickaxe = new NibiruItemRedGemPickaxe(NibiruConfigManager.idToolRedGemPickaxe, TOOLREDGEM).setUnlocalizedName("redGemPickaxe");
		NibiruToolsItems.redGemAxe = new NibiruItemRedGemAxe(NibiruConfigManager.idToolRedGemAxe, TOOLREDGEM).setUnlocalizedName("redGemAxe");
		NibiruToolsItems.redGemHoe = new NibiruItemRedGemHoe(NibiruConfigManager.idToolRedGemHoe, TOOLREDGEM).setUnlocalizedName("redGemHoe");
		NibiruToolsItems.redGemSpade = new NibiruItemRedGemSpade(NibiruConfigManager.idToolRedGemSpade, TOOLREDGEM).setUnlocalizedName("redGemSpade");
		NibiruToolsItems.redGemSword = new NibiruItemRedGemSword(NibiruConfigManager.idToolRedGemSword, TOOLREDGEM).setUnlocalizedName("redGemSword");
		NibiruToolsItems.noriumPickaxe = new NibiruItemNoriumPickaxe(NibiruConfigManager.idToolNoriumPickaxe, TOOLNORIUM).setUnlocalizedName("noriumPickaxe");
		NibiruToolsItems.noriumAxe = new NibiruItemNoriumAxe(NibiruConfigManager.idToolNoriumAxe, TOOLNORIUM).setUnlocalizedName("noriumAxe");
		NibiruToolsItems.noriumHoe = new NibiruItemNoriumHoe(NibiruConfigManager.idToolNoriumHoe, TOOLNORIUM).setUnlocalizedName("noriumHoe");
		NibiruToolsItems.noriumSpade = new NibiruItemNoriumSpade(NibiruConfigManager.idToolNoriumSpade, TOOLNORIUM).setUnlocalizedName("noriumSpade");
		NibiruToolsItems.noriumSword = new NibiruItemNoriumSword(NibiruConfigManager.idToolNoriumSword, TOOLNORIUM).setUnlocalizedName("noriumSword");

		registerItems();
		registerHarvestLevels();
	}

	public static void registerHarvestLevels()
	{
		MinecraftForge.setToolClass(redGemPickaxe, "pickaxe", 6);
		MinecraftForge.setToolClass(redGemAxe, "axe", 6);
		MinecraftForge.setToolClass(redGemSpade, "shovel", 6);
		MinecraftForge.setToolClass(noriumPickaxe, "pickaxe", 6);
		MinecraftForge.setToolClass(noriumAxe, "axe", 6);
		MinecraftForge.setToolClass(noriumSpade, "shovel", 6);
	}

	public static void registerItems()
	{
		registerItem(redGemPickaxe);
		registerItem(redGemAxe);
		registerItem(redGemHoe);
		registerItem(redGemSpade);
		registerItem(redGemSword);
		registerItem(noriumPickaxe);
		registerItem(noriumAxe);
		registerItem(noriumHoe);
		registerItem(noriumSpade);
		registerItem(noriumSword);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}