package com.softserve.task3;

import java.io.BufferedReader;
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

        while (fileName.equals("")){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        FileReader read = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(read);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try{
            while ((line = reader.readLine()) !=null){
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        }catch (IOException e){
            reader.close();
        }

        nameOfFile =  stringBuilder.toString();
        notify();
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
        int k = 0;
        int per = -1;
        int kper = 0;
        int max = 1;
        for (int i = 1; i < contentFile.length; i++) {
            if (contentFile[i - 1] == contentFile[i]) {
                k++;
                kper = contentFile[i];
                System.out.print(" "+k+" ");
            } else if (k > max) {
                max = k;
                per = kper;
            } else if (contentFile[i - 1] != contentFile[i]) {
                k = 1;
            }
        }
        notify();
        System.out.println("Mximum: " + max + "  " + per);
    }
}
