package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.PermsUtils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.actionlog.ActionLogger;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.messaging.MessagingService;
import net.luckperms.api.messenger.MessengerProvider;
import net.luckperms.api.metastacking.MetaStackFactory;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.NodeBuilderRegistry;
import net.luckperms.api.node.matcher.NodeMatcherFactory;
import net.luckperms.api.node.types.PermissionNode;
import net.luckperms.api.platform.Platform;
import net.luckperms.api.platform.PlayerAdapter;
import net.luckperms.api.platform.PluginMetadata;
import net.luckperms.api.query.QueryOptionsRegistry;
import net.luckperms.api.track.TrackManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.*;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BuilderCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("smp.command.builder")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        User user = User.get(player);

        boolean builder = user.getFile().getBoolean("builder");

        if(builder == false) {
            PermsUtils permsUtils = PermsUtils.getInstance();

            LuckPerms api = LuckPermsProvider.get();
            net.luckperms.api.model.user.User lpu = api.getPlayerAdapter(Player.class).getUser(player);
            lpu.data().remove(PermissionNode.builder("-worldguard.region.bypass.SMP").build());
            lpu.data().add(PermissionNode.builder("worldguard.region.bypass.SMP").build());
            lpu.data().add(PermissionNode.builder("minecraft.command.gamemode").build());
            lpu.data().add(PermissionNode.builder("fawe.admin").build());
            lpu.data().add(PermissionNode.builder("fawe.*").build());
            lpu.data().add(PermissionNode.builder("worldedit.*").build());
            lpu.data().add(PermissionNode.builder("minecraft.command.give").build());
            api.getUserManager().saveUser(lpu);


            player.sendMessage(MessageUtils.format("§8> §fJe hebt #00DC99§lB#00D091§lu#00BF85§li#00AE79§ll#019B6C§ld#008A60§le#007F58§lr §fmode §aaangezet§f."));
            player.sendMessage(MessageUtils.format("§8> §7§oJe kan nu bouwen in spawn en hebt permissions voor /gamemode creative en worldedit."));

            for(Player online : Bukkit.getOnlinePlayers()) {
                if(online.hasPermission("smp.staff")) {
                    online.sendMessage("§4§lOP §8> §c" + player.getName() + " §fheeft builder mode aangezet.");
                }
            }
            user.getFile().set("builder", true);
            user.saveFile();
        }else {
            PermsUtils permsUtils = PermsUtils.getInstance();

            LuckPerms api = LuckPermsProvider.get();
            net.luckperms.api.model.user.User lpu = api.getPlayerAdapter(Player.class).getUser(player);
            lpu.data().add(PermissionNode.builder("-worldguard.region.bypass.SMP").build());
            lpu.data().remove(PermissionNode.builder("worldguard.region.bypass.SMP").build());
            lpu.data().remove(PermissionNode.builder("minecraft.command.gamemode").build());
            lpu.data().remove(PermissionNode.builder("fawe.admin").build());
            lpu.data().remove(PermissionNode.builder("fawe.*").build());
            lpu.data().remove(PermissionNode.builder("worldedit.*").build());
            lpu.data().remove(PermissionNode.builder("minecraft.command.give").build());
            api.getUserManager().saveUser(lpu);


            player.sendMessage(MessageUtils.format("§8> §fJe hebt #00DC99§lB#00D091§lu#00BF85§li#00AE79§ll#019B6C§ld#008A60§le#007F58§lr §fmode §cuitgezet§f."));

            for(Player online : Bukkit.getOnlinePlayers()) {
                if(online.hasPermission("smp.staff")) {
                    online.sendMessage("§4§lOP §8> §c" + player.getName() + " §fheeft builder mode uitgezet.");
                }
            }
            user.getFile().set("builder", false);
            user.saveFile();


        }

        return true;
    }
}
