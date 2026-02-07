package me.yourname.soulcontrol;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.*;

public class NPCStorage {

    private static final Map<UUID, ArmorStand> npcs = new HashMap<>();

    public static void spawn(Player p) {
        ArmorStand a = p.getWorld().spawn(p.getLocation(), ArmorStand.class);
        a.setCustomName(p.getName());
        a.setCustomNameVisible(true);
        a.setGravity(false);
        a.setInvulnerable(true);
        npcs.put(p.getUniqueId(), a);
    }

    public static void remove(UUID id) {
        ArmorStand a = npcs.remove(id);
        if (a != null) a.remove();
    }

    public static void clear() {
        npcs.values().forEach(Entity::remove);
        npcs.clear();
    }
}
