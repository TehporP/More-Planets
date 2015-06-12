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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class BlockSiriusGlowstone extends Block
{
	public BlockSiriusGlowstone(String name)
	{
		super(Material.glass);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
		this.setStepSound(Block.soundTypeGlass);
		this.setBlockName(name);
		this.setBlockTextureName("siriusb:sirius_glowstone");
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 2 + rand.nextInt(3);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return SiriusBItems.sirius_glowstone_dust;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetsCore.mpBlocksTab;
	}

	@Override
	public MapColor getMapColor(int meta)
	{
		return MapColor.diamondColor;
	}
}