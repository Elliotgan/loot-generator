package edu.grinnell.csc207.lootgenerator;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.LootGeneratorHelper.Armor;
import edu.grinnell.csc207.LootGeneratorHelper.Monster;
import edu.grinnell.csc207.LootGeneratorHelper.PrefixSuffix;
import edu.grinnell.csc207.LootGeneratorHelper.Treasure;

/**
 * 
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    /**
     * 1
     * 
     * @param args
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
        Random rand = new Random();
        System.out.println("This program kills monsters and generates loot!\n");
        Monster monsterclass = new Monster(DATA_SET);
        Treasure treasureclass = new Treasure(DATA_SET);
        Armor armorclass = new Armor(DATA_SET);
        PrefixSuffix prefixsuffixclass = new PrefixSuffix(DATA_SET);
        boolean runflag = true;
        Scanner scan = new Scanner(System.in);

        while (runflag) {
            boolean prefixflag = rand.nextBoolean();
            boolean suffixflag = rand.nextBoolean();

            String[] fightmonster = monsterclass.chooseMonster();
            String drop = treasureclass.dropTreasure(fightmonster[3]);
            String[] prefix = prefixsuffixclass.getPrefix();
            String[] suffix = prefixsuffixclass.getSuffix();
            String armorquality = armorclass.armorQuality(drop);

            System.out.println("Fighting " + fightmonster[1] + "...");
            System.out.println("You have slain " + fightmonster[1] + "!");
            System.out.println(fightmonster[1] + " dropped:");

            if (prefixflag) {
                drop = prefix[0] + " " + drop;
            }

            if (suffixflag) {
                drop = drop + " " + suffix[0];
            }
            System.out.println("");
            System.out.println(drop);
            System.out.println("Defense: " + armorquality);
            if (prefixflag) {
                System.out.println(prefix[1] + ": " + prefix[2]);
            }
            if (suffixflag) {
                System.out.println(suffix[1] + ": " + suffix[2]);
            }

            System.out.println("");
            boolean validflag;
            String arg;
            do {
                System.out.println("Fight again [y/n]?");
                arg = scan.next().toLowerCase();
                validflag = (arg.equals("y") || arg.equals("n"));
            } while (!validflag);

            if (arg.equals("n")) {
                runflag = false;
            } else {
                System.out.println("\n");
            }

        }
        scan.close();

    }
}
