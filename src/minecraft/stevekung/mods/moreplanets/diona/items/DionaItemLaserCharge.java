/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemLaserCharge extends Item
{
	private static final String[] laserTypes = new String[] {
		"normalLaserCharge",
		"hyperLaserCharge",
		"empLaserCharge"
	};

	private Icon[] textures;

	public DionaItemLaserCharge(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < laserTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= laserTypes.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + laserTypes[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[laserTypes.length];

		for (int i = 0; i < laserTypes.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("diona:" + laserTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return this.textures[meta];
	}
}