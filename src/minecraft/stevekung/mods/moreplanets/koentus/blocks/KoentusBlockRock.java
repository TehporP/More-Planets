/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;

public class KoentusBlockRock extends Block
{
	private Icon[] koentusBlockIcon;

	public KoentusBlockRock(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 3.0F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.koentusBlockIcon = new Icon[1];
		this.koentusBlockIcon[0] = par1IconRegister.registerIcon("koentus:koentusHardenedStone");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.koentusBlockIcon[0];
		}
		return this.koentusBlockIcon[0];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 1; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0)
		{
			return 3.0F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0)
		{
			return 3.0F;
		}
		return 1.0F;
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