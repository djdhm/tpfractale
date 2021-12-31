package org.openjfx;

import org.apache.commons.cli.*;

import java.util.Locale;

public class SystemInfo {

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}