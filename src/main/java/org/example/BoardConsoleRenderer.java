package org.example;

import org.example.board.Board;
import org.example.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableMoveSquares = emptySet();
        if (pieceToMove != null){
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);}
        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighLight = availableMoveSquares.contains(coordinates);
                if (board.isSquareEmpty(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates, isHighLight);
                } else {
                    line += getPieceSprite(board.getPiece(coordinates), isHighLight);
                }
            }

            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public void render(Board board){
render(board, null);
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighLighted) {
        // format = background color + font color + text
        String result = sprite;

        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHighLighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighLight) {
        return colorizeSprite("   ", Color.WHITE, Board.isSquareDark(coordinates), isHighLight);
    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return "P";

            case "Knight":
                return "k";

            case "Bishop":
                return "B";

            case "Rook":
                return "R";

            case "Queen":
                return "Q";

            case "King":
                return "K";
        }

        return "";
    }

    private String getPieceSprite(Piece piece, boolean isHighLight) {
        return colorizeSprite(
                " " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates)
                , isHighLight
        );
    }
}
