/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.client.render.entities;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.venus.client.render.entities.layers.LayerVenusianSlimeGel;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianSlime;

@SideOnly(Side.CLIENT)
public class RenderVenusianSlime extends RenderLiving
{
	private ResourceLocation slimeTextures = new ResourceLocation("moreplanets:textures/entity/venusian_slime.png");

	public RenderVenusianSlime(RenderManager render)
	{
		super(render, new ModelSlime(16), 0.25F);
		this.addLayer(new LayerVenusianSlimeGel(this));
	}

	public void doRender(EntityVenusianSlime living, double par2, double par3, double par4, float par5, float par6)
	{
		this.shadowSize = 0.25F * living.getSlimeSize();
		super.doRender(living, par2, par3, par4, par5, par6);
	}

	protected void preRenderCallback(EntityVenusianSlime living, float par2)
	{
		float f1 = living.getSlimeSize();
		float f2 = (living.prevSquishFactor + (living.squishFactor - living.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	protected ResourceLocation getEntityTexture(EntityVenusianSlime entity)
	{
		return this.slimeTextures;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityVenusianSlime)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.preRenderCallback((EntityVenusianSlime)entity, par2);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityVenusianSlime)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityVenusianSlime)entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityVenusianSlime)entity, x, y, z, par5, partialTicks);
	}
}