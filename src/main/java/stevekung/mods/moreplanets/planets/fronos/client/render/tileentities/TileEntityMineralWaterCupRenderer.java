/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.tileentities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelFilledCup;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityMineralWaterCup;

@SideOnly(Side.CLIENT)
public class TileEntityMineralWaterCupRenderer extends TileEntitySpecialRenderer
{
	private ResourceLocation textureNormal = new ResourceLocation("moreplanets:textures/model/cup/mineral_water.png");
	private ModelFilledCup cup = new ModelFilledCup();

	public void renderTile(TileEntityMineralWaterCup cup, double par2, double par3, double par4, int par5)
	{
		int meta;

		if (!cup.hasWorldObj())
		{
			meta = 0;
		}
		else
		{
			cup.getBlockType();
			meta = cup.getBlockMetadata();
		}

		if (par5 >= 0)
		{
			this.bindTexture(DESTROY_STAGES[par5]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		else
		{
			this.bindTexture(this.textureNormal);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();

		if (par5 < 0)
		{
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float)par2, (float)par3 + 1.0F, (float)par4 + 1.0F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		switch (meta)
		{
		case 2:
			short1 = 180;
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			break;
		case 3:
			short1 = 0;
			GlStateManager.translate(0.5F, -0.5F, 0.5F);
			break;
		case 4:
			short1 = 90;
			GlStateManager.translate(0.5F, -0.5F, -0.5F);
			break;
		case 5:
			short1 = -90;
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			break;
		}
		GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		this.cup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (par5 >= 0)
		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double posX, double posZ, double par4, float par5, int par6)
	{
		this.renderTile((TileEntityMineralWaterCup)tile, posX, posZ, par4, par6);
	}
}