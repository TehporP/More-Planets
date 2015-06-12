/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei.handler;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.core.nei.NEIMorePlanetConfig;
import codechicken.core.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class MPSpaceshipRecipeHandler extends TemplateRecipeHandler
{
	private static final ResourceLocation rocketGuiTexture = new ResourceLocation("galacticraftmars:textures/gui/schematic_rocket_T2.png");

	public String getRecipeId()
	{
		return "galacticraft.rocketT4";
	}

	@Override
	public int recipiesPerPage()
	{
		return 1;
	}

	public Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRecipes()
	{
		return NEIMorePlanetConfig.getRocketBenchRecipes();
	}

	@Override
	public void drawBackground(int i)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(MPSpaceshipRecipeHandler.rocketGuiTexture);
		GuiDraw.drawTexturedModalRect(0, -8, 3, 4, 168, 140);
	}

	@Override
	public void drawExtras(int i)
	{
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void loadTransferRects()
	{
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if (outputId.equals(this.getRecipeId()))
		{
			for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
			{
				this.arecipes.add(new CachedRocketRecipe(irecipe));
			}
		}
		super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
		{
			if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getValue().item, result))
			{
				this.arecipes.add(new CachedRocketRecipe(irecipe));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
		{
			for (final PositionedStack pstack : irecipe.getKey())
			{
				if (NEIServerUtils.areStacksSameTypeCrafting(ingredient, pstack.item))
				{
					this.arecipes.add(new CachedRocketRecipe(irecipe));
					break;
				}
			}
		}
	}

	public class CachedRocketRecipe extends TemplateRecipeHandler.CachedRecipe
	{
		public ArrayList<PositionedStack> input;
		public PositionedStack output;

		@Override
		public ArrayList<PositionedStack> getIngredients()
		{
			return this.input;
		}

		@Override
		public PositionedStack getResult()
		{
			return this.output;
		}

		public CachedRocketRecipe(ArrayList<PositionedStack> pstack1, PositionedStack pstack2)
		{
			super();
			this.input = pstack1;
			this.output = pstack2;
		}

		public CachedRocketRecipe(Map.Entry<ArrayList<PositionedStack>, PositionedStack> recipe)
		{
			this(recipe.getKey(), recipe.getValue());
		}
	}

	@Override
	public String getRecipeName()
	{
		return "NASA Workbench";
	}

	@Override
	public String getGuiTexture()
	{
		return "/mods/galacticraftmars/textures/gui/schematic_rocket_T2.png";
	}
}