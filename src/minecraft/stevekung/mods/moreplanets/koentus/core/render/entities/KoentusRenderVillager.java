/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.core.render.entities;

import micdoodle8.mods.galacticraft.core.client.model.GCCoreModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.koentus.entities.KoentusEntityVillager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KoentusRenderVillager extends RenderLiving
{
	private static final ResourceLocation villagerTexture = new ResourceLocation("koentus:textures/model/koentusVillager.png");

	protected GCCoreModelVillager villagerModel;

	public KoentusRenderVillager()
	{
		super(new GCCoreModelVillager(0.0F), 0.5F);
		this.villagerModel = (GCCoreModelVillager) this.mainModel;
	}

	protected int shouldVillagerRenderPass(KoentusEntityVillager par1EntityVillager, int par2, float par3)
	{
		return -1;
	}

	public void renderVillager(KoentusEntityVillager par1EntityVillager, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityVillager, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110902_a(KoentusEntityVillager par1EntityVillager)
	{
		return KoentusRenderVillager.villagerTexture;
	}

	protected void renderVillagerEquipedItems(KoentusEntityVillager par1EntityVillager, float par2)
	{
		super.renderEquippedItems(par1EntityVillager, par2);
	}

	protected void preRenderVillager(KoentusEntityVillager par1EntityVillager, float par2)
	{
		float f1 = 0.9375F;

		if (par1EntityVillager.getGrowingAge() < 0)
		{
			f1 = (float) (f1 * 0.5D);
			this.shadowSize = 0.25F;
		}
		else
		{
			this.shadowSize = 0.5F;
		}

		GL11.glScalef(f1, f1, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderVillager((KoentusEntityVillager) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.preRenderVillager((KoentusEntityVillager) par1EntityLivingBase, par2);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldVillagerRenderPass((KoentusEntityVillager) par1EntityLivingBase, par2, par3);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.renderVillagerEquipedItems((KoentusEntityVillager) par1EntityLivingBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.func_110902_a((KoentusEntityVillager) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderVillager((KoentusEntityVillager) par1Entity, par2, par4, par6, par8, par9);
	}
}
