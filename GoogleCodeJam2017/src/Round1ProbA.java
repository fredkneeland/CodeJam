/**
 * Created by fredkneeland on 4/14/17.
 */
public class Round1ProbA {

    public static void main(String[] args) {
        String[] test = new String[3];

        test[0] = "GGS";
        test[1] = "GGS";
        test[2] = "CSS";

        Round1ProbA solver = new Round1ProbA();

//        solver.solve(null);
    }

    public String[] solve(String[] grid) {
        Letter[] letters = new Letter[grid.length * grid[0].length()];
        int currentIndex = 0;


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                Character character = grid[i].charAt(j);

                if (character != '?') {
                    letters[currentIndex] = new Letter(grid, i, j, character);
                    letters[currentIndex].computeRects();
                }
                currentIndex++;
            }
        }

        int totalNumberOfRects = 0;

        for (int i = 0; i < letters.length; i++) {
            totalNumberOfRects += letters[i].possibleRectCount;
        }


        // add cut out for all rects already checked
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].possibleRectCount; j++) {
                for (int k = 0; k < totalNumberOfRects; k++) {

                }
            }
        }

    }

    public class Letter {
        int x, y;
        String[] grid;
        int[][] possibleRects;
        int possibleRectCount = 0;
        Character us;

        public Letter(String[] grid, int x, int y, Character us) {
            this.grid = grid;
            this.x = x;
            this.y = y;
            possibleRects = new int[grid.length * grid[0].length()][4];
            this.us = us;
        }

        public void computeRects() {
            int minX = x;
            int maxX = x;
            int minY = y;
            int maxY = y;

            // find maxX
            for (int i = x; i < grid[y].length(); i++) {
                Character character = grid[y].charAt(i);
                if (character != us && character != '?') {
                    maxX = i-1;
                    break;
                } else if (i + 1 == grid[y].length()) {
                    maxX = i;
                }
            }

            // find minX
            for (int i = x; i > 0; i--) {
                Character character = grid[y].charAt(i);
                if (character != us && character != '?') {
                    minX = i+1;
                    break;
                } else if (i + 1 == grid[y].length()) {
                    minX = 0;
                }
            }

            // find maxX
            for (int i = y; i < grid.length; i++) {
                Character character = grid[i].charAt(x);
                if (character != us && character != '?') {
                    maxY = i-1;
                    break;
                } else if (i + 1 == grid[y].length()) {
                    maxY = i;
                }
            }

            // find minY
            for (int i = y; i > 0; i--) {
                Character character = grid[i].charAt(x);
                if (character != us && character != '?') {
                    minY = i+1;
                    break;
                } else if (i + 1 == grid[y].length()) {
                    minY = 0;
                }
            }

            for (int i = minX; i < maxX; i++) {
                for (int j = minY; j < maxY; j++) {
                    for (int k = minX; k < maxX; k++) {
                        for (int l = minY; l < maxY; l++) {
                            // see if we make a valid rect
                            int rectMinX = k;
                            int rectMaxX = i;
                            int rectMinY = l;
                            int rectMaxY = j;

                            if (i < k) {
                                rectMinX = i;
                                rectMaxX = k;
                            }

                            if (j < l) {
                                rectMinY = j;
                                rectMaxY = l;
                            }

                            if (isRectangleCorrect(grid, rectMinX, rectMaxX, rectMinY, rectMaxY, us)) {
                                possibleRects[possibleRectCount] = new int[]{
                                        rectMinX, rectMaxX, rectMinY, rectMaxY
                                };
                                possibleRectCount++;
                            }
                        }
                    }
                }
            }
        }
    }


    public static String[] getRows(String[] start) {
        return null;
    }

    public static boolean isRectangleCorrect(String[] grid, int startX, int stopX, int startY, int stopY, Character character) {
        for (int i = startX; i < stopX; i++) {
            for (int j = startY; j < stopY; j++) {
                if (grid[i].charAt(j) != character) return false;
            }
        }
        return true;
    }


    /*
            public static boolean isValid(String[] grid) {
        int[][] solved = new int[grid.length][grid[0].length()];



        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (solved[i][j] != 1) {
                    // go out from here
                    Character character = grid[i].charAt(j);
                    Character current = character;
                    int k;
                    for (k = i; k < grid.length && current == character; k++) {
                        current = grid[k].charAt(j);
                    }

                    current = character;

                    int l;
                    for (l = j; l < grid[i].length() && current == character; l++) {
                        current = grid[i].charAt(l);
                    }

                    if (!isRectangleCorrect(grid, i, k-1, j, l-1, character)) {
                        return false;
                    }

                    for (int m = i; m < k; m++) {
                        for (int n = j; n < l; n++) {
                            solved[m][n] = 1;
                        }
                    }
                }
            }
        }
        return true;
    }

     */
}


