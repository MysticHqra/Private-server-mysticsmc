package me.mystichara.mysticshangout.commands;

import me.mystichara.mysticshangout.MysticsHangout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Bengali implements CommandExecutor {

    Plugin plugin = MysticsHangout.getPlugin(MysticsHangout.class);
    static String owner = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(args.length==0) {
                if(owner.equals("")) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"npc select 4");
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard "+sender.getName());
                    owner = sender.getName();
                    sender.sendMessage(MysticsHangout.Color("&aBengali is now guarding you."));
                }
                else if(owner.equals(sender.getName())) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"npc select 4");
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard");
                    owner = "";
                    sender.sendMessage(MysticsHangout.Color("&cBengali is no longer guarding you."));
                }
                else {
                    sender.sendMessage("Bengali was guarding "+owner);
                    transfer(sender.getName(), owner);
                    sender.sendMessage("Bengali is now guarding you");
                }

            }
            else if(args.length==1) {

                if(args[0].equals(sender.getName())) {
                    if(owner.equals("")) {
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"npc select 4");
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard "+sender.getName());
                        owner = sender.getName();
                        sender.sendMessage(MysticsHangout.Color("&aBengali is now guarding you."));
                    }
                    else if(owner.equals(sender.getName())) {
                        sender.sendMessage(MysticsHangout.Color("&cBengali is already guarding you. To stop guarding, use /bengali stop"));
                    }
                    else {
                        sender.sendMessage(MysticsHangout.Color("&cBengali was guarding "+owner));
                        transfer(sender.getName(), owner);
                        sender.sendMessage(MysticsHangout.Color("&aBengali is now guarding you"));
                    }
                }

                else if(args[0].equals("stop")) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"npc select 4");
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard");
                    owner = "";
                    sender.sendMessage(MysticsHangout.Color("&cBengali no longer guards you."));
                }

                else if(sender.hasPermission("mysticshangout.bengali.others")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target instanceof Player) {
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"npc select 4");
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard "+args[0]);
                        sender.sendMessage(MysticsHangout.Color("&6Bengali now guards "+args[0]));
                        target.sendMessage(MysticsHangout.Color("&6"+sender.getName()+" made Bengali guard you"));
                    }
                    else
                        sender.sendMessage(MysticsHangout.Color("&c"+args[0]+" is offline, try again later!"));
                }
                else
                    sender.sendMessage(MysticsHangout.Color("&cYou don''t have permission to do this."));

            }
            else
                sender.sendMessage(MysticsHangout.Color("&cInvalid Usage: Use /bengali [name]"));

        }
        else
            System.out.println(MysticsHangout.Color("&cYou must execute this command as a player"));

        return true;
    }

    public void transfer(String sender, String owner) {
        Player targetowner = Bukkit.getPlayerExact(owner);
        targetowner.sendMessage(ChatColor.RED+sender+" took your Bengali");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"sentinel guard "+sender);
    }
}