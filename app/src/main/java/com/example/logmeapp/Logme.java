package com.example.logmeapp;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;

public class Logme {
    final String[] severity = {"EMERGENCY", "ALERT", "CRITICAL", "ERROR", "WARNING", "NOTIFICATION", "INFORMATIONAL", "DEBUGGING"};
    final String[] environments = {"DEBUGGING", "STAGING", "PRODUCTION"};

    final String EMERGENCY = "EMERGENCY";
    final String ALERT = "ALERT";
    final String CRITICAL = "CRITICAL";
    final String ERROR = "ERROR";
    final String WARNING = "WARNING";
    final String NOTIFICATION = "NOTIFICATION";
    final String INFORMATIONAL = "INFORMATIONAL";
    final String DEBUGGING = "DEBUGGING";

    final String STAGING = "STAGING";
    final String PRODUCTION = "PRODUCTION";

    private String session;
    private String tag;
    private String environment;
    private Context context;

    public Logme(Context context, String environment, String tag, String session) {
        this.session = session;
        this.tag = tag;
        this.environment = environment;
        this.context = context;
    }


    public void log(String severity, String message){
        String str = String.format("(%d) %d [%d] -%d: %d", severity, this.environment, this.session, this.tag, message);
        System.out.println(str);
    }

    private void writeFileOnInternalStorage(String filename, String body){
        File dir = new File(context.getApplicationContext().getFilesDir(), "logme");
        if(!dir.exists()){
            dir.mkdir();
        }

        try{
            File logfile = new File(dir, filename);
            FileWriter writer = new FileWriter(logfile);
            writer.append(body);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void emergency(String message){
        log(EMERGENCY, message);
    }

    public void alert(String message){
        log(ALERT, message);
    }

    public void warn(String message){
        log(WARNING, message);
    }

    public void nofify(String message){
        log(NOTIFICATION, message);
    }

    public void info(String message){
        log(INFORMATIONAL, message);
    }

    public void debug(String message){
        log(DEBUGGING, message);
    }
}

