/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStrawberryChicken;

@SideOnly(Side.CLIENT)
public class RenderStrawberryChicken extends RenderLiving
{
	private ResourceLocation chickenTextures = new ResourceLocation("moreplanets:textures/entity/strawberry_chicken.png");

	public RenderStrawberryChicken(RenderManager render)
	{
		super(render, new ModelChicken(), 0.3F);
	}

	protected ResourceLocation func_180568_a(EntityStrawberryChicken entity)
	{
		return this.chickenTextures;
	}

	protected float func_180569_a(EntityStrawberryChicken entity, float par2)
	{
		float f1 = entity.field_70888_h + (entity.field_70886_e - entity.field_70888_h) * par2;
		float f2 = entity.field_70884_g + (entity.destPos - entity.field_70884_g) * par2;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase entity, float par2)
	{
		return this.func_180569_a((EntityStrawberryChicken)entity, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_180568_a((EntityStrawberryChicken)entity);
	}
}