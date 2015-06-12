/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosEntityGoldenGrassFX extends EntityFX
{
	float reddustParticleScale;

	public FronosEntityGoldenGrassFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
	{
		this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
	}

	public FronosEntityGoldenGrassFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10, float par11)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;

		if (par9 == 0.0F)
		{
			par9 = 1.0F;
		}

		this.particleRed = 1.0F;
		this.particleGreen = 0.85F;
		this.particleBlue = 0.0F;
		this.particleScale *= 0.75F;
		this.particleScale *= par8;
		this.reddustParticleScale = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)(this.particleMaxAge * par8);
		this.noClip = false;
	}

	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		float f6 = (this.particleAge + par2) / this.particleMaxAge * 32.0F;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}

		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}

		par1Tessellator.draw();
		GL11.glPushMatrix();

		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 1);
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setBrightness(250);

		float f7 = f6 + 0.0624375F;
		float f8 = this.particleTextureIndexY / 16.0F;
		float f9 = f8 + 0.0624375F;
		float f10 = 0.1F * this.particleScale;
		float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
		float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
		float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);

		par1Tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
		par1Tessellator.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, f7, f9);
		par1Tessellator.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, f7, f8);
		par1Tessellator.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, f6, f8);
		par1Tessellator.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, f6, f9);

		par1Tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);

		GL11.glPopMatrix();
		par1Tessellator.startDrawingQuads();

		this.particleScale = this.reddustParticleScale * f6;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}