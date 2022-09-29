import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class POS_panel extends JPanel {
	
	// 16가지 메뉴,가격 배열 설정
	JButton Menubtn[] = new JButton[16]; // 주문할 메뉴
	JButton Sbtn[] = new JButton[2];	// 선택,전체삭제
	JButton Sbtn2[] = new JButton[4];	// 부가기능
	JButton Pbtn[] = new JButton[2];	// 현금,카드
 	
	String menu[] = {	"아메리카노H", "아메리카노I", "카페라떼H", "카페라떼I", 
						"카페모카H", "카페모카I", "바닐라라떼H", "바닐라라떼I",
						"녹차라떼H", "녹차라떼I", "초코라떼H", "초코라떼I",
					 	"자몽에이드", "레몬에이드", "체리에이드", "망고에이드"	};
	String submenu[] = {	"선택취소", "전체취소"	};
	String submenu2[] = {	"쿠폰사용", "샷추가", "사이즈업", "테이크아웃"	};
	String paymenu[] = {	"현금결제", "카드결제"	};
	
	int price[] = {	2000, 2000, 3000, 3000,
					3000, 3000, 3000, 3000,
					3500, 3500, 3500, 3500,
					3000, 3000, 3000, 3000	};
	
	// 다른곳으로 들어가는 배열변수를 초기화하는 int값
	int cnt = 1;
	// JTable의 '행'을 배열로 지정
	String Col[] = {	"상품명", "수량", "금액"	};
	// JTable의 '열'을 2차원 배열로 지정
	String Data[][] = {	};
	// JTable의 행,열을 불러오기 (모델객체 생성)
	DefaultTableModel model = new DefaultTableModel(Data,Col);
	// 행,열 변수를 JTable에 붙임↓↓↓
	JTable table = new JTable(model);
	// 금액란 화면은 JTextField로 구현
	JTextField tf = new JTextField(10);
	// 계산 되는 곳의 계산총합
	
	// JTable 컴포넌트를 붙일때는 꼭 JScrollPane 위에 붙여야 함
	// JscrollPane이 아닌 부분에 JTable을 붙이면 Table이 보이지 않으므로 꼭 주의
	
	
	
// 모든 Panel을 각각의 클래스로 만들어 설정, 생성자로 불러와서 사용 
	// 메인 패널
	public POS_panel() 
	{
		setLayout(null);
		// 배경색 커스터마이징
		setBackground(new Color(31,26,55));
		// 각 panel 로 사용할 클래스 불러오기
		Mbtn mb = new Mbtn();
		Sbtn sb = new Sbtn();
		Sbtn2 sb2 = new Sbtn2();
		Pbtn pb = new Pbtn();
		Window wd = new Window();
		Pay pay = new Pay();
		
		
		
		// 음료 메뉴 세팅
		mb.setVisible(true);
		mb.setSize(540,350);
		mb.setLocation(510,40);
		add(mb);
		// 서브메뉴 화면 세팅
		pb.setVisible(true);
		pb.setSize(540,90);
		pb.setLocation(510,620);
		add(pb);
		// 서브메뉴2 화면 세팅
		sb2.setVisible(true);
		sb2.setSize(540,90);
		sb2.setLocation(510,404);
		add(sb2);
		// 주문 화면 세팅
		wd.setVisible(true);
		wd.setSize(460,500);
		wd.setLocation(30,40);
		add(wd);
		// 취소선택 화면 세팅
		sb.setVisible(true);
		sb.setSize(540,90);
		sb.setLocation(510,512);
		add(sb);

		// 계산 화면 세팅
		tf.setVisible(true);
		tf.setSize(460,150);
		tf.setLocation(30,560);
		add(tf);
		
	}
	
	// 음료메뉴 클래스
	class Mbtn extends JPanel 
	{
		Mbtn()
		{
			setBackground(new Color(0,0,0));
			// n x n / x간격 , y간격 (int rows, int cols , int hgap, int vgap)
			setLayout(new GridLayout(4,4,5,5));
			// 메뉴 배열의 개수대로 버튼을 생성, 그리드 레이아웃에서만 설정 가능
			for(int i=0; i<Menubtn.length; i++)
			{
				Menubtn[i] = new JButton(menu[i]);
				add(Menubtn[i]);
			}
			// 음료메뉴 버튼 색상
			Menubtn[1].setBackground(new Color(92,209,255));
			Menubtn[3].setBackground(new Color(92,209,255));
			Menubtn[5].setBackground(new Color(92,209,255));
			Menubtn[7].setBackground(new Color(92,209,255));
			Menubtn[9].setBackground(new Color(92,209,255));
			Menubtn[11].setBackground(new Color(92,209,255));
			
			Menubtn[0].setBackground(new Color(255,178,245));
			Menubtn[2].setBackground(new Color(255,178,245));
			Menubtn[4].setBackground(new Color(255,178,245));
			Menubtn[6].setBackground(new Color(255,178,245));
			Menubtn[8].setBackground(new Color(255,178,245));
			Menubtn[10].setBackground(new Color(255,178,245));
			
			Menubtn[12].setBackground(new Color(255,216,92));
			Menubtn[13].setBackground(new Color(255,216,92));
			Menubtn[14].setBackground(new Color(255,216,92));
			Menubtn[15].setBackground(new Color(255,216,92));
//			
//			Font font13 = new Font("맑은고딕", Font.BOLD, 13);
//			Menubtn[0].setFont(font13);		Menubtn[5].setFont(font13);
//			Menubtn[1].setFont(font13);		Menubtn[6].setFont(font13);
//			Menubtn[2].setFont(font13);		Menubtn[7].setFont(font13);
//			Menubtn[3].setFont(font13);		Menubtn[8].setFont(font13);
//			Menubtn[4].setFont(font13);		Menubtn[9].setFont(font13);			
//			Menubtn[10].setFont(font13);	Menubtn[13].setFont(font13);
//			Menubtn[11].setFont(font13);	Menubtn[14].setFont(font13);
//			Menubtn[12].setFont(font13);	Menubtn[15].setFont(font13);
		}
		
	}
	
	// 서브메뉴(현금,카드) 클래스
	class Pbtn extends JPanel 
	{
		Pbtn()
		{
			setBackground(new Color(0,0,0));
			setLayout(new GridLayout(0,2,6,6));
			for(int i=0;i<paymenu.length; i++)
			{
				Pbtn[i] = new JButton(paymenu[i]);
				add(Pbtn[i]);
			}
			 Pbtn[0].setBackground(new Color(71,200,62));
			 Pbtn[1].setBackground(new Color(255,167,167));
			
		}
	}

	// 취소선택 메뉴 클래스
	class Sbtn extends JPanel 
	{
		Sbtn()
		{
			setBackground(new Color(0,0,0));
			setLayout(new GridLayout(0,2,6,6));
			for(int i=0;i<submenu.length; i++)
			{
				Sbtn[i] = new JButton(submenu[i]);
				add(Sbtn[i]);
			}
			Sbtn[0].setBackground(new Color(206,251,201));
			Sbtn[1].setBackground(new Color(255,216,216));
		}
	}
	
	// 서브2메뉴(쿠폰~) 클래스
	class Sbtn2 extends JPanel 
	{
		Sbtn2()
		{
			setBackground(new Color(0,0,0));
			setLayout(new GridLayout(0,4,5,5));
			for(int i=0; i<submenu2.length; i++)
			{
				Sbtn2[i] = new JButton(submenu2[i]);
				add(Sbtn2[i]);
			}
			Sbtn2[0].setForeground(new Color(255,0,0));
			Sbtn2[0].setBackground(new Color(255,255,187));
			Sbtn2[1].setBackground(new Color(255,255,255));
			Sbtn2[2].setBackground(new Color(183,240,177));
			Sbtn2[3].setBackground(new Color(178,204,255));

		}
	}
	
	// 주문 입력되는 스크린
	class Window extends JPanel 
	{
		Window()
		{
			setBackground(new Color(255,255,255));
			//이 그리드레이아웃이 Jpanel 의 header와 총 길이에 영향을 미침. (1,3)부분
			setLayout(new GridLayout(1,3,3,3));
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			// Jtable의 내용이 넘칠 시 자동으로 스크롤을 잡아줌. 얘가 없으면 Jtable이 구현이 안됨!!
			add(new JScrollPane(table));
			//각 행간의 높이를 설정
			table.setRowHeight(40);
			// header열의 폰트를 지정
			table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 12));
			
			
			//다음 탐구할 의문점 data 2차배열 변수의 빈값은 어디서 생성하는가?
			//DefaultTableModel의 정확한 역할은 무엇이며 왜 상단에 한번, 생성자에 한번 총 두번 생성하는가?
			//getModel의 기능과 다른 명령어는 어떤것이 있는가?

			//고찰 : 그간 Jtable이 생성되지 않은 이유: 
			// 예상1. Jframe에서 contentpane을 JPanel에 달아주지 않음(유력함)
			// 예상2. DefaultTableModel을 1개만 선언(?) 
			// 예상3. Jpanel 각각의 레이아웃을 (null)로 설정하여 일부만 구현되는 현상
			
			
			// 버튼 클릭 시 JTable에 값 출력 - 완성시 메뉴 쌓이게 말고 중복메뉴는 갯수랑 가격만 늘어나게 조건문 걸기
			for(int i=0; i<Menubtn.length; i++)
			{
				// index 초기화
				int idx = i;
				// 총 결제 금액

				Menubtn[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton mnBtn = (JButton)e.getSource();
						// addRow가 들어갈 JTable 변수 불러오기
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						// row를 추가하는 메소드, i값을 붙여준다.
						m.addRow(new Object[]{menu[idx],cnt,price[idx]});
						System.out.println("메뉴: "+ menu[idx] + " / 수량: "+ cnt + " / 금액: "+ price[idx]);
						
						}
					
					});
			}
			
				// 쿠폰 버튼 이벤트
				Sbtn2[0].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn2 = (JButton)e.getSource();
				
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						//  칼럼의 (바꿀값, Row순서 고르기, Row순서)
						table.setValueAt(0, table.getSelectedRow(), 1);
						
						table.setValueAt("쿠폰사용", table.getSelectedRow(), 2);
					}
				});
				// 샷추가 버튼
				Sbtn2[1].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn2 = (JButton)e.getSource();
					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						 
						// 500원이 추가되는 변수명
						int addShot = 500;
						// Row 값 하나만 가져올수 없고, Row,Column 둘 다 가져와서 temp 변수에 저장
						int temp = (int) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
//						String temp2 = (String) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
//						Object temp3 = (Object) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
						//샷추가
						System.out.println("샷추가 : +" + addShot);
						table.setValueAt(temp+addShot, table.getSelectedRow(), 2);
						

					}
				});
				// 사이즈업 버튼
				Sbtn2[2].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn2 = (JButton)e.getSource();
					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						// 500원이 추가되는 변수명
						int addSize = 1000;
						// Row 값 하나만 가져올수 없고, Row,Column 둘 다 가져와서 temp 변수에 저장
						int temp = (int) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
//						String temp2 = (String) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
//						Object temp3 = (Object) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
						//샷추가
						System.out.println("사이즈업 : +" + addSize);
						table.setValueAt(temp+addSize, table.getSelectedRow(), 2);
						
					}
				});
				// 테이크아웃 버튼
				Sbtn2[3].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn2 = (JButton)e.getSource();
					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						POS_CellRenderer renderer = new POS_CellRenderer();
//						try {
//							table.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
//						} 
//						catch (ClassNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
						String to = "테이크아웃";
						Object totemp = (Object) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
						table.setValueAt(totemp+to, table.getSelectedRow(), 2);
						
						
					}
				});
				
				
				// 선택취소버튼 이벤트
				Sbtn[0].addActionListener(new ActionListener() {
						
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn = (JButton)e.getSource();
						// removeRow가 들어갈 JTable 변수 불러오기
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.removeRow(table.getSelectedRow());
						
					}
				});
				// 전체취소 버튼 이벤트
				Sbtn[1].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton smBtn = (JButton)e.getSource();
				
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.setRowCount(0);
						tf.setText(String.valueOf(""));
					}
				});
		
				// 현금결제 버튼 이벤트
				Pbtn[0].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton pmBtn = (JButton)e.getSource();
					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						int sum = 0;
						int grc = table.getRowCount();
						for(int i=0; i<grc; i++ ) {
						
							sum += (int)table.getValueAt(i,2);
						}
						
						// textField에 출력
						tf.setFont((new Font("맑은고딕", Font.BOLD, 22)));
						tf.setText("결제금액 : " + sum);
						System.out.println("현금결제 : " + sum);
					}
				});
				// 카드결제 버튼 이벤트
				Pbtn[1].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton pmBtn = (JButton)e.getSource();
					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						int sum2 = 0;
						int grc2 = table.getRowCount();
						for(int i=0; i<grc2; i++ ) {
						// 0,1,2 번째 중 금액란이 2번째이므로 (i,2)
							sum2 += (int)table.getValueAt(i,2);
						}
						
						// textField에 출력
						tf.setFont((new Font("맑은고딕", Font.BOLD, 22)));
						tf.setText("결제금액 : " + sum2);
						System.out.println("카드결제 : " + sum2);
						
						Card card = new Card();
					}
				});
					
						
		}
	}
	// 계산되는 스크린 - 총액 값 Jtable 로 넣어보기!!
	class Pay extends JPanel 
	{
		Pay()
		{
			Font font20 = new Font("맑은고딕", Font.BOLD, 20);
			//tf에도 배경속성 사용가능함
			tf.setBackground(new Color(255,255,255));
			tf.setFont(font20);
			
		}
	}
	// 카드결제 안내창
	class Card extends JOptionPane
	{
		Card() 
		{
			JOptionPane.showMessageDialog(this, "카드를 읽혀주세요", "카드결제", JOptionPane.INFORMATION_MESSAGE);	
		}
		
	}


}

