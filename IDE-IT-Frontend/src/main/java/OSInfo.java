package main.java;

public class OSInfo {
   private static String OS = null;
   
   public static String getOSName() {
      if(OS == null) { OS = System.getProperty("os.name").toLowerCase(); }
      return OS;
   }
   
   public static boolean isWindows() {
      return (getOSName().indexOf("win") >= 0);
   }

   public static boolean isUnix() {
	   return (getOSName().indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
   }
   
   public static boolean isMac() {
	   return (getOSName().indexOf("mac") >= 0);
   }
   
   public static boolean isSolaris() {
	   return (OS.indexOf("sunos") >= 0);
   }
}
