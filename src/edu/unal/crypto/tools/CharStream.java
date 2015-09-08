package edu.unal.crypto.tools;

public class CharStream {

	public static Character[] fromString(String str) {
		
		Character[] output = new Character[str.length()];
		for (int i = 0; i < output.length; i++) {
			output[i] = str.charAt(i);
		}
		return output;
	}
	
	public static Character[] fromCharArray(char[] arr) {
		
		Character[] output = new Character[arr.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = arr[i];
		}
		return output;
	}
	
	public static Character[] fromByteArray(byte[] arr) {
		
		Character[] output = new Character[arr.length];
		for (int i = 0; i < output.length; i++) {
			char c = (char)arr[i];
			output[i] = (Character)c;
		}
		return output;
	}
	
	public static Character[] fromIntArray(int[] arr) {
		
		Character[] output = new Character[arr.length];
		for (int i = 0; i < output.length; i++) {
			char c = (char)arr[i];
			output[i] = (Character)c;
		}
		return output;
	}
	
	public static void err(Character[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			System.err.print(arr[i]);
		}
		System.err.println();
	}
	
	public static void out(Character[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			System.err.print(arr[i]);
		}
		System.err.println();
	}
	
	public static boolean equals(Character[] a, Character[] b) {
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
}
