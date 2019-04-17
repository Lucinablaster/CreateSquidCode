package create.view;

import javax.swing.JFrame;

import create.controller.Controller;

public class AppFrame extends JFrame
{
	private Controller controller;
	private Panel appPanel;
	
	public AppFrame(Controller controller){
		super();
		this.controller = controller;
		this.appPanel = new Panel(this.controller);
		setupFrame();
	}
	
	private void setupFrame() {
		this.setContentPane(appPanel);
		this.setTitle("");
		this.setSize(500, 500);
		this.setVisible(true);
	}
}
