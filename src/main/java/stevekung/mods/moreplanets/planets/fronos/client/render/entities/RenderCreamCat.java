/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelCreamCat;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;

@SideOnly(Side.CLIENT)
public class RenderCreamCat extends RenderLiving
{
	private ResourceLocation chocolateCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/chocolate.png");
	private ResourceLocation spaceCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/default.png");
	private ResourceLocation vanillaCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/vanilla.png");
	private ResourceLocation strawberryCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/strawberry.png");
	private ResourceLocation orangeCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/orange.png");
	private ResourceLocation teaCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/tea.png");
	private ResourceLocation lemonCatTextures = new ResourceLocation("moreplanets:textures/entity/cream_cat/lemon.png");

	public RenderCreamCat(RenderManager render)
	{
		super(render, new ModelCreamCat(), 0.1F);
	}

	protected ResourceLocation func_110874_a(EntityCreamCat entity)
	{
		switch (entity.getTameSkin())
		{
		case 0:
		default:
			return this.spaceCatTextures;
		case 1:
			return this.vanillaCatTextures;
		case 2:
			return this.chocolateCatTextures;
		case 3:
			return this.strawberryCatTextures;
		case 4:
			return this.orangeCatTextures;
		case 5:
			return this.teaCatTextures;
		case 6:
			return this.lemonCatTextures;
		}
	}

	protected void preRenderOcelot(EntityCreamCat par1EntityOcelot, float par2)
	{
		super.preRenderCallback(par1EntityOcelot, par2);

		if (par1EntityOcelot.isTamed())
		{
			GlStateManager.scale(0.8F, 0.8F, 0.8F);
		}
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		this.preRenderOcelot((EntityCreamCat)entity, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_110874_a((EntityCreamCat)entity);
	}
}