package com.rainchat.villagesaddons.multyflags.Listener;

import com.rainchat.villages.data.village.Village;
import com.rainchat.villages.managers.VillageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;
import org.bukkit.material.PressureSensor;

public class Use implements Listener {

    private final VillageManager villageManager;

    public Use(VillageManager villageManager) {
        this.villageManager = villageManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block == null) return;

        Village playerVillage = villageManager.getVillage(player);
        Village entityVillage = villageManager.getVillage(block.getChunk());


        if (block.getBlockData() instanceof Door
                || block.getBlockData() instanceof TrapDoor
                || block.getBlockData() instanceof Switch
                || block.getType().toString().contains("PRESSURE_PLATE")
        ) {

            if (playerVillage == entityVillage) return;

            if (entityVillage.hasPermission("USE", block.getLocation())) {
                event.setCancelled(true);
            }
        }
    }

}
