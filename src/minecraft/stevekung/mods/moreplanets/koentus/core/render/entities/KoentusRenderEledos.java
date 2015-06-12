/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.core.render.entities;

import java.util.Random;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.diona.core.models.entities.DionaModelSpaceEnderman;
import stevekung.mods.moreplanets.koentus.entities.KoentusEntityEledos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KoentusRenderEledos extends RenderLiving
{
	private static final ResourceLocation endermanEyesTexture = new ResourceLocation("koentus:textures/model/eledos/eledosEyes.png");
	private static final ResourceLocation endermanTextures = new ResourceLocation("koentus:textures/model/eledos/eledos.png");

	private DionaModelSpaceEnderman endermanModel;
	private Random rnd = new Random();

	public KoentusRenderEledos()
	{
		super(new DionaModelSpaceEnderman(), 0.5F);
		this.endermanModel = (DionaModelSpaceEnderman)super.mainModel;
		this.setRenderPassModel(this.endermanModel);
	}

	public void renderEnderman(KoentusEntityEledos par1, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getEndermanTextures(KoentusEntityEledos par1)
	{
		return endermanTextures;
	}

	protected int renderEyes(KoentusEntityEledos par1, int par2, float par3)
	{
		if (par2 != 0)
		{
			return -1;
		}
		else
		{
			this.bindTexture(endermanEyesTexture);
			float f1 = 1.5F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);

			if (par1.isInvisible())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return 1;
		}
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderEnderman((KoentusEntityEledos)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.renderEyes((KoentusEntityEledos)par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getEndermanTextures((KoentusEntityEledos)par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderEnderman((KoentusEntityEledos)par1Entity, par2, par4, par6, par8, par9);
	}
}