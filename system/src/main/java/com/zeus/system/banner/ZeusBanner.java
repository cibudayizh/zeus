package com.zeus.system.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;


/**
 * @author zhuzihang
 * @create 2021/12/28
 */
@Component
public class ZeusBanner implements Banner {








    private static final String[] BANNER = {"__________                   \n" +
            "\\____    /____  __ __  ______\n" +
            "  /     // __ \\|  |  \\/  ___/\n" +
            " /     / \\  ___/|  |  /\\___ \\ \n" +
            "/_______  \\___  >____//____  > "};
    private static final String MUSES_VERSION = " :: Zeus Version :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (String line : BANNER) {
            out.println(AnsiOutput.toString(AnsiColor.GREEN, line,
                    AnsiStyle.FAINT));
        }
        String musesVersion = ZeusVersion.getVersion();
        musesVersion = (musesVersion != null) ? " (v" + musesVersion + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (musesVersion.length() + MUSES_VERSION.length())) {
            padding.append(" ");
        }

        out.println(AnsiOutput.toString(AnsiColor.GREEN, MUSES_VERSION, AnsiColor.DEFAULT, padding.toString(),
                AnsiColor.RED, musesVersion));
        out.println();
    }
}
