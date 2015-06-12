/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items.armor;
//package stevekung.mods.moreplanets.core.items.armor;
//
//import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
//import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.EnumRarity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.core.MorePlanetCore;
//
//public abstract class ItemBreathableArmor extends ItemArmor implements IBreathableArmor
//{
//	public ItemBreathableArmor(ArmorMaterial material, int render, int type)
//	{
//		super(material, render, type);
//	}
//
//	@Override
//	public CreativeTabs getCreativeTab()
//	{
//		return MorePlanetCore.mpArmorTab;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public EnumRarity getRarity(ItemStack itemStack)
//	{
//		return ClientProxyCore.galacticraftItem;
//	}
//
//	@Override
//	public boolean canBreathe(ItemStack itemStack, EntityPlayer player, EnumGearType type)
//	{
//		if (itemStack.getItem() == this.getBreathableArmor())
//		{
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
//	{
//		if (itemStack2.getItem() == this.getRepairItems() && itemStack2.getItemDamage() == this.getRepairItemsMetadata())
//		{
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public boolean handleGearType(EnumGearType type)
//	{
//		if (type == this.getGearType())
//		{
//			return true;
//		}
//		return false;
//	}
//
//	public abstract Item getRepairItems();
//	public abstract int getRepairItemsMetadata();
//	public abstract EnumGearType getGearType();
//	public abstract Item getBreathableArmor();
//}