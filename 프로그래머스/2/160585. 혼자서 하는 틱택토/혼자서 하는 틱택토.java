class Solution {
    public int solution(String[] board) {
        int countO = 0;
        int countX = 0;

        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') {
                    countO++;
                } else if (c == 'X') {
                    countX++;
                }
            }
        }

        if (countO < countX) {
            return 0;
        }

        if (countO == countX) {
            if (gameOver(board, 'O')) {
                return 0;
            } else {
                return 1;
            }
        }

        if (countO == countX + 1) {
            if (!gameOver(board, 'X')) {
                return 1;
            }
        }

        return 0;
    }

    boolean gameOver(String[] board, char s) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == s && board[i].charAt(1) == s && board[i].charAt(2) == s) {
                return true;
            }
            if (board[0].charAt(i) == s && board[1].charAt(i) == s && board[2].charAt(i) == s) {
                return true;
            }
        }

        if (board[0].charAt(0) == s && board[1].charAt(1) == s && board[2].charAt(2) == s) {
            return true;
        }
        if (board[0].charAt(2) == s && board[1].charAt(1) == s && board[2].charAt(0) == s) {
            return true;
        }

        return false;
    }
}
