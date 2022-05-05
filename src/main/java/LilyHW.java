import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LilyHW {

    public int doHW(List<Integer> arr){
        int min,max,temp, count = 0, countReverse=0;
        List<Integer> tempArr = new ArrayList<>();
        tempArr.addAll(arr);
        for(int i=0;i<arr.size();i++) {
            boolean doCount= false;
            temp=arr.get(i);
            min = i;
            for( int j=i+1;j<arr.size();j++) { // find smallest
                if (arr.get(min) > arr.get(j)) {
                    min = j;
                    doCount = true;
                }
            }
            if(doCount){
                arr.set(i,arr.get(min));
                arr.set(min,temp);
                count++;
            }
        }
        for(int i=tempArr.size()-1;i>=0;i--) {
            boolean doCount= false;
            temp=tempArr.get(i);
            max = i;
            for( int j=i-1;j>=0;j--) {
                if (tempArr.get(max) > tempArr.get(j)) {
                    max = j;
                    doCount = true;
                }
            }
            if(doCount){
                tempArr.set(i,tempArr.get(max));
                tempArr.set(max,temp);
                countReverse++;
            }
        }
        return count > countReverse ? countReverse : count;
    }

    public int doHW2(List<Integer> arr) {

        Map<Integer, Integer> indexMap1 = new HashMap<>();
        Map<Integer, Integer> indexMap2 = new HashMap<>();
        List<Integer> tempArr = new ArrayList<>();
        List<Integer> sortedArrNormal = new ArrayList<>();
        List<Integer> sortedArrReverse = new ArrayList<>();
        tempArr.addAll(arr);
        sortedArrNormal.addAll(arr);
        sortedArrReverse.addAll(arr);
        Collections.sort(sortedArrNormal);
        Collections.sort(sortedArrReverse, Comparator.reverseOrder());
        // mapping orignal index Integer list
        indexMap1 = arr.stream().collect(
                Collectors.toMap(
                        (entry) -> entry,
                        arr::indexOf
                )
        );
        indexMap2.putAll(indexMap1);
        int swapNormal = findSwap(arr, indexMap1, sortedArrNormal);
        int swapReverse = findSwap(tempArr, indexMap2, sortedArrReverse);

        return Math.min(swapNormal,swapReverse);
    }

    private int findSwap(List<Integer> unsortArr, Map<Integer, Integer> indexMap, List<Integer> sortedArr) {
        int swap = 0;
        BiFunction<Integer, Integer, Boolean>  findMisMatchIndex = (x, y) -> x != y;
        for (int i = 0; i < unsortArr.size(); i++) {
            if (findMisMatchIndex.apply(unsortArr.get(i), sortedArr.get(i))) {
                swap++;
                int j = indexMap.get(sortedArr.get(i));
                indexMap.put(unsortArr.get(i), j);
                indexMap.put(unsortArr.get(j), i);
                swap(i, j, unsortArr);
            }
        }
        return swap;
    }

    public static void swap(int x, int y, List<Integer> arr) {
        int temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }
}
