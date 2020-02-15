package me.luke.hg.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.luke.hg.HG;
import me.luke.hg.HGState;

public class HGWorldBorder implements Listener{

	@EventHandler
	public void event(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		if (HG.state == HGState.STARTING) {
			if (p.getWorld().getSpawnLocation().distance(p.getLocation()) > 100) {
				e.setCancelled(true);
				p.sendMessage("�cVoce atingiu o limite espere a partida iniciar!");
			}
		} else {
			if (p.getWorld().getSpawnLocation().distance(p.getLocation()) > 1000) {
				e.setCancelled(true);
				p.sendMessage("�cVoce esta no limite do mapa!");
			}
		}

	}

}
