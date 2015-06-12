/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.util;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;
import stevekung.mods.moreplanets.common.blocks.BlockLeavesMP;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.BlockFronisiumTNT;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.BlockUraniumBomb;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.BlockSiriusFire;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.stevecore.RegisterHelper;

public class StateMapperUtil
{
	public static void registerStateMapper()
	{
		RegisterHelper.registerBlockWithStateMapper(DionaBlocks.fronisium_tnt, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFronisiumTNT.EXPLODE}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.ancient_dark_fence_gate, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.orange_fence_gate, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.ancient_dark_leaves, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLeavesMP.CHECK_DECAY, BlockLeavesMP.DECAYABLE}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.orange_leaves, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLeavesMP.CHECK_DECAY, BlockLeavesMP.DECAYABLE}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.ancient_dark_door, new Builder().addPropertiesToIgnore(new IProperty[] {BlockDoor.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(NibiruBlocks.orange_door, new Builder().addPropertiesToIgnore(new IProperty[] {BlockDoor.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(KoentusBlocks.crystal_fence_gate, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(KoentusBlocks.crystal_leaves, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLeavesMP.CHECK_DECAY, BlockLeavesMP.DECAYABLE}).build());
		RegisterHelper.registerBlockWithStateMapper(KoentusBlocks.crystal_door, new Builder().addPropertiesToIgnore(new IProperty[] {BlockDoor.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.fronos_colorized_leaves, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLeavesMP.CHECK_DECAY, BlockLeavesMP.DECAYABLE}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.fronos_leaves, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLeavesMP.CHECK_DECAY, BlockLeavesMP.DECAYABLE}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.coconut_fence_gate, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.maple_fence_gate, new Builder().addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.fronos_coral, new Builder().addPropertiesToIgnore(new IProperty[] {BlockLiquid.LEVEL}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.coconut_door_block, new Builder().addPropertiesToIgnore(new IProperty[] {BlockDoor.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(FronosBlocks.maple_door_block, new Builder().addPropertiesToIgnore(new IProperty[] {BlockDoor.POWERED}).build());
		RegisterHelper.registerBlockWithStateMapper(KapteynBBlocks.uranium_bomb, new Builder().addPropertiesToIgnore(new IProperty[] {BlockUraniumBomb.EXPLODE}).build());
		RegisterHelper.registerBlockWithStateMapper(SiriusBBlocks.sirius_fire, new Builder().addPropertiesToIgnore(new IProperty[] {BlockSiriusFire.AGE}).build());
	}
}