/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockMorePlanets;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCandyCane1;

public class ItemBlockCandyCane1 extends ItemBlockMorePlanets
{
	public ItemBlockCandyCane1(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 3;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName() + "." + BlockCandyCane1.BlockType.byMetadata(itemStack.getMetadata()).getUnlocalizedName();
	}
}