package net.tomocraft.safecrating

import com.google.common.eventbus.EventBus
import net.minecraftforge.fml.common.DummyModContainer
import net.minecraftforge.fml.common.LoadController
import net.minecraftforge.fml.common.ModMetadata

class SafeCrafting : DummyModContainer(ModMetadata()) {
    init {
        val meta = this.metadata
        meta.modId = "safecrafting"
        meta.name = "SafeCrafting"
        meta.version = "1.0"
        meta.authorList = arrayListOf("tomocrafter")
    }

    override fun registerBus(bus : EventBus, controller: LoadController) : Boolean {
        return true
    }
}