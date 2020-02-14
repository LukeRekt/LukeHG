package me.luke.hg.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luke.hg.Main;
import me.luke.hg.utils.ServerStage;

public class ForceIniciar implements CommandExecutor{
  private Main plugin;

  
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		this.plugin = Main.getPlugin();
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("finiciar")) {
			if(plugin.hasServerStage(ServerStage.INICIANDO)) {
				Bukkit.broadcastMessage("O Op " + p.getName() + " forcou o inicio da partida!");
				
				plugin.forceServerStage();
				return true;
			}else {
				p.sendMessage("nao pode");
				
			}		
		
			
		}else {
			sender.sendMessage("seila");
			return true;
		}
		return false;
	}

}
