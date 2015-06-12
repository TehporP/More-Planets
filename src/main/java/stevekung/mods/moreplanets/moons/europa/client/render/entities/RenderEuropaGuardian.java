/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.moons.europa.client.model.ModelEuropaGuardian;
import stevekung.mods.moreplanets.moons.europa.client.render.entities.layer.LayerEuropaGuardianEyes;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaGuardian;

@SideOnly(Side.CLIENT)
public class RenderEuropaGuardian extends RenderLiving
{
	private ResourceLocation mainTexture = new ResourceLocation("moreplanets:textures/entity/europa_guardian.png");
	private ResourceLocation elderTexture = new ResourceLocation("textures/entity/guardian_elder.png");
	private ResourceLocation beamTexture = new ResourceLocation("textures/entity/guardian_beam.png");
	int field_177115_a;

	public RenderEuropaGuardian(RenderManager render)
	{
		super(render, new ModelEuropaGuardian(), 0.5F);
		this.addLayer(new LayerEuropaGuardianEyes(this));
		this.field_177115_a = 54;
	}

	public boolean shouldRenderGuardian(EntityEuropaGuardian entity, ICamera p_177113_2_, double p_177113_3_, double p_177113_5_, double p_177113_7_)
	{
		if (super.shouldRenderLiving(entity, p_177113_2_, p_177113_3_, p_177113_5_, p_177113_7_))
		{
			return true;
		}
		else
		{
			if (entity.func_175474_cn())
			{
				EntityLivingBase entitylivingbase = entity.getTargetedEntity();

				if (entitylivingbase != null)
				{
					Vec3 vec3 = this.func_177110_a(entitylivingbase, entitylivingbase.height * 0.5D, 1.0F);
					Vec3 vec31 = this.func_177110_a(entity, entity.getEyeHeight(), 1.0F);

					if (p_177113_2_.isBoundingBoxInFrustum(AxisAlignedBB.fromBounds(vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord)))
					{
						return true;
					}
				}
			}
			return false;
		}
	}

	private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_)
	{
		double d1 = p_177110_1_.lastTickPosX + (p_177110_1_.posX - p_177110_1_.lastTickPosX) * p_177110_4_;
		double d2 = p_177110_2_ + p_177110_1_.lastTickPosY + (p_177110_1_.posY - p_177110_1_.lastTickPosY) * p_177110_4_;
		double d3 = p_177110_1_.lastTickPosZ + (p_177110_1_.posZ - p_177110_1_.lastTickPosZ) * p_177110_4_;
		return new Vec3(d1, d2, d3);
	}

	public void func_177109_a(EntityEuropaGuardian entity, double p_177109_2_, double p_177109_4_, double p_177109_6_, float p_177109_8_, float p_177109_9_)
	{
		if (this.field_177115_a != 54)
		{
			this.mainModel = new ModelEuropaGuardian();
			this.field_177115_a = 54;
		}

		super.doRender(entity, p_177109_2_, p_177109_4_, p_177109_6_, p_177109_8_, p_177109_9_);
		EntityLivingBase entitylivingbase = entity.getTargetedEntity();

		if (entitylivingbase != null)
		{
			float f2 = entity.func_175477_p(p_177109_9_);
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			this.bindTexture(this.beamTexture);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.depthMask(true);
			float f3 = 240.0F;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f3, f3);
			GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
			float f4 = entity.worldObj.getTotalWorldTime() + p_177109_9_;
			float f5 = f4 * 0.5F % 1.0F;
			float f6 = entity.getEyeHeight();
			GlStateManager.pushMatrix();
			GlStateManager.translate((float)p_177109_2_, (float)p_177109_4_ + f6, (float)p_177109_6_);
			Vec3 vec3 = this.func_177110_a(entitylivingbase, entitylivingbase.height * 0.5D, p_177109_9_);
			Vec3 vec31 = this.func_177110_a(entity, f6, p_177109_9_);
			Vec3 vec32 = vec3.subtract(vec31);
			double d3 = vec32.lengthVector() + 1.0D;
			vec32 = vec32.normalize();
			float f7 = (float)Math.acos(vec32.yCoord);
			float f8 = (float)Math.atan2(vec32.zCoord, vec32.xCoord);
			GlStateManager.rotate(((float)Math.PI / 2F + -f8) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(f7 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
			byte b0 = 1;
			double d4 = f4 * 0.05D * (1.0D - (b0 & 1) * 2.5D);
			worldrenderer.startDrawingQuads();
			float f9 = f2 * f2;
			worldrenderer.setColorRGBA(64 + (int)(f9 * 240.0F), 32 + (int)(f9 * 192.0F), 128 - (int)(f9 * 64.0F), 255);
			double d5 = b0 * 0.2D;
			double d6 = d5 * 1.41D;
			double d7 = 0.0D + Math.cos(d4 + 2.356194490192345D) * d6;
			double d8 = 0.0D + Math.sin(d4 + 2.356194490192345D) * d6;
			double d9 = 0.0D + Math.cos(d4 + Math.PI / 4D) * d6;
			double d10 = 0.0D + Math.sin(d4 + Math.PI / 4D) * d6;
			double d11 = 0.0D + Math.cos(d4 + 3.9269908169872414D) * d6;
			double d12 = 0.0D + Math.sin(d4 + 3.9269908169872414D) * d6;
			double d13 = 0.0D + Math.cos(d4 + 5.497787143782138D) * d6;
			double d14 = 0.0D + Math.sin(d4 + 5.497787143782138D) * d6;
			double d15 = 0.0D + Math.cos(d4 + Math.PI) * d5;
			double d16 = 0.0D + Math.sin(d4 + Math.PI) * d5;
			double d17 = 0.0D + Math.cos(d4 + 0.0D) * d5;
			double d18 = 0.0D + Math.sin(d4 + 0.0D) * d5;
			double d19 = 0.0D + Math.cos(d4 + Math.PI / 2D) * d5;
			double d20 = 0.0D + Math.sin(d4 + Math.PI / 2D) * d5;
			double d21 = 0.0D + Math.cos(d4 + Math.PI * 3D / 2D) * d5;
			double d22 = 0.0D + Math.sin(d4 + Math.PI * 3D / 2D) * d5;
			double d23 = 0.0D;
			double d24 = 0.4999D;
			double d25 = -1.0F + f5;
			double d26 = d3 * (0.5D / d5) + d25;
			worldrenderer.addVertexWithUV(d15, d3, d16, d24, d26);
			worldrenderer.addVertexWithUV(d15, 0.0D, d16, d24, d25);
			worldrenderer.addVertexWithUV(d17, 0.0D, d18, d23, d25);
			worldrenderer.addVertexWithUV(d17, d3, d18, d23, d26);
			worldrenderer.addVertexWithUV(d19, d3, d20, d24, d26);
			worldrenderer.addVertexWithUV(d19, 0.0D, d20, d24, d25);
			worldrenderer.addVertexWithUV(d21, 0.0D, d22, d23, d25);
			worldrenderer.addVertexWithUV(d21, d3, d22, d23, d26);
			double d27 = 0.0D;

			if (entity.ticksExisted % 2 == 0)
			{
				d27 = 0.5D;
			}

			worldrenderer.addVertexWithUV(d7, d3, d8, 0.5D, d27 + 0.5D);
			worldrenderer.addVertexWithUV(d9, d3, d10, 1.0D, d27 + 0.5D);
			worldrenderer.addVertexWithUV(d13, d3, d14, 1.0D, d27);
			worldrenderer.addVertexWithUV(d11, d3, d12, 0.5D, d27);
			tessellator.draw();
			GlStateManager.popMatrix();
		}
	}

	protected void func_177112_a(EntityEuropaGuardian entity)
	{
		if (entity.isElder())
		{
			GlStateManager.scale(2.5F, 2.5F, 2.5F);
		}
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_177109_a((EntityEuropaGuardian)entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	public boolean shouldRenderLiving(EntityLiving entity, ICamera p_177104_2_, double p_177104_3_, double p_177104_5_, double p_177104_7_)
	{
		return this.shouldRenderGuardian((EntityEuropaGuardian)entity, p_177104_2_, p_177104_3_, p_177104_5_, p_177104_7_);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float p_77041_2_)
	{
		this.func_177112_a((EntityEuropaGuardian)entity);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_177109_a((EntityEuropaGuardian)entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ((EntityEuropaGuardian)entity).isElder() ? this.elderTexture : this.mainTexture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.func_177109_a((EntityEuropaGuardian)entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ)
	{
		return this.shouldRenderGuardian((EntityEuropaGuardian)entity, camera, camX, camY, camZ);
	}
}