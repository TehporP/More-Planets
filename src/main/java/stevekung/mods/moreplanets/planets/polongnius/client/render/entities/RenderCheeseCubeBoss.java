/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.polongnius.client.model.ModelCheeseCubeEye;

public class RenderCheeseCubeBoss extends RenderLiving
{
	private ResourceLocation cheeseCubeTexture = new ResourceLocation("polongnius:textures/model/cheese_cube_boss.png");

	public RenderCheeseCubeBoss(RenderManager render)
	{
		super(render, new ModelCheeseCubeEye(), 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.cheeseCubeTexture;
	}

	@Override
	public void doRender(EntityLiving living, double par2, double par4, double par6, float par8, float par9)
	{
		BossStatus.setBossStatus((IBossDisplayData)living, false);
		super.doRender(living, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase living, float par2)
	{
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}
}