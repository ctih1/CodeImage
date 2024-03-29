package xyz.ctih1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<String> lines;
    static List<String> orderedLines;
    static BufferedImage lastImage;
    public static int current;
    public static int total;
    public static long startTime;
    public static void main(String[] args) throws IOException, RateLimitException {
        if(args.length<5) {
            System.err.println("Invalid usage. java -jar <username> <reponame> <branch> <.filetype> <token> <(optional) type: maxHeight, minHeight");
            return;
        }
        System.out.println("[Main] starting...");
        startTime = System.currentTimeMillis();
        Image image = new Image();

        Code code = new Code();

        lines = code.get(args[0],args[1],args[2],args[3],args[4]);
        orderedLines = (lines.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList()));
        if(args.length>=6&&args[5].equalsIgnoreCase("maxHeight")) {
            System.out.println("[Main] Reversing list...");
            Collections.reverse(orderedLines);;
        }
        total = lines.size();
        current = 0;
        for(String line : orderedLines) {
            lastImage = image.create(line,lastImage);
        }
        image.export(lastImage);
    }
}