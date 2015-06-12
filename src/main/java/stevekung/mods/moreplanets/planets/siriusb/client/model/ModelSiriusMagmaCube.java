/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;

@SideOnly(Side.CLIENT)
public class ModelSiriusMagmaCube extends ModelBase
{
	ModelRenderer[] segments = new ModelRenderer[8];
	ModelRenderer core;

	public ModelSiriusMagmaCube()
	{
		for (int i = 0; i < this.segments.length; ++i)
		{
			byte b0 = 0;
			int j = i;

			if (i == 2)
			{
				b0 = 24;
				j = 10;
			}
			else if (i == 3)
			{
				b0 = 24;
				j = 19;
			}
			this.segments[i] = new ModelRenderer(this, b0, j);
			this.segments[i].addBox(-4.0F, 16 + i, -4.0F, 8, 1, 8);
		}
		this.core = new ModelRenderer(this, 0, 16);
		this.core.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase living, float p_78086_2_, float p_78086_3_, float p_78086_4_)
	{
		EntitySiriusMagmaCube entitymagmacube = (EntitySiriusMagmaCube)living;
		float f3 = entitymagmacube.prevSquishFactor + (entitymagmacube.squishFactor - entitymagmacube.prevSquishFactor) * p_78086_4_;

		if (f3 < 0.0F)
		{
			f3 = 0.0F;
		}

		for (int i = 0; i < this.segments.length; ++i)
		{
			this.segments[i].rotationPointY = -(4 - i) * f3 * 1.7F;
		}
	}

	@Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
	{
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
		this.core.render(p_78088_7_);

		for (int i = 0; i < this.segments.length; ++i)
		{
			this.segments[i].render(p_78088_7_);
		}
	}
}