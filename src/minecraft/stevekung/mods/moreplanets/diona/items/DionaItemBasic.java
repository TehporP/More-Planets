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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemBasic extends Item
{
	public static String[] names = {
		"quontoniumIngot",//0
		"baronsiumIngot",//1
		"fronisiumIngot",//2
		"titaniumIngot",//3
		"quontoniumPlate",//4
		"fronisiumPlate",//5
		"heavyDutyPlateT3",//6
		"heavyDutyPlateT4",//7
		"rawKoentusMeteor",//8
		"koentusMeteorIngot",//9
		"koentusMeteorPlate",//10
		"redCanvas",//11
		"whiteCanvas",//12
		"blueCanvas",//13
		"thailandCanvasBottom",//14
		"thailandCanvasUp",//15
		"quontoniumStick",//16
		"fronisiumStick"//17
	};
	protected Icon[] icons = new Icon[DionaItemBasic.names.length];

	public DionaItemBasic(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (par2EntityPlayer.worldObj.isRemote)
		{
			switch (par1ItemStack.getItemDamage())
			{
			case 6:
				par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier3.name"));
				break;
			case 7:
				par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier4.name"));
				break;
			case 14:
				par3List.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("desc.red.canvas.name"));
				par3List.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("desc.white.canvas.name"));
				par3List.add(EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("desc.blue.canvas.name"));
				break;
			case 15:
				par3List.add(EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("desc.blue.canvas.name"));
				par3List.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("desc.white.canvas.name"));
				par3List.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("desc.red.canvas.name"));
				break;
			}
		}
	}

	@Override
	public float getSmeltingExperience(ItemStack item)
	{
		return 2.5F;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return ModuleDiona.dionaTab;
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
		for (int i = 0; i < DionaItemBasic.names.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + DionaItemBasic.names[par1ItemStack.getItemDamage()];
		}
		return "unnamed";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		int i = 0;

		for (String name : DionaItemBasic.names)
		{
			this.icons[i++] = iconRegister.registerIcon("diona:" + name);
		}
	}
}