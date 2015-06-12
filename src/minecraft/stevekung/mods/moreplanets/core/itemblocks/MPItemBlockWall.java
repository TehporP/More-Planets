/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MPItemBlockWall extends ItemBlock
{
	public MPItemBlockWall(int block)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyMP.morePlanetItemRarity;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = ".";

		switch (itemstack.getItemDamage())
		{
		case 0:
			name = "tinWall";
			break;
		case 1:
			name = "tinWall2";
			break;
		case 2:
			name = "marsStone";
			break;
		case 3:
			name = "polongniusCobblestone";
			break;
		case 4:
			name = "nibiruCobblestone";
			break;
		case 5:
			name = "quonBrickWall";
			break;
		case 6:
			name = "koentusCobblestoneWall";
			break;
		case 7:
			name = "fronosCobblestoneWall";
			break;
		case 8:
			name = "kapteynBCobblestoneWall";
			break;
		default:
			name = "null";
		}
		return Block.blocksList[this.getBlockID()].getUnlocalizedName() + "." + name;
	}

	@Override
	public String getUnlocalizedName()
	{
		return Block.blocksList[this.getBlockID()].getUnlocalizedName() + ".0";
	}
}