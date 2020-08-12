package de.dakir.bungeesupportchat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Data {

    public static ArrayList<UUID> supports = new ArrayList<>();
    public static HashMap<UUID, UUID> inSupport = new HashMap<>();

    public static boolean enableMySQL;
    public static boolean enableQueueNotification;
    public static int queueNotificationInterval;

}
