/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseSlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBlockCheeseSlimeEgg extends BlockDragonEgg
{
	public PolongniusBlockCheeseSlimeEgg(int par1, Material par2)
	{
		super(par1);
		this.setResistance(0.0F);
		this.setHardness(-1.0F);
		this.setStepSound(Block.soundClothFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("polongnius:polongniusSlime");
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5 - 1);
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
		return ModulePolongnius.polongniusTab;
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
			PolongniusEntityCheeseSlime slime = new PolongniusEntityCheeseSlime(par1World);
			slime.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
			par1World.spawnEntityInWorld(slime);
			slime.setSlimeSize(par1World.rand.nextInt(3));
		}
		par1World.setBlockToAir(par2, par3, par4);
		par1World.playSoundEffect(par2, par3, par4, "mob.slime.big", 1.0F, 1.0F);
		this.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
	}
}