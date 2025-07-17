package org.example;

import org.example.board.Board;
import org.example.piece.Piece;

import java.util.List;
import java.util.Set;

public class StalemateGameChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);
        for (Piece piece : pieces){
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (availableMoveSquares.size()>0){
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
