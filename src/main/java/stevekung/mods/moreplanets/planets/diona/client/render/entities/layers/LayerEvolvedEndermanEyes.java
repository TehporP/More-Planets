/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.client.render.entities.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;

@SideOnly(Side.CLIENT)
public class LayerEvolvedEndermanEyes implements LayerRenderer
{
	private ResourceLocation field_177203_a = new ResourceLocation("moreplanets:textures/entity/evolved_enderman/evolved_enderman_eyes.png");
	private RenderEvolvedEnderman field_177202_b;

	public LayerEvolvedEndermanEyes(RenderEvolvedEnderman render)
	{
		this.field_177202_b = render;
	}

	public void func_177201_a(EntityEvolvedEnderman entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		this.field_177202_b.bindTexture(this.field_177203_a);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(1, 1);
		GlStateManager.disableLighting();

		if (entity.isInvisible())
		{
			GlStateManager.depthMask(false);
		}
		else
		{
			GlStateManager.depthMask(true);
		}

		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GlStateManager.enableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_177202_b.getMainModel().render(entity, par2, par3, par5, par6, par7, par8);
		this.field_177202_b.func_177105_a(entity, par4);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		this.func_177201_a((EntityEvolvedEnderman)entity, par2, par3, par4, par5, par6, par7, par8);
	}
}