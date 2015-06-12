/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.worldgen.dungeon;

import java.util.ArrayList;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreDungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreDungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreMapGenDungeon;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityAncientChest;

public class NibiruRoomEmpty extends GCCoreDungeonRoom
{
	int sizeX;
	int sizeY;
	int sizeZ;

	private final ArrayList<ChunkCoordinates> chests = new ArrayList<ChunkCoordinates>();

	public NibiruRoomEmpty(GCCoreMapGenDungeon dungeon, int posX, int posY, int posZ, ForgeDirection entranceDir)
	{
		super(dungeon, posX, posY, posZ, entranceDir);
		if (this.worldObj != null)
		{
			final Random rand = new Random(this.worldObj.getSeed() * posX * posY * 57 * posZ);
			this.sizeX = rand.nextInt(4) + 5;
			this.sizeY = rand.nextInt(2) + 4;
			this.sizeZ = rand.nextInt(4) + 5;
		}
	}

	@Override
	public void generate(short[] chunk, byte[] meta, int cx, int cz)
	{
		for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
		{
			for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
			{
				for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
				{
					if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, this.dungeonInstance.DUNGEON_WALL_ID, this.dungeonInstance.DUNGEON_WALL_META);
					}
					else
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, 0, 0);
					}
				}
			}
		}
		final int hx = (this.posX + this.posX + this.sizeX) / 2;
		final int hz = (this.posZ + this.posZ + this.sizeZ) / 2;
		if (this.placeBlock(chunk, meta, hx, this.posY, hz, cx, cz, NibiruBlocks.nibiruAncientChest.blockID, 0))
		{
			this.chests.add(new ChunkCoordinates(hx, this.posY, hz));
		}
	}

	@Override
	public GCCoreDungeonBoundingBox getBoundingBox()
	{
		return new GCCoreDungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
	}

	@Override
	protected void handleTileEntities(Random rand)
	{
		for (final ChunkCoordinates chestCoords : this.chests)
		{
			final NibiruTileEntityAncientChest chest = (NibiruTileEntityAncientChest) this.worldObj.getBlockTileEntity(chestCoords.posX, chestCoords.posY, chestCoords.posZ);

			if (chest != null)
			{
				ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
				WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), chest, info.getCount(rand));
			}
		}
	}

	@Override
	protected GCCoreDungeonRoom makeRoom(GCCoreMapGenDungeon dungeon, int x, int y, int z, ForgeDirection dir)
	{
		return new NibiruRoomEmpty(dungeon, x, y, z, dir);
	}
}