package com.softserve.task1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ${JDEEK} on ${03.11.2018}.
 */
public class IsPrimeChecker extends Thread {

    private int begin;
    private int end;
    private List<Integer> primeList;


    public IsPrimeChecker() {
    }
    public IsPrimeChecker(int begin, int end, List<Integer> primeList) {
        this.begin = begin;
        this.end = end;
        this.primeList = primeList;
    }

    @Override
    public void run() {
        while (begin <= end){
            boolean isPrime = primeChecker(begin);
            if (isPrime){
                primeList.add(begin);
            }
            begin += 1;
        }

        System.out.println("Checker #"+getName()+" has finished work!");
    }

    ///////CHECK PRIME NUMBERS:
    private boolean primeChecker(int num){
        for (int i=2; i<=num/2; i++) {
            int temp = num % i;
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }

    private void intervalDistribution(int beginOfInterval, int endOfInterval, int threadCount){
        int lastElement = endOfInterval/threadCount;

        IsPrimeChecker[] checkers = new IsPrimeChecker[threadCount];
        List[] copies = new ArrayList[threadCount];

        for(int i = 1; i < threadCount; i++){
            List<Integer> listForEvenThread = new ArrayList<>();
            checkers[i] = new IsPrimeChecker(lastElement*i,lastElement*i+lastElement,listForEvenThread);
            checkers[i].setName("Checker #"+i);
            copies[i] = listForEvenThread;
        }

        for(int i = 0; i < threadCount; i ++){
            List<Integer> listForEvenThread = new ArrayList<>();
            checkers[0] = new IsPrimeChecker(beginOfInterval,lastElement, listForEvenThread);
            checkers[i].start();
            checkers[0].run();
            copies[0] = listForEvenThread;
        }

        try {
            for (int i = 0 ; i < threadCount; i ++){
                checkers[i].join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Result: "+ Arrays.toString(copies));
    }

    public static void main(String[] args){
        ///////INPUT DATA:
        Scanner scanner = new Scanner(System.in);
        IsPrimeChecker checker = new IsPrimeChecker();
        System.out.println("Enter begin of interval: ");
        int beginOfInterval = scanner.nextInt();
        System.out.println("Enter end of interval: ");
        int endOfInterval = scanner.nextInt();
        System.out.println("Enter count of threads: ");
        int countOfThreads = scanner.nextInt();

        checker.intervalDistribution(beginOfInterval,endOfInterval,countOfThreads);
    }
}
