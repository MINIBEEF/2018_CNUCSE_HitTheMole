import java.awt.Graphics;
import java.awt.GridLayout;
/* Term Project 주제 - 두저지 잡기
 * Scene 구성 - Title, 난이도 선택, 게임화면, 게임방법, 랭킹 
 * 
 * TODO 김기훈 - Thread를 이용한 실시간 난수 발생, 버튼에 적용 및 버튼 눌렀을 때 액션 구현
 * TODO 강성엽 - Game_Start Frame(Class)에서 Login기능 구현 및 관리, 전체적인 이미지 입히기 작업
 * TODO 박민 - GUI 구현, 서버/네트워크 단에서 서비스 구현, 데이터베이스 구현(유저 정보, 랭킹의 실시간 동기화)
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*   Main Section 에서는 프로그램을 실행했을 때에 초기화면과 전체적인 환경을 관할
 *   버튼을 눌렀을 때 화면 전환도 여기서 구현
 */
class Title extends JFrame {
	public Title() {
		/* Title Frame 환경설정 */
		setTitle("Title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		/* Title의 컴포넌트 */
		JPanel Title_Panel = new JPanel();
		Title_Panel.setLayout(new GridLayout(4, 1, 15, 15)); // array to vertical

		JButton Game_Start = new JButton("Game Start");
		Game_Start.setContentAreaFilled(false);
		
		JButton How_To_Play = new JButton("How To Play?");
		How_To_Play.setContentAreaFilled(false);
		
		JButton Ranking = new JButton("Ranking");
		Ranking.setContentAreaFilled(false);
		
		JButton Game_EXIT = new JButton("Game EXIT");
		Game_EXIT.setContentAreaFilled(false);
		
		/* 이벤트 구현부 */
		Game_Start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Game_Start Frame pop
				dispose();
				new Game_Start();
			}
		});

		How_To_Play.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				// How_To_Play Frame pop
				dispose();
				new How_To_Play();
			}
		});

		Ranking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ranking Frame pop
				dispose();
				new Ranking();
			}
		});

		Game_EXIT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // Program dispose
			}
		});

		Title_Panel.add(Game_Start);
		Title_Panel.add(How_To_Play);
		Title_Panel.add(Ranking);
		Title_Panel.add(Game_EXIT);

		this.add(Title_Panel);

		setVisible(true);
	}

}