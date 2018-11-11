package com.softserve.task3;

import java.io.IOException;

/**
 * Created by ${JDEEK} on ${11.11.2018}.
 */
public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileReadingProcess process = new FileReadingProcess();
        ByteSequenceSearch search = new ByteSequenceSearch(process);
        new Thread(search).start();

    }
}
