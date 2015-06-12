/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.client.render.entities;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.siriusb.client.render.entities.layers.LayerSiriusCreeperCharge;

@SideOnly(Side.CLIENT)
public class RenderSiriusCreeper extends RenderLiving
{
	private ResourceLocation creeperTextures = new ResourceLocation("moreplanets:textures/entity/sirius_creeper.png");

	public RenderSiriusCreeper(RenderManager render)
	{
		super(render, new ModelCreeper(), 0.5F);
		this.addLayer(new LayerSiriusCreeperCharge(this));
	}

	protected void func_180570_a(EntityCreeper creeper, float light)
	{
		float f1 = creeper.getCreeperFlashIntensity(light);
		float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
		f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
		f1 *= f1;
		f1 *= f1;
		float f3 = (1.0F + f1 * 0.4F) * f2;
		float f4 = (1.0F + f1 * 0.1F) / f2;
		GlStateManager.scale(f3, f4, f3);
	}

	protected int func_180571_a(EntityCreeper creeper, float light)
	{
		float f2 = creeper.getCreeperFlashIntensity(light);

		if ((int)(f2 * 10.0F) % 2 == 0)
		{
			return 0;
		}
		else
		{
			int i = (int)(f2 * 0.2F * 255.0F);
			i = MathHelper.clamp_int(i, 0, 255);
			return i << 24 | 16777215;
		}
	}

	protected ResourceLocation getEntityTexture(EntityCreeper entity)
	{
		return this.creeperTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase living, float par2)
	{
		this.func_180570_a((EntityCreeper)living, par2);
	}

	@Override
	protected int getColorMultiplier(EntityLivingBase living, float par2, float par3)
	{
		return this.func_180571_a((EntityCreeper)living, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityCreeper)entity);
	}
}