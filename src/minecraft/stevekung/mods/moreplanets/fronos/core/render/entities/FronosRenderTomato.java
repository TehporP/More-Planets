/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelTomato;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityTomato;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderTomato extends RenderLiving
{
	private static final ResourceLocation tomatoTextures = new ResourceLocation("fronos:textures/model/tomato.png");

	public FronosRenderTomato()
	{
		super(new FronosModelTomato(), 1.0F);
	}

	protected ResourceLocation berryTexture(FronosEntityTomato par1EntityBerry)
	{
		return tomatoTextures;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBerry((FronosEntityTomato)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBerry((FronosEntityTomato)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.berryTexture((FronosEntityTomato)par1Entity);
	}

	public void renderBerry(FronosEntityTomato par1EntityBerry, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityBerry, par2, par4, par6, par8, par9);
	}

	protected void renderMooshroomEquippedItems(FronosEntityTomato par1EntityMooshroom, float par2)
	{
		super.renderEquippedItems(par1EntityMooshroom, par2);

		if (!par1EntityMooshroom.isChild())
		{
			this.bindTexture(TextureMap.locationBlocksTexture);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, -1.0F, 1.0F);
			GL11.glTranslatef(0.0F, -0.2F, 0.0F);
			GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
			this.renderBlocks.renderBlockAsItem(FronosBlocks.fronosCoral, 6, 1.0F);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
		}
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.renderMooshroomEquippedItems((FronosEntityTomato)par1EntityLivingBase, par2);
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