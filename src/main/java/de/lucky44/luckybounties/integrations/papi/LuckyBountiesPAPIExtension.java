package de.lucky44.luckybounties.integrations.papi;

import de.lucky44.luckybounties.LuckyBounties;
import de.lucky44.luckybounties.files.DebugLog;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class LuckyBountiesPAPIExtension extends PlaceholderExpansion {
    //region settings
    @Override
    public @NotNull String getIdentifier() { return "lb"; }

    @Override
    public @NotNull String getAuthor() { return "Lucky44"; }

    @Override
    public @NotNull String getVersion() { return "1.0.4"; }

    @Override
    public boolean persist(){ return true; }
    //endregion

    private final LuckyBounties plugin;

    public LuckyBountiesPAPIExtension(){
        DebugLog.info("[PAPI-INT] Connected to PlaceHolderAPI (Hopefully, no checks in place)");
        plugin = LuckyBounties.I;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params){
        String query = params.toLowerCase();
        String ret = "NAN";

        switch (query) {
            case "collected" -> ret = "" + LuckyBounties.I.fetchPlayer(player.getUniqueId()).collected;
            case "bounty" -> ret = "" + LuckyBounties.I.fetchPlayer(player.getUniqueId()).worth;
            case "top_bounty" -> ret = LuckyBounties.mostWorth == null ? "No one" : LuckyBounties.mostWorth.playerName;
            case "top_col" -> ret = LuckyBounties.mostCollected == null ? "No one" : LuckyBounties.mostCollected.playerName;
            case "top_eco_bounty" -> ret = LuckyBounties.ecoMostWorth == null ? "No one" : LuckyBounties.ecoMostWorth.playerName;
            case "eco_bounty_money" -> ret = "" + (LuckyBounties.I.getEcoBounty(player.getUniqueId()) == null ? 0 : LuckyBounties.I.getEcoBounty(player.getUniqueId()).moneyPayment);
            case "top_eco_bounty_money" -> ret = LuckyBounties.ecoMostWorth == null ? "No money amount on bounty ? this shouldn't be possible" : "" + LuckyBounties.ecoMostWorth.ecoWorth;
            case "all_eco_bounties_money" -> ret = LuckyBounties.I.getAllEcoBountyWorth();
        }

        return ret;
    }
}
