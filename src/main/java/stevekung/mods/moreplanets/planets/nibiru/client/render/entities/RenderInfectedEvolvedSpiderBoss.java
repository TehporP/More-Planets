/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.client.render.entities;
//package stevekung.mods.moreplanets.planets.nibiru.render.entities;
//
//import net.minecraft.client.model.ModelSpider;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.boss.BossStatus;
//import net.minecraft.entity.boss.IBossDisplayData;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedEvolvedSpiderBoss;
//import stevekung.mods.moreplanets.planets.nibiru.render.entities.layers.LayerInfectedEvolvedSpiderBossEyes;
//
//@SideOnly(Side.CLIENT)
//public class RenderInfectedEvolvedSpiderBoss extends RenderLiving
//{
//	private ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
//
//	public RenderInfectedEvolvedSpiderBoss(RenderManager render)
//	{
//		super(render, new ModelSpider(), 1.0F);
//		this.addLayer(new LayerInfectedEvolvedSpiderBossEyes(this));
//	}
//
//	@Override
//	public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
//	{
//		BossStatus.setBossStatus((IBossDisplayData) entity, false);
//		super.doRender(entity, par2, par4, par6, par8, par9);
//	}
//
//	@Override
//	protected void preRenderCallback(EntityLivingBase entity, float par2)
//	{
//		GlStateManager.scale(2.0F, 2.0F, 2.0F);
//	}
//
//	protected ResourceLocation getEntityTexture(EntityInfectedEvolvedSpiderBoss entity)
//	{
//		return this.spiderTextures;
//	}
//
//	@Override
//	protected float getDeathMaxRotation(EntityLivingBase entity)
//	{
//		return 180.0F;
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(Entity entity)
//	{
//		return this.getEntityTexture((EntityInfectedEvolvedSpiderBoss)entity);
//	}
//}