package ohtu;

public class TennisGame {
    
    private int player1score = 0;
    private int player2score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        player1score = 0;
        player2score = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)){
            player1score++;
        } else {
            player2score++;
        }
    }

    public String getScore() {
        String[] texts = {"Love","Fifteen","Thirty","Forty"};
        String score = "";
        
        int tempScore=0;
        
        if (player1score==player2score)
        {
            if(player1score < 4){
                score = texts[player1score]+"-All";
            } else {
                score = "Deuce";
            }
        }
        else if (player1score >= 4 || player2score >= 4)
        {
            int minusResult = player1score-player2score;
            if (minusResult==1){
                score ="Advantage "+player1Name;
            }
            else if (minusResult ==-1){
                score ="Advantage "+player2Name;
            }
            else if (minusResult >= 2){
                score = "Win for "+player1Name;
            }
            else{
                score = "Win for "+player2Name;
            }
        }
        else
        {
            for (int i=1; i<3; i++)
            {
                if (i==1){
                    tempScore = player1score;
                }
                else {
                    score+="-";
                    tempScore = player2score;
                }
                score += texts[tempScore];
            }
        }
        return score;
    }
}