/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockSand extends BlockSand
{
	private Icon[] sandIcon;

	public FronosBlockSand(int par1)
	{
		super(par1, Material.sand);
		this.setStepSound(Block.soundSandFootstep);
		this.setHardness(0.5F);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.sandIcon = new Icon[2];
		this.sandIcon[0] = par1IconRegister.registerIcon("fronos:fronosSand");
		this.sandIcon[1] = par1IconRegister.registerIcon("fronos:whiteSand");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.sandIcon[0];
		case 1:
			return this.sandIcon[1];
		}
		return this.blockIcon;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}