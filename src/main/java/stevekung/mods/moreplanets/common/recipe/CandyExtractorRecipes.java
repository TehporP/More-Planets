/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

import com.google.common.collect.Maps;

public class CandyExtractorRecipes
{
	private static CandyExtractorRecipes extractingBase = new CandyExtractorRecipes();
	private Map extractingList = Maps.newHashMap();
	private Map experienceList = Maps.newHashMap();

	public static CandyExtractorRecipes instance()
	{
		return extractingBase;
	}

	public void addExtractingRecipe(ItemStack input, ItemStack itemStack, float experience)
	{
		this.extractingList.put(input, itemStack);
		this.experienceList.put(itemStack, Float.valueOf(experience));
	}

	public ItemStack getExtractingResult(ItemStack itemStack)
	{
		Iterator iterator = this.extractingList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}
			entry = (Entry)iterator.next();
		}
		while (!this.compareItemStacks(itemStack, (ItemStack)entry.getKey()));
		return (ItemStack)entry.getValue();
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map getExtractingList()
	{
		return this.extractingList;
	}

	public float getExtractingExperience(ItemStack itemStack)
	{
		float ret = itemStack.getItem().getSmeltingExperience(itemStack);

		if (ret != -1)
		{
			return ret;
		}

		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return 0.0F;
			}
			entry = (Entry)iterator.next();
		}
		while (!this.compareItemStacks(itemStack, (ItemStack)entry.getKey()));
		return ((Float)entry.getValue()).floatValue();
	}
}