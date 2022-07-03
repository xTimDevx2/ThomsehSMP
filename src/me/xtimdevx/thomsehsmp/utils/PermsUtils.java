package me.xtimdevx.thomsehsmp.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PermsUtils{

    private static PermsUtils permsUtils = new PermsUtils();
    public static PermsUtils getInstance(){
        return permsUtils;
    }

    private LuckPerms luckPerms;

    public PermsUtils() {
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
        }
    }


    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public boolean addToGroup(OfflinePlayer player, String group) {
        Group newGroup = luckPerms.getGroupManager().getGroup(group);
        if (newGroup == null) {
            return false;
        }

        String playerName = player.getName();
        if (playerName == null) {
            return false;
        }
        User user = luckPerms.getUserManager().getUser(playerName);
        if (user == null) {
            return false;
        }

        InheritanceNode node = InheritanceNode.builder(group).build();
        DataMutateResult resultb = user.data().remove(node);
        DataMutateResult result = user.data().add(node);
        if (result == DataMutateResult.FAIL) {
            return false;
        }

        luckPerms.getUserManager().saveUser(user);
        return true;
    }

    public Group getGroup(String group) {
        return luckPerms.getGroupManager().getGroup(group);
    }

    public User getUser(OfflinePlayer player) {
        String playerName = player.getName();

        return luckPerms.getUserManager().getUser(playerName);
    }

    public boolean setGroup(OfflinePlayer player, String group) {
        String playerName = player.getName();
        if (playerName == null) {
            return false;
        }
        User user = luckPerms.getUserManager().getUser(playerName);
        if (user == null) {
            return false;
        }
        InheritanceNode groupNode = InheritanceNode.builder(group).build();
        DataMutateResult result = user.data().add(groupNode);
        if (result == DataMutateResult.FAIL) {
            return false;
        }
        user.data().clear(node -> {
            if (!(node instanceof InheritanceNode)) {
                return false;
            }
            InheritanceNode inheritanceNode = (InheritanceNode) node;
            return !inheritanceNode.equals(groupNode);
        });

        luckPerms.getUserManager().saveUser(user);
        return true;
    }


    public boolean isInGroup(OfflinePlayer player, String group) {
        String playerName = player.getName();
        if (playerName == null) {
            return false;
        }
        User user = luckPerms.getUserManager().getUser(playerName);
        if (user == null) {
            return false;
        }

        InheritanceNode inheritanceNode = InheritanceNode.builder(group).build();
        return user.data().contains(inheritanceNode, NodeEqualityPredicate.EXACT).asBoolean();
    }
}
