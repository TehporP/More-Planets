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
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosBlockOvantine extends Block
{
	public FronosBlockOvantine(int id)
	{
		super(id, Material.clay);
		this.setStepSound(Block.soundSandFootstep);
		this.blockHardness = 0.55F;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosItems.fronosFood2.itemID;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 4;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("fronos:ovantineBlock");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}
}