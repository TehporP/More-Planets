/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.client.render.entities;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;

import com.google.common.collect.Lists;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderBiped
{
	private ResourceLocation zombieTextures = new ResourceLocation("moreplanets:textures/entity/infected_zombie.png");
	private ModelBiped field_82434_o;
	private List field_177121_n;
	private List field_177122_o;

	public RenderInfectedZombie(RenderManager render)
	{
		super(render, new ModelZombie(), 0.5F, 1.0F);
		this.layerRenderers.get(0);
		this.field_82434_o = this.modelBipedMain;
		this.addLayer(new LayerHeldItem(this));
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
		{
			@Override
			protected void func_177177_a()
			{
				this.field_177189_c = new ModelZombie(0.5F, true);
				this.field_177186_d = new ModelZombie(1.0F, true);
			}
		};
		this.addLayer(layerbipedarmor);
		this.field_177122_o = Lists.newArrayList(this.layerRenderers);
		this.removeLayer(layerbipedarmor);
		this.addLayer(new LayerVillagerArmor(this));
		this.field_177121_n = Lists.newArrayList(this.layerRenderers);
	}

	public void func_180579_a(EntityInfectedZombie p_180579_1_, double p_180579_2_, double p_180579_4_, double p_180579_6_, float p_180579_8_, float p_180579_9_)
	{
		this.func_82427_a(p_180579_1_);
		super.doRender(p_180579_1_, p_180579_2_, p_180579_4_, p_180579_6_, p_180579_8_, p_180579_9_);
	}

	protected ResourceLocation func_180578_a(EntityInfectedZombie p_180578_1_)
	{
		return this.zombieTextures;
	}

	private void func_82427_a(EntityInfectedZombie p_82427_1_)
	{
		this.mainModel = this.field_82434_o;
		this.layerRenderers = this.field_177122_o;
		this.modelBipedMain = (ModelBiped)this.mainModel;
	}

	protected void rotateCorpse(EntityInfectedZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		if (p_77043_1_.isConverting())
		{
			p_77043_3_ += (float)(Math.cos(p_77043_1_.ticksExisted * 3.25D) * Math.PI * 0.25D);
		}
		super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity)
	{
		return this.func_180578_a((EntityInfectedZombie)entity);
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_180579_a((EntityInfectedZombie)entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		this.rotateCorpse((EntityInfectedZombie)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_180579_a((EntityInfectedZombie)entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_180578_a((EntityInfectedZombie)entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_180579_a((EntityInfectedZombie)entity, x, y, z, p_76986_8_, partialTicks);
	}
}