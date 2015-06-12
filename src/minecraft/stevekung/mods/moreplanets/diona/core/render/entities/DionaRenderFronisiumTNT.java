/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.render.entities;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.entities.DionaEntityFronisiumTNT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DionaRenderFronisiumTNT extends Render
{
	private RenderBlocks blockRenderer = new RenderBlocks();

	public DionaRenderFronisiumTNT()
	{
		this.shadowSize = 0.5F;
	}

	public void renderPrimedTNT(DionaEntityFronisiumTNT par1EntityTNTPrimed, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);

		if (par1EntityTNTPrimed.fuse - par9 + 1.0F < 10.0F)
		{
			float var10 = 1.0F - (par1EntityTNTPrimed.fuse - par9 + 1.0F) / 10.0F;
			if (var10 < 0.0F)
			{
				var10 = 0.0F;
			}
			if (var10 > 1.0F)
			{
				var10 = 1.0F;
			}
			var10 *= var10;
			var10 *= var10;
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}

		float var10 = (1.0F - (par1EntityTNTPrimed.fuse - par9 + 1.0F) / 100.0F) * 0.8F;

		this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		this.blockRenderer.renderBlockAsItem(DionaBlocks.fronisiumTnt, 0, par1EntityTNTPrimed.getBrightness(par9));

		if (par1EntityTNTPrimed.fuse / 5 % 2 == 0)
		{
			GL11.glDisable(3553);
			GL11.glDisable(2896);
			GL11.glEnable(3042);
			GL11.glBlendFunc(770, 772);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
			this.blockRenderer.renderBlockAsItem(Block.tnt, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(3042);
			GL11.glEnable(2896);
			GL11.glEnable(3553);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return null;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderPrimedTNT((DionaEntityFronisiumTNT)par1Entity, par2, par4, par6, par8, par9);
	}
}