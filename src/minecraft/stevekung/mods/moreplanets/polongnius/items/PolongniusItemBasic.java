/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusItemBasic extends Item
{
	public static String[] names = {
		"flonium",//0
		"polongniusMeteorRaw",//1
		"palladiumRaw",//2
		"polongniusMeteorIngot",//3
		"palladiumIngot",//4
		"polongniusMeteorPlate",//5
		"palladiumPlate",//6
		"polongniusStick",//7
		"palladiumStick",//8
		"leatherOfCheese",//9
		"cheeseSpore"//10
	};
	protected Icon[] icons = new Icon[names.length];

	public PolongniusItemBasic(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public float getSmeltingExperience(ItemStack item)
	{
		return 2.5F;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	public Icon getIconFromDamage(int damage)
	{
		if (this.icons.length > damage)
		{
			return this.icons[damage];
		}
		return super.getIconFromDamage(damage);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < names.length; i++)
		{
			if (i != 10)
			{
				par3List.add(new ItemStack(par1, 1, i));
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + names[par1ItemStack.getItemDamage()];
		}
		return "unnamed";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		int i = 0;

		for (String name : names)
		{
			this.icons[i++] = iconRegister.registerIcon("polongnius:" + name);
		}
	}
}