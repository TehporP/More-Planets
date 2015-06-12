/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.client.render.tileentities;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.venus.blocks.BlockVenusAncientChest;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusAncientChest;

@SideOnly(Side.CLIENT)
public class TileEntityVenusAncientChestRenderer extends TileEntitySpecialRenderer
{
	private ResourceLocation textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
	private ResourceLocation textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
	private ResourceLocation textureNormalDouble = new ResourceLocation("moreplanets:textures/model/venus_ancient_chest_double.png");
	private ResourceLocation textureNormal = new ResourceLocation("moreplanets:textures/model/venus_ancient_chest.png");
	private ResourceLocation steveChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest.png");
	private ResourceLocation steveLargeChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest_double.png");

	private ModelChest simpleChest = new ModelChest();
	private ModelChest largeChest = new ModelLargeChest();

	private Calendar calendar = Calendar.getInstance();

	private boolean isChristmas;
	private boolean isSteveBirthDay;

	public TileEntityVenusAncientChestRenderer()
	{
		//                        Month					//Day Before			//Day After
		if (this.calendar.get(2) + 1 == 12 && this.calendar.get(5) >= 24 && this.calendar.get(5) <= 26)
		{
			this.isChristmas = true;
		}
		else if (this.calendar.get(2) + 1 == 2 && this.calendar.get(5) >= 1 && this.calendar.get(5) <= 3)
		{
			this.isSteveBirthDay = true;
		}
	}

	public void func_180538_a(TileEntityVenusAncientChest chest, double p_180538_2_, double p_180538_4_, double p_180538_6_, float p_180538_8_, int p_180538_9_)
	{
		int j;

		if (!chest.hasWorldObj())
		{
			j = 0;
		}
		else
		{
			Block block = chest.getBlockType();
			j = chest.getBlockMetadata();

			if (block instanceof BlockVenusAncientChest && j == 0)
			{
				((BlockVenusAncientChest)block).checkForSurroundingChests(chest.getWorld(), chest.getPos(), chest.getWorld().getBlockState(chest.getPos()));
				j = chest.getBlockMetadata();
			}
			chest.checkForAdjacentChests();
		}

		if (chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null)
		{
			ModelChest modelchest;

			if (chest.adjacentChestXPos == null && chest.adjacentChestZPos == null)
			{
				modelchest = this.simpleChest;

				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				}
				else if (this.isChristmas)
				{
					this.bindTexture(this.textureChristmas);
				}
				else if (this.isSteveBirthDay)
				{
					this.bindTexture(this.steveChestNormal);
				}
				else
				{
					this.bindTexture(this.textureNormal);
				}
			}
			else
			{
				modelchest = this.largeChest;

				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				}
				else if (this.isChristmas)
				{
					this.bindTexture(this.textureChristmasDouble);
				}
				else if (this.isSteveBirthDay)
				{
					this.bindTexture(this.steveLargeChestNormal);
				}
				else
				{
					this.bindTexture(this.textureNormalDouble);
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			if (p_180538_9_ < 0)
			{
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}

			GlStateManager.translate((float)p_180538_2_, (float)p_180538_4_ + 1.0F, (float)p_180538_6_ + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (j == 2)
			{
				short1 = 180;
			}
			if (j == 3)
			{
				short1 = 0;
			}
			if (j == 4)
			{
				short1 = 90;
			}
			if (j == 5)
			{
				short1 = -90;
			}

			if (j == 2 && chest.adjacentChestXPos != null)
			{
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}
			if (j == 5 && chest.adjacentChestZPos != null)
			{
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}

			GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float f1 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * p_180538_8_;
			float f2;

			if (chest.adjacentChestZNeg != null)
			{
				f2 = chest.adjacentChestZNeg.prevLidAngle + (chest.adjacentChestZNeg.lidAngle - chest.adjacentChestZNeg.prevLidAngle) * p_180538_8_;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			if (chest.adjacentChestXNeg != null)
			{
				f2 = chest.adjacentChestXNeg.prevLidAngle + (chest.adjacentChestXNeg.lidAngle - chest.adjacentChestXNeg.prevLidAngle) * p_180538_8_;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
			modelchest.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			if (p_180538_9_ >= 0)
			{
				GlStateManager.matrixMode(5890);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(5888);
			}
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posZ, double p_180535_6_, float p_180535_8_, int p_180535_9_)
	{
		this.func_180538_a((TileEntityVenusAncientChest)p_180535_1_, posX, posZ, p_180535_6_, p_180535_8_, p_180535_9_);
	}
}