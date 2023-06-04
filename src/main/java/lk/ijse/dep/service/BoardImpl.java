package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private Piece [][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI=boardUI;
         pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int c = 0; c < pieces.length; c++) {
            for (int d = 0; d < pieces[c].length; d++) {
                pieces[c][d] = Piece.EMPTY;
            }

        }
    }



    @Override
    public BoardUI getBoardUI() {

        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for(int r=0; r<pieces[r].length;r++){
            if(pieces[col][r]==Piece.EMPTY){
                return r;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        int columnCount = findNextAvailableSpot(col);

        if (columnCount == -1) {
            return false;
        } else {
            return  true;
        }


    }



    @Override
    public boolean existLegalMoves() {
        boolean b = false;

        for (int c = 0; c < pieces.length; c++) {
            for (int d = 0; d < pieces[c].length; d++) {
                if (pieces[c][d] == Piece.EMPTY) {
                    b = true;
                }
            }
        }

        return b;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int columnCount = findNextAvailableSpot(col);

        pieces[col][findNextAvailableSpot(col)] = move;
    }




    @Override
    public Winner findWinner() {
        //Vertical Checking the Winner
        for (int i = 0; i < NUM_OF_COLS; i++) {
            int blueCount = 0;
            int greenCount = 0;
            int emptyCount = 0;

            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (pieces[i][j] == Piece.GREEN) {
                    greenCount += 1;
                    blueCount = 0;
                    emptyCount = 0;
                }
                if (pieces[i][j] == Piece.BLUE) {
                    blueCount += 1;
                    greenCount = 0;
                    emptyCount = 0;
                }
                if (pieces[i][j] == Piece.EMPTY) {
                    blueCount = 0;
                    greenCount = 0;
                    emptyCount += 1;
                }
                if (greenCount == 4 || blueCount == 4) {
                    Piece piece = greenCount == 4 ? Piece.GREEN : Piece.BLUE;
                    return new Winner(piece, i, i, j - 3, j);
                }
            }
        }

        //Horizontal Checking the winner
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            int blueCount = 0;
            int greenCount = 0;
            int emptyCount = 0;

            for (int j = 0; j < NUM_OF_COLS; j++) {
                if (pieces[j][i] == Piece.GREEN) {
                    greenCount += 1;
                    blueCount = 0;
                    emptyCount = 0;
                }
                if (pieces[j][i] == Piece.BLUE) {
                    blueCount += 1;
                    greenCount = 0;
                    emptyCount = 0;
                }
                if (pieces[j][i] == Piece.EMPTY) {
                    blueCount = 0;
                    greenCount = 0;
                    emptyCount += 1;
                }
                if (greenCount == 4 || blueCount == 4) {
                    Piece piece = greenCount == 4 ? Piece.GREEN : Piece.BLUE;
                    return new Winner(piece, j - 3, j, i, i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

    private int findAvailableRaws(int row){

        int count=0;
        for(int c=0; c<pieces.length;c++){
            if(pieces[c][row]==Piece.EMPTY){
                count++;
            }
        }


        return count;



    }
}
