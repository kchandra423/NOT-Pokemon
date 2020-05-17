//
//  Author: Eric Chang
//  Revised: Eric Chang
//			5/17/20
//  Notes:
//         An alternate way of playing the game where player perspective
//         is switched instead of having both players on the same screen at once.
//         Not really our priority at this point, but more of a cool feature
//  Bugs:
//       There probably are some bugs, but we don't test this class as we mainly focus on the Battle class
//
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Battle2 {
    public static int P1numberOfFaintedMons=0;
    public static int P2numberOfFaintedMons=0;
    
    private JFrame frame;
    private JPanel panel, display, movePanel, switchPanel;
    private JLabel name1, name2, image1, image2, attack, switchOut;
    private HealthBar bar1, bar2;
    private Button[] moves = new Button[4];
    private Button[] switches = new Button[6];
    private JTextArea text;
    
    private Calculator calculator;
    
    private Player p1, p2;
    private int currPlayer;
    private int selection;

    // has two player params because team builder in main, make one with no params in future
    public Battle2(Player p1, Player p2) {
    	this.p1 = p1;
    	this.p2 = p2;
    	currPlayer = 1;
    	selection = 0;
    	calculator = new Calculator();
    	setupGUI();
    }
    
    public static void main (String[] args) {
//    ReadFile read = new ReadFile();
//        String copy = "something went wrong; ";
//        try {
////			copy = Files.readString(Paths.get("Stats.txt"));
//            byte[] file = Files.readAllBytes(Paths.get("ShowdownLearnsets.txt"));
//            copy = new String(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    System.out.println(
////            read.orderMoves(
//            (read.formatShowDownLearnSets(copy).replace("\t","")));
    	
        EaseOfUse ez=new EaseOfUse();
        Scanner kboard=new Scanner(System.in);
        Calculator calc=new Calculator();
//        String filepath = "PokemonTitleScreen.wav";
//        PlayMusic musicObject = new PlayMusic();
//        musicObject.playMusic(filepath);
        Pokemon[] p1mons=new Pokemon[6];
        Pokemon[] p2mons=new Pokemon[6];
        int[][] givenMoves = new int[6][4];

        ez.print("P1) Select your first pokemon's dex number");
        p1mons[0]=new Pokemon(1);

        ez.print("P1) Select your second pokemon's dex number");
        p1mons[1]=new Pokemon(2);
        ez.print("P1) Select your third pokemon's dex number");
        p1mons[2]=new Pokemon(3);
        ez.print("P1) Select your fourth pokemon's dex number");
        p1mons[3]=new Pokemon(4);
        ez.print("P1) Select your fifth pokemon's dex number");
        p1mons[4]=new Pokemon(5);
        ez.print("P1) Select your sixth pokemon's dex number");
        p1mons[5]=new Pokemon(6);


        ez.print("P2) Select your first pokemon's dex number");
        p2mons[0]=new Pokemon(7);
        ez.print("P2) Select your second pokemon's dex number");
        p2mons[1]=new Pokemon(8);
        ez.print("P2) Select your third pokemon's dex number");
        p2mons[2]=new Pokemon(9);
        ez.print("P2) Select your fourth pokemon's dex number");
        p2mons[3]=new Pokemon(10);
        ez.print("P2) Select your fifth pokemon's dex number");
        p2mons[4]=new Pokemon(11);
        ez.print("P2) Select your sixth pokemon's dex number");
        p2mons[5]=new Pokemon(12);
        givenMoves[0]= new int[]{14,370,609,1};
        givenMoves[1]= new int[]{6,7,8,9};
        givenMoves[2]= new int[]{10,11,12,13};
        givenMoves[3]= new int[]{14,15,16,17};
        givenMoves[4]= new int[]{18,19,20,21};
        givenMoves[5]= new int[]{22,23,24,25};

        Player p1=new Player(p1mons,givenMoves);
        Player p2=new Player(p2mons,givenMoves);
        p1.setOpposingPlayer(p2);
        
        SwingUtilities.invokeLater(new Runnable() {
        	@Override
        	public void run() {
        		new Battle2(p1, p2);
        	}
        });
        
        System.out.println("The match has begun!");
        boolean gameNotOver=true;
        while(gameNotOver) {

            boolean p1WillSwitch=false;
            boolean p2WillSwitch=false;
            int p1SelectedMoveIndex=0;
            int p2SelectedMoveIndex = 0;
            int selection;
            boolean youShallNotPass = true;
            while (youShallNotPass) {
                System.out.println("Player1 what would you like to do\n1)Switch out\n2)Fight");
                selection = kboard.nextInt();
                if (selection == 1&&P1numberOfFaintedMons<5) {
                    p1WillSwitch = true;
                    youShallNotPass = false;
                } else if (selection == 2) {
                    System.out.println("Choose which move you want to use");
                    for (int i = 0; i < 4; i++) {
                        System.out.println((i + 1) + ")" + p1.getCurrentMon().getMoves()[i]);
                    }
                    p1SelectedMoveIndex = kboard.nextInt() - 1;
                    youShallNotPass = false;
                }
                else if(selection == 1&&P1numberOfFaintedMons>=5){
                    ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
                }
                else {
                    System.out.println("That wasn't a valid input");
                }
            }
            youShallNotPass=true;
            selection=0;
            while(youShallNotPass) {
                System.out.println("Player2 what would you like to do\n1)Switch out\n2)Fight");
                selection = kboard.nextInt();
                if (selection == 1) {
                    p2WillSwitch = true;
                    youShallNotPass = false;
                } else if (selection == 2) {
                    System.out.println("Choose which move you want to use");
                    for (int i = 0; i < 4; i++) {
                        System.out.println((i + 1) + ")" + p2.getCurrentMon().getMoves()[i]);
                    }
                    p2SelectedMoveIndex = kboard.nextInt() - 1;
                    youShallNotPass = false;
                }
                else if(selection == 1&&P2numberOfFaintedMons>=5){
                    ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
                }else {
                    System.out.println("That wasn't a valid input");
                }
            }

            if(p1WillSwitch==true&&p2WillSwitch==true){
                if(p1.getCurrentMon().getSpeed()>p2.getCurrentMon().getSpeed()){
                    //p1.switchOut();
                    //p2.switchOut();
                }
                else if(p2.getCurrentMon().getSpeed()>p1.getCurrentMon().getSpeed()){
                    //p2.switchOut();
                    //p1.switchOut();
                }
                else{
                    if(Math.random()>0.5){
                        //p1.switchOut();
                        //p2.switchOut();
                    }
                    else{
                        //p2.switchOut();
                        //p1.switchOut();
                    }
                }

            }
            else if(p1WillSwitch==true){
                //p1.switchOut();
                p2.fight(p2SelectedMoveIndex);
            }
            else if(p2WillSwitch==true){
                //p2.switchOut();
                p1.fight(p1SelectedMoveIndex);
            }
            else{

                int x=calc.calculateWhoGoesFirst(p1,p2,p1.getCurrentMon().getMoves()[p1SelectedMoveIndex],p2.getCurrentMon().getMoves()[p2SelectedMoveIndex]);
                if(x==1){
                    p1.fight(p1SelectedMoveIndex);
                    p2.fight(p2SelectedMoveIndex);
                }
                else if(x==2){
                    p2.fight(p2SelectedMoveIndex);
                    p1.fight(p1SelectedMoveIndex);
                }
                else{
                    while(true) {
                        ez.print("Something went wrong");
                    }
                }
            }
            if(p1.isDefeated()){
                gameNotOver=false;
                break;
            }
            if(p2.isDefeated()){
                gameNotOver=false;
                break;
            }
            if(p1.getCurrentMon().getHealth()<=0){
                //p1.switchOut();
                P1numberOfFaintedMons++;
            }
            if(p2.getCurrentMon().getHealth()<=0){
                //p2.switchOut();
                P2numberOfFaintedMons++;
            }
        }





    }
    
    private void swapView() {
    	currPlayer = 3 - currPlayer; // if 1, swap to 2, if 2, swap to 1, another way is % 2 + 1
    	setupGUI();
    }
    
    private void setupGUI() {
    	if(frame != null) {
    		frame.getContentPane().removeAll();
    	}
    	else {
    		frame = new JFrame();
    	}
    	panel = new JPanel(new GridBagLayout());
    	display = new JPanel();
    	movePanel = new JPanel();
    	switchPanel = new JPanel();
    	GridBagConstraints c = new GridBagConstraints();
		if(currPlayer == 1 || selection == 11) {
			name1 = new JLabel(p1.getCurrentMon().getName());
			name2 = new JLabel(p2.getCurrentMon().getName());
			bar1 = new HealthBar(p1.getCurrentMon());
			bar2 = new HealthBar(p2.getCurrentMon());
			ImageIcon pic = new ImageIcon("Images/Sprites/SpritesBack/" + p1.getCurrentMon().getID() +"-back.gif");
			image1 = new JLabel(pic);
			image1.setToolTipText(toToolTipText(p1.getCurrentMon().toString()));
			ImageIcon pic2 = new ImageIcon("Images/Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif");
			image2 = new JLabel(pic2);
			image2.setToolTipText("You can only see your own pokemon's stats!");
		}
		else {
			name1 = new JLabel(p2.getCurrentMon().getName());
			name2 = new JLabel(p1.getCurrentMon().getName());
			bar1 = new HealthBar(p2.getCurrentMon());
			bar2 = new HealthBar(p1.getCurrentMon());
			ImageIcon pic = new ImageIcon("Images/Sprites/SpritesBack/" + p2.getCurrentMon().getID() +"-back.gif");
			image1 = new JLabel(pic);
			image1.setToolTipText(toToolTipText(p2.getCurrentMon().toString()));
			ImageIcon pic2 = new ImageIcon("Images/Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif");
			image2 = new JLabel(pic2);
			image2.setToolTipText("You can only see your own pokemon's stats!");
		}
		bar1.setXOffset(true);
		bar2.setXOffset(false);
		GroupLayout l = new GroupLayout(display);
		display.setLayout(l);
		l.setAutoCreateGaps(true);
		l.setAutoCreateContainerGaps(true);
		l.setHorizontalGroup(l.createSequentialGroup()
			.addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGap(100)
				.addComponent(name1)
				.addComponent(bar1)
				.addComponent(image1))
			.addGap(200)
			.addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(name2)
				.addComponent(bar2)
				.addComponent(image2))
		);
		l.setVerticalGroup(l.createSequentialGroup()
			.addComponent(name2)
			.addComponent(bar2)
			.addComponent(image2)
			.addGap(100)
			.addComponent(name1)
			.addComponent(bar1)
			.addComponent(image1)
		);
		// l.linkSize(SwingConstants.HORIZONTAL, name1, image1);
		// l.linkSize(SwingConstants.HORIZONTAL, name2, image2);
		display.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 0);
		panel.add(display, c);
    	attack = new JLabel("Attack");
    	c.gridx = 0;
    	c.gridy = 3;
    	c.gridwidth = 4;
    	c.gridheight = 1;
    	c.weightx = 0.0;
		c.weighty = 0.0;
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, 10, 0, 0);
    	panel.add(attack, c);
    	GridLayout gl = new GridLayout(2, 2);
    	movePanel.setLayout(gl);
    	for(int move = 0; move < moves.length; move++) {
    		if(currPlayer == 1 || selection == 11) {
    			moves[move] = new Button(p1.getCurrentMon().getMoves()[move].getName(), move+1);
    			moves[move].setToolTipText(toToolTipText(p1.getCurrentMon().getMoves()[move].toString()));
    			if(p1.getCurrentMon().getHealth() <= 0 || p1.getCurrentMon().getMoves()[move].getPP() == 0) {
        			moves[move].setEnabled(false);
        		}
    		}
    		else {
    			moves[move] = new Button(p2.getCurrentMon().getMoves()[move].getName(), move+1);
    			moves[move].setToolTipText(toToolTipText(p2.getCurrentMon().getMoves()[move].toString()));
    			if(p2.getCurrentMon().getHealth() <= 0 || p2.getCurrentMon().getMoves()[move].getPP() == 0) {
        			moves[move].setEnabled(false);
        		}
    		}
    		moves[move].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int move = ((Button)e.getSource()).getNum();
					if(currPlayer == 1) { // p1 fight
						selection = move;
					}
					else { // p2 fight
						if(selection < 5) { // p1 fight
							int first = calculator.calculateWhoGoesFirst(p1, p2,
								p1.getCurrentMon().getMoves()[move-1],
								p2.getCurrentMon().getMoves()[move-1]);
							if(first == 1) { // p1 goes first
								p1.fight(selection-1);  // remember, move is for currPlayer (p2)
								p2.fight(move-1);
							}
							else { // p2 goes first
								p2.fight(move-1);
								p1.fight(selection-1);
							}
						}
						else { // p1 switch
							p1.switchOut(selection-4-1);
							p2.fight(move-1);
						}
						selection = 0;
					}
					if(p1.isDefeated() || p2.isDefeated()) {
						if(p1.isDefeated() && !p2.isDefeated()) {
							log("Player 2 is the winner!");
						}
						else if(p2.isDefeated() && !p1.isDefeated()) {
							log("Player 1 is the winner!");
						}
						else {
							log("The match was a tie!");
						}
						setupGUI();
					}
					if(p1.getCurrentMon().getHealth() <= 0 ^ p2.getCurrentMon().getHealth() <= 0) {
						if(p1.getCurrentMon().getHealth() <= 0 && p2.getCurrentMon().getHealth() > 0) {
							selection = 11;
						}
						else if(p2.getCurrentMon().getHealth() <= 0 && p1.getCurrentMon().getHealth() > 0) {
							selection = 12;
						}
						setupGUI();
					}
					else {
						swapView();
					}
				}
    		});
    		movePanel.add(moves[move]);
    	}
    	c.gridx = 0;
    	c.gridy = 4;
    	c.gridwidth = 4;
    	c.gridheight = 1;
    	c.weightx = 0.0;
		c.weighty = 0.0;
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, 0, 0, 0);
    	panel.add(movePanel, c);
    	switchOut = new JLabel("Switch");
    	c.gridx = 0;
    	c.gridy = 5;
    	c.gridwidth = 4;
    	c.gridheight = 1;
    	c.weightx = 0.0;
		c.weighty = 0.0;
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, 10, 0, 0);
    	panel.add(switchOut, c);
    	GridLayout gl2 = new GridLayout(3, 2);
    	switchPanel.setLayout(gl2);
    	for(int mon = 0; mon < switches.length; mon++) {
    		if(currPlayer == 1 || selection == 11) {
    			switches[mon] = new Button(p1.getPokemon()[mon].getName(), mon+1);
    			switches[mon].setToolTipText(toToolTipText(p1.getPokemon()[mon].toString()));
    			if(p1.getPokemon()[mon].getHealth() <= 0 || p1.getPokemon()[mon] == p1.getCurrentMon()) {
    				switches[mon].setEnabled(false);
    			}
    		}
    		else {
    			switches[mon] = new Button(p2.getPokemon()[mon].getName(), mon+1);
    			switches[mon].setToolTipText(toToolTipText(p2.getPokemon()[mon].toString()));
    			if(p2.getPokemon()[mon].getHealth() <= 0 || p2.getPokemon()[mon] == p2.getCurrentMon()) {
    				switches[mon].setEnabled(false);
    			}
    		}
    		switches[mon].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int move = ((Button)e.getSource()).getNum();
					if(selection > 10) {
						if(selection == 11) {
							p1.switchOut(move-1);
						}
						else if(selection == 12) {
							p2.switchOut(move-1);
						}
						selection = 0;
					}
					else if(currPlayer == 1) { // p1 switch
						selection = move+4;
					}
					else { // p2 switch
						if(selection > 4) { // p1 switch
							if(p1.getCurrentMon().getSpeed() > p2.getCurrentMon().getSpeed()) {
								p1.switchOut(selection-4-1);
								p2.switchOut(move-1);
							}
							else if(p2.getCurrentMon().getSpeed() > p1.getCurrentMon().getSpeed()) {
								p2.switchOut(move-1);
								p1.switchOut(selection-4-1);
			                }
			                else {
			                	if(Math.random()>=0.5) {
			                		p1.switchOut(selection-4-1);
			                		p2.switchOut(move-1);
			                	}
			                	else {
			                		p2.switchOut(move-1);
			                		p1.switchOut(selection-4-1);
			                	}
			                }
						}
						else { // p1 fight
							p2.switchOut(move-1);
							p1.fight(selection-1);
						}
						selection = 0;
					}
					if(p1.getCurrentMon().getHealth() <= 0 ^ p2.getCurrentMon().getHealth() <= 0) {
						if(p1.getCurrentMon().getHealth() <= 0 && p2.getCurrentMon().getHealth() > 0) {
							selection = 11;
						}
						else if(p2.getCurrentMon().getHealth() <= 0 && p1.getCurrentMon().getHealth() > 0) {
							selection = 12;
						}
						setupGUI();
					}
					else {
						swapView();
					}
				}
    		});
    		switchPanel.add(switches[mon]);
    	}
    	c.gridx = 0;
    	c.gridy = 6;
    	c.gridwidth = 4;
    	c.gridheight = 1;
    	c.weightx = 0.0;
		c.weighty = 0.0;
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, 0, 0, 0);
    	panel.add(switchPanel, c);
    	if(text == null) {
	    	text = new JTextArea(20, 20);
	    	text.setText("The match has begun!");
	    	text.setEditable(false);
	    	text.setLineWrap(true);
	    	text.setWrapStyleWord(true);
    	}
    	JScrollPane pane = new JScrollPane(text,
    			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
    			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	c.gridx = 4;
    	c.gridy = 0;
    	c.gridwidth = 3;
    	c.gridheight = 7;
    	c.weightx = 0.5;
		c.weighty = 0.5;
    	c.anchor = GridBagConstraints.FIRST_LINE_END;
    	c.fill = GridBagConstraints.BOTH;
    	c.insets = new Insets(5, 5, 5, 5);
    	panel.add(pane, c);
    	frame.getContentPane().add(panel);
    	frame.pack();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocation(100, 100);
    	// frame.setResizable(false);
    	frame.setTitle("NOT Pokemon");
    	frame.setVisible(true);
    }
    
    private void log(String s) {
    	text.setText(text.getText() + "\n" + s);
    }
    
    private String toToolTipText(String s) {
    	return "<html>" + s.trim().replace("\n", "<br>") + "</html>";
    }
}
