/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/package stevekung.mods.moreplanets.fronos.core.render.blocks;

 import net.minecraft.block.Block;
 import net.minecraft.client.renderer.RenderBlocks;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.world.IBlockAccess;

 import org.lwjgl.opengl.GL11;
 import org.lwjgl.opengl.GL12;

 import stevekung.mods.moreplanets.fronos.tileentities.FronosTileEntitySpaceOysterClose;
 import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

 public class FronosBlockRendererSpaceOysterClose implements ISimpleBlockRenderingHandler
 {
	 final int renderID;
	 private final FronosTileEntitySpaceOysterClose oyster = new FronosTileEntitySpaceOysterClose();

	 public FronosBlockRendererSpaceOysterClose(int var1)
	 {
		 this.renderID = var1;
	 }

	 public void renderOyster(RenderBlocks renderBlocks, IBlockAccess iblockaccess, Block par1Block, int par2, int par3, int par4)
	 {
		 renderBlocks.overrideBlockTexture = par1Block.getBlockTexture(iblockaccess, par2, par3, par4, 0);

		 renderBlocks.clearOverrideBlockTexture();
	 }

	 @Override
	 public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	 {
		 GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
		 GL11.glTranslatef(-0.6F, -0.15F, -0.55F);
		 GL11.glScalef(1.0F, 1.0F, 1.0F);
		 TileEntityRenderer.instance.renderTileEntityAt(this.oyster, 0.0D, 0.0D, 0.0D, 0.0F);
		 GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	 }

	 @Override
	 public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	 {
		 this.renderOyster(renderer, world, block, x, y, z);
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