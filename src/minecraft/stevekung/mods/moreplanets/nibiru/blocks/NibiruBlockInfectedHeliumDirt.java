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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruBlockInfectedHeliumDirt extends Block
{
	public NibiruBlockInfectedHeliumDirt(int par1)
	{
		super(par1, Material.ground);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8135F, 1.0F);
		this.setLightOpacity(0);
		this.setStepSound(soundGravelFootstep);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return AxisAlignedBB.getAABBPool().getAABB(par2 + 0, par3 + 0, par4 + 0, par2 + 1, par3 + 0.8135F, par4 + 1);
	}

	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z)
	{
		return false;
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
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean canRenderInPass(int pass)
	{
		ClientProxyMP.infectedDirtHeliumRenderPass = pass;
		return true;
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return NibiruBlocks.infectedDirt.getBlockTextureFromSide(par1);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return NibiruBlocks.infectedDirt.idDropped(0, par2Random, par3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.blockID;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying)
			{
				return;
			}
			entity.motionY = 0.09F;
			entity.fallDistance = 0.0F;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	}
}