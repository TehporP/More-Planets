/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KapteynBBlockOre extends Block
{
	private Icon[] kapteynBlockIcons;

	public KapteynBBlockOre(int block)
	{
		super(block, Material.rock);
		this.blockHardness = 4.0F;
		this.setStepSound(Block.soundMetalFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.kapteynBlockIcons = new Icon[4];
		this.kapteynBlockIcons[0] = par1IconRegister.registerIcon("kapteynb:iridiumBlock");
		this.kapteynBlockIcons[1] = par1IconRegister.registerIcon("kapteynb:elementiumBlock");
		this.kapteynBlockIcons[2] = par1IconRegister.registerIcon("kapteynb:frozenIronBlock");
		this.kapteynBlockIcons[3] = par1IconRegister.registerIcon("kapteynb:uraniumBlock");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKapteynB.kapteynB;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		return this.blockHardness;
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return super.canHarvestBlock(player, meta);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.kapteynBlockIcons[0];
		case 1:
			return this.kapteynBlockIcons[1];
		case 2:
			return this.kapteynBlockIcons[2];
		case 3:
			return this.kapteynBlockIcons[3];
		default:
			return this.kapteynBlockIcons[0];
		}
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 4; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.blockID;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		return meta;
	}
}