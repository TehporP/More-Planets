/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.IPowerCrystal;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBasicKapteynB extends ItemBaseMP implements IPowerCrystal
{
	public ItemBasicKapteynB(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "frozen_iron_ingot", "uranium_gem", "compressed_frozen_iron", "uranium_stick", "frozen_iron_stick", "ice_crystal_shard" };
	}

	@Override
	public String getTexturesFolder()
	{
		return "kapteynb";
	}

	@Override
	public boolean isPowerCrystal(int meta)
	{
		return meta == 1 || meta == 5;
	}

	@Override
	public int getPowerCrystalBurnTime(int meta)
	{
		return meta == 1 ? 16000 : meta == 5 ? 6400 : 0;
	}

	/*@Override
	public boolean onEntityItemUpdate(EntityItem item)
	{
		ItemStack itemStack = item.getEntityItem();
		World world = item.worldObj;

		if (item.worldObj.getBlock((int)Math.floor(item.posX), (int)Math.floor(item.posY), (int)Math.floor(item.posZ)) == KapteynBBlocks.frozen_water)
		{
			if (itemStack.getItemDamage() == 5 && itemStack.stackSize == 5)
			{
				if (item.ticksExisted % 50 == 0)
				{
					item.setEntityItemStack(new ItemStack(Items.diamond));
				}
			}
		}
		return false;
	}*/
}