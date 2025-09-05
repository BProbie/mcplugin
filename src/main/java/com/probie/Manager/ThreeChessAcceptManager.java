package com.probie.Manager;

import java.util.UUID;
import com.probie.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.probie.Packet.ThreeChessInvitePacket;

public class ThreeChessAcceptManager extends ThreeChessManager {

    public final static ThreeChessAcceptManager INSTANCE = new ThreeChessAcceptManager();

    public void accept(Player acceptPlayer) {
        for (ThreeChessInvitePacket invitePacket : ThreeChessInviteManager.getINSTANCE().getInvitePackets().keySet()) {
            for (UUID uuid : invitePacket.getInviteMap().keySet()) {
                if (uuid == acceptPlayer.getUniqueId()) {
                    Player sendPlayer = Bukkit.getPlayer(invitePacket.getInviteMap().get(uuid));
                    if (sendPlayer != null) {
                        ThreeChessGameManager.getINSTANCE().game(sendPlayer, acceptPlayer);
                        ThreeChessInviteManager.getINSTANCE().getInvitePackets().remove(invitePacket);
                    } else {
                        acceptPlayer.sendMessage(Data.getSimpleMessageHead()+"玩家不在线");
                    }
                    return;
                }
            }
        }
        acceptPlayer.sendMessage(Data.getSimpleMessageHead()+"没有找到最近的游戏请求");
    }

    public static ThreeChessAcceptManager getINSTANCE() {
        return INSTANCE;
    }

}