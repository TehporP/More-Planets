/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.client.render.entities;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;

@SideOnly(Side.CLIENT)
public class RenderCheeseCow extends RenderLiving
{
	private ResourceLocation cowTextures = new ResourceLocation("moreplanets:textures/entity/cheese_cow.png");

	public RenderCheeseCow(RenderManager render)
	{
		super(render, new ModelCow(), 0.6F);
	}

	protected ResourceLocation getEntityTexture(EntityCheeseCow entity)
	{
		return this.cowTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityCheeseCow)entity);
	}
}