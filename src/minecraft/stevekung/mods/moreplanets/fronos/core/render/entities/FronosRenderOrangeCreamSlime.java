/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.fronos.entities.FronosEntityOrangeCreamSlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderOrangeCreamSlime extends RenderLiving
{
	private static final ResourceLocation orangeCreamSlimeTextures = new ResourceLocation("fronos:textures/model/orangeCreamSlime.png");
	private ModelBase scaleAmount;

	public FronosRenderOrangeCreamSlime(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(par1ModelBase, par3);
		this.scaleAmount = par2ModelBase;
	}

	protected int shouldSlimeRenderPass(FronosEntityOrangeCreamSlime par1EntitySlime, int par2, float par3)
	{
		if (par1EntitySlime.isInvisible())
		{
			return 0;
		}
		else if (par2 == 0)
		{
			this.setRenderPassModel(this.scaleAmount);
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			return 1;
		}
		else
		{
			if (par2 == 1)
			{
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			return -1;
		}
	}

	protected void scaleSlime(FronosEntityOrangeCreamSlime par1EntitySlime, float par2)
	{
		float f1 = par1EntitySlime.getSlimeSize();
		float f2 = (par1EntitySlime.prevSquishFactor + (par1EntitySlime.squishFactor - par1EntitySlime.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	protected ResourceLocation getSlimeTextures(FronosEntityOrangeCreamSlime par1EntitySlime)
	{
		return orangeCreamSlimeTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.scaleSlime((FronosEntityOrangeCreamSlime)par1EntityLivingBase, par2);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldSlimeRenderPass((FronosEntityOrangeCreamSlime)par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getSlimeTextures((FronosEntityOrangeCreamSlime)par1Entity);
	}
}