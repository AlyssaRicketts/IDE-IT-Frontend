package main.java;

/**
 * Extracts information about the operating system that eclipse is currently running on
 */
public class OSInfo {
	// String indicating what the current operating system is
   private static String OS = null;
   
   /**
    * Returns the operating system name, and sets the global field if not yet set
    * 
    * @return the operating system name, as a string
    */
   public static String getOSName() {
      if (OS == null) { OS = System.getProperty("os.name").toLowerCase(); }
      return OS;
   }
   
   /**
    * Indicates if the operating system is Windows
    * 
    * @return true if the operating system is Windows, false otherwise
    */
   public static boolean isWindows() {
	   return (getOSName().indexOf("win") >= 0);
   }

   /**
    * Indicates if the operating system is Unix/Linux
    * 
    * @return true if the operating system is Unix/Linux, false otherwise
    */
   public static boolean isUnix() {
	   return (getOSName().indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
   }
   
   /**
    * Indicates if the operating system is Mac
    * 
    * @return true if the operating system is Mac, false otherwise
    */
   public static boolean isMac() {
	   return (getOSName().indexOf("mac") >= 0);
   }
   
   /**
    * Indicates if the operating system is Solaris
    * 
    * @return true if the operating system is Solaris, false otherwise
    */
   public static boolean isSolaris() {
	   return (OS.indexOf("sunos") >= 0);
   }
}
