package org.poseidonplugins.kotlin

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import org.bukkit.plugin.java.PluginClassLoader
import java.net.URL

class PoseidonKotlin : JavaPlugin() {

    override fun onLoad() {
        val loaders = getField(this.pluginLoader, "loaders") as HashMap<String, PluginClassLoader>
        val urls = arrayOf<URL>(this.file.toURI().toURL())
        val loader = PluginClassLoader(
            this.pluginLoader as JavaPluginLoader, urls, this.pluginLoader::class.java.classLoader
        )

        loaders["_PoseidonKotlin"] = loader
        server.logger.info("${description.name} ${description.version} has been loaded.")
    }

    override fun onEnable() {}

    override fun onDisable() {}

    private fun getField(obj: Any, name: String): Any {
        val field = obj.javaClass.getDeclaredField(name)
        field.isAccessible = true
        val objField = field.get(obj)
        field.isAccessible = false
        return objField
    }
}