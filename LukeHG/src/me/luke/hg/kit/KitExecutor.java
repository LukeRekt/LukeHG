package me.luke.hg.kit;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.luke.hg.Main;

public abstract class KitExecutor {
	
	protected final Main plugin;

	protected String name;
	protected List<String> lore;
	protected Material material;
	protected ItemStack[] itens;
	
	
	public KitExecutor() {
		this.plugin = Main.getPlugin();
		
		this.name = "Nenhum";
		this.lore = Arrays.asList("");
		this.material = Material.AIR;
		this.itens = new ItemStack[36];
	}
	
	public String getName() {
		return name;
	}
	public List<String> getLore(){
		return lore;
	}
	public Material getMaterial() {
		return material;
	}
	public ItemStack[] getItens() {
		return itens;
	}
	public abstract void onPlayerInteract(PlayerInteractEvent e);
}
