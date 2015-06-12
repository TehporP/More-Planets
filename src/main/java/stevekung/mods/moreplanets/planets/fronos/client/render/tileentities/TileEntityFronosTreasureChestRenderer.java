/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.render.tileentities;
//package stevekung.mods.moreplanets.planets.fronos.render.tileentities;
//
//import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChest;
//import net.minecraft.client.model.ModelChest;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosTreasureChest;
//
//@SideOnly(Side.CLIENT)
//public class TileEntityFronosTreasureChestRenderer extends TileEntitySpecialRenderer
//{
//	private ResourceLocation textureNormal = new ResourceLocation("fronos:textures/model/fronos_treasure_chest.png");
//
//	private ModelTreasureChest simpleChest = new ModelTreasureChest();
//
//	public void func_180538_a(TileEntityFronosTreasureChest chest, double p_180538_2_, double p_180538_4_, double p_180538_6_, float p_180538_8_, int p_180538_9_)
//	{
//		int j;
//
//		if (!chest.hasWorldObj())
//		{
//			j = 0;
//		}
//		else
//		{
//			chest.getBlockType();
//			j = chest.getBlockMetadata();
//		}
//
//		ModelChest modelchest;
//
//		modelchest = this.simpleChest;
//
//		if (p_180538_9_ >= 0)
//		{
//			this.bindTexture(DESTROY_STAGES[p_180538_9_]);
//			GlStateManager.matrixMode(5890);
//			GlStateManager.pushMatrix();
//			GlStateManager.scale(4.0F, 4.0F, 1.0F);
//			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
//			GlStateManager.matrixMode(5888);
//		}
//		else
//		{
//			this.bindTexture(this.textureNormal);
//		}
//
//
//		GlStateManager.pushMatrix();
//		GlStateManager.enableRescaleNormal();
//
//		if (p_180538_9_ < 0)
//		{
//			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//		}
//
//		GlStateManager.translate((float)p_180538_2_, (float)p_180538_4_ + 1.0F, (float)p_180538_6_ + 1.0F);
//		GlStateManager.scale(1.0F, -1.0F, -1.0F);
//		GlStateManager.translate(0.5F, 0.5F, 0.5F);
//		short short1 = 0;
//
//		if (j == 2)
//		{
//			short1 = 180;
//		}
//		if (j == 3)
//		{
//			short1 = 0;
//		}
//		if (j == 4)
//		{
//			short1 = 90;
//		}
//		if (j == 5)
//		{
//			short1 = -90;
//		}
//
//		GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
//		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
//		float f1 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * p_180538_8_;
//		f1 = 1.0F - f1;
//		f1 = 1.0F - f1 * f1 * f1;
//		modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
//		modelchest.renderAll();
//		GlStateManager.disableRescaleNormal();
//		GlStateManager.popMatrix();
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//
//		if (p_180538_9_ >= 0)
//		{
//			GlStateManager.matrixMode(5890);
//			GlStateManager.popMatrix();
//			GlStateManager.matrixMode(5888);
//		}
//	}
//
//	@Override
//	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posZ, double p_180535_6_, float p_180535_8_, int p_180535_9_)
//	{
//		this.func_180538_a((TileEntityFronosTreasureChest)p_180535_1_, posX, posZ, p_180535_6_, p_180535_8_, p_180535_9_);
//	}
//}