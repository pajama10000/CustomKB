package com.booked.knockback

import com.booked.knockback.commands.Reload
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class Knockback : JavaPlugin(), Listener {

    companion object {
        lateinit var instance: Knockback
            private set
    }

    override fun onEnable() {
        instance = this
        server.pluginManager.registerEvents(this, this)
        getCommand("knockback")?.setExecutor(Reload())
        saveDefaultConfig()
        server.consoleSender.sendMessage("§4Knockback plugin enabled! \uD83D\uDE03\n" +
                "\n" +
                "§dCheck the config.yml to change the knockback and for information on how to contact me!\n" +
                "§bThis plugin was made by booked61 and can be used with his permission. See config.yml for contact information!")
    }

    override fun onDisable() {
    }

    @EventHandler
    fun onPlayerHit(e: EntityDamageByEntityEvent) {
        val damagedEntity = e.entity
        if (damagedEntity is Player) {
            e.isCancelled = true
            BukkitRunnable {
                damagedEntity.velocity = e.damager.location.direction.multiply(getConfig().getDouble("knockback-of-players"))
            }.runTaskLater(this, (delayOfKnockbackPlayers() * 20L))
        } else if (damagedEntity != null) {
            e.isCancelled = true
            BukkitRunnable {
                damagedEntity.velocity = e.damager.location.direction.multiply(getConfig().getDouble("knockback-of-mobs"))
            }.runTaskLater(this, (delayOfKnockbackMobs() * 20L))
        }
    }

    private fun delayOfKnockbackPlayers(): Double {
        return instance.config.getDouble("delay-of-kb-players")
    }

    private fun delayOfKnockbackMobs(): Double {
        return instance.config.getDouble("delay-of-kb-mobs")
    }
}
