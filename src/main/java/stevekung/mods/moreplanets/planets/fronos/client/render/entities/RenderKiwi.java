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
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelKiwi;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityKiwi;

@SideOnly(Side.CLIENT)
public class RenderKiwi extends RenderLiving
{
	private ResourceLocation kiwiTextures = new ResourceLocation("moreplanets:textures/entity/kiwi.png");

	public RenderKiwi(RenderManager render)
	{
		super(render, new ModelKiwi(), 0.4F);
	}

	protected ResourceLocation kiwiTexture(EntityKiwi entity)
	{
		return this.kiwiTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.kiwiTexture((EntityKiwi)entity);
	}
}