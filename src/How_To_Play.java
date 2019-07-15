import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class How_To_Play extends JFrame{
   public How_To_Play() {
      setTitle("How To Play?");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500, 200);
      setLocationRelativeTo(null);
      setResizable(false); // frame size fixed
      
      JPanel How_To_Play = new JPanel();
      
      JLabel explain = new JLabel();
      JButton prev = new JButton("<< Previous");
      prev.setContentAreaFilled(false);
      
      explain.setText("<html>== 게임 방법 == "
            + "<br>1. 조작 키는 qwe/asd/zxc 이고 각각 1, 2, 3/4, 5, 6/7, 8, 9 번째 두더지와  대응된다.\n"
            + "<br>2. 난이도를 높이면 주어지는 시간, 두더지를 잡았을 때 점수가 낮아진다."
            + "<br>3. 즐거운 게임 플레이 되세요!</html>");
      
      prev.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //goto previous frame ;)
            dispose();
            new Title();
         }
      });
      
      How_To_Play.add(explain);
      How_To_Play.add(prev);
      this.add(How_To_Play);
      
      setVisible(true);
   }
}