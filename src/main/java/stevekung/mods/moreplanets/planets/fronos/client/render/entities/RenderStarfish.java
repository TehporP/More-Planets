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
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelStarfish;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStarfish;

@SideOnly(Side.CLIENT)
public class RenderStarfish extends RenderLiving
{
	private ResourceLocation starfishTextures = new ResourceLocation("moreplanets:textures/entity/space_starfish.png");

	public RenderStarfish(RenderManager render)
	{
		super(render, new ModelStarfish(), 0.3F);
	}

	protected ResourceLocation starFishTexture(EntityStarfish entity)
	{
		return this.starfishTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.starFishTexture((EntityStarfish)entity);
	}
}