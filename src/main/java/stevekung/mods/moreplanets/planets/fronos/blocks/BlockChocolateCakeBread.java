/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import stevekung.mods.moreplanets.common.blocks.BlockCakeMP;

public class BlockChocolateCakeBread extends BlockCakeMP
{
	public BlockChocolateCakeBread(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public int getFoodAmount()
	{
		return 2;
	}

	@Override
	public float getSaturationAmount()
	{
		return 0.3F;
	}
}