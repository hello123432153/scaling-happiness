package me.yourname.soulcontrol;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (ControlSession.isVictim(p.getUniqueId())) {
            e.setCancelled(true);
            return;
        }

        ControlSession.all().forEach((victim, controller) -> {
            if (controller.equals(p.getUniqueId())) {
                Player v = Bukkit.getPlayer(victim);
                if (v != null) v.teleport(e.getTo());
            }
        });
    }
}
