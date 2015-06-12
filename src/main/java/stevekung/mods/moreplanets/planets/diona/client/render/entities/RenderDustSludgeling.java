/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.diona.client.render.entities;
//
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.planets.diona.entities.EntityDustSludgeling;
//
//@SideOnly(Side.CLIENT)
//public class RenderDustSludgeling extends RenderLiving
//{
//	private ResourceLocation delriumSludgelingTexture = new ResourceLocation("diona:textures/model/dust_sludgeling.png");
//
//	public RenderDustSludgeling(RenderManager render)
//	{
//		super(render, new ModelSludgeling(), 0.2F);
//	}
//
//	protected ResourceLocation delriumWormTexture(EntityDustSludgeling entityDelriumWorm)
//	{
//		return this.delriumSludgelingTexture;
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(Entity par1Entity)
//	{
//		return this.delriumWormTexture((EntityDustSludgeling) par1Entity);
//	}
//}