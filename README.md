# find-pair
An app which finds 2 distinct items whose sum is minimally under a gift card balance

## Features 
message-hash-store has the following features:
* Hash and store a message
* Retrieve a message previously stored by Hash

## How to run this app:
### Build the app:
#### 1. Running scripts
```
$ cd find-pair
$ ./build.sh
```
#### 2. Manually
```
$ cd find-pair
$ mvn package
```

### Run the app:
Examples:
```
$ ./find-pair.sh prices.txt 2500
Candy Bar 500, Earmuffs 2000

$ ./find-pair.sh prices.txt 1100
Not possibe
```

## Performance & Scaling:
As the file's store items come already sorted, the time complexity is only O(n), where n is the number of items in the file. The reason for that is that due to 2 pointers progressing from each side until they meet, we would only go over the entire list items once. The space complexity is also O(n) as we need to save all items in memory.

In case the file is so large so that all items cannot be saved in memory at once, algorithm will remain the same but it will take first a part from the end of the file and a part from the file start and apply the algorithm first on this list, and then will apply it gradually on smaller portions from each side accordingly.

## Bonus - three items:
In order to implement this we can still use the core algorithm from the two items, only that it will be applied with conjunction of another runner pointer that for each pointers progress will restart from index 0 and will look for a third item (not items[start], items[end]) which is <= card balance. Because this runner will run for each loop progress the time complexity will be O(n^2). 