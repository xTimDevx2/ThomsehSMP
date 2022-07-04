package me.xtimdevx.thomsehsmp;

import me.xtimdevx.thomsehsmp.commands.*;
import me.xtimdevx.thomsehsmp.crates.CratesCommands;
import me.xtimdevx.thomsehsmp.crates.CratesEvents;
import me.xtimdevx.thomsehsmp.events.*;
import me.xtimdevx.thomsehsmp.features.BowPackage;
import me.xtimdevx.thomsehsmp.features.MoneyCheques;
import me.xtimdevx.thomsehsmp.features.RedstonePackage;
import me.xtimdevx.thomsehsmp.managers.CooldownManager;
import me.xtimdevx.thomsehsmp.managers.NPCManager;
import me.xtimdevx.thomsehsmp.npc.AaronTrait;
import me.xtimdevx.thomsehsmp.npc.BaldemarTrait;
import me.xtimdevx.thomsehsmp.npc.RedstoneMarketTrait;
import me.xtimdevx.thomsehsmp.quests.QuestCommands;
import me.xtimdevx.thomsehsmp.quests.QuestEvents;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.trait.TraitInfo;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    public static List<String> home = new ArrayList<>();

    public void onEnable() {
        plugin = this;
        Settings.getInstance().setup();
        registerCommands();
        registerListeners();

        home.add("home");


        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(AaronTrait.class).withName("aaron"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(BaldemarTrait.class).withName("baldemar"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(RedstoneMarketTrait.class).withName("redstonemarket"));

        try {
            loadDaily();
           getLogger().severe("Loaded correct");
        }catch (Exception e) {
            getLogger().severe("Load");
        }

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }
    }

    public void onDisable() {
        try {
            saveDaily();
        }catch (Exception e) {
            Bukkit.getLogger().log(Level.ALL, "Error while loading daily cooldowns.");
        }
    }

    public void saveDaily() {
        for (Map.Entry<String, Long> entry : RewardCommand.cooldownsdaily.entrySet()) {
            Settings.getInstance().getData().set("Db." + entry.getKey(), entry.getValue());
        }
        Settings.getInstance().saveData();
    }

    public  void loadDaily() {
        for (String key : Settings.getInstance().getData().getConfigurationSection("Db").getKeys(false)) {
            RewardCommand.cooldownsdaily.put(key, Settings.getInstance().getData().getLong("Db." + key));
        }
    }

    public void registerCommands() {
        getCommand("setrank").setExecutor(new SetRankCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("sethome").setExecutor(new SethomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("randomtp").setExecutor(new RandomTPCommand());
        getCommand("givekey").setExecutor(new CratesCommands());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("reward").setExecutor(new RewardCommand());
        getCommand("respawnnpc").setExecutor(new RespawnNPCCommand());
        getCommand("quest").setExecutor(new QuestCommands());
        getCommand("user").setExecutor(new UserCommand());
        getCommand("summonarmorstand").setExecutor(new SummonArmorstandCommand());

        //TAB
        getCommand("setrank").setTabCompleter(new SetRankCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ServerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new CratesEvents(), this);
        Bukkit.getPluginManager().registerEvents(new MoneyCheques(), this);
        Bukkit.getPluginManager().registerEvents(new RedstonePackage(), this);
        Bukkit.getPluginManager().registerEvents(new BowPackage(), this);
        Bukkit.getPluginManager().registerEvents(new CommandEvents(), this);
        Bukkit.getPluginManager().registerEvents(new NPCManager(), this);
        Bukkit.getPluginManager().registerEvents(new QuestEvents(), this);
    }
}
