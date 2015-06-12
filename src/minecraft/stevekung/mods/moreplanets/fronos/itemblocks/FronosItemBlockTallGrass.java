/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBlockTallGrass extends ItemBlock
{
	private static final String[] fronosGrass = new String[] {
		"fronosShortGrass",//0
		"fronosMediumGrass",//1
		"fronosTallGrass",//2
		"fronosPinkShortGrass",//3
		"fronosPinkMediumGrass",//4
		"fronosPinkTallGrass",//5
		"fronosPurpleShortGrass",//6
		"fronosPurpleMediumGrass",//7
		"fronosPurpleTallGrass",//8
		"fronosPlainsShortGrass",//9
		"fronosPlainsMediumGrass",//10
		"fronosPlainsTallGrass",//11
		"fronosGoldenTallGrass"//12
	};

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public FronosItemBlockTallGrass(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();

		if (meta < 0 || meta >= fronosGrass.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + fronosGrass[meta];
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return Block.blocksList[this.itemID].getIcon(0, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}
}