package me.nathanaelps.plugins.healinghoe;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HealingHoe extends JavaPlugin implements Listener {
	
	public static String pluginName;
	public static String pluginVersion;
	public static Server server;
	public static HealingHoe plugin;
	
    public void onDisable() {
        // TODO: Place any custom disable code here.
        System.out.println(this + " is now disabled!");
    }

    public void onEnable() {
        // TODO: Place any custom enable code here, such as registering events
    	pluginName = this.getDescription().getName();
    	pluginVersion = this.getDescription().getVersion();
    	server = this.getServer();
    	plugin = this;
    	getServer().getPluginManager().registerEvents(this, this);
        System.out.println(plugin + " is now enabled!");
    }
    
	@EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
		ItemStack tool = event.getPlayer().getItemInHand();

		if((tool.getType().equals(Material.GOLD_HOE))){// &&
			Player player = (Player) event.getPlayer();
			List<Entity> nearentities = player.getNearbyEntities(5, 5, 5);
			nearentities.add(player);
			for(Entity e : nearentities){
				if (e instanceof Player){
					Player p = (Player) e;
					if(p.getHealth() < p.getMaxHealth()){
						p.setHealth(p.getHealth()+1);
						p.getLocation().getWorld().strikeLightningEffect(p.getEyeLocation());
					}
				}
			}
		}
	}
}
