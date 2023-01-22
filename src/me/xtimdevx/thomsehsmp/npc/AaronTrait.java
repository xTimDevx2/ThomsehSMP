package me.xtimdevx.thomsehsmp.npc;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import me.xtimdevx.thomsehsmp.quests.QuestManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@TraitName("aaron")
public class AaronTrait extends Trait {

    private Logger logger;

    private QuestManager manager = new QuestManager();

    public AaronTrait() {
        super("aaron");

        plugin = JavaPlugin.getPlugin(Main.class);
    }

    Main plugin = null;

    @Persist("aaronsettings")
    boolean automaticallyPersistedSetting = false;

    boolean setting = false;

    // Here you should load up any values you have previously saved (optional).
    // This does NOT get called when applying the trait for the first time, only
    // loading onto an existing npc at server start.
    // This is called AFTER onAttach so you can load defaults in onAttach and
    // they will be overridden here.
    // This is called BEFORE onSpawn, npc.getBukkitEntity() will return null.
    public void load(DataKey key) {
        setting = key.getBoolean("aaronsettings", false);
    }

    // Save settings for this NPC (optional). These values will be persisted to
    // the Citizens saves file
    public void save(DataKey key) {
        key.setBoolean("aaronsettings", setting);
    }

    // An example event handler. All traits will be registered automatically as
    // Bukkit Listeners.

    EconomyManager economyManager = new EconomyManager();
    @EventHandler
    public void click(NPCRightClickEvent event) {
        if (event.getNPC() == this.getNPC()) {
            User user = User.get(event.getClicker());
            Player player = (Player) event.getClicker();

            if(user.getFile().getBoolean("quest.TIMBER.completed")) {
                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Ik heb voorlopig geen nieuwe quests voor jou. Maar kom zo nu en dan even langs!");
                return;
            }
            if(!manager.questlineStarted(QuestManager.QuestLines.TIMBER, event.getClicker())) {
                manager.startQuestline(QuestManager.QuestLines.TIMBER, event.getClicker());
            }else {
                if(manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_16LOGS, event.getClicker())) {
                    manager.npcReply(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_16LOGS, event.getClicker());
                    return;
                }
                if(manager.isQuestCompleted(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_16LOGS, event.getClicker()) && !manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, event.getClicker()) && !manager.isQuestCompleted(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, player)) {
                    if(QuestManager.NPCTalking.contains(event.getClicker().getName())) {
                        return;
                    }
                    manager.startQuest(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, event.getClicker());
                    return;
                }

                if(manager.isQuestCompleted(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, event.getClicker()) && !manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_3SAPLING, event.getClicker())) {
                    manager.startQuest(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_3SAPLING, event.getClicker());
                    return;
                }


                if(manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_3SAPLING, player)) {
                    if (QuestManager.NPCTalking.contains(player.getName())) {
                        return;
                    }
                    Inventory i = player.getInventory();
                    if (i.containsAtLeast(new ItemStack(Material.SPRUCE_SAPLING), 1) && i.containsAtLeast(new ItemStack(Material.ACACIA_SAPLING), 1) && i.containsAtLeast(new ItemStack(Material.JUNGLE_SAPLING), 1)) {
                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.SPRUCE_SAPLING)) {
                                if (inven.getAmount() == 0) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 1);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.ACACIA_SAPLING)) {
                                if (inven.getAmount() == 0) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 1);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.JUNGLE_SAPLING)) {
                                if (inven.getAmount() == 0) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 1);
                                }
                            }
                        }

                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Dat zijn de sapplings die ik nodig heb!");

                        int taskID = -1;

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Nu kunnen we ons eigen magische bos beginnen te planten.");
                            }
                        }, 50);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Ik had dit nooit zonder jou hulp kunnen doen reisiger.");
                            }
                        }, 100);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Je hebt deze rewards meer dan verdient. Ik hoop je snel weer terug te zien.");
                            }
                        }, 150);
                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(player, "§3§lQuestLine Afgerond!");
                                MessageUtils.sendCenteredMessage(player, "§fAaron de houdhakker ");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fHoofdstuk §33§8/§33");
                                MessageUtils.sendCenteredMessage(player, "§fVerzamel de saplings die Aaron nodig heeft..");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fDit was voorlopig de laatste quest voor aaron.");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendTitle("§3§lQuest Afgerond", "§fHet magische bos van Aaron!");
                                player.sendMessage("§8§m----------------------------------------------------");
                                player.sendMessage("§7§o§lReward");
                                player.sendMessage("§7§o- Aaron staat bij je in het krijt");
                                player.sendMessage("§7§o- 1000 ⛀");
                                player.sendMessage("§7§o- 10 EXP levels");
                                player.sendMessage("§8§m----------------------------------------------------");
                                player.sendMessage("§7§oAaron will remember that...");
                                user.getFile().set("quest.TIMBER.TIMBER_3SAPLING.completed", true);
                                user.getFile().set("quest.TIMBER.TIMBER_3SAPLING.active", false);
                                user.getFile().set("quest.TIMBER.completed", true);
                                user.saveFile();
                                economyManager.addBalance(player, 1000);
                                player.giveExpLevels(10);
                            }
                        }, 200);
                        return;
                    }
                }

                if(manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, event.getClicker())) {
                    if(user.getFile().getInt("quest.TIMBER.TIMBER_DIAMONDAXE.stage") == 1) {
                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Breng 4 diamonds, 2 sticks en een bookshelf naar Baldemar op -65, 85, -117 en zeg dat ik je gestuurd heb.");
                        return;
                    }
                    if(user.getFile().getInt("quest.TIMBER.TIMBER_DIAMONDAXE.stage") == 2) {
                        ItemStack item = player.getItemInHand();

                        if(item.getItemMeta() != null && item.getItemMeta().getDisplayName().equalsIgnoreCase("§cHeatwaker")) {
                            manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Zo te zien heb je Baldemar gevonden en heb je Heatwaker te pakken weten te krijgen.");

                            int taskID = -1;

                            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Die gaat goed van pas komen bij onze volgende missie.");
                                }
                            }, 50);

                            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Ga voorlopig maar uitrusten. Kom terug met me praten als je klaar bent voor onze volgende missie");
                                }
                            }, 100);

                            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    player.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(player, "§3§lQuest Afgerond!");
                                    MessageUtils.sendCenteredMessage(player, "§fAaron de houthakker ");
                                    player.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(player, "§fHoofdstuk §32§8/§33");
                                    MessageUtils.sendCenteredMessage(player, "§fVerkrijg de Heatwaker bijl van Baldemar de smid.");
                                    player.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(player, "§fPraat opnieuw met Aaron voor je laatste quest.");
                                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    player.sendTitle("§3§lHoofdstuk Afgerond", "§fVolgende: Praat met Aaron");
                                    player.sendMessage("§8§m----------------------------------------------------");
                                    player.sendMessage("§7§o§lReward");
                                    player.sendMessage("§7§o- §cHeatwaker");
                                    player.sendMessage("§7§o- 200 ⛀");
                                    player.sendMessage("§7§o- 5 XP levels");
                                    player.sendMessage("§8§m----------------------------------------------------");
                                    user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.completed", true);
                                    user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.active", false);
                                    user.getFile().set("quest.TIMBER.TIMBER_3SAPLING.active", false);
                                    user.getFile().set("quest.TIMBER.TIMBER_3SAPLING.completed", false);
                                    user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.stage", 1);
                                    user.saveFile();

                                    economyManager.addBalance(player, 200);
                                    player.giveExpLevels(5);
                                }
                            }, 150);
                            return;
                        }
                    }
                }
            }
        }
    }

    // Called every tick
    @Override
    public void run() {
    }

    // Run code when your trait is attached to a NPC.
    // This is called BEFORE onSpawn, so npc.getBukkitEntity() will return null
    // This would be a good place to load configurable defaults for new NPCs.
    @Override
    public void onAttach() {
    }

    // Run code when the NPC is despawned. This is called before the entity
    // actually despawns so npc.getBukkitEntity() is still valid.
    @Override
    public void onDespawn() {
    }

    // Run code when the NPC is spawned. Note that npc.getBukkitEntity() will be
    // null until this method is called.
    // This is called AFTER onAttach and AFTER Load when the server is started.
    @Override
    public void onSpawn() {
    }

    // run code when the NPC is removed. Use this to tear down any repeating
    // tasks.
    @Override
    public void onRemove() {
    }
}
