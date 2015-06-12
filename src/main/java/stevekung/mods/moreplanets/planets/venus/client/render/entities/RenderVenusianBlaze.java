/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.client.render.entities;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;

@SideOnly(Side.CLIENT)
public class RenderVenusianBlaze extends RenderLiving
{
	private ResourceLocation blazeTextures = new ResourceLocation("moreplanets:textures/entity/venusian_blaze.png");

	public RenderVenusianBlaze(RenderManager render)
	{
		super(render, new ModelBlaze(), 0.5F);
	}

	protected ResourceLocation getBlazeTextures(EntityVenusianBlaze par1EntityBlaze)
	{
		return this.blazeTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getBlazeTextures((EntityVenusianBlaze)par1Entity);
	}
}