/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceWolf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DionaRenderSpaceWolf extends RenderLiving
{
	private static final ResourceLocation wolfTextures = new ResourceLocation("diona:textures/model/spaceWolf/wolfSpace.png");
	private static final ResourceLocation tamedWolfTextures = new ResourceLocation("diona:textures/model/spaceWolf/wolfSpaceTame.png");
	private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("diona:textures/model/spaceWolf/wolfSpaceAngry.png");
	private static final ResourceLocation wolfCollarTextures = new ResourceLocation("diona:textures/model/spaceWolf/wolfSpaceCollar.png");

	public DionaRenderSpaceWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(par1ModelBase, par3);
		this.setRenderPassModel(par2ModelBase);
	}

	protected float getTailRotation(DionaEntitySpaceWolf par1EntityWolf, float par2)
	{
		return par1EntityWolf.getTailRotation();
	}

	protected int func_82447_a(DionaEntitySpaceWolf par1EntityWolf, int par2, float par3)
	{
		float f1;

		if (par2 == 0 && par1EntityWolf.getWolfShaking())
		{
			f1 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
			this.bindTexture(wolfTextures);
			GL11.glColor3f(f1, f1, f1);
			return 1;
		}
		else if (par2 == 1 && par1EntityWolf.isTamed())
		{
			this.bindTexture(wolfCollarTextures);
			f1 = 1.0F;
			int j = par1EntityWolf.getCollarColor();
			GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	protected ResourceLocation func_110914_a(DionaEntitySpaceWolf par1EntityWolf)
	{
		return par1EntityWolf.isTamed() ? tamedWolfTextures : par1EntityWolf.isAngry() ? anrgyWolfTextures : wolfTextures;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.func_82447_a((DionaEntitySpaceWolf)par1EntityLivingBase, par2, par3);
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
	{
		return this.getTailRotation((DionaEntitySpaceWolf)par1EntityLivingBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.func_110914_a((DionaEntitySpaceWolf)par1Entity);
	}
}