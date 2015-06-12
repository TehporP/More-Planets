/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.client.render.entities.layers;
//package stevekung.mods.moreplanets.planets.nibiru.render.entities.layers;
//
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedEvolvedSpiderBoss;
//import stevekung.mods.moreplanets.planets.nibiru.render.entities.RenderInfectedEvolvedSpiderBoss;
//
//@SideOnly(Side.CLIENT)
//public class LayerInfectedEvolvedSpiderBossEyes implements LayerRenderer
//{
//	private ResourceLocation texture = new ResourceLocation("textures/entity/spider_eyes.png");
//	private RenderInfectedEvolvedSpiderBoss render;
//
//	public LayerInfectedEvolvedSpiderBossEyes(RenderInfectedEvolvedSpiderBoss render)
//	{
//		this.render = render;
//	}
//
//	public void func_177148_a(EntityInfectedEvolvedSpiderBoss p_177148_1_, float p_177148_2_, float p_177148_3_, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float p_177148_8_)
//	{
//		this.render.bindTexture(this.texture);
//		GlStateManager.enableBlend();
//		GlStateManager.disableAlpha();
//		GlStateManager.blendFunc(1, 1);
//
//		if (p_177148_1_.isInvisible())
//		{
//			GlStateManager.depthMask(false);
//		}
//		else
//		{
//			GlStateManager.depthMask(true);
//		}
//
//		char c0 = 61680;
//		int i = c0 % 65536;
//		int j = c0 / 65536;
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, i / 1.0F, j / 1.0F);
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//		this.render.getMainModel().render(p_177148_1_, p_177148_2_, p_177148_3_, p_177148_5_, p_177148_6_, p_177148_7_, p_177148_8_);
//		int k = p_177148_1_.getBrightnessForRender(p_177148_4_);
//		i = k % 65536;
//		j = k / 65536;
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, i / 1.0F, j / 1.0F);
//		this.render.func_177105_a(p_177148_1_, p_177148_4_);
//		GlStateManager.disableBlend();
//		GlStateManager.enableAlpha();
//	}
//
//	@Override
//	public boolean shouldCombineTextures()
//	{
//		return false;
//	}
//
//	@Override
//	public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
//	{
//		this.func_177148_a((EntityInfectedEvolvedSpiderBoss)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
//	}
//}