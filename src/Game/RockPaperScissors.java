package Game;

import java.util.Scanner;

//Action interface'i
interface Action {
 String chooseAction();
}

//Player sınıfı, Action interface'ini uygular
abstract class Player implements Action {
 String name;
 int score;

 // Constructor
 Player(String name) {
     this.name = name;
     this.score = 0;
 }
}

//HumanPlayer sınıfı, Player sınıfından kalıtılmıstır
class HumanPlayer extends Player {
 // Constructor
 HumanPlayer(String name) {
     super(name);
 }

 // Action interface'ini uygularız
 @Override
 public String chooseAction() {
     Scanner scanner = new Scanner(System.in);
     System.out.print(name + ", secimini gir (tas, kagit,makas): ");
     return scanner.nextLine().toLowerCase();
 }
}

//ComputerPlayer sınıfı, Player sınıfından kalıtılmıstır
class ComputerPlayer extends Player {
 // Constructor
 ComputerPlayer(String name) {
     super(name);
 }

 // Action interface'ini uygularız
 @Override
 public String chooseAction() {
     String[] actions = {"tas", "kagit", "makas"};
     return actions[(int) (Math.random() * actions.length)];
 }
}

//Game sınıfı
class RockPaperScissorsGame {
 Player player1, player2;

 // Constructor
 RockPaperScissorsGame(Player player1, Player player2) {
     this.player1 = player1;
     this.player2 = player2;
 }

 // Karar verme metodu
 Player determineWinner(String action1, String action2) {
     if (action1.equals(action2))
         return null; // Berabere
     else if ((action1.equals("tas") && action2.equals("makas")) ||
             (action1.equals("kagit") && action2.equals("tas")) ||
             (action1.equals("makas") && action2.equals("kag�t")))
         return player1;
     else
         return player2;
 }

 // Tur oynama metodu
 void playRound() {
     System.out.println(player1.name + " vs. " + player2.name);
     String action1 = player1.chooseAction();
     String action2 = player2.chooseAction();

     System.out.println(player1.name + " chose " + action1);
     System.out.println(player2.name + " chose " + action2);

     Player winner = determineWinner(action1, action2);
     if (winner != null) {
         System.out.println(winner.name + " oyunu kazand�n");
         winner.score++;
     } else {
         System.out.println("beraberlikkk");
     }
 }

 // Kazananı ekrana yazdırma metodu
 void displayWinner() {
     if (player1.score > player2.score)
         System.out.println(player1.name + " oyunu kazan�yor " + player1.score + " points!");
     else if (player1.score < player2.score)
         System.out.println(player2.name + " oyunu kazan�yor " + player2.score + " points!");
     else
         System.out.println("beraberlikk");
 }
}

//Main class
public class  RockPaperScissors {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     // Oyuncuları olustur
     System.out.print("ismini gir: ");
     String player1Name = scanner.nextLine();
     Player player1 = new HumanPlayer(player1Name);

     Player player2 = new ComputerPlayer("Computer");

     // Oyunu baslat
     RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2);
     System.out.print("istedgin el say�s�n� gir: ");
     int numRounds = scanner.nextInt();

     for (int i = 0; i < numRounds; i++) {
         System.out.println("\nRound " + (i + 1) + ":");
         game.playRound();
     }

     System.out.println("\nGame Over!");
     game.displayWinner();
 }
}
