package me.yourname.soulcontrol;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.*;
import net.kyori.adventure.text.Component;
import org.bukkit.*;

import java.util.UUID;

public class SoulProtocol {

    public static void init(JavaPlugin plugin) {
        ProtocolLibrary.getProtocolManager().addPacketListener(
            new PacketAdapter(plugin, PacketType.Play.Client.CHAT) {

                @Override
                public void onPacketReceiving(PacketEvent e) {
                    UUID ctrl = e.getPlayer().getUniqueId();
                    String msg = e.getPacket().getStrings().read(0);

                    ControlSession.all().forEach((victim, controller) -> {
                        if (controller.equals(ctrl)) {
                            Player v = Bukkit.getPlayer(victim);
                            if (v == null) return;

                            e.setCancelled(true);
                            Bukkit.broadcast(Component.text(
                                "<" + v.getName() + "> " + msg
                            ));
                        }
                    });
                }
            }
        );
    }
}
