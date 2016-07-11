package com.mora.batch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BatchProcessor {

    public String runBatFile(String filepath) {
        String s = "";
        try {
            String line;
            Process p = Runtime.getRuntime().exec(filepath);
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = bri.readLine()) != null) {
                System.out.println(line);
                s = s + line + ",";
            }
            bri.close();
            p.waitFor();

            if ((String.format("%02d", p.exitValue()).equals("02"))) {
                s = s + "Exited with error code 02,and now it is after midi";
            } else {
                s = s + "Exited with error code 01,and now it is before midi";
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return s;

    }
}
