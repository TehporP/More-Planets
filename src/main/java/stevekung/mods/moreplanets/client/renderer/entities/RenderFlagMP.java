/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.client.renderer.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.model.ModelFlagMP;
import stevekung.mods.moreplanets.common.entities.EntityFlagMP;

@SideOnly(Side.CLIENT)
public class RenderFlagMP extends Render
{
	static ResourceLocation[] flagTexture;
	static String[] flag = { "thai", "laos", "singapore", "myanmar", "marlaysia", "vietnam", "indonesia", "philippines", "cambodia", "brunei" };
	private ModelFlagMP modelFlag;

	static
	{
		flagTexture = new ResourceLocation[flag.length];

		for (int i = 0; i < flag.length; i++)
		{
			RenderFlagMP.flagTexture[i] = new ResourceLocation("moreplanets:textures/entity/flag/" + flag[i] + ".png");
		}
	}

	public RenderFlagMP(RenderManager render)
	{
		super(render);
		this.shadowSize = 1F;
		this.modelFlag = new ModelFlagMP();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return RenderFlagMP.flagTexture[((EntityFlagMP)entity).getType()];
	}

	public void renderFlag(EntityFlagMP entity, double par2, double par4, double par6)
	{
		GlStateManager.pushMatrix();
		long var10 = entity.getEntityId() * 493286711L;
		var10 = var10 * var10 * 4392167121L + var10 * 98761L;
		float var12 = (((var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float var13 = (((var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float var14 = (((var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		GlStateManager.translate(var12, var13, var14);
		GlStateManager.translate((float) par2, (float) par4 + 1.5F, (float) par6);
		GlStateManager.rotate(180.0F - entity.getFacingAngle(), 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.modelFlag.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderFlag((EntityFlagMP)entity, par2, par4, par6);
	}
}