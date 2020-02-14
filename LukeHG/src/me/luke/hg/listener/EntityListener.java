package me.luke.hg.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.luke.hg.Main;
import me.luke.hg.utils.ServerStage;

public class EntityListener implements Listener{
	
	private Main plugin;
	
	public EntityListener(Main plugin) {
		this.plugin = plugin;
	}
	
     @EventHandler
     public void onEntity(EntityDamageEvent e) {
    	 if(!(e.getEntity() instanceof Player))
    		 return;
    	 
    	 if(plugin.hasServerStage(ServerStage.INICIANDO) || plugin.hasServerStage(ServerStage.INVENCIBILIDADE)) {
    		 e.setCancelled(true);
    	 }
     }
}
