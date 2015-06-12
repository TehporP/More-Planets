/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusBlockFallenMeteor extends Block
{
	public KoentusBlockFallenMeteor(int id)
	{
		super(id, Material.rock);
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
		this.setHardness(50.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("koentus:koentusMeteor");
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
	{
		return 1;
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return DionaItems.dionaBasicItem.itemID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return 8;
	}

	@Override
	public boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		for (int l = 0; l < 4; ++l)
		{
			double d0 = par2 + par5Random.nextFloat();
			double d1 = par3 + par5Random.nextFloat();
			double d2 = par4 + par5Random.nextFloat();
			int i1 = par5Random.nextInt(2) * 2 - 1;

			if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID)
			{
				d0 = par2 + 0.5D + 0.25D * i1;
			}
			else
			{
				d2 = par4 + 0.5D + 0.25D * i1;
			}
			MorePlanetCore.proxy.spawnParticle("koentusSmoke", d0, d1, d2);
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			this.tryToFall(par1World, par2, par3, par4);
		}
	}

	private void tryToFall(World par1World, int par2, int par3, int par4)
	{
		if (canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
		{
			par1World.setBlock(par2, par3, par4, 0, 0, 3);

			while (canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
			{
				--par3;
			}

			if (par3 > 0)
			{
				par1World.setBlock(par2, par3, par4, this.blockID, 0, 3);
			}
		}
	}

	public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
	{
		final int var4 = par0World.getBlockId(par1, par2, par3);

		if (var4 == 0)
		{
			return true;
		}
		else if (var4 == Block.fire.blockID)
		{
			return true;
		}
		else
		{
			final Material var5 = Block.blocksList[var4].blockMaterial;
			return var5 == Material.water ? true : var5 == Material.lava;
		}
	}
}