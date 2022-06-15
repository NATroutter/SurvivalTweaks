package net.natroutter.survivaltweaks.features.settings;

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
        return switch (this) {
            case CHAT -> "§cChat";
            case TITLE -> "§cTitle";
            case ACTION -> "§cAction bar";
        };
    }
}
