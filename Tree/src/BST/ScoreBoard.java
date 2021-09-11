package BST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

//Implements 2 APIs, add() and print(), when print, it should be sorted
//First of all, clarify the relative frequency of the 2 APIs.
class ScoreBoardS1 {
    //scenario 1. Add 10000 to print 1. Using ArrayList since add - O(1)
    //print O(n)
    List<Integer> scores;

    public ScoreBoardS1() {
        scores = new ArrayList<>();
    }

    public void add(Integer score) {
        this.scores.add(score);
    }

    public void print() {
        Collections.sort(scores);
        System.out.println(scores);
    }
}

class ScoreBoardS2 {
    //scenario 2. Add 1 to print 10000. Using sorted ArrayList(find the place to insert at first)
    //Add O(n), print O(n)
    List<Integer> scores;

    public ScoreBoardS2() {
        scores = new ArrayList<>();
    }

    public void add(Integer score) { //sorted array, iterating the array,
        // every time we insert the elements to the right positon(keep the array sorted) where first time less than the current idx element
        int position = scores.size();
        for (int i = 0; i < scores.size(); i++) {
            if (score < scores.get(i)) {
                position = i;
                break;
            }
        }
        scores.add(position, score);
    }

    public void print() {
        System.out.println(scores);
    }
}

class ScoreBoardS3 { //Note : duplicate elements gonna be lost
    //scenario 3. Add 1 to print 1. Using BST add O(lgn), print O(n)
    TreeSet<Integer> scores;

    public ScoreBoardS3() {
        scores = new TreeSet<>();
    }

    public void add(Integer score) {
        scores.add(score);
    }

    public void print() {
        System.out.println(scores);
    }
}
