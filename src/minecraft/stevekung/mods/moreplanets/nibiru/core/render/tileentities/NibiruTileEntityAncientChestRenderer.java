/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core.render.tileentities;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlockAncientChest;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityAncientChest;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NibiruTileEntityAncientChestRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation christmasChest = new ResourceLocation("textures/entity/chest/christmas.png");
	private static final ResourceLocation largeChristmasChest = new ResourceLocation("textures/entity/chest/christmas_double.png");
	private static final ResourceLocation chestNormal = new ResourceLocation("nibiru:textures/model/nibiruAncientChest.png");
	private static final ResourceLocation largeChestNormal = new ResourceLocation("nibiru:textures/model/nibiruAncientChestLarge.png");
	//private static final ResourceLocation mcBirthChestNormal = new ResourceLocation("nibiru:textures/model/epicChest/mcBirthChest.png");
	//private static final ResourceLocation mcBirthChestLarge = new ResourceLocation("nibiru:textures/model/epicChest/mcBirthChestLarge.png");

	private ModelChest chestModel = new ModelChest();
	private ModelChest largeChestModel = new ModelLargeChest();

	private boolean isChristmas;
	/*private boolean isSongkran;
    private boolean isLoikratong;
    private boolean isFatherDay;
    private boolean isMotherDay;
    private boolean isNewYear;
    private boolean isMCCommanderBirthDay;
    private boolean isValentine;*/

	public NibiruTileEntityAncientChestRenderer()
	{
		Calendar calendar = Calendar.getInstance();
		//Month					//Day Before			//Day After
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
		{
			this.isChristmas = true;
		}

		/*if (calendar.get(2) + 1 == 4 && calendar.get(5) >= 12 && calendar.get(5) <= 16)
        {
            this.isSongkran = true;
        }

        if (calendar.get(2) + 1 == 11 && calendar.get(5) == 6)
        {
            this.isLoikratong = true;
        }

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 4 && calendar.get(5) <= 6)
        {
            this.isFatherDay = true;
        }

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 30 && calendar.get(5) <= 31 || calendar.get(2) + 1 == 1 && calendar.get(5) >= 1 && calendar.get(5) <= 2)
        {
            this.isNewYear = true;
        }

        if (calendar.get(2) + 1 == 2 && calendar.get(5) == 2)
        {
            this.isMCCommanderBirthDay = true;
        }

        if (calendar.get(2) + 1 == 2 && calendar.get(5) == 14)
        {
            this.isValentine = true;
        }

        if (calendar.get(2) + 1 == 8 && calendar.get(5) >= 11 && calendar.get(5) <= 13)
        {
            this.isMotherDay = true;
        }*/
	}

	public void renderTileEntityChestAt(NibiruTileEntityAncientChest par1TileEntityChest, double par2, double par4, double par6, float par8)
	{
		int i;

		if (!par1TileEntityChest.hasWorldObj())
		{
			i = 0;
		}
		else
		{
			Block block = par1TileEntityChest.getBlockType();
			i = par1TileEntityChest.getBlockMetadata();

			if (block instanceof NibiruBlockAncientChest && i == 0)
			{
				try
				{
					((NibiruBlockAncientChest)block).unifyAdjacentChests(par1TileEntityChest.getWorldObj(), par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
				}
				catch (ClassCastException e)
				{
					FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest",
							par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
				}
				i = par1TileEntityChest.getBlockMetadata();
			}

			par1TileEntityChest.checkForAdjacentChests();
		}

		if (par1TileEntityChest.adjacentChestZNeg == null && par1TileEntityChest.adjacentChestXNeg == null)
		{
			ModelChest modelchest;

			if (par1TileEntityChest.adjacentChestXPos == null && par1TileEntityChest.adjacentChestZPosition == null)
			{
				modelchest = this.chestModel;

				if (this.isChristmas)
				{
					this.bindTexture(christmasChest);
				}
				/*else if (this.isSongkran) //TODO
                {
                    this.bindTexture(christmasChest);
                }
                else if (this.isLoikratong) //TODO
                {
                    this.bindTexture(christmasChest);
                }
                else if (this.isFatherDay) //TODO
                {
                    this.bindTexture(christmasChest);
                }
                else if (this.isNewYear) //TODO
                {
                    this.bindTexture(christmasChest);
                }
                else if (this.isMCCommanderBirthDay)
                {
                    this.bindTexture(mcBirthChestNormal);
                }
                else if (this.isValentine) //TODO
                {
                    this.bindTexture(christmasChest);
                }
                else if (this.isMotherDay) //TODO
                {
                    this.bindTexture(christmasChest);
                }*/
				else
				{
					this.bindTexture(chestNormal);
				}
			}
			else
			{
				modelchest = this.largeChestModel;

				if (this.isChristmas)
				{
					this.bindTexture(largeChristmasChest);
				}
				/*else if (this.isSongkran) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }
                else if (this.isLoikratong) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }
                else if (this.isFatherDay) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }
                else if (this.isNewYear) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }
                else if (this.isMCCommanderBirthDay)
                {
                    this.bindTexture(mcBirthChestLarge);
                }
                else if (this.isValentine) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }
                else if (this.isMotherDay) //TODO
                {
                    this.bindTexture(largeChristmasChest);
                }*/
				else
				{
					this.bindTexture(largeChestNormal);
				}
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (i == 2)
			{
				short1 = 180;
			}

			if (i == 3)
			{
				short1 = 0;
			}

			if (i == 4)
			{
				short1 = 90;
			}

			if (i == 5)
			{
				short1 = -90;
			}

			if (i == 2 && par1TileEntityChest.adjacentChestXPos != null)
			{
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if (i == 5 && par1TileEntityChest.adjacentChestZPosition != null)
			{
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);
			}

			GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f1 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
			float f2;

			if (par1TileEntityChest.adjacentChestZNeg != null)
			{
				f2 = par1TileEntityChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityChest.adjacentChestZNeg.lidAngle - par1TileEntityChest.adjacentChestZNeg.prevLidAngle) * par8;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			if (par1TileEntityChest.adjacentChestXNeg != null)
			{
				f2 = par1TileEntityChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityChest.adjacentChestXNeg.lidAngle - par1TileEntityChest.adjacentChestXNeg.prevLidAngle) * par8;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
			modelchest.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderTileEntityChestAt((NibiruTileEntityAncientChest)par1TileEntity, par2, par4, par6, par8);
	}
}
