package net.natroutter.survivaltweaks.features.Settings;

import org.checkerframework.checker.units.qual.A;

import java.util.Locale;

public enum AlertMode {
    CHAT,
    TITLE,
    ACTION;

    public AlertMode next()  {
        return values()[(this.ordinal()+1) % values().length];
    }

    public static AlertMode fromString(String val) {
        if (val == null) {return CHAT;}
        if (val.equalsIgnoreCase(CHAT.name())) {
            return CHAT;
        } else if (val.equalsIgnoreCase(TITLE.name())) {
            return TITLE;
        } else if (val.equalsIgnoreCase(ACTION.name())) {
            return ACTION;
        }
        return CHAT;
    }

    public String lang() {
        switch (this) {
            case CHAT:return "§cChat";
            case TITLE:return "§cTitle";
            case ACTION:return "§cAction bar";
            default:return "";
        }
    }
}
