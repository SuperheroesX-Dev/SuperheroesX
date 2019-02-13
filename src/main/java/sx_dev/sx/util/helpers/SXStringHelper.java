package sx_dev.sx.util.helpers;

import net.minecraft.util.text.TextFormatting;
import sx_dev.sx.util.Reference;
import sx_dev.sx.util.config.ModConfig;
import sx_dev.sx.util.handlers.EnumHandler;

import java.text.DecimalFormat;
import java.util.List;

import static net.minecraft.client.resources.I18n.format;

public abstract class SXStringHelper {
    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    public static String getFormattedNumber(int number) {
        return formatter.format(number);
    }


    public static String getStateText(boolean state) {
        String onOrOff = state ? TextFormatting.GREEN + localize("tooltip.on") : TextFormatting.RED + localize("tooltip.off");
        return TextFormatting.GOLD + localize("tooltip.state") + ": " + onOrOff;
    }

    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? TextFormatting.GREEN + localize("tooltip.enabled") : TextFormatting.RED + localize("tooltip.disabled");
        return TextFormatting.GOLD + localize("tooltip.hoverMode") + ": " + enabledOrDisabled;
    }

//	public static String getChargerStateText(boolean state) {
//		String onOrOff = state ? TextFormatting.GREEN + localize("tooltip.enabled") : TextFormatting.RED + localize("tooltip.disabled");
//		return TextFormatting.GOLD + localize("tooltip.chargerState") + ": " + onOrOff;
//	}
//
//	public static String getChargerRateText(int rate) {
//		String rateText = rate > 0 ? getFormattedNumber(rate) + " RF/t" : localize("tooltip.energy.none");
//		return TextFormatting.GOLD + localize("tooltip.chargerRate") + ": " + TextFormatting.GRAY + rateText;
//	}

    public static String getParticlesText(EnumHandler.ParticleType particle) {
        return TextFormatting.GOLD + localize("tooltip.particles") + ": " + TextFormatting.GRAY + localize("tooltip.particles." + particle.ordinal());
    }

    public static String getGUIText(String key) {
        return TextFormatting.AQUA.toString() + TextFormatting.ITALIC + localize("tooltip.packGUIKey", key);
    }

    public static String getHUDFuelText(String prefix, int percent, int fuel) {
        String text = "";
        if (!ModConfig.client.hud.minimalFuelHUD) {
            text += localize("gui.hud." + prefix + ".fuel") + ": ";
        }
        if (percent > 0) {
            text += getColoredPercent(percent) + "%";
        } else {
            text += TextFormatting.DARK_RED + localize("gui.hud.fuel.depleted");
        }
        if (ModConfig.client.hud.showExactFuelInHUD) {
            text += " (" + getFormattedNumber(fuel) + "RF" + ")";
        }
        return text;
    }

    public static String getHUDStateText(Boolean engine, Boolean hover, Boolean charger) {
        String text = "";
        if (engine != null) {
            text += (engine ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.engine") + TextFormatting.RESET;
        }
        if (hover != null) {
            if (engine != null) {
                text += TextFormatting.GRAY + " - ";
            }
            text += (hover ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.hover") + TextFormatting.RESET;
        }
        if (charger != null) {
            if (hover != null || engine != null) {
                text += TextFormatting.GRAY + " - ";
            }
            text += (charger ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.charger");
        }
        return text;
    }

    public static String getColoredPercent(int percent) {
        if (percent > 70) {
            return TextFormatting.GREEN.toString() + percent;
        } else if (percent > 40) {
            return TextFormatting.YELLOW.toString() + percent;
        } else if (percent > 10) {
            return TextFormatting.GOLD.toString() + percent;
        } else {
            return TextFormatting.RED.toString() + percent;
        }
    }

    public static String getShiftText() {
        return TextFormatting.GRAY + localize("tooltip.holdShift", TextFormatting.YELLOW.toString() + TextFormatting.ITALIC + localize("tooltip.holdShift.shift") + TextFormatting.RESET + TextFormatting.GRAY);
    }

    public static String getFuelText(int amount, int max, boolean infinite) {
        if (infinite) {
            return TextFormatting.GRAY + localize("tooltip.fuel.infinite.energy");
        }
        return TextFormatting.GRAY + getFormattedNumber(amount) + " / " + getFormattedNumber(max) + " RF";
    }

    public static boolean canShowDetails() {
        return !ModConfig.client.hud.holdShiftForDetails || StringHelper.isShiftKeyDown();
    }

    public static String localize(String unlocalized, Object... args) {
        return localize(unlocalized, true, args);
    }

    public static String localize(String unlocalized, boolean prefix, Object... args) {
        String toLocalize = (prefix ? Reference.PREFIX : "") + unlocalized;
        if (args != null && args.length > 0) {
            return format(toLocalize, args);
        } else {
            return format(toLocalize);
        }
    }

    public static void addDescriptionLines(List<String> list, String base, String color) {
        int i = 1;
        while (true) {
            String unlocalized = Reference.PREFIX + "tooltip." + base + ".description." + i;
            String localized = format(unlocalized);
            if (unlocalized.equals(localized)) {
                break;
            }
            list.add(color + localized);
            i++;
        }
    }

    public static void addDescriptionLines(List<String> list, String base) {
        addDescriptionLines(list, base, "");
    }
}
