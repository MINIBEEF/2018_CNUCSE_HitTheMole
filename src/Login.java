import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//로그인 화면
public class Login extends JFrame {
	public ArrayList<String> id = new ArrayList<>();
	public ArrayList<String> pw = new ArrayList<>();

	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setLocationRelativeTo(null);

		JPanel Loginpanel = new JPanel();
		Loginpanel.setLayout(null);

		JLabel IDLabel = new JLabel("ID:");
		IDLabel.setBounds(10, 10, 80, 25);
		JTextField IDtext = new JTextField(20);
		IDtext.setBounds(100, 10, 160, 25);
		JLabel PWLabel = new JLabel("Password:");
		PWLabel.setBounds(10, 40, 80, 25);
		JPasswordField PWtext = new JPasswordField(20);
		PWtext.setBounds(100, 40, 160, 25);
		JButton LoginButton = new JButton("로그인");
		LoginButton.setBounds(30, 80, 90, 25);
		LoginButton.setContentAreaFilled(false);
		
		JButton SignButton = new JButton("회원가입");
		SignButton.setBounds(171, 80, 90, 25);
		SignButton.setContentAreaFilled(false);

		/* 로그인 창의 이벤트 구현부 */

		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader rId = null, rPw = null;
				String str;
				try {
					rId = new BufferedReader(new FileReader("Read_ID.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rPw = new BufferedReader(new FileReader("Read_PW.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					while ((str = rId.readLine()) != null) {
						id.add(str);
						pw.add(rPw.readLine());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (id.contains(IDtext.getText()) && pw.contains(PWtext.getText())) {
					JOptionPane.showMessageDialog(null, "Login success");
					new Title(); // 로그인을 성공하면 창이 바뀜.
					dispose();
				} else // 로그인이 실패하면 창은 바뀌지 않음.
					JOptionPane.showMessageDialog(null, "Login false");
			}
		});

		SignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp(); // 회원가입창으로 전환.
			}
		});

		Loginpanel.add(IDLabel);
		Loginpanel.add(IDtext);
		Loginpanel.add(PWLabel);
		Loginpanel.add(PWtext);
		Loginpanel.add(LoginButton);
		Loginpanel.add(SignButton);

		add(Loginpanel);
		setVisible(true);
	}

	public static void main(String args[]) {
		new Login();
	}

}

// 회원가입 화면
class SignUp extends JFrame {

	ArrayList<String> id = new ArrayList<>();
	ArrayList<String> pw = new ArrayList<>();

	public SignUp() {
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		JLabel IDLabel = new JLabel("ID:");
		JLabel PWLabel = new JLabel("Password:");
		
		IDB.setContentAreaFilled(false);
		SignButton.setContentAreaFilled(false);
		JPanel Sign_panel = new JPanel();
		Sign_panel.setLayout(new BoxLayout(Sign_panel, BoxLayout.Y_AXIS));

		JPanel IDPanel = new JPanel();
		IDPanel.setLayout(null);
		IDLabel.setBounds(10, 10, 80, 25);
		IDText.setBounds(100, 10, 160, 25);
		IDB.setBounds(270, 10, 100, 25);
		IDPanel.add(IDLabel);
		IDPanel.add(IDText);
		IDPanel.add(IDB);

		JPanel PWPanel = new JPanel();
		PWPanel.setLayout(null);
		PWLabel.setBounds(10, 10, 80, 25);
		PWText.setBounds(100, 10, 160, 25);
		SignButton.setBounds(270, 10, 100, 25);
		PWPanel.add(PWLabel);
		PWPanel.add(PWText);
		PWPanel.add(SignButton);

		JPanel backPanel = new JPanel();
		backPanel.setLayout(null);
		JButton backButton = new JButton("<< Previous");
		backButton.setContentAreaFilled(false);
		backButton.setBounds(150, 10, 103, 25);
		backPanel.add(backButton);

		/* 회원가입 창의 이벤트 구현부 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();// 뒤로가기 버튼을 누르면 로그인 창으로 전환.
			}
		});
		// 아이디 중복 체크
		IDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader RID = null;
				String check = IDText.getText();
				String str;
				try {
					RID = new BufferedReader(new FileReader("Read_ID.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					while ((str = RID.readLine()) != null) {
						id.add(str);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					RID.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (!id.contains(check))
					JOptionPane.showMessageDialog(null, "Available");
				else
					JOptionPane.showMessageDialog(null, "OverLap");
			}
		});
		// 회원 등록
		SignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter wid = null, wpw = null;
				id.add(IDText.getText());
				pw.add(PWText.getText());
//				if (!id.contains(IDText.getText())) {
				try {
					wid = new PrintWriter(new FileWriter("Read_ID.txt"));
					wpw = new PrintWriter(new FileWriter("Read_PW.txt"));

					for (int i = 0; i < id.size(); i++) {
						wid.println(id.get(i));
						wpw.println(pw.get(i));
					}
					JOptionPane.showMessageDialog(null, "Sign up success");
					dispose();
					new Login();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Sign false");
				}
				wid.close();
				wpw.close();

//				} else
//					JOptionPane.showMessageDialog(null, "Please check ID OverLap first");

			}
		});

		Sign_panel.add(IDPanel);
		Sign_panel.add(PWPanel);
		Sign_panel.add(backPanel);

		add(Sign_panel);
		setVisible(true);
	}

	JTextField IDText = new JTextField(20);
	JButton IDB = new JButton("중복 확인");

	JTextField PWText = new JTextField(20);
	JButton SignButton = new JButton("회원 등록");

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == SignButton) {
//			try {
//				doSignUp();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void actionPerformed1(ActionEvent e) {
//		if (e.getSource() == IDB) {
//			try {
//				doIdCheck();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void doIdCheck() throws IOException {
//		BufferedReader rid = null;
//		String str;
//
//		rid = new BufferedReader(new FileReader("Read_ID.txt"));
//
//		while ((str = rid.readLine()) != null) {
//			id.add(str);
//		}
//
//		if (!id.equals(IDText.getText()))
//			JOptionPane.showMessageDialog(null, "Available");
//		else
//			JOptionPane.showMessageDialog(null, "OverLap");
//	}
//
//	public void doSignUp() throws IOException {
//		FileWriter wid = null;
//		FileWriter wpw = null;
//
//		try {
//			wid = new FileWriter("Read_ID.txt", true);
//			wpw = new FileWriter("Read_PW.txt", true);
//			wid.write(IDText.getText() + "\r\n");
//			wpw.write(PWText.getText() + "\r\b");
//		} finally {
//			if (wid != null)
//				wid.close();
//			if (wpw != null)
//				wpw.close();
//			else
//				JOptionPane.showMessageDialog(null, "Sign up false");
//			JOptionPane.showMessageDialog(null, "Sign up success");
//			dispose();
//			new Login();
//
//		}
//
//	}

}