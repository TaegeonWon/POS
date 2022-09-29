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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.StartDocument;


public class SignIn extends JFrame implements ActionListener {
	
	JFrame f = new JFrame();

	Dimension dimen, dimen1;
	
	JButton btnSignin = new JButton("로그인");
	JButton btnSignUp = new JButton("회원가입");
	JLabel jbc = new JLabel();
	
	Label lbTitle = new Label("POS 로그인", Label.CENTER);
	Label lbId = new Label("아이디", Label.CENTER);
	Label lbPw = new Label("비밀번호", Label.CENTER);
	
	static JTextField tfId  = new JTextField(20);	
	JPasswordField tfPw  = new JPasswordField(20);
	
	boolean loginCheck = false;
	
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/pos?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";	//학원
//	String pass = "0000";	//집
	
	PreparedStatement pstmt = null;	
	Statement stmt = null;
	ResultSet rs = null;
	
	String result = "";

	public static void main(String[] args) {
		
		SignIn si = new SignIn();
	}
		
	
	SignIn()
	{
		init();
		Start();
	}


	public void init() 
	{
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font17 = new Font("SansSerif", Font.BOLD, 17);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font12 = new Font("SansSerif", Font.BOLD, 12);
		
		setTitle("POS 로그인"); 
			
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		this.setLocation(700, 300);
		this.setSize(400,400);
		this.setBackground(Color.WHITE);

		this.add(lbTitle);
		lbTitle.setBounds(95, 40, 200, 40);
		lbTitle.setFont(font17);
		lbTitle.setBackground(Color.WHITE);
		
		this.add(lbId);
		this.setLayout(null);
		this.setVisible(true);
		lbId.setBounds(100, 100, 100, 30);
		lbId.setFont(font12);
		lbId.setBackground(Color.WHITE);
		
		add(tfId);
		tfId.setBounds(130, 130, 120, 25);
		tfId.setFont(font15);
		tfId.setVisible(true);
		
		this.add(lbPw);
		this.setLayout(null);
		this.setVisible(true);
		lbPw.setBounds(105, 160, 100, 30);
		lbPw.setFont(font12);
		lbPw.setBackground(Color.WHITE);
		
		add(tfPw);
		tfPw.setBounds(130, 190, 120, 25);
		tfPw.setFont(font15);
		tfPw.setEchoChar('*');
		
		this.add(btnSignin);
		this.setLayout(null);
		this.setVisible(true);
		btnSignin.setBounds(100, 250, 90, 30);
		btnSignin.setFont(font12);
		btnSignin.setBackground(Color.ORANGE);
		
		this.add(btnSignUp);
		this.setLayout(null);
		this.setVisible(true);
		btnSignUp.setBounds(200, 250, 90, 30);
		btnSignUp.setFont(font12);
		btnSignUp.setBackground(Color.GREEN);

		this.add(jbc);
		jbc.setVisible(true);
		jbc.setLocation(0, 0);
		jbc.setSize(400,400);
		jbc.setBackground(Color.WHITE);
		jbc.setOpaque(true);

	
	}
	
	public void Start() 
	{
		btnSignin.addActionListener(this);
		btnSignUp.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
	}
	
	void checkLogin(String loginId, String loginPw)
	{
			
	try {
	
		conn = DriverManager.getConnection(url, id, pass);
		stmt = conn.createStatement();
	
		String loginQuery = "select * from logindb where id='"+loginId+"' AND pw='"+loginPw+"'";
		rs = stmt.executeQuery(loginQuery);
	
		while (rs.next()) {
			
			loginCheck = true;
			
		}

			rs.close();
		} catch (SQLException error) {
			System.err.println("error = " + error.toString());			
		}	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSignin)
		{
			
			String id = tfId.getText();
			String pw = tfPw.getText();
			
			//아이디 비번 공백체크
			if(id.equals("")) {dlgMsg("아이디를 입력해주세요.");return;}
			if(pw.equals("")) {dlgMsg("비밀번호를 입력해주세요.");return;}
			
			checkLogin(id, pw);
			
			if(loginCheck == true)
			{
				POS_frame pf = new POS_frame();
				setVisible(false);
				System.out.println("로그인 ID : " + id);
				System.out.println("로그인 PW : " + pw);

			}
			else
			{
				dlgMsg2("아이디 또는 비밀번호 오류");
			}
					
		}
		
		if(e.getSource() == btnSignUp)
		{
			SignUp su = new SignUp();
			setVisible(false);
		}
		
	}
	
	
	//알림 dlg
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
	void dlgMsg2(String msg)
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
		lbTitle.setBounds(55, 50, 250, 30);		
		lbTitle.setFont(font15);
		
		
		
		dlg.add(bt);
		bt.setBounds(100, 100, 100, 30);		
		bt.setFont(font15);
		
		
		dlg.setLocation(810, 400);
		dlg.add("South", pp);
		dlg.setSize(300, 200);
		dlg.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
