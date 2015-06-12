/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.nibiru.core.models.entities.NibiruModelGiantWorm;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityGiantWorm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NibiruRenderGiantWorm extends RenderLiving
{
	private static final ResourceLocation giantWormTexture = new ResourceLocation("nibiru:textures/model/giantWorm.png");

	protected void scaleMob(float f)
	{
		GL11.glScalef(f, f, f);
	}

	public NibiruRenderGiantWorm()
	{
		super(new NibiruModelGiantWorm(5.0F), 2.0F);
	}

	protected ResourceLocation giantWormTexture(NibiruEntityGiantWorm par1EntityGiantWorm)
	{
		return giantWormTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.giantWormTexture((NibiruEntityGiantWorm) par1Entity);
	}

	public void renderGiantWorm(NibiruEntityGiantWorm par1EntityGiantWorm, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityGiantWorm, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
	{
		return 180.0F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return -1;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		GL11.glScalef(0.25F, 0.25F, 0.25F);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderGiantWorm((NibiruEntityGiantWorm) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderGiantWorm((NibiruEntityGiantWorm) par1Entity, par2, par4, par6, par8, par9);
	}
}