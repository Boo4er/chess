package org.example;

public class Main {
    public static void main(String[] args) {
//        Board board = new Board();
//        board.setupDefaultPiecesPositions();
        Board board = (new BoardFactory()).fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);
//
//        Piece piece = board.getPiece(new Coordinates(File.G, 8));
//        Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
//
//        int a = 123;

//        Game game = new Game(board);
//        game.gameLoop();
    }
}