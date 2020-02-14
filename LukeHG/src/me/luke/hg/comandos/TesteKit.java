package me.luke.hg.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luke.hg.Main;
import me.luke.hg.kit.KitSelector;

public class TesteKit implements CommandExecutor {

	private Main plugin;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kits")) {
			this.plugin = Main.getPlugin();
			
			Player p = (Player)sender;
			KitSelector kit = new KitSelector(p);
			kit.openInventory();
			return true;
		}
		
		return false;
	}

}
