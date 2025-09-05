package com.probie.Manager;

import com.probie.Data;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.probie.Packet.ThreeChessInvitePacket;

public class ThreeChessInviteManager extends ThreeChessManager {

    private final static ThreeChessInviteManager INSTANCE = new ThreeChessInviteManager();

    private final HashMap<ThreeChessInvitePacket, Integer> invitePackets = new HashMap<>();

    private boolean hasInit = false;

    public void invite(Player sendPlayer, Player receivePlayer) {
        sendPlayer.sendMessage(Data.getSimpleMessageHead()+"你向玩家"+Data.colorPlayerName(receivePlayer.getName())+"发送了三子棋请求");
        receivePlayer.sendMessage(Data.getSimpleMessageHead()+"玩家"+Data.colorPlayerName(sendPlayer.getName())+"向你发送了三子棋请求");
        receivePlayer.sendMessage(Data.getSimpleMessageHead()+"请在"+Data.colorWord(Data.getInviteOutDateTime())+"秒内输入"+Data.colorWord("/threechess-accept")+"接受邀请");
        getInvitePackets().put(new ThreeChessInvitePacket(receivePlayer, sendPlayer), Data.getInviteOutDateTime());
        if (!hasInit) {
            hasInit = true;
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    second();
                }
            };
            bukkitRunnable.runTaskTimerAsynchronously(Data.getPlugin(), 20, 20);
            Data.getBukkitRunnables().add(bukkitRunnable);
        }
    }

    public void second() {
        for (ThreeChessInvitePacket packet : getInvitePackets().keySet()) {
            int remaining = getInvitePackets().get(packet) - 1;
            if (remaining <= 0) {
                getInvitePackets().remove(packet);
            } else {
                getInvitePackets().put(packet, remaining);
            }
        }
    }

    public static ThreeChessInviteManager getINSTANCE() {
        return INSTANCE;
    }

    public HashMap<ThreeChessInvitePacket, Integer> getInvitePackets() {
        return invitePackets;
    }

}