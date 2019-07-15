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
 * MEMO - �ڹ�
 * 
 * ���� ������(Thread) ���� ���� �߻� => �� ���� %9 => �ش� ��ư 1�� ����
 * 
 * Event ó�� => ��ư ������ �� ��ư�� (getName() == 1)�̸� score++
 * 
 * -���� ����Ŭ������ �ۼ��ؾ��� (���ټ�)
 */

public class Play extends JFrame {
	public int Difficult;
	public int Diff_Score;
	public int Pop_Speed;

	ImageIcon Icondudeoji = new ImageIcon("�δ���.png");
	Image imgdudeoji = Icondudeoji.getImage();
	Image chdudeoji = imgdudeoji.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Alive = new ImageIcon(chdudeoji);

	ImageIcon Icongumeong = new ImageIcon("�δ�������.PNG");
	Image imggumeong = Icongumeong.getImage();
	Image chgumeong = imggumeong.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Hole = new ImageIcon(chgumeong);

	ImageIcon Icondie = new ImageIcon("�δ�������.PNG");
	Image imgdie = Icondie.getImage();
	Image chdie = imgdie.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	ImageIcon Digda_Dead = new ImageIcon(chdie);

	BufferedImage img = null;
	Random rand = new Random();
	JButton[] Digda = new JButton[9]; // �δ����� �� ��ư��, Thread���� �����ϱ� ���� ����� ����
	private int Popup = 0;
	private int score = 0;
	private long RealTime_Clock_Start = System.currentTimeMillis(); // ���� ���� �ð�
	private long Clock = 0;

	boolean Thread_Dead = false;

	JLabel View_ScoreBoard = new JLabel(); // TODO Thread, ȭ�鿡 ǥ�õǴ� ���ھ��
	JLabel View_RealTime_Clock = new JLabel(); // TODO Thread, ȭ�鿡 ǥ�õǴ� ���� �ð�

	class Game_RealTime_Controller_Thread implements Runnable { // �ֱ������� ȭ�鿡 ��µǴ� score�� ����ð�(RealTime_Clock)�� ����
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
			// �۾��� TODO �ڹ�
			while (true) {
				Clock = (System.currentTimeMillis() - RealTime_Clock_Start) / 1000;

				View_RealTime_Clock.setText("Time ==> " + Long.toString(Clock));
				View_ScoreBoard.setText("SC0re ==> " + Integer.toString(score));
				/* Ÿ�Ӿƿ� */
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
				/* �δ��� ������ �� */
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

	class Digda_Event implements ActionListener { // �δ��� ������ ���ھ�++
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
			Digda[i] = new JButton(Digda_Hole); // �δ��� �� ���� == 0, ���� ���� == 1
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