package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemExtractor;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;
import com.vivadaylight3.myrmecology.item.ItemMyrmecologyBook;
import com.vivadaylight3.myrmecology.item.ItemMyrmopaedia;
import com.vivadaylight3.myrmecology.util.Log;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemAnt antForest = new ItemAnt.Forest(Ants.forest),
	    antDesert = new ItemAnt.Desert(Ants.desert),
	    antSwamp = new ItemAnt.Swamp(Ants.swamp),
	    antPlains = new ItemAnt.Plains(Ants.plains),
	    antStone = new ItemAnt.Stone(Ants.stone),
	    antJungle = new ItemAnt.Jungle(Ants.jungle),
	    antCommon = new ItemAnt.Common(Ants.common), antX = new ItemAnt.X(
		    Ants.x), antBarbaric = new ItemAnt.Barbaric(Ants.barbaric),
	    antPlentiful = new ItemAnt.Plentiful(Ants.plentiful),
	    antCultivator = new ItemAnt.Cultivator(Ants.cultivator),
	    antMason = new ItemAnt.Mason(Ants.mason),
	    antCarpenter = new ItemAnt.Carpenter(Ants.carpenter),
	    antLeafcutter = new ItemAnt.Leafcutter(Ants.leafcutter),
	    antScavenger = new ItemAnt.Scavenger(Ants.scavenger),
	    antPlanter = new ItemAnt.Planter(Ants.planter),
	    antHostile = new ItemAnt.Hostile(Ants.hostile),
	    antSprouter = new ItemAnt.Sprouter(Ants.sprouter),
	    antHarvester = new ItemAnt.Harvester(Ants.harvester),
	    antRancher = new ItemAnt.Rancher(Ants.rancher),
	    antSlaughterer = new ItemAnt.Slaughterer(Ants.slaughterer);

    public static ItemMyrmecology extractor = new ItemExtractor(),
	    myrmopaedia = new ItemMyrmopaedia(),
	    book = new ItemMyrmecologyBook();

    public static void addItem(final ItemMyrmecology item) {
	GameRegistry.registerItem(item, item.name);
    }

    public static void init() {
	Log.info("Init ModItems");

	addItem(extractor);
	addItem(myrmopaedia);
	addItem(book);

	// Tier 1
	addItem(antForest);
	addItem(antDesert);
	addItem(antJungle);
	addItem(antPlains);
	addItem(antStone);
	addItem(antSwamp);
	addItem(antCommon);

	// Tier 2
	addItem(antBarbaric);
	addItem(antCultivator);
	addItem(antMason);
	addItem(antPlentiful);

	// Tier 3
	addItem(antHarvester);
	addItem(antHostile);
	addItem(antLeafcutter);
	addItem(antPlanter);
	addItem(antRancher);
	addItem(antScavenger);
	addItem(antSlaughterer);
	addItem(antSprouter);
    }

}
