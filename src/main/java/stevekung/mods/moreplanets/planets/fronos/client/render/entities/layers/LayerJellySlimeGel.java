/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities.layers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderJellySlime;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;

@SideOnly(Side.CLIENT)
public class LayerJellySlimeGel implements LayerRenderer
{
	private RenderJellySlime slimeRenderer;
	private ModelBase slimeModel = new ModelSlime(0);

	public LayerJellySlimeGel(RenderJellySlime render)
	{
		this.slimeRenderer = render;
	}

	public void doRenderLayer(EntityJellySlime entity, float par2, float par3, float par5, float par6, float par7, float par8)
	{
		if (!entity.isInvisible())
		{
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableNormalize();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 771);
			this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
			this.slimeModel.render(entity, par2, par3, par5, par6, par7, par8);
			GlStateManager.disableBlend();
			GlStateManager.disableNormalize();
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}

	@Override
	public void doRenderLayer(EntityLivingBase  entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		this.doRenderLayer((EntityJellySlime)entity, par2, par3, par5, par6, par7, par8);
	}
}