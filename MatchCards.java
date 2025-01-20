import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class MatchCards {
    class Card{
        String cardName;
        ImageIcon cardImageIcon;

        Card(String cardName, ImageIcon cardImageIcon){
            this.cardName=cardName;
            this.cardImageIcon=cardImageIcon;
        }
        public String toString(){
            return cardName;
        }
    }
    String[] cardList = {
            "inner1",
            "inner2",
            "inner3",
            "inner4",
            "inner5",
            "inner6",
            "inner7",
            "inner8",
            "inner9",
            "inner10"

    };
    int rows=4;
    int colums=5;
    int cardWidth=90;
    int cardHeight=128;

    ArrayList<Card> cardSet;
    ImageIcon cardBackImageIcon;

    int boardWidth = colums* cardWidth; //5*128 = 640px
    int boardHeight = rows* cardHeight; //4*90 =360px

    JFrame frame = new JFrame ("Pokémon Match Cards");
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel ();
    JPanel boardPanel = new JPanel ();
    JPanel restartGamePanel = new JPanel ();
    JButton restartButton = new JButton ()



    int errorCount =0;
    ArrayList<JButton> board;


    MatchCards() {
       setupCards();
       shuffleCards();
       frame.setVisible (true);
       frame.setLayout (new BorderLayout ());
       frame.setSize (boardWidth, boardHeight);
       frame.setLocationRelativeTo (null);
       frame.setResizable (false);
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       textLable.setFont (new Font ("Arial",Font.PLAIN,20));
       textLable.setHorizontalAlignment (JLabel.CENTER);
       textLable.setText ("Error: "+ Integer.toString (errorCount));

       textPanel.setPreferredSize (new Dimension (boardWidth,30));
       textPanel.add(textLable);
       frame.add (textPanel, BorderLayout.NORTH);
       board = new ArrayList<JButton> ();
       boardPanel.setLayout (new GridLayout (rows,colums));
       for (int i=0; i < cardSet.size ();i++){
           JButton tile = new JButton ();
           tile.setPreferredSize (new Dimension (cardWidth,cardHeight));
           tile.setOpaque (true);
           tile.setIcon (cardSet.get (i).cardImageIcon);
           tile.setFocusable (false);
           board.add (tile);
           boardPanel.add (tile);
       }
       frame.add(boardPanel);

       // restart game button
        restartButton.setFont (new Font ("Arial",Font.PLAIN,16));
        restartButton.setText ("Restart Game");
        restartButton.setPreferredSize (new Dimension (boardWidth,30));
        restartButton.setFocusable (false);

       frame.pack ();
       frame.setVisible (true);
    }

    void setupCards(){
        cardSet = new ArrayList<Card>();
        for (String cardName : cardList){
            //load each card image
            Image cardImg = new ImageIcon(getClass().getResource("./assests/" + cardName + ".jpg")).getImage ();
            ImageIcon cardImageIcon = new ImageIcon (cardImg.getScaledInstance (cardWidth,cardHeight, Image.SCALE_SMOOTH));
            //create card object and add to cardSet
            Card card = new Card (cardName,cardImageIcon);
            cardSet.add (card);
        }
        cardSet.addAll (cardSet);

        //image the back card image
        Image cardBackImg = new ImageIcon (getClass ().getResource ("./assests/OuterCards.jpg")).getImage ();
        cardBackImageIcon = new ImageIcon (cardBackImg.getScaledInstance (cardWidth,cardHeight, Image.SCALE_SMOOTH));

    }
    void  shuffleCards(){
        System.out.println (cardSet);
        //shuffle
        for (int i =0; i< cardSet.size();i++){
            int j = (int) (Math.random () * cardSet.size ()); //get random index
            //swap
            Card temp = cardSet.get(i);
            cardSet.set (i, cardSet.get(j));
            cardSet.set (j, temp);
        }
        System.out.println (cardSet);
    }
}
