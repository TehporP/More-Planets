/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class FronosCandyExtractorRecipes
{
	private static final FronosCandyExtractorRecipes extractingBase = new FronosCandyExtractorRecipes();

	private Map extractingList = new HashMap();
	private Map experienceList = new HashMap();
	private HashMap<List<Integer>, ItemStack> metaExtractingList = new HashMap<List<Integer>, ItemStack>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

	public static final FronosCandyExtractorRecipes extracting()
	{
		return extractingBase;
	}

	private FronosCandyExtractorRecipes()
	{
	}

	public void addExtracting(int par1, ItemStack par2ItemStack, float par3)
	{
		this.extractingList.put(Integer.valueOf(par1), par2ItemStack);
		this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
	}

	public Map getExtractingList()
	{
		return this.extractingList;
	}

	public void addExtracting(int itemID, int metadata, ItemStack itemstack, float experience)
	{
		this.metaExtractingList.put(Arrays.asList(itemID, metadata), itemstack);
		this.metaExperience.put(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()), experience);
	}

	public ItemStack getExtractingResult(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		ItemStack ret = this.metaExtractingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
		if (ret != null)
		{
			return ret;
		}
		return (ItemStack)this.extractingList.get(Integer.valueOf(item.itemID));
	}

	public float getExperience(ItemStack item)
	{
		if (item == null || item.getItem() == null)
		{
			return 0;
		}

		float ret = item.getItem().getSmeltingExperience(item);

		if (ret < 0 && this.metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			ret = this.metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		if (ret < 0 && this.experienceList.containsKey(item.itemID))
		{
			ret = ((Float)this.experienceList.get(item.itemID)).floatValue();
		}
		return ret < 0 ? 0 : ret;
	}

	public Map<List<Integer>, ItemStack> getMetaExtractingList()
	{
		return this.metaExtractingList;
	}
}