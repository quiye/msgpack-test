import org.msgpack.core.MessagePack

object Tester {
  def main(args: Array[String]) = {
    val packer = MessagePack.newDefaultBufferPacker()
    packer.packInt(777)
    packer.packInt(888)
    packer.packString("nine nine nine")
    packer.close()
    val unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray())
    println(unpacker.unpackInt)
    println(unpacker.unpackInt)
    println(unpacker.unpackString)
    unpacker.close()
  }
}
