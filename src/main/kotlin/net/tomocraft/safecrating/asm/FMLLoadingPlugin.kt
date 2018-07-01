package net.tomocraft.safecrating.asm

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import net.tomocraft.safecrating.SafeCrafting
import java.io.File

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.TransformerExclusions("net.tomocraft.asm")
class FMLLoadingPlugin : IFMLLoadingPlugin {

    companion object {
        @JvmStatic
        var location: File? = null
    }

    override fun getASMTransformerClass(): Array<String> {
        return arrayOf(GuiContainerTransformer::class.java.name)
    }

    override fun getModContainerClass(): String {
        return SafeCrafting::class.java.name
    }

    override fun getSetupClass(): String? {
        return null
    }

    override fun injectData(data: MutableMap<String, Any>) {
        if (data.containsKey("coremodLocation")) {
            location = data["coremodLocation"] as File
        }
    }

    override fun getAccessTransformerClass(): String? {
        return null
    }
}