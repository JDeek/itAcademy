package com.softserve.task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ${JDEEK} on ${11.11.2018}.
 */
public class FileReadingProcess {

    private String nameOfFile;

    public FileReadingProcess() {
    }

    public FileReadingProcess(String fileName) {
        this.nameOfFile = fileName;
    }

    public synchronized void fileRead(String fileName) throws IOException {

        while (fileName.equals("")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try (FileReader read = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(read)) {

             String line;
             StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
            }

            nameOfFile = stringBuilder.toString();
            notify();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public byte[] byteReceiving(){
        return nameOfFile.getBytes();
    }

    public synchronized void sequenceChecking(){
        try {
            wait();
            System.out.println("Sequence search method waiting for notify()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Search processing....");
        byte[] contentFile = byteReceiving();
        int counter = 0;
        int interrupt = -1;
        int currentElement = 0;
        int maxSequence = 1;
        for (int i = 1; i < contentFile.length; i++) {
            if (contentFile[i - 1] == contentFile[i]) {
                counter++;
                currentElement = contentFile[i];
                System.out.print(" "+counter+" ");
            } else if (counter > maxSequence) {
                maxSequence = counter;
                interrupt = currentElement;

            } else if (contentFile[i - 1] != contentFile[i]) {
                counter = 1;
            }
        }
        notify();
        System.out.println("Mximum: " + maxSequence + "  " + interrupt);
    }
}
