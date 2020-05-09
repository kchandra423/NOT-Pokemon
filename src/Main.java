import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class Main extends JFrame {
    public static int P1numberOfFaintedMons=0;
    public static int P2numberOfFaintedMons=0;
    
    private JFrame frame;
    private JPanel panel, display, movePanel, switchPanel;
    private JLabel name1, name2, image1, image2, attack, switchOut;
    private JButton[] movesButtons = new JButton[4];
    private JButton[] switchesButtons = new JButton[6];
    private JTextArea text;

    public Main() {

        EaseOfUse ez=new EaseOfUse();
        Scanner kboard=new Scanner(System.in);
        Calculator calc=new Calculator();
        String filepath = "PokemonTitleScreen.wav";
        PlayMusic musicObject = new PlayMusic();
        musicObject.playMusic(filepath);
        BasicPokemon[] p1mons=new BasicPokemon[6];
        BasicPokemon[] p2mons=new BasicPokemon[6];
        int[][] givenMoves = new int[6][4];

        ez.print("P1) Select your first pokemon's dex number");
        p1mons[0]=new BasicPokemon(kboard.nextInt());

        ez.print("P1) Select your second pokemon's dex number");
        p1mons[1]=new BasicPokemon(kboard.nextInt());
        ez.print("P1) Select your third pokemon's dex number");
        p1mons[2]=new BasicPokemon(kboard.nextInt());
        ez.print("P1) Select your fourth pokemon's dex number");
        p1mons[3]=new BasicPokemon(kboard.nextInt());
        ez.print("P1) Select your fifth pokemon's dex number");
        p1mons[4]=new BasicPokemon(kboard.nextInt());
        ez.print("P1) Select your sixth pokemon's dex number");
        p1mons[5]=new BasicPokemon(kboard.nextInt());


        ez.print("P2) Select your first pokemon's dex number");
        p2mons[0]=new BasicPokemon(kboard.nextInt());
        ez.print("P2) Select your second pokemon's dex number");
        p2mons[1]=new BasicPokemon(kboard.nextInt());
        ez.print("P2) Select your third pokemon's dex number");
        p2mons[2]=new BasicPokemon(kboard.nextInt());
        ez.print("P2) Select your fourth pokemon's dex number");
        p2mons[3]=new BasicPokemon(kboard.nextInt());
        ez.print("P2) Select your fifth pokemon's dex number");
        p2mons[4]=new BasicPokemon(kboard.nextInt());
        ez.print("P2) Select your sixth pokemon's dex number");
        p2mons[5]=new BasicPokemon(kboard.nextInt());
        givenMoves[0]= new int[]{14,370,609,1};
        givenMoves[1]= new int[]{6,7,8,9};
        givenMoves[2]= new int[]{10,11,12,13};
        givenMoves[3]= new int[]{14,15,16,17};
        givenMoves[4]= new int[]{18,19,20,21};
        givenMoves[5]= new int[]{22,23,24,25};

        Player p1=new Player(p1mons,givenMoves);
        Player p2=new Player(p2mons,givenMoves,p1);
        p1.setOpposingPlayer(p2);
        frame = new JFrame();
    	panel = new JPanel(new GridBagLayout());
    	display = new JPanel();
    	movePanel = new JPanel();
    	switchPanel = new JPanel();
    	GridBagConstraints c = new GridBagConstraints();
    	try {
    		name1 = new JLabel(p1.currentMon.getName());
    		name2 = new JLabel(p2.currentMon.getName());
			BufferedImage pic = ImageIO.read(new File("Sprites/SpritesBack/"+p1.currentMon.getID()+"-back.gif"));
			image1 = new JLabel(new ImageIcon(pic));
			pic = ImageIO.read(new File("Sprites/SpritesFront/"+p2.currentMon.getID()+".gif"));
			image2 = new JLabel(new ImageIcon(pic));
			GroupLayout l = new GroupLayout(display);
			display.setLayout(l);
			l.setAutoCreateGaps(true);
			l.setAutoCreateContainerGaps(true);
			l.setHorizontalGroup(l.createSequentialGroup()
				.addGroup(l.createParallelGroup()
					.addGap(400)
					.addComponent(name1)
					.addComponent(image1))
				.addGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(name2)
					.addComponent(image2))
			);
			l.setVerticalGroup(l.createSequentialGroup()
				.addComponent(name2)
				.addComponent(image2)
				.addGap(100)
				.addComponent(name1)
				.addComponent(image1)
			);
			l.linkSize(SwingConstants.HORIZONTAL, name1, name2, image1, image2);
			l.linkSize(image1, image2);
			display.setBackground(Color.LIGHT_GRAY);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 4;
			c.gridheight = 3;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 5, 5, 0);
			panel.add(display, c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	panel.add(attack, c);
    	GridLayout l = new GridLayout(1, 4);
    	movePanel.setLayout(l);
    	for(int move = 0; move < moves.length; move++) {
    		moves[move] = new JButton("" + (move+1));
    		moves[move].addActionListener(){

            }
    		movePanel.add(moves[move]);
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
    	panel.add(movePanel, c);
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
    	panel.add(switchOut, c);
    	l = new GridLayout(1, 6);
    	switchPanel.setLayout(l);
    	for(int mon = 0; mon < switches.length; mon++) {
    		switches[mon] = new JButton("" + (mon+1));
    		switches[mon].addActionListener(this);
    		switchPanel.add(switches[mon]);
    	}
    	c.gridx = 0;
    	c.gridy = 6;
    	c.gridwidth = 4;
    	c.gridheight = 1;
    	c.weightx = 0.5;
		c.weighty = 0.0;
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, 0, 0, 0);
    	panel.add(switchPanel, c);
    	text = new JTextArea(20, 20);
    	text.setEditable(false);
    	text.setLineWrap(true);
    	text.setWrapStyleWord(true);
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
    	frame.setBounds(100, 100, 800, 600);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	// frame.setResizable(false);
    	frame.setTitle("NOT Pokemon");
    	frame.setVisible(true);
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

    	new Main();

        EaseOfUse ez=new EaseOfUse();
        Scanner kboard=new Scanner(System.in);
        Calculator calc=new Calculator();
//        String filepath = "PokemonTitleScreen.wav";
//        PlayMusic musicObject = new PlayMusic();
//        musicObject.playMusic(filepath);
//        BasicPokemon[] p1mons=new BasicPokemon[6];
//        BasicPokemon[] p2mons=new BasicPokemon[6];
//        int[][] givenMoves = new int[6][4];
//
//        ez.print("P1) Select your first pokemon's dex number");
//        p1mons[0]=new BasicPokemon(kboard.nextInt());
//
//        ez.print("P1) Select your second pokemon's dex number");
//        p1mons[1]=new BasicPokemon(kboard.nextInt());
//        ez.print("P1) Select your third pokemon's dex number");
//        p1mons[2]=new BasicPokemon(kboard.nextInt());
//        ez.print("P1) Select your fourth pokemon's dex number");
//        p1mons[3]=new BasicPokemon(kboard.nextInt());
//        ez.print("P1) Select your fifth pokemon's dex number");
//        p1mons[4]=new BasicPokemon(kboard.nextInt());
//        ez.print("P1) Select your sixth pokemon's dex number");
//        p1mons[5]=new BasicPokemon(kboard.nextInt());
//
//
//        ez.print("P2) Select your first pokemon's dex number");
//        p2mons[0]=new BasicPokemon(kboard.nextInt());
//        ez.print("P2) Select your second pokemon's dex number");
//        p2mons[1]=new BasicPokemon(kboard.nextInt());
//        ez.print("P2) Select your third pokemon's dex number");
//        p2mons[2]=new BasicPokemon(kboard.nextInt());
//        ez.print("P2) Select your fourth pokemon's dex number");
//        p2mons[3]=new BasicPokemon(kboard.nextInt());
//        ez.print("P2) Select your fifth pokemon's dex number");
//        p2mons[4]=new BasicPokemon(kboard.nextInt());
//        ez.print("P2) Select your sixth pokemon's dex number");
//        p2mons[5]=new BasicPokemon(kboard.nextInt());
//        givenMoves[0]= new int[]{14,370,609,1};
//        givenMoves[1]= new int[]{6,7,8,9};
//        givenMoves[2]= new int[]{10,11,12,13};
//        givenMoves[3]= new int[]{14,15,16,17};
//        givenMoves[4]= new int[]{18,19,20,21};
//        givenMoves[5]= new int[]{22,23,24,25};
//
//        Player p1=new Player(p1mons,givenMoves);
//        Player p2=new Player(p2mons,givenMoves,p1);
//        p1.setOpposingPlayer(p2);
//        System.out.println("The match has begun!");
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
                        System.out.println((i + 1) + ")" + p1.currentMon.getMoves()[i]);
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
                        System.out.println((i + 1) + ")" + p2.currentMon.getMoves()[i]);
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
                if(p1.currentMon.getSpeed()>p2.currentMon.getSpeed()){
                    p1.switchOut();
                    p2.switchOut();
                }
                else if(p2.currentMon.getSpeed()>p1.currentMon.getSpeed()){
                    p2.switchOut();
                    p1.switchOut();
                }
                else{
                    if(Math.random()>0.5){
                        p1.switchOut();
                        p2.switchOut();
                    }
                    else{
                        p2.switchOut();
                        p1.switchOut();
                    }
                }

            }
            else if(p1WillSwitch==true){
                p1.switchOut();
                p2.fight(p2SelectedMoveIndex);
            }
            else if(p2WillSwitch==true){
                p2.switchOut();
                p1.fight(p1SelectedMoveIndex);
            }
            else{

                int x=calc.calculateWhoGoesFirst(p1,p2,p1.currentMon.getMoves()[p1SelectedMoveIndex],p2.currentMon.getMoves()[p2SelectedMoveIndex]);
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
            if(p1.currentMon.getHealth()<=0){
                p1.switchOut();
                P1numberOfFaintedMons++;
            }
            if(p2.currentMon.getHealth()<=0){
                p2.switchOut();
                P2numberOfFaintedMons++;
            }
        }




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

        //        for(int i=0;i<6;i++){
//            ez.print(p1.pokemon[i].getName());
//        }
//        for(int i=0;i<6;i++){
//            ez.print(p2.pokemon[i].getName());
//        }
//        ez.print("Player 1's)Pokemon"+p1);
//        ez.print(p2);

        //        BasicPokemon test = new BasicPokemon(1);
//    System.out.println(test);
//    BasicMove anothertest =new BasicMove(1);
//        System.out.println(anothertest);

//        BasicPokemon[] p1Pokemon={p11,p12,p13,p14,p15,p16};
//        BasicPokemon[] p2Pokemon={p21,p22,p23,p24,p25,p26};

        //        BasicMove move1=new BasicMove("Move1",1,100,"Fire");
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
//        System.out.println(p2.currentMon);
//        p1.fight();
//        System.out.println(p2.currentMon);
//        p2.switchOut();
//        System.out.println(p2.currentMon);
//        p1.fight();
//        System.out.println(p2.currentMon);
//        p2.switchOut();
//        System.out.println(p2.currentMon);
//        p1.fight();
//        System.out.println(p2.currentMon);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


}
