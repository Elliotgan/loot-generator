package edu.grinnell.csc207.LootGeneratorHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class pick a treasure dropped by a certain monster from /TreasureClassEx.txt
 */
public class Treasure {
    private ArrayList<String[]> treasures;

    /**
     * Instantiate by reading all treasures in /TreasureClassEx.txt and put them into an ArrayList
     * 
     * @param path
     * @throws FileNotFoundException
     */
    public Treasure(String path) throws FileNotFoundException {
        File file = new File(path + "/TreasureClassEx.txt");
        Scanner scan = new Scanner(file);
        this.treasures = new ArrayList<String[]>();
        while (scan.hasNextLine()) {
            String[] input = new String[4];
            String currentline = scan.nextLine();
            for (int iter = 0; iter < 4; iter++) {
                input[iter] = "";
            }
            int currentindex = 0;
            for (int iter = 0; iter < currentline.length(); iter++) {
                char currentchar = currentline.charAt(iter);
                if (currentchar == '\t') {
                    currentindex += 1;
                } else {
                    input[currentindex] += Character.toString(currentchar);
                }
            }
            treasures.add(input);
        }
        scan.close();
    }

    /**
     * Return a random item dropped by certain treasure class
     * 
     * @param tc
     * @return String that represents the dropped item
     * @throws IllegalArgumentException
     */
    public String dropTreasure(String tc) throws IllegalArgumentException {
        Random rand = new Random();
        String drop = tc;
        boolean runflag = true;
        boolean successflag = false;
        while (runflag == true) {
            runflag = false;
            for (int iter = 0; iter < treasures.size(); iter++) {
                if (treasures.get(iter)[0].equals(drop)) {
                    int dropindex = (Math.abs(rand.nextInt()) % 3) + 1;
                    drop = treasures.get(iter)[dropindex];
                    runflag = true;
                    successflag = true;
                    break;
                }
            }
        }
        if (successflag == false) {
            throw new IllegalArgumentException("Invalid Treasure Class");
        }
        return drop;
    }

}
