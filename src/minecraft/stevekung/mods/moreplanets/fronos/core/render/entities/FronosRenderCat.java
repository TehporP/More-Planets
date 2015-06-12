/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.fronos.entities.FronosEntityCat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderCat extends RenderLiving
{
	private static final ResourceLocation chocolateCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/chocolate.png");
	private static final ResourceLocation spaceCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/spaceCat.png");
	private static final ResourceLocation vanillaCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/vanilla.png");
	private static final ResourceLocation strawberryCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/strawberry.png");
	private static final ResourceLocation orangeCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/orange.png");
	private static final ResourceLocation teaCatTextures = new ResourceLocation("fronos:textures/model/spaceCat/tea.png");

	public FronosRenderCat(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	public void renderLivingOcelot(FronosEntityCat par1EntityOcelot, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityOcelot, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110874_a(FronosEntityCat par1EntityOcelot)
	{
		switch (par1EntityOcelot.getTameSkin())
		{
		case 0:
		default:
			return spaceCatTextures;
		case 1:
			return strawberryCatTextures;
		case 2:
			return vanillaCatTextures;
		case 3:
			return chocolateCatTextures;
		case 4:
			return orangeCatTextures;
		case 5:
			return teaCatTextures;
		}
	}

	protected void preRenderOcelot(FronosEntityCat par1EntityOcelot, float par2)
	{
		super.preRenderCallback(par1EntityOcelot, par2);

		if (par1EntityOcelot.isTamed())
		{
			GL11.glScalef(0.8F, 0.8F, 0.8F);
		}
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderLivingOcelot((FronosEntityCat)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.preRenderOcelot((FronosEntityCat)par1EntityLivingBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.func_110874_a((FronosEntityCat)par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderLivingOcelot((FronosEntityCat)par1Entity, par2, par4, par6, par8, par9);
	}
}