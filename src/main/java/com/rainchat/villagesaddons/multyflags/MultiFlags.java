package com.rainchat.villagesaddons.multyflags;

import com.rainchat.villages.Villages;
import com.rainchat.villages.api.VillagesApi;
import com.rainchat.villages.data.VillageExtension;
import com.rainchat.villages.data.flags.FlagVillage;
import com.rainchat.villages.managers.FlagManager;
import com.rainchat.villagesaddons.multyflags.Listener.TrampleCrops;
import com.rainchat.villagesaddons.multyflags.Listener.Use;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public final class MultiFlags extends VillageExtension {

    public static MultiFlags instance;


    @Override
    public void onReload() {
    }

    @Override
    public void onSave() {
    }

    @Override
    public void initialize(Plugin plugin) {
        instance = this;
        VillagesApi api = Villages.getAPI();

        FlagManager flagManager = api.getFlagManager();


        // TRAMPLE_CROPS
        flagManager.addFlag(new FlagVillage("TRAMPLE_CROPS",new ItemStack(Material.DIAMOND_SWORD)));
        plugin.getServer().getPluginManager().registerEvents(new TrampleCrops(api.getVillageManage()),plugin);

        // USE
        flagManager.addFlag(new FlagVillage("USE",new ItemStack(Material.DIAMOND_SWORD)));
        plugin.getServer().getPluginManager().registerEvents(new Use(api.getVillageManage()),plugin);

    }

    public static MultiFlags getInstance() {
        return instance;
    }

}
