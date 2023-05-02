
public class Sketch{
    public static void main(String[] args) {
            
       RedeNeural neuralNetwork = new RedeNeural(2, 3, 1);
       double[][] dadosInput = {{1, 1},{1, 0},{0, 1},{0, 0}}; //atenção na declaração de quem é linha ou coluna 
       double[][] target = {{0},{1},{1},{0}};

       neuralNetwork.train(dadosInput, target);
       
       neuralNetwork.userInteraction(dadosInput, neuralNetwork);
    }
       
    
}