package com.slb.findPair;


import com.slb.findPair.model.StoreItem;
import com.slb.findPair.utils.ItemFileParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class FindPair {

    static void findPair(String filename, int balance) throws IOException {
        List<StoreItem> allItems = ItemFileParser.parseItems(filename);

        Optional.ofNullable(findMinPair(allItems, balance))
                .ifPresentOrElse(
                        items -> {
                            StringJoiner joiner = new StringJoiner(", ");
                            items.forEach(item -> joiner.add(item.getName() + " " + item.getPrice()));
                            System.out.print(joiner.toString());
                        },
                        () -> System.out.print("Not possible"));
    }

    static List<StoreItem> findMinPair(List<StoreItem> items, int balance) {
        List<StoreItem> res = null;
        int maxSum = Integer.MIN_VALUE;

        int start = 0, end = items.size() - 1;
        while (start < end) {
            int sum = items.get(start).getPrice() + items.get(end).getPrice();
            if (sum > balance) {
                end--;
            }
            else if (sum == balance) {
                res = Arrays.asList(items.get(start), items.get(end));
                break;
            }
            else {
                if (sum > maxSum) {
                    maxSum = sum;
                    res = Arrays.asList(items.get(start), items.get(end));
                }
                start++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            handleUsageError();
        }
        try {
            findPair(args[0], Integer.parseInt(args[1]));
        } catch (Exception e) {
            handleUsageError();
        }
    }

    private static void handleUsageError() {
        System.err.println("Usage: find-pair filename.txt balance; make sure formatted file exists");
        System.exit(2);
    }
}