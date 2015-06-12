/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class BlockSiriusGlowstone extends BlockBaseMP
{
	public BlockSiriusGlowstone(String name)
	{
		super(Material.glass);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
		this.setStepSound(Block.soundTypeGlass);
		this.setUnlocalizedName(name);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 2 + rand.nextInt(3);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return SiriusBItems.sirius_glowstone_dust;
	}

	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return MapColor.diamondColor;
	}
}