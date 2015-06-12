/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.client.render.entities;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.moons.europa.client.render.entities.layer.LayerEuropaSquidEyes;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;

@SideOnly(Side.CLIENT)
public class RenderEuropaSquid extends RenderLiving
{
	private ResourceLocation squidTextures = new ResourceLocation("moreplanets:textures/entity/europa_squid.png");

	public RenderEuropaSquid(RenderManager render)
	{
		super(render, new ModelSquid(), 0.7F);
		this.addLayer(new LayerEuropaSquidEyes(this));
	}

	protected ResourceLocation getEntityTexture(EntityEuropaSquid entity)
	{
		return this.squidTextures;
	}

	protected void rotateCorpse(EntityEuropaSquid p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		float f3 = p_77043_1_.prevSquidPitch + (p_77043_1_.squidPitch - p_77043_1_.prevSquidPitch) * p_77043_4_;
		float f4 = p_77043_1_.prevSquidYaw + (p_77043_1_.squidYaw - p_77043_1_.prevSquidYaw) * p_77043_4_;
		GlStateManager.translate(0.0F, 0.5F, 0.0F);
		GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(f4, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, -1.2F, 0.0F);
	}

	protected float handleRotationFloat(EntityEuropaSquid p_77044_1_, float p_77044_2_)
	{
		return p_77044_1_.lastTentacleAngle + (p_77044_1_.tentacleAngle - p_77044_1_.lastTentacleAngle) * p_77044_2_;
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_)
	{
		return this.handleRotationFloat((EntityEuropaSquid)p_77044_1_, p_77044_2_);
	}

	@Override
	protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		this.rotateCorpse((EntityEuropaSquid)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityEuropaSquid)entity);
	}
}