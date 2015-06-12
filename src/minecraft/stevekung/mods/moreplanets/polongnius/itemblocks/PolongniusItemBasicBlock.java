/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusItemBasicBlock extends ItemBlock
{
	public PolongniusItemBasicBlock(int i)
	{
		super(i);
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
	public CreativeTabs getCreativeTab()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	public String getUnlocalizedName()
	{
		return Block.blocksList[this.getBlockID()].getUnlocalizedName() + ".0";
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = ".";

		switch (itemstack.getItemDamage())
		{
		case 0:
			name = "polongniusCopperOre";
			break;
		case 1:
			name = "polongniusTinOre";
			break;
		case 2:
			name = "polongniusIronOre";
			break;
		case 3:
			name = "palladiumOre";
			break;
		case 4:
			name = "floniumOre";
			break;
		case 5:
			name = "cheeseOfMilkOre";
			break;
		case 6:
			name = "polongniusMeteorOre";
			break;
		case 7:
			name = "polongniusCheeseStone";
			break;
		case 8:
			name = "polongniusDirt";
			break;
		case 9:
			name = "polongniusStone";
			break;
		case 10:
			name = "polongniusCobblestone";
			break;
		case 11:
			name = "polongniusMeteorBlock";
			break;
		case 12:
			name = "floniumBlock";
			break;
		case 13:
			name = "palladiumBlock";
			break;
		case 14:
			name = "polongniusBrick";
			break;
		default:
			name = "null";
		}

		return Block.blocksList[this.getBlockID()].getUnlocalizedName() + "." + name;
	}
}