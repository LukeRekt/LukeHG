package me.luke.hg.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.luke.hg.kit.list.KangarooExecutor;

public enum Kit {

	
	KANGAROO(new KangarooExecutor());
	
	private KitExecutor kitExecutor;
	
	private Kit(KitExecutor kitExecutor) {
		this.kitExecutor = kitExecutor;
	}
	
	public KitExecutor getKitExecutor() {
		return kitExecutor;
	}
	
	public String getName() {
		return kitExecutor.getName();
	}
	public List<String> getLore(){
		return kitExecutor.getLore();
	}
	public Material getMaterial() {
		return kitExecutor.getMaterial();
	}
	public ItemStack[] getItens() {
		return kitExecutor.getItens();
	}
		
	}

