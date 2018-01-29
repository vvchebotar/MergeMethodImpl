package mergemethod;

import static java.lang.System.out;
import java.util.ArrayList;

/**
 * Условие:
 * реализовать метод static void merge(ArrayList a, ArrayList b) {}
 * метод соединяет отсортированные коллекции
 *
 * требования:
 *  - код должен работать
 *  - код должен быть эффективным (очень важно)
 *  - первая коллекция содержит обе коллекции
 *  - вторая коллекция остается неизменной
 */
public class MergeMethod {

    public static void main(String[] args) {
        ArrayList  listA = new ArrayList();
        ArrayList  listB = new ArrayList();

        int [] arrayA = {-4,-3,6,7,8,12,21,22};
        int [] arrayB = {3,4,6,8,11,13,22};

        clearAndFillIn(listA, listB, arrayA, arrayB);

        printBothArrays(listA, listB);

        merge(listA,listB);

        printBothArrays(listA, listB);
    }

    static void merge(ArrayList a, ArrayList b) {

        int firstElementFromA = (int) a.get(0);
        int firstElementFromB = (int) b.get(0);
        int lastElemenFromA = (int) a.get(a.size() - 1);
        int lastElementFromB = (int) b.get(b.size()-1);

        //if last element from "a" less than the first element from "b"
        if (lastElemenFromA <= firstElementFromB) {
            a.addAll(b);
            return;
        }

        //if first element from "a" bigger than the last element from "b"
        if (firstElementFromA>lastElementFromB) {
            a.addAll(0, b);
            return;
        }

        // binary search to find place to start adding
        int lowerBound = 0;
        int upperBound = a.size() - 1;
        int carriage = 0;
        boolean b1 = true;

        while (b1) {
            carriage = (lowerBound + upperBound) / 2;
            int elementFromA = (int) a.get(carriage);
            if (elementFromA == firstElementFromB) {
                b1 = false;
            } else  if (lowerBound > upperBound){
                b1 = false;
            } else {
                if(elementFromA < firstElementFromB) {
                    lowerBound = carriage + 1;
                } else {
                    upperBound = carriage - 1;
                }
            }
        }

        // inserting "b" to "a"
        int indexOfB = 0;
        int sizeOfB = b.size();

        for (int i = carriage; i < a.size(); i++) {
            //don't proceed, if elements from "b" is over.
            if (indexOfB == sizeOfB){
                break;
            }

            int elementFromB = (int)b.get(indexOfB);

            //don't proceed, if element from "b" bigger than the last element from "a"
            if (elementFromB > lastElemenFromA){
                break;
            }

            //find place for element from "b"
            if (elementFromB < (int) a.get(i)) {
                //insert element from "b"
                a.add(i, elementFromB);
                indexOfB++;
            }
        }

        //add all remained elements from "b" to "a"
        while (indexOfB < b.size()){
            a.add(b.get(indexOfB++));
        }
    }

    public static void printBothArrays(ArrayList  a, ArrayList  b){
        out.print("arrayA(size " + a.size()+ "): \t");
        for (Object i:a)
            out.print(i+" ");
        out.println();
        out.print("arrayB(size " + b.size()+ "): \t");
        for (Object i:b)
            out.print(i+ " ");
        out.println();
    }


    public static void clearAndFillIn(ArrayList  listA, ArrayList  listB, int[] arrayA, int[] arrayB){
        listA.clear();
        listB.clear();
        for (int i: arrayA)
            listA.add(i);
        for (int i: arrayB)
            listB.add(i);
    }
}
