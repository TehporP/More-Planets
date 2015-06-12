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
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosItemBlockOre extends ItemBlock
{
	public FronosItemBlockOre(int i)
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
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = ".";

		switch (itemstack.getItemDamage())
		{
		case 0:
			name = "greenTopazOre";
			break;
		case 1:
			name = "jellyBerryOre";
			break;
		case 2:
			name = "jellyStrawberryOre";
			break;
		case 3:
			name = "jellyGrapeOre";
			break;
		case 4:
			name = "jellyRaspberryOre";
			break;
		case 5:
			name = "jellyLimeOre";
			break;
		case 6:
			name = "jellyOrangeOre";
			break;
			/*case 7:
            name = "koentusSubStone";
            break;
        case 8:
            name = "koentusStone";
            break;
        case 9:
            name = "koentusCobbletone";
            break;
        case 10:
            name = "whiteCrystalBlock";
            break;
        case 11:
            name = "blueMineralBlock";
            break;
        case 12:
            name = "koentusBrick";
            break;*/
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