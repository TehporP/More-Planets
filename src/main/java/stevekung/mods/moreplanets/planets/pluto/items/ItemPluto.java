/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.IPowerCrystal;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;

public class ItemPluto extends ItemBaseMP implements IPowerCrystal
{
	public ItemPluto(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "xeonium_gem" };
	}

	@Override
	public boolean isPowerCrystal(int meta)
	{
		return true;
	}

	@Override
	public int getPowerCrystalBurnTime(int meta)
	{
		return 3600;
	}
}