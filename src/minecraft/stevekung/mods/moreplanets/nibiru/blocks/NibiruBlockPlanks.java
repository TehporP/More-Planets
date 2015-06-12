/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;

public class NibiruBlockPlanks extends Block
{
	private static final String[] woodTypes = new String[] {"whiteWoodPlanks", "orangeWoodPlanks"};
	private Icon[] textures;

	public NibiruBlockPlanks(int blockID)
	{
		super(blockID, Material.wood);
		Block.setBurnProperties(this.blockID, 5, 20);
		this.setHardness(2.0F);
		this.setStepSound(Block.soundWoodFootstep);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[woodTypes.length];

		for (int i = 0; i < woodTypes.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("nibiru:"+woodTypes[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.textures.length)
		{
			meta = 0;
		}

		return this.textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < woodTypes.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}