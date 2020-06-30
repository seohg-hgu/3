package project3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Main extends Frame implements ActionListener, ItemListener, Runnable {

	DTO dto = new DTO();// DTO 객체 생성
	
	String MODE="DEFAULT";
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	
	//for login Frame.
	TextField logIn_id; // 아이디
	JPasswordField logIn_pw; // 비밀번호
	JButton logIn; // 전송버튼
	JButton join; // 전송버튼
	
	//for join frame.
	boolean check;
	TextField name; //이름 
	TextField id; // 아이디: 중복확인
	JPasswordField pw; // 비밀번호 : 조건 설정
	JPasswordField pw2; // 비밀번호  확인
	
	JRadioButton r1;    
	JRadioButton r2;
	
	
	JComboBox phone1;
	String []phonelist= {"010","016","031"};
	
	Choice phone; // 번호 1
	TextField phone2; //번호2
	TextField phone3; // 번호3

	JButton submit;
	JButton back;
	
	
	
	//after login; 일반사용자 
	//탈퇴
	//정보수정
	
	//after login; 관리자
	//전체 사용자보기
	//강제 탈퇴
	TextArea in_info; // 자기소개
	
	//개인정보 수정 
	
	//delete Account : 비밀번호 입력받아 확인 후 삭제
	
	

	//String job = ""; // 직업

	public Main(String str) {
		super(str);
		init();
		start();
		this.setLocation(0, 0); // 프레임 시작위치
		super.setVisible(true); // 실제로 프레임을 화면에 출력
		super.setSize(480, 720); // 프레임의 처음 크기
		super.setResizable(true); // 프레임 사이즈 조절
		
		this.add(p1);
		
		p1.setVisible(true);
		//p2.setVisible(true);
	}

	
	public void init() {
		setLayout(new BorderLayout());

		Label id = new Label("ID");
		id.setSize(100,20);
		Label password = new Label("PW");
		password.setSize(100,20);
		
		logIn = new JButton("Login");
		logIn.addActionListener(this); // 버튼이벤트
		
		join = new JButton("Join");
		join.addActionListener(this); // 버튼이벤트

		logIn_id = new TextField(20);
		logIn_pw = new JPasswordField(20);

		GridLayout g = new GridLayout(6, 2);
		p1.setLayout(g);
		p1.add(id);
		p1.add(logIn_id);
		p1.add(password);
		p1.add(logIn_pw);
		p1.add(logIn);
		p1.add(join);
		this.add(p1);
		
		join.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==join) {
					MODE="JOIN";
					joinpanel();
					
					
				}
			}	
		});
	}

	public void start() {

		this.addWindowListener(new WindowAdapter() { // x를 눌렀을때 종료

			public void windowClosing(WindowEvent e) {

				System.exit(0);

			}

		});

	}
	
	public void joinpanel() {
		JFrame joinpage = new JFrame();
		joinpage.setLocation(0, 0); // 프레임 시작위치
		joinpage.setVisible(true); // 실제로 프레임을 화면에 출력
		joinpage.setSize(480, 720); // 프레임의 처음 크기
		joinpage.setResizable(true); // 프레임 사이즈 조절
		
		JPanel p2 = new JPanel();
		
		setLayout(new BorderLayout());
		
		JLabel Name = new JLabel("이름");
		Name.setBounds(50, 50, 50, 50);
		JLabel Sex = new JLabel("성별");
		Sex.setBounds(50, 110, 50,50);
		JLabel Id = new JLabel("ID");
		Id.setBounds(50, 170, 50,50);
		JLabel Pw1 = new JLabel("PW ");
		Pw1.setBounds(50, 230, 50,50);
		JLabel Pw2 = new JLabel("PdW check");
		Pw2.setBounds(50, 290, 50,50);
		JLabel Phone = new JLabel("전화번호");
		Phone.setBounds(50, 350, 50,50);
		
		
		name=new TextField(20);
		name.setBounds(120,50,200,50);
		
		ButtonGroup bg=new ButtonGroup();    
		r1=new JRadioButton("남");
		r1.setText("M");
		r1.setBounds(120,110,100,50);
		r2=new JRadioButton("여");
		r2.setBounds(230,110,100,50);
		r2.setText("W");
		bg.add(r1);
		bg.add(r2);  
		
		
		
		id = new TextField(20);
		id.setBounds(120,170,200,50);
		
		pw = new JPasswordField(20);
		pw.setBounds(120,230,200,50);

		pw2 = new JPasswordField(20);
		pw2.setBounds(120,290,200,50);
		
		phone1=new JComboBox(phonelist);
		phone1.setBounds(120,350,70,50);
		
		phone2=new TextField(5);
		phone2.setBounds(200,350,70,50);
		
		phone3=new TextField(5);
		phone3.setBounds(290,350,70,50);
		
		submit=new JButton("submit");
		submit.setBounds(30,450,150,50);
		
		back=new JButton("back");
		back.setBounds(200,450,150,50);
		p2.setLayout(null);
		p2.add(Name);
		p2.add(name);
		p2.add(Sex);
		p2.add(r1);
		p2.add(r2);
		p2.add(Id);
		p2.add(id);
		p2.add(Pw1);
		p2.add(pw);
		p2.add(Pw2);
		p2.add(pw2);
		p2.add(Phone);
		p2.add(phone1);
		p2.add(phone2);
		p2.add(phone3);
		p2.add(submit);
		p2.add(back);
		//p2.add(name);
		//p1.add(Pw2);
		//p1.add(logIn_pw);
		//p1.add(logIn);
		//p1.add(join);
		joinpage.add(p2);
		
		//add("Center", p);
		//add("South", logIn);
		//add("South", join);
		joinpage.setVisible(true);
		back.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==back) {
					joinpage.setVisible(false);
					
					
				}
			}	
		});
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==submit) {
					String temp= null;
					String b=null;
					String p1,p2;
					System.out.println("HI");
					check=false;
					dto.setName(name.getText());
					if(r1.isSelected()) {
						b="MALE";
					}else {
						b="FEMALE";
					}
					dto.setS(b);
					dto.setId(id.getText()); // 입력된 아이디를 가져와 dto에 저장
					dto.setPassword(pw.getPassword()); // 입력된 비밀번호를 가져와 dto에 저장
					p1=new String (pw.getPassword());
					p2=new String (pw2.getPassword());
					System.out.println(p1);
					System.out.println(p2);
					if(p1.equals(p2)) {
						check=true;
					}
					temp=phonelist[phone1.getSelectedIndex()]+"-"+phone2.getText()+"-"+phone3.getText();
					dto.setPhone(temp);
					//dto.setInfo(in_info.getText()); // 입력된 자기소개를 가져와 dto에 저장
					//dto.setJob(list.getSelectedItem()); // 입력된 직업을 가져와 dto에 저장
					try {
						if(check==true) {
							System.out.println("true");
							insertDAO.create(dto); // dto를 DAO에 넘겨준다.
							System.out.println("true");
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
						check=false;
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					
				}
			}	
		});
	}

	public static void main(String[] args) {

		Main exam = new Main("기본프레임");

	}

	public void run() { // 스레드 정의부분

	}

	public void itemStateChanged(ItemEvent e) { // 체크상태 확인

	}

	public void actionPerformed(ActionEvent e) { // 액션이벤트

		Object obj = e.getSource();



		if ((JButton) obj == logIn) {
/*
			dto.setId(logIn_id.getText()); // 입력된 아이디를 가져와 dto에 저장
			dto.setPassword(logIn_pw.getPassword()); // 입력된 비밀번호를 가져와 dto에 저장

			//dto.setInfo(in_info.getText()); // 입력된 자기소개를 가져와 dto에 저장
			//dto.setJob(list.getSelectedItem()); // 입력된 직업을 가져와 dto에 저장
			try {
				insertDAO.create(dto); // dto를 DAO에 넘겨준다.
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
*/
		}if ((JButton) obj == submit) {
			

		}

	}

}
