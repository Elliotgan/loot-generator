package edu.grinnell.csc207.lootgenerator;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.LootGeneratorHelper.Monster;
import edu.grinnell.csc207.LootGeneratorHelper.Armor;
import edu.grinnell.csc207.LootGeneratorHelper.PrefixSuffix;
import edu.grinnell.csc207.LootGeneratorHelper.Treasure;

public class Tests {
    private String dataset = "data/small";

    @Test
    public void monsterTest() throws FileNotFoundException {
        Monster monsterclass = new Monster(dataset);
        String[] monster = monsterclass.chooseMonster();
        assert (monster[0].equals("Hell Bovine"));
        assert (monster[1].equals("Cow"));
        assert (monster[2].equals("90"));
        assert (monster[3].equals("Cow (H)"));
    }

    @Test
    public void armorTest() throws FileNotFoundException {
        Armor armorclass = new Armor(dataset);
        int val0 = Integer.valueOf(armorclass.armorQuality("Leather Armor"));
        int val1 = Integer.valueOf(armorclass.armorQuality("Sun Spirit"));
        int val2 = Integer.valueOf(armorclass.armorQuality("Diadem"));

        assert (14 <= val0 && val0 <= 17);
        assert (98 <= val1 && val1 <= 147);
        assert (50 <= val2 && val2 <= 60);

    }

    @Test
    public void prefixSuffixTest() throws FileNotFoundException {
        PrefixSuffix prefixsuffixclass = new PrefixSuffix(dataset);
        String prefix = prefixsuffixclass.getPrefix()[0];
        String suffix = prefixsuffixclass.getSuffix()[0];

        assert (prefix.equals("Glorious")
                || prefix.equals("Brutal")
                || prefix.equals("Dragon's")
                || prefix.equals("Emerald")
                || prefix.equals("Glowing"));

        assert (suffix.equals("of Precision")
                || suffix.equals("of Regrowth")
                || suffix.equals("of the Tiger")
                || suffix.equals("of the Titan")
                || suffix.equals("of the Leech"));
    }

    @Test
    public void treasureTest() throws FileNotFoundException {
        Treasure treasureclass = new Treasure(dataset);

        String treasure = treasureclass.dropTreasure("Cow (H)");

        assert (treasure.equals("Quilted Armor")
                || treasure.equals("Buckler")
                || treasure.equals("Leather Armor")
                || treasure.equals("Embossed Plate")
                || treasure.equals("Sun Spirit")
                || treasure.equals("Fury Visor")
                || treasure.equals("Sacred Rondache")
                || treasure.equals("Mage Plate")
                || treasure.equals("Diadem"));
    }
}
