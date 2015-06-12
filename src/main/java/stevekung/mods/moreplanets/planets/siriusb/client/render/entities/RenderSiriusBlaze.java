/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.client.render.entities;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;

@SideOnly(Side.CLIENT)
public class RenderSiriusBlaze extends RenderLiving
{
	private ResourceLocation blazeTextures = new ResourceLocation("moreplanets:textures/entity/sirius_blaze.png");

	public RenderSiriusBlaze(RenderManager render)
	{
		super(render, new ModelBlaze(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntitySiriusBlaze entity)
	{
		return this.blazeTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntitySiriusBlaze)entity);
	}
}