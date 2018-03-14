import org.msgpack.core.MessagePack
import org.msgpack.core.MessageUnpacker
import org.msgpack.core.MessageFormat.{UINT16,FIXSTR}
import scala.annotation.tailrec

object Tester {
  def getData(u:MessageUnpacker): Any = u.getNextFormat() match {
    case UINT16 => u.unpackInt()
    case FIXSTR => u.unpackString()
    case _ => "[warn] It would fail on the following inputs: BOOLEAN, EXT32, FIXEXT16, FIXEXT4, FIXMAP, INT16, INT64, NEVER_USED, NIL, STR16"
  }
  def main(args: Array[String]) = {
    // シリアライズ
    val packer = MessagePack.newDefaultBufferPacker()
    packer.packInt(777)
    packer.packString("eight eight eight")
    packer.packInt(999)
    packer.packString("ten ten ten")
    packer.close()

    // デシリアライズ
    val unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray())

    @tailrec
    def unroll(u:MessageUnpacker): Unit = u.hasNext() match{
      case false => return
      case true => {
        println(getData(u))
        unroll(u)
      }
    }
    unroll(unpacker)

    unpacker.close()
  }
}
