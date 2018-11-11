package com.softserve.task3;

/**
 * Created by ${JDEEK} on ${11.11.2018}.
 */
public class ByteSequenceSearch implements  Runnable{
    FileReadingProcess readingProcess;
    public ByteSequenceSearch(FileReadingProcess readingProcess) {
        this.readingProcess = readingProcess;
    }

    @Override
    public void run() {
        readingProcess.sequenceChecking();
    }
}
