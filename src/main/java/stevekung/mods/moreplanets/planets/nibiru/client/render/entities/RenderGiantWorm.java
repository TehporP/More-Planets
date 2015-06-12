/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.client.model.ModelGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;

@SideOnly(Side.CLIENT)
public class RenderGiantWorm extends RenderLiving
{
	private ResourceLocation giantWormTexture = new ResourceLocation("moreplanets:textures/entity/giant_worm.png");

	public RenderGiantWorm(RenderManager render)
	{
		super(render, new ModelGiantWorm(5.0F), 0.65F);
	}

	protected ResourceLocation giantWormTexture(EntityGiantWorm entity)
	{
		return this.giantWormTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.giantWormTexture((EntityGiantWorm) entity);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase living)
	{
		return 180.0F;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase living, float par2)
	{
		GlStateManager.scale(0.25F, 0.25F, 0.25F);
	}
}