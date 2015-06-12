/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.client.render.entities.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.render.entities.RenderSpaceWolf;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;

@SideOnly(Side.CLIENT)
public class LayerSpaceWolfCollar implements LayerRenderer
{
	private ResourceLocation field_177147_a = new ResourceLocation("moreplanets:textures/model/space_wolf/space_wolf_collar.png");
	private RenderSpaceWolf render;

	public LayerSpaceWolfCollar(RenderSpaceWolf render)
	{
		this.render = render;
	}

	public void func_177145_a(EntitySpaceWolf entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		if (entity.isTamed() && !entity.isInvisible())
		{
			this.render.bindTexture(this.field_177147_a);
			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			this.render.getMainModel().render(entity, par2, par3, par4, par5, par6, par7);
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
		this.func_177145_a((EntitySpaceWolf)entity, par2, par3, par5, par6, par7, par8);
	}
}