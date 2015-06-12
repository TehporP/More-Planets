/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.config.ConfigManagerMP;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;

public class ItemTier4RocketSchematic extends ItemBaseMP /*implements ISchematicItem*/
{
	public ItemTier4RocketSchematic(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		if (ConfigManagerMP.enableRocketWithThaiFlag)
		{
			for (int i = 0; i < this.getItemVariantsName().length; i++)
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
		else
		{
			for (int i = 0; i < this.getItemVariantsName().length; i++)
			{
				if (i != 0)
				{
					list.add(new ItemStack(this, 1, i));
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (player.worldObj.isRemote)
		{
			if (itemStack.getItemDamage() == 0)
			{
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier4.rocket.name"));
			}
			else if (itemStack.getItemDamage() == 1)
			{
				if (ConfigManagerMP.enableRocketWithThaiFlag)
				{
					list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier4.rocket.noflag.name"));
				}
				else
				{
					list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier4.rocket.name"));
				}
			}
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "tier_4_rocket_schematic", "tier_4_rocket_schematic_no_flag" };
	}
}