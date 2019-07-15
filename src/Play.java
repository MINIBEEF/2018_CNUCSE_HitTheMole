import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * MEMO - 박민
 * 
 * 일정 딜레이(Thread) 마다 난수 발생 => 그 난수 %9 => 해당 버튼 1로 변경
 * 
 * Event 처리 => 버튼 눌렀을 때 버튼의 (getName() == 1)이면 score++
 * 
 * -전부 내부클래스로 작성해야함 (접근성)
 */

public class Play extends JFrame {
	public int Difficult;
	public int Diff_Score;
	public int Pop_Speed;

	ImageIcon Icondudeoji = new ImageIcon("두더지.png");
	Image imgdudeoji = Icondudeoji.getImage();
	Image chdudeoji = imgdudeoji.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Alive = new ImageIcon(chdudeoji);

	ImageIcon Icongumeong = new ImageIcon("두더지구멍.PNG");
	Image imggumeong = Icongumeong.getImage();
	Image chgumeong = imggumeong.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Hole = new ImageIcon(chgumeong);

	ImageIcon Icondie = new ImageIcon("두더지죽음.PNG");
	Image imgdie = Icondie.getImage();
	Image chdie = imgdie.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Dead = new ImageIcon(chdie);

	BufferedImage img = null;
	Random rand = new Random();
	JButton[] Digda = new JButton[9]; // 두더지가 될 버튼들, Thread에서 접근하기 위해 멤버로 선언
	private int Popup = 0;
	private int score = 0;
	private long RealTime_Clock_Start = System.currentTimeMillis(); // 게임 잔행 시간
	private long Clock = 0;

	boolean Thread_Dead = false;

	JLabel View_ScoreBoard = new JLabel(); // TODO Thread, 화면에 표시되는 스코어보드
	JLabel View_RealTime_Clock = new JLabel(); // TODO Thread, 화면에 표시되는 현재 시간

	class Game_RealTime_Controller_Thread implements Runnable { // 주기적으로 화면에 출력되는 score와 진행시간(RealTime_Clock)을 관리
		public void rankSave() {
			File f1 = new File("ranking_easy.txt");
			File f2 = new File("ranking_normal.txt");
			File f3 = new File("ranking_hard.txt");
			if (Difficult == 0) {
				try {
					PrintWriter pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1, true)));
					pw1.write("\n" + score);
					pw1.flush();
					pw1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (Difficult == 1) {
				try {
					PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2, true)));
					pw2.write("\n" + score);
					pw2.flush();
					pw2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (Difficult == 2) {
				try {
					PrintWriter pw3 = new PrintWriter(new BufferedWriter(new FileWriter(f3, true)));
					pw3.write("\n" + score);
					pw3.flush();
					pw3.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void run() {
			// 작업중 TODO 박민
			while (true) {
				Clock = (System.currentTimeMillis() - RealTime_Clock_Start) / 1000;

				View_RealTime_Clock.setText("Time ==> " + Long.toString(Clock));
				View_ScoreBoard.setText("SC0re ==> " + Integer.toString(score));
				/* 타임아웃 */
				if (Clock >= 20) {
					dispose();
					rankSave();
					JOptionPane.showMessageDialog(null, "Time Out !!! (20 seconds)");
					new Game_Start();
					Thread_Dead = true;
					break;
				}
			}
		}
	}

	class Game_Controller_Thread implements Runnable {
		public void run() {
			while (true) {
				/* 두더지 나오고 들어감 */
				Digda[Popup].setIcon(Digda_Hole);
				Popup = rand.nextInt(10) % 9;
				Digda[Popup].setIcon(Digda_Alive);

				try {
					Thread.sleep(Pop_Speed);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	class Digda_Event implements ActionListener { // 두더지 누르면 스코어++
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource();

			if (temp.getIcon().equals(Digda_Alive)) {
				score += Diff_Score;
				temp.setIcon(Digda_Dead);
			} else {
				score -= Diff_Score;
			}
		}
	}

	public Play(int diff) {
		this.Difficult = diff;

		setTitle("Play");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false); // frame size fixed
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel Status_Tabel = new JPanel();
		Status_Tabel.setBackground(Color.GRAY);

		Status_Tabel.add(View_ScoreBoard);
		Status_Tabel.add(View_RealTime_Clock);

		JPanel Game_Tabel = new JPanel();
		Game_Tabel.setBackground(Color.WHITE);
		Game_Tabel.setLayout(new GridLayout(3, 3, 30, 30));

		switch (Difficult) {
		case 0: // Easy
			Diff_Score = 1;
			Pop_Speed = 2000;
			break;

		case 1: // Normal
			Diff_Score = 2;
			Pop_Speed = 1000;
			break;

		case 2: // Hard
			Diff_Score = 4;
			Pop_Speed = 350;
			break;

		default:
			System.out.println("Switch fail");
		} // set Difficulty

		for (int i = 0; i < 9; i++) {
			Digda[i] = new JButton(Digda_Hole); // 두더지 들어간 상태 == 0, 나온 상태 == 1
			Digda[i].addActionListener(new Digda_Event());
			Digda[i].setBorderPainted(false);
			Digda[i].setFocusPainted(false);
			Digda[i].setContentAreaFilled(false);
			Game_Tabel.add(Digda[i]);
		}

		Thread GCT = new Thread(new Game_Controller_Thread());
		Thread GRCT = new Thread(new Game_RealTime_Controller_Thread());

		GCT.start();
		GRCT.start();

		panel.add(Status_Tabel);
		panel.add(Game_Tabel);
		add(panel);
		setVisible(true);

	}

}