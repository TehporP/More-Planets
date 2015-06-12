/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.render.entities.projectiles;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.entities.projectiles.DionaEntityProjectileFronisiumTNT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DionaRenderProjectileTNT extends Render
{
	private final RenderBlocks renderBlocks = new RenderBlocks();

	public DionaRenderProjectileTNT()
	{
		this.shadowSize = 0.5F;
	}

	public void renderProjectileTNT(DionaEntityProjectileFronisiumTNT projectileTNT, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		this.bindTexture(TextureMap.locationBlocksTexture);
		final Block var10 = DionaBlocks.fronisiumTnt;
		GL11.glDisable(GL11.GL_LIGHTING);
		if (var10 != null)
		{
			this.renderBlocks.setRenderBoundsFromBlock(var10);
			this.renderBlocks.renderBlockSandFalling(var10, projectileTNT.worldObj, MathHelper.floor_double(projectileTNT.posX), MathHelper.floor_double(projectileTNT.posY), MathHelper.floor_double(projectileTNT.posZ), 0);
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderProjectileTNT((DionaEntityProjectileFronisiumTNT) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}