/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.fluids.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PolongniusBlockFluidCheeseOfMilk extends BlockFluidClassic
{
	public static Icon cheeseOfMilkStillIcon;
	public static Icon cheeseOfMilkFlowingIcon;

	public PolongniusBlockFluidCheeseOfMilk(int par1, Fluid fluid, Material par2Material)
	{
		super(par1, PolongniusBlocks.cheeseOfMilkFluid, par2Material);
		this.setQuantaPerBlock(6);
		this.setRenderPass(1);
		this.needsRandomTick = true;
		this.stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? cheeseOfMilkStillIcon : cheeseOfMilkFlowingIcon;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		cheeseOfMilkStillIcon = par1IconRegister.registerIcon("polongnius:cheeseOfMilk_flowing");
		cheeseOfMilkFlowingIcon = par1IconRegister.registerIcon("polongnius:cheeseOfMilk_still");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(1) == 0)
		{
			MorePlanetCore.proxy.spawnParticle("cheeseBubble", par2 + par5Random.nextFloat(), par3 + 1.0F, par4 + par5Random.nextFloat());
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 50));
		}
	}
}