/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public class BlockCookie extends BlockBaseMP
{
	public BlockCookie(String name)
	{
		super(Material.clay);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeGravel);
		this.setUnlocalizedName(name);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.cookie;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(4) + 1;
	}
}