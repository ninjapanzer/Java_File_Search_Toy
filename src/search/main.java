package search;

import java.io.File;

public class main {

	public static void main(String[] args) {
		//args = new String[2];
		File[] files = new File[1];
		if(args[1] == null){
			files = File.listRoots();
		}else{
			files[0] = new File(args[1]);
		}
		
		String searchString = args[0];
		for(File root : files){
			String search = dig(root.getPath(), searchString);
			if(search != null){
				System.out.println("\n"+search);
				return;
			}
		}  
	}
	
	private static String dig(String path, String searchString){
		String[] listing = new File(path).list();
		if(listing == null){
			listing = new String[0];
		}
		for(String item : listing){
			String newPath = path+"/"+item;
			File instance = new File(newPath);
			if(instance.isDirectory()){
				int start = 0;
				if(newPath.length() > 100){
					start = newPath.length() - 100;
				}
				System.out.print("\r                                                                                                                       ");
				System.out.print("\r Searching in: "+newPath.substring(start, newPath.length()));
				String result = dig(newPath, searchString);
				if(result != null){
					return result;
				}
			}else{
				Boolean matched = file(newPath, searchString);
				if(matched){
					return "Item found at "+newPath;
				}
			}
		}
		return null;
	}
	
	private static Boolean file(String path, String target){
		if (path.toLowerCase().contains(target.toLowerCase())){
			return true;
		}
		return false;
	}

}
