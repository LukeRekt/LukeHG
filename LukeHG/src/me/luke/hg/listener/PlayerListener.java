package me.luke.hg.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.luke.hg.Main;
import me.luke.hg.manager.PlayerManager;
import me.luke.hg.utils.ServerStage;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class PlayerListener implements Listener {

	
	private Main plugin;
	
	public PlayerListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		e.setJoinMessage(null);
		Player p = e.getPlayer();
		
		if(plugin.hasServerStage(ServerStage.INICIANDO)) {
			plugin.sendScoreboard(p);
			p.sendMessage(ChatColor.GREEN + "Bem-Vindo " + p.getName() + "!");
			//p.getInventory().setItem(0, new ItemStack(Material.CHEST), "");
			
			UUID uuid = p.getUniqueId();
			plugin.playermanager.add(new PlayerManager(uuid, true, true));
			plugin.playersInGame.add(p.getUniqueId());
			
			ItemStack item = new ItemStack(Material.CHEST, 1);

			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName("Kits");
			
			ItemStack itemr = new ItemStack(Material.STICK, 1);

			ItemMeta itemmetar = item.getItemMeta();
			itemmeta.setDisplayName("PIMPS DO NEGAO| JIAN Like");

			item.setItemMeta(itemmeta);
			p.getInventory().setItem(0, new ItemStack(item));
			
			item.setItemMeta(itemmetar);
			p.getInventory().setItem(1, new ItemStack(itemr));
			
			World mundo = Bukkit.getWorld("world");
			Location location = new Location(mundo, -5, 90, 9);
			p.teleport(location);
			

		}
	}
	@EventHandler
	public void onMorrer(PlayerDeathEvent e) {
		
		Player p = e.getEntity();
		plugin.playersInGame.remove(p.getUniqueId());
		p.sendMessage("dead por " + p.getUniqueId());
	}
	@EventHandler
	public void onAchiev(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		
		if(!action.name().contains("PYSICAL")) {
			ItemStack hand = p.getItemInHand();
			EntityPlayer player = ((CraftPlayer) p).getHandle();
			
			if(hand != null && hand.getType() == Material.MUSHROOM_SOUP) {
				if(player.getHealth() < player.getMaxHealth()) {
					double newHealth = (player.getHealth() + 7.0F);
					
					p.setHealth(newHealth >= player.getMaxHealth() ? player.getMaxHealth() : newHealth);
					p.getInventory().setItemInHand(new ItemStack(Material.BOWL));
				}else if (p.getFoodLevel() < 20) {
					p.setFoodLevel((int) (p.getFoodLevel() + 60.0F));
					p.getInventory().setItemInHand(new ItemStack(Material.BOWL));
				}
				p.updateInventory();
			} else if (hand != null && hand.getType() == Material.COMPASS) {
				Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> compassPlayer(p));
			}
		}
	}
	
	public void compassPlayer(Player p) {
		double distance = Double.POSITIVE_INFINITY;
		Player target = null;
		
		for(Entity entities : p.getNearbyEntities(500.0D, 160.0D, 500.0D)) {
			if(entities instanceof Player && entities != p) {
				Player p2 = (Player) entities;
				double distancia = p.getLocation().distance(p2.getLocation());
				
				if(distancia <= distance && distancia >= 10.0D) {
					distancia = distancia;
					target = p2;
				}
			}
		}
		
		p.setCompassTarget(target == null ? p.getWorld().getSpawnLocation() : target.getLocation());
		p.sendMessage(target == null ? "Nao foi possivel encontrar players" : "apontando para" + target.getName());
	}
}
