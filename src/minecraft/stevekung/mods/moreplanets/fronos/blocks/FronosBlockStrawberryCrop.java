/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockStrawberryCrop extends BlockCrops
{
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public FronosBlockStrawberryCrop(int par1)
	{
		super(par1);
	}

	@Override
	protected int getCropItem()
	{
		return FronosItems.fronosFood.itemID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		if (par2 < 7)
		{
			if (par2 == 6)
			{
				par2 = 5;
			}
			return this.iconArray[par2 >> 1];
		}
		else
		{
			return this.iconArray[3];
		}
	}

	@Override
	protected int getSeedItem()
	{
		return FronosItems.strawberrySeed.itemID;
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == FronosBlocks.fronosFarmland.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.iconArray = new Icon[4];

		for (int i = 0; i < this.iconArray.length; ++i)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("fronos:strawberryBush" + "_stage_" + i);
		}
	}
}