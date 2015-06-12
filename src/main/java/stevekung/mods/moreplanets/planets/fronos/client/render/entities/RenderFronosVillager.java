/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityFronosVillager;

@SideOnly(Side.CLIENT)
public class RenderFronosVillager extends RenderLiving
{
	private ResourceLocation villagerTextures = new ResourceLocation("moreplanets:textures/entity/fronos_villager.png");

	public RenderFronosVillager(RenderManager render)
	{
		super(render, new ModelVillager(0.0F), 0.5F);
	}

	public ModelVillager model()
	{
		return (ModelVillager)super.getMainModel();
	}

	protected ResourceLocation getEntityTexture(EntityFronosVillager entity)
	{
		return this.villagerTextures;
	}

	protected void preRenderCallback(EntityFronosVillager entity)
	{
		float f1 = 0.9375F;

		if (entity.getGrowingAge() < 0)
		{
			f1 = (float)(f1 * 0.5D);
			this.shadowSize = 0.25F;
		}
		else
		{
			this.shadowSize = 0.5F;
		}
		GlStateManager.scale(f1, f1, f1);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.preRenderCallback((EntityFronosVillager)entity);
	}

	@Override
	public ModelBase getMainModel()
	{
		return this.model();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityFronosVillager)entity);
	}
}