import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Train {
	public static void train(int iterations) {
        // ==========================================================
		for (int iter = 0; iter < iterations; iter++){
			
			// output each iteration result
			try {
				Data.bw.write("Iter:" + Integer.toString(iter) + "| ");
				Data.bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.print("Iter:" + Integer.toString(iter) + "| ");
			try {
				Test.test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // ===========================================
            // Calculate gradient
			float grad_V[][] = new float[Data.m + 1][Data.d];
			float grad_U[][] = new float[Data.n + 1][Data.d];
			int countI[] = new int[Data.m + 1];
			for (int u = 1; u <= Data.n; u++) {
			    // --- Calculate gradient in training data 
				for (int i : Data.I_u[u]) {
					countI[i]++;
					float pred = 0; 
					for (int f=0; f<Data.d; f++)
					{
						pred += Data.U[u][f] * Data.V[i][f];
					}
						
					float error = Data.r[u][i] - pred;
					
					for(int f=0; f<Data.d; f++)
					{	
						grad_U[u][f] += -error * Data.V[i][f] + Data.alpha_u * Data.U[u][f];    	
						grad_V[i][f] += -error * Data.U[u][f] + Data.alpha_v * Data.V[i][f];	    			
					}
				}
			}       
    		// ----------------------------------------------------
            
            // ===========================================
			// Update
			for (int u = 1; u <= Data.n; ++u) {
				for(int f=0; f<Data.d; f++) Data.U[u][f] = (float) (Data.U[u][f] - Data.gamma * grad_U[u][f] / Data.I_u[u].size());				
			}
			for (int i = 1; i <= Data.m; ++i) {
				if (countI[i] != 0)
					for(int f=0; f<Data.d; f++) Data.V[i][f] = (float) (Data.V[i][f] - Data.gamma * grad_V[i][f] / countI[i]);
			}
    		// ----------------------------------------------------
		
			Data.gamma = (float) (Data.gamma * 0.9);  //Decrese $\gamma$
		} 	
		// ----------------------------------------------------

	}
		
}