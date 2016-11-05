package jlppc.regimys.evolution;

import jlppc.regimys.objects.Espece;
import jlppc.regimys.objects.Pokemon;
import jlppc.regimys.objects.items.Item;

public class E_TradeItem extends Evolution {
	
	protected Item forEvolve;
	
	
	public E_TradeItem(int evo, Item forEvolve) {
		super(evo);
		this.forEvolve = forEvolve;
	}

	@Override
	public boolean checkEvolve(Pokemon toCheck) {
		return toCheck.itemHeld().equals(forEvolve);
	}
}
