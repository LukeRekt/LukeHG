package me.luke.hg.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.luke.hg.Main;
import me.luke.hg.mapac.Bolo;
import me.luke.hg.mapac.Feast;
import me.luke.hg.utils.ServerStage;

public class GamerTaskTimer implements Runnable {

	private Main plugin;

	public GamerTaskTimer(Main plugin) {
		this.plugin = plugin;
	}
	
	

	@Override
	public void run() {
		if (plugin.hasServerStage(ServerStage.INICIANDO)
				&& Bukkit.getOnlinePlayers().size() >= plugin.MINIMO_JOGADORES) {
			plugin.TEMPO_INICIANDO -= 1;
			plugin.updateScoreboard();

			if (plugin.TEMPO_INICIANDO > 30 && plugin.TEMPO_INICIANDO % 60 == 0) {
				Bukkit.broadcastMessage(ChatColor.GREEN + "Faltam " + plugin.TEMPO_INICIANDO + " para iniciar o jogo!");
			} else if (plugin.TEMPO_INICIANDO == 30) {
				Bukkit.broadcastMessage("Game iniciando em 30 segundos!");
			} else if (plugin.TEMPO_INICIANDO > 0 && plugin.TEMPO_INICIANDO < 11) {
				Bukkit.broadcastMessage("Game iniciando em " + plugin.TEMPO_INICIANDO
						+ (plugin.TEMPO_INICIANDO == 1 ? " segundo!" : " segundos!"));
			} else if (plugin.TEMPO_INICIANDO == 0) {

				plugin.setServerStage(ServerStage.INVENCIBILIDADE);
				plugin.updateScoreboard();
				// falta fazer o resto
			}
		} else if (plugin.hasServerStage(ServerStage.INVENCIBILIDADE)) {
			plugin.TEMPO_INVENCIBILIDADE -= 1;
			plugin.updateScoreboard();
		}
		if (plugin.TEMPO_INVENCIBILIDADE == 0) {
			// Bukkit.broadcastMessage(ChatColor.RED + "Invencibilidade acabou");
			plugin.setServerStage(ServerStage.ANDAMENTO);
			plugin.TEMPO_JOGO += 1;
			plugin.updateScoreboard();

			if (plugin.TEMPO_JOGO == 10) {
				Feast feast = new Feast();
				Bukkit.broadcastMessage(ChatColor.RED + "o feast deu spawn em " + feast.feastLoc().toString());
				feast.loadFeast();

			}

		}
		if (plugin.TEMPO_FIM >= 1) {
			plugin.TEMPO_FIM += 1;
		}
		if (plugin.TEMPO_FIM == 15) {
		Bukkit.getServer().shutdown();
		}

		if (plugin.hasServerStage(ServerStage.ANDAMENTO) && plugin.playersInGame.size() == 1) {

			Player ganhador = Bukkit.getPlayer(plugin.playersInGame.get(0));

			plugin.setServerStage(ServerStage.ACABOU);
			plugin.updateScoreboard();
			if (plugin.hasServerStage(ServerStage.ACABOU) && plugin.TEMPO_FIM == 0) {
				plugin.TEMPO_FIM += 1;
				Bukkit.broadcastMessage("O player " + ganhador.getName() + "Ganhou o game!");
				Bolo bolor = new Bolo();
				bolor.loadBolo();
				ganhador.teleport(bolor.winloc);
				



			}

		}
		
	}
	
}

