/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.client.render.entities.projectiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class RenderProjectileFronisiumTNT extends Render
{
	private BlockRendererDispatcher renderBlocks = Minecraft.getMinecraft().getBlockRendererDispatcher();

	public RenderProjectileFronisiumTNT(RenderManager render)
	{
		super(render);
		this.shadowSize = 0.5F;
	}

	public void renderProjectileTNT(double par2, double par4, double par6, float par8, float par9)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		this.bindTexture(TextureMap.locationBlocksTexture);
		GlStateManager.enableCull();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) par2, (float) par4, (float) par6);
		blockrendererdispatcher.renderBlockBrightness(DionaBlocks.fronisium_tnt.getDefaultState(), 1.0F);
		GlStateManager.popMatrix();
		GlStateManager.disableCull();
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderProjectileTNT(par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TextureMap.locationBlocksTexture;
	}
}