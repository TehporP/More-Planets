/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.items;

import micdoodle8.mods.galacticraft.api.transmission.core.item.IItemElectric;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KapteynBItemUraniumBattery extends Item implements IItemElectric
{
	public KapteynBItemUraniumBattery(int id)
	{
		super(id);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleKapteynB.kapteynB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public float getMaxElectricityStored(ItemStack itemStack)
	{
		return 25000;
	}

	@Override
	public float getVoltage(ItemStack itemStack)
	{
		return 120;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("kapteynb:uraniumBattery");
	}

	@Override
	public float recharge(ItemStack itemStack, float energy, boolean doRecharge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float discharge(ItemStack itemStack, float energy,
			boolean doDischarge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getElectricityStored(ItemStack theItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setElectricity(ItemStack itemStack, float joules) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getTransfer(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 0;
	}
}