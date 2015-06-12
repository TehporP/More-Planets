/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class FronosItemRendererCup implements IItemRenderer
{
	private static final ResourceLocation emptyCupTextures = new ResourceLocation("fronos:textures/model/ovantineCup.png");
	private static final ResourceLocation ovantineCup = new ResourceLocation("fronos:textures/model/ovantineCup.png");
	private static final ResourceLocation mineralWaterCup = new ResourceLocation("fronos:textures/model/mineralWaterCup.png");
	private static final ResourceLocation coconutMilkCup = new ResourceLocation("fronos:textures/model/coconutMilkCup.png");
	private static final ResourceLocation cheeseOfMilkCup = new ResourceLocation("fronos:textures/model/cheeseOfMilkCup.png");

	private ModelBase emptyCup;
	private ModelBase fullCup;

	public FronosItemRendererCup(ModelBase emptyCup, ModelBase fullCup)
	{
		this.emptyCup = emptyCup;
		this.fullCup = fullCup;
	}

	private void renderOvantineCup(ItemRenderType type, RenderBlocks render, ItemStack itemStack, float translateX, float translateY, float translateZ)
	{
		int meta = itemStack.getItemDamage();

		GL11.glPushMatrix();
		this.transform(type);

		if (meta == 0)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(emptyCupTextures);
			this.emptyCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		else if (meta == 1)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(mineralWaterCup);
			this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		else if (meta == 2)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(ovantineCup);
			this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		else if (meta == 3)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(coconutMilkCup);
			this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		else if (meta == 4)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(cheeseOfMilkCup);
			this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
	}

	public void transform(ItemRenderType type)
	{
		GL11.glScalef(2.0F, 2.0F, 2.0F);

		if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glRotatef(180, 0.0F, 0, 0);
			GL11.glRotatef(1, 0.0F, 1, 1);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glTranslatef(-0.25F, -1.5F, -0.25F);
		}

		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			GL11.glRotatef(30, 0, 1, 0);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslatef(0.0F, -1.65F, -0.5F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}

		if (type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY)
		{
			GL11.glTranslatef(0, 0.0F, 0);

			if (type == ItemRenderType.INVENTORY)
			{
				GL11.glTranslatef(0, 1.25F, 0);
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glRotatef(180.0F, 1.0F, 0, 0);
				GL11.glRotatef(Sys.getTime() / 90F % 360F, 0F, 1F, 0F);
			}
			else
			{
				GL11.glTranslatef(0, 1.0F, 0);
				GL11.glScalef(0.7F, 0.7F, 0.7F);
				GL11.glRotatef(180.0F, 1.0F, 0, 0);
			}
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch (type)
		{
		case ENTITY:
			return true;
		case EQUIPPED:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch (type)
		{
		case EQUIPPED:
			this.renderOvantineCup(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		case EQUIPPED_FIRST_PERSON:
			this.renderOvantineCup(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		case INVENTORY:
			this.renderOvantineCup(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		case ENTITY:
			this.renderOvantineCup(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
			break;
		default:
			break;
		}
	}
}