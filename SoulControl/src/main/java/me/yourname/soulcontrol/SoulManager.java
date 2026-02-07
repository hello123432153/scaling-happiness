package me.yourname.soulcontrol;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class SoulManager {

    public static NamespacedKey KEY = new NamespacedKey(SoulControl.instance, "soul");

    public static ItemStack create(Player p) {
        ItemStack i = new ItemStack(Material.RED_DYE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.RED + "Soul of " + p.getName());
        m.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, p.getUniqueId().toString());
        i.setItemMeta(m);
        return i;
    }

    public static boolean isSoul(ItemStack i) {
        return i != null && i.hasItemMeta() &&
               i.getItemMeta().getPersistentDataContainer().has(KEY, PersistentDataType.STRING);
    }

    public static UUID owner(ItemStack i) {
        return UUID.fromString(
            i.getItemMeta().getPersistentDataContainer().get(KEY, PersistentDataType.STRING)
        );
    }
}
