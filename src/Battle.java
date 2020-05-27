//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/26/20
//
//  Notes:
//       Holds all the GUI for the project and houses the main. Uses almost every other class to run a game of Pokemon.
//
//  Bugs:
//      unknown


import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Battle {
	public static int P1numberOfFaintedMons = 0;// self explanatory
	public static int P2numberOfFaintedMons = 0;// self explanatory

	private final String[] MUSIC_OPTIONS = { "Battle! (Brendan_May)", "Battle! Rival Hugh",
			"BattleVsTrainer", "BattleVsWildPokemon", "BillsLighthouse",
			"bw2-kanto-gym-leader", "bw-subway-trainer", "PaletteTown",
			"PewterCity", "PokemonBattleMusic", "PokemonGym",
			"PokemonTitleScreen", "PokemonThemeSong", "RivalAppears",
			"TeamRocketHideout", "ViridianForest", "Battle! Team Plasma",
			"bw2-theme","Pinch in Battle!","platinum-title", "xy-theme",
			"xy_vs_gymleader","bw2-title","heartgold-title","Decisive Battle! N"};// 25 music options
	private final String[] TITLE_SCREENS={"PokemonTitleScreen","bw2-title","heartgold-title",
			"xy-theme","platinum-title","bw2-theme"};//6 title screens

	private JFrame frame;// self explanatory
	private JPanel mainPanel;// self explanatory
	private JPanel leftPanel, rightPanel;
	private BackgroundPanel leftDisplayPanel, rightDisplayPanel;// the display shown to the user
	private JPanel leftUI, rightUI;// the users interface panel
	private JPanel leftMovePanel, rightMovePanel;// move options
	private JPanel leftSwitchPanel, rightSwitchPanel;// switch options

	//  Jlabels for each pokemon, on each side
	private JLabel P1pokemon, P1OpposingPokemon, P1pokemonImage, P1opposingPokemonImage, P2pokemon, P2OpposingPokemon, P2pokemonImage, P2opposingPokemonImage, attack, switchOut;

	//a health bar for each pokemon
	private HealthBar P1pokemonHealthBar, P1opposingPokemonHealthBar, P2pokemonHealthBar, P2opposingPokemonHealthBar;

	// the buttons that each player can choose: move is for attacking, switch, is for switching out
	private Button[] leftMoveButtons, rightMoveButtons;
	private Button[] leftSwitchButtons, rightSwitchButtons;

	//the text areas for each player
	private JTextArea leftText, rightText;

	private Timer timer;//self explanatory
	
	private boolean confirm1 = false, confirm2 = false;// confirms whether each player has confirmed there team during the teamvbuilder phase
	private static Player P1, P2;//self explanatory
	private static Object myObject1 = new Object(), myObject2 = new Object(), myObject3=new Object(), myObject4 =new Object();
	//used for thread synchronization. TBH we probably could have just used 1
	
	private MusicPlayer musicPlayer;//self explanatory
	private Calculator calc;//self explanatory
	
	private int musicSelection=-1;
	private int p1Selection = -1;
	private int p2Selection = -1;
	private long textSpeed=5;
	private int healthBarDecreationRate=4;


	public Battle() {

		musicPlayer = new MusicPlayer();//creating a music play
		calc = new Calculator(this);//creating a calculator
		
        frame = new JFrame();//creating a JFrame
        BufferedImage icon;//image for logo of the app icon thing. Doesnt seem to work
        try {
			icon = ImageIO.read(new File("Images/Pokeball.png"));//getting image
	        frame.setIconImage(icon);//setting image
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    	GridBagConstraints constrainstsForP1Panel = new GridBagConstraints();//the constraints for the grid bag layout

		frame.setBounds(100, 100, 2250, 850);//setting frame size

		frame.setVisible(true);//set visible
		 frame.setResizable(false);//cant be resize
		frame.setTitle("NOT Pokemon");//set title
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//program ends when you close the window

		titleScreen();//run the title screen

		try{
			synchronized(myObject4){
				myObject4.wait();//wait for the title screen to notify you  that its done
			}}
		catch (Exception e){
			e.printStackTrace();
		}
		frame.getContentPane().removeAll();// remove everything in the Jframe

		teamBuilder();//run the teambuilder


		try{
		synchronized(myObject1){
			myObject1.wait();//wait for the teambuikder to notify you that its done
		}}
		catch (Exception e){
			e.printStackTrace();
		}

		frame.getContentPane().removeAll();//remove evertyhgin from the frame
		mainPanel=new JPanel();//the main panel where everytihng is held
			P1.setOpposingPlayer(P2);//set oppsoing players
			P2.setOpposingPlayer(P1);

			mainPanel.setLayout(new GridLayout(1, 2));//grid layout for main panel
		frame.add(mainPanel);//adding to frame
			leftPanel = new JPanel(new GridBagLayout());//using grid baglayout
			rightPanel = new JPanel(new GridBagLayout());
			int backgroundNumber;
			do {
				backgroundNumber = (int)(Math.random()*18)+1;//choose which background to use
			} while(backgroundNumber == 9 || backgroundNumber == 12);//you cant use backgrounds 9 and 12
			BufferedImage background;
			try {
				background = ImageIO.read(new File("Images/Backgrounds/BattleBackground"
						+ backgroundNumber + ".png"));
				leftDisplayPanel = new BackgroundPanel(background);//set the background to the display panels
				rightDisplayPanel = new BackgroundPanel(background);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			leftPanel.add(leftDisplayPanel);//adding display
			rightPanel.add(rightDisplayPanel);
			leftUI = new JPanel(new GridLayout(2, 1));//ui has a gird layout
			rightUI = new JPanel(new GridLayout(2, 1));

			leftMovePanel = new JPanel();//where you select moves
			rightMovePanel = new JPanel();
			leftSwitchPanel = new JPanel();//where you select switches
			rightSwitchPanel = new JPanel();
			leftUI.add(leftMovePanel);//add to UI

			leftUI.add(leftSwitchPanel);
			rightUI.add(rightMovePanel);
			rightUI.add(rightSwitchPanel);
			mainPanel.add(leftPanel);
			mainPanel.add(rightPanel);

P1.setCurrentMon();//set current mon to something random, as theyll be cahnged later
P2.setCurrentMon();
			P1pokemon = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);//label is now the name of the pokemon
			P1pokemon.setOpaque(true);//you can see it
			P1pokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P1pokemon.setBackground(Color.LIGHT_GRAY);

			P1OpposingPokemon = new JLabel(P2.getCurrentMon().getName(), SwingConstants.CENTER);//label name is of the opposing pokmeon
		P1OpposingPokemon = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);
		P1OpposingPokemon.setOpaque(true);//you can see it
		P1OpposingPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));//has border
		P1OpposingPokemon.setBackground(Color.LIGHT_GRAY);//has background
			P1pokemonHealthBar = new HealthBar(P1.getCurrentMon());//uses the current pokemon of player 1

			P1pokemonHealthBar.setXOffset(true);
			P1pokemonHealthBar.setPreferredSize(new Dimension(200,15));
			P1opposingPokemonHealthBar = new HealthBar(P2.getCurrentMon());

			P1opposingPokemonHealthBar.setXOffset(false);
			ImageIcon pic =
					new ImageIcon("Images/Sprites/SpritesBack/" + P1.getCurrentMon().getID() + "-back.gif");

			P1pokemonImage = new JLabel(pic);
			P1pokemonImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {//add a mouse listener
					if (SwingUtilities.isRightMouseButton(e)) {//checks if its a right clickk
						JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");//pops up stuff if so

						leftPokemonInfoPopup.setBackground(Color.DARK_GRAY);
						leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel,
								P1.getCurrentMon().toString(), "Player 1 Pokemon Info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif"));
					}
				}
			});

			pic = new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif");//same as above
			P1opposingPokemonImage = new JLabel(pic);
			P1opposingPokemonImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
						leftPokemonInfoPopup.showMessageDialog(
								leftPanel,
								P2.getCurrentMon().toString(),
								"Player 2 Pokemon Info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif"));
					}
				}
			});
			leftDisplayPanel.setLayout(new GridBagLayout());//uses grid bag

		/*if you dont know how to use grid bag please go to the API so you understand what is going.
		this will not all be commented because there is just too much stuff. Basically, the tldr version is that
		a grid bag layout is like a grid layout buts easier to set the position and size of specific componenets

		 */
		GridBagConstraints constraintsForP1Display = new GridBagConstraints();
			constraintsForP1Display.gridx = 0;
			constraintsForP1Display.gridy = 3;
			constraintsForP1Display.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP1Display.insets = new Insets(5, 5, 5, 5);
			constraintsForP1Display.anchor = GridBagConstraints.LAST_LINE_START;
			constraintsForP1Display.weightx = 0.5;
			constraintsForP1Display.weighty = 0.5;

			leftDisplayPanel.add(P1pokemon, constraintsForP1Display);
			constraintsForP1Display.gridy = 4;

			leftDisplayPanel.add(P1pokemonHealthBar, constraintsForP1Display);
			constraintsForP1Display.gridy = 5;

			leftDisplayPanel.add(P1pokemonImage, constraintsForP1Display);
			constraintsForP1Display.gridx = 1;
			constraintsForP1Display.gridy = 0;
			constraintsForP1Display.insets = new Insets(5, 105, 5, 5);
			constraintsForP1Display.anchor = GridBagConstraints.FIRST_LINE_END;

			leftDisplayPanel.add(P1OpposingPokemon, constraintsForP1Display);
			constraintsForP1Display.gridy = 1;

			leftDisplayPanel.add(P1opposingPokemonHealthBar, constraintsForP1Display);
			constraintsForP1Display.gridy = 2;

			leftDisplayPanel.add(P1opposingPokemonImage, constraintsForP1Display);
			leftDisplayPanel.setBackground(Color.LIGHT_GRAY);

			constrainstsForP1Panel.gridx = 0;
			constrainstsForP1Panel.gridy = 0;
			constrainstsForP1Panel.gridwidth = 4;
			constrainstsForP1Panel.gridheight = 3;
			constrainstsForP1Panel.weightx = 0.0;
			constrainstsForP1Panel.weighty = 0.0;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constrainstsForP1Panel.fill = GridBagConstraints.HORIZONTAL;
			constrainstsForP1Panel.insets = new Insets(5, 5, 5, 0);
			leftPanel.add(leftDisplayPanel, constrainstsForP1Panel);

			attack = new JLabel("Attack");
			constrainstsForP1Panel.gridx = 0;
			constrainstsForP1Panel.gridy = 3;
			constrainstsForP1Panel.gridwidth = 4;
			constrainstsForP1Panel.gridheight = 1;
			constrainstsForP1Panel.weightx = 0.5;
			constrainstsForP1Panel.weighty = 0.0;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constrainstsForP1Panel.fill = GridBagConstraints.HORIZONTAL;
			constrainstsForP1Panel.insets = new Insets(0, 10, 0, 0);

			leftPanel.add(attack, constrainstsForP1Panel);

			GridLayout P1MovePanelLayout = new GridLayout(2, 2);
			leftMovePanel.setLayout(P1MovePanelLayout);
			leftMoveButtons = new Button[4];

			for (int i = 0; i < leftMoveButtons.length; i++) {//adding the buttons to the left move panels
				leftMoveButtons[i] = new Button(P1.getCurrentMon().getMoves()[i].getName(), i);
				leftMoveButtons[i].setPreferredSize(new Dimension(100,45));

				leftMovePanel.add(leftMoveButtons[i]);
			}

			constrainstsForP1Panel.gridx = 0;
			constrainstsForP1Panel.gridy = 4;
			constrainstsForP1Panel.gridwidth = 4;
			constrainstsForP1Panel.gridheight = 1;
			constrainstsForP1Panel.weightx = 0.5;
			constrainstsForP1Panel.weighty = 0.0;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constrainstsForP1Panel.fill = GridBagConstraints.HORIZONTAL;
			constrainstsForP1Panel.insets = new Insets(0, 0, 0, 0);
			leftPanel.add(leftMovePanel, constrainstsForP1Panel);

			switchOut = new JLabel("Switch");
			constrainstsForP1Panel.gridx = 0;
			constrainstsForP1Panel.gridy = 5;
			constrainstsForP1Panel.gridwidth = 4;
			constrainstsForP1Panel.gridheight = 1;
			constrainstsForP1Panel.weightx = 0.5;
			constrainstsForP1Panel.weighty = 0.0;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constrainstsForP1Panel.fill = GridBagConstraints.HORIZONTAL;
			constrainstsForP1Panel.insets = new Insets(0, 10, 0, 0);

			leftPanel.add(switchOut, constrainstsForP1Panel);
			P1MovePanelLayout = new GridLayout(3, 2);

			leftSwitchPanel.setLayout(P1MovePanelLayout);

			leftSwitchButtons = new Button[6];

			for (int i = 0; i < leftSwitchButtons.length; i++) {//adding switch buttons to panel
				leftSwitchButtons[i] = new Button(P1.getPokemon()[i].getName(), i + 4);
				leftSwitchPanel.add(leftSwitchButtons[i]);
			}

			constrainstsForP1Panel.gridx = 0;
			constrainstsForP1Panel.gridy = 6;
			constrainstsForP1Panel.gridwidth = 1;
			constrainstsForP1Panel.gridheight = 1;
			constrainstsForP1Panel.weightx = 0.5;
			constrainstsForP1Panel.weighty = 0.0;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constrainstsForP1Panel.fill = GridBagConstraints.HORIZONTAL;
			constrainstsForP1Panel.insets = new Insets(0, 0, 0, 0);

			leftPanel.add(leftSwitchPanel, constrainstsForP1Panel);
			leftText = new JTextArea(20, 20);
			leftText.setEditable(false);//cant edit
			leftText.setLineWrap(true);
			leftText.setWrapStyleWord(true);

			((DefaultCaret)leftText.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//always updates text
			JScrollPane pane = new JScrollPane(leftText,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


			constrainstsForP1Panel.gridx = 4;
			constrainstsForP1Panel.gridy = 0;
			constrainstsForP1Panel.gridwidth = 3;
			constrainstsForP1Panel.gridheight = 7;
			constrainstsForP1Panel.weightx = 0.5;
			constrainstsForP1Panel.weighty = 0.5;
			constrainstsForP1Panel.anchor = GridBagConstraints.FIRST_LINE_END;
			constrainstsForP1Panel.fill = GridBagConstraints.BOTH;
			constrainstsForP1Panel.insets = new Insets(5, 5, 5, 5);
			leftPanel.add(pane, constrainstsForP1Panel);// pane to left panel



			/*
			These are the the same as the left side, except for the right side which is p2. NO comments, if you have a question, look at the other side
			 */
			GridBagConstraints constraintsForP2Panel = new GridBagConstraints();
			P2pokemon = new JLabel(P2.getCurrentMon().getName(), SwingConstants.CENTER);
		P2pokemon = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);
		P2pokemon.setOpaque(true);
		P2pokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P2pokemon.setBackground(Color.LIGHT_GRAY);
			P2OpposingPokemon = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);
		P2OpposingPokemon = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);
		P2OpposingPokemon.setOpaque(true);
		P2OpposingPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P2OpposingPokemon.setBackground(Color.LIGHT_GRAY);
			P2pokemonHealthBar = new HealthBar(P2.getCurrentMon());

			P2pokemonHealthBar.setXOffset(true);
			P2opposingPokemonHealthBar = new HealthBar(P1.getCurrentMon());

			P2opposingPokemonHealthBar.setXOffset(false);
			ImageIcon pic2 = new ImageIcon("Images/Sprites/SpritesBack/" + P2.getCurrentMon().getID() + "-back.gif");
			P2pokemonImage = new JLabel(pic2);
			P2pokemonImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
					leftPokemonInfoPopup.showMessageDialog(
							rightDisplayPanel,
							P2.getCurrentMon().toString(),
							"Player 2 Pokemon Info",
							2,
							new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif"));


				}
			});
			pic2 = new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif");
			P2opposingPokemonImage = new JLabel(pic2);
			P2opposingPokemonImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");
					leftPokemonInfoPopup.showMessageDialog(rightDisplayPanel,
							P1.getCurrentMon().toString(), "Player 1 Pokemon Info",
							2,
							new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif"));
				}
			});
			rightDisplayPanel.setLayout(new GridBagLayout());
			GridBagConstraints rightDisplayConstraints = new GridBagConstraints();
			rightDisplayConstraints.gridx = 0;
			rightDisplayConstraints.gridy = 3;
			rightDisplayConstraints.fill = GridBagConstraints.HORIZONTAL;
			rightDisplayConstraints.insets = new Insets(5, 5, 5, 5);
			rightDisplayConstraints.anchor = GridBagConstraints.LAST_LINE_START;
			rightDisplayConstraints.weightx = 0.5;
			rightDisplayConstraints.weighty = 0.5;
			rightDisplayPanel.add(P2pokemon, rightDisplayConstraints);
			rightDisplayConstraints.gridy = 4;
			rightDisplayPanel.add(P2pokemonHealthBar, rightDisplayConstraints);
			rightDisplayConstraints.gridy = 5;
			rightDisplayPanel.add(P2pokemonImage, rightDisplayConstraints);
			rightDisplayConstraints.gridx = 1;
			rightDisplayConstraints.gridy = 0;
			rightDisplayConstraints.insets = new Insets(5, 105, 5, 5);
			rightDisplayConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
			rightDisplayPanel.add(P2OpposingPokemon, rightDisplayConstraints);
			rightDisplayConstraints.gridy = 1;
			rightDisplayPanel.add(P2opposingPokemonHealthBar, rightDisplayConstraints);
			rightDisplayConstraints.gridy = 2;
			rightDisplayPanel.add(P2opposingPokemonImage, rightDisplayConstraints);
			rightDisplayPanel.setBackground(Color.LIGHT_GRAY);
			constraintsForP2Panel.gridx = 0;
			constraintsForP2Panel.gridy = 0;
			constraintsForP2Panel.gridwidth = 4;
			constraintsForP2Panel.gridheight = 3;
			constraintsForP2Panel.weightx = 0.0;
			constraintsForP2Panel.weighty = 0.0;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constraintsForP2Panel.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP2Panel.insets = new Insets(5, 5, 5, 0);
			rightPanel.add(rightDisplayPanel, constraintsForP2Panel);


			attack = new JLabel("Attack");
			constraintsForP2Panel.gridx = 0;
			constraintsForP2Panel.gridy = 3;
			constraintsForP2Panel.gridwidth = 4;
			constraintsForP2Panel.gridheight = 1;
			constraintsForP2Panel.weightx = 0.5;
			constraintsForP2Panel.weighty = 0.0;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constraintsForP2Panel.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP2Panel.insets = new Insets(0, 10, 0, 0);
			rightPanel.add(attack, constraintsForP2Panel);
			GridLayout layout3 = new GridLayout(2, 2);
			rightMovePanel.setLayout(layout3);
			rightMoveButtons = new Button[4];
			for (int i = 0; i < rightMoveButtons.length; i++) {
				rightMoveButtons[i] = new Button(P2.getCurrentMon().getMoves()[i].getName(), i);
				rightMoveButtons[i].setPreferredSize(new Dimension(100,45));

				rightMovePanel.add(rightMoveButtons[i]);
			}
			constraintsForP2Panel.gridx = 0;
			constraintsForP2Panel.gridy = 4;
			constraintsForP2Panel.gridwidth = 4;
			constraintsForP2Panel.gridheight = 1;
			constraintsForP2Panel.weightx = 0.5;
			constraintsForP2Panel.weighty = 0.0;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constraintsForP2Panel.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP2Panel.insets = new Insets(0, 0, 0, 0);
			rightPanel.add(rightMovePanel, constraintsForP2Panel);
			switchOut = new JLabel("Switch");
			constraintsForP2Panel.gridx = 0;
			constraintsForP2Panel.gridy = 5;
			constraintsForP2Panel.gridwidth = 4;
			constraintsForP2Panel.gridheight = 1;
			constraintsForP2Panel.weightx = 0.5;
			constraintsForP2Panel.weighty = 0.0;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constraintsForP2Panel.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP2Panel.insets = new Insets(0, 10, 0, 0);
			rightPanel.add(switchOut, constraintsForP2Panel);
			layout3 = new GridLayout(3, 2);
			rightSwitchPanel.setLayout(layout3);
			rightSwitchButtons = new Button[6];
			for (int i = 0; i < rightSwitchButtons.length; i++) {
				rightSwitchButtons[i] = new Button(P2.getPokemon()[i].getName(), i + 4);

				rightSwitchPanel.add(rightSwitchButtons[i]);
			}
			constraintsForP2Panel.gridx = 0;
			constraintsForP2Panel.gridy = 6;
			constraintsForP2Panel.gridwidth = 1;
			constraintsForP2Panel.gridheight = 1;
			constraintsForP2Panel.weightx = 0.5;
			constraintsForP2Panel.weighty = 0.0;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_START;
			constraintsForP2Panel.fill = GridBagConstraints.HORIZONTAL;
			constraintsForP2Panel.insets = new Insets(0, 0, 0, 0);
			rightPanel.add(rightSwitchPanel, constraintsForP2Panel);
			rightText = new JTextArea(20, 20);
			rightText.setEditable(false);
			rightText.setLineWrap(true);
			rightText.setWrapStyleWord(true);

			((DefaultCaret)rightText.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			JScrollPane pane2 = new JScrollPane(rightText,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			constraintsForP2Panel.gridx = 4;
			constraintsForP2Panel.gridy = 0;
			constraintsForP2Panel.gridwidth = 3;
			constraintsForP2Panel.gridheight = 7;
			constraintsForP2Panel.weightx = 0.5;
			constraintsForP2Panel.weighty = 0.5;
			constraintsForP2Panel.anchor = GridBagConstraints.FIRST_LINE_END;
			constraintsForP2Panel.fill = GridBagConstraints.BOTH;
			constraintsForP2Panel.insets = new Insets(5, 5, 5, 5);
			rightPanel.add(pane2, constraintsForP2Panel);
		Font pokemonPlaceHolder=new Font("Arial", Font.PLAIN, 40);
		try {

				pokemonPlaceHolder=Font.createFont(Font.TRUETYPE_FONT,new File("Fonts/PokemonGB.ttf"));//set fonts




		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Font pokemonGB=pokemonPlaceHolder.deriveFont((float) 15);//change font size
		leftText.setFont(pokemonGB);
		rightText.setFont(pokemonGB);
			leftPanel.updateUI();
			rightPanel.updateUI();
			repaint(P1, P2);

    }

    //the main, where everything happens
    public static void main (String[] args) {

boolean dangerZoneActivated=false;//if you have 1 pokemon remaining, different music plays
		Battle b = new Battle();// calls the constructor, which sets up the gui
		Player p1 = b.P1;// doesn't use the global variables because this was created before we had GUI
		Player p2 = b.P2;
		p1.setOpposingPlayer(p2);
b.confirm1=false;
b.confirm2=false;




		b.log("The match has begun!\n");//self explanatory

		boolean gameNotOver = true;//self explanatory
		boolean p1WillSwitch = false;//self explanatory
		boolean p2WillSwitch = false;//self explanatory
		int p1SelectedMoveIndex = -1;//the move a player is selecting
		int p2SelectedMoveIndex = -1;
		int p1SwitchIn = -1;
		int p2SwitchIn = -1;
		JOptionPane popup = new JOptionPane("");//the joption pane used to get input or show feedback
		popup.setIcon(new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));//self explanatory

		for (int i = 0; i < b.leftMoveButtons.length; i++) {

			b.leftMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {//mouse listener for right click, doesnt select anything


					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getCurrentMon().getMoves()[x].toString();//gets the info about the move this button represents
						popup.showMessageDialog(b.leftPanel,//in the left display panel
								textInfo,//see above
								"Move Info",//self explanatory
								2,//self explanatory
								(new ImageIcon(new ImageIcon("Images/Types/" + //the icon depends on the type of the move
										p1.getCurrentMon().getMoves()[x].getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.leftMoveButtons[i].addActionListener(new ActionListener() {//for left clicks
				//the reason we used a mouse and action listener is because mouslistener has weird specifications for a left click
				//and makes it feel weird to click it on a trackpad, although it would proably be fine on a mouse
				@Override
				public void actionPerformed(ActionEvent e) {


					b.p1Selection = ((Button) e.getSource()).getNum();//sets the selection
//					synchronized (myObject2) {//notifies the object
//						myObject2.notify();
//					}
					synchronized (myObject2){
						if(b.confirm2==true){//notifys the object in main if the other player has selected
							myObject2.notify();
						}else{
							b.confirm1 =true;//if the other player hasn't confirmed, confirms your own
						}
					}
				}
			});

		}
		for (int i = 0; i < b.leftSwitchButtons.length; i++) {//same things as move buttons but its for switches

			b.leftSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.leftPanel,
								textInfo,
								"Pokemon Switch info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + p1.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.leftSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p1Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject2){
						if(b.confirm2==true){//notifys the object in main if the other player has selected
							myObject2.notify();
						}else{
							b.confirm1 =true;//if the other player hasn't confirmed, confirms your own
						}
					}

				}
			});
		}


		for (int i = 0; i < b.rightMoveButtons.length; i++) {//same thing as for the left move buttons, see above comments

			b.rightMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getCurrentMon().getMoves()[x].toString();
						popup.showMessageDialog(b.rightDisplayPanel,
								textInfo,
								"Move Info",
								2,
								(new ImageIcon(new ImageIcon("Images/Types/" + p2.getCurrentMon().getMoves()[x].getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.rightMoveButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p2Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject2){
						if(b.confirm1==true){//notifys the object in main if the other player has selected
							myObject2.notify();
						}else{
							b.confirm2 =true;//if the other player hasn't confirmed, confirms your own
						}
					}
				}
			});


		}
		for (int i = 0; i < b.rightSwitchButtons.length; i++) {//same things as for the left switch buttons, see above

			b.rightSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.rightDisplayPanel,
								textInfo,
								"Pokemon Switch info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + p2.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.rightSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p2Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject2){
						if(b.confirm1==true){//notifys the object in main if the other player has selected
							myObject2.notify();
						}else{
							b.confirm2 =true;//if the other player hasn't confirmed, confirms your own
						}
					}

				}
			});


		}
		b.repaint(p1,p2);
		while (gameNotOver) {

//**for p1***
			try {
				synchronized (myObject2) {
					myObject2.wait();//waits until it is notified by a button
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			b.confirm2=false;
			b.confirm1=false;
//buttons betweens 0 and 4 are for moves, buttons between 4 and 8 are for switches
			if (b.p1Selection >= 4 && P1numberOfFaintedMons < 5) {
				p1WillSwitch = true;
				p1SwitchIn = b.p1Selection - 4;

				b.p1Selection = -1;
			} else if (b.p1Selection < 4 && b.p1Selection >= 0) {


				p1SelectedMoveIndex = b.p1Selection;

				b.p1Selection = -1;
			}



//**for p2**
//same thing as p1



		if (b.p2Selection >=4 && P2numberOfFaintedMons < 5) {
			p2WillSwitch = true;
			p2SwitchIn = b.p2Selection - 4;

			b.p2Selection = -1;
		} else if (b.p2Selection < 4 && b.p2Selection >= 0) {


			p2SelectedMoveIndex = b.p2Selection;

			b.p2Selection = -1;
		}

				b.log("\n------\n");

			if (p1WillSwitch && p2WillSwitch) {//calculates who switches first based on whos faster
			//kind of useless since you cant see it and use the information it gives you since there are no
			//animations but, good to have implemented incase we add them
			if (p1.getCurrentMon().getSpeed() > p2.getCurrentMon().getSpeed()) {//self explanatory
				p1.getCurrentMon().resetBoosts();//you reset your boosts when you switch out
				p2.getCurrentMon().resetBoosts();
				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
				p1WillSwitch = false;
				p2WillSwitch = false;
			} else if (p2.getCurrentMon().getSpeed() > p1.getCurrentMon().getSpeed()) {//self explanatory

				p1.getCurrentMon().resetBoosts();
				p2.getCurrentMon().resetBoosts();
				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
				p1WillSwitch = false;
				p2WillSwitch = false;
//self explanatory
			} else {
				if (Math.random() > 0.5) {
					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;
//self explanatory
				} else {
					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;

				}
			}
//self explanatory
		} else if (p1WillSwitch) {
			p1.getCurrentMon().resetBoosts();
//switching always outspeeds anything else
			p1.switchOut(p1.getPokemon()[p1SwitchIn]);
			b.repaint(p1, p2);//repaints the display
			p2.fight(p2SelectedMoveIndex);
			p1WillSwitch = false;
			p2WillSwitch = false;

		} else if (p2WillSwitch) {

			p2.getCurrentMon().resetBoosts();
			p2.switchOut(p2.getPokemon()[p2SwitchIn]);
			b.repaint(p1, p2);//repaints the display
			p1.fight(p1SelectedMoveIndex);
			p1WillSwitch = false;
			p2WillSwitch = false;

		} else {
//self explanatory
			if(p1SelectedMoveIndex==4){

				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");
				System.out.println("player1's index was 4 somehow");

			}
			if(p2SelectedMoveIndex==4){
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");
				System.out.println("player2's index was 4 somehow");

			}
			int x = b.calc.calculateWhoGoesFirst(p1, p2, p1.getCurrentMon().getMoves()[p1SelectedMoveIndex], p2.getCurrentMon().getMoves()[p2SelectedMoveIndex]);
			if (x == 1) {
				p1.fight(p1SelectedMoveIndex);
				p2.fight(p2SelectedMoveIndex);
			} else if (x == 2) {
				p2.fight(p2SelectedMoveIndex);
				p1.fight(p1SelectedMoveIndex);
			} else {
				int y=5;
				while (y>0) {
					System.out.println("Something went wrong");//questioinable style, but gets your attention when it happens, and we have never had it happen
					y--;
				}
			}
		}
		b.repaint(p1, p2);//repaints the display
			try {
				synchronized (myObject3) {
					myObject3.wait();//waits until it is notified by a button
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (p1.isDefeated()) {//self explanatory
			gameNotOver = false;
			popup.showMessageDialog(b.mainPanel,
					"Player 2 Won the game!",
					"Game Finished",
					2,
					(new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
			b.frame.dispatchEvent(new WindowEvent(b.frame, WindowEvent.WINDOW_CLOSING));
			break;
		}
		if (p2.isDefeated()) {//self explanatory
			gameNotOver = false;
			popup.showMessageDialog(b.mainPanel,
					"Player 1 Won the game!",
					"Game Finished",
					2,
					(new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
			b.frame.dispatchEvent(new WindowEvent(b.frame, WindowEvent.WINDOW_CLOSING));
			break;
		}
		if (p1.getCurrentMon().getHealth() <= 0) {//self explanatory
			P1numberOfFaintedMons++;

			String x[] = new String[6 - b.P1numberOfFaintedMons];
			int z = 0;
			for (int i = 0; i < p1.getPokemon().length; i++) {
				if (p1.getPokemon()[i].getHealth() <= 0) {//doesnt add to list if dead

				} else if (p1.getPokemon()[i] == p1.getCurrentMon()) {//doesnt add to list if already in play(would be dead anyway)

				} else {


					x[z] = (i + 1) + ") " + p1.getPokemon()[i].getName() + "\n";//adds to list
					z++;

				}
			}

			String y;
			do {
				y = (String) popup.showInputDialog(b.leftDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in."//self explanatory
						, "Pokemon defeated",
						2,
						new ImageIcon(new ImageIcon("Images/Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x,
						"Please select a Pokemon");
			}
			while (y == null);//prevents you from exiting without giving an answer

			p1SwitchIn = Integer.parseInt(String.valueOf(y.charAt(0)));//gets the first character, which is  its number in your team
			p1.switchOut(p1.getPokemon()[p1SwitchIn - 1]);//switches in your selection

			p1SwitchIn = -1;//resets selection
			b.repaint(p1, p2);
		}

            if(p2.getCurrentMon().getHealth()<=0){//same thing as for p1
				P2numberOfFaintedMons++;
            	int firstOccurence=-1;
            	int z=0;
				String [] x = new String[6-b.P2numberOfFaintedMons];
				for (int i =0;i<p2.getPokemon().length;i++){
					if(p2.getPokemon()[i].getHealth()<=0){

					}
					else if(p2.getPokemon()[i]==p2.getCurrentMon()){

					}
					else{
						if (firstOccurence==-1){
							firstOccurence=i;
						}
						x[z]=(i+1)+") "+p2.getPokemon()[i].getName()+"\n";
						z++;
					}
				}
				String y;
						do{y= (String) popup.showInputDialog(b.rightDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in."
						,"Pokemon defeated",
						2,
						new ImageIcon (new ImageIcon("Images/Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x,
						"Please select a Pokemon");}
						while(y==null);

				p2SwitchIn=Integer.parseInt(String.valueOf(y.charAt(0)));
            	p2.switchOut(p2.getPokemon()[p2SwitchIn-1]);

				p2SwitchIn=-1;
				b.repaint(p1, p2);

            }
			if((P1numberOfFaintedMons==5||P2numberOfFaintedMons==5)&&!dangerZoneActivated){
				b.musicPlayer.stop();
				b.musicPlayer.play("Music/Pinch in Battle!.wav");
				dangerZoneActivated=true;
			}
        }

    }
    //repaints the screen with updates info
    private void repaint(Player p1, Player p2) {

		P1pokemon.setText(p1.getCurrentMon().getName());//self explanatory

			P1OpposingPokemon.setText(p2.getCurrentMon().getName());//self explanatory

			if(P1pokemonHealthBar.getPokemon() != p1.getCurrentMon()) {//self explanatory
				P1pokemonHealthBar.setPokemon(p1.getCurrentMon());
				P1pokemonHealthBar.setXOffset(true);
			}
			P1pokemonHealthBar.repaint();

			if(P1opposingPokemonHealthBar.getPokemon() != p2.getCurrentMon()) {//self explanatory
				P1opposingPokemonHealthBar.setPokemon(p2.getCurrentMon());
				P1opposingPokemonHealthBar.setXOffset(false);
			}
			P1opposingPokemonHealthBar.repaint();

			Icon pic = null;


				P1pokemonImage.setIcon(new ImageIcon("Images/Sprites/SpritesBack/" + p1.getCurrentMon().getID() + "-back.gif"));//self explanatory



				P1opposingPokemonImage.setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));//self explanatory


			for (int i =0;i<leftMoveButtons.length;i++){
				leftMoveButtons[i].setOpaque(true);
				leftMoveButtons[i].setText(p1.getCurrentMon().getMoves()[i].getName() + " " + p1.getCurrentMon().getMoves()[i].getPP() + "/" + p1.getCurrentMon().getMoves()[i].getBasePP());//self explanatory
				int num=leftMoveButtons[i].getNum();
				leftMoveButtons[i].setIcon(new ImageIcon("Images/Types-Classic/" + p1.getCurrentMon().getMoves()[i].getType() + ".png"));
				leftMoveButtons[i].setRolloverIcon(new ImageIcon("Images/Categories/" + p1.getCurrentMon().getMoves()[i].getCategory() + ".png"));
				if(p1.getCurrentMon().getMoves()[i].getPP() == 0) {
					leftMoveButtons[i].setEnabled(false);
				}
				else {
					leftMoveButtons[i].setEnabled(true);
				}
				//foreground is the text, background is behind the button
				double howEffective=calc.typeModifier(
						calc.getIntFromType(p1.getCurrentMon().getMoves()[i].getType()),
						calc.getIntFromType(p2.getCurrentMon().getType1()),
						calc.getIntFromType(p2.getCurrentMon().getType2()));
				if(howEffective==4.0){
					leftMoveButtons[i].setBackground(new Color(255,0,0));
					leftMoveButtons[i].setForeground(new Color(255,0,0));
				}
				else if(howEffective==2.0){
					leftMoveButtons[i].setBackground(new Color(255,127,0));
					leftMoveButtons[i].setForeground(new Color(255,127,0));

				}
				else if(howEffective==1.0){
					leftMoveButtons[i].setBackground(new Color(255,255,255));
					leftMoveButtons[i].setForeground(new Color(0,0,0));
				}
				else if(howEffective==0.5){
					leftMoveButtons[i].setBackground(new Color(0,255,255));
					leftMoveButtons[i].setForeground(new Color(0,127,255));
				}
				else if(howEffective==0.25){
					leftMoveButtons[i].setBackground(new Color(0,0,255));
					leftMoveButtons[i].setForeground(new Color(0,0,255));
				}
				else if(howEffective==0.0){
					leftMoveButtons[i].setBackground(new Color(0,0,0));
					leftMoveButtons[i].setForeground(new Color(0,0,0));


				}
			}
			for (int i =0;i<leftSwitchButtons.length;i++){
				leftSwitchButtons[i].setText(p1.getPokemon()[i].getName());
				ImageIcon pokemonPic = new ImageIcon("Images/Sprites/SpritesFront/" + p1.getPokemon()[i].getID() + ".gif");
				int switchPanelWidth=frame.getWidth()-leftText.getWidth()-rightPanel.getWidth();
int switchPanelHeight=frame.getHeight()-leftMovePanel.getHeight()-leftDisplayPanel.getHeight();
				double multiplier;
				double linkWithHeight=(double)switchPanelHeight/pokemonPic.getIconHeight()/12;
				double linkWithWidth=(double)switchPanelWidth/pokemonPic.getIconHeight()/12;
				if(linkWithHeight<linkWithWidth) {
					multiplier =linkWithHeight;
				}
				else{
					multiplier =linkWithWidth;
				}
				leftSwitchButtons[i].setIcon(new ImageIcon((pokemonPic).getImage().getScaledInstance((int)(pokemonPic.getIconWidth() * multiplier),
						(int)(pokemonPic.getIconHeight() * multiplier), Image.SCALE_DEFAULT)));
				if(p1.getPokemon()[i].getHealth() <= 0 || p1.getPokemon()[i] == p1.getCurrentMon()) {//self explanatory
					leftSwitchButtons[i].setEnabled(false);
					leftSwitchButtons[i].setOpaque(false);
				}
				else{
					leftSwitchButtons[i].setEnabled(true);
					leftSwitchButtons[i].setOpaque(true);
					if (p1.getPokemon()[leftSwitchButtons[i].getNum() - 4].getSpeed() > p2.getCurrentMon().getSpeed()) {

						leftSwitchButtons[i].setBackground(Color.RED);
					}
					else {
						leftSwitchButtons[i].setBackground(Color.WHITE);
					}
					if (!(p1.getPokemon()[leftSwitchButtons[i].getNum() - 4].getStatus().equalsIgnoreCase(""))) {

						leftSwitchButtons[i].setForeground(Color.BLUE);
					}
					else {
						leftSwitchButtons[i].setForeground(Color.BLACK);
					}
				}
			}




		P2pokemon.setText(p2.getCurrentMon().getName());//self explanatory

		P2OpposingPokemon.setText(p1.getCurrentMon().getName());//self explanatory

		if(P2pokemonHealthBar.getPokemon() != p2.getCurrentMon()) {
			P2pokemonHealthBar.setPokemon(p2.getCurrentMon());//self explanatory
			P2pokemonHealthBar.setXOffset(true);
		}
		P2pokemonHealthBar.repaint();

		if(P2opposingPokemonHealthBar.getPokemon() != p1.getCurrentMon()) {
			P2opposingPokemonHealthBar.setPokemon(p1.getCurrentMon());
			P2opposingPokemonHealthBar.setXOffset(false);
		}//self explanatory
		P2opposingPokemonHealthBar.repaint();

		pic = null;

		P2pokemonImage.setIcon(new ImageIcon("Images/Sprites/SpritesBack/" + p2.getCurrentMon().getID() + "-back.gif"));



		P2opposingPokemonImage.setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif"));


		for (int i =0;i<rightMoveButtons.length;i++){//self explanatory
			rightMoveButtons[i].setOpaque(true);
			rightMoveButtons[i].setText(p2.getCurrentMon().getMoves()[i].getName() + " " + p2.getCurrentMon().getMoves()[i].getPP() + "/" + p2.getCurrentMon().getMoves()[i].getBasePP());
			int num=rightMoveButtons[i].getNum();
			rightMoveButtons[i].setIcon(new ImageIcon("Images/Types-Classic/" + p2.getCurrentMon().getMoves()[i].getType() + ".png"));
			rightMoveButtons[i].setRolloverIcon(new ImageIcon("Images/Categories/" + p2.getCurrentMon().getMoves()[i].getCategory() + ".png"));
			if(p2.getCurrentMon().getMoves()[i].getPP() == 0) {
				rightMoveButtons[i].setEnabled(false);
			}
			else {
				rightMoveButtons[i].setEnabled(true);
			}
			double howEffective=calc.typeModifier(
					calc.getIntFromType(p2.getCurrentMon().getMoves()[i].getType()),
					calc.getIntFromType(p1.getCurrentMon().getType1()),
					calc.getIntFromType(p1.getCurrentMon().getType2()));
			if(howEffective==4.0){
				rightMoveButtons[i].setBackground(new Color(255,0,0));
				rightMoveButtons[i].setForeground(new Color(255,0,0));
			}
			else if(howEffective==2.0){
				rightMoveButtons[i].setBackground(new Color(255,127,0));
				rightMoveButtons[i].setForeground(new Color(255,127,0));

			}
			else if(howEffective==1.0){
				rightMoveButtons[i].setBackground(new Color(255,255,255));
				rightMoveButtons[i].setForeground(new Color(0,0,0));
			}
			else if(howEffective==0.5){
				rightMoveButtons[i].setBackground(new Color(0,255,255));
				rightMoveButtons[i].setForeground(new Color(0,127,255));
			}
			else if(howEffective==0.25){
				rightMoveButtons[i].setBackground(new Color(0,0,255));
				rightMoveButtons[i].setForeground(new Color(0,0,255));
			}
			else if(howEffective==0.0){
				rightMoveButtons[i].setBackground(new Color(0,0,0));
				rightMoveButtons[i].setForeground(new Color(0,0,0));


			}
		}
		for (int i =0;i<rightSwitchButtons.length;i++) {
			rightSwitchButtons[i].setText(p2.getPokemon()[i].getName());//self explanatory
			ImageIcon pokemonPic = new ImageIcon("Images/Sprites/SpritesFront/" + p2.getPokemon()[i].getID() + ".gif");
			int switchPanelWidth=frame.getWidth()-rightText.getWidth()-leftPanel.getWidth();
			int switchPanelHeight=frame.getHeight()-rightMovePanel.getHeight()-rightDisplayPanel.getHeight();
			double multiplier;
			double linkWithHeight=(double)switchPanelHeight/pokemonPic.getIconHeight()/12;
			double linkWithWidth=(double)switchPanelWidth/pokemonPic.getIconHeight()/12;
			if(linkWithHeight<linkWithWidth) {
				multiplier =linkWithHeight;
			}
			else{
				multiplier =linkWithWidth;
			}
			rightSwitchButtons[i].setIcon(new ImageIcon((pokemonPic).getImage().getScaledInstance((int)(pokemonPic.getIconWidth() * multiplier),
					(int)(pokemonPic.getIconHeight() * multiplier), Image.SCALE_DEFAULT)));
			if (p2.getPokemon()[i].getHealth() <= 0 || p2.getPokemon()[i] == p2.getCurrentMon()) {
				rightSwitchButtons[i].setEnabled(false);
				rightSwitchButtons[i].setOpaque(false);
			} else {
				rightSwitchButtons[i].setEnabled(true);
				rightSwitchButtons[i].setOpaque(true);
				if (p2.getPokemon()[rightSwitchButtons[i].getNum() - 4].getSpeed() > p1.getCurrentMon().getSpeed()) {

					rightSwitchButtons[i].setBackground(Color.RED);
				}
				else {
					rightSwitchButtons[i].setBackground(Color.WHITE);
				}
				if (!(p2.getPokemon()[rightSwitchButtons[i].getNum() - 4].getStatus().equalsIgnoreCase(""))) {

					rightSwitchButtons[i].setForeground(Color.BLUE);
				}
				else {
					rightSwitchButtons[i].setForeground(Color.BLACK);
				}
			}
		}
		animateHPChange();
	}

    //used for animating the hp change of a pokemon using its health bar
	private void animateHPChange() {
    	timer = new Timer(healthBarDecreationRate, new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if(!P1pokemonHealthBar.isChanging() && !P1opposingPokemonHealthBar.isChanging()
    					&& !P2pokemonHealthBar.isChanging() && !P2opposingPokemonHealthBar.isChanging()) {
    				timer.stop();
    				synchronized (myObject3)
					{myObject3.notify();}
    			}
    			if(P1pokemonHealthBar.isChanging()) {
    				P1pokemonHealthBar.repaint();
    			}
    			if(P1opposingPokemonHealthBar.isChanging()) {
    				P1opposingPokemonHealthBar.repaint();
    			}
    			if(P2pokemonHealthBar.isChanging()) {
    				P2pokemonHealthBar.repaint();
    			}
    			if(P2opposingPokemonHealthBar.isChanging()) {
    				P2opposingPokemonHealthBar.repaint();
    			}
    		}
    	});
    	timer.setInitialDelay(500);
    	timer.start();
    }
    // This method lets you choose your pokemon and their moves
	private void teamBuilder(){
		JPanel mainPanelTB=new JPanel();// the main panel with all of the the components inside
		mainPanelTB.setLayout(new GridLayout(1,2));
		frame.add(mainPanelTB);//using the frame from the field so as not to use multiple Jframes
		JPanel leftPanelTB=new JPanel(new GridLayout(2,1));// the panel for P1
		JPanel rightPanelTB=new JPanel(new GridLayout(2,1));// the panel for P2
		JPanel leftDisplay=new JPanel(new GridLayout(1,6)),rightDisplay=new JPanel(new GridLayout(1,6));// the display of the pokemon when you choose them
		mainPanelTB.add(leftPanelTB);
		mainPanelTB.add(rightPanelTB);
		leftPanelTB.add(leftDisplay);
		rightPanelTB.add(rightDisplay);
		JPanel leftUI=new JPanel(new GridBagLayout()), rightUI=new JPanel(new GridBagLayout());// the panel for all the user input
		leftPanelTB.add(leftUI);
		rightPanelTB.add(rightUI);
		GridBagConstraints constraints=new GridBagConstraints();//we used a  grid bag layout for the UI
		JLabel[] leftPanelImages=new JLabel[6], rightPanelImages=new JLabel[6];//self explanatory
		JTextArea[] rightPokemonInputs=new JTextArea[6], leftPokemonInputs=new JTextArea[6];//self explanatory
		KTextField[][] rightMoveInputs=new KTextField[6][4],leftMoveInputs=new KTextField[6][4];//self explanatory
		Button[] rightConfirmationButtons=new Button[6],leftConfirmationButtons=new Button[6];//confirms a pokemon
		JButton rightValidationButton=new JButton("Confirm Team"),leftValidationButton=new JButton("Confirm Team");//confirms the entire team

		Pokemon[] p1Pokemon=new Pokemon[6],p2Pokemon=new Pokemon[6];//self explanatory
		JButton leftRandomButton=new JButton("Random!"),rightRandomButton=new JButton("Random!");//buttons to create a random team will be implemented soon
		JButton leftPresetTeam1=new JButton("Preset Team 1"), leftPresetTeam2=new JButton("Preset Team 2"),leftPresetTeam3=new JButton("Preset Team 3"),
				rightPresetTeam1=new JButton("Preset Team 4"),rightPresetTeam2=new JButton("Preset Team 5"),rightPresetTeam3=new JButton("Preset Team 6");

	for (int i = 0; i < leftPanelImages.length; i++) {

		ImageIcon pic = new ImageIcon("Images/QuestionMark.png");//self explanatory
			pic = new ImageIcon((pic).getImage().getScaledInstance((int)(pic.getIconWidth() * 0.1), (int)(pic.getIconHeight() * 0.1), Image.SCALE_DEFAULT));//self explanatory
		leftPanelImages[i] = new JLabel( pic);//self explanatory
		leftDisplay.add(leftPanelImages[i]);//adding the image to the display
	}
	for (int i = 0; i < rightPanelImages.length; i++) {

		ImageIcon pic = new ImageIcon("Images/QuestionMark.png");//self explanatory
		pic = new ImageIcon((pic).getImage().getScaledInstance((int)(pic.getIconWidth() * 0.1), (int)(pic.getIconHeight() * 0.1), Image.SCALE_DEFAULT));//self explanatory
		rightPanelImages[i] = new JLabel(pic);//self explanatory
		rightDisplay.add(rightPanelImages[i]);//adding the image to the display
	}
		for (int i = 0; i < leftPokemonInputs.length; i++) {//i is the column you are on

			leftPokemonInputs[i] = new JTextArea("Pokemon");
			leftPokemonInputs[i].setRows(3);
			constraints.gridx = i;//your current column
			constraints.gridy = 0;//1st row
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(5, 5, 5, 0);
			leftUI.add(leftPokemonInputs[i], constraints);//adding to the ui
		}
		for (int i = 0; i < 6; i++) {//keeps track of the column you are on

			for (int k = 0; k < 4; k++) {//keeps track of the row
				leftMoveInputs[i][k] = new KTextField("Move");
				leftMoveInputs[i][k].setColumn(i);
				leftMoveInputs[i][k].setRow(k);
				leftMoveInputs[i][k].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							int x = ((KTextField) e.getSource()).getColumn();
							int y=((KTextField) e.getSource()).getRow();
							String textInfo = leftMoveInputs[x][y].getText();

							try {
								Move z=new Move(textInfo);
								JOptionPane.showMessageDialog(leftPanelTB,//in the left panel
										z.toString(),//see above
										"Move Info",//self explanatory
										2,//self explanatory
										(new ImageIcon(new ImageIcon("Images/Types/" + //the icon depends on the type of the move
												z.getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
							}
							catch (Exception ex){
								JOptionPane.showMessageDialog(leftPanelTB,
										"That was not a valid move",
										"Invalid Move",//self explanatory
										2,//self explanatory
										(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));//self explanatory
							}

						}
					}
				});
				constraints.gridx = i;//the column you are on
				constraints.gridy = k + 1;//row+1 because the pokemon input are in the first row
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.weightx = 0.5;
				constraints.weighty = 0.0;
				constraints.anchor = GridBagConstraints.PAGE_START;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.insets = new Insets(5, 5, 5, 0);
				leftUI.add(leftMoveInputs[i][k], constraints);
			}
			leftConfirmationButtons[i] = new Button("Confirm Pokemon", i);
			leftConfirmationButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					int x = ((Button) e.getSource()).getNum();//x is the column its from
					boolean validPokemon=true;//if the user chooses a pokemon that is valid
					if(calc.isPokemon(leftPokemonInputs[x].getText())) {
						
					}
					else {//if the input is not a pokemon, do this
						leftConfirmationButtons[x].setProblem(true);
						validPokemon=false;
						String columnWordEnd = "th";
						if(x+1 == 1) {
							columnWordEnd = "st";
						}
						else if(x+1 == 2) {
							columnWordEnd = "nd";
						}
						else if(x+1 == 3) {
							columnWordEnd = "rd";
						}
						JOptionPane.showMessageDialog(leftDisplay,
								"Your pokemon in the "+(x+1)+columnWordEnd+" column is not a valid pokemon",
								"Invalid Pokemon",//self explanatory
								2,//self explanatory
								(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));//self explanatory
					}
					boolean validMove=true;//if the user chooses a move that is valid
					for (int i = 0; i < 4; i++) {//length is 4
						if(calc.isMove(leftMoveInputs[x][i].getText())
								&&calc.hasMove(new Pokemon(leftPokemonInputs[x].getText()),new Move(leftMoveInputs[x][i].getText()))
						){

						}
						else{//if the input is not a move, do this
							leftConfirmationButtons[x].setProblem(true);
							validMove=false;
							String rowWordEnd = "th", columnWordEnd = "th";
							if(i+1 == 1) {
								rowWordEnd = "st";
							}
							else if(i+1 == 2) {
								rowWordEnd = "nd";
							}
							else if(i+1 == 3) {
								rowWordEnd = "rd";
							}
							if(x+1 == 1) {
								columnWordEnd = "st";
							}
							else if(x+1 == 2) {
								columnWordEnd = "nd";
							}
							else if(x+1 == 3) {
								columnWordEnd = "rd";
							}
							JOptionPane.showMessageDialog(leftDisplay,
									"Your move in the "+(x+1)+columnWordEnd+" column and "+(i+1)+rowWordEnd+" row is not a valid move",//yes I know this doesnt work for 1 2 and 3,
									// but its really not a big deal, you get the point
									"Invalid Move",//self explanatory
									2,//self explanatory
									(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));//self explanatory
						}
					}
					if(validPokemon && validMove) {//do this if the pokemon and the move is valid
						leftConfirmationButtons[x].setProblem(false);
						Pokemon pokemon = new Pokemon(Battle.this, leftPokemonInputs[x].getText());//get the text from the pokemon input at column x
						Move[] moves = new Move[4];
						for (int i = 0; i < moves.length; i++) {//length is 4
							moves[i] = new Move(leftMoveInputs[x][i].getText());
						}
						pokemon.setMoves(moves);
						p1Pokemon[x] = pokemon;
						leftPanelImages[x].setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + pokemon.getID() + ".gif"));
					}
				}
			});
			constraints.gridx = i;//the column you are on
			constraints.gridy = 5;//should be the 6th row of things in this column
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0.0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(50, 0, 5, 0);

			leftUI.add(leftConfirmationButtons[i], constraints);//adding to the ui with these constraitns
		}
		constraints.gridx = 0;//the column you are on
		constraints.gridy = 6;//should be the 6th row of things in this column
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(50, 5, 5, 0);
		leftValidationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean problem=false;
				for (int i = 0; i < leftConfirmationButtons.length; i++) {
					leftConfirmationButtons[i].doClick();//clicks everybutton
					if(leftConfirmationButtons[i].hasProblem()){
							problem=true;//if there is a problem
					}
					else {
//if theres isnt a problem, turn off all the buttons for htis player
						leftConfirmationButtons[i].setEnabled(false);
						leftPokemonInputs[i].setEnabled(false);
						for (int k = 0; k < leftMoveInputs[i].length; k++) {

							leftMoveInputs[i][k].setEnabled(false);
						}

					}

				}//if there isn't a problem
				if(problem==false){
					synchronized (myObject1){
						if(confirm2==true){//notifys the object in main if the other player has selected
						myObject1.notify();
					}else{
						confirm1 =true;//if the other player hasn't confirmed, confirms your own
					}
					}
			}
			}
		});
		leftUI.add(leftValidationButton, constraints);//adds to UI
		leftPresetTeam1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//manually coded preset team
				//each presetTeamButton is the same except the pokemon are different
				leftPokemonInputs[0].setText("Landorus-therian");
				leftMoveInputs[0][0].setText("Earthquake");
				leftMoveInputs[0][1].setText("Stone Edge");
				leftMoveInputs[0][2].setText("Swords Dance");
				leftMoveInputs[0][3].setText("Fly");


				leftPokemonInputs[1].setText("Volcarona");
				leftMoveInputs[1][0].setText("Quiver Dance");
				leftMoveInputs[1][1].setText("Fire Blast");
				leftMoveInputs[1][2].setText("Bug Buzz");
				leftMoveInputs[1][3].setText("Roost");


				leftPokemonInputs[2].setText("Dragonite");
				leftMoveInputs[2][0].setText("Earthquake");
				leftMoveInputs[2][1].setText("Extreme Speed");
				leftMoveInputs[2][2].setText("Fly");
				leftMoveInputs[2][3].setText("Dragon Dance");


				leftPokemonInputs[3].setText("Charizard-mega-x");
				leftMoveInputs[3][0].setText("Flare Blitz");
				leftMoveInputs[3][1].setText("Dragon claw");
				leftMoveInputs[3][2].setText("Earthquake");
				leftMoveInputs[3][3].setText("dragon dance");

				leftPokemonInputs[4].setText("Magearna");
				leftMoveInputs[4][0].setText("Shift Gear");
				leftMoveInputs[4][1].setText("Thunderbolt");
				leftMoveInputs[4][2].setText("Ice Beam");
				leftMoveInputs[4][3].setText("Focus Blast");

				leftPokemonInputs[5].setText("Tapu Bulu");
				leftMoveInputs[5][0].setText("Wood Hammer");
				leftMoveInputs[5][1].setText("Horn Leech");
				leftMoveInputs[5][2].setText("Superpower");
				leftMoveInputs[5][3].setText("Stone Edge");
				leftConfirmationButtons[0].doClick();
				leftConfirmationButtons[1].doClick();
				leftConfirmationButtons[2].doClick();
				leftConfirmationButtons[3].doClick();
				leftConfirmationButtons[4].doClick();
				leftConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		leftUI.add(leftPresetTeam1, constraints);//adds to UI

		leftPresetTeam2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftPokemonInputs[5].setText("Kommo-o");
				leftMoveInputs[5][0].setText("Clanging scales");
				leftMoveInputs[5][1].setText("Close Combat");
				leftMoveInputs[5][2].setText("Poison Jab");
				leftMoveInputs[5][3].setText("Dragon dance");


				leftPokemonInputs[1].setText("Salazzle");
				leftMoveInputs[1][0].setText("Nasty Plot");
				leftMoveInputs[1][1].setText("Sludge Wave");
				leftMoveInputs[1][2].setText("Flamethrower");
				leftMoveInputs[1][3].setText("Dragon Pulse");


				leftPokemonInputs[2].setText("Xurkitree");
				leftMoveInputs[2][0].setText("Thunderbolt");
				leftMoveInputs[2][1].setText("Energy Ball");
				leftMoveInputs[2][2].setText("Power whip");
				leftMoveInputs[2][3].setText("Tail Glow");


				leftPokemonInputs[3].setText("Scizor-mega");
				leftMoveInputs[3][0].setText("Swords Dance");
				leftMoveInputs[3][1].setText("Bullet Punch");
				leftMoveInputs[3][2].setText("Roost");
				leftMoveInputs[3][3].setText("Superpower");

				leftPokemonInputs[4].setText("Tapu Fini");
				leftMoveInputs[4][0].setText("Scald");
				leftMoveInputs[4][1].setText("Moonblast");
				leftMoveInputs[4][2].setText("Calm Mind");
				leftMoveInputs[4][3].setText("Ice Beam");

				leftPokemonInputs[0].setText("Tyranitar-Mega");
				leftMoveInputs[0][0].setText("Fire Punch");
				leftMoveInputs[0][1].setText("Earthquake");
				leftMoveInputs[0][2].setText("Stone edge");
				leftMoveInputs[0][3].setText("Crunch");



				leftConfirmationButtons[0].doClick();
				leftConfirmationButtons[1].doClick();
				leftConfirmationButtons[2].doClick();
				leftConfirmationButtons[3].doClick();
				leftConfirmationButtons[4].doClick();
				leftConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		leftUI.add(leftPresetTeam2, constraints);//adds to UI

		leftPresetTeam3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftPokemonInputs[0].setText("Azelf");
				leftMoveInputs[0][0].setText("Nasty Plot");
				leftMoveInputs[0][1].setText("Psychic");
				leftMoveInputs[0][2].setText("Flamethrower");
				leftMoveInputs[0][3].setText("Dazzling Gleam");


				leftPokemonInputs[1].setText("Gyarados-mega");
				leftMoveInputs[1][0].setText("Crunch");
				leftMoveInputs[1][1].setText("Earthquake");
				leftMoveInputs[1][2].setText("Hydro Pump");
				leftMoveInputs[1][3].setText("Dragon Dance");


				leftPokemonInputs[2].setText("Kartana");
				leftMoveInputs[2][0].setText("Leaf Blade");
				leftMoveInputs[2][1].setText("Sacred Sword");
				leftMoveInputs[2][2].setText("Night Slash");
				leftMoveInputs[2][3].setText("Swords Dance");


				leftPokemonInputs[3].setText("Volcarona");
				leftMoveInputs[3][0].setText("Fiery Dance");
				leftMoveInputs[3][1].setText("Bug Buzz");
				leftMoveInputs[3][2].setText("Giga Drain");
				leftMoveInputs[3][3].setText("Quiver Dance");

				leftPokemonInputs[4].setText("Magnezone");
				leftMoveInputs[4][0].setText("Thunderbolt");
				leftMoveInputs[4][1].setText("Thunder wave");
				leftMoveInputs[4][2].setText("Flash Cannon");
				leftMoveInputs[4][3].setText("Explosion");

				leftPokemonInputs[5].setText("Excadrill");
				leftMoveInputs[5][0].setText("Earthquake");
				leftMoveInputs[5][1].setText("Smart Strike");
				leftMoveInputs[5][2].setText("Rock Tomb");
				leftMoveInputs[5][3].setText("Swords Dance");


				leftConfirmationButtons[0].doClick();
				leftConfirmationButtons[1].doClick();
				leftConfirmationButtons[2].doClick();
				leftConfirmationButtons[3].doClick();
				leftConfirmationButtons[4].doClick();
				leftConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		leftUI.add(leftPresetTeam3, constraints);//adds to UI






		//**********PLAYER 2******************//
		//this isn't really any different from player 1, except its on the right side, so i wont be commenting this
		//they are practically identical so just look at it for player 1








		for (int i = 0; i < rightPokemonInputs.length; i++) {
		rightPokemonInputs[i] = new JTextArea("Pokemon");
		rightPokemonInputs[i].setRows(3);
		constraints.gridx = i;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);

		rightUI.add(rightPokemonInputs[i], constraints);
	}
	for (int i = 0; i < 6; i++) {//keeps track of the column you are on

		for (int k = 0; k < 4; k++) {//keeps track of the row
			rightMoveInputs[i][k] = new KTextField("Move");
			rightMoveInputs[i][k].setColumn(i);
			rightMoveInputs[i][k].setRow(k);
			rightMoveInputs[i][k].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((KTextField) e.getSource()).getColumn();
						int y=((KTextField) e.getSource()).getRow();
						String textInfo = rightMoveInputs[x][y].getText();

						try {
							Move z=new Move(textInfo);
							JOptionPane.showMessageDialog(rightPanelTB,//in the right panel
									z.toString(),//see above
									"Move Info",//self explanatory
									2,//self explanatory
									(new ImageIcon(new ImageIcon("Images/Types/" + //the icon depends on the type of the move
											z.getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
						}
						catch (Exception ex){
							JOptionPane.showMessageDialog(rightPanelTB,
									"That was not a valid move",
									"Invalid Move",//self explanatory
									2,//self explanatory
									(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));//self explanatory
						}

					}
				}
			});
			constraints.gridx = i;//the column you are on
			constraints.gridy = k + 1;//row+1 because the pokemon input are in the first row
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0.0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(5, 5, 5, 0);

			rightUI.add(rightMoveInputs[i][k], constraints);
		}
		rightConfirmationButtons[i] = new Button("Confirm Pokemon", i);
		rightConfirmationButtons[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{

					int x = ((Button) e.getSource()).getNum();//x is the column its from
					boolean validPokemon=true;
					if(calc.isPokemon(rightPokemonInputs[x].getText())) {
						
					} else {
						rightConfirmationButtons[x].setProblem(true);
						validPokemon=false;
						String columnWordEnd = "th";
						if(x+1 == 1) {
							columnWordEnd = "st";
						}
						else if(x+1 == 2) {
							columnWordEnd = "nd";
						}
						else if(x+1 == 3) {
							columnWordEnd = "rd";
						}
						JOptionPane.showMessageDialog(rightDisplay,
								"Your pokemon in the "+(x+1)+columnWordEnd+" column is not a valid pokemon",
								"Invalid Pokemon",
								2,
								(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
					}
					boolean validMove = true;
					for (int i = 0; i < 4; i++) {//length is 4
						if(calc.isMove(rightMoveInputs[x][i].getText())
								&&calc.hasMove(new Pokemon(rightPokemonInputs[x].getText()),new Move(rightMoveInputs[x][i].getText()))
						){

						} else {
							rightConfirmationButtons[x].setProblem(true);
							validMove = false;
							String rowWordEnd = "th", columnWordEnd = "th";
							if(i+1 == 1) {
								rowWordEnd = "st";
							}
							else if(i+1 == 2) {
								rowWordEnd = "nd";
							}
							else if(i+1 == 3) {
								rowWordEnd = "rd";
							}
							if(x+1 == 1) {
								columnWordEnd = "st";
							}
							else if(x+1 == 2) {
								columnWordEnd = "nd";
							}
							else if(x+1 == 3) {
								columnWordEnd = "rd";
							}
							JOptionPane.showMessageDialog(rightDisplay,
									"Your move in the " + (x + 1) + columnWordEnd + " column and " + (i + 1) + rowWordEnd + " row is not a valid move",
									"Invalid Move",
									2,
									(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));


						}
					}
					if (validPokemon && validMove) {
						rightConfirmationButtons[x].setProblem(false);
						Pokemon pokemon = new Pokemon(Battle.this, rightPokemonInputs[x].getText());//get the text from the pokemon input at column x
						Move[] moves = new Move[4];
						for (int i = 0; i < moves.length; i++) {//length is 4
							moves[i] = new Move(rightMoveInputs[x][i].getText());
						}
						pokemon.setMoves(moves);
						p2Pokemon[x] = pokemon;
						rightPanelImages[x].setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + pokemon.getID() + ".gif"));
					}
				}
			}
		});
		constraints.gridx = i;//the column you are on
		constraints.gridy = 5;//should be the 6th row of things in this column
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(50, 5, 5, 0);

		rightUI.add(rightConfirmationButtons[i], constraints);
	}
		constraints.gridx = 0;//the column you are on
		constraints.gridy = 6;//should be the 6th row of things in this column
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(50, 0, 5, 0);
		rightValidationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean problem=false;
				for (int i = 0; i < rightConfirmationButtons.length; i++) {
					rightConfirmationButtons[i].doClick();
					if(rightConfirmationButtons[i].hasProblem()){
						problem=true;
					}
					else {

						rightConfirmationButtons[i].setEnabled(false);
						rightPokemonInputs[i].setEnabled(false);
						for (int k = 0; k < rightMoveInputs[i].length; k++) {

							rightMoveInputs[i][k].setEnabled(false);
						}


					}}
				if(problem==false){
					synchronized (myObject1){if(confirm1==true){
						myObject1.notify();
					}else{
						confirm2 =true;
					}
					}}
			}
		});
		rightUI.add(rightValidationButton, constraints);//self explanatory

		rightPresetTeam1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPokemonInputs[1].setText("Garchomp-mega");
				rightMoveInputs[1][0].setText("Earthquake");
				rightMoveInputs[1][1].setText("Draco Meteor");
				rightMoveInputs[1][2].setText("Fire Blast");
				rightMoveInputs[1][3].setText("Swords Dance");


				rightPokemonInputs[0].setText("Deoxys-attack");
				rightMoveInputs[0][0].setText("Psycho Boost");
				rightMoveInputs[0][1].setText("Ice beam");
				rightMoveInputs[0][2].setText("superpower");
				rightMoveInputs[0][3].setText("Dark pulse");


				rightPokemonInputs[2].setText("Genesect");
				rightMoveInputs[2][0].setText("Bug Buzz");
				rightMoveInputs[2][1].setText("Flamethrower");
				rightMoveInputs[2][2].setText("Ice Beam");
				rightMoveInputs[2][3].setText("Flash cannon");


				rightPokemonInputs[3].setText("Terrakion");
				rightMoveInputs[3][0].setText("Close Combat");
				rightMoveInputs[3][1].setText("Stone Edge");
				rightMoveInputs[3][2].setText("Earthquake");
				rightMoveInputs[3][3].setText("Quick Attack");

				rightPokemonInputs[4].setText("Manaphy");
				rightMoveInputs[4][0].setText("Tail Glow");
				rightMoveInputs[4][1].setText("Rest");
				rightMoveInputs[4][2].setText("Surf");
				rightMoveInputs[4][3].setText("Psychic");

				rightPokemonInputs[5].setText("Staraptor");
				rightMoveInputs[5][0].setText("Brave Bird");
				rightMoveInputs[5][1].setText("Double-Edge");
				rightMoveInputs[5][2].setText("Close Combat");
				rightMoveInputs[5][3].setText("Roost");
				rightConfirmationButtons[0].doClick();
				rightConfirmationButtons[1].doClick();
				rightConfirmationButtons[2].doClick();
				rightConfirmationButtons[3].doClick();
				rightConfirmationButtons[4].doClick();
				rightConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		rightUI.add(rightPresetTeam1, constraints);//adds to UI

		rightPresetTeam2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPokemonInputs[0].setText("Tapu Fini");
				rightMoveInputs[0][0].setText("Scald");
				rightMoveInputs[0][1].setText("Moonblast");
				rightMoveInputs[0][2].setText("Icy wind");
				rightMoveInputs[0][3].setText("Calm Mind");


				rightPokemonInputs[1].setText("Charizard-mega-y");
				rightMoveInputs[1][0].setText("Flamethrower");
				rightMoveInputs[1][1].setText("Focus blast");
				rightMoveInputs[1][2].setText("Roost");
				rightMoveInputs[1][3].setText("Solar beam");


				rightPokemonInputs[2].setText("Landorus-therian");
				rightMoveInputs[2][0].setText("Explosion");
				rightMoveInputs[2][1].setText("Earthquake");
				rightMoveInputs[2][2].setText("Stone edge");
				rightMoveInputs[2][3].setText("Knock off");


				rightPokemonInputs[3].setText("Tapu koko");
				rightMoveInputs[3][0].setText("Thunderbolt");
				rightMoveInputs[3][1].setText("Dazzling Gleam");
				rightMoveInputs[3][2].setText("Roost");
				rightMoveInputs[3][3].setText("thunder wave");

				rightPokemonInputs[4].setText("Greninja");
				rightMoveInputs[4][0].setText("Hydro pump");
				rightMoveInputs[4][1].setText("dark pulse");
				rightMoveInputs[4][2].setText("water shuriken");
				rightMoveInputs[4][3].setText("gunk shot");

				rightPokemonInputs[5].setText("Tapu Lele");
				rightMoveInputs[5][0].setText("Psychic");
				rightMoveInputs[5][1].setText("Moonblast");
				rightMoveInputs[5][2].setText("Shadow Ball");
				rightMoveInputs[5][3].setText("Focus Blast");



				rightConfirmationButtons[0].doClick();
				rightConfirmationButtons[1].doClick();
				rightConfirmationButtons[2].doClick();
				rightConfirmationButtons[3].doClick();
				rightConfirmationButtons[4].doClick();
				rightConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		rightUI.add(rightPresetTeam2, constraints);//adds to UI

		rightPresetTeam3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPokemonInputs[0].setText("Garchomp-mega");
				rightMoveInputs[0][0].setText("Swords Dance");
				rightMoveInputs[0][1].setText("Fire fang");
				rightMoveInputs[0][2].setText("Stone edge");
				rightMoveInputs[0][3].setText("Earthquake");


				rightPokemonInputs[1].setText("Keldeo");
				rightMoveInputs[1][0].setText("Calm mind");
				rightMoveInputs[1][1].setText("Scald");
				rightMoveInputs[1][2].setText("Focus Blast");
				rightMoveInputs[1][3].setText("Icy wind");


				rightPokemonInputs[2].setText("Tapu Lele");
				rightMoveInputs[2][0].setText("Psychic");
				rightMoveInputs[2][1].setText("Calm mind");
				rightMoveInputs[2][2].setText("Moonblast");
				rightMoveInputs[2][3].setText("Focus Blast");


				rightPokemonInputs[3].setText("Hawlucha");
				rightMoveInputs[3][0].setText("High Jump Kick");
				rightMoveInputs[3][1].setText("Stone Edge");
				rightMoveInputs[3][2].setText("Acrobatics");
				rightMoveInputs[3][3].setText("Swords Dance");

				rightPokemonInputs[4].setText("Tapu Bulu");
				rightMoveInputs[4][0].setText("bulk up");
				rightMoveInputs[4][1].setText("horn leech");
				rightMoveInputs[4][2].setText("Superpower");
				rightMoveInputs[4][3].setText("megahorn");

				rightPokemonInputs[5].setText("Victini");
				rightMoveInputs[5][0].setText("V-create");
				rightMoveInputs[5][1].setText("Bolt Strike");
				rightMoveInputs[5][2].setText("Focus blast");
				rightMoveInputs[5][3].setText("Zen headbutt");


				rightConfirmationButtons[0].doClick();
				rightConfirmationButtons[1].doClick();
				rightConfirmationButtons[2].doClick();
				rightConfirmationButtons[3].doClick();
				rightConfirmationButtons[4].doClick();
				rightConfirmationButtons[5].doClick();
			}
		});
		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		rightUI.add(rightPresetTeam3, constraints);//adds to UI
		leftPanelTB.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
		rightPanelTB.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
		rightDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
		leftDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory

		P1=new Player(this, calc, p1Pokemon);
// set the pokemon chosen to the fields
		P2=new Player(this, calc, p2Pokemon);
	}


	//the titlescreen is where you select your music and settings
	private void titleScreen(){
		int random=(int)(Math.random()*6);
		musicPlayer.play("Music/" + TITLE_SCREENS[random] + ".wav");//play random titlescreen music
		//the titlescreen image is 450 by 225
		Icon logoIcon=(new ImageIcon(new ImageIcon("Images/NotPokemonLogo.png").getImage().getScaledInstance((int)(450*1.75), (int)(225*1.75), Image.SCALE_DEFAULT)));
		FadeLabel logoLabel=new FadeLabel(logoIcon,0.02f);


		JPanel mainPanelTitleScreen =new JPanel(new GridLayout(2,1));
		JPanel logo= new JPanel();
		mainPanelTitleScreen.add(logo);
		JPanel UI=new JPanel(new BorderLayout());
		mainPanelTitleScreen.add(UI);
		JPanel music=new JPanel(new BorderLayout()),settings=new JPanel(new BorderLayout()),play =new JPanel( new BorderLayout());
		JButton musicButton =new JButton("Music");
		JButton settingsButton=new JButton("Settings");
		JButton playButton =new JButton("Play!");
		frame.add(mainPanelTitleScreen);
		music.add(musicButton);
		settings.add(settingsButton);
		play.add(playButton);

		logo.add(logoLabel);
		logoLabel.faidIn();
		logo.setOpaque(true);
		logo.setBackground(Color.WHITE);
		play.setOpaque(true);
		play.setBackground(Color.WHITE);
		settings.setOpaque(true);
		settings.setBackground(Color.WHITE);
		music.setOpaque(true);
		music.setBackground(Color.WHITE);

musicButton.setPreferredSize(new Dimension(500,100));
Font pokemonPlaceHolder=new Font("Arial", Font.PLAIN, 40);
try {

		pokemonPlaceHolder=Font.createFont(Font.TRUETYPE_FONT,new File("Fonts/PokemonSolid.ttf"));//setting fonts


		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		settingsButton.setPreferredSize(new Dimension(500,100));
		Font pokemon=pokemonPlaceHolder.deriveFont((float) 65.0);

		playButton.setFont(pokemon);
		settingsButton.setFont(pokemon);

		musicButton.setFont(pokemon);

		UI.add(music,BorderLayout.WEST);


		UI.add(play,BorderLayout.CENTER);


		UI.add(settings,BorderLayout.EAST);

		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (myObject4){

					myObject4.notify();//notify that everything is done

				}
			}
		});
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input;
				input= (String) JOptionPane.showInputDialog(mainPanelTitleScreen,
						"Select your music. If you don't select anything, the current music will keep playing"
						,"Music",
						2,
						new ImageIcon (new ImageIcon("Images/Music.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						MUSIC_OPTIONS,
						null);
				if(input!=null){
					for (int i = 0; i < MUSIC_OPTIONS.length; i++) {
						if (input.equalsIgnoreCase(MUSIC_OPTIONS[i])) {
							musicSelection = i;
							musicPlayer.stop();
							musicPlayer.play("Music/" + MUSIC_OPTIONS[musicSelection] + ".wav");
							break;
						}
					}
				}
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options=new String[]{"Healthbar","TextSpeed"};
				String input;
				input= (String) JOptionPane.showInputDialog(mainPanelTitleScreen,
						"What setting would you like to change"
						,"Settings",
						2,
						new ImageIcon (new ImageIcon("Images/Settings.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						options,
						null);
				if(input!=null){
					if(input.equals("Healthbar")){
						int u=-1;
						Object[] selection = new Object[]{0,2,4,6,8,10};


						try{
							u = (int)JOptionPane.showInputDialog(mainPanelTitleScreen,
									"HealthBar speed.\nA lower number means that the Health bars will change quickly,\n and a higher number means that the Health bars will change slowly"//self explanatory
									, "Health bar speed",
									2,
									new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
									selection,
									"15");}
						catch (Exception ex) {

						}


						healthBarDecreationRate= u;
					}else if(input.equals("TextSpeed")){
						int u=-1;
						Object[] selection = new Object[]{0,1,5,10,15,20};


						try{
							u = (int)JOptionPane.showInputDialog(mainPanelTitleScreen,
									"Choose your text speed.\nA lower number means that text will be written quickly, and a higher number means that text will be written slowly"//self explanatory
									, "TextSpeed",
									2,
									new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
									selection,
									"15");}
						catch (Exception ex) {

						}


						textSpeed= u;
					}
				}
			}

		});

	}
	//prints everything to the text area one character a time depending on text speed
	public void log(String s)  {

		Object myOject0=new Object();
if(textSpeed==0){
	leftText.setText(leftText.getText() + "" + s);
	rightText.setText(rightText.getText() + "" + s);
}else {
	for (int i = 0; i < s.length(); i++) {
		leftText.setText(leftText.getText() + "" + s.charAt(i));
		rightText.setText(rightText.getText() + "" + s.charAt(i));

		if (textSpeed != 1) {
			synchronized (myOject0) {
				try {
					myOject0.wait(textSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
		leftText.setText(leftText.getText() + "\n") ;
		rightText.setText(rightText.getText() + "\n") ;
	}
}