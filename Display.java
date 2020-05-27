import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Luis Juarez
 */


public class Display {
    private JButton displayButton;
    public JLabel displayLabel;
    public JPanel Panel1;
    private String totalText = "";

    public Display() {
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int count = 0; count < JavaFinal.teamStats.size(); count++)
                {
                    String currentTeam = JavaFinal.teamStats.get(count).getTeamName();
                    String currentWin =JavaFinal.teamStats.get(count).getWins();
                    String currentLoss = JavaFinal.teamStats.get(count).getLosses();
                    String show =  currentTeam + " " + currentWin + " " + currentLoss ;
                    totalText += show;
                }
                displayLabel.setText(totalText);

            }
        });
    }
}
