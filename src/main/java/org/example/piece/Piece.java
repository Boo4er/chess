package org.example.piece;

import org.example.board.Board;
import org.example.Color;
import org.example.Coordinates;

import java.util.HashSet;
import java.util.Set;

abstract public class Piece {
    public final Color color;
    public Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift shift : getPieceMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isSquareAvailableForMove(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }
        }

        return result;
    }

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();

    protected Set<CoordinatesShift> getPieceAttacks() {
        return getPieceMoves();
    }


    public Set<Coordinates> getAttackedSquares(Board board) {
        Set<CoordinatesShift> getPieceAttacks = getPieceAttacks();
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift pieceAttack : getPieceAttacks) {
            if (coordinates.canShift(pieceAttack)) {
                Coordinates shiftCoordinates = coordinates.shift(pieceAttack);
                if (isSquareAvailableForAttack(shiftCoordinates, board)) {
                    result.add(shiftCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
