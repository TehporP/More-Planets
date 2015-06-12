/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.core.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class KoentusBlockRendererEgg implements ISimpleBlockRenderingHandler
{
	final int renderID;

	public KoentusBlockRendererEgg(int var1)
	{
		this.renderID = var1;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
	{
		this.renderBlockMeteor(var7, var5, var1, var2, var3, var4);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return this.renderID;
	}

	public static void renderInvNormalBlock(RenderBlocks renderBlocks, Block par1Block, int var2)
	{
		renderBlocks.setRenderBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.52F, 0.0F, 0.4F, 0.68F, 0.68F, 0.6F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.65F, 0.75F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.15F, 0.0F, 0.25F, 0.2F, 0.4F, 0.75F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.8F, 0.0F, 0.25F, 0.85F, 0.4F, 0.75F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.1F, 0.75F, 0.4F, 0.2F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.8F, 0.75F, 0.4F, 0.9F);
		KoentusBlockRendererEgg.renderStandardBlock(renderBlocks, par1Block, var2);

		renderBlocks.clearOverrideBlockTexture();
	}

	private static void renderStandardBlock(RenderBlocks var0, Block var1, int var2)
	{
		GL11.glPushMatrix();
		final Tessellator var3 = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var3.startDrawingQuads();
		var3.setNormal(0.0F, -1.0F, 0.0F);
		var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(0, var2));
		var3.draw();
		var3.startDrawingQuads();
		var3.setNormal(0.0F, 1.0F, 0.0F);
		var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(1, var2));
		var3.draw();
		var3.startDrawingQuads();
		var3.setNormal(0.0F, 0.0F, -1.0F);
		var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(2, var2));
		var3.draw();
		var3.startDrawingQuads();
		var3.setNormal(0.0F, 0.0F, 1.0F);
		var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(3, var2));
		var3.draw();
		var3.startDrawingQuads();
		var3.setNormal(-1.0F, 0.0F, 0.0F);
		var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(4, var2));
		var3.draw();
		var3.startDrawingQuads();
		var3.setNormal(1.0F, 0.0F, 0.0F);
		var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(5, var2));
		var3.draw();
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		KoentusBlockRendererEgg.renderInvNormalBlock(renderer, block, metadata);
	}

	public void renderBlockMeteor(RenderBlocks renderBlocks, Block par1Block, IBlockAccess var1, int par2, int par3, int par4)
	{
		renderBlocks.setRenderBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.52F, 0.0F, 0.4F, 0.68F, 0.68F, 0.6F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.65F, 0.75F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.15F, 0.0F, 0.25F, 0.2F, 0.4F, 0.75F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.8F, 0.0F, 0.25F, 0.85F, 0.4F, 0.75F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.1F, 0.75F, 0.4F, 0.2F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.setRenderBounds(0.25F, 0.0F, 0.8F, 0.75F, 0.4F, 0.9F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.clearOverrideBlockTexture();
		par1Block.setBlockBoundsForItemRender();
		renderBlocks.uvRotateTop = 0;
	}
}