/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.fronos.entities.FronosEntityGrappy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderGrappy extends RenderLiving
{
	private static final ResourceLocation grappyTextures = new ResourceLocation("fronos:textures/model/grappy_sheared.png");
	private static final ResourceLocation shearedGrappyTextures = new ResourceLocation("fronos:textures/model/grappy.png");

	public FronosRenderGrappy(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(par1ModelBase, par3);
		this.setRenderPassModel(par2ModelBase);
	}

	protected int setWoolColorAndRender(FronosEntityGrappy par1EntitySheep, int par2, float par3)
	{
		if (par2 == 0 && !par1EntitySheep.getSheared())
		{
			this.bindTexture(grappyTextures);
			float f1 = 1.0F;
			int j = par1EntitySheep.getFleeceColor();
			GL11.glColor3f(f1 * FronosEntityGrappy.fleeceColorTable[j][0], f1 * FronosEntityGrappy.fleeceColorTable[j][1], f1 * FronosEntityGrappy.fleeceColorTable[j][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	protected ResourceLocation func_110883_a(FronosEntityGrappy par1EntitySheep)
	{
		return shearedGrappyTextures;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.setWoolColorAndRender((FronosEntityGrappy)par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.func_110883_a((FronosEntityGrappy)par1Entity);
	}
}