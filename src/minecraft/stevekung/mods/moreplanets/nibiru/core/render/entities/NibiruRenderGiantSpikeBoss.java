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
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.nibiru.core.models.entities.NibiruModelGiantSpikeBoss;

public class NibiruRenderGiantSpikeBoss extends RenderLiving
{
	private static final ResourceLocation giantSpikeTexture = new ResourceLocation("nibiru:textures/model/giantSpike.png");

	public NibiruRenderGiantSpikeBoss()
	{
		super(new NibiruModelGiantSpikeBoss(), 1.0F);
	}

	protected void scaleMob(float f)
	{
		GL11.glScalef(f, f, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return giantSpikeTexture;
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		BossStatus.setBossStatus((IBossDisplayData) par1EntityLiving, false);

		super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		this.scaleMob(2.0F);
	}

	@Override
	protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
	{
		return super.getColorMultiplier(par1EntityLivingBase, par2, par3);
	}

	@Override
	protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return -1;
	}
}