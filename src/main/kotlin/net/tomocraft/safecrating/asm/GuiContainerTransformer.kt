package net.tomocraft.safecrating.asm

import net.minecraft.launchwrapper.IClassTransformer
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.zip.ZipFile

class GuiContainerTransformer : IClassTransformer{
    companion object {
        private const val TARGET_CLASS_NAME = "GuiContainer"
    }

    override fun transform(name: String, transformedName: String, basicClass: ByteArray): ByteArray {
        if (transformedName != TARGET_CLASS_NAME && transformedName != "$TARGET_CLASS_NAME$1") {
            return basicClass
        } else {
            try {
                return this.replaceClass(transformedName, basicClass)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return basicClass
    }

    @Throws(IOException::class)
    private fun replaceClass(name: String, bytes: ByteArray): ByteArray {
        println("Patching: $name")
        val zip = ZipFile(FMLLoadingPlugin.location)

        zip.getEntry(name.replace(".", "/") + ".class")?.let { entry ->
            val bout = ByteArrayOutputStream()
            val zin = zip.getInputStream(entry)
            val buffer = ByteArray(1024)
            while (true) {
                val len = zin.read(buffer)
                if (len < 0) {
                    break
                }
                bout.write(buffer, 0, len)
            }
            zin.close()

            return bout.toByteArray()
        }

        return bytes
    }

}
