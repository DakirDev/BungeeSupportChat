package de.dakir.bungeesupportchat.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import de.dakir.bungeesupportchat.PluginManager;
import de.dakir.bungeesupportchat.utils.ConfigManager;
import de.dakir.bungeesupportchat.utils.Data;
import de.dakir.bungeesupportchat.utils.HexxAPI;
import de.dakir.bungeesupportchat.utils.MySQLData;
import de.dakir.bungeesupportchat.utils.Strings;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class Support extends Command implements TabExecutor {

    public Support(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (args.length == 0) {
                if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")) {
                    p.sendMessage(new TextComponent(Strings.prefix + Strings.noSupportRequest));
                } else {
                    if (HexxAPI.isSupporterOnline()) {
                        if (HexxAPI.isInSupportChat(p)) {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.inSupportChat));
                        } else {
                            if (!(Data.supports.contains(p.getUniqueId()))) {
                                Data.supports.add(p.getUniqueId());
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.joinSupportQueue));
                                HexxAPI.sendSupportMessage(Strings.needSupport.replace("%player%", p.getName()));
                            } else {
                                Data.supports.remove(p.getUniqueId());
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.leaveSupportQueue));
                                HexxAPI.sendSupportMessage(Strings.needNoSupport.replace("%player%", p.getName()));
                            }
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noSupporterOnline));
                    }
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.list")) {
                        if (Data.supports.size() == 0) {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noSupportNeeded));
                        } else if (Data.supports.size() == 1) {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.onePlayerNeedSupport.replace("%number%", Data.supports.size() + "")));
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.playerInQueue.replace("%player%", ProxyServer.getInstance().getPlayer(Data.supports.get(0)).getName())));
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.morePlayerNeedSupport.replace("%number%", Data.supports.size() + "")));
                            for (int i = 0; i < Data.supports.size(); i++) {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.playerInQueue.replace("%player%", ProxyServer.getInstance().getPlayer(Data.supports.get(i)).getName())));
                            }
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("open")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")) {
                        if (Data.supports.size() == 0) {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noSupportNeeded));
                        } else {
                            ProxiedPlayer o = ProxyServer.getInstance().getPlayer(Data.supports.get(0));
                            HexxAPI.openSupportChat(p, o);
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("close")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.close")) {
                        if (HexxAPI.isInSupportChat(p)) {
                            HexxAPI.closeSupportChat(p);
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.notInSupportChat));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.help")) {
                        p.sendMessage(new TextComponent(Strings.header));
                        p.sendMessage(new TextComponent(Strings.sc_help));
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use")) {
                            p.sendMessage(new TextComponent(Strings.sc));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.list")) {
                            p.sendMessage(new TextComponent(Strings.sc_list));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.close")) {
                            p.sendMessage(new TextComponent(Strings.sc_close));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")) {
                            p.sendMessage(new TextComponent(Strings.sc_open));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")) {
                            p.sendMessage(new TextComponent(Strings.sc_open_player));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.list")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_list));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.stats")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_stats));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.delete")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_delete));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.add")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_add));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.remove")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_remove));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.set")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_set));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.reset")) {
                            p.sendMessage(new TextComponent(Strings.sc_data_reset));
                        }
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.reload")) {
                            p.sendMessage(new TextComponent(Strings.sc_reload));
                        }
                        p.sendMessage(new TextComponent(Strings.footer));
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.reload")) {
                        ConfigManager.load();
                        PluginManager.reloadScheudler();
                        sender.sendMessage(new TextComponent(Strings.prefix + Strings.reload));
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else {
                    p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("open")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.use") || p.hasPermission("supportchat.open")) {
                        try {
                            ProxiedPlayer o = ProxyServer.getInstance().getPlayer(args[1]);
                            if (o.hasPermission("supportchat.*") || o.hasPermission("supportchat.use") || o.hasPermission("supportchat.open")) {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.playerIsSupporter));
                            } else {
                                HexxAPI.openSupportChat(p, o);
                            }
                        } catch (NullPointerException e) {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("data") && args[1].equalsIgnoreCase("list")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.list")) {
                        if (Data.enableMySQL) {
                            HashMap<Integer, String> list = MySQLData.getList();
                            p.sendMessage(new TextComponent(Strings.dataPlayerList_header));
                            for (int i = 0; i < list.size(); i++) {
                                int id = i + 1;
                                p.sendMessage(new TextComponent(Strings.dataPlayerList_entry.replace("%id%", String.valueOf(id)).replace("%player%", list.get(id)).replace("%number%", String.valueOf(MySQLData.getSupportsByName(list.get(id))))));
                            }
                            p.sendMessage(new TextComponent(Strings.dataPlayerList_footer));
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else if (args[0].equalsIgnoreCase("data") && args[1].equalsIgnoreCase("reset")) {
                    if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.reset")) {
                        if (Data.enableMySQL) {
                            MySQLData.resetSupports();
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.dataSupportsReset));
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                    }
                } else {
                    p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("data")) {
                    if (args[1].equalsIgnoreCase("stats")) {
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.stats")) {
                            if (Data.enableMySQL) {
                                String player = args[2];
                                if (MySQLData.isNameExists(player)) {
                                    int number = MySQLData.getSupportsByName(player);
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.dataPlayerStats.replace("%player%", player).replace("%number%", String.valueOf(number))));
                                } else {
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                                }
                            } else {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                            }
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                        }
                    } else if (args[1].equalsIgnoreCase("delete")) {
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.delete")) {
                            if (Data.enableMySQL) {
                                String player = args[2];
                                if (MySQLData.isNameExists(player)) {
                                    MySQLData.deletePlayer(player);
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.dataPlayerDeleted.replace("%player%", player)));
                                } else {
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                                }
                            } else {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                            }
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                    }
                } else {
                    p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("data")) {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.add")) {
                            if (Data.enableMySQL) {
                                String player = args[2];
                                if (MySQLData.isNameExists(player)) {
                                    try {
                                        int number = Integer.parseInt(args[3]);
                                        MySQLData.addSupportsByName(player, number);
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.dataPlayerSupportsAdded.replace("%player%", player).replace("%number%", String.valueOf(number))));
                                    } catch (NumberFormatException e) {
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noNumber));
                                    }
                                } else {
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                                }
                            } else {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                            }
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                        }
                    } else if (args[1].equalsIgnoreCase("remove")) {
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.remove")) {
                            if (Data.enableMySQL) {
                                String player = args[2];
                                if (MySQLData.isNameExists(player)) {
                                    try {
                                        int number = Integer.parseInt(args[3]);
                                        MySQLData.removeSupportsByName(player, number);
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.dataPlayerSupportsRemoved.replace("%player%", player).replace("%number%", String.valueOf(number))));
                                    } catch (NumberFormatException e) {
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noNumber));
                                    }
                                } else {
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                                }
                            } else {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                            }
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                        }
                    } else if (args[1].equalsIgnoreCase("set")) {
                        if (p.hasPermission("supportchat.*") || p.hasPermission("supportchat.data.*") || p.hasPermission("supportchat.data.set")) {
                            if (Data.enableMySQL) {
                                String player = args[2];
                                if (MySQLData.isNameExists(player)) {
                                    try {
                                        int number = Integer.parseInt(args[3]);
                                        MySQLData.setSupportsByName(player, number);
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.dataPlayerSupportsSet.replace("%player%", player).replace("%number%", String.valueOf(number))));
                                    } catch (NumberFormatException e) {
                                        p.sendMessage(new TextComponent(Strings.prefix + Strings.noNumber));
                                    }
                                } else {
                                    p.sendMessage(new TextComponent(Strings.prefix + Strings.playerNotFound));
                                }
                            } else {
                                p.sendMessage(new TextComponent(Strings.prefix + Strings.mysqlNotEnabled));
                            }
                        } else {
                            p.sendMessage(new TextComponent(Strings.prefix + Strings.noPermission));
                        }
                    } else {
                        p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                    }
                } else {
                    p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
                }
            } else {
                p.sendMessage(new TextComponent(Strings.prefix + Strings.commandNotExists));
            }
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    ConfigManager.load();
                    PluginManager.reloadScheudler();
                    sender.sendMessage(new TextComponent(Strings.cprefix + Strings.reload));
                }
            } else {
                sender.sendMessage(new TextComponent(Strings.onlyPlayer));
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> subcommands = new ArrayList<>();
        List<String> matches = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.use") || sender.hasPermission("supportchat.close")) {
                subcommands.add("close");
            }

            if (sender.hasPermission("supportchat.*")
                    || sender.hasPermission("supportchat.data.*")
                    || sender.hasPermission("supportchat.data.list")
                    || sender.hasPermission("supportchat.data.stats")
                    || sender.hasPermission("supportchat.data.delete")
                    || sender.hasPermission("supportchat.data.add")
                    || sender.hasPermission("supportchat.data.remove")
                    || sender.hasPermission("supportchat.data.set")
                    || sender.hasPermission("supportchat.data.reset")) {
                subcommands.add("data");
            }

            if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.use") || sender.hasPermission("supportchat.help")) {
                subcommands.add("help");
            }

            if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.use") || sender.hasPermission("supportchat.list")) {
                subcommands.add("list");
            }

            if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.use") || sender.hasPermission("supportchat.open")) {
                subcommands.add("open");
            }

            if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.reload")) {
                subcommands.add("reload");
            }

            matches = subcommands.stream().filter(val -> val.startsWith(args[0])).collect(Collectors.toList());

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("data")) {
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.add")) {
                    subcommands.add("add");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.delete")) {
                    subcommands.add("delete");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.list")) {
                    subcommands.add("list");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.remove")) {
                    subcommands.add("remove");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.reset")) {
                    subcommands.add("reset");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.set")) {
                    subcommands.add("set");
                }
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*") || sender.hasPermission("supportchat.data.stats")) {
                    subcommands.add("stats");
                }
                matches = subcommands.stream().filter(val -> val.startsWith(args[1])).collect(Collectors.toList());
            } else if (args[0].equalsIgnoreCase("open")) {
                if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.use") || sender.hasPermission("supportchat.open")) {
                    for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                        if (player.getName().startsWith(args[1])) {
                            matches.add(player.getName());
                        }
                    }
                }
            }

        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("data")) {
                if (args[1].equalsIgnoreCase("add")
                        || args[1].equalsIgnoreCase("delete")
                        || args[1].equalsIgnoreCase("remove")
                        || args[1].equalsIgnoreCase("set")
                        || args[1].equalsIgnoreCase("stats")) {
                    if (sender.hasPermission("supportchat.*") || sender.hasPermission("supportchat.data.*")
                            || sender.hasPermission("supportchat.data.add") || sender.hasPermission("supportchat.data.delete")
                            || sender.hasPermission("supportchat.data.remove") || sender.hasPermission("supportchat.data.set")
                            || sender.hasPermission("supportchat.data.stats")) {
                        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                            if (player.getName().startsWith(args[2])) {
                                matches.add(player.getName());
                            }
                        }
                    }
                }
            }
        }
        return matches;
    }
}
