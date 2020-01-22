package game;

public class Renderer {

    public String renderGameBoard(Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < board.getNumCols(); i++)
            sb.append(i);
        sb.append("\n");

        String[][] gameBoard = board.getBoardMatrix();
        for (int x = 0; x < gameBoard.length; x++) {
            sb.append(x + "|");
            for (int y = 0; y < gameBoard[x].length; y++) {
                if(Ship.SHIP.toString().equalsIgnoreCase(gameBoard[x][y])) {
                    sb.append(" ");
                } else {
                    sb.append(gameBoard[x][y]);
                }
            }
            sb.append("|" + x + "\n");
        }
        sb.append("  ");
        for (int i = 0; i < board.getNumCols(); i++)
            sb.append(i);
        sb.append("\n");

        return sb.toString();
    }

}
