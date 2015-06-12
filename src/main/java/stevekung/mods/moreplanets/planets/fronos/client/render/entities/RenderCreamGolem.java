/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.entities;

import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;

@SideOnly(Side.CLIENT)
public class RenderCreamGolem extends RenderLiving
{
	private ResourceLocation vanillaTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/vanilla.png");
	private ResourceLocation chocolateTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/chocolate.png");
	private ResourceLocation strawberryTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/strawberry.png");
	private ResourceLocation orangeTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/orange.png");
	private ResourceLocation teaTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/tea.png");
	private ResourceLocation lemonTextures = new ResourceLocation("moreplanets:textures/entity/cream_golem/lemon.png");

	public RenderCreamGolem(RenderManager render)
	{
		super(render, new ModelSnowMan(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_110874_a((EntityCreamGolem)entity);
	}

	protected ResourceLocation func_110874_a(EntityCreamGolem entity)
	{
		switch (entity.getCreamGolemType())
		{
		case 0:
		default:
			return this.vanillaTextures;
		case 1:
			return this.chocolateTextures;
		case 2:
			return this.strawberryTextures;
		case 3:
			return this.orangeTextures;
		case 4:
			return this.teaTextures;
		case 5:
			return this.lemonTextures;
		}
	}
}