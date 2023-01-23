package me.xtimdevx.thomsehsmp;

import me.xtimdevx.thomsehsmp.commands.*;
import me.xtimdevx.thomsehsmp.crates.CratesCommands;
import me.xtimdevx.thomsehsmp.crates.CratesEvents;
import me.xtimdevx.thomsehsmp.events.*;
import me.xtimdevx.thomsehsmp.features.*;
import me.xtimdevx.thomsehsmp.managers.AFKManager;
import me.xtimdevx.thomsehsmp.managers.NPCManager;
import me.xtimdevx.thomsehsmp.managers.TriviaManager;
import me.xtimdevx.thomsehsmp.managers.WorldManager;
import me.xtimdevx.thomsehsmp.markets.BlockMarket;
import me.xtimdevx.thomsehsmp.markets.FishMarket;
import me.xtimdevx.thomsehsmp.markets.RedstoneMarket;
import me.xtimdevx.thomsehsmp.markets.ResourceMarket;
import me.xtimdevx.thomsehsmp.npc.*;
import me.xtimdevx.thomsehsmp.quests.QuestCommands;
import me.xtimdevx.thomsehsmp.quests.QuestEvents;
import me.xtimdevx.thomsehsmp.utils.MovementChecker;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {

    public static Plugin plugin;

    public static List<String> home = new ArrayList<>();



    public static HashMap<CommandSender, CommandSender> msg = new HashMap<CommandSender, CommandSender>();

    public Bossbar bar;

    private AFKManager afkManager;
    private TriviaManager triviaManager;




    public void onEnable() {
        plugin = this;
        Settings.getInstance().setup();
        this.afkManager = new AFKManager();

        this.triviaManager = new TriviaManager();

        triviaManager.startTrivia();


        registerCommands();
        registerListeners();


        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(this.afkManager), 0L, 6000L);


        bar = new Bossbar(this);
        bar.createBar();

        if(Bukkit.getOnlinePlayers().size() > 0)
            for(Player online : Bukkit.getOnlinePlayers())
                bar.addPlayer(online);


        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(AaronTrait.class).withName("aaron"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(BaldemarTrait.class).withName("baldemar"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(RedstoneMarketTrait.class).withName("redstonemarket"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ResourceMarketTrait.class).withName("resourcemarket"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(FishMarketTrait.class).withName("fishmarket"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(BlockMarketTrait.class).withName("blockmarket"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ObiwanTrait.class).withName("obiwan"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(AshTrait.class).withName("ash"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(FakenaTrait.class).withName("fakena"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(FlopTrait.class).withName("flop"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ThomsehTrait.class).withName("thomseh"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(OogwayTrait.class).withName("oogway"));

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




        int taskID = -1;

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
            }
        }, 100);

    }

    public void onDisable() {
        try {
            saveDaily();
        }catch (Exception e) {
            Bukkit.getLogger().log(Level.ALL, "Error while loading daily cooldowns.");
        }

        bar.getBar().removeAll();


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
        getCommand("delhome").setExecutor(new DelhomeCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("tempban").setExecutor(new TempbanCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("world").setExecutor(new WorldCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("shout").setExecutor(new ShoutCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("donateannounce").setExecutor(new DonateAnnounceCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("crates").setExecutor(new CratesCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("helpop").setExecutor(new HelpopCommand());
        getCommand("createhologram").setExecutor(new CreateHologramCommand());
        getCommand("discord").setExecutor(new SocialCommands());
        getCommand("twitch").setExecutor(new SocialCommands());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("builder").setExecutor(new BuilderCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("giveaway").setExecutor(new GiveawayCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("language").setExecutor(new LanguageCommand());
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("checkhomes").setExecutor(new CheckHomesCommand());
        getCommand("amulet").setExecutor(new DonatorWand());
        getCommand("atp").setExecutor(new AdminTeleportCommand());
        getCommand("afk").setExecutor(new AFKCommand(this.afkManager));
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("settings").setExecutor(new SettingsCommand());
        getCommand("socialspy").setExecutor(new SocialspyCommand());



        //TAB
        getCommand("setrank").setTabCompleter(new SetRankCommand());
        getCommand("home").setTabCompleter(new HomeCommand());
        getCommand("maintenance").setTabCompleter(new MaintenanceCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(this, this);
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
        Bukkit.getPluginManager().registerEvents(new RedstoneMarket(), this);
        Bukkit.getPluginManager().registerEvents(new ResourceMarket(), this);
        Bukkit.getPluginManager().registerEvents(new FishMarket(), this);
        Bukkit.getPluginManager().registerEvents(new BlockMarket(), this);
        Bukkit.getPluginManager().registerEvents(new WorldManager(), this);
        Bukkit.getPluginManager().registerEvents(new Tutorial(), this);
        Bukkit.getPluginManager().registerEvents(new AFKListeners(this.afkManager), this);
        Bukkit.getPluginManager().registerEvents(new TriviaManager(), this);
        Bukkit.getPluginManager().registerEvents(new SettingsCommand(), this);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!bar.getBar().getPlayers().contains(event.getPlayer())) {
            bar.addPlayer(event.getPlayer());
        }
    }
}
