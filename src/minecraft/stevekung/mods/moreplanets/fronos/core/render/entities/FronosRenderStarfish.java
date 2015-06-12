/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.fronos.core.models.entities.FronosModelStarfish;
import stevekung.mods.moreplanets.fronos.entities.FronosEntitySpaceStarfish;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderStarfish extends RenderLiving
{
	private static final ResourceLocation starfishTextures = new ResourceLocation("fronos:textures/model/spaceStarfish.png");

	public FronosRenderStarfish()
	{
		super(new FronosModelStarfish(), 0.50F);
	}

	protected ResourceLocation starFishTexture(FronosEntitySpaceStarfish starFish)
	{
		return starfishTextures;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderStarFish((FronosEntitySpaceStarfish)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderStarFish((FronosEntitySpaceStarfish)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.starFishTexture((FronosEntitySpaceStarfish)par1Entity);
	}

	public void renderStarFish(FronosEntitySpaceStarfish starFish, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(starFish, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
	{
		return 0.0F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return -1;
	}
}