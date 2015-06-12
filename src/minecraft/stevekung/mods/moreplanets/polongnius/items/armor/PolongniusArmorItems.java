/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items.armor;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class PolongniusArmorItems
{
	public static Item leatherOfCheeseHelmet;
	public static Item leatherOfCheeseChestplate;
	public static Item leatherOfCheeseLeggings;
	public static Item leatherOfCheeseBoots;
	public static Item polongniusMeteorHelmet;
	public static Item polongniusMeteorChestplate;
	public static Item polongniusMeteorLeggings;
	public static Item polongniusMeteorBoots;
	public static Item palladiumHelmet;
	public static Item palladiumChestplate;
	public static Item palladiumLeggings;
	public static Item palladiumBoots;
	public static Item breathablePolongniusMeteorHelmet;
	public static Item breathablePalladiumHelmet;

	public static EnumArmorMaterial ARMORCHEESE = EnumHelper.addArmorMaterial("leatherOfCheese", 56, new int[] { 7, 10, 9, 6 }, 16);//Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],enchantability
	public static EnumArmorMaterial ARMORMETEOR = EnumHelper.addArmorMaterial("meteor", 67, new int[] { 13, 17, 11, 10 }, 16);
	public static EnumArmorMaterial ARMOR_PALLADIUM = EnumHelper.addArmorMaterial("palladium", 70, new int[] { 15, 19, 13, 12 }, 16);

	public static void initItems()
	{
		PolongniusArmorItems.leatherOfCheeseHelmet = new PolongniusArmorLeatherOfCheese(PolongniusConfigManager.idArmorLeatherOfCheeseHelmet, ARMORCHEESE, 7, 0).setUnlocalizedName("leatherOfCheeseHelmet");
		PolongniusArmorItems.leatherOfCheeseChestplate = new PolongniusArmorLeatherOfCheese(PolongniusConfigManager.idArmorLeatherOfCheeseChestplate, ARMORCHEESE, 7, 1).setUnlocalizedName("leatherOfCheeseChestplate");
		PolongniusArmorItems.leatherOfCheeseLeggings = new PolongniusArmorLeatherOfCheese(PolongniusConfigManager.idArmorLeatherOfCheeseLeggings, ARMORCHEESE, 7, 2).setUnlocalizedName("leatherOfCheeseLeggings");
		PolongniusArmorItems.leatherOfCheeseBoots = new PolongniusArmorLeatherOfCheese(PolongniusConfigManager.idArmorLeatherOfCheeseBoots, ARMORCHEESE, 7, 3).setUnlocalizedName("leatherOfCheeseBoots");
		PolongniusArmorItems.polongniusMeteorHelmet = new PolongniusArmorMeteor(PolongniusConfigManager.idArmorPolongniusMeteorHelmet, ARMORMETEOR, 7, 0).setUnlocalizedName("polongniusMeteorHelmet");
		PolongniusArmorItems.polongniusMeteorChestplate = new PolongniusArmorMeteor(PolongniusConfigManager.idArmorPolongniusMeteorChestplate, ARMORMETEOR, 7, 1).setUnlocalizedName("polongniusMeteorChestplate");
		PolongniusArmorItems.polongniusMeteorLeggings = new PolongniusArmorMeteor(PolongniusConfigManager.idArmorPolongniusMeteorLeggings, ARMORMETEOR, 7, 2).setUnlocalizedName("polongniusMeteorLeggings");
		PolongniusArmorItems.polongniusMeteorBoots = new PolongniusArmorMeteor(PolongniusConfigManager.idArmorPolongniusMeteorBoots, ARMORMETEOR, 7, 3).setUnlocalizedName("polongniusMeteorBoots");
		PolongniusArmorItems.palladiumHelmet = new PolongniusArmorPalladium(PolongniusConfigManager.idArmorPalladiumHelmet, ARMOR_PALLADIUM, 7, 0).setUnlocalizedName("palladiumHelmet");
		PolongniusArmorItems.palladiumChestplate = new PolongniusArmorPalladium(PolongniusConfigManager.idArmorPalladiumChestplate, ARMOR_PALLADIUM, 7, 1).setUnlocalizedName("palladiumChestplate");
		PolongniusArmorItems.palladiumLeggings = new PolongniusArmorPalladium(PolongniusConfigManager.idArmorPalladiumLeggings, ARMOR_PALLADIUM, 7, 2).setUnlocalizedName("palladiumLeggings");
		PolongniusArmorItems.palladiumBoots = new PolongniusArmorPalladium(PolongniusConfigManager.idArmorPalladiumBoots, ARMOR_PALLADIUM, 7, 3).setUnlocalizedName("palladiumBoots");
		PolongniusArmorItems.breathablePolongniusMeteorHelmet = new PolongniusArmorBreathableMeteor(PolongniusConfigManager.idArmorBreathablePolongniusMeteorHelmet, ARMORMETEOR, 7, 0).setUnlocalizedName("breathablePolongniusMeteorHelmet");
		PolongniusArmorItems.breathablePalladiumHelmet = new PolongniusArmorBreathablePalladium(PolongniusConfigManager.idArmorBreathablePalladiumHelmet, ARMOR_PALLADIUM, 7, 0).setUnlocalizedName("breathablePalladiumHelmet");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(leatherOfCheeseHelmet);
		registerItem(leatherOfCheeseChestplate);
		registerItem(leatherOfCheeseLeggings);
		registerItem(leatherOfCheeseBoots);
		registerItem(polongniusMeteorHelmet);
		registerItem(polongniusMeteorChestplate);
		registerItem(polongniusMeteorLeggings);
		registerItem(polongniusMeteorBoots);
		registerItem(palladiumHelmet);
		registerItem(palladiumChestplate);
		registerItem(palladiumLeggings);
		registerItem(palladiumBoots);
		registerItem(breathablePolongniusMeteorHelmet);
		registerItem(breathablePalladiumHelmet);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}