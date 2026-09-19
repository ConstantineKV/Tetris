package com.example.tetris.models
import android.graphics.Color
import android.graphics.Point
import androidx.annotation.NonNull
import com.example.tetris.constants.FieldConstants
import kotlin.random.Random
import kotlin.uuid.Uuid.Companion.random

class Block (
    private var shapeIndex: Int,
    private var color: BlockColor
) {
    var frameNumber: Int = 0
    var position: Point = Point(FieldConstants.COLUMN_COUNT.value / 2, 0)

    public enum class BlockColor (val rgbValue: Int, val byteValue: Byte) {
        PINK(Color.rgb(255, 105, 180), 2),
        GREEN(Color.rgb(0, 128, 0), 3),
        ORANGE(Color.rgb(255, 140, 0), 4),
        YELLOW(Color.rgb(255, 255, 0), 5),
        CYAN(Color.rgb(0, 255, 255), 6);
    }

    public final fun setState(frame: Int, position: Point) {
        this.frameNumber = frame
        this.position = position
    }

    @NonNull
    public fun getShape(frameNumber: Int): Array<ByteArray> {
        return Shape.values()[shapeIndex].getFrame(frameNumber).as2dByteArray()
    }

    public final fun getFrameCount() : Int {
        return Shape.values()[shapeIndex].frameCount
    }

    public fun getColor() : Int {
        return color.rgbValue
    }

    public fun getStaticValue() : Byte {
        return color.byteValue
    }

    companion object {
        fun createBlock(): Block {
            var shapeIndex: Int = Random.nextInt(Shape.values().size)
            var blockColor: BlockColor = BlockColor.values()[Random.nextInt(BlockColor.values().size)]
            var block: Block = Block(shapeIndex, blockColor)
            block.position.x = block.position.x - Shape.values()[shapeIndex].startPosition
            return block
        }

        fun getColor(value: Byte) : Int {
            for (color in BlockColor.values()) {
                if (value == color.byteValue) {
                    return color.rgbValue;
                }
            }
            return -1;
        }
    }

}