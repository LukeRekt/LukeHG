package me.luke.hg.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.server.ServerListPingEvent;

import me.luke.hg.Main;
import me.luke.hg.utils.ServerStage;

public class WorldListener implements Listener{
	private Main plugin;
	
	public WorldListener(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.hasServerStage(ServerStage.INICIANDO) || plugin.hasServerStage(ServerStage.ACABOU)) {
			boolean iniciando = plugin.hasServerStage(ServerStage.INICIANDO) ? true : false;
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.hasServerStage(ServerStage.INICIANDO) || plugin.hasServerStage(ServerStage.ACABOU)) {
			boolean iniciando = plugin.hasServerStage(ServerStage.INICIANDO) ? true : false;
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onMotd(ServerListPingEvent e) {
		e.setMotd(ChatColor.GREEN + "JOGO INICIANDO EM " + plugin.TEMPO_INICIANDO);
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		
		if(!(e.getEntity() instanceof Player)) {			
			Player p = (Player) e.getEntity();
            if(plugin.hasServerStage(ServerStage.INICIANDO) || plugin.hasServerStage(ServerStage.INVENCIBILIDADE)) {
    			if(p.getFoodLevel() < 20) {
    				p.setFoodLevel(20);
    			}
    			e.setCancelled(true);
            }
			p.setSaturation(4.2F);
		}
			
		
		
	}
}
