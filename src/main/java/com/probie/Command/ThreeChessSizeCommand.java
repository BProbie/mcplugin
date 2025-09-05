package com.probie.Command;

import java.util.List;
import com.probie.Data;
import java.util.ArrayList;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class ThreeChessSizeCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("threechess-size")) {
            switch (strings.length) {
                case 0: {
                    commandSender.sendMessage(Data.getSimpleMessageHead()+"当前大小为"+Data.colorWord(Data.getSize()));
                    break;
                }
                case 1: {
                    if (commandSender.isOp()) {
                        try {
                            int size = Integer.parseInt(strings[0]);
                            if (size >= 3 && size <= 6) {
                                Data.setSize(size);
                                Data.setUiSize(9 * size);
                                Data.setChessWidth(size);
                                Data.setChessHeight(size);
                                Data.getConfig().set("size",size);
                                try {
                                    Data.getConfig().save(Data.getConfigFilePath());
                                } catch (IOException ioException) {
                                    throw new RuntimeException(ioException);
                                }
                                commandSender.sendMessage(Data.getSimpleMessageHead()+"大小更改成功,当前大小为"+Data.colorWord(size));
                            } else {
                                commandSender.sendMessage(Data.getSimpleMessageHead()+strings[0]+"不是3-6的整数");
                            }
                        } catch (NumberFormatException numberFormatException) {
                            commandSender.sendMessage(Data.getSimpleMessageHead()+strings[0]+"不是3-6的整数");
                        }
                    } else {
                        commandSender.sendMessage(Data.getSimpleMessageHead()+"权限不足");
                    }
                    break;
                }
                default: {
                    commandSender.sendMessage(Data.getMessageHead()+command.getUsage());
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCommand = new ArrayList<>();
        if (strings.length == 0) {
            tabCommand.add("threechess-size");
        }
        else if (strings.length == 1 && command.getName().equalsIgnoreCase("threechess-size")) {
            for (int i = 3; i <= 6; i++) {
                tabCommand.add(String.valueOf(i));
            }
        }
        return tabCommand;
    }

}