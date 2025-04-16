package edu.grinnell.csc207.LootGeneratorHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class pick random prefix and suffix from /MagicPrefix.txt and /MagixSuffix.txt
 */
public class PrefixSuffix {
    private ArrayList<String[]> prefixs;
    private ArrayList<String[]> suffixs;

    /**
     * Instantiate by reading all prefixs and suffixs in /MagicPrefix.txt and /MagixSuffix.txt, 
     * then put them into an ArrayList
     * 
     * @param path
     * @throws FileNotFoundException
     */
    public PrefixSuffix(String path) throws FileNotFoundException {
        File file = new File(path + "/MagicPrefix.txt");
        Scanner scan = new Scanner(file);
        this.prefixs = new ArrayList<String[]>();
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
            prefixs.add(input);
        }
        scan.close();

        file = new File(path + "/MagicSuffix.txt");
        scan = new Scanner(file);
        this.suffixs = new ArrayList<String[]>();
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
            suffixs.add(input);
        }
        scan.close();
    }

    /**
     * Return a random Prefix
     * 
     * @return String array that contains all necessary information of a Prefix
     * in the form [Name, mod1code, mod1val]
     */
    public String[] getPrefix() {
        Random rand = new Random();
        String[] prefix = new String[3];
        int prefixindex = (Math.abs(rand.nextInt())) % prefixs.size();
        prefix[0] = prefixs.get(prefixindex)[0];
        prefix[1] = prefixs.get(prefixindex)[1];
        int loval = Integer.valueOf(prefixs.get(prefixindex)[2]);
        int hival = Integer.valueOf(prefixs.get(prefixindex)[3]);
        int quality = loval + (Math.abs(rand.nextInt()) % (hival - loval + 1));
        prefix[2] = Integer.toString(quality);
        return prefix;
    }

    /**
     * Return a random Suffix
     * 
     * @return String array that contains all necessary information of a Suffix
     * in the form [Name, mod1code, mod1val]
     */
    public String[] getSuffix() {
        Random rand = new Random();
        String[] suffix = new String[3];
        int suffixindex = (Math.abs(rand.nextInt())) % suffixs.size();
        suffix[0] = suffixs.get(suffixindex)[0];
        suffix[1] = suffixs.get(suffixindex)[1];
        int loval = Integer.valueOf(suffixs.get(suffixindex)[2]);
        int hival = Integer.valueOf(suffixs.get(suffixindex)[3]);
        int quality = loval + (Math.abs(rand.nextInt()) % (hival - loval + 1));
        suffix[2] = Integer.toString(quality);
        return suffix;
    }

}
