//  ----------------------------------------------------
// Assignment 4 
//  Question: Part 1
// Written by: Nicholas Yiphoiyen 40117237, Kewen Liang 40129628
//  ----------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class subdict {
	
	//validator for each word to check if there is a digit in it
	public static boolean validator(String s) {
		char[] chars = s.toCharArray();
		for(char c : chars) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		ArrayList<String> wordlist = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new FileInputStream("PersonOfTheCentury.txt"));
			String text = "";
			while (sc.hasNext()) {
				//normalizes according to the assignment instructions
				text = "";
				text = text + sc.next();
				text = text.toUpperCase();
				text = text.replace(".", "");
				text = text.replace(",", "");
				text = text.replace("?", "");
				text = text.replace("!", "");
				text = text.replace("\"", "");
				text = text.replace("'S", "");
				text = text.replace("'M", "");
				text = text.replace("=", "");
				text = text.replace("(", "");
				text = text.replace(")", "");
				text = text.replace("[", "");
				text = text.replace("]", "");
				text = text.replace(":", "");
				text = text.replace(";", "");
				text = text.replace("’M", "");
				text = text.replace("’S", "");
				if (text.length() < 2){
					if (!text.equals("A") && !text.equals("I"))
						continue;
				}
				if (validator(text))
					continue;
					
				int index = wordlist.size();
				boolean inlist = false;
				
				//using compareTo method to place the words as they are read in the right place in the arraylist
				for (int i = 0; i < wordlist.size(); i++) {
					if(wordlist.get(i).equals(text))
						inlist = true;
					if (wordlist.get(i).compareTo(text) > 0) {
						index = i;
						break;
					}
				}
				if (inlist == false)
					wordlist.add(index, text);
			}
			FileOutputStream subdict = new FileOutputStream("SubDict.txt");
			PrintWriter w = new PrintWriter(subdict);
			
			if (wordlist.size() > 1) {char firstletter = wordlist.get(0).charAt(0);
				w.write("The document produced this sub-dictionary, which includes " + wordlist.size() + " entries.\n\n" + firstletter + "\n==\n");
				w.write(wordlist.get(0) + "\n");
				
				//read the first letter of the word, then compares it with the first letter of previous word to check if it starts with the same letter
				for (int i = 1; i < wordlist.size(); i++) {
					char firstletterprevword = wordlist.get(i-1).charAt(0);
					if (wordlist.get(i).charAt(0) == firstletterprevword)
						w.write(wordlist.get(i) + "\n");
					else {
						w.write("\n" + wordlist.get(i).charAt(0) + "\n==\n");
						w.write(wordlist.get(i) + "\n");
					}
				}
			}
			
			else {
				w.write("The document produced this sub-dictionary, which includes " + wordlist.size() + " entries.");
			}
			
			w.flush();
			w.close();
			sc.close();
			System.out.println("Done.");
		} catch (FileNotFoundException e) {
			System.out.println("Error, file not found.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("oof");
			e.printStackTrace();
		}
		
		
	}

}
