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
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelGrappy2;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.layers.LayerGrappyWool;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;

@SideOnly(Side.CLIENT)
public class RenderGrappy extends RenderLiving
{
	private ResourceLocation grappyTextures = new ResourceLocation("moreplanets:textures/entity/grappy/grappy_sheared.png");

	public RenderGrappy(RenderManager render)
	{
		super(render, new ModelGrappy2(), 0.6F);
		this.addLayer(new LayerGrappyWool(this));
	}

	protected ResourceLocation func_110883_a(EntityGrappy entity)
	{
		return this.grappyTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_110883_a((EntityGrappy)entity);
	}
}