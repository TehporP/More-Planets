/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelGrappy1;
import stevekung.mods.moreplanets.planets.fronos.client.render.entities.RenderGrappy;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;

@SideOnly(Side.CLIENT)
public class LayerGrappyWool implements LayerRenderer
{
	private ResourceLocation grappyTextures = new ResourceLocation("moreplanets:textures/entity/grappy/grappy.png");
	private RenderGrappy sheepRenderer;
	private ModelGrappy1 sheepModel = new ModelGrappy1();

	public LayerGrappyWool(RenderGrappy render)
	{
		this.sheepRenderer = render;
	}

	public void doRenderLayer(EntityGrappy entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		if (!entity.getSheared() && !entity.isInvisible())
		{
			this.sheepRenderer.bindTexture(this.grappyTextures);

			if (entity.hasCustomName() && "steve".equals(entity.getCustomNameTag()))
			{
				int i = entity.ticksExisted / 25 + entity.getEntityId();
				int j = EnumDyeColor.values().length;
				int k = i % j;
				int l = (i + 1) % j;
				float f7 = (entity.ticksExisted % 25 + par4) / 25.0F;
				float[] afloat1 = EntityGrappy.func_175513_a(EnumDyeColor.byMetadata(k));
				float[] afloat2 = EntityGrappy.func_175513_a(EnumDyeColor.byMetadata(l));
				GlStateManager.color(afloat1[0] * (1.0F - f7) + afloat2[0] * f7, afloat1[1] * (1.0F - f7) + afloat2[1] * f7, afloat1[2] * (1.0F - f7) + afloat2[2] * f7);
			}
			else
			{
				float[] afloat = EntityGrappy.func_175513_a(entity.getFleeceColor());
				GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			}
			this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
			this.sheepModel.setLivingAnimations(entity, par2, par3, par4);
			this.sheepModel.render(entity, par2, par3, par5, par6, par7, par8);
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		this.doRenderLayer((EntityGrappy)entity, par2, par3, par4, par5, par6, par7, par8);
	}
}