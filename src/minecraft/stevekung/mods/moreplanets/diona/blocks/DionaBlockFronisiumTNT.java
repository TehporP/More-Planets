/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.diona.entities.DionaEntityFronisiumTNT;
import stevekung.mods.moreplanets.fronos.entities.projectiles.FronosEntityPoisonArrow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaBlockFronisiumTNT extends Block
{
	private Icon TNTTop;
	private Icon TNTBottom;
	private Icon TNTSide;

	public DionaBlockFronisiumTNT(int par1)
	{
		super(par1, Material.tnt);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 0 ? this.TNTBottom : par1 == 1 ? this.TNTTop : this.TNTSide;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);

		if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
		{
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleDiona.dionaTab;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
		{
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
	{
		if (!par1World.isRemote)
		{
			DionaEntityFronisiumTNT entitytntprimed = new DionaEntityFronisiumTNT(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, par5Explosion.getExplosivePlacedBy());
			entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
			par1World.spawnEntityInWorld(entitytntprimed);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
	{
		this.primeTnt(par1World, par2, par3, par4, par5, (EntityLivingBase)null);
	}

	public void primeTnt(World par1World, int par2, int par3, int par4, int par5, EntityLivingBase par6EntityLivingBase)
	{
		if (!par1World.isRemote)
		{
			if ((par5 & 1) == 1)
			{
				DionaEntityFronisiumTNT entitytntprimed = new DionaEntityFronisiumTNT(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, par6EntityLivingBase);
				par1World.spawnEntityInWorld(entitytntprimed);
				par1World.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
		{
			this.primeTnt(par1World, par2, par3, par4, 1, par5EntityPlayer);
			par1World.setBlockToAir(par2, par3, par4);
			par5EntityPlayer.getCurrentEquippedItem().damageItem(1, par5EntityPlayer);
			return true;
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par5Entity instanceof EntityArrow && !par1World.isRemote)
		{
			EntityArrow entityarrow = (EntityArrow)par5Entity;

			if (entityarrow.isBurning())
			{
				this.primeTnt(par1World, par2, par3, par4, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.shootingEntity : null);
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
		else if (par5Entity instanceof FronosEntityPoisonArrow && !par1World.isRemote)
		{
			FronosEntityPoisonArrow entityarrow = (FronosEntityPoisonArrow)par5Entity;

			if (entityarrow.isBurning())
			{
				this.primeTnt(par1World, par2, par3, par4, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.shootingEntity : null);
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.TNTSide = par1IconRegister.registerIcon("diona:fronisiumTNTSide");
		this.TNTTop = par1IconRegister.registerIcon("diona:fronisiumTNTTop");
		this.TNTBottom = par1IconRegister.registerIcon("diona:fronisiumTNTBottom");
	}
}