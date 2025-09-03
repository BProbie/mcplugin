package com.probie.Command;

import java.util.List;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;

public class CommandTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCommandList = new ArrayList<>();
        if (strings.length == 1) {
            tabCommandList.add("tpa");
            tabCommandList.add("tpaccept");
        }
        return tabCommandList;
    }
}
