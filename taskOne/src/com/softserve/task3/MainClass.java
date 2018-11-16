package com.softserve.task3;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ${JDEEK} on ${11.11.2018}.
 */
public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileReadingProcess process = new FileReadingProcess();
        ByteSequenceSearch search = new ByteSequenceSearch(process);
        new Thread(search).start();
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        process.fileRead(filePath);
    }
}
