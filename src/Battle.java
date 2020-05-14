import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Battle {
	public static int P1numberOfFaintedMons = 0;
	public static int P2numberOfFaintedMons = 0;

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel leftPanel, rightPanel, leftDisplayPanel, rightDisplayPanel;
	private JPanel leftUI, rightUI;
	private JPanel leftMovePanel = new JPanel(), rightMovePanel = new JPanel();
	private JPanel leftSwitchPanel = new JPanel(), rightSwitchPanel = new JPanel();

	private JLabel name1, name2, image1, image2, name3, name4, image3, image4, attack = new JLabel("Attack"),
			switchOut = new JLabel("Switch");
	private Button[] leftMoveButtons = new Button[4], rightMoveButtons = new Button[4];
	private int p1Selection = -1;
	private int p2Selection = -1;
//	private JButton p1PokemonButton=new JButton("Get your Pokemon's stats"), p2PokemonButton=new JButton("Get your Pokemon's stats");
	private Button[] leftSwitchButtons = new Button[6], rightSwitchButtons = new Button[6];
	private ButtonGroup leftButtons = new ButtonGroup(), rightButtons = new ButtonGroup();
	public JTextArea leftText, rightText;
//	private JButton leftP1Image=new JButton();

//	private int playerPerspective=1;

	public Battle() {

	}

	public Battle(Player p1, Player p2) {

		String filepath = "PokemonTitleScreen.wav";
		PlayMusic musicObject = new PlayMusic();
		musicObject.playMusic(filepath);
		frame = new JFrame();
//    	panel = new JPanel(new GridBagLayout());
//    	display = new JPanel();
//    	movePanel = new JPanel();
//    	switchPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		mainPanel = new JPanel(new GridLayout(1, 2));

		leftPanel = new JPanel(new GridBagLayout());
		rightPanel = new JPanel(new GridBagLayout());
		leftDisplayPanel = new JPanel();
		leftPanel.add(leftDisplayPanel);
		rightDisplayPanel = new JPanel();
		rightPanel.add(rightDisplayPanel);
		leftUI = new JPanel(new GridLayout(2, 1));
		rightUI = new JPanel(new GridLayout(2, 1));
//		leftPanel.add(leftUI, BorderLayout.SOUTH);
//		rightPanel.add(rightUI,BorderLayout.SOUTH);
//		leftUI.add(attack);
		leftUI.add(leftMovePanel);
//		leftUI.add(switchOut);
		leftUI.add(leftSwitchPanel);
		rightUI.add(rightMovePanel);
		rightUI.add(rightSwitchPanel);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
//		p1PokemonButton.setVisible(true);
//		p1PokemonButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane leftPokemonInfoPopup=new JOptionPane("P1 current Pokemon info");
//				leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel,
//						p1.getCurrentMon().toString(),"Pokemon Info",
//						2,
//						new ImageIcon("Sprites/SpritesFront/"+p1.getCurrentMon().getID()+".gif"));
////			leftText.setText(leftText.getText()+p1.getCurrentMon().toString());
//			}
//		});
//		p2PokemonButton.setVisible(true);
//		p2PokemonButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane leftPokemonInfoPopup=new JOptionPane("P2 current Pokemon info");
//				leftPokemonInfoPopup.showMessageDialog(
//						rightDisplayPanel,
//						p2.getCurrentMon().toString(),
//						"Pokemon Info",
//						2,
//						new ImageIcon("Sprites/SpritesFront/"+p2.getCurrentMon().getID()+".gif"));
////				rightText.setText(rightText.getText()+p2.getCurrentMon().toString());
//			}
//		});

		name1 = new JLabel(p1.getCurrentMon().getName());
		name2 = new JLabel(p2.getCurrentMon().getName());
		ImageIcon pic =
//					new ImageIcon
//					(new ImageIcon(
//							"Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif")
//							.getImage().
//									getScaledInstance(100, 500, Image.SCALE_DEFAULT));

				new ImageIcon("Sprites/SpritesBack/" + p1.getCurrentMon().getID() + "-back.gif");
		pic = new ImageIcon(pic.getImage().getScaledInstance((int) (pic.getIconWidth() * 4),
				(int) (pic.getIconHeight() * 4), Image.SCALE_DEFAULT));
		image1 = new JLabel(pic);
		image1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");

					leftPokemonInfoPopup.setBackground(Color.DARK_GRAY);
					leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel, p1.getCurrentMon().toString(),
							"Player 1 Pokemon Info", 2,
//								(new ImageIcon (new ImageIcon("Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
							new ImageIcon("Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif"));
				}
			}
		});
//			leftP1Image.setIcon(pic);
		pic = new ImageIcon("Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif");
		image2 = new JLabel(pic);
		image2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
					leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel, p2.getCurrentMon().toString(),
							"Player 2 Pokemon Info", 2,
							new ImageIcon("Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));
//				rightText.setText(rightText.getText()+p2.getCurrentMon().toString());
				}
			}
		});
		GroupLayout layout1 = new GroupLayout(leftDisplayPanel);
		leftDisplayPanel.setLayout(layout1);
		layout1.setAutoCreateGaps(true);
		layout1.setAutoCreateContainerGaps(true);
		layout1.setHorizontalGroup(
				layout1.createSequentialGroup().addGroup(layout1.createParallelGroup().addGap(500).addComponent(name1)
//						.addComponent(p1PokemonButton)
						.addComponent(image1))
//.addComponent(leftP1Image)
						.addGroup(layout1.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(name2)
								.addComponent(image2))

		);
		layout1.setVerticalGroup(layout1.createSequentialGroup().addComponent(name2).addComponent(image2).addGap(200)
				.addComponent(name1).addComponent(image1)
//					.addComponent(leftP1Image)
//					.addComponent(p1PokemonButton)
		);
		layout1.linkSize(SwingConstants.HORIZONTAL, name1, name2, image1, image2);
		layout1.linkSize(image1, image2);
		leftDisplayPanel.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 3;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 0);
		leftPanel.add(leftDisplayPanel, c);
//
		attack = new JLabel("Attack");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 0);
		leftPanel.add(attack, c);
		GridLayout layout2 = new GridLayout(1, 4);
		leftMovePanel.setLayout(layout2);
		for (int i = 0; i < leftMoveButtons.length; i++) {
			leftMoveButtons[i] = new Button(p1.getCurrentMon().getMoves()[i].getName(), i);
//    		leftMoveButtons[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
			leftMovePanel.add(leftMoveButtons[i]);
		}
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		leftPanel.add(leftMovePanel, c);
		switchOut = new JLabel("Switch");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 0);
		leftPanel.add(switchOut, c);
		layout2 = new GridLayout(1, 6);
		leftSwitchPanel.setLayout(layout2);
		for (int i = 0; i < leftSwitchButtons.length; i++) {
			leftSwitchButtons[i] = new Button(p1.getPokemon()[i].getName(), i + 4);
//    		switches[mon].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
			leftSwitchPanel.add(leftSwitchButtons[i]);
		}
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		leftPanel.add(leftSwitchPanel, c);
		leftText = new JTextArea(20, 20);
//    	text.setEditable(false);
		leftText.setLineWrap(true);
		leftText.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(leftText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
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
		leftPanel.add(pane, c);

		GridBagConstraints y = new GridBagConstraints();
		name3 = new JLabel(p2.getCurrentMon().getName());
		name4 = new JLabel(p1.getCurrentMon().getName());
		ImageIcon pic2 = new ImageIcon("Sprites/SpritesBack/" + p2.getCurrentMon().getID() + "-back.gif");
		image3 = new JLabel(pic2);
		image3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
				leftPokemonInfoPopup.showMessageDialog(rightDisplayPanel, p2.getCurrentMon().toString(),
						"Player 2 Pokemon Info", 2,
						new ImageIcon("Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));

			}
		});
		pic2 = new ImageIcon("Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif");
		image4 = new JLabel(pic2);
		image4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");
				leftPokemonInfoPopup.showMessageDialog(rightDisplayPanel, p1.getCurrentMon().toString(),
						"Player 1 Pokemon Info", 2,
						new ImageIcon("Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif"));
			}
		});
		GroupLayout layout4 = new GroupLayout(rightDisplayPanel);
		rightDisplayPanel.setLayout(layout4);
		layout4.setAutoCreateGaps(true);
		layout4.setAutoCreateContainerGaps(true);
		layout4.setHorizontalGroup(
				layout4.createSequentialGroup().addGroup(layout4.createParallelGroup().addGap(500).addComponent(name3)
//						.addComponent(p2PokemonButton)
						.addComponent(image3))

						.addGroup(layout4.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(name4)
								.addComponent(image4)));
		layout4.setVerticalGroup(layout4.createSequentialGroup().addComponent(name4).addComponent(image4).addGap(200)
				.addComponent(name3).addComponent(image3)
//				.addComponent(p2PokemonButton)
		);
		layout4.linkSize(SwingConstants.HORIZONTAL, name3, name4, image3, image4);
		layout4.linkSize(image3, image4);
		rightDisplayPanel.setBackground(Color.LIGHT_GRAY);
		y.gridx = 0;
		y.gridy = 0;
		y.gridwidth = 4;
		y.gridheight = 3;
		y.weightx = 0.5;
		y.weighty = 0.0;
		y.anchor = GridBagConstraints.FIRST_LINE_START;
		y.fill = GridBagConstraints.HORIZONTAL;
		y.insets = new Insets(5, 5, 5, 0);
		rightPanel.add(rightDisplayPanel, y);

		attack = new JLabel("Attack");
		y.gridx = 0;
		y.gridy = 3;
		y.gridwidth = 4;
		y.gridheight = 1;
		y.weightx = 0.5;
		y.weighty = 0.0;
		y.anchor = GridBagConstraints.FIRST_LINE_START;
		y.fill = GridBagConstraints.HORIZONTAL;
		y.insets = new Insets(0, 10, 0, 0);
		rightPanel.add(attack, y);
		GridLayout layout3 = new GridLayout(1, 4);
		rightMovePanel.setLayout(layout3);
		for (int i = 0; i < rightMoveButtons.length; i++) {
			rightMoveButtons[i] = new Button(p2.getCurrentMon().getMoves()[i].getName(), i);
//    		rightMoveButtons[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
			rightMovePanel.add(rightMoveButtons[i]);
		}
		y.gridx = 0;
		y.gridy = 4;
		y.gridwidth = 4;
		y.gridheight = 1;
		y.weightx = 0.5;
		y.weighty = 0.0;
		y.anchor = GridBagConstraints.FIRST_LINE_START;
		y.fill = GridBagConstraints.HORIZONTAL;
		y.insets = new Insets(0, 0, 0, 0);
		rightPanel.add(rightMovePanel, y);
		switchOut = new JLabel("Switch");
		y.gridx = 0;
		y.gridy = 5;
		y.gridwidth = 4;
		y.gridheight = 1;
		y.weightx = 0.5;
		y.weighty = 0.0;
		y.anchor = GridBagConstraints.FIRST_LINE_START;
		y.fill = GridBagConstraints.HORIZONTAL;
		y.insets = new Insets(0, 10, 0, 0);
		rightPanel.add(switchOut, y);
		layout3 = new GridLayout(1, 6);
		rightSwitchPanel.setLayout(layout3);
		for (int i = 0; i < rightSwitchButtons.length; i++) {
			rightSwitchButtons[i] = new Button(p2.getPokemon()[i].getName(), i + 4);
//    		switches[mon].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
			rightSwitchPanel.add(rightSwitchButtons[i]);
		}
		y.gridx = 0;
		y.gridy = 6;
		y.gridwidth = 1;
		y.gridheight = 1;
		y.weightx = 0.5;
		y.weighty = 0.0;
		y.anchor = GridBagConstraints.FIRST_LINE_START;
		y.fill = GridBagConstraints.HORIZONTAL;
		y.insets = new Insets(0, 0, 0, 0);
		rightPanel.add(rightSwitchPanel, y);
		rightText = new JTextArea(20, 20);
//    	text.setEditable(false);
		rightText.setLineWrap(true);
		rightText.setWrapStyleWord(true);
		JScrollPane pane2 = new JScrollPane(rightText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		y.gridx = 4;
		y.gridy = 0;
		y.gridwidth = 3;
		y.gridheight = 7;
		y.weightx = 0.5;
		y.weighty = 0.5;
		y.anchor = GridBagConstraints.FIRST_LINE_END;
		y.fill = GridBagConstraints.BOTH;
		y.insets = new Insets(5, 5, 5, 5);
		rightPanel.add(pane2, y);
		frame.getContentPane().add(mainPanel);
//		frame.getContentPane().add(rightPanel);
		frame.setBounds(100, 100, 3000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.setTitle("NOT Pokemon");
		frame.setVisible(true);
		leftPanel.updateUI();
		rightPanel.updateUI();
		repaint(p1, p2);
	}

	public static void main(String[] args) {
//    ReadFile read = new ReadFile();
//        String copy = "something went wrong; ";
//        try {
////			copy = Files.readString(Paths.get("Stats.txt"));
//            byte[] file = Files.readAllBytes(Paths.get("ShowdownLearnsets.txt"));
//            copy = new String(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    ez.println(
////            read.orderMoves(
//            (read.formatShowDownLearnSets(copy).replace("\t","")));
		EaseOfUse ez = new EaseOfUse();
		Scanner kboard = new Scanner(System.in);
		Calculator calc = new Calculator();
//        PlayMusic playMusic = new PlayMusic();
		String music = "PokemonTitleScreen.wav";
//        playMusic.playMusic(music);
		Pokemon[] p1mons = new Pokemon[6];
		Pokemon[] p2mons = new Pokemon[6];
		int[][] givenMoves = new int[6][4];
		int[][] givenMoves2 = new int[6][4];

//        ez.print("P1) Select your first pokemon's dex number");
//        p1mons[0]=new Pokemon(kboard.nextInt());
//
//        ez.print("P1) Select your second pokemon's dex number");
//        p1mons[1]=new Pokemon(kboard.nextInt());
//        ez.print("P1) Select your third pokemon's dex number");
//        p1mons[2]=new Pokemon(kboard.nextInt());
//        ez.print("P1) Select your fourth pokemon's dex number");
//        p1mons[3]=new Pokemon(kboard.nextInt());
//        ez.print("P1) Select your fifth pokemon's dex number");
//        p1mons[4]=new Pokemon(kboard.nextInt());
//        ez.print("P1) Select your sixth pokemon's dex number");
//        p1mons[5]=new Pokemon(kboard.nextInt());
//
//
//        ez.print("P2) Select your first pokemon's dex number");
//        p2mons[0]=new Pokemon(kboard.nextInt());
//        ez.print("P2) Select your second pokemon's dex number");
//        p2mons[1]=new Pokemon(kboard.nextInt());
//        ez.print("P2) Select your third pokemon's dex number");
//        p2mons[2]=new Pokemon(kboard.nextInt());
//        ez.print("P2) Select your fourth pokemon's dex number");
//        p2mons[3]=new Pokemon(kboard.nextInt());
//        ez.print("P2) Select your fifth pokemon's dex number");
//        p2mons[4]=new Pokemon(kboard.nextInt());
//        ez.print("P2) Select your sixth pokemon's dex number");
//        p2mons[5]=new Pokemon(kboard.nextInt());
		p1mons[0] = new Pokemon(130);
		p1mons[1] = new Pokemon(143);
		p1mons[2] = new Pokemon(150);
		p1mons[3] = new Pokemon(384);
		p1mons[4] = new Pokemon(493);
		p1mons[5] = new Pokemon(487);

		p2mons[0] = new Pokemon(700);
		p2mons[1] = new Pokemon(802);
		p2mons[2] = new Pokemon(801);
		p2mons[3] = new Pokemon(800);
		p2mons[4] = new Pokemon(798);
		p2mons[5] = new Pokemon(791);

		givenMoves[0] = new int[] { 14, 370, 609, 26 };
		givenMoves[1] = new int[] { 53, 56, 59, 76 };
		givenMoves[2] = new int[] { 85, 89, 94, 98 };
		givenMoves[3] = new int[] { 14, 370, 609, 26 };
		givenMoves[4] = new int[] { 53, 56, 59, 76 };
		givenMoves[5] = new int[] { 85, 89, 94, 98 };

//        givenMoves[3]= new int[]{14,15,16,17};
//        givenMoves[4]= new int[]{18,19,20,21};
//        givenMoves[5]= new int[]{22,23,24,25};
		givenMoves2[0] = new int[] { 22, 23, 24, 25 };
		givenMoves2[1] = new int[] { 14, 370, 609, 1 };
		givenMoves2[2] = new int[] { 6, 7, 8, 9 };
		givenMoves2[3] = new int[] { 10, 11, 12, 13 };
		givenMoves2[4] = new int[] { 14, 15, 16, 17 };
		givenMoves2[5] = new int[] { 18, 19, 20, 21 };
		Player p1 = new Player(p1mons, givenMoves);
//        Player p2=new Player(p2mons,givenMoves2,p1);
		Player p2 = new Player(p2mons, givenMoves, p1);

		p1.setOpposingPlayer(p2);
		Battle b = new Battle(p1, p2);
//		b.playerPerspective=1;
//ez.println(b.leftText.getBounds().height);
//		ez.println(b.leftText.getBounds().width);
//		ez.println(b.rightText.getBounds().height);
//		ez.println(b.rightText.getBounds().width);
//        ez.println("The match has begun!");
		b.leftText.setText("The match has begun!");
		b.rightText.setText("The match has begun!");
		boolean gameNotOver = true;
		boolean p1WillSwitch = false;
		boolean p2WillSwitch = false;
		int p1SelectedMoveIndex = -1;
		int p2SelectedMoveIndex = -1;
		int p1SwitchIn = -1;
		int p2SwitchIn = -1;
		JOptionPane popup = new JOptionPane("All your pokemon have fainted");
		popup.setIcon(
				new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
//		final int[] moveSelection = new int[1];

//            int switchSelection;
		for (int i = 0; i < b.leftMoveButtons.length; i++) {

			b.leftMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getCurrentMon().getMoves()[x].toString();
						popup.showMessageDialog(b.leftDisplayPanel, textInfo, "Move Info", 2,
								(new ImageIcon(
										new ImageIcon("Types/" + p1.getCurrentMon().getMoves()[x].getType() + ".png")
												.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.leftMoveButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					b.p1Selection = ((Button) e.getSource()).getNum();
				}
			});
			b.leftButtons.add(b.leftMoveButtons[i]);
			// @Override
//					public void actionPerformed(ActionEvent e) {
////moveSelection[0] =((Button)e.getSource()).getNum();
//
////						((Button) e.getSource()).setEnabled(false);
////
//
//					}
		}
		for (int i = 0; i < b.leftSwitchButtons.length; i++) {

			b.leftSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.leftDisplayPanel, textInfo, "Pokemon Switch info", 2,
								new ImageIcon("Sprites/SpritesFront/" + p1.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.leftSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					b.p1Selection = ((Button) e.getSource()).getNum();

				}
			});
			b.leftButtons.add(b.leftSwitchButtons[i]);
		}

		for (int i = 0; i < b.rightMoveButtons.length; i++) {

			b.rightMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getCurrentMon().getMoves()[x].toString();
						popup.showMessageDialog(b.rightDisplayPanel, textInfo, "Move Info", 2,
								(new ImageIcon(
										new ImageIcon("Types/" + p2.getCurrentMon().getMoves()[x].getType() + ".png")
												.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.rightMoveButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					b.p2Selection = ((Button) e.getSource()).getNum();
				}
			});
//				@Override
//				public void actionPerformed(ActionEvent e) {
////moveSelection[0] =((Button)e.getSource()).getNum();
//					b.p2Selection =((Button)e.getSource()).getNum();
////						((Button) e.getSource()).setEnabled(false);
////
//
//				}

			b.rightButtons.add(b.rightMoveButtons[i]);
		}
		for (int i = 0; i < b.rightSwitchButtons.length; i++) {

			b.rightSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.rightDisplayPanel, textInfo, "Pokemon Switch info", 2,
								new ImageIcon("Sprites/SpritesFront/" + p2.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.rightSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					b.p2Selection = ((Button) e.getSource()).getNum();

				}
			});
//				@Override
//				public void actionPerformed(ActionEvent e) {
////moveSelection[0] =((Button)e.getSource()).getNum();
//					b.p2Selection =((Button)e.getSource()).getNum();
////					((Button) e.getSource()).setEnabled(false);
////

			b.rightButtons.add(b.rightSwitchButtons[i]);
		}

		while (gameNotOver) {

			boolean youShallNotPass = true;
			while (youShallNotPass) {
//                ez.println("Player1 what would you like to do\n1)Switch out\n2)Fight");
//                selection = kboard.nextInt();
				if (b.p1Selection >= 5 && P1numberOfFaintedMons < 5) {
					p1WillSwitch = true;
					p1SwitchIn = b.p1Selection - 4;
					youShallNotPass = false;
					b.p1Selection = -1;
				} else if (b.p1Selection <= 4 && b.p1Selection >= 0) {
//                    ez.println("Choose which move you want to use");
//                    for (int i = 0; i < 4; i++) {
//                        ez.println((i + 1) + ")" + p1.getCurrentMon().getMoves()[i]);
//                    }

					p1SelectedMoveIndex = b.p1Selection;
					youShallNotPass = false;
					b.p1Selection = -1;
				}
//                else if(b.selection >=5 &&P1numberOfFaintedMons>=5)
////				{
////                    ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
////                }
				else {
					System.out.print("");
				}
			}

			youShallNotPass = true;

			while (youShallNotPass) {
//                ez.println("Player2 what would you like to do\n1)Switch out\n2)Fight");
//                b.p1Selection = kboard.nextInt();
				if (b.p2Selection >= 5 && P2numberOfFaintedMons < 5) {
					p2WillSwitch = true;
					p2SwitchIn = b.p2Selection - 4;
					youShallNotPass = false;
					b.p2Selection = -1;
				} else if (b.p2Selection <= 4 && b.p2Selection >= 0) {
//                    ez.println("Choose which move you want to use");
//                    for (int i = 0; i < 4; i++) {
//                        ez.println((i + 1) + ")" + p1.getCurrentMon().getMoves()[i]);
//                    }

					p2SelectedMoveIndex = b.p2Selection;
					youShallNotPass = false;
					b.p2Selection = -1;
				}
//                else if(b.selection >=5 &&P1numberOfFaintedMons>=5)
////				{
////                    ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
////                }
				else {
					System.out.print("");
				}
			}
			youShallNotPass = true;
			if (p1WillSwitch && p2WillSwitch) {
				if (p1.getCurrentMon().getSpeed() > p2.getCurrentMon().getSpeed()) {
					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;
				} else if (p2.getCurrentMon().getSpeed() > p1.getCurrentMon().getSpeed()) {

					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;

				} else {
					if (Math.random() > 0.5) {
						p1.getCurrentMon().resetBoosts();
						p2.getCurrentMon().resetBoosts();
						p1.switchOut(p1.getPokemon()[p1SwitchIn]);
						p2.switchOut(p2.getPokemon()[p2SwitchIn]);
						p1WillSwitch = false;
						p2WillSwitch = false;

					} else {
						p1.getCurrentMon().resetBoosts();
						p2.getCurrentMon().resetBoosts();
						p2.switchOut(p2.getPokemon()[p2SwitchIn]);
						p1.switchOut(p1.getPokemon()[p1SwitchIn]);
						p1WillSwitch = false;
						p2WillSwitch = false;

					}
				}

			}

			else if (p1WillSwitch) {
				p1.getCurrentMon().resetBoosts();

				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
				p2.fight(p2SelectedMoveIndex);
				p1WillSwitch = false;
				p2WillSwitch = false;

			} else if (p2WillSwitch) {

				p2.getCurrentMon().resetBoosts();
				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
				p1.fight(p1SelectedMoveIndex);
				p1WillSwitch = false;
				p2WillSwitch = false;

			} else {

				int x = calc.calculateWhoGoesFirst(p1, p2, p1.getCurrentMon().getMoves()[p1SelectedMoveIndex],
						p2.getCurrentMon().getMoves()[p2SelectedMoveIndex]);
				if (x == 1) {
					p1.fight(p1SelectedMoveIndex);
					p2.fight(p2SelectedMoveIndex);
				} else if (x == 2) {
					p2.fight(p2SelectedMoveIndex);
					p1.fight(p1SelectedMoveIndex);
				} else {
					while (true) {
						ez.print("Something went wrong");
					}
				}
			}
			if (p1.isDefeated()) {
				gameNotOver = false;
				popup.showMessageDialog(b.mainPanel, "Player 2 Won the game!", "Game Finished", 2, (new ImageIcon(
						new ImageIcon("Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
				break;
			}
			if (p2.isDefeated()) {
				gameNotOver = false;
//				popup.showMessageDialog(b.mainPanel,"Player 1 Won the game!");
				popup.showMessageDialog(b.mainPanel, "Player 1 Won the game!", "Game Finished", 2, (new ImageIcon(
						new ImageIcon("Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

				break;
			}
			if (p1.getCurrentMon().getHealth() <= 0) {
				P1numberOfFaintedMons++;
				int firstOccurence = -1;
				String x[] = new String[6 - Battle.P1numberOfFaintedMons];
				int z = 0;
				for (int i = 0; i < p1.getPokemon().length; i++) {
					if (p1.getPokemon()[i].getHealth() <= 0) {

					} else if (p1.getPokemon()[i] == p1.getCurrentMon()) {

					} else {
						if (firstOccurence == -1) {
							firstOccurence = i;
						}

						x[z] = (i + 1) + ") " + p1.getPokemon()[i].getName() + "\n";
						z++;

					}
				}
//            	p1SwitchIn=Integer.parseInt(popup.showInputDialog(b.leftDisplayPanel,"Your current Pokemon fainted." +
//						" The pokemon that you can switch in are"+x+"Please enter the number of the pokemon you want to switch in.","Pokemon defeated",2,new ImageIcon (new ImageIcon("Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
//				p1.switchOut(p1.getPokemon()[p1SwitchIn-1]);
				String y = (String) popup.showInputDialog(b.leftDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in.",
						"Pokemon defeated", 2,
						new ImageIcon(
								new ImageIcon("Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x, "Please select a Pokemon");

				p1SwitchIn = Integer.parseInt(String.valueOf(y.charAt(0)));
				p1.switchOut(p1.getPokemon()[p1SwitchIn - 1]);

				p1SwitchIn = -1;
			}
			if (p2.getCurrentMon().getHealth() <= 0) {
				P2numberOfFaintedMons++;
				int firstOccurence = -1;
				int z = 0;
				String[] x = new String[6 - Battle.P2numberOfFaintedMons];
				for (int i = 0; i < p2.getPokemon().length; i++) {
					if (p2.getPokemon()[i].getHealth() <= 0) {

					} else if (p2.getPokemon()[i] == p2.getCurrentMon()) {

					} else {
						if (firstOccurence == -1) {
							firstOccurence = i;
						}
						x[z] = (i + 1) + ") " + p2.getPokemon()[i].getName() + "\n";
						z++;
					}
				}
				String y = (String) popup.showInputDialog(b.rightDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in.",
						"Pokemon defeated", 2,
						new ImageIcon(
								new ImageIcon("Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x, "Please select a Pokemon");

				p2SwitchIn = Integer.parseInt(String.valueOf(y.charAt(0)));
				p2.switchOut(p2.getPokemon()[p2SwitchIn - 1]);

				p2SwitchIn = -1;
			}
//			ez.println(b.leftText.getBounds().height);
//			ez.println(b.leftText.getBounds().width);
//			ez.println(b.rightText.getBounds().height);
//			ez.println(b.rightText.getBounds().width);
//			ez.println(b..getWidth());
			b.repaint(p1, p2);
//            ez.println(b.image4.getWidth());
//			ez.println(b.leftText.getBounds().height);
//			ez.println(b.leftText.getBounds().width);
//			ez.println(b.rightText.getBounds().height);
//			ez.println(b.rightText.getBounds().width);
		}
//

//        ez.print("P1) Select your first pokemon's first move");
//        givenMoves[0][0]=kboard.nextInt();
//        ez.print("P1) Select your first pokemon's second move");
//        givenMoves[0][1]=kboard.nextInt();
//        ez.print("P1) Select your first pokemon's third move");
//        givenMoves[0][2]=kboard.nextInt();
//        ez.print("P1) Select your first pokemon's fourth move");
//        givenMoves[0][3]=kboard.nextInt();
//        ez.print("P1) Select your second pokemon's first move");
//        givenMoves[1][0]=kboard.nextInt();
//        ez.print("P1) Select your second pokemon's second move");
//        givenMoves[1][1]=kboard.nextInt();
//        ez.print("P1) Select your second pokemon's third move");
//        givenMoves[1][2]=kboard.nextInt();
//        ez.print("P1) Select your second pokemon's fourth move");
//        givenMoves[1][3]=kboard.nextInt();
//        ez.print("P1) Select your third pokemon's first move");
//        givenMoves[2][0]=kboard.nextInt();
//        ez.print("P1) Select your third pokemon's second move");
//        givenMoves[2][1]=kboard.nextInt();
//        ez.print("P1) Select your third pokemon's third move");
//        givenMoves[2][2]=kboard.nextInt();
//        ez.print("P1) Select your third pokemon's fourth move");
//        givenMoves[2][3]=kboard.nextInt();
//        ez.print("P1) Select your fourth pokemon's first move");
//        givenMoves[3][0]=kboard.nextInt();
//        ez.print("P1) Select your fourth pokemon's second move");
//        givenMoves[3][1]=kboard.nextInt();
//        ez.print("P1) Select your fourth pokemon's third move");
//        givenMoves[3][2]=kboard.nextInt();
//        ez.print("P1) Select your fourth pokemon's fourth move");
//        givenMoves[3][3]=kboard.nextInt();

		// for(int i=0;i<6;i++){
//            ez.print(p1.getPokemon()[i].getName());
//        }
//        for(int i=0;i<6;i++){
//            ez.print(p2.getPokemon()[i].getName());
//        }
//        ez.print("Player 1's)Pokemon"+p1);
//        ez.print(p2);

		// BasicPokemon test = new BasicPokemon(1);
//    ez.println(test);
//    BasicMove anothertest =new BasicMove(1);
//        ez.println(anothertest);

//        BasicPokemon[] p1Pokemon={p11,p12,p13,p14,p15,p16};
//        BasicPokemon[] p2Pokemon={p21,p22,p23,p24,p25,p26};

		// BasicMove move1=new BasicMove("Move1",1,100,"Fire");
//        BasicMove move2=new BasicMove("Move2",2,50,"Water");
//        BasicMove move3=new BasicMove("Move3",3,150,"Dragon");
//        BasicMove move4=new BasicMove("Move4",4,50,"Fairy");
//        BasicMove[] moveSet1= {move1,move2,move3,move4};
//        BasicPokemon p11=new BasicPokemon(1,"P1 mon1",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p12=new BasicPokemon(1,"P1 mon2",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p13=new BasicPokemon(1,"P1 mon3",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p14=new BasicPokemon(1,"P1 mon3",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p15=new BasicPokemon(1,"P1 mon5",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p16=new BasicPokemon(1,"P1 mon6",
//                500,100,20,69,moveSet1,"Fighting","Grass");
//        BasicPokemon p21=new BasicPokemon(2,"P2 mon1",
//                200,50,200,400,moveSet1,"Water","Dragon");
//        BasicPokemon p22=new BasicPokemon(2,"P2 mon2",
//                200,50,200,400,moveSet1,"Water","Dragon");
//        BasicPokemon p23=new BasicPokemon(2,"P2 mon3",
//                200,50,200,400,moveSet1,"Water","Dragon");
//        BasicPokemon p24=new BasicPokemon(2,"P2 mon4",
//                200,50,200,400,moveSet1,"Water","Dragon");
//        BasicPokemon p25=new BasicPokemon(2,"P2 mon5",
//                200,50,200,400,moveSet1,"Water","Dragon");
//        BasicPokemon p26=new BasicPokemon(2,"P2 mon6",
//                200,50,200,400,moveSet1,"Water","Dragon");
		// declares an array of integers
//        p1.setOpposingPlayer(p2);
//        ez.println(p2.getCurrentMon());
//        p1.fight();
//        ez.println(p2.getCurrentMon());
//        p2.switchOut();
//        ez.println(p2.getCurrentMon());
//        p1.fight();
//        ez.println(p2.getCurrentMon());
//        p2.switchOut();
//        ez.println(p2.getCurrentMon());
//        p1.fight();
//        ez.println(p2.getCurrentMon());
	}

	public void repaint(Player p1, Player p2) {

		name1.setText(p1.getCurrentMon().getName());

		name2.setText(p2.getCurrentMon().getName());
		Icon pic = null;

//				pic =
		image1.setIcon(new ImageIcon("Sprites/SpritesBack/" + p1.getCurrentMon().getID() + "-back.gif"));

//				pic =
		image2.setIcon(new ImageIcon("Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));

		for (int i = 0; i < leftMoveButtons.length; i++) {
			leftMoveButtons[i].setText(p1.getCurrentMon().getMoves()[i].getName());
		}
		for (int i = 0; i < leftSwitchButtons.length; i++) {
			if (p1.getPokemon()[i].getHealth() <= 0) {
				leftSwitchButtons[i].setText(p1.getPokemon()[i].getName());
				leftSwitchButtons[i].setEnabled(false);
			} else if (p1.getPokemon()[i] == p1.getCurrentMon()) {
				leftSwitchButtons[i].setText(p1.getPokemon()[i].getName());
				leftSwitchButtons[i].setEnabled(false);
			} else {
				leftSwitchButtons[i].setText(p1.getPokemon()[i].getName());
				leftSwitchButtons[i].setEnabled(true);
			}
		}

		name3.setText(p2.getCurrentMon().getName());

		name4.setText(p1.getCurrentMon().getName());
		pic = null;

//		pic = ;
		image3.setIcon(new ImageIcon("Sprites/SpritesBack/" + p2.getCurrentMon().getID() + "-back.gif"));

//		pic = ;
		image4.setIcon(new ImageIcon("Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif"));

		for (int i = 0; i < rightMoveButtons.length; i++) {
			rightMoveButtons[i].setText(p2.getCurrentMon().getMoves()[i].getName());
		}
		for (int i = 0; i < rightSwitchButtons.length; i++) {
			if (p2.getPokemon()[i].getHealth() <= 0) {
				rightSwitchButtons[i].setText(p2.getPokemon()[i].getName());
				rightSwitchButtons[i].setEnabled(false);
			} else if (p2.getPokemon()[i] == p2.getCurrentMon()) {
				rightSwitchButtons[i].setText(p2.getPokemon()[i].getName());
				rightSwitchButtons[i].setEnabled(false);
			} else {
				rightSwitchButtons[i].setText(p2.getPokemon()[i].getName());
				rightSwitchButtons[i].setEnabled(true);
			}
		}

	}

}

//import java.io.*;
//		import java.util.Scanner;
//		import javax.swing.*;
//		import java.awt.*;
//		import java.awt.event.ActionEvent;
//		import java.awt.event.ActionListener;
//		import java.awt.image.BufferedImage;
//		import javax.imageio.*;
//
//public class Battle {
//	public static int P1numberOfFaintedMons=0;
//	public static int P2numberOfFaintedMons=0;
//
//	private JFrame frame;
//	private JPanel panel, display, movePanel, switchPanel;
//	private JLabel name1, name2, image1, image2, attack, switchOut;
//	private Button[] moves = new Button[4];
//	private int selection=-1;
//	private Button[] switches = new Button[6];
//	private ButtonGroup buttons=new ButtonGroup();
//	private JTextArea text;
//	private int playerPerspective=1;
//
//
//
//
//
//	public Battle(Player p1, Player p2) {
//
//		String filepath = "PokemonTitleScreen.wav";
//		PlayMusic musicObject = new PlayMusic();
//		frame = new JFrame();
//		panel = new JPanel(new GridBagLayout());
//		display = new JPanel();
//		movePanel = new JPanel();
//		switchPanel = new JPanel();
//		GridBagConstraints c = new GridBagConstraints();
//		try {
//			name1 = new JLabel(p1.getCurrentMon().getName());
//			name2 = new JLabel(p2.getCurrentMon().getName());
//			BufferedImage pic = ImageIO.read(new File("Sprites/SpritesBack/"+p1.getCurrentMon().getID()+"-back.gif"));
//			image1 = new JLabel(new ImageIcon(pic));
//			pic = ImageIO.read(new File("Sprites/SpritesFront/"+p2.getCurrentMon().getID()+".gif"));
//			image2 = new JLabel(new ImageIcon(pic));
//			GroupLayout l = new GroupLayout(display);
//			display.setLayout(l);
//			l.setAutoCreateGaps(true);
//			l.setAutoCreateContainerGaps(true);
//			l.setHorizontalGroup(l.createSequentialGroup()
//					.addGroup(l.createParallelGroup()
//							.addGap(400)
//							.addComponent(name1)
//							.addComponent(image1))
//					.addGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
//							.addComponent(name2)
//							.addComponent(image2))
//			);
//			l.setVerticalGroup(l.createSequentialGroup()
//					.addComponent(name2)
//					.addComponent(image2)
//					.addGap(100)
//					.addComponent(name1)
//					.addComponent(image1)
//			);
//			l.linkSize(SwingConstants.HORIZONTAL, name1, name2, image1, image2);
//			l.linkSize(image1, image2);
//			display.setBackground(Color.LIGHT_GRAY);
//			c.gridx = 0;
//			c.gridy = 0;
//			c.gridwidth = 4;
//			c.gridheight = 3;
//			c.weightx = 0.5;
//			c.weighty = 0.0;
//			c.anchor = GridBagConstraints.FIRST_LINE_START;
//			c.fill = GridBagConstraints.HORIZONTAL;
//			c.insets = new Insets(5, 5, 5, 0);
//			panel.add(display, c);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		attack = new JLabel("Attack");
//		c.gridx = 0;
//		c.gridy = 3;
//		c.gridwidth = 4;
//		c.gridheight = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.0;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(0, 10, 0, 0);
//		panel.add(attack, c);
//		GridLayout l = new GridLayout(1, 4);
//		movePanel.setLayout(l);
//		for(int move = 0; move < moves.length; move++) {
//			moves[move] = new Button(p1.getCurrentMon().moves[move].getMoveName(),move);
////    		moves[move].addActionListener(new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent e) {
////
////                }
////            });
//			movePanel.add(moves[move]);
//		}
//		c.gridx = 0;
//		c.gridy = 4;
//		c.gridwidth = 4;
//		c.gridheight = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.0;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(0, 0, 0, 0);
//		panel.add(movePanel, c);
//		switchOut = new JLabel("Switch");
//		c.gridx = 0;
//		c.gridy = 5;
//		c.gridwidth = 4;
//		c.gridheight = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.0;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(0, 10, 0, 0);
//		panel.add(switchOut, c);
//		l = new GridLayout(1, 6);
//		switchPanel.setLayout(l);
//		for(int mon = 0; mon < switches.length; mon++) {
//			switches[mon] = new Button(p1.getPokemon()[mon].getName(),mon+4);
////    		switches[mon].addActionListener(new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent e) {
////
////                }
////            });
//			switchPanel.add(switches[mon]);
//		}
//		c.gridx = 0;
//		c.gridy = 6;
//		c.gridwidth = 4;
//		c.gridheight = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.0;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(0, 0, 0, 0);
//		panel.add(switchPanel, c);
//		text = new JTextArea(20, 20);
//		text.setEditable(false);
//		text.setLineWrap(true);
//		text.setWrapStyleWord(true);
//		JScrollPane pane = new JScrollPane(text,
//				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		c.gridx = 4;
//		c.gridy = 0;
//		c.gridwidth = 3;
//		c.gridheight = 7;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
//		c.anchor = GridBagConstraints.FIRST_LINE_END;
//		c.fill = GridBagConstraints.BOTH;
//		c.insets = new Insets(5, 5, 5, 5);
//		panel.add(pane, c);
//		frame.getContentPane().add(panel);
//		frame.setBounds(100, 100, 800, 600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		// frame.setResizable(false);
//		frame.setTitle("NOT Pokemon");
//		frame.setVisible(true);
//	}
//
//	public static void main (String[] args) {
////    ReadFile read = new ReadFile();
////        String copy = "something went wrong; ";
////        try {
//////			copy = Files.readString(Paths.get("Stats.txt"));
////            byte[] file = Files.readAllBytes(Paths.get("ShowdownLearnsets.txt"));
////            copy = new String(file);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    ez.println(
//////            read.orderMoves(
////            (read.formatShowDownLearnSets(copy).replace("\t","")));
//		EaseOfUse ez=new EaseOfUse();
//		Scanner kboard=new Scanner(System.in);
//		Calculator calc=new Calculator();
//		BasicPokemon[] p1mons=new BasicPokemon[6];
//		BasicPokemon[] p2mons=new BasicPokemon[6];
//		int[][] givenMoves = new int[6][4];
//
//
//
//
//
//		ez.print("P1) Select your first pokemon's dex number");
//		p1mons[0]=new BasicPokemon(kboard.nextInt());
//
//		ez.print("P1) Select your second pokemon's dex number");
//		p1mons[1]=new BasicPokemon(kboard.nextInt());
//		ez.print("P1) Select your third pokemon's dex number");
//		p1mons[2]=new BasicPokemon(kboard.nextInt());
//		ez.print("P1) Select your fourth pokemon's dex number");
//		p1mons[3]=new BasicPokemon(kboard.nextInt());
//		ez.print("P1) Select your fifth pokemon's dex number");
//		p1mons[4]=new BasicPokemon(kboard.nextInt());
//		ez.print("P1) Select your sixth pokemon's dex number");
//		p1mons[5]=new BasicPokemon(kboard.nextInt());
//
//
//		ez.print("P2) Select your first pokemon's dex number");
//		p2mons[0]=new BasicPokemon(kboard.nextInt());
//		ez.print("P2) Select your second pokemon's dex number");
//		p2mons[1]=new BasicPokemon(kboard.nextInt());
//		ez.print("P2) Select your third pokemon's dex number");
//		p2mons[2]=new BasicPokemon(kboard.nextInt());
//		ez.print("P2) Select your fourth pokemon's dex number");
//		p2mons[3]=new BasicPokemon(kboard.nextInt());
//		ez.print("P2) Select your fifth pokemon's dex number");
//		p2mons[4]=new BasicPokemon(kboard.nextInt());
//		ez.print("P2) Select your sixth pokemon's dex number");
//		p2mons[5]=new BasicPokemon(kboard.nextInt());
//		givenMoves[0]= new int[]{14,370,609,1};
//		givenMoves[1]= new int[]{6,7,8,9};
//		givenMoves[2]= new int[]{10,11,12,13};
//		givenMoves[3]= new int[]{14,15,16,17};
//		givenMoves[4]= new int[]{18,19,20,21};
//		givenMoves[5]= new int[]{22,23,24,25};
//
//		Player p1=new Player(p1mons,givenMoves);
//		Player p2=new Player(p2mons,givenMoves,p1);
//		p1.setOpposingPlayer(p2);
//		Battle b= new Battle(p1,p2);
//		b.playerPerspective=1;
//
//
//		ez.println("The match has begun!");
//		boolean gameNotOver=true;
//		boolean p1WillSwitch=false;
//		boolean p2WillSwitch=false;
//		int p1SelectedMoveIndex=-1;
//		int p2SelectedMoveIndex=-1;
//		int p1SwitchIn=-1;
//		int p2SwitchIn=-1;
////		final int[] moveSelection = new int[1];
//
//		int switchSelection;
//		for(int i=0;i<b.moves.length;i++){
//
//			b.moves[i].addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
////moveSelection[0] =((Button)e.getSource()).num;
//					b.selection=((Button)e.getSource()).num;
////						((Button) e.getSource()).setEnabled(false);
////
//
//				}
//			});
//			b.buttons.add(b.moves[i]);
//		}
//		for(int i=0;i<b.switches.length;i++){
//
//			b.switches[i].addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
////moveSelection[0] =((Button)e.getSource()).num;
//					b.selection=((Button)e.getSource()).num;
////					((Button) e.getSource()).setEnabled(false);
////
//
//				}
//			});
//			b.buttons.add(b.switches[i]);
//		}
//
//		while(gameNotOver) {
//
//
//			boolean youShallNotPass = true;
//			while (youShallNotPass) {
////                ez.println("Player1 what would you like to do\n1)Switch out\n2)Fight");
////                selection = kboard.nextInt();
//				if (b.selection >=5&&P1numberOfFaintedMons<5) {
//					p1WillSwitch = true;
//					p1SwitchIn=b.selection-4;
//					youShallNotPass = false;
//				} else if (b.selection <=4 &&b.selection>=0) {
////                    ez.println("Choose which move you want to use");
////                    for (int i = 0; i < 4; i++) {
////                        ez.println((i + 1) + ")" + p1.getCurrentMon().getMoves()[i]);
////                    }
//
//					p1SelectedMoveIndex = b.selection;
//					youShallNotPass = false;
//				}
////                else if(b.selection >=5 &&P1numberOfFaintedMons>=5)
//////				{
//////                    ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
//////                }
//				else {
//					ez.print("");
//				}
//			}
//
//			youShallNotPass=true;
//			b.selection=-1;
//			while(youShallNotPass) {
//				ez.println("Player2 what would you like to do\n1)Switch out\n2)Fight");
//				b.selection = kboard.nextInt();
//				if (b.selection == 1) {
//					p2WillSwitch = true;
//					youShallNotPass = false;
//				} else if (b.selection == 2) {
//					ez.println("Choose which move you want to use");
//					for (int i = 0; i < 4; i++) {
//						ez.println((i + 1) + ")" + p2.getCurrentMon().getMoves()[i]);
//					}
//					p2SelectedMoveIndex = kboard.nextInt() - 1;
//					youShallNotPass = false;
//				}
//				else if(b.selection == 1&&P2numberOfFaintedMons>=5){
//					ez.print("You have no available pokemon to switch in, because every other Pokemon has fainted");
//				}else {
//					ez.println("That wasn't a valid input");
//				}
//			}
//			b.selection=-1;
//			if(p1WillSwitch && p2WillSwitch){
//				if(p1.getCurrentMon().getSpeed()>p2.getCurrentMon().getSpeed()){
//					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//					p1WillSwitch=false;
//				}
//				else if(p2.getCurrentMon().getSpeed()>p1.getCurrentMon().getSpeed()){
//					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//					p1WillSwitch=false;
//				}
//				else{
//					if(Math.random()>0.5){
//						p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//						p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//						p1WillSwitch=false;
//					}
//					else{
//						p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//						p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//						p1WillSwitch=false;
//					}
//				}
//
//			}
//			else if(p1WillSwitch){
//				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//				p2.fight(p2SelectedMoveIndex);
//				p1WillSwitch=false;
//			}
//			else if(p2WillSwitch){
//				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//				p1.fight(p1SelectedMoveIndex);
//				p1WillSwitch=false;
//			}
//			else{
//
//				int x=calc.calculateWhoGoesFirst(p1,p2,p1.getCurrentMon().getMoves()[p1SelectedMoveIndex],p2.getCurrentMon().getMoves()[p2SelectedMoveIndex]);
//				if(x==1){
//					p1.fight(p1SelectedMoveIndex);
//					p2.fight(p2SelectedMoveIndex);
//				}
//				else if(x==2){
//					p2.fight(p2SelectedMoveIndex);
//					p1.fight(p1SelectedMoveIndex);
//				}
//				else{
//					while(true) {
//						ez.print("Something went wrong");
//					}
//				}
//			}
//			if(p1.isDefeated()){
//				gameNotOver=false;
//				break;
//			}
//			if(p2.isDefeated()){
//				gameNotOver=false;
//				break;
//			}
//			if(p1.getCurrentMon().getHealth()<=0){
//				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
//				P1numberOfFaintedMons++;
//			}
//			if(p2.getCurrentMon().getHealth()<=0){
//				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
//				P2numberOfFaintedMons++;
//			}
//			b.repaint(p1,p2);
//		}
////
//
//
//
////        ez.print("P1) Select your first pokemon's first move");
////        givenMoves[0][0]=kboard.nextInt();
////        ez.print("P1) Select your first pokemon's second move");
////        givenMoves[0][1]=kboard.nextInt();
////        ez.print("P1) Select your first pokemon's third move");
////        givenMoves[0][2]=kboard.nextInt();
////        ez.print("P1) Select your first pokemon's fourth move");
////        givenMoves[0][3]=kboard.nextInt();
////        ez.print("P1) Select your second pokemon's first move");
////        givenMoves[1][0]=kboard.nextInt();
////        ez.print("P1) Select your second pokemon's second move");
////        givenMoves[1][1]=kboard.nextInt();
////        ez.print("P1) Select your second pokemon's third move");
////        givenMoves[1][2]=kboard.nextInt();
////        ez.print("P1) Select your second pokemon's fourth move");
////        givenMoves[1][3]=kboard.nextInt();
////        ez.print("P1) Select your third pokemon's first move");
////        givenMoves[2][0]=kboard.nextInt();
////        ez.print("P1) Select your third pokemon's second move");
////        givenMoves[2][1]=kboard.nextInt();
////        ez.print("P1) Select your third pokemon's third move");
////        givenMoves[2][2]=kboard.nextInt();
////        ez.print("P1) Select your third pokemon's fourth move");
////        givenMoves[2][3]=kboard.nextInt();
////        ez.print("P1) Select your fourth pokemon's first move");
////        givenMoves[3][0]=kboard.nextInt();
////        ez.print("P1) Select your fourth pokemon's second move");
////        givenMoves[3][1]=kboard.nextInt();
////        ez.print("P1) Select your fourth pokemon's third move");
////        givenMoves[3][2]=kboard.nextInt();
////        ez.print("P1) Select your fourth pokemon's fourth move");
////        givenMoves[3][3]=kboard.nextInt();
//
//		//        for(int i=0;i<6;i++){
////            ez.print(p1.getPokemon()[i].getName());
////        }
////        for(int i=0;i<6;i++){
////            ez.print(p2.getPokemon()[i].getName());
////        }
////        ez.print("Player 1's)Pokemon"+p1);
////        ez.print(p2);
//
//		//        BasicPokemon test = new BasicPokemon(1);
////    ez.println(test);
////    BasicMove anothertest =new BasicMove(1);
////        ez.println(anothertest);
//
////        BasicPokemon[] p1Pokemon={p11,p12,p13,p14,p15,p16};
////        BasicPokemon[] p2Pokemon={p21,p22,p23,p24,p25,p26};
//
//		//        BasicMove move1=new BasicMove("Move1",1,100,"Fire");
////        BasicMove move2=new BasicMove("Move2",2,50,"Water");
////        BasicMove move3=new BasicMove("Move3",3,150,"Dragon");
////        BasicMove move4=new BasicMove("Move4",4,50,"Fairy");
////        BasicMove[] moveSet1= {move1,move2,move3,move4};
////        BasicPokemon p11=new BasicPokemon(1,"P1 mon1",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p12=new BasicPokemon(1,"P1 mon2",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p13=new BasicPokemon(1,"P1 mon3",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p14=new BasicPokemon(1,"P1 mon3",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p15=new BasicPokemon(1,"P1 mon5",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p16=new BasicPokemon(1,"P1 mon6",
////                500,100,20,69,moveSet1,"Fighting","Grass");
////        BasicPokemon p21=new BasicPokemon(2,"P2 mon1",
////                200,50,200,400,moveSet1,"Water","Dragon");
////        BasicPokemon p22=new BasicPokemon(2,"P2 mon2",
////                200,50,200,400,moveSet1,"Water","Dragon");
////        BasicPokemon p23=new BasicPokemon(2,"P2 mon3",
////                200,50,200,400,moveSet1,"Water","Dragon");
////        BasicPokemon p24=new BasicPokemon(2,"P2 mon4",
////                200,50,200,400,moveSet1,"Water","Dragon");
////        BasicPokemon p25=new BasicPokemon(2,"P2 mon5",
////                200,50,200,400,moveSet1,"Water","Dragon");
////        BasicPokemon p26=new BasicPokemon(2,"P2 mon6",
////                200,50,200,400,moveSet1,"Water","Dragon");
//		// declares an array of integers
////        p1.setOpposingPlayer(p2);
////        ez.println(p2.getCurrentMon());
////        p1.fight();
////        ez.println(p2.getCurrentMon());
////        p2.switchOut();
////        ez.println(p2.getCurrentMon());
////        p1.fight();
////        ez.println(p2.getCurrentMon());
////        p2.switchOut();
////        ez.println(p2.getCurrentMon());
////        p1.fight();
////        ez.println(p2.getCurrentMon());
//	}
//	public void repaint(Player p1, Player p2) {
//		if (playerPerspective==1) {
//			name1.setText(p1.getCurrentMon().getName());
//
//			name2 = new JLabel(p2.getCurrentMon().getName());
//			BufferedImage pic = null;
//			try {
//				pic = ImageIO.read(new File("Sprites/SpritesBack/" + p1.getCurrentMon().getID() + "-back.gif"));
//				image1 = new JLabel(new ImageIcon(pic));
//			} catch (Exception e) {
//
//			}
//			image1.setIcon(new ImageIcon(pic));
//			try {
//				pic = ImageIO.read(new File("Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));
//			} catch (Exception e) {
//
//			}
//			image2.setIcon(new ImageIcon(pic));
//			for (int i =0;i<moves.length;i++){
//				moves[i].setText(p1.getCurrentMon().getMoves()[i].getMoveName());
//			}
//			for (int i =0;i<switches.length;i++){
//				if(p1.getPokemon()[i].getHealth()<=0){
//					switches[i].setText(p1.getPokemon()[i].getName()+" (Fainted)");
//					switches[i].setEnabled(false);
//				}
//				else if(p1.getPokemon()[i]==p1.getCurrentMon()){
//					switches[i].setText(p1.getPokemon()[i].getName()+" (Already in Play)");
//					switches[i].setEnabled(false);
//				}
//				else{
//					switches[i].setText(p1.getPokemon()[i].getName());
//					switches[i].setEnabled(true);
//				}
//			}
//		}
//
//	}
//
//}