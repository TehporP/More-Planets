/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelBearry;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;

@SideOnly(Side.CLIENT)
public class RenderBearry extends RenderLiving
{
	private ResourceLocation bearryTextures = new ResourceLocation("moreplanets:textures/entity/bearry.png");

	public RenderBearry(RenderManager render)
	{
		super(render, new ModelBearry(), 0.5F);
	}

	protected ResourceLocation bearryTexture(EntityBearry entity)
	{
		return this.bearryTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.bearryTexture((EntityBearry)entity);
	}
}