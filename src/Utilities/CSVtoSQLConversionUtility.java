package Utilities;

import Models.UfoSighting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This utility was created using the Java File Handling chapters sourced from https://www.w3schools.com/java/java_files.asp to research ways to read and write to files

//Run this utility to create an sql script file which can be executed to create the database required to run the main application
public class CSVtoSQLConversionUtility {
    /***
     * This method reads from a csv file and writes to a new SQL file that can be used to create the database
     * using insert statements for the data converted from the csv
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //csv file containing data to be converted to sql
        String csvSource = "src\\Models\\allSightingsScrubbed.csv";

        //destination file for sql script
        File sqlFile = new File("src\\Models\\create_UFO_Database.sql");
        FileWriter writer = new FileWriter(sqlFile);

        //list to store the ufo objects created from the csv
        List<UfoSighting> ufos = getUfosFromCSV(csvSource);

        //database naming
        String tableName = "allUfoSightings";
        String dbName = "COMP1011Java";

        writer.write("/**Create database**/\n" +
                "CREATE DATABASE IF NOT EXISTS "+dbName+";\n" +
                "/**Create table**/\n" +
                "USE "+dbName+";\n" +
                "DROP TABLE IF EXISTS "+tableName+";\n" +
                "CREATE TABLE IF NOT EXISTS "+tableName+"(\n" +
                "   sightingID  INT  NOT NULL PRIMARY KEY AUTO_INCREMENT\n" +
                "  ,city        VARCHAR(100) NOT NULL\n" +
                "  ,state       VARCHAR(25) NOT NULL\n" +
                "  ,country     VARCHAR(25) NOT NULL\n" +
                "  ,durationSec INTEGER  NOT NULL\n" +
                "  ,ufoShape    VARCHAR(30) NOT NULL\n" +
                ");\n" +
                "\n" +
                "/**This data was obtained using data modified from the National UFO Reporting Center sourced from https://www.kaggle.com/NUFORC/ufo-sightings**/\n");

        for (UfoSighting u: ufos){
            try {
                writer.write("INSERT INTO "+dbName+"(sightingID, city, state, country, durationSec, ufoShape) " +
                        "VALUES ("+u.getSightingID()+",'"+u.getCity()+"','"+u.getState()+"','"+u.getCountry()+"',"+u.getDurationSec()+",'"+u.getUfoShape()+"');\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.close();
    }

    /***
     * This method reads a csv file, splits its contents separated by commas, uses the values to create new UFOSighting objects, which are then stored in an arraylist.
     * @param csvSource
     * @return ufos arraylist containing a new ufo sighting from each line of the csv
     * @throws IOException
     */
    private static List<UfoSighting> getUfosFromCSV(String csvSource) throws IOException {
        List<UfoSighting> ufos = new ArrayList<>();

        File csvFile = new File(csvSource);
        Scanner reader = new Scanner(csvFile);

        //skip the header line
        reader.nextLine();

        //read file, line by line
        while (reader.hasNextLine())
        {
            String line = reader.nextLine();
            //split the line on commas into values and put in an array
            String[] values = line.split(",");
            //create a new ufo with values from the line
            UfoSighting ufo = createUFO(values);
            //add new ufo to the ufo list
            ufos.add(ufo);
            //set line to the next line before looping, so that line is null when the end of file is reached and loop terminates
        }
        reader.close();
        return ufos;
    }

    /***
     * This method creates a new UFOSighting object using the values from a single csv line
     * @param ufos
     * @return a new ufoSighting object
     */
    private static UfoSighting createUFO(String[] ufos) {
        int sightingID = Integer.parseInt(ufos[0]);
        String city = ufos[1];
        String state = ufos[2];
        String country = ufos[3];
        int durationSec = Integer.parseInt(ufos[4]);
        String ufoShape = ufos[5];

        return new UfoSighting(sightingID, city, state, country, durationSec, ufoShape);
    }
}
