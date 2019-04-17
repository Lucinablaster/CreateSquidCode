package create.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import create.controller.Controller;

public class Panel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller appController;
	private FlowLayout appLayout;
	
	
	private JLabel iconLabel;
	private JButton StabSquid;
	private JButton FeedSquid;
	private JButton UpgradeFeedSquid;
	private JButton UpgradeStabSquid;
	private JLabel SquidMeat;
	private JLabel SquidSize;
	
	private int meat;
	private int size;
	private int life;
	private double feedIncrease;
	private double killIncreaseMultiplier;
	private double feedPrice;
	private double killPrice;
	private double timer;
	//private JLabel healthLabel;
	
	public Panel(Controller appController){
		super();
		life = 20;
		meat = 0;
		size = 50;
		timer = 10;
		feedIncrease = 1;
		killIncreaseMultiplier = 1;
		
		//Upgrade price of kill/feed
		killPrice = 100;
		feedPrice = 100;
		
		this.appController = appController;
		appLayout = new FlowLayout();
		iconLabel = new JLabel("", new ImageIcon(getClass().getResource("/create/view/images/squid.jpg")), JLabel.CENTER);
		StabSquid = new JButton("Stab Squid");
		FeedSquid = new JButton("Feed Squid");
		UpgradeStabSquid = new JButton("Upgrade Stab Squid: " + killPrice + " meat");
		UpgradeFeedSquid = new JButton("Upgrade Feed Squid: " + feedPrice + " meat");
		SquidMeat = new JLabel("Total Squid Meat: " + meat);
		SquidSize = new JLabel("Squid Size: " + size);
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void killSquid() {
		meat += (size*killIncreaseMultiplier);
		size = 50;
		if(life < 1) {
			//END GAME HERE
		}
		updateInfo();
	}
	private void feedSquid() {
		size += feedIncrease;
		updateInfo();
	}
	
	private void upgrade_killIncreaseMultipler() {
		if(meat >= killPrice) {
			killIncreaseMultiplier = killIncreaseMultiplier*1.5;
			meat -= killPrice;
			killPrice = killPrice*3;
			updateInfo();
		}
	}
	private void upgrade_feedIncrease() {
		if(meat >= feedPrice) {
			feedIncrease = 25+(feedIncrease*1.5);
			meat -= feedPrice;
			feedPrice = feedPrice*3;
			updateInfo();
		}
	}
	
	private void setupPanel() {
		this.setLayout(appLayout);
		this.add(iconLabel);
		this.add(StabSquid);
		this.add(FeedSquid);
		this.add(SquidMeat);
		this.add(SquidSize);
		this.add(UpgradeFeedSquid);
		this.add(UpgradeStabSquid);
	}
	private void setupLayout() {
		
		
		repaint();
	}
	private void setupListeners() {
		UpgradeFeedSquid.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent click) {
		 		upgrade_feedIncrease();
		 	}
		});
		UpgradeStabSquid.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent click) {
		 		upgrade_killIncreaseMultipler();
		 	}
		});
		StabSquid.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent click) {
		 		killSquid();
		 	}
		});
		
		FeedSquid.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent click) {
		 		feedSquid();
		 	}
		});
	}
	
	private void updateImage() {
		String path = "/create/view/images/";
		String defaultname = "squid";
		String name = "Squid Boi";
		String extension = ".jpg";
		ImageIcon icon;
		
		try {
			icon = new ImageIcon(getClass().getResource(path + name + extension));
		}catch(NullPointerException missingImageFile) {
			icon = new ImageIcon(getClass().getResource(path+defaultname+extension));
		}
		
		iconLabel.setIcon(icon);
	}
	
	private void updateInfo() {
		SquidMeat.setText("Total Squid Meat: " + meat);
		SquidSize.setText("Squid Size: " + size);
		UpgradeStabSquid.setText("Upgrade Stab Squid - " + killPrice + " meat");
		UpgradeFeedSquid.setText("Upgrade Feed Squid - " + feedPrice + " meat");
	}
	

}
