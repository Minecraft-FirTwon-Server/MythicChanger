package cn.superiormc.mythicchanger.commands;

import cn.superiormc.mythicchanger.MythicChanger;
import cn.superiormc.mythicchanger.manager.*;
import cn.superiormc.mythicchanger.objects.ObjectCommand;
import org.bukkit.entity.Player;

public class SubReload extends ObjectCommand {


    public SubReload() {
        this.id = "reload";
        this.requiredPermission =  "mythicchanger." + id;
        this.onlyInGame = false;
        this.requiredArgLength = new Integer[]{1};
    }

    /* Usage:

    /prefix reload

     */
    @Override
    public void executeCommandInGame(String[] args, Player player) {
        MythicChanger.instance.reloadConfig();
        new ConfigManager();
        new ItemManager();
        new ChangesManager();
        new MatchItemManager();
        LanguageManager.languageManager.sendStringText(player, "plugin.reloaded");
    }

    @Override
    public void executeCommandInConsole(String[] args) {
        new ConfigManager();
        new ItemManager();
        new ChangesManager();
        new MatchItemManager();
        LanguageManager.languageManager.sendStringText("plugin.reloaded");
    }
}
