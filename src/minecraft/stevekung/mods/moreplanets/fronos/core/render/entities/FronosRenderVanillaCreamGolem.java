/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityVanillaGolem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderVanillaCreamGolem extends RenderLiving
{
	private static final ResourceLocation snowManTextures = new ResourceLocation("fronos:textures/model/vanillaCreamGolem.png");

	public FronosRenderVanillaCreamGolem()
	{
		super(new ModelSnowMan(), 0.5F);
	}

	protected ResourceLocation getSnowManTextures(FronosEntityVanillaGolem par1EntitySnowman)
	{
		return snowManTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getSnowManTextures((FronosEntityVanillaGolem)par1Entity);
	}
}