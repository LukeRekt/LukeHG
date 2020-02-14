package me.luke.hg.kit.list;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.luke.hg.kit.KitExecutor;
import me.luke.hg.utils.ServerStage;

public class KangarooExecutor extends KitExecutor{
	
	private HashMap<UUID, Integer> jumped;
	
	public KangarooExecutor() {
		this.jumped = new HashMap<>();
		
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack hand = p.getItemInHand();
		
		if(plugin.hasServerStage(ServerStage.INICIANDO))
			return;
		
		if(hand.getType() == Material.FIREWORK) {
			if(!this.jumped.containsKey(p.getUniqueId())) {
				if(!p.isSneaking()) {
					if(!((CraftPlayer)p).getHandle().onGround) {
						p.setVelocity(new Vector(p.getVelocity().getX(), 1.2D, p.getVelocity().getZ()));
						this.jumped.put(p.getUniqueId(), 1);
					}else {
						p.setVelocity(new Vector(p.getVelocity().getX(), 1.2D, p.getVelocity().getZ()));
					}
				}else if (!((CraftPlayer)p).getHandle().onGround) {
					p.setVelocity(p.getLocation().getDirection().multiply(1.5D).setY(0.6D));
					this.jumped.put(p.getUniqueId(), 1);
				}else {
					p.setVelocity(p.getLocation().getDirection().multiply(1.5D).setY(0.6D));
				}
			}
			
			e.setCancelled(true);
		}
		
	}

}
