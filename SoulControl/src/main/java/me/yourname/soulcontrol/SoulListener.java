package me.yourname.soulcontrol;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class SoulListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().containsAtLeast(SoulManager.create(p), 1)) return;
        p.getInventory().setItem(8, SoulManager.create(p));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setKeepInventory(true);
        e.getDrops().removeIf(i -> !SoulManager.isSoul(i));
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        ItemStack i = e.getItem().getItemStack();
        if (!SoulManager.isSoul(i)) return;

        UUID owner = SoulManager.owner(i);
        if (!e.getPlayer().getUniqueId().equals(owner)) {
            ControlSession.start(owner, e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onDespawn(ItemDespawnEvent e) {
        if (!SoulManager.isSoul(e.getEntity().getItemStack())) return;
        Player p = Bukkit.getPlayer(SoulManager.owner(e.getEntity().getItemStack()));
        if (p != null) p.getInventory().setItem(8, SoulManager.create(p));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UUID id = e.getPlayer().getUniqueId();
        if (!ControlSession.isVictim(id)) return;

        NPCStorage.spawn(e.getPlayer());
    }
}
