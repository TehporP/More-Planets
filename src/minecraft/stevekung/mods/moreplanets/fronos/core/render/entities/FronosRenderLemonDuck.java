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
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelLemonDuck;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityLemonDuck;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderLemonDuck extends RenderLiving
{
	private static final ResourceLocation lemonDuckTextures = new ResourceLocation("fronos:textures/model/lemonDuck.png");

	public FronosRenderLemonDuck()
	{
		super(new FronosModelLemonDuck(), 0.50F);
	}

	protected ResourceLocation giviTexture(FronosEntityLemonDuck par1)
	{
		return lemonDuckTextures;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderGivi((FronosEntityLemonDuck)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderGivi((FronosEntityLemonDuck)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.giviTexture((FronosEntityLemonDuck)par1Entity);
	}

	public void renderGivi(FronosEntityLemonDuck marshmallow, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(marshmallow, par2, par4, par6, par8, par9);
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