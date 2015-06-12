/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.render.blocks;

import static stevekung.mods.moreplanets.core.proxy.ClientProxyMP.infectedDirtHeliumRenderID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;
import stevekung.mods.moreplanets.core.util.MPBlockSkinRenderUtil;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class NibiruBlockRendererInfectedHeliumDirt implements ISimpleBlockRenderingHandler
{
	private final int renderID;

	public NibiruBlockRendererInfectedHeliumDirt(int var1)
	{
		this.renderID = var1;
	}

	@Override
	public void renderInventoryBlock (Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == infectedDirtHeliumRenderID) {
			this.renderStandardInvBlock(renderer, block, metadata);
		}
		this.renderHeliumBlock(renderer, block, metadata);
	}

	private void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	private void renderHeliumBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		Block helium = NibiruBlocks.heliumBlock;
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.8F, 0.0F);
		renderblocks.renderFaceYNeg(helium, 0.0D, 0.01D, 0.0D, helium.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.8F, 0.0F);
		renderblocks.renderFaceYPos(helium, 0.0D, 0.02D, 0.0D, helium.getIcon(1, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock (IBlockAccess world, int x, int y, int z, Block getBlock, int modelID, RenderBlocks renderer)
	{
		if (modelID == infectedDirtHeliumRenderID) {
			if (ClientProxyMP.infectedDirtHeliumRenderPass == 0)
			{
				renderer.renderStandardBlock(getBlock, x, y, z);
			}
			else
			{
				renderer.setRenderBounds(0.0, 0.8135, 0.0, 1.0, 0.8880, 1.0);

				Block block = Block.blocksList[NibiruBlocks.heliumBlock.blockID];
				MPBlockSkinRenderUtil.renderMetadataBlock(block, 0, x, y, z, renderer, world);
			}
		}
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
}