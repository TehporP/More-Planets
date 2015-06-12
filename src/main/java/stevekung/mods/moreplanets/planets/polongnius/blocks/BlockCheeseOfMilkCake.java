/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import stevekung.mods.moreplanets.common.blocks.BlockCakeMP;

public class BlockCheeseOfMilkCake extends BlockCakeMP /*implements IBlockShiftDesc*/
{
	public BlockCheeseOfMilkCake(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	//	@Override
	//	public String getShiftDescription(IBlockState state)
	//	{
	//		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
	//	}
	//
	//	@Override
	//	public boolean showDescription(IBlockState state)
	//	{
	//		return true;
	//	}

	@Override
	public int getFoodAmount()
	{
		return 4;
	}

	@Override
	public float getSaturationAmount()
	{
		return 0.6F;
	}
}