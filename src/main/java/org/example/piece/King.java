package org.example.piece;

import org.example.Color;
import org.example.Coordinates;

import java.util.Set;

public class King extends Piece {
    public King(Color color, Coordinates coordinates) {

        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return null;
    }
}
