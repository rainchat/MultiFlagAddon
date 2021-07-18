package com.rainchat.villagesaddons.multyflags.Listener;

import com.rainchat.villages.data.village.Village;
import com.rainchat.villages.managers.VillageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TrampleCrops implements Listener {

    private final VillageManager villageManager;

    public TrampleCrops(VillageManager villageManager) {
        this.villageManager = villageManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getAction() != Action.PHYSICAL) return;

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        Village playerVillage = villageManager.getVillage(player);
        Village entityVillage = villageManager.getVillage(block.getChunk());

        if (block.getType() == Material.FARMLAND) {

            if (playerVillage == entityVillage) return;

            if (entityVillage.hasPermission("TRAMPLE_CROPS", block.getLocation())) {
                event.setCancelled(true);
            }
        }
    }
}
