import java.util.ArrayList;
public class CustomSearch {

    private static final boolean debug = false;

    private static void debug(String output) {
        if (debug)
            System.out.println(output);
    }

    /**
     * @param <T>
     * @param list
     * @param target
     * @param index
     * @return
     */
    private static <T extends Comparable<? super T>>
    int checkIndex(SortedList<T> list, T target, int index) {
        T item = list.get(index);
        debug(String.format("%d: %s\n", index, item));
        if (item == null) return -1;
        return item.compareTo(target); // negative if target > item
    }
    
    public static <T extends Comparable<? super T>>
    int locateItem(SortedList<T> list, T target)
    {
        debug("Looking for: " + target.toString());
        
        
        int comp = checkIndex(list, target, 0);
        
    
        int prev = 0;
        int current = 1;

        while (comp < 0 && current < list.getCount()) {
            prev = current;
            current *= 2;
            if (current >= list.getCount()){
                current = list.getCount() - 1;
            }
            comp = checkIndex(list, target, current);
            if (comp == 0) return current;
            if (current == list.getCount() - 1 && comp < 0) return -1;
        }
        
        return binarySearch(list, target, prev, current);
    }

    private static <T extends Comparable<? super T>>
    int binarySearch(SortedList<T> list, T target, int prev, int current) {
        int high = current;
        int low = prev;

        while (low <= high) {
            int mid = (high+low)/2;
            int comp = checkIndex(list, target, mid);
            if (comp > 0) high = mid - 1;
            else if (comp < 0) low = mid + 1;
            else return mid;
        }

        return -1;
    }
    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int x = 0; x < 800000; x += 2)
            a.add(x);
        SortedList<Integer> list = new SortedList<Integer>(a);


        int position = locateItem(list, 0);
        int queries = list.getCount();


        System.out.println(position);
        System.out.println(queries);

        for (int x = 800000; x < 1800000; x += 2)
            a.add(x);
        list = new SortedList<Integer>(a);

        position = locateItem(list, 15);
        queries = list.getCount();

        System.out.println(position);
        System.out.println(queries);

       


    }

}
