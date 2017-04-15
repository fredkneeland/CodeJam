import java.io.*;

/**
 * Created by fredkneeland on 4/8/17.
 */
public class ProblemC {

    public static void main(String[] args) {

        // The name of the file to open.
        String fileName = "./src/problemCInput.txt";

        // This will reference one line at a time
        String line = null;

        int count = -1;
        int[][] stalls = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int counter = 0;

            while((line = bufferedReader.readLine()) != null) {
                if (count < 0) {
                    count = Integer.parseInt(line);
                    stalls = new int[count][2];
                } else {
                    stalls[counter][0] = Integer.parseInt(line.split(" ")[0]);
                    stalls[counter][1] = Integer.parseInt(line.split(" ")[1]);
                    counter++;
                }
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


        // write it out
        // The name of the file to open.
        fileName = "outputC.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < stalls.length; i++) {
                bufferedWriter.write("Case #" + (i + 1));
                bufferedWriter.write(": ");

                int[] result = getStalls(stalls[i][0], stalls[i][1]);

                bufferedWriter.write("" + result[0] + " " + result[1]);

                if (i+1 < stalls.length) {
                    bufferedWriter.newLine();
                }
            }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public static void printBoolean(boolean[] bools) {
        System.out.println();
        for (int i = 0; i < bools.length; i++) {
            System.out.print(bools[i]);
        }
        System.out.println();
    }

    public static int[] getStalls(int stallCount, int people) {
        boolean[] stalls = new boolean[stallCount + 2];
        stalls[0] = true;
        stalls[stallCount + 1] = true;

        int[] results = new int[2];

//        System.out.println("getStalls");

        for (int i = 0; i < people; i++) {
            if ((i + 1) == people) {
                results = getPlaceInStall(stalls);
            } else {
                stalls = updateStalls(stalls);
            }
//            printBoolean(stalls);
        }

        return results;
    }

    public static boolean[] updateStalls(boolean[] stalls) {
        int[] result = getPlaceInStall(stalls);

        stalls[result[2]] = true;
        return stalls;
    }

    // 0 -> Max(Ls, Rs)
    // 1 -> Min(Ls, Rs)
    // 2 -> stall
    public static int[] getPlaceInStall(boolean[] stalls) {
        int indexOfLastFilledStall = 0;
        int currentBiggestGap = 0;
        int indexOfStartOfBiggestGap = 0;

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i]) {
                int gap = i - indexOfLastFilledStall;
                if (gap > currentBiggestGap) {
                    currentBiggestGap = gap;
                    indexOfStartOfBiggestGap = indexOfLastFilledStall;

                }
                indexOfLastFilledStall = i;
            }
        }

        int count = 0;
        for (int i = indexOfStartOfBiggestGap + 1; !stalls[i]; i++) {
            count++;
        }

//        System.out.println("count: " + count);

        int index = indexOfStartOfBiggestGap + count/2;

//        System.out.println("index: " + index);

        int[] results = new int[3];

        if (count % 2 == 1) {
            results[1] = count / 2;
            index++;
        } else {
            results[1] = count / 2 - 1;
        }
        results[0] = count / 2;
        results[2] = index;
        return results;

    }

//    public static int divideUpStalls(boolean[] stalls, int peopleLeft, int start, int stop) {
//        peopleLeft--;
//
//        int index = start + (start-stop) / 2;
//
//        stalls[start + (start-stop) / 2] = true;
//
//        if (peopleLeft > 0) {
//
//        } else {
//            return index;
//        }
//    }
}
