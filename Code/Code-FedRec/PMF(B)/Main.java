import java.io.*;

public class Main 
{
    public static void main(String[] args) throws IOException 
	{	
		// 1. read configurations		
		ReadConfigurations.readConfigurations(args);

		// 2. read training data and test data
        ReadData.readData();
               
		// 3. apply initialization
		Initialization.initialization();
		
		// 4. train
		Train.train(Data.num_iterations);
		
		// 5. test
		Test.test();		
    }
}
