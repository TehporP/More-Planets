/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.ItemAxeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemHoeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemPickaxeMP;
import stevekung.mods.moreplanets.core.items.tools.ItemShovelMP;
import stevekung.mods.moreplanets.core.items.tools.ItemSwordMP;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.stevecore.RegisterHelper;

public class KoentusToolsItems
{
	public static Item white_crystal_pickaxe;
	public static Item white_crystal_axe;
	public static Item white_crystal_hoe;
	public static Item white_crystal_shovel;
	public static Item white_crystal_sword;
	public static Item koentus_meteoric_iron_pickaxe;
	public static Item koentus_meteoric_iron_axe;
	public static Item koentus_meteoric_iron_hoe;
	public static Item koentus_meteoric_iron_shovel;
	public static Item koentus_meteoric_iron_sword;

	/**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
	public static ToolMaterial white_crystal = EnumHelper.addToolMaterial("white_crystal", 4, 1612, 9.25F, 3.25F, 8);
	public static ToolMaterial koentus_meteoric_iron = EnumHelper.addToolMaterial("koentus_meteor", 4, 1654, 9.5F, 3.75F, 8);

	public static void init()
	{
		KoentusToolsItems.initItems();
		KoentusToolsItems.registerItems();
		KoentusToolsItems.registerHarvestLevels();
	}

	private static void initItems()
	{
		KoentusToolsItems.white_crystal_pickaxe = new ItemPickaxeMP("white_crystal_pickaxe", KoentusToolsItems.white_crystal, KoentusItems.koentus_item, 5, "koentus:white_crystal_pickaxe");
		KoentusToolsItems.white_crystal_axe = new ItemAxeMP("white_crystal_axe", KoentusToolsItems.white_crystal, KoentusItems.koentus_item, 5, "koentus:white_crystal_axe");
		KoentusToolsItems.white_crystal_hoe = new ItemHoeMP("white_crystal_hoe", KoentusToolsItems.white_crystal, KoentusItems.koentus_item, 5, "koentus:white_crystal_hoe");
		KoentusToolsItems.white_crystal_shovel = new ItemShovelMP("white_crystal_spade", KoentusToolsItems.white_crystal, KoentusItems.koentus_item, 5, "koentus:white_crystal_shovel");
		KoentusToolsItems.white_crystal_sword = new ItemSwordMP("white_crystal_sword", KoentusToolsItems.white_crystal, KoentusItems.koentus_item, 5, "koentus:white_crystal_sword");
		KoentusToolsItems.koentus_meteoric_iron_pickaxe = new ItemPickaxeMP("koentus_meteor_pickaxe", KoentusToolsItems.koentus_meteoric_iron, KoentusItems.koentus_item, 6, "koentus:koentus_meteoric_iron_pickaxe");
		KoentusToolsItems.koentus_meteoric_iron_axe = new ItemAxeMP("koentus_meteor_axe", KoentusToolsItems.koentus_meteoric_iron, KoentusItems.koentus_item, 6, "koentus:koentus_meteoric_iron_axe");
		KoentusToolsItems.koentus_meteoric_iron_hoe = new ItemHoeMP("koentus_meteor_hoe", KoentusToolsItems.koentus_meteoric_iron, KoentusItems.koentus_item, 6, "koentus:koentus_meteoric_iron_hoe");
		KoentusToolsItems.koentus_meteoric_iron_shovel = new ItemShovelMP("koentus_meteor_spade", KoentusToolsItems.koentus_meteoric_iron, KoentusItems.koentus_item, 6, "koentus:koentus_meteoric_iron_shovel");
		KoentusToolsItems.koentus_meteoric_iron_sword = new ItemSwordMP("koentus_meteor_sword", KoentusToolsItems.koentus_meteoric_iron, KoentusItems.koentus_item, 6, "koentus:koentus_meteoric_iron_sword");
	}

	private static void registerHarvestLevels()
	{
		KoentusToolsItems.white_crystal_pickaxe.setHarvestLevel("pickaxe", 4);
		KoentusToolsItems.white_crystal_axe.setHarvestLevel("axe", 4);
		KoentusToolsItems.white_crystal_shovel.setHarvestLevel("shovel", 4);
		KoentusToolsItems.koentus_meteoric_iron_pickaxe.setHarvestLevel("pickaxe", 4);
		KoentusToolsItems.koentus_meteoric_iron_axe.setHarvestLevel("axe", 4);
		KoentusToolsItems.koentus_meteoric_iron_shovel.setHarvestLevel("shovel", 4);
	}

	private static void registerItems()
	{
		RegisterHelper.registerItem(KoentusToolsItems.white_crystal_pickaxe);
		RegisterHelper.registerItem(KoentusToolsItems.white_crystal_axe);
		RegisterHelper.registerItem(KoentusToolsItems.white_crystal_hoe);
		RegisterHelper.registerItem(KoentusToolsItems.white_crystal_shovel);
		RegisterHelper.registerItem(KoentusToolsItems.white_crystal_sword);
		RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_pickaxe);
		RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_axe);
		RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_hoe);
		RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_shovel);
		RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_sword);
	}
}