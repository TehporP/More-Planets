/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.layers.LayerSpaceWolfCollar;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;

@SideOnly(Side.CLIENT)
public class RenderSpaceWolf extends RenderLiving
{
	private ResourceLocation wolfTextures = new ResourceLocation("moreplanets:textures/entity/space_wolf/space_wolf.png");
	private ResourceLocation tamedWolfTextures = new ResourceLocation("moreplanets:textures/entity/space_wolf/space_wolf_tamed.png");
	private ResourceLocation anrgyWolfTextures = new ResourceLocation("moreplanets:textures/entity/space_wolf/space_wolf_angry.png");

	public RenderSpaceWolf(RenderManager render)
	{
		super(render, new ModelSpaceWolf(), 0.5F);
		this.addLayer(new LayerSpaceWolfCollar(this));
	}

	protected float func_180593_a(EntitySpaceWolf entity)
	{
		return entity.getTailRotation();
	}

	public void func_177135_a(EntitySpaceWolf entity, double par2, double par3, double par4, float par5, float par6)
	{
		if (entity.isWolfWet())
		{
			float f2 = entity.getBrightness(par6) * entity.getShadingWhileWet(par6);
			GlStateManager.color(f2, f2, f2);
		}
		super.doRender(entity, par2, par3, par4, par5, par6);
	}

	protected ResourceLocation getEntityTexture(EntitySpaceWolf entity)
	{
		return entity.isTamed() ? this.tamedWolfTextures : entity.isAngry() ? this.anrgyWolfTextures : this.wolfTextures;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.func_177135_a((EntitySpaceWolf)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase entity, float par2)
	{
		return this.func_180593_a((EntitySpaceWolf)entity);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.func_177135_a((EntitySpaceWolf)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntitySpaceWolf)entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.func_177135_a((EntitySpaceWolf)entity, x, y, z, par5, partialTicks);
	}
}