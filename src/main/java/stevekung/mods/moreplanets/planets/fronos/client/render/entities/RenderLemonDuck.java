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
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;

@SideOnly(Side.CLIENT)
public class RenderLemonDuck extends RenderLiving
{
	private ResourceLocation lemonDuckTextures = new ResourceLocation("moreplanets:textures/entity/lemon_duck.png");

	public RenderLemonDuck(RenderManager render)
	{
		super(render, new ModelLemonDuck(), 0.35F);
	}

	protected ResourceLocation duckTexture(EntityLemonDuck entity)
	{
		return this.lemonDuckTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.duckTexture((EntityLemonDuck)entity);
	}
}