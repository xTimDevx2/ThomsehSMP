package me.xtimdevx.thomsehsmp.npc;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.markets.MarketMethods;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@TraitName("fakena")
public class FakenaTrait extends Trait {

    private Logger logger;


    public FakenaTrait() {
        super("fakena");

        plugin = JavaPlugin.getPlugin(Main.class);
    }

    Main plugin = null;

    @Persist("fakenasettings")
    boolean automaticallyPersistedSetting = false;

    boolean setting = false;

    // Here you should load up any values you have previously saved (optional).
    // This does NOT get called when applying the trait for the first time, only
    // loading onto an existing npc at server start.
    // This is called AFTER onAttach so you can load defaults in onAttach and
    // they will be overridden here.
    // This is called BEFORE onSpawn, npc.getBukkitEntity() will return null.
    public void load(DataKey key) {
        setting = key.getBoolean("fakenasettings", false);
    }

    // Save settings for this NPC (optional). These values will be persisted to
    // the Citizens saves file
    public void save(DataKey key) {
        key.setBoolean("fakenasettings", setting);
    }

    // An example event handler. All traits will be registered automatically as
    // Bukkit Listeners.

    public boolean isTalking = false;

    @EventHandler
    public void click(NPCRightClickEvent event) {
        if (event.getNPC() == this.getNPC()) {
            User user = User.get(event.getClicker());
            Player player = event.getClicker();
            MarketMethods marketMethods = new MarketMethods();

            marketMethods.marketNPCMessage(MarketMethods.MarketNPC.FAKENA, player, "§4§k;; §fGet fakena'd. §4§k;;");

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
