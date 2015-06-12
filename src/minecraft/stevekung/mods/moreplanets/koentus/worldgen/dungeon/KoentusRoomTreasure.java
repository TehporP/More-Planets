/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.worldgen.dungeon;

import java.util.HashSet;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreDungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreDungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreMapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.tileentities.KoentusTileEntityTreasureChest;

public class KoentusRoomTreasure extends GCCoreDungeonRoom
{
	int sizeX;
	int sizeY;
	int sizeZ;

	private final HashSet<ChunkCoordinates> chests = new HashSet<ChunkCoordinates>();

	public KoentusRoomTreasure(GCCoreMapGenDungeon dungeon, int posX, int posY, int posZ, ForgeDirection entranceDir)
	{
		super(dungeon, posX, posY, posZ, entranceDir);
		if (this.worldObj != null)
		{
			final Random rand = new Random(this.worldObj.getSeed() * posX * posY * 57 * posZ);
			this.sizeX = rand.nextInt(6) + 7;
			this.sizeY = rand.nextInt(2) + 5;
			this.sizeZ = rand.nextInt(6) + 7;
		}
	}

	@Override
	public void generate(short[] chunk, byte[] meta, int cx, int cz)
	{
		for (int i = this.posX - 1; i <= this.posX + this.sizeX; i++)
		{
			for (int k = this.posZ - 1; k <= this.posZ + this.sizeZ; k++)
			{
				for (int j = this.posY - 1; j <= this.posY + this.sizeY; j++)
				{
					if (i == this.posX - 1 || i == this.posX + this.sizeX || j == this.posY - 1 || j == this.posY + this.sizeY || k == this.posZ - 1 || k == this.posZ + this.sizeZ)
					{
						this.placeBlock(chunk, meta, i, j, k, cx, cz, this.dungeonInstance.DUNGEON_WALL_ID, this.dungeonInstance.DUNGEON_WALL_META);
					}
					else
					{
						if ((i == this.posX || i == this.posX + this.sizeX - 1) && (k == this.posZ || k == this.posZ + this.sizeZ - 1))
						{
							this.placeBlock(chunk, meta, i, j, k, cx, cz, Block.glowStone.blockID, 0);
						}
						else
						{
							this.placeBlock(chunk, meta, i, j, k, cx, cz, 0, 0);
						}
					}
				}
			}
		}
		final int hx = (this.posX + this.posX + this.sizeX) / 2;
		final int hz = (this.posZ + this.posZ + this.sizeZ) / 2;

		if (this.placeBlock(chunk, meta, hx, this.posY, hz, cx, cz, KoentusBlocks.koentusTreasureChest.blockID, 0))
		{
			this.chests.add(new ChunkCoordinates(hx, this.posY, hz));
		}
	}

	public ItemStack getGuaranteedLoot(Random rand)
	{
		return null;
		/*switch (rand.nextInt(20))
        {
        case 0:
            return new ItemStack(KoentusBlocks.koentusEgg, 1, 0);
        }
        return null;*///TODO
	}

	@Override
	public GCCoreDungeonBoundingBox getBoundingBox()
	{
		return new GCCoreDungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
	}

	@Override
	protected GCCoreDungeonRoom makeRoom(GCCoreMapGenDungeon dungeon, int x, int y, int z, ForgeDirection dir)
	{
		return new KoentusRoomTreasure(dungeon, x, y, z, dir);
	}

	@Override
	protected void handleTileEntities(Random rand)
	{
		if (!this.chests.isEmpty())
		{
			HashSet<ChunkCoordinates> removeList = new HashSet<ChunkCoordinates>();

			for (ChunkCoordinates coords : this.chests)
			{
				this.worldObj.setBlock(coords.posX, coords.posY, coords.posZ, KoentusBlocks.koentusTreasureChest.blockID, 0, 3);
				this.worldObj.setBlockTileEntity(coords.posX, coords.posY, coords.posZ, new KoentusTileEntityTreasureChest(6));
				removeList.add(coords);
			}

			this.chests.removeAll(removeList);
		}

		for (final ChunkCoordinates chestCoords : this.chests)
		{
			final TileEntity chest = this.worldObj.getBlockTileEntity(chestCoords.posX, chestCoords.posY, chestCoords.posZ);
			if (chest != null && chest instanceof KoentusTileEntityTreasureChest)
			{
				ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);

				WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), (KoentusTileEntityTreasureChest) chest, info.getCount(rand));

				((KoentusTileEntityTreasureChest) chest).setInventorySlotContents(rand.nextInt(((KoentusTileEntityTreasureChest) chest).getSizeInventory()), this.getGuaranteedLoot(rand));
			}
		}
	}
}