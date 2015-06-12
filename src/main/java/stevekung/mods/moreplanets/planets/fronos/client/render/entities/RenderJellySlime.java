/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

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
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.layers.LayerJellySlimeGel;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;

@SideOnly(Side.CLIENT)
public class RenderJellySlime extends RenderLiving
{
	private ResourceLocation strawberrySlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/strawberry.png");
	private ResourceLocation berrySlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/berry.png");
	private ResourceLocation raspberrySlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/raspberry.png");
	private ResourceLocation orangeSlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/orange.png");
	private ResourceLocation grapeSlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/grape.png");
	private ResourceLocation limeSlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/lime.png");
	private ResourceLocation greenSlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/green.png");
	private ResourceLocation lemonSlimeTextures = new ResourceLocation("moreplanets:textures/entity/jelly_slime/lemon.png");

	public RenderJellySlime(RenderManager render)
	{
		super(render, new ModelSlime(16), 0.25F);
		this.addLayer(new LayerJellySlimeGel(this));
	}

	public void doRender(EntityJellySlime entity, double par2, double par3, double par4, float par5, float par6)
	{
		this.shadowSize = 0.25F * entity.getSlimeSize();
		super.doRender(entity, par2, par3, par4, par5, par6);
	}

	protected void preRenderCallback(EntityJellySlime entity, float par2)
	{
		float f1 = entity.getSlimeSize();
		float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	protected ResourceLocation getEntityTexture(EntityJellySlime par1Entity)
	{
		switch (par1Entity.getJellySlimeType())
		{
		case 0:
		default:
			return this.grapeSlimeTextures;
		case 1:
			return this.raspberrySlimeTextures;
		case 2:
			return this.strawberrySlimeTextures;
		case 3:
			return this.berrySlimeTextures;
		case 4:
			return this.limeSlimeTextures;
		case 5:
			return this.orangeSlimeTextures;
		case 6:
			return this.greenSlimeTextures;
		case 7:
			return this.lemonSlimeTextures;
		}
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityJellySlime)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.preRenderCallback((EntityJellySlime)entity, par2);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityJellySlime)entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityJellySlime)entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityJellySlime)entity, x, y, z, par5, partialTicks);
	}
}