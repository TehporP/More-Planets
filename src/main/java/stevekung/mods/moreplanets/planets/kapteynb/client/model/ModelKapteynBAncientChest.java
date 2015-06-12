/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKapteynBAncientChest extends ModelBase
{
	public ModelRenderer chestLid;
	public ModelRenderer chestBelow;
	public ModelRenderer chestKnob;

	public ModelKapteynBAncientChest()
	{
		this.chestLid = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.chestLid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
		this.chestLid.rotationPointX = 1.0F;
		this.chestLid.rotationPointY = 7.0F;
		this.chestLid.rotationPointZ = 15.0F;

		this.chestKnob = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.chestKnob.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
		this.chestKnob.rotationPointX = 8.0F;
		this.chestKnob.rotationPointY = 7.0F;
		this.chestKnob.rotationPointZ = 15.0F;

		this.chestBelow = new ModelRenderer(this, 0, 19).setTextureSize(64, 64);
		this.chestBelow.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
		this.chestBelow.rotationPointX = 1.0F;
		this.chestBelow.rotationPointY = 6.0F;
		this.chestBelow.rotationPointZ = 1.0F;
	}

	public void renderAll()
	{
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		this.chestKnob.rotateAngleX = this.chestLid.rotateAngleX;
		this.chestLid.render(0.0625F);
		this.chestKnob.render(0.0625F);
		this.chestBelow.render(0.0625F);
		GlStateManager.disableBlend();
	}
}