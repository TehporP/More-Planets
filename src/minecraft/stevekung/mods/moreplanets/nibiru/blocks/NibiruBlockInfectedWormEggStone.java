/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedWorm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruBlockInfectedWormEggStone extends Block
{
	public NibiruBlockInfectedWormEggStone(int id)
	{
		super(id, Material.rock);
		this.setResistance(5.0F);
		this.setHardness(4.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
	{
		if (!par1World.isRemote)
		{
			NibiruEntityInfectedWorm infectedWorm = new NibiruEntityInfectedWorm(par1World);
			infectedWorm.setLocationAndAngles(par2 + 0.5D, par3, par4 + 0.5D, 0.0F, 0.0F);
			par1World.spawnEntityInWorld(infectedWorm);
			infectedWorm.spawnExplosionParticle();
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("nibiru:infectedWormEgg");
	}
}