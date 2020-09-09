package com.slb.findPair.utils;

import com.slb.findPair.model.StoreItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemFileParser {

    public static List<StoreItem> parseItems(String filename) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(str -> str.split(",[ ]+"))
                    .map(parts -> new StoreItem(parts[0], Integer.parseInt(parts[1])))
                    .collect(Collectors.toList());
        }
    }
}
