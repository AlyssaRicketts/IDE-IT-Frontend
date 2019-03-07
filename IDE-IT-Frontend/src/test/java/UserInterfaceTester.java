package test.java;

import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBotAssert;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.FileUtils;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.Bundle;

import main.activation.Activator;
import plugin.views.HotkeyDisplayComposite;

@RunWith(SWTBotJunit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserInterfaceTester {
	private static SWTWorkbenchBot bot;
	private static SWTBotView ideitView;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();
		openJavaPerspective();
		closeWelcomePage();
		
		/*// Must set up preferences in workspace, to what the workspace typically launches as
		IEclipsePreferences JDTUIPrefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
		JDTUIPrefs.put("content_assist_autoactivation", "false");
		JDTUIPrefs.put("smart_semicolon", "false");
		
		IEclipsePreferences JDTCorePrefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
		JDTCorePrefs.put("org.eclipse.jdt.core.compiler.problem.fieldHiding", "ignore");*/
		
		openIDEITWindow();
		createANewJavaProject();
		createANewJavaClass();
	}
	
	@AfterClass
    public static void afterClass() {
    	bot.resetWorkbench();
    }
	
	@Test
	public void test1_testOpenIDEITWindow() {
		assertNotNull(bot.viewByTitle("IDE-IT"));
	}
	
    @Test
    public void test2_testContentAssistAutoActivation() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    	prefs.put("content_assist_autoactivation", "false");
    	assertTrue(prefs.get("content_assist_autoactivation", "default").equals("false"));
        bot.checkBox("Enable content assist auto activation").click();
        assertTrue(prefs.get("content_assist_autoactivation", "default").equals("true"));
    }
    
    @Test
    public void test3_testSmartSemicolon() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    	prefs.put("smart_semicolon", "false");
    	assertTrue(prefs.get("smart_semicolon", "default").equals("false"));
        bot.checkBox("Enable smart semicolon activation").click();
        assertTrue(prefs.get("smart_semicolon", "default").equals("true"));
    }
    
    @Test
    public void test4_testShadowedVariableWarning() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
    	prefs.put("org.eclipse.jdt.core.compiler.problem.fieldHiding", "ignore");
    	assertTrue(prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default").equals("ignore"));
        bot.checkBox("Enable shadowed variable warning").click();
        assertTrue(prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default").equals("warning"));
    }
    
    @Test
	public void test5_testMultilineComment() {
		SWTBotEclipseEditor editor = bot.editorByTitle("HelloWorld.java").toTextEditor();
		SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.MockKeyboardStrategy";
		
		editor.setText("\n\npublic class HelloWorld {\n\n" +
		    "\tpublic static void main(String[] args) {\n" +
		        "\t\tSystem.out.println(\"Hello\");\n" +
				"\t\tSystem.out.println(\"World\");\n\n\n" +
		    "\t}\n\n" +
			"}");
		
		editor.save();
		editor.typeText(5, 2, "//");
		bot.sleep(200);
		editor.typeText(6, 2, "//");
		bot.sleep(200);
		
		SWTBotCLabel clabelBot = bot.clabel("Try using 'CMD + /' to comment several lines.");
		SWTBotAssert.assertVisible(clabelBot);
	}
    
    @Test
 	public void test6_testAddImportStatements() {
 		SWTBotEclipseEditor editor = bot.editorByTitle("HelloWorld.java").toTextEditor();
 		SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.MockKeyboardStrategy";
 		
 		editor.typeText(7, 0, "\tMap<String, String> myMap = new Map<String, String>();");
 		bot.sleep(1000);
 		
 		editor.typeText(0, 0, "import ");
 		bot.sleep(1000);
 		
 		SWTBotCLabel clabelBot = bot.clabel("Try using 'CTRL + SHIFT + O' to add import statements.");
 		SWTBotAssert.assertVisible(clabelBot);
 	}
    
     

    
    private static void openJavaPerspective() throws InterruptedException {
	  Display.getDefault().syncExec(new Runnable() {
	    public void run() {
	      try {
	         IWorkbench workbench = PlatformUI.getWorkbench();
	         workbench.showPerspective("org.eclipse.jdt.ui.JavaPerspective",
	                workbench.getActiveWorkbenchWindow());
	      } catch (WorkbenchException e) {
	         e.printStackTrace();
	      }
	    }
	  });
    }
    
    private static void closeWelcomePage() {
    	for (SWTBotView view : bot.views()) {
            if (view.getTitle().equals("Welcome")) {
                view.close();
            }
    	}
    }
    
    private static void openIDEITWindow() {
	    bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		bot.tree().expandNode("IDE-IT Plug-in").select("IDE-IT");
		bot.button("Open").click();
		SWTBotView ideitView = bot.viewByTitle("IDE-IT");
    }
    
	private static void createANewJavaProject() throws Exception {
		bot.menu("File").menu("New").menu("Project...").click();
		bot.shell("New Project").activate();
		bot.tree().expandNode("Java").select("Java Project");
		bot.button("Next >").click();
		bot.textWithLabel("Project name:").setText("MyFirstProject");
		bot.button("Finish").click();
	}

	private static void createANewJavaClass() throws Exception {
		bot.toolbarDropDownButtonWithTooltip("New Java Class").menuItem("Class").click();
		bot.shell("New Java Class").activate();
		bot.textWithLabel("Source folder:").setText("MyFirstProject/src");
		bot.textWithLabel("Name:").setText("HelloWorld");
		bot.button("Finish").click();
	}
}
