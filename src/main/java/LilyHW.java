import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
}
