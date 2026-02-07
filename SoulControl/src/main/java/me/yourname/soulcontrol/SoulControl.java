package me.yourname.soulcontrol;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulControl extends JavaPlugin {

    public static SoulControl instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new SoulListener(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);

        SoulProtocol.init(this);
    }

    @Override
    public void onDisable() {
        ControlSession.clearAll();
        NPCStorage.clear();
    }
}
