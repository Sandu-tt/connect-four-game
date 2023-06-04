package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        if (!board.isLegalMove(col)) return;
        System.out.println("Leagl move");
        board.updateMove(col, Piece.BLUE);
        board.getBoardUI().update(col, true);

        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.BLUE) {
            board.getBoardUI().notifyWinner(winner);
            return;
        }

        if (!board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }

    }
}

