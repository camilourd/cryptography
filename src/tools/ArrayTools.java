package tools;

public class ArrayTools {
	
	public static void mixElements(char[] arr) {
		for(int i = 0; i < arr.length; ++i) {
			int a = (int)(Math.random() * arr.length);
			int b = (int)(Math.random() * arr.length);
			char c = arr[a];
			arr[a] = arr[b];
			arr[b] = c;
		}
	}

}
