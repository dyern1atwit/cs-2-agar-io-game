package edu.wit.cs.dyermccoy.agario;

public class Settings {

	final static int mainMenuHeight = 500;

	final static int mainMenuWidth = 500;

	final static int windowWidth = 975;

	final static int windowHeight = 1000;

	final static int limitFood = (int) (windowWidth * .05);

	final static int limitVirus = 2;
	
	final static double Minspeed = .2;
	
	final static double speedRate = .000006;
	
	final static double growthRate = .6;

	final static int GRIDHEIGHT = 5;

	final static int GRIDWIDTH = 5;
	
	final static String HowTo = "Use your mouse to direct the blue circle. \n"
			+ "Move over the black food to grow, move over smaller players to grow more. \n"
			+ "Do not be eaten by circles that are bigger than you.\n"
			+ "When finished hit escape on keyboard to exit.";


}
