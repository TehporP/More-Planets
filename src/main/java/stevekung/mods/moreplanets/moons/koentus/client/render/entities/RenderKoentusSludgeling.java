/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.moons.koentus.client.render.entities;
//
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusSludgeling;
//
//@SideOnly(Side.CLIENT)
//public class RenderKoentusSludgeling extends RenderLiving
//{
//	private ResourceLocation sludgelingTexture = new ResourceLocation("koentus:textures/model/koentus_sludgeling.png");
//
//	public RenderKoentusSludgeling(RenderManager render)
//	{
//		super(render, new ModelSludgeling(), 0.2F);
//	}
//
//	protected ResourceLocation delriumWormTexture(EntityKoentusSludgeling entity)
//	{
//		return this.sludgelingTexture;
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(Entity entity)
//	{
//		return this.delriumWormTexture((EntityKoentusSludgeling) entity);
//	}
//}