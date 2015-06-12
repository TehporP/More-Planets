/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityJungleIrisFX extends EntityFX
{
	private String texture = "moreplanets:textures/particles/iris.png";
	private ResourceLocation particles = new ResourceLocation("textures/particle/particles.png");

	public EntityJungleIrisFX(World world, double x, double y, double z, double moX, double moY, double moZ)
	{
		super(world, x, y, z, moX, moY, moZ);
		float f = this.rand.nextFloat() * 0.1F + 0.2F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
		this.motionX *= 0.01999999955296516D;
		this.motionY *= 0.01999999955296516D;
		this.motionZ *= 0.01999999955296516D;
		this.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
		this.noClip = false;
		this.setParticleTextureIndex(82);
		this.setRBGColorF(1.0F, 1.0F, 1.0F);
		this.setSize(0.02F, 0.02F);
	}

	@Override
	public void func_180434_a(WorldRenderer worldRender, Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		Tessellator tessellator = Tessellator.getInstance();
		super.func_180434_a(worldRender, entity, par2, par3, par4, par5, par6, par7);

		tessellator.draw();
		GlStateManager.pushMatrix();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(this.texture));
		float sizeFactor = 0.1F * this.particleScale;
		float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - EntityFX.interpPosX);
		float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - EntityFX.interpPosY);
		float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - EntityFX.interpPosZ);
		worldRender.startDrawingQuads();
		worldRender.setBrightness(225);
		worldRender.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 0.75F);
		worldRender.addVertexWithUV(var13 - par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 - par5 * sizeFactor - par7 * sizeFactor, 0.0D, 1.0D);
		worldRender.addVertexWithUV(var13 - par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 - par5 * sizeFactor + par7 * sizeFactor, 1.0D, 1.0D);
		worldRender.addVertexWithUV(var13 + par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 + par5 * sizeFactor + par7 * sizeFactor, 1.0D, 0.0D);
		worldRender.addVertexWithUV(var13 + par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 + par5 * sizeFactor - par7 * sizeFactor, 0.0D, 0.0D);
		tessellator.draw();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.popMatrix();
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.particles);
		worldRender.startDrawingQuads();
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.99D;
		this.motionY *= 0.99D;
		this.motionZ *= 0.99D;

		if (this.particleMaxAge-- <= 0)
		{
			this.setDead();
		}
	}
}