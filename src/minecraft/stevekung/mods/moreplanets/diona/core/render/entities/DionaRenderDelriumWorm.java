/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.render.entities;

import micdoodle8.mods.galacticraft.mars.client.model.GCMarsModelSludgeling;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.diona.entities.DionaEntityDelriumWorm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DionaRenderDelriumWorm extends RenderLiving
{
	private static final ResourceLocation delriumWormTexture = new ResourceLocation("diona:textures/model/delriumWorm.png");

	public DionaRenderDelriumWorm()
	{
		super(new GCMarsModelSludgeling(), 0.3F);
	}

	protected ResourceLocation delriumWormTexture(DionaEntityDelriumWorm entityDelriumWorm)
	{
		return DionaRenderDelriumWorm.delriumWormTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.delriumWormTexture((DionaEntityDelriumWorm) par1Entity);
	}

	public void renderDelriumWorm(DionaEntityDelriumWorm entityDelriumWorm, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(entityDelriumWorm, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
	{
		return 180.0F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return -1;
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderDelriumWorm((DionaEntityDelriumWorm) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderDelriumWorm((DionaEntityDelriumWorm) par1Entity, par2, par4, par6, par8, par9);
	}
}