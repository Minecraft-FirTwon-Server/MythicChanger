package cn.superiormc.mythicchanger.objects.changes;

import cn.superiormc.mythicchanger.manager.ConfigManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddFirColorChanger extends AbstractChangesRule {

    public AddFirColorChanger() {
        super();
    }

    @Override
    public ItemStack setChange(ConfigurationSection section, ItemStack original, ItemStack item, Player player, boolean fakeOrReal) {
        boolean apply = section.getBoolean("add-fir-color-changer", false);
        if (!apply) return item;
        if (item.getType() != Material.LEATHER_HORSE_ARMOR) return item;

        // 获取最大耐久度和当前损坏度。
        NBTItem nbtItem = new NBTItem(item.clone());
        Integer currentDamage = nbtItem.getInteger("CurrentDamage");
        Integer maxDurability = nbtItem.getInteger("MaxDurability");

        // 计算耐久度当前处于什么阶段，计算出耐久度的颜色值。
        int durabilityStage = (int) Math.ceil((maxDurability - currentDamage) / (maxDurability / 13.0));
        int colorValue = durabilityStage + 16777200;

        // 设置耐久度的color值。
        nbtItem.setInteger("display.color", colorValue);

        // 保存修改后的NBT数据。
        return nbtItem.getItem();
    }

    @Override
    public int getWeight() {
        return ConfigManager.configManager.getRuleWeight("add-fir-color-changer", 201);
    }

    @Override
    public boolean getNeedRewriteItem(ConfigurationSection section) {
        return true;
    }

    @Override
    public boolean configNotContains(ConfigurationSection section) {
        return section.get("add-fir-color-changer") == null;
    }
}
