package edu.grinnell.csc207.LootGeneratorHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class pick a random monster from /monstats.txt
 * 
 */
public class Monster {
    private ArrayList<String[]> monsters;

    /**
     * Instantiate by reading all monsters in /monstats.txt and put them into an ArrayList
     * 
     * @param path
     * @throws FileNotFoundException
     */
    public Monster(String path) throws FileNotFoundException {
        File file = new File(path + "/monstats.txt");
        Scanner scan = new Scanner(file);
        this.monsters = new ArrayList<String[]>();
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
            monsters.add(input);
        }
        scan.close();
    }

    /**
     * Choose a random monster from /monstats.txt, return its information
     * 
     * @return String array that contains all information of a random monster in /monstats.txt
     * in the form [Class, Type, Level, TreasureClass]
     */
    public String[] chooseMonster() {
        Random rand = new Random();
        String[] drop;
        int monsterindex = (Math.abs(rand.nextInt())) % monsters.size();
        drop = monsters.get(monsterindex);
        return drop;
    }

}
