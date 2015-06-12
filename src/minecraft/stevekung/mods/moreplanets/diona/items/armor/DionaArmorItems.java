/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items.armor;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class DionaArmorItems
{
	public static Item quontoniumHelmet;
	public static Item quontoniumChestplate;
	public static Item quontoniumLeggings;
	public static Item quontoniumBoots;
	public static Item fronisiumHelmet;
	public static Item fronisiumChestplate;
	public static Item fronisiumLeggings;
	public static Item fronisiumBoots;
	public static Item breathableQuontoniumHelmet;
	public static Item breathableFronisiumHelmet;

	public static EnumArmorMaterial ARMOR_QUONTONIUM = EnumHelper.addArmorMaterial("quontonium", 68, new int[] { 14, 18, 16, 12 }, 20);//Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],enchantability
	public static EnumArmorMaterial ARMOR_FRONISIUM = EnumHelper.addArmorMaterial("fronisium", 69, new int[] { 15, 19, 17, 13 }, 20);

	public static void initItems()
	{
		DionaArmorItems.quontoniumHelmet = new DionaArmorQuontonium(DionaConfigManager.idArmorQuontoniumHelmet, ARMOR_QUONTONIUM, 7, 0).setUnlocalizedName("quontoniumHelmet");
		DionaArmorItems.quontoniumChestplate = new DionaArmorQuontonium(DionaConfigManager.idArmorQuontoniumChestplate, ARMOR_QUONTONIUM, 7, 1).setUnlocalizedName("quontoniumChestplate");
		DionaArmorItems.quontoniumLeggings = new DionaArmorQuontonium(DionaConfigManager.idArmorQuontoniumLeggings, ARMOR_QUONTONIUM, 7, 2).setUnlocalizedName("quontoniumLeggings");
		DionaArmorItems.quontoniumBoots = new DionaArmorQuontonium(DionaConfigManager.idArmorQuontoniumBoots, ARMOR_QUONTONIUM, 7, 3).setUnlocalizedName("quontoniumBoots");
		DionaArmorItems.fronisiumHelmet = new DionaArmorFronisium(DionaConfigManager.idArmorFronisiumHelmet, ARMOR_FRONISIUM, 7, 0).setUnlocalizedName("fronisiumHelmet");
		DionaArmorItems.fronisiumChestplate = new DionaArmorFronisium(DionaConfigManager.idArmorFronisiumChestplate, ARMOR_FRONISIUM, 7, 1).setUnlocalizedName("fronisiumChestplate");
		DionaArmorItems.fronisiumLeggings = new DionaArmorFronisium(DionaConfigManager.idArmorFronisiumLeggings, ARMOR_FRONISIUM, 7, 2).setUnlocalizedName("fronisiumLeggings");
		DionaArmorItems.fronisiumBoots = new DionaArmorFronisium(DionaConfigManager.idArmorFronisiumBoots, ARMOR_FRONISIUM, 7, 3).setUnlocalizedName("fronisiumBoots");
		DionaArmorItems.breathableQuontoniumHelmet = new DionaArmorBreathableQuontonium(DionaConfigManager.idArmorBreathableQuontoniumHelmet, ARMOR_QUONTONIUM, 7, 0).setUnlocalizedName("breathableQuontoniumHelmet");
		DionaArmorItems.breathableFronisiumHelmet = new DionaArmorBreathableFronisium(DionaConfigManager.idArmorBreathableFronisiumHelmet, ARMOR_FRONISIUM, 7, 0).setUnlocalizedName("breathableFronisiumHelmet");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(quontoniumHelmet);
		registerItem(quontoniumChestplate);
		registerItem(quontoniumLeggings);
		registerItem(quontoniumBoots);
		registerItem(fronisiumHelmet);
		registerItem(fronisiumChestplate);
		registerItem(fronisiumLeggings);
		registerItem(fronisiumBoots);
		registerItem(breathableQuontoniumHelmet);
		registerItem(breathableFronisiumHelmet);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}