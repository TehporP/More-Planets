/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items;

import stevekung.mods.moreplanets.common.items.ItemMorePlanets;

public class ItemSiriusBDungeonKey extends ItemMorePlanets //implements IKeyItem
{
	public ItemSiriusBDungeonKey(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
	}

	//	@Override
	//	public int getTier(ItemStack itemStack)
	//	{
	//		return 8;
	//	}
}