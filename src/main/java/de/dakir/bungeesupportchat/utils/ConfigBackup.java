package de.dakir.bungeesupportchat.utils;

public class ConfigBackup {

    public static String configversion;

    public static String prefix = "&8[&9Support&8] &b";
    public static boolean enableMySQL = false;
    public static String supporterColor = "&c";
    public static String userColor = "&f";
    public static String chatColor = "&b";
    public static String chatFormat = "%prefix%%nameColor%%player% &8»%msg%";
    public static boolean enableQueueNotification = true;
    public static int queueNotificationInterval = 60;
    public static boolean enableTabComplete = true;

    public static String reload = "Config has been reloaded!";
    public static String noPermission = "You have no permission for that!";
    public static String noNumber = "A number may not contain any letters!";
    public static String commandNotExists = "This command does not exist!";
    public static String playerNotFound = "Player not found!";
    public static String playerIsSupporter = "This player is a team member!";
    public static String notInSupportChat = "You are not in a support chat!";
    public static String inSupportChat = "You are already in a support chat!";
    public static String playerIsInSupportChat = "&f%player% &bis already in a support chat!";
    public static String noSupportNeeded = "No one needs support right now!";
    public static String noSupporterOnline = "Currently no team member is online! Please try again later.";
    public static String noSupportRequest = "You can not submit a support request!";
    public static String joinSupportQueue = "You are in the queue now.";
    public static String leaveSupportQueue = "You left the queue.";
    public static String needSupport = "&f%player% &bneeds support!";
    public static String needNoSupport = "&f%player% &bdoes not need support anymore!";
    public static String onePlayerNeedSupport = "There is currently &f%number% &bplayer in the queue:";
    public static String morePlayerNeedSupport = "There are currently &f%number% &bplayers in the queue:";
    public static String playerInQueue = "&8» &f%player%";
    public static String closeSupportChat = "The support chat has been closed.";
    public static String openSupportChat = "You are now in support chat.";
    public static String openSupportChat_head = "Chat Head: &c%player%";
    public static String openSupportChat_user = "Player: &f%player%";
    public static String openSupportChat_space = "";
    public static String openSupportChat_hellomessage = "&c%player% &8» &bHello, how can I help you?";
    public static String mysqlNotEnabled = "The MySQL database is not activated in the config!";
    public static String dataSupportsReset = "Supports have been reset.";
    public static String dataPlayerStats = "The player &f%player% &bhas already made &f%number% &bsupport(s).";
    public static String dataPlayerDeleted = "The player &f%player% &bwas successfully deleted from the database.";
    public static String dataPlayerSupportsAdded = "Added &f%number% &bsupport(s) to player &f%player%&b.";
    public static String dataPlayerSupportsRemoved = "Removed &f%number% &bsupport(s) of player &f%player%&b.";
    public static String dataPlayerSupportsSet = "The supports(s) of &f%player% &bwere set to &f%number%&b.";
    public static String dataPlayerList_header = "&8&m]&7&m--------&8&m[&r &b♦ &8&m]&7&m--------&8&m[";
    public static String dataPlayerList_entry = "&9%id%. &f%player% &8- &b%number%";
    public static String dataPlayerList_footer = "&8&m]&7&m--------&8&m[&r &b♦ &8&m]&7&m--------&8&m[";

    public static String header = "&8&m]&7&m------------------------&8&m[&r &b♦ &8&m]&7&m------------------------&8&m[";
    public static String sc_help = "&9/sc help &8- &fShows this menu";
    public static String sc = "&9/sc &8- &fEnter/Leave the queue";
    public static String sc_list = "&9/sc list &8- &fList of queue";
    public static String sc_close = "&9/sc close &8- &fClose the support chat";
    public static String sc_open = "&9/sc open &8- &fOpens support chat with last support request";
    public static String sc_open_player = "&9/sc open <player> &8- &fOpens support chat with specific player";
    public static String sc_data_list = "&9/sc data list &8- &fShows the support list";
    public static String sc_data_stats = "&9/sc data stats <player> &8- &fShows the supports of a player";
    public static String sc_data_delete = "&9/sc data delete <player> &8- &fDeletes a player from the database";
    public static String sc_data_add = "&9/sc data add <player> <number> &8- &fAdds supports to a player";
    public static String sc_data_remove = "&9/sc data remove <player> <number> &8- &fRemoves supports of a player";
    public static String sc_data_set = "&9/sc data set <player> <number> &8- &fSets the supports of a player";
    public static String sc_data_reset = "&9/sc data reset &8- &fResets the supports";
    public static String sc_reload = "&9/sc reload &8- &fReloads the config";
    public static String footer = "&8&m]&7&m------------------------&8&m[&r &b♦ &8&m]&7&m------------------------&8&m[";

}
