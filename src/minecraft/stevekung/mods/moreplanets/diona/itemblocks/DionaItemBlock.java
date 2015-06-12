/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.itemblocks;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaItemBlock extends ItemBlock
{
	public static final String[] types = new String[] {"quontoniumOre",
		"dionaSiliconOre",
		"dionaAluminiumOre",
		"dionaTitaniumOre",
		"baronsiumOre",
		"fronisiumOre",
		"dionaTurf",
		"dionaDirt",
		"dionaStone",
		"quontoniumBlock",
		"baronsiumBlock",
		"quontoniumDecorationSmooth",
		"quontoniumDecorationBrick",
		"quontoniumDecorationChiseled",
	"dionaDungeonBrick"};

	public DionaItemBlock(int par1)
	{
		super(par1);
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
		int meta = itemstack.getItemDamage();

		if (meta < 0 || meta >= types.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + types[meta];
	}
}