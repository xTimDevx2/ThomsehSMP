package me.xtimdevx.thomsehsmp.npc;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.quests.QuestManager;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@TraitName("baldemar")
public class BaldemarTrait extends Trait {

    private Logger logger;

    private QuestManager manager = new QuestManager();

    public BaldemarTrait() {
        super("baldemar");

        plugin = JavaPlugin.getPlugin(Main.class);
    }

    Main plugin = null;

    @Persist("baldemarsettings")
    boolean automaticallyPersistedSetting = false;

    boolean setting = false;

    // Here you should load up any values you have previously saved (optional).
    // This does NOT get called when applying the trait for the first time, only
    // loading onto an existing npc at server start.
    // This is called AFTER onAttach so you can load defaults in onAttach and
    // they will be overridden here.
    // This is called BEFORE onSpawn, npc.getBukkitEntity() will return null.
    public void load(DataKey key) {
        setting = key.getBoolean("baldemarsettings", false);
    }

    // Save settings for this NPC (optional). These values will be persisted to
    // the Citizens saves file
    public void save(DataKey key) {
        key.setBoolean("baldemarsettings", setting);
    }

    // An example event handler. All traits will be registered automatically as
    // Bukkit Listeners.

    public boolean isTalking = false;

    @EventHandler
    public void click(NPCRightClickEvent event) {
        if (event.getNPC() == this.getNPC()) {
            User user = User.get(event.getClicker());
            Player player = event.getClicker();
            if(manager.isQuestActive(QuestManager.QuestLines.TIMBER, QuestManager.Quests.TIMBER_DIAMONDAXE, event.getClicker())) {
                if (user.getFile().getInt("quest.TIMBER.TIMBER_DIAMONDAXE.stage") == 1) {
                    if (isTalking) {
                        player.sendMessage("§8> §fBaldemar kan maar met 1 speler tegelijk praten. Wachten even tot hij klaar is.");
                        return;
                    }
                    Inventory i = player.getInventory();

                    if (i.containsAtLeast(new ItemStack(Material.DIAMOND), 4) && i.containsAtLeast(new ItemStack(Material.STICK), 2) && i.containsAtLeast(new ItemStack(Material.BOOKSHELF), 1)) {
                        isTalking = true;
                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.DIAMOND)) {
                                if (inven.getAmount() == 3) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 4);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.STICK)) {
                                if (inven.getAmount() == 1) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 2);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.BOOKSHELF)) {
                                if (inven.getAmount() == 0) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 1);
                                }
                            }
                        }

                        manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Jij bent vast door Aaron gestuurd, met deze materialen kan ik voor jullie een bijl maken zoals jullie nog nooit gezien hebben.");

                        Location anvil = new Location(Bukkit.getWorld("world"), -66.5, 86.5, -118.5);

                        int taskID = -1;

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Het is maar een suggestie maar ik zou een paar stappen achteruit gaan als ik jou was.");

                                event.getNPC().faceLocation(new Location(Bukkit.getWorld("world"), -66.5, 85, -118.5));


                            }
                        }, 50);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);

                                QuestManager.spawnAnvilParticle();


                            }
                        }, 80);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 1);
                                QuestManager.spawnAnvilParticle();


                            }
                        }, 110);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                                QuestManager.spawnAnvilParticle();
                                npc.faceLocation(player.getLocation());


                            }
                        }, 150);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                                axe.addEnchantment(Enchantment.DURABILITY, 2);

                                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);

                                manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Dit is heatwaker. Een bijl met magische krachten. Als je deze terug brengt naar Aaron zal hij zeker niet teleur gesteld zijn.");
                            }
                        }, 180);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                                axe.addEnchantment(Enchantment.DURABILITY, 2);
                                axe.addEnchantment(Enchantment.DIG_SPEED, 2);
                                ItemMeta axeMeta = axe.getItemMeta();
                                axeMeta.setDisplayName("§cHeatwaker");
                                axe.setItemMeta(axeMeta);

                                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.FLINT_AND_STEEL, 1));

                                manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Veel success en tot de volgende keer reiziger.");

                                player.sendMessage("§8> §fJe kreeg de §cHeatwaker §fbijl van baldemar, ga terug met Aaron praten.");
                                player.sendMessage("§7§o(Tip: Ben je heatwaker verloren? Je kan altijd een nieuwe gaan maken bij Baldemar)");
                                Utils.giveItem(player, axe, 1);

                                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.stage", 2);
                                user.saveFile();

                                isTalking = false;

                                npc.faceLocation(new Location(Bukkit.getWorld("world"), -63, 85, -112));
                            }
                        }, 230);
                        return;


                    } else {
                        manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Jij bent vast door Aaron gestuurd, als je wilt dat ik die bijl voor je maak zul je me 4 diamonds, 2 sticks en een bookshelf moeten brengen.");
                    }
                    return;
                }
                if (user.getFile().getInt("quest.TIMBER.TIMBER_DIAMONDAXE.stage") == 2) {
                    if(user.getFile().getBoolean("quest.TIMBER.TIMBER_DIAMONDAXE.heatwakerherkansing")) {
                        manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Ik heb al een nieuwe voor jou gemaakt reiziger, je kansen zijn op. Mischien wil iemand wel met je traden.");
                        return;
                    }
                    if (isTalking) {
                        player.sendMessage("§8> §fBaldemar kan maar met 1 speler tegelijk praten. Wachten even tot hij klaar is.");
                        return;
                    }
                    Inventory i = player.getInventory();

                    if (i.containsAtLeast(new ItemStack(Material.DIAMOND), 4) && i.containsAtLeast(new ItemStack(Material.STICK), 2) && i.containsAtLeast(new ItemStack(Material.BOOKSHELF), 1)) {
                        isTalking = true;
                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.DIAMOND)) {
                                if (inven.getAmount() == 3) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 4);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.STICK)) {
                                if (inven.getAmount() == 1) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 2);
                                }
                            }
                        }

                        for (ItemStack inven : i.getContents()) {
                            if ((inven != null) && (inven.getType() == Material.BOOKSHELF)) {
                                if (inven.getAmount() == 0) {
                                    inven.setType(Material.AIR);
                                } else {
                                    inven.setAmount(inven.getAmount() - 1);
                                }
                            }
                        }

                        manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Oke ik maak een nieuwe voor je, maar alleen voor deze keer.");

                        Location anvil = new Location(Bukkit.getWorld("world"), -66.5, 86.5, -118.5);

                        int taskID = -1;

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Het is maar een suggestie maar ik zou een paar stappen achteruit gaan als ik jou was.");

                                event.getNPC().faceLocation(new Location(Bukkit.getWorld("world"), -66.5, 85, -118.5));


                            }
                        }, 50);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);

                                QuestManager.spawnAnvilParticle();


                            }
                        }, 80);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 1);
                                QuestManager.spawnAnvilParticle();


                            }
                        }, 110);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                                QuestManager.spawnAnvilParticle();
                                npc.faceLocation(player.getLocation());


                            }
                        }, 150);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                                axe.addEnchantment(Enchantment.DURABILITY, 2);

                                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);

                            }
                        }, 180);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {

                                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                                axe.addEnchantment(Enchantment.DURABILITY, 2);
                                axe.addEnchantment(Enchantment.DIG_SPEED, 2);
                                ItemMeta axeMeta = axe.getItemMeta();
                                axeMeta.setDisplayName("§cHeatwaker");
                                axe.setItemMeta(axeMeta);

                                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.FLINT_AND_STEEL, 1));

                                manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Verlies hem nu niet nog eens reiziger.");

                                player.sendMessage("§8> §fJe kreeg de §cHeatwaker §fbijl van baldemar, ga terug met Aaron praten.");
                                player.sendMessage("§7§o(Tip: Verlies Heatwaker niet nog eens, je kan hem niet nog eens maken.)");
                                Utils.giveItem(player, axe, 1);

                                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.heatwakerherkansing", true);
                                user.saveFile();

                                isTalking = false;

                                npc.faceLocation(new Location(Bukkit.getWorld("world"), -63, 85, -112));
                            }
                        }, 230);


                    } else {
                        manager.sendNPCMessage(QuestManager.NPCS.BALDEMAR, player, "Ben je de bijl verloren? Geen probleem breng gewoon opnieuw 4 diamonds, 2 sticks en een bookshelf en dan maak ik een nieuwe voor je.");
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
