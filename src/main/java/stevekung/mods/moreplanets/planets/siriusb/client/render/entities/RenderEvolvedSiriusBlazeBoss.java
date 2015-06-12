/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.client.render.entities;
//package stevekung.mods.moreplanets.planets.siriusb.render.entities;
//
//import net.minecraft.client.model.ModelBlaze;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.boss.BossStatus;
//import net.minecraft.entity.boss.IBossDisplayData;
//import net.minecraft.util.ResourceLocation;
//import stevekung.mods.moreplanets.planets.siriusb.entities.EntityEvolvedSiriusBlazeBoss;
//import stevekung.mods.moreplanets.planets.siriusb.render.entities.layers.LayerSiriusBlazeBoss;
//
//public class RenderEvolvedSiriusBlazeBoss extends RenderLiving
//{
//	private ResourceLocation blazeTextures = new ResourceLocation("siriusb:textures/model/sirius_blaze.png");
//
//	public RenderEvolvedSiriusBlazeBoss(RenderManager render)
//	{
//		super(render, new ModelBlaze(), 0.5F);
//		this.addLayer(new LayerSiriusBlazeBoss(this));
//	}
//
//	protected ResourceLocation getBlazeTextures(EntityEvolvedSiriusBlazeBoss par1EntityBlaze)
//	{
//		return this.blazeTextures;
//	}
//
//	@Override
//	public void doRender(EntityLiving living, double par2, double par4, double par6, float par8, float par9)
//	{
//		BossStatus.setBossStatus((IBossDisplayData)living, false);
//		super.doRender(living, par2, par4, par6, par8, par9);
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(Entity par1Entity)
//	{
//		return this.getBlazeTextures((EntityEvolvedSiriusBlazeBoss)par1Entity);
//	}
//
//	@Override
//	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
//	{
//		GlStateManager.scale(4.0F, 4.0F, 4.0F);
//	}
//}