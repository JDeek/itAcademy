package com.softserve.task2;

import java.util.List;

/**
 * Created by ${JDEEK} on ${10.11.2018}.
 */
public class ExecutorPrimeChecker implements Runnable {
    private int beginOfInterval;
    private int endOfInterval;
    private List<Integer> primeList;

    public ExecutorPrimeChecker(int beginOfInterval, int endOfInterval, List<Integer> primeList) {
        this.beginOfInterval = beginOfInterval;
        this.endOfInterval = endOfInterval;
        this.primeList = primeList;
    }
    @Override
    public void run() {
        while (beginOfInterval <= endOfInterval){
            boolean isPrime = primeChecker(beginOfInterval);
            if (isPrime){
                primeList.add(beginOfInterval);
            }
            beginOfInterval++;
        }
    }

    public boolean primeChecker(int probablePrime){
        for (int i=2; i<=probablePrime/2; i++) {
            int temp = probablePrime % i;
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }


}
