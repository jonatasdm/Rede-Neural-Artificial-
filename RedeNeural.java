import java.lang.Math;
import java.util.Scanner;

public class RedeNeural {
    int i_nodes, h_nodes, o_nodes;
    double[][] bias_ih;
    double[][] bias_ho;
    double[][] weigth_ih;
    double[][] weigth_ho;
    double learning_rate;
    boolean train = true;

    public RedeNeural(int i_nodes,int h_nodes, int o_nodes){
        this.i_nodes = i_nodes;
        this.h_nodes = h_nodes;
        this.o_nodes = o_nodes;

        this.bias_ih = new double[this.h_nodes][1];
        this.bias_ih = Matrix.randomize(this.bias_ih);

        this.bias_ho = new double[this.o_nodes][1];
        this.bias_ho = Matrix.randomize(this.bias_ho);

        this.weigth_ih = new double[h_nodes][this.i_nodes];
        this.weigth_ih = Matrix.randomize(this.weigth_ih);       
    
        this.weigth_ho = new double[o_nodes][this.h_nodes];
        this.weigth_ho = Matrix.randomize(this.weigth_ho);

        this.learning_rate = 0.1;
        
    }

    public void network(double[][] input, double[][] target){
        //INPUT --> HIDDEN
        double[][] hidden = Matrix.multiply(this.weigth_ih, input);
        hidden = Matrix.add(hidden, this.bias_ih);
        hidden = sigmoid(hidden);
        
        //HIDDEN --> OUTPUT
        //d(Sigmoid) = Output * (1 - Output)
        double[][] output = Matrix.multiply(this.weigth_ho, hidden);
        output = Matrix.add(output, this.bias_ho);
        output = sigmoid(output);

        //BACKPROPAGATION

        // OUTPUT --> HIDDEN
        double[][] output_error = Matrix.subtract(target, output);
        double[][] d_output = dsigmoid(output);
        double[][] hidden_T = Matrix.transpose(hidden);

        double[][] gradient = Matrix.hadamard(d_output, output_error);
        gradient = Matrix.escalar_multiply(gradient, this.learning_rate);
        
        //Adjust Bias O--> H
        this.bias_ho = Matrix.add(this.bias_ho, gradient);
        //Adjust Weigths O --> H
        double[][] weigth_ho_deltas = Matrix.multiply(gradient, hidden_T);
        this.weigth_ho = Matrix.add(this.weigth_ho, weigth_ho_deltas);
        
        //HIDDEN --> INPUT
        double[][] weigth_ho_T = Matrix.transpose(this.weigth_ho);
        double[][] hidden_erro = Matrix.multiply(weigth_ho_T, output_error);
        double[][] d_hidden = dsigmoid(hidden);
        double[][] input_t = Matrix.transpose(input);

        double[][] gradient_Hidden = Matrix.hadamard(d_hidden, hidden_erro);
        gradient_Hidden =Matrix.escalar_multiply(gradient_Hidden, this.learning_rate);

        //Adjust Bias O --> H
        this.bias_ih = Matrix.add(this.bias_ih, gradient_Hidden);
        //Adjust Weigths H --> I
        double[][] weigth_ih_deltas = Matrix.multiply(gradient_Hidden, input_t);
        this.weigth_ih = Matrix.add(this.weigth_ih, weigth_ih_deltas);

    }

    public void train(double[][] dadosInput, double[][] target){

        int j = 0;
        double[][] pacote = new double[dadosInput[0].length][1];
        double[][] pacote01 = new double[dadosInput[0].length][1];
        double[][] pacoteS = new double[1][1];

        while(train){

            for(int i=0; i<10000; i++){
                int indexi = (int) Math.floor(Math.random()*4);
                if( j==57){
                    indexi = 0;
                    j = 0;
                }
                j++;
                for(int k=0; k<dadosInput[0].length; k++){
                    pacote[k][0] = dadosInput[indexi][k];
                    pacoteS[0][0] = target[indexi][0];
                }
                network(pacote, pacoteS);
            }
            for(int k=0; k<dadosInput[0].length; k++){
                pacote[k][0] = dadosInput[3][k];
                pacote01[k][0] = dadosInput[1][k];
            }
            if(predict(pacote)[0][0]<0.04 && predict(pacote01)[0][0]>0.98){
                System.out.println("Treinamento concluido.");
                train = false;
            }
        }
    }

    public double[][] predict(double[][] input){
        //INPUT --> HIDDEN
        
        System.out.println();
        double[][] hidden = Matrix.multiply(this.weigth_ih, input);
        hidden = Matrix.add(hidden, this.bias_ih);
        hidden = sigmoid(hidden);
        
        //HIDDEN --> OUTPUT
        System.out.println();
        double[][] output = Matrix.multiply(this.weigth_ho, hidden);
        output = Matrix.add(output, this.bias_ho);
        output = sigmoid(output);
       
        return output;
    }

    public static double[][] sigmoid(double[][] n){
        double[][] normal = new double[n.length][n[0].length];
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[0].length; j++) {
                normal[i][j] = 1/(1 + Math.exp(- n[i][j]));
            }
        }
        return normal;
    }

    //derivada da sigmoid
    public static double[][] dsigmoid(double[][] f){
        double[][] dnormal = new double[f.length][f[0].length];
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[0].length; j++) {
                dnormal[i][j] = f[i][j] * (1 - f[i][j]);
            }
        }
        return dnormal;
    }

    public void userInteraction(double[][] dados, RedeNeural rn){

        double[][] pacote1 = new double[dados[0].length][1];
        double[][] pacote2 = new double[dados[0].length][1];
        double[][] pacote3 = new double[dados[0].length][1];
        double[][] pacote4 = new double[dados[0].length][1]; 
         
        for(int k=0; k<dados[0].length; k++){
         pacote1[k][0] = dados[0][k];
         pacote2[k][0] = dados[1][k];
         pacote3[k][0] = dados[2][k];
         pacote4[k][0] = dados[3][k];
        }
    
        boolean teste = true;
        int opcao = 0;
        double resultado = 0;
        try (Scanner teclado = new Scanner(System.in)) {
             while(teste){
                 // System.out.print("\033[H\033[2j]");
                     System.out.flush();
                     System.out.println();
                     System.out.println("-------------------------------------");
                     System.out.println(" Escolha um teste para a Rede Neural ");
                     System.out.println("-------------------------------------");
                     System.out.println("(1) |1,1|");
                     System.out.println("(2) |1,0|");
                     System.out.println("(3) |0,1|");
                     System.out.println("(4) |0,0|");
                     System.out.println("(0) [sair]");
                     System.out.println("-------------------------------------");
                     System.out.println("Para sair digite (0) ou outro número.");
                     System.out.println("-------------------------------------");
                     if(opcao!=0){
                         System.out.print("Opção " + opcao + " Resultado: ");
                         System.out.printf("%.3f", resultado);
                         System.out.println();
                     }
                     opcao = teclado.nextInt();
 
                     if(opcao==1){
                         resultado = rn.predict(pacote1)[0][0];
                     }else if(opcao==2){
                         resultado = rn.predict(pacote2)[0][0];
                     }else if(opcao==3){
                         resultado = rn.predict(pacote3)[0][0];
                     }else if(opcao==4){
                         resultado = rn.predict(pacote4)[0][0];
                     }else if(opcao == 0){
                         teste = false;
                     }else{
                         System.out.println("Número não encontrado. Reinicie :) ");
                         teste = false;
                     }
                     
             }
         }
     }
}
