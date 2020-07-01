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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Main extends Frame implements ActionListener,ItemListener, Runnable {

	DTO dto = new DTO();// DTO 객체 생성
	
	String MODE="DEFAULT";
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	
	//for login Frame.
	TextField logIn_id; // 아이디
	JPasswordField logIn_pw; // 비밀번호
	static String receivedPW; // 비밀번호
	static String receivedID=null; // 중복체크
	JButton logIn; // 전송버튼
	JButton join; // 전송버튼
	
	//for join frame.
	JFrame infopage;
	boolean check;
	boolean sameCheck=false;
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
	JButton sameId;
	
	
	
	//after login; 일반사용자 
	boolean rcheck=false;
	TextField rname; //이름 
	JRadioButton rr1;//성별m    
	JRadioButton rr2; //성별w
	JPasswordField rpw; // 비밀번호 : 조건 설정
	JPasswordField rpw2; // 비밀번호  확인
	
	JComboBox rphone1;
	String []rphonelist= {"010","016","031"};
	
	Choice rphone; // 번호 1
	TextField rphone2; //번호2
	TextField rphone3; // 번호3

	JButton revise; //수정
	JButton logout; //로그아웃
	JButton quit; //탈퇴
	
	static String tname;
	static String tsex;
	static String tp;
	String tp1;
	String tp2;
	String tp3;
	
	
	
	//탈퇴
	JPasswordField qpw; // 비밀번호 : 조건 설정
	JButton qquit; //탈퇴
	
	//정보수정
	
	//after login; 관리자
	JFrame adminpage;
	//전체 사용자보기
	String header[]= {"name", "sex", "id", "password", "phone"};
	static String[][] content=new String[100][100];
	JTable table;
	
	JLabel txt;
	JButton add, del,adlogout;
	TextField aname, asex, aid, apassword, aphone;
	int index;

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
		logIn.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
					if(e.getSource()==logIn) {
						MODE="logIn";
						String ID=logIn_id.getText();
						String PW=new String(logIn_pw.getPassword());
						try {
							searchDAO.searchpw(ID);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(PW.equals(receivedPW)) {
							//로그인 된 화면 출력
							if(!ID.equals("admin")) {
								JOptionPane.showMessageDialog(null, "로그인되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
								infoPanel(ID);
							}else {
								adminPanel();
							}
								
								
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
							System.out.println("로그인 실패");
						}
						receivedPW=null;
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
		
		sameId=new JButton("중복체크");
		sameId.setBounds(330,170,100,50);
		
		ButtonGroup bg=new ButtonGroup();    
		r1=new JRadioButton("남");
		r1.setText("MALE");
		r1.setBounds(120,110,100,50);
		r2=new JRadioButton("여");
		r2.setBounds(230,110,100,50);
		r2.setText("FEMALE");
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
		p2.add(sameId);
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
		sameId.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
					if(e.getSource()==sameId) {
						String ID=id.getText();
						sameCheck=false;
						System.out.println(ID);
						try {
							receivedID=null;
							searchDAO.search(ID);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(receivedID!=null) {
							//로그인 된 화면 출력
							System.out.println("중복");
							JOptionPane.showMessageDialog(null, "중복 ID", "Warning", JOptionPane.INFORMATION_MESSAGE);
							sameCheck=true;
						}else {
							//로그인 된 화면 출력
							System.out.println("사용가능");
							JOptionPane.showMessageDialog(null, "사용 가능한 ID", "", JOptionPane.INFORMATION_MESSAGE);	
							sameCheck=false;
						}
					}
				}	
			});
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
						if(check==true&&sameCheck==false) {
							System.out.println("true");
							insertDAO.create(dto); // dto를 DAO에 넘겨준다.
							JOptionPane.showMessageDialog(null, "가입되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);	
							System.out.println("true");
							check=false;
							sameCheck=true;
							receivedID=null;
						}else if(check==false){
							JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}else if(sameCheck==true){
							JOptionPane.showMessageDialog(null, "ID를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(null, "ID를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					
				}
			}	
		});
	}
	public void quitPanel(String info) {
		JFrame quitpage = new JFrame();
		JPanel p4= new JPanel();
		
		quitpage.setLocation(0, 0); // 프레임 시작위치
		quitpage.setVisible(true); // 실제로 프레임을 화면에 출력
		quitpage.setSize(480, 360); // 프레임의 처음 크기
		quitpage.setResizable(true); // 프레임 사이즈 조절
		
		JLabel qId = new JLabel("ID");
		qId.setBounds(50, 50, 50,50);
		JLabel q2Id = new JLabel(info);
		q2Id.setBounds(120, 50, 200,50);
		q2Id.setText(info);
		
		JLabel qin = new JLabel("비밀번호를 입력하세요");
		qin.setBounds(50, 110, 250, 50);
		
		JLabel qName = new JLabel("PW");
		qName.setBounds(50, 170, 500, 50);

		qpw = new JPasswordField(20);
		qpw.setBounds(50,170,220,50);
	
		qquit=new JButton("탈퇴하기");
		qquit.setBounds(50,230,250,50);
		
		p4.setLayout(null);
		p4.add(qId);
		p4.add(q2Id);
		p4.add(qin);
		p4.add(qName);
		p4.add(qpw);
		p4.add(qquit);
		quitpage.add(p4);
		
		qquit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==qquit) {
					String ID=info;
					String PW=new String(qpw.getPassword());
					try {
						searchDAO.searchpw(ID);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(PW.equals(receivedPW)) {
						//로그인 된 화면 출력
						try {
							deleteDAO.delete(ID);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "탈퇴되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
						quitpage.setVisible(false);
						infopage.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						System.out.println("탈퇴 실패");
					}
					receivedPW=null;
					
				}
			}	
		});
		
		
	}
	
	public void infoPanel(String info) {
		infopage = new JFrame();
		infopage.setLocation(0, 0); // 프레임 시작위치
		infopage.setVisible(true); // 실제로 프레임을 화면에 출력
		infopage.setSize(480, 720); // 프레임의 처음 크기
		infopage.setResizable(true); // 프레임 사이즈 조절
		
		JPanel p3 = new JPanel();
		
		setLayout(new BorderLayout());
		
		try {
			searchDAO.search(receivedID);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel Id = new JLabel("ID");
		Id.setBounds(50, 50, 50,50);
		JLabel rId = new JLabel(info);
		rId.setBounds(120, 50, 200,50);
		rId.setText(info);
		System.out.println(info);
		JLabel Name = new JLabel("이름");
		Name.setBounds(50, 110, 50, 50);
		JLabel Sex = new JLabel("성별");
		Sex.setBounds(50, 170, 50,50);
		JLabel Pw1 = new JLabel("PW ");
		Pw1.setBounds(50, 230, 50,50);
		JLabel Pw2 = new JLabel("PW check");
		Pw2.setBounds(50, 290, 50,50);
		JLabel Phone = new JLabel("전화번호");
		Phone.setBounds(50, 350, 50,50);
		
		quit=new JButton("탈퇴");
		quit.setBounds(330,50,100,50);
		
		rname=new TextField(20);
		rname.setBounds(120,110,200,50);
		rname.setText(tname);
		
		
		ButtonGroup bg=new ButtonGroup();    
		rr1=new JRadioButton("남");
		rr1.setText("MALE");
		rr1.setBounds(120,170,100,50);
		rr2=new JRadioButton("여");
		rr2.setBounds(230,170,100,50);
		rr2.setText("FEMALE");
		bg.add(rr1);
		bg.add(rr2);  
		if(tsex.equals("MALE")) {
			rr1.setSelected(true);
		}else if (tsex.equals("FEMALE")) {
			rr2.setSelected(true);
		}	
		String[] pp = tp.split("-");
		tp1=pp[0];
		tp2=pp[1];
		tp3=pp[2];
		
		pw = new JPasswordField(20);
		pw.setBounds(120,230,200,50);

		pw2 = new JPasswordField(20);
		pw2.setBounds(120,290,200,50);
		
		phone1=new JComboBox(phonelist);
		phone1.setBounds(120,350,70,50);
		for(int i=0;i<phonelist.length;i++) {
			if(tp1.equals(phonelist[i])) {
				phone1.setSelectedIndex(i);
			}
		}
		
		phone2=new TextField(5);
		phone2.setBounds(200,350,70,50);
		phone2.setText(tp2);
		
		phone3=new TextField(5);
		phone3.setBounds(290,350,70,50);
		phone3.setText(tp3);
		
		revise=new JButton("revise");
		revise.setBounds(30,450,150,50);
		
		logout=new JButton("logout");
		logout.setBounds(200,450,150,50);
		
		
		p3.setLayout(null);
		p3.add(Id);
		p3.add(rId);
		p3.add(quit);
		p3.add(Name);
		p3.add(rname);
		p3.add(Sex);
		p3.add(rr1);
		p3.add(rr2);
		p3.add(Pw1);
		p3.add(Pw2);
		p3.add(pw);
		p3.add(pw2);
		p3.add(Phone);
		p3.add(phone1);
		p3.add(phone2);
		p3.add(phone3);
		p3.add(revise);
		p3.add(logout);
		
		infopage.add(p3);
		
		infopage.setVisible(true);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==quit) {
					System.out.println(info);
					quitPanel(info);
				}
			}	
		});
		logout.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==logout) {
					infopage.setVisible(false);
					
					
				}
			}	
		});
		revise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==revise) {
					String temp= null;
					String b=null;
					String p1,p2;
					System.out.println("HI");
					rcheck=false;
					dto.setName(rname.getText());
					if(rr1.isSelected()) {
						b="MALE";
					}else {
						b="FEMALE";
					}
					dto.setS(b);
					dto.setId(info); 
					dto.setPassword(pw.getPassword()); // 입력된 비밀번호를 가져와 dto에 저장
					p1=new String (pw.getPassword());
					p2=new String (pw2.getPassword());
					System.out.println(p1);
					System.out.println(p2);
					if(p1.equals(p2)) {
						rcheck=true;
					}
					temp=phonelist[phone1.getSelectedIndex()]+"-"+phone2.getText()+"-"+phone3.getText();
					dto.setPhone(temp);
					//dto.setInfo(in_info.getText()); // 입력된 자기소개를 가져와 dto에 저장
					//dto.setJob(list.getSelectedItem()); // 입력된 직업을 가져와 dto에 저장
					try {
						if(rcheck==true) {
							System.out.println("true");
							updateDAO.update(dto); // dto를 DAO에 넘겨준다
							JOptionPane.showMessageDialog(null, "정보가 변경되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
							rcheck=false;
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					
				}
			}	
		});
	}
	public void adminPanel() {
		setLayout(new BorderLayout());
		adminpage = new JFrame();
		adminpage.setLocation(0, 0); // 프레임 시작위치
		adminpage.setVisible(true); // 실제로 프레임을 화면에 출력
		adminpage.setSize(480, 720); // 프레임의 처음 크기
		adminpage.setResizable(true); // 프레임 사이즈 조절
		adminpage.setLayout(null);
		
	
		JPanel pp1 = new JPanel();
		pp1.setBounds(0,0,480,250);
		pp1.setLayout(null);
		JPanel t= new JPanel();
		t.setBounds(0,250,480,470);

		
		txt = new JLabel("관리자모드");
		txt.setText("admin page");
		txt.setBounds(30, 0, 460, 50);
		pp1.add(txt);
		
		aname = new TextField("");
		aname.setBounds(30, 50, 200, 30);
		aname.setText("name");
		pp1.add(aname);
		asex = new TextField("");
		asex.setBounds(30, 90, 200, 30);
		asex.setText("sex");
		pp1.add(asex);
		aid = new TextField("");
		aid.setBounds(30, 130, 200, 30);
		aid.setText("id");
		pp1.add(aid);
		apassword = new TextField("");
		apassword.setBounds(30, 170, 200, 30);
		apassword.setText("password");
		pp1.add(apassword);
		aphone = new TextField("");
		aphone.setBounds(30, 210, 200, 30);
		aphone.setText("phone");
		pp1.add(aphone);

		add = new JButton("추가");
		add.setText("추가");
		add.setBounds(250, 50, 100, 30);
		pp1.add(add);

		del = new JButton("삭제");
		del.setText("삭제");
		del.setBounds(250, 90, 100, 30);
		pp1.add(del);
		
		adlogout = new JButton("logout");
		adlogout.setText("logout");
		adlogout.setBounds(250, 130, 100, 30);
		pp1.add(adlogout);
	

		try {
			searchDAO.searchAll();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		table=new JTable(content, header);
		JScrollPane scrollpane = new JScrollPane(table);
		t.add(scrollpane);
		
		adminpage.add(pp1);
		adminpage.add(t);
		adminpage.setVisible(true);
		
		adlogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==adlogout) {
					adminpage.setVisible(false);
				}
			}	
		});

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==add) {
					boolean tf=false;
					String dname= aname.getText();
					String dsex= asex.getText();
					String did= aid.getText();
					String dpassword= apassword.getText();
					String dphone= aphone.getText();
					if(dname.equals("name")) {
						JOptionPane.showMessageDialog(null, "이름을 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}else if(dsex.equals("asex")) {
						JOptionPane.showMessageDialog(null, "성별을 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}else if(did.equals("aid")) {
						JOptionPane.showMessageDialog(null, "아이디를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}else if(dpassword.equals("apassword")) {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}else if(dphone.equals("aphone")) {
						JOptionPane.showMessageDialog(null, "번호를 확인하세요", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}else {
						tf=true;
					}
					
					if(tf==true) {
						index = table.getSelectedRow();
						content[index][0]=dname;
						content[index][1]=dsex;
						content[index][2]=did;
						content[index][3]=dpassword;
						content[index][4]=dphone;						
						dto.setName(dname);
						dto.setS(dsex);
						dto.setId(did);
						char[] arr= dpassword.toCharArray();
						dto.setPassword(arr);
						dto.setPhone(dphone);
						t.remove(scrollpane);
						try {
							insertDAO.create(dto);
						}catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
						try {
							searchDAO.searchAll();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						t.add(scrollpane);
					}
					
					
				}
			}	
		});
		
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==del) {
					try {
						index = table.getSelectedRow();
						System.out.println(index);
						t.remove(scrollpane);
						deleteDAO.delete(content[index][2]);

						try {
							searchDAO.searchAll();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						searchDAO.searchAll();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t.add(scrollpane);
					
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
