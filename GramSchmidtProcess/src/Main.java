import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("This tool will allow you to generate orthogonal or orthonormal bases in the Euclidean Vector Space (R^n). \nTo begin, please input your basis as a set of vectors through 2d arrays. \nSpecify the dimension of your Inner product Space here: ");
        int n = input.nextInt();
        System.out.println("Please input " + n + " vectors as a space-separated list of numbers. Each vector should have size " + n + ". ");
        double basis[][] = new double[n][n];
        input.nextLine();
        for (int i = 1; i <= n; i++) {
            System.out.print("Vector #" + i + ": ");
            String s = input.nextLine();
            String[] s1 = s.split(" ");
            if (s1.length == n) {
                for (int j = 0 ; j < n; j++) {
                    basis[i - 1][j] = Integer.parseInt(s1[j]);
                }
            }
        }
        System.out.print("Type 'orthogonal' to generate an orthogonal basis or 'orthonormal' to generate an orthonormal base: ");
        boolean gonal = (input.next().equals("orthogonal"));
        double[][] newB;
        if (gonal) {
            newB = gonal(basis);
        }
        else {
            newB = normal(basis);
        }
        System.out.println("Here's the new basis: ");
        for (int i = 1; i <= n; i++) {
            System.out.print("Vector #" + i + ": ");
            for (int j = 0; j < n; j++ ) {
                System.out.print(newB[i - 1][j] + " ");
            }
            System.out.println();
        }
    }

    private static double[][] normal(double[][] basis) {
        double[][] h = gonal(basis);
        double[][] ans = new double[basis.length][basis.length];
        for (int r = 0; r < h.length; r++) {
            double norm = Math.sqrt(normSquared(h[r]));
            for (int c = 0; c < h.length; c++) {
                ans[r][c] = h[r][c] / norm;
            }
        }
        return ans;
    }

    private static double[][] gonal(double[][] basis) {
        double[][] w = new double[basis.length][basis.length];
        for (int r = 0; r < basis.length; r++) {
            double[] newR = new double[basis.length];
            for (int i = 0; i < basis.length; i++) {
                newR[i] = basis[r][i];
                for (int s = 1; s <= r; s++) {
                    newR[i] -= projection(basis[r], w[s - 1])[i];
                }
            }
            for (int i = 0; i < basis.length; i++) {
                w[r][i] = newR[i];
            }
        }
        return w;
    }

    private static double[] projection(double[] u, double[] v) {
        double[] proj = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            proj[i] = v[i] * (dotProduct(u, v) / normSquared(v));
        }
        return proj;
    }

    private static double dotProduct(double[] u, double[] v) {
        double ans = 0;
        for (int i = 0; i < u.length; i++) {
            ans += u[i] * v[i];
        }
        return ans;
    }

    private static double normSquared(double[] u) {
        double ans = 0;
        for (int i = 0; i < u.length; i++) {
            ans += u[i] * u[i];
        }
        return ans;
    }
}
