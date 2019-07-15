import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Ranking extends JFrame {
	JLabel[] Easy_Score = new JLabel[255];
	JLabel[] Normal_Score = new JLabel[255];
	JLabel[] Hard_Score = new JLabel[255];

	ArrayList<String> easy = new ArrayList<>();
	ArrayList<String> normal = new ArrayList<>();
	ArrayList<String> hard = new ArrayList<>();

	public Ranking() {
		setTitle("Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setLayout(new GridLayout(4, 1, 15, 15));

		JPanel RankPanel = new JPanel();
		JButton Easy_Ranking = new JButton("Easy Ranking");
		JButton Normal_Ranking = new JButton("Normal Ranking");
		JButton Hard_Ranking = new JButton("Hard Ranking");

		Easy_Ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Easy_Ranking();
			}
		});

		Normal_Ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Normal_Ranking();
			}
		});

		Hard_Ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Hard_Ranking();
			}
		});

		Easy_Ranking.setContentAreaFilled(false);
		Normal_Ranking.setContentAreaFilled(false);
		Hard_Ranking.setContentAreaFilled(false);
		add(Easy_Ranking);
		add(Normal_Ranking);
		add(Hard_Ranking);

		JButton prev = new JButton("<< Previous");
		prev.setBounds(90, 330, 103, 25);
		prev.setContentAreaFilled(false);

		setLocationRelativeTo(null);
		setResizable(false); // frame size fixed
		setVisible(true);

		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// goto previous frame ;)
				dispose();
				new Title();
			}
		});
		RankPanel.add(prev);
		add(RankPanel);
		setVisible(true);
		
	}

	class Easy_Ranking extends JFrame {
		public Easy_Ranking() {
			setTitle("Easy Ranking");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(300, 400);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(4, 1, 15, 15));
			rankLoad_e();

			for (int i = 0; i < easy.size(); i++) {
				Easy_Score[i] = new JLabel((i + 1) + "번째 : " + easy.get(i) + " 점");
				add(Easy_Score[i]);
			}
			setVisible(true);
		}

		public void rankLoad_e() {
			try {
				File fe = new File("ranking_easy.txt");
				FileReader filereader = new FileReader(fe);
				BufferedReader bufReader = new BufferedReader(filereader);
				String line = null;
				while ((line = bufReader.readLine()) != null) {
					easy.add(line);
				}
				bufReader.close();
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	class Normal_Ranking extends JFrame {
		public Normal_Ranking() {
			setTitle("Normal Ranking");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(300, 400);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(4, 1, 15, 15));
			rankLoad_n();

			for (int i = 0; i < normal.size(); i++) {
				Normal_Score[i] = new JLabel((i + 1) + "번째 : " + normal.get(i) + " 점"); //
				add(Normal_Score[i]);
			}
			setVisible(true);
		}

		public void rankLoad_n() {
			try {
				File fn = new File("ranking_normal.txt");
				FileReader filereader2 = new FileReader(fn);
				BufferedReader bufReader2 = new BufferedReader(filereader2);
				String line2 = null;
				while ((line2 = bufReader2.readLine()) != null) {
					easy.add(line2);
				}
				bufReader2.close();
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Hard_Ranking extends JFrame {
		public Hard_Ranking() {
			setTitle("Hard Ranking");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(300, 400);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(4, 1, 15, 15));
			rankLoad_h();

			for (int i = 0; i < hard.size(); i++) {
				Hard_Score[i] = new JLabel((i + 1) + "번째 : " + hard.get(i) + " 점"); //
				add(Hard_Score[i]);
			}
			setVisible(true);
		}

		public void rankLoad_h() {
			try {
				File fh = new File("ranking_easy.txt");
				FileReader filereader3 = new FileReader(fh);
				BufferedReader bufReader3 = new BufferedReader(filereader3);
				String line3 = null;
				while ((line3 = bufReader3.readLine()) != null) {
					easy.add(line3);
				}
				bufReader3.close();
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}