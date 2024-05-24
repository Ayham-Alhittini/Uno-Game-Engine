package variations.junior;

import engine.cards.ColoredLabelCard;
import shared.constants.CardColor;

public class AnimalCard extends ColoredLabelCard {
    public AnimalCard(CardColor color, AnimalType animalType) {
        super(color, animalType.toString());
    }
}
