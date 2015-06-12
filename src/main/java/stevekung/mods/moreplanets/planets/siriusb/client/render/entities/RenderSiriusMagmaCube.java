/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.siriusb.client.model.ModelSiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;

@SideOnly(Side.CLIENT)
public class RenderSiriusMagmaCube extends RenderLiving
{
	private ResourceLocation magmaCubeTextures = new ResourceLocation("moreplanets:textures/entity/sirius_magma_cube.png");

	public RenderSiriusMagmaCube(RenderManager render)
	{
		super(render, new ModelSiriusMagmaCube(), 0.25F);
	}

	protected ResourceLocation getEntityTexture(EntitySiriusMagmaCube entity)
	{
		return this.magmaCubeTextures;
	}

	protected void preRenderCallback(EntitySiriusMagmaCube entity, float par2)
	{
		int i = entity.getSlimeSize();
		float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * par2) / (i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		float f3 = i;
		GlStateManager.scale(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.preRenderCallback((EntitySiriusMagmaCube)entity, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntitySiriusMagmaCube)entity);
	}
}