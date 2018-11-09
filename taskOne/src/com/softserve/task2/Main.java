package com.softserve.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ${JDEEK} on ${10.11.2018}.
 */
public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter begin of interval: ");
        int beginOfInterval = scanner.nextInt();
        System.out.println("Enter end of interval: ");
        int endOfInterval = scanner.nextInt();
        System.out.println("Enter count of threads: ");
        int countOfThreads = scanner.nextInt();
        int lastElement2 = endOfInterval / countOfThreads;

        ExecutorPrimeChecker checkers[] = new ExecutorPrimeChecker[countOfThreads];
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        List[] copies = new ArrayList[countOfThreads];
        ArrayList<Integer> list = new ArrayList<>();
        checkers[0] = new ExecutorPrimeChecker(beginOfInterval, lastElement2, list);
        executorService.execute(checkers[0]);
        for (int i = 1; i < countOfThreads; i ++){
            copies[0] = list;
            ArrayList<Integer> listsForThreads= new ArrayList<>();
            checkers[i] = new ExecutorPrimeChecker(lastElement2*i,lastElement2*i+lastElement2,listsForThreads);
            executorService.execute(checkers[i]);
            copies[i] = listsForThreads;
        }
        executorService.shutdown();
        System.out.println("Total primes:"+Arrays.toString(copies));
    }
}
