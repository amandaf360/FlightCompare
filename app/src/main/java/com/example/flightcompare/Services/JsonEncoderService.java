package com.example.flightcompare.Services;

import android.util.Log;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JsonEncoderService {

    /**
     * Converts objects to Json
     * @param o Basic object representing the Json string
     * @return The given object as a Json string
     */
    public String objectToJson(Object o){
        Gson gson = new Gson();
        String json = gson.toJson(o);
        return json;
    }

    /**
     * Converts Json to a basic object type
     * @param s Json string to be converted
     * @param c Class type the function will convert the string to
     * @return Basic object type representing Json string
     */
    public Object stringToObject(String s, Class c){
        Gson gson = new Gson();
        Object o = gson.fromJson(s, c);
        return o;
    }

    /**
     * Takes a file name and converts the Json to a string
     * @param file File to read Json from
     * @return Basic object type representing Json string in the file
     */
    public String fileToString(String file){
        String path = new File("./Users/Colin/AndroidStudioProjects/FlightCompare/app/json/flights.json").getAbsolutePath();
        File fileObj = new File(path);
        Log.d("Reading JSON file", "File exists: " + fileObj.exists());
        Log.d("Reading JSON file", "File is directory: " + fileObj.isDirectory());
        Log.d("Reading JSON file", "File can read: " + fileObj.canRead());
        Log.d("Reading JSON file", "Current directory: " + path);
        Log.d("Reading JSON file", "The path is: " + file);
        try(Scanner in = new Scanner(new File(file))){
            StringBuilder sb = new StringBuilder();
            while(in.hasNextLine()){
                sb.append(in.nextLine());
                sb.append('\n');
            }
            in.close();

            return sb.toString();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
