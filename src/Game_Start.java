import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Game_Start Section ������ ���� ���̵��� �����ϴ� Frame�� �����Ѵ�.
 * ��ư Ŭ�� => (event) ���̵� ���� ���� => â ��ȯ(dispose)
 */
public class Game_Start extends JFrame {
	/*
	 * Easy == 0 Normal == 1 Hard == 2
	 */

	public Game_Start() {
		setTitle("select Difficulty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);
		setResizable(false); // frame size fixed

		JPanel diff = new JPanel();
		diff.setLayout(new GridLayout(4, 1, 15, 15));

		JButton Easy = new JButton("Easy");
		Easy.setContentAreaFilled(false);

		JButton Normal = new JButton("Normal");
		Normal.setContentAreaFilled(false);

		JButton Hard = new JButton("Hard");
		Hard.setContentAreaFilled(false);

		JButton prev = new JButton("<< Previous");
		prev.setContentAreaFilled(false);

		/* �̺�Ʈ ������ */
		Easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Play play = new Play(0);
			}
		});

		Normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Play play = new Play(1);
			}
		});

		Hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Play play = new Play(2);
			}
		});

		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// goto previous frame ;)
				dispose();
				new Title();
			}
		});

		diff.add(Easy);
		diff.add(Normal);
		diff.add(Hard);
		diff.add(prev);
		add(diff);
		setVisible(true);
	}
}