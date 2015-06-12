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
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockCookie extends Block
{
	public FronosBlockCookie(int par1)
	{
		super(par1, Material.cake);
		this.setStepSound(Block.soundGravelFootstep);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Item.cookie.itemID;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(5);
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		par1World.getBlockMetadata(par2, par3, par4);

		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		world.getBlockMetadata(x, y, z);

		return 1.0F;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("fronos:cookieBlock");
	}
}