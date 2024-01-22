import java.io.IOException;
import java.util.*;

public class Test 
{
    public static void test() throws IOException 
	{       	
    	// --- number of test cases
    	float mae=0;
    	float rmse=0;    	
    	int num = 0;
        // ==========================================================
    	for(int t=0; t<Data.num_test; t++)
    	{
    		int userID = Data.indexUserTest[t];
    		int itemID = Data.indexItemTest[t];
    		float rating = Data.ratingTest[t];
//    		if(Data.r[userID][itemID] == 0)continue; // if training set have't the rating of user $u$ to item $i$

    		// ===========================================    		
    		// --- prediction via inner product
    		float pred = 0;
    		for (int f=0; f<Data.d; f++)
    		{
    			pred +=  Data.U[userID][f] * Data.V[itemID][f];	
    		}
    		if(pred < Data.MinRating) pred = Data.MinRating;
    		if(pred > Data.MaxRating) pred = Data.MaxRating;
    		
			float err = pred-rating;
			if(!Double.isNaN(err))
			{
				mae += Math.abs(err);
				rmse += err*err;
			}
			else 
			{
				num++;
			}
    		// ===========================================    		
    	}
    	
    	System.out.println("NaN num = " + num);
    	float MAE = mae/Data.num_test;
    	float RMSE = (float) Math.sqrt(rmse/Data.num_test);
        // ==========================================================
        
        // ==========================================================
    	//output result
    	String result = "MAE:" + Float.toString(MAE) +  "| RMSE:" + Float.toString(RMSE);
    	System.out.println(result);
		try {
            Data.bw.write("NaN num = " + num + "\r\n" + result +"\r\n"); 
            Data.bw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // ==========================================================
    }
   	
}