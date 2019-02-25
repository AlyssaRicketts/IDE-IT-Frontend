package test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class UserInterfaceTester {
	private static SWTWorkbenchBot bot;
	private static SWTBotView ideitView;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();
		openJavaPerspective();
		closeWelcomePage();
		openIDEITWindow();
		
		// Must set up preferences in workspace, to what the workspace typically launches as
		IEclipsePreferences JDTUIPrefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
		JDTUIPrefs.put("content_assist_autoactivation", "false");
		JDTUIPrefs.put("smart_semicolon", "false");
		
		IEclipsePreferences JDTCorePrefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
		JDTCorePrefs.put("org.eclipse.jdt.core.compiler.problem.fieldHiding", "ignore");
	}
	
	@AfterClass
    public static void afterClass() {
    	bot.resetWorkbench();
    }
	
	@Test
	public void testOpenIDEITWindow() {
		assertNotNull(bot.viewByTitle("IDE-IT"));
	}
	
    @Test
    public void testContentAssistAutoActivation() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    	assertTrue(prefs.get("content_assist_autoactivation", "default").equals("false"));
        bot.checkBox("Enable content assist auto activation").click();
        assertTrue(prefs.get("content_assist_autoactivation", "default").equals("true"));
    }
    
    @Test
    public void testSmartSemicolon() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    	assertTrue(prefs.get("smart_semicolon", "default").equals("false"));
        bot.checkBox("Enable smart semicolon activation").click();
        assertTrue(prefs.get("smart_semicolon", "default").equals("true"));
    }
    
    @Test
    public void testShadowedVariableWarning() {
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
    	assertTrue(prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default").equals("ignore"));
        bot.checkBox("Enable shadowed variable warning").click();
        assertTrue(prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default").equals("warning"));
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
}
