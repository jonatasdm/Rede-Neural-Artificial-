import java.lang.Math;

public class Matrix {
    int rows, cols;
    double[][] data;
    double[][] somaM;

    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];

        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.cols; j++){
                this.data[i][j] = 0;
            }
        }
    }

    //Funções diversas

    public void printMatrix(){
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.cols; j++){
                System.out.printf("%.2f ", this.data[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void printMatrix(double[][] A){
        for(int i = 0; i<A.length; i++){
            for(int j = 0; j<A[0].length; j++){
                System.out.printf("%.2f ", A[i][j]);
            }
            System.out.println();
        }
    }

    public static double[][] randomize(double[][] matriz){
        double[][] rd = new double[matriz.length][matriz[0].length];
        for(int i = 0; i<matriz.length; i++){
            for(int j = 0; j<matriz[0].length; j++){
                rd[i][j] = Math.random()*2-1;
            }
        }
        return rd;
    }

    public static double[][] transpose(double[][] A){
        double[][] matrix = new double[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[j][i] = A[i][j];
            }
        } 
        return matrix;
    }

    // Operações Estáticas Matriz x Escalar
    public static double[][] escalar_multiply(double[][] A, double escalar) {
        int l = A.length;
        int c = A[0].length;
        
        double[][] multM = new double[l][c];

       for (int i = 0; i < l; i++) {
           for (int j = 0; j < c; j++) {
               multM[i][j] = A[i][j] * escalar;
           }
       }
       return multM;        
    }

    // Operações Estáticas Matriz x Matriz

    public static double[][] hadamard(double[][] A, double[][] B) {
        int l = A.length;
        int c = A[0].length;
        
        double[][] productM = new double[l][c];

       for (int i = 0; i < l; i++) {
           for (int j = 0; j < c; j++) {
               productM[i][j] = A[i][j] * B[i][j];
           }
       }
       return productM;        
    }

    public static double[][] add(double[][] A, double[][] B) {
         int l = A.length;
         int c = A[0].length;
         
         double[][] somaM = new double[l][c];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                somaM[i][j] = A[i][j] + B[i][j];
            }
        }
        return somaM;        
    }

    public static double[][] subtract(double[][] A, double[][] B) {
        int l = A.length;
        int c = A[0].length;
        
        double[][] subM = new double[l][c];

       for (int i = 0; i < l; i++) {
           for (int j = 0; j < c; j++) {
               subM[i][j] = A[i][j] - B[i][j];
           }
       }
       return subM;        
    }   

    public static double[][] multiply(double[][] A, double[][] B) {
        int lA = A.length; // número de linhas da matriz A
        int cA = A[0].length; // número de colunas da matriz A
        int cB = B[0].length; // número de colunas da matriz B
        double[][] C = new double[lA][cB]; // matriz resultado
    
        for (int i = 0; i < lA; i++) {
            for (int j = 0; j < cB; j++) {
                double sum = 0;
                for (int k = 0; k < cA; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
    
        return C;
    }
    
    

    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;
    }
    public void setRows(int rows){
        this.rows = rows;
    }
    public void setCols(int cols){
        this.cols = cols;
    }
    public double[][] getData(){
        return this.data;
    }
    public void setData(double[][] data){
        this.data = data;
    }

}
