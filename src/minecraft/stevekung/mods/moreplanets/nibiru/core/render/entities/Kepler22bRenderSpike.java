/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.diona.core.models.entities.Kepler22bModelSpike;
import stevekung.mods.moreplanets.nibiru.entities.Kepler22bEntitySpike;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Kepler22bRenderSpike extends RenderLiving
{
	private static final ResourceLocation spikeTextures = new ResourceLocation("nibiru:textures/entity/spike.png");

	public Kepler22bRenderSpike()
	{
		super(new Kepler22bModelSpike(), 0.5F);
	}

	protected ResourceLocation spikeTexture(Kepler22bEntitySpike spike)
	{
		return spikeTextures;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderSpike((Kepler22bEntitySpike)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderSpike((Kepler22bEntitySpike)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.spikeTexture((Kepler22bEntitySpike)par1Entity);
	}

	public void renderSpike(Kepler22bEntitySpike spike, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(spike, par2, par4, par6, par8, par9);
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
