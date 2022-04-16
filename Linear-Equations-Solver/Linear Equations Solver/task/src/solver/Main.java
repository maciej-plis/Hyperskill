package solver;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String fileIn = getParameterFromArgs(args, "-in");
        if(fileIn == null)
            fileIn = "input.txt";

        String fileOut = getParameterFromArgs(args, "-out");
        if(fileOut == null)
            fileOut = "output.txt";

        Matrix matrix = new Matrix(fileIn);
        LinearEquation lEquation = new LinearEquation(matrix);

        ArrayList<Double> unknowns = lEquation.solve();

        if(unknowns == null) {
            String message = lEquation.specialCase();
            System.out.println(message);
            writeSolutionToFile(fileOut, message);
        } else {
            printSolution(unknowns);
            writeSolutionToFile(fileOut, unknowns);
        }
    }

    public static void printSolution(ArrayList<Double> solution) {
        System.out.print("The solution is: (");

        for(int i=0; i<solution.size(); i++) {
            System.out.print(solution.get(i));
            if(i == solution.size()-1) {
                System.out.println(")");
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void writeSolutionToFile(String fileName, ArrayList<Double> solution) {
        try(PrintWriter writer = new PrintWriter(fileName)) {
            for (double num: solution) {
                writer.println(num);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Saved to " + fileName);
    }

    public static void writeSolutionToFile(String fileName, String toWrite) {
        try(PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(toWrite);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Saved to " + fileName);
    }

    public static String getParameterFromArgs(String[] args, String searched) {
        for(int i=0; i<args.length; i++) {
            if(args[i].equals(searched)) {
                return args[i+1];
            }
        }

        return null;
    }
}