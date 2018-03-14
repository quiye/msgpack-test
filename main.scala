import org.msgpack.core.MessagePack

object Tester {
  def main(args: Array[String]) = {
    val packer = MessagePack.newDefaultBufferPacker()
    packer.packInt(999)
    packer.close()
    val unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray())
    println(unpacker.unpackInt)
  }
}
