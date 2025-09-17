package com.univer.mediamanager.model;

import java.util.ArrayList;
import java.util.List;

public enum Platform {
    ANDROID,
    IOS,
    MAC,
    PS4,
    PS5,
    STEAMDECK,
    VR,
    WINDOWS_PC,
    XBOX;

    public static List<String> getPlatformsAsStrings() {
        List<String> list = new ArrayList<>();
        for (Platform platform : Platform.values()) {
            String name = platform.name();
            list.add(name);
        }
        return list;
    }
}
