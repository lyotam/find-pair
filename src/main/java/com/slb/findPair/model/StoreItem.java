package com.slb.findPair.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreItem {

    @NonNull
    private String name;

    @NonNull
    private int price;
}
