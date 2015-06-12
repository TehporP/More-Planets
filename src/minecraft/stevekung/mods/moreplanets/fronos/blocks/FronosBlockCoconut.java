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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockCoconut extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon coconutBlock;

	public FronosBlockCoconut(int par1)
	{
		super(par1, Material.wood);
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
		this.setTickRandomly(true);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundWoodFootstep);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{
		return this.coconutBlock;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.coconutBlock = par1IconRegister.registerIcon("fronos:coconutBlock");
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
	{
		if (!par1World.isRemote && par1World.rand.nextInt(100) == 0)
		{
			if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
			{
				return;
			}
			par1World.playSoundEffect(par2, par3, par4, "dig.wood", 5.0F, 1.2F);
			par1World.setBlock(par2, par3, par4, FronosBlocks.coconutMilkFluidBlock.blockID);
		}
	}
}