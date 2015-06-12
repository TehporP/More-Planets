/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityPinkChicken;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderPinkChicken extends RenderLiving
{
	private static final ResourceLocation chickenTextures = new ResourceLocation("fronos:textures/model/pinkChicken.png");

	public FronosRenderPinkChicken()
	{
		super(new ModelChicken(), 0.3F);
	}

	public void renderChicken(FronosEntityPinkChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityChicken, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getChickenTextures(FronosEntityPinkChicken par1EntityChicken)
	{
		return chickenTextures;
	}

	protected float getWingRotation(FronosEntityPinkChicken par1EntityChicken, float par2)
	{
		float f1 = par1EntityChicken.field_70888_h + (par1EntityChicken.field_70886_e - par1EntityChicken.field_70888_h) * par2;
		float f2 = par1EntityChicken.field_70884_g + (par1EntityChicken.destPos - par1EntityChicken.field_70884_g) * par2;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderChicken((FronosEntityPinkChicken)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
	{
		return this.getWingRotation((FronosEntityPinkChicken)par1EntityLivingBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getChickenTextures((FronosEntityPinkChicken)par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderChicken((FronosEntityPinkChicken)par1Entity, par2, par4, par6, par8, par9);
	}
}