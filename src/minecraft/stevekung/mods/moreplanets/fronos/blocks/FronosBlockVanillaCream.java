/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosBlockVanillaCream extends Block
{
	public FronosBlockVanillaCream(int id)
	{
		super(id, Material.craftedSnow);
		this.setStepSound(Block.soundSnowFootstep);
		this.blockHardness = 0.2F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("fronos:vanillaCream");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosItems.creamBall.itemID;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 4;
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 11)
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}
}