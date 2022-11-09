package icu.awkitsune.inkyrl.blocks

import icu.awkitsune.inkyrl.builders.GameTileRepository.EMPTY
import icu.awkitsune.inkyrl.builders.GameTileRepository.FLOOR
import icu.awkitsune.inkyrl.builders.GameTileRepository.WALL
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(content: Tile = FLOOR) : BaseBlock<Tile> (
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to content)
){

    val isFloor: Boolean
        get() = content == FLOOR

    val isWall: Boolean
        get() = content == WALL

}