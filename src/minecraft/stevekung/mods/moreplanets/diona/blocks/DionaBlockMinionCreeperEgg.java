/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.entities.DionaEntityMinionCreeper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaBlockMinionCreeperEgg extends BlockDragonEgg
{
	public DionaBlockMinionCreeperEgg(int par1)
	{
		super(par1);
		this.setResistance(0.0F);
		this.setHardness(-1.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("diona:minionCreeperEgg");
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return 0;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.blockID;
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		return false;
	}

	@Override
	public void onBlockExploded(World par1World, int par2, int par3, int par4, Explosion explosion)
	{
		if (!par1World.isRemote)
		{
			DionaEntityMinionCreeper minionCreeper = new DionaEntityMinionCreeper(par1World);
			minionCreeper.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
			par1World.spawnEntityInWorld(minionCreeper);
		}
		par1World.setBlockToAir(par2, par3, par4);
		this.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
	}
}