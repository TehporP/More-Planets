/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.items.tools.KoentusToolsItems;
import stevekung.mods.moreplanets.planets.diona.items.tools.DionaToolsItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.tools.FronosToolsItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.tools.KapteynBToolsItems;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.tools.NibiruToolsItems;
import stevekung.mods.moreplanets.planets.polongnius.items.tools.PolongniusToolsItems;
import stevekung.mods.moreplanets.planets.siriusb.items.tools.SiriusBToolsItems;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameData;

public class TreeCapitatorIntegrationMP
{
	public static void init()
	{
		NBTTagCompound tpModCfg = new NBTTagCompound();
		NBTTagList treeList = new NBTTagList();
		NBTTagCompound tree = new NBTTagCompound();

		String nibiruLog = blockName(NibiruBlocks.nibiru_log);
		String ancientDarkLeaves = blockName(NibiruBlocks.ancient_dark_leaves);
		String orangeLeaves = blockName(NibiruBlocks.orange_leaves);

		String koentusLog = blockName(KoentusBlocks.crystal_log);
		String koentusLeaves = blockName(KoentusBlocks.crystal_leaves);

		String fronosLog = blockName(FronosBlocks.fronos_log);
		String fronosColorizedLeaves = blockName(FronosBlocks.fronos_colorized_leaves);
		String fronosLeaves = blockName(FronosBlocks.fronos_leaves);

		String quontoniumAxe = itemName(DionaToolsItems.quontonium_axe);
		String fronisiumAxe = itemName(DionaToolsItems.fronisium_axe);
		String polongniusMeteorAxe = itemName(PolongniusToolsItems.polongnius_meteoric_iron_axe);
		String palladiumAxe = itemName(PolongniusToolsItems.palladium_axe);
		String purpleCrystalAxe = itemName(PolongniusToolsItems.purple_crystal_axe);
		String redGemAxe = itemName(NibiruToolsItems.red_gem_axe);
		String noriumAxe = itemName(NibiruToolsItems.norium_axe);
		String koentusMeteorAxe = itemName(KoentusToolsItems.koentus_meteoric_iron_axe);
		String whiteCrystalAxe = itemName(KoentusToolsItems.white_crystal_axe);
		String blackDiamondAxe = itemName(FronosToolsItems.black_diamond_axe);
		String iridiumAxe = itemName(FronosToolsItems.iridium_axe);
		String candyAxe = itemName(FronosToolsItems.candy_axe);
		String frozenIronAxe = itemName(KapteynBToolsItems.frozen_iron_axe);
		String uraniumAxe = itemName(KapteynBToolsItems.uranium_axe);
		String sulfurAxe = itemName(SiriusBToolsItems.sulfur_axe);

		tpModCfg.setString("modID", "MorePlanet");
		tpModCfg.setString("axeIDList", quontoniumAxe + "; " + fronisiumAxe + "; " + polongniusMeteorAxe + "; " + palladiumAxe + "; " + purpleCrystalAxe + "; " + redGemAxe + "; " + noriumAxe + "; " + koentusMeteorAxe + "; " + whiteCrystalAxe + "; " + blackDiamondAxe + "; " +
				iridiumAxe + "; " + candyAxe + "; " + frozenIronAxe + "; " + uraniumAxe + "; " + sulfurAxe);

		//Ancient Dark Tree
		tree.setString("treeName", "ancientdark");
		tree.setString("logs", String.format("%s, 0; %s, 4; %s, 8", nibiruLog, nibiruLog, nibiruLog));
		tree.setString("leaves", String.format("%s, 0; %s, 1; %s, 2; %s, 3; %s, 8; %s, 9; %s, 10; %s, 11", ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves, ancientDarkLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Orange Tree
		tree.setString("treeName", "orange");
		tree.setString("logs", String.format("%s, 1; %s, 5; %s, 9", nibiruLog, nibiruLog, nibiruLog));
		tree.setString("leaves", String.format("%s, 0; %s, 1; %s, 2; %s, 3; %s, 8; %s, 9; %s, 10; %s, 11", orangeLeaves, orangeLeaves, orangeLeaves, orangeLeaves, orangeLeaves, orangeLeaves, orangeLeaves, orangeLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Coconut Tree
		tree.setString("treeName", "coconut");
		tree.setString("logs", String.format("%s, 0; %s, 4; %s, 8", fronosLog, fronosLog, fronosLog));
		tree.setString("leaves", String.format("%s, 0; %s, 8", fronosColorizedLeaves, fronosColorizedLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Red Maple Tree
		tree.setString("treeName", "redmaple");
		tree.setString("logs", String.format("%s, 1; %s, 5; %s, 9", fronosLog, fronosLog, fronosLog));
		tree.setString("leaves", String.format("%s, 0; %s, 8", fronosLeaves, fronosLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Yellow Maple Tree
		tree.setString("treeName", "yellowmaple");
		tree.setString("logs", String.format("%s, 1; %s, 5; %s, 9", fronosLog, fronosLog, fronosLog));
		tree.setString("leaves", String.format("%s, 1; %s, 9", fronosLeaves, fronosLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Purple Maple Tree
		tree.setString("treeName", "purplemaple");
		tree.setString("logs", String.format("%s, 1; %s, 5; %s, 9", fronosLog, fronosLog, fronosLog));
		tree.setString("leaves", String.format("%s, 2; %s, 10", fronosLeaves, fronosLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		//Crystal Tree
		tree.setString("treeName", "crystal");
		tree.setString("logs", String.format("%s, 0; %s, 4; %s, 8", koentusLog, koentusLog, koentusLog));
		tree.setString("leaves", String.format("%s, 0; %s, 8", koentusLeaves, koentusLeaves));
		tree.setBoolean("requireLeafDecayCheck", false);
		treeList.appendTag(tree);

		FMLInterModComms.sendMessage("TreeCapitator", "ThirdPartyModConfig", tpModCfg);
		FMLInterModComms.sendMessage("Treecapitator", "ThirdPartyModConfig", tpModCfg);
	}

	private static String blockName(Block block)
	{
		return GameData.getBlockRegistry().getNameForObject(block);
	}

	private static String itemName(Item item)
	{
		return GameData.getItemRegistry().getNameForObject(item);
	}
}