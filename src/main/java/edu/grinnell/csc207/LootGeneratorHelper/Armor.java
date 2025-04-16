package edu.grinnell.csc207.LootGeneratorHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class pick a random value for given armor from /armor.txt
 */
public class Armor {
    private ArrayList<String[]> armors;

    /**
     * Instantiate by reading all armors in /armor.txt and put them into an ArrayList
     * 
     * @param path
     * @throws FileNotFoundException
     */
    public Armor(String path) throws FileNotFoundException {
        File file = new File(path + "/armor.txt");
        Scanner scan = new Scanner(file);
        this.armors = new ArrayList<String[]>();
        while (scan.hasNextLine()) {
            String[] input = new String[3];
            String currentline = scan.nextLine();
            for (int iter = 0; iter < 3; iter++) {
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
            armors.add(input);
        }
        scan.close();
    }

    /**
     * Return a random value of given armor in the range given by /armor.txt
     * 
     * @param armor
     * @return String that represent the defence of the armor
     * @throws IllegalArgumentException
     */
    public String armorQuality(String armor) throws IllegalArgumentException {
        Random rand = new Random();
        String drop;
        for (int iter = 0; iter < armors.size(); iter++) {
            if (armors.get(iter)[0].equals(armor)) {
                int loval = Integer.valueOf(armors.get(iter)[1]);
                int hival = Integer.valueOf(armors.get(iter)[2]);
                int quality = loval + (Math.abs(rand.nextInt()) % (hival - loval + 1));
                drop = Integer.toString(quality);
                return drop;
            }
        }
        throw new IllegalArgumentException("Invalid armor");
    }

}
