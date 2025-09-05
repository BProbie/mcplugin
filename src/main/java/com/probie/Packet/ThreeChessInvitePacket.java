package com.probie.Packet;

import java.util.UUID;
import java.util.HashMap;
import java.util.Objects;
import org.bukkit.entity.Player;

public class ThreeChessInvitePacket extends ThreeChessPacket {

    /**
     * @第一个UUID 被邀请的玩家
     * @第二个UUID 发出邀请的玩家
     * */
    private final HashMap<UUID, UUID> inviteMap = new HashMap<>();

    public ThreeChessInvitePacket(Player sendPlayer, Player receivePlayer) {
        getInviteMap().put(sendPlayer.getUniqueId(), receivePlayer.getUniqueId());
    }

    public HashMap<UUID, UUID> getInviteMap() {
        return inviteMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        else if (obj.getClass() == getClass()) {
            ThreeChessInvitePacket packet = (ThreeChessInvitePacket) obj;
            return packet.getInviteMap().equals(getInviteMap());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInviteMap());
    }

}