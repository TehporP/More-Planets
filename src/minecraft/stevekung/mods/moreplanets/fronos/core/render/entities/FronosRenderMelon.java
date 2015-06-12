/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelMelon;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityMelon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderMelon extends RenderLiving
{
	private static final ResourceLocation melonTextures = new ResourceLocation("fronos:textures/model/melon.png");

	public FronosRenderMelon()
	{
		super(new FronosModelMelon(), 1.0F);
	}

	protected ResourceLocation berryTexture(FronosEntityMelon par1EntityBerry)
	{
		return melonTextures;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBerry((FronosEntityMelon)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBerry((FronosEntityMelon)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.berryTexture((FronosEntityMelon)par1Entity);
	}

	public void renderBerry(FronosEntityMelon par1EntityBerry, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityBerry, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
	{
		return 90.0F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return -1;
	}
}