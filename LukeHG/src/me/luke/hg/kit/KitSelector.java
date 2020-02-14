package me.luke.hg.kit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.luke.hg.Main;

public class KitSelector {
	
	private final Player p;
	
	public Kit kit;
	public int pagina;
	public int paginaNumbers;
	
	public List<Kit> kitsList;
	
	public KitSelector(Player p) {
		this.p = p;
		this.pagina = 1;
		
		this.kitsList = new ArrayList<>();
		for(Kit kit : Kit.values()) {
			if(!kitsList.contains(kit) && p.hasPermission("kit." + kit.getName())) {
				kitsList.add(kit);
			}
		}
		openInventory();
	}
	public void openInventory() {
		Inventory inventory = Bukkit.createInventory(p, 54, "Kits");
		
		paginaNumbers = kitsList.size() / 53 + 1;
		int i = 9, page = 1;
		
		for (Kit kit : kitsList) {
			if(page < pagina) {
				if(i == 53) {
					i = 8;
					page += 1;
				}
				
				i += 1;
			}else {
				if(i > 53)
					break;
				
				ItemStack kitItem = new ItemStack(kit.getMaterial());
				ItemMeta kitMeta = kitItem.getItemMeta();
				kitMeta.setDisplayName("§a" + kit.getName());
				kitMeta.setLore(kit.getLore());
				kitItem.setItemMeta(kitMeta);
				inventory.setItem(i++, kitItem);
			}
		}
	}
     private Listener listener;
     
     public void createListener() {
    	 this.listener = new Listener() {
    		 
    		 @EventHandler
    		 public void onInventoryClose(InventoryCloseEvent e){
    			 if(e.getPlayer() != p)
    				 return;
    			 
    			 HandlerList.unregisterAll(listener);
    		 }
    		 
    		 @EventHandler
    		 public void onQuit(PlayerQuitEvent e) {
    			 if(e.getPlayer() != p)
    				 return;
    			 
    			 HandlerList.unregisterAll(listener);
    		 }
    		 
    		 @EventHandler
    		 public void onClick(InventoryClickEvent e) {
    			 if(e.getWhoClicked() != p)
    				 return;
    			 
    			 e.setCancelled(true);
    			 Inventory inventory = e.getInventory();
    			 ItemStack item = e.getCurrentItem();
    			 if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
    				 if(inventory.getName().equalsIgnoreCase("Kits")) {
    					// if(e.getRawSlot() < 9)
    					//	 return;
    					 //-> incompleto
    				 }
    			 }
    		 }
    		 
    	 };
    	 Bukkit.getPluginManager().registerEvents(listener, Main.getPlugin());
     }
}
