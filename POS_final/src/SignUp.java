import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUp extends JFrame implements ActionListener {
	
	JFrame f = new JFrame();
	
	Dimension dimen, dimen1;
	
	JButton btnSignUp = new JButton("회원가입");
	JButton btnBack = new JButton("돌아가기");
	JButton btnIdChk = new JButton("중복확인");
	
	JLabel jbc = new JLabel();
	
	Label lbTitle = new Label("회원가입", Label.CENTER);
	Label lbId = new Label("아이디", Label.CENTER);
	Label lbPw = new Label("비밀번호", Label.CENTER);
	Label lbPwChk = new Label(" 비밀번호 확인", Label.CENTER);
	Label lbName = new Label("   성명", Label.CENTER);
	Label lbHP = new Label("연락처", Label.CENTER);
	Label lbAdr = new Label("   주소", Label.CENTER);
	
	JTextField tfId  = new JTextField(20);	
	JTextField tfPw  = new JTextField(20);
	JTextField tfPwChk  = new JTextField(20);
	JTextField tfName  = new JTextField(20);
	JTextField tfHP  = new JTextField(20);
	JTextField tfAdr  = new JTextField(20);

	boolean idCheck = false;
	
	// DB
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/pos?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	
	PreparedStatement pstmt = null;	
	Statement stmt = null;
	ResultSet rs = null;
	
	String result = "";
	
	SignUp()
	{
		init();
		Start();
	}
	
	
	public void init() 
	{
		
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font13 = new Font("SansSerif", Font.BOLD, 13);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		setTitle("POS 회원가입"); 
			
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		this.setVisible(true);
		this.setLocation(700, 300);
		this.setSize(400, 500);
	
		
		//title
		add(lbTitle);
		lbTitle.setBounds(140, 20, 100, 40);
		lbTitle.setFont(font15);
		lbTitle.setBackground(Color.WHITE);
	
		
		// lb
		add(lbId);
		lbId.setBounds(55, 80, 100, 40);
		lbId.setFont(font13);
		lbId.setBackground(Color.WHITE);
		
		add(lbPw);
		lbPw.setBounds(50, 120, 100, 40);
		lbPw.setFont(font13);
		lbPw.setBackground(Color.WHITE);
		
		add(lbPwChk);
		lbPwChk.setBounds(30, 160, 100, 40);
		lbPwChk.setFont(font13);
		lbPwChk.setBackground(Color.WHITE);

		
		add(lbName);
		lbName.setBounds(65, 200, 80, 40);
		lbName.setFont(font13);
		lbName.setBackground(Color.WHITE);
		
		add(lbHP);
		lbHP.setBounds(55, 240, 100, 40);
		lbHP.setFont(font13);
		lbHP.setBackground(Color.WHITE);
		
		
		add(lbAdr);
		lbAdr.setBounds(65, 280, 80, 40);
		lbAdr.setFont(font13);
		lbAdr.setBackground(Color.WHITE);
	
		
		// tf	
		add(tfId);
		tfId.setBounds(160, 90, 120, 25);
		tfId.setFont(font15);
		
		
		add(tfPw);
		tfPw.setBounds(160, 130, 120, 25);
		tfPw.setFont(font15);
		
		add(tfPwChk);
		tfPwChk.setBounds(160, 170, 120, 25);
		tfPwChk.setFont(font15);
		
		add(tfName);
		tfHP.setBounds(160, 210, 120, 25);
		tfHP.setFont(font15);
		
		add(tfHP);
		tfName.setBounds(160, 250, 120, 25);
		tfName.setFont(font15);
		
		add(tfAdr);
		tfAdr.setBounds(160, 290, 120, 25);
		tfAdr.setFont(font15);


		//btn
		add(btnBack);
		btnBack.setLayout(null);
		btnBack.setBounds(200, 350, 90, 30);
		btnBack.setFont(font13);
		btnBack.setBackground(Color.PINK);
		
		add(btnSignUp);
		btnSignUp.setLayout(null);
		btnSignUp.setBounds(100, 350, 90, 30);
		btnSignUp.setFont(font13);
		btnSignUp.setBackground(Color.GREEN);
		
		
		add(btnIdChk);
		btnIdChk.setBounds(287, 85, 90, 30);
		btnIdChk.setFont(font13);
		btnIdChk.setBackground(Color.ORANGE);
		
		this.add(jbc);
		jbc.setVisible(true);
		jbc.setLocation(0, 0);
		jbc.setSize(400,400);
		jbc.setBackground(Color.WHITE);
		jbc.setOpaque(true);
		
	}
	
	
	public void Start()
	{
		btnBack.addActionListener(this);
		btnSignUp.addActionListener(this);
		btnIdChk.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
	}
	
	
	public static void main(String[] args)
	{	new SignIn();	}
	
	
	void selectCheck(String joinId)
	{
				
		try {
	
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
		
			String query= "select * from logindb";
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				if(joinId.equals(rs.getString("id")))
				{
					dlgMsg("이미 사용중입니다.");
					idCheck = false;
					return;					
				}				
					
			}	
			dlgMsg("사용가능한 아이디입니다.");
			tfId.setEnabled(false);
			idCheck = true;
			
			rs.close();
		} catch (SQLException error) {
			System.err.println("error = " + error.toString());			
		}	
	}

	
	void insert(String joinId, String joinPw, String joinName,String joinHp,String joinAddr )
	{
			

		try {
			conn = DriverManager.getConnection(url, id, pass);
			
			String query = "insert into logindb values (null, ?, ?, ?, ?, ?,now())";

			pstmt = conn.prepareStatement(query);	
			
			pstmt.setString(1, joinId);
			pstmt.setString(2, joinPw);
			pstmt.setString(3, joinName); 
			pstmt.setString(4, joinHp);			
			pstmt.setString(5, joinAddr);
			pstmt.executeUpdate();
		} catch (SQLException e1) {			
		}	
		
		tfId.setText("");
		tfPw.setText("");
		tfPwChk.setText("");
		tfName.setText("");
		tfHP.setText("");
		tfAdr.setText("");
			
	}

	
	void dlgMsg(String msg)
	{
		final Dialog dlg = new Dialog(this, "알림", true);
		dlg.setLayout(null);	
		
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		Label lbTitle = new Label(msg);
		
		
		Button bt = new Button("확인");
		Panel pp = new Panel(new FlowLayout());
		pp.add(bt);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlg.setVisible(false);
			}
		});
		
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dlg.setVisible(false);
			}
		});	
		
		dlg.add(lbTitle);
		lbTitle.setBounds(70, 50, 250, 30);		
		lbTitle.setFont(font15);	
		
		dlg.add(bt);
		bt.setBounds(100, 100, 100, 30);		
		bt.setFont(font15);
		
		dlg.setLocation(810, 400);
		dlg.add("South", pp);
		dlg.setSize(300, 200);
		dlg.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnBack)
		{
			SignIn si = new SignIn();
			setVisible(false);

		}
	
		if(e.getSource() == btnIdChk)
		{
			String id = tfId.getText();
			if(id.equals("")) {dlgMsg("아이디를 입력해주세요.");return;}
			selectCheck(id);
	
		}
		else if(e.getSource() == btnSignUp)
		{
	
			String id = tfId.getText();
			String pw = tfPw.getText();
			String pw2 = tfPwChk.getText();
			String name = tfName.getText();
			String hp = tfHP.getText();
			String addr = tfAdr.getText();
			
			if(id.equals("")) {dlgMsg("아이디를 입력하세요.");return;}
			else if(pw.equals("")) {dlgMsg("비밀번호를 입력하세요.");return;}
			else if(pw2.equals("")) {dlgMsg("비밀번호를 확인하세요.");return;}
			else if(name.equals("")) {dlgMsg("  성명을 입력하세요.");return;}
			else if(hp.equals("")) {dlgMsg("연락처를 입력하세요.");return;}
			else if(addr.equals("")) {dlgMsg("   주소를  입력하세요.");return;}
			
			if(!pw.equals(pw2)){dlgMsg("비밀번호를 확인하세요.");return;}	
			if(idCheck == false){dlgMsg("중복체크를 확인하세요");return;}
		
			insert(id, pw, name, hp, addr );
			
			SignIn si = new SignIn();
			setVisible(false);
			
		}


	
	
	
	}	

}
