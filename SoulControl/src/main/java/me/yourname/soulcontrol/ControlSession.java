package me.yourname.soulcontrol;

import java.util.*;

public class ControlSession {

    private static final Map<UUID, UUID> sessions = new HashMap<>();

    public static void start(UUID victim, UUID controller) {
        sessions.put(victim, controller);
    }

    public static void stop(UUID victim) {
        sessions.remove(victim);
        NPCStorage.remove(victim);
    }

    public static UUID getController(UUID victim) {
        return sessions.get(victim);
    }

    public static boolean isVictim(UUID id) {
        return sessions.containsKey(id);
    }

    public static Map<UUID, UUID> all() {
        return sessions;
    }

    public static void clearAll() {
        sessions.clear();
    }
}
