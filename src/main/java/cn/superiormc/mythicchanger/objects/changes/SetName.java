package cn.superiormc.mythicchanger.objects.changes;

import cn.superiormc.mythicchanger.manager.ConfigManager;
import cn.superiormc.mythicchanger.manager.ErrorManager;
import cn.superiormc.mythicchanger.utils.TextUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetName extends AbstractChangesRule {

    public SetName() {
        super();
    }

    @Override
    public ItemStack setChange(ConfigurationSection section, ItemStack item, Player player, boolean fakeOrReal) {
        if (section.getString("set-name") == null) {
            return item;
        }
        if (fakeOrReal || !ConfigManager.configManager.getBoolean("ignore-fake-change-warning")) {
            ErrorManager.errorManager.sendErrorMessage("§x§9§8§F§B§9§8[MythicChanger] §6Warning: set-name rule has incompatibility issues with" +
                    " other packet based item plugins, it is recommend that remove it in fake changes from all your rule configs!" +
                    " If you want to ignore this warning, please disable warning display in config.yml file.");
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(TextUtil.parse(section.getString("set-name"), player));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public int getWeight() {
        return 7;
    }
}
