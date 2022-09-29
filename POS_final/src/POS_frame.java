import javax.swing.JFrame;

import java.awt.Container;

import javax.swing.*;

public class POS_frame extends JFrame{

	POS_frame()
	{		
		// POS_panel 클래스에 레이아웃, 패널 등 대부분의 컴포넌트를 설정 가능한 ContentPane 생성(Main Panel 역할)
		// JTable 사용을 위해 POS_Panel에 붙임
		setContentPane(new POS_panel());
		// 닫기 버튼 활성화
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Frame 창 설정
		setTitle("POS 시스템 : 원태건");		
		setVisible(true);
		setSize(1100, 790);
		// Frame 창 열릴때 가운데 정렬 (dimen안써도 됨)
		setLocationRelativeTo(null);
		
	}
}
