/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.items.armor;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class NibiruArmorItems
{
	public static Item redGemHelmet;
	public static Item redGemChestplate;
	public static Item redGemLeggings;
	public static Item redGemBoots;
	public static Item noriumHelmet;
	public static Item noriumChestplate;
	public static Item noriumLeggings;
	public static Item noriumBoots;
	public static Item breathableRedGemHelmet;
	public static Item breathableNoriumHelmet;

	public static EnumArmorMaterial ARMORREDGEM = EnumHelper.addArmorMaterial("redGem", 72, new int[] { 15, 18, 16, 13 }, 16);//Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],enchantability
	public static EnumArmorMaterial ARMORNORIUM = EnumHelper.addArmorMaterial("norium", 74, new int[] { 16, 19, 17, 12 }, 16);

	public static void initItems()
	{
		NibiruArmorItems.redGemHelmet = new NibiruArmorRedGem(NibiruConfigManager.idArmorRedGemHelmet, ARMORREDGEM, 7, 0).setUnlocalizedName("redGemHelmet");
		NibiruArmorItems.redGemChestplate = new NibiruArmorRedGem(NibiruConfigManager.idArmorRedGemChestplate, ARMORREDGEM, 7, 1).setUnlocalizedName("redGemChestplate");
		NibiruArmorItems.redGemLeggings = new NibiruArmorRedGem(NibiruConfigManager.idArmorRedGemLeggings, ARMORREDGEM, 7, 2).setUnlocalizedName("redGemLeggings");
		NibiruArmorItems.redGemBoots = new NibiruArmorRedGem(NibiruConfigManager.idArmorRedGemBoots, ARMORREDGEM, 7, 3).setUnlocalizedName("redGemBoots");
		NibiruArmorItems.noriumHelmet = new NibiruArmorNorium(NibiruConfigManager.idArmorNoriumHelmet, ARMORNORIUM, 7, 0).setUnlocalizedName("noriumHelmet");
		NibiruArmorItems.noriumChestplate = new NibiruArmorNorium(NibiruConfigManager.idArmorNoriumChestplate, ARMORNORIUM, 7, 1).setUnlocalizedName("noriumChestplate");
		NibiruArmorItems.noriumLeggings = new NibiruArmorNorium(NibiruConfigManager.idArmorNoriumLeggings, ARMORNORIUM, 7, 2).setUnlocalizedName("noriumLeggings");
		NibiruArmorItems.noriumBoots = new NibiruArmorNorium(NibiruConfigManager.idArmorNoriumBoots, ARMORNORIUM, 7, 3).setUnlocalizedName("noriumBoots");
		NibiruArmorItems.breathableRedGemHelmet = new NibiruArmorBreathableRedGem(NibiruConfigManager.idArmorBreathableRedGemHelmet, ARMORREDGEM, 7, 0).setUnlocalizedName("breathableRedGemHelmet");
		NibiruArmorItems.breathableNoriumHelmet = new NibiruArmorBreathableNorium(NibiruConfigManager.idArmorBreathableNoriumHelmet, ARMORNORIUM, 7, 0).setUnlocalizedName("breathableNoriumHelmet");

		registerItems();
	}

	public static void registerItems()
	{
		registerItem(redGemHelmet);
		registerItem(redGemChestplate);
		registerItem(redGemLeggings);
		registerItem(redGemBoots);
		registerItem(noriumHelmet);
		registerItem(noriumChestplate);
		registerItem(noriumLeggings);
		registerItem(noriumBoots);
		registerItem(breathableRedGemHelmet);
		registerItem(breathableNoriumHelmet);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}