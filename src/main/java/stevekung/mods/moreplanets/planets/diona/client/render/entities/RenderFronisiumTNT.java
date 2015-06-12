/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.client.render.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFronisiumTNT;

@SideOnly(Side.CLIENT)
public class RenderFronisiumTNT extends Render
{
	public RenderFronisiumTNT(RenderManager render)
	{
		super(render);
		this.shadowSize = 0.5F;
	}

	public void doRender(EntityFronisiumTNT entity, double x, double y, double z, float par5, float partialTicks)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x, (float)y + 0.5F, (float)z);
		float f2;

		if (entity.fuse - partialTicks + 1.0F < 10.0F)
		{
			f2 = 1.0F - (entity.fuse - partialTicks + 1.0F) / 10.0F;
			f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
			f2 *= f2;
			f2 *= f2;
			float f3 = 1.0F + f2 * 0.3F;
			GlStateManager.scale(f3, f3, f3);
		}

		f2 = (1.0F - (entity.fuse - partialTicks + 1.0F) / 100.0F) * 0.8F;
		this.bindEntityTexture(entity);
		GlStateManager.translate(-0.5F, -0.5F, 0.5F);
		blockrendererdispatcher.renderBlockBrightness(DionaBlocks.fronisium_tnt.getDefaultState(), entity.getBrightness(partialTicks));
		GlStateManager.translate(0.0F, 0.0F, 1.0F);

		if (entity.fuse / 5 % 2 == 0)
		{
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 772);
			GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
			GlStateManager.doPolygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			blockrendererdispatcher.renderBlockBrightness(DionaBlocks.fronisium_tnt.getDefaultState(), 1.0F);
			GlStateManager.doPolygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, par5, partialTicks);
	}

	protected ResourceLocation func_180563_a(EntityFronisiumTNT entity)
	{
		return TextureMap.locationBlocksTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_180563_a((EntityFronisiumTNT)entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityFronisiumTNT)entity, x, y, z, par5, partialTicks);
	}
}