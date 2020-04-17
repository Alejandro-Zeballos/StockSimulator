package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
	
	BufferedReader br;
	
	public UserInterface() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void listOptions() {
		System.out.println("");
		System.out.println("------------------------o-----------------------");
		System.out.println("Choose one option to continue");
		System.out.println("");
		System.out.println("1) Company with the highest capital");
		System.out.println("2) Company with the lowest capital");
		System.out.println("3) Investor with the highest number of shares");
		System.out.println("4) Investor with the lowest number of shares");
		System.out.println("5) Exit");
		
	}
	
	public int getOption(){
		
		int initialOption = 1;
		int finalOption = 5;
		String option = "";
		int numericOption = 0;
		
		//this line will try to convert the input to integer and if it fails means that the input is wrong 
		try {
			option = br.readLine().trim();
			numericOption = Integer.parseInt(option);
		
		//this catch will hadle input related errors
		}catch(IOException e) {
			System.out.println("Somethin went wrong try again");
			return getOption();
			
		//this catch will be executed when the user does not input a numeric number
		}catch(NumberFormatException e) {
			System.out.println("Input a valid number");
			return getOption();
		}

		if(numericOption <= finalOption & numericOption >= initialOption) {
			return numericOption;
		}else {
			System.out.println("Input a valid number in the range: "+ initialOption +" - "+ finalOption );
			return getOption();
		}
		
	}
	
	public void printMessage(String message) {
		System.out.println(message);
		System.out.println("");
	}
	
	

	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
