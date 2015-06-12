/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.core.render.entities;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PolongniusRenderCheeseCow extends RenderLiving
{
	private ResourceLocation cheeseCowTextures = new ResourceLocation("polongnius:textures/model/cheeseCow.png");

	public PolongniusRenderCheeseCow()
	{
		super(new ModelCow(), 0.6F);
	}

	protected ResourceLocation getMooshroomTextures(PolongniusEntityCheeseCow entity)
	{
		return this.cheeseCowTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getMooshroomTextures((PolongniusEntityCheeseCow)entity);
	}
}