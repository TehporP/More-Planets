/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NibiruModelGiantSpikeBoss extends ModelBase
{
	public NibiruModelGiantSpikeBoss()
	{
		this.body1 = new ModelRenderer(this, 0, 0);
		this.body1.addBox(-7F, -13F, -7F, 14, 13, 14, 0F);
		this.body1.setRotationPoint(0F, 24F, 0F);
		this.body2 = new ModelRenderer(this, 0, 0);
		this.body2.addBox(-6.5F, -10F, -6.5F, 13, 10, 13, 0F);
		this.body2.setRotationPoint(0F, 11F, 0F);
		this.body2.rotateAngleX = 0F;
		this.body2.rotateAngleY = 0F;
		this.body2.rotateAngleZ = 0F;
		this.body2.mirror = false;
		this.body3 = new ModelRenderer(this, 0, 0);
		this.body3.addBox(-6F, -8F, -6F, 12, 8, 12, 0F);
		this.body3.setRotationPoint(0F, 1F, 0F);
		this.body3.rotateAngleX = 0F;
		this.body3.rotateAngleY = 0F;
		this.body3.rotateAngleZ = 0F;
		this.body3.mirror = false;
		this.body4 = new ModelRenderer(this, 0, 0);
		this.body4.addBox(-4F, -6F, -4F, 8, 7, 8, 0F);
		this.body4.setRotationPoint(0F, -8F, 0F);
		this.body4.rotateAngleX = 0F;
		this.body4.rotateAngleY = 0F;
		this.body4.rotateAngleZ = 0F;
		this.body4.mirror = false;
		this.body5 = new ModelRenderer(this, 0, 0);
		this.body5.addBox(-3F, -7F, -3F, 6, 7, 6, 0F);
		this.body5.setRotationPoint(0F, -14F, 0F);
		this.body5.rotateAngleX = 0F;
		this.body5.rotateAngleY = 0F;
		this.body5.rotateAngleZ = 0F;
		this.body5.mirror = false;
		this.body6 = new ModelRenderer(this, 0, 0);
		this.body6.addBox(-2F, -5F, -2F, 4, 5, 4, 0F);
		this.body6.setRotationPoint(0F, -21F, 0F);
		this.body6.rotateAngleX = 0F;
		this.body6.rotateAngleY = 0F;
		this.body6.rotateAngleZ = 0F;
		this.body6.mirror = false;
		this.spike1 = new ModelRenderer(this, 0, 0);
		this.spike1.addBox(0F, -0.5F, -0.5F, 3, 1, 1, 0F);
		this.spike1.setRotationPoint(2F, -25F, 0F);
		this.spike1.rotateAngleX = 0F;
		this.spike1.rotateAngleY = 0F;
		this.spike1.rotateAngleZ = 0F;
		this.spike1.mirror = false;
		this.spike2 = new ModelRenderer(this, 0, 0);
		this.spike2.addBox(-3F, -0.5F, -0.5F, 3, 1, 1, 0F);
		this.spike2.setRotationPoint(-2F, -25F, 0F);
		this.spike2.rotateAngleX = 0F;
		this.spike2.rotateAngleY = 0F;
		this.spike2.rotateAngleZ = 0F;
		this.spike2.mirror = false;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3F, -6F, -3F, 6, 6, 6, 0F);
		this.head.setRotationPoint(0F, -26F, 0F);
		this.head.rotateAngleX = 0F;
		this.head.rotateAngleY = 0F;
		this.head.rotateAngleZ = 0F;
		this.head.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.body1.render(f5);
		this.body2.render(f5);
		this.body3.render(f5);
		this.body4.render(f5);
		this.body5.render(f5);
		this.body6.render(f5);
		this.spike1.render(f5);
		this.spike2.render(f5);
		this.head.render(f5);
	}

	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer body3;
	public ModelRenderer body4;
	public ModelRenderer body5;
	public ModelRenderer body6;
	public ModelRenderer spike1;
	public ModelRenderer spike2;
	public ModelRenderer head;
}
