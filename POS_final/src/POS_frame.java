import javax.swing.JFrame;

import java.awt.Container;

import javax.swing.*;

public class POS_frame extends JFrame{

	POS_frame()
	{		
		// POS_panel Ŭ������ ���̾ƿ�, �г� �� ��κ��� ������Ʈ�� ���� ������ ContentPane ����(Main Panel ����)
		// JTable ����� ���� POS_Panel�� ����
		setContentPane(new POS_panel());
		// �ݱ� ��ư Ȱ��ȭ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Frame â ����
		setTitle("POS �ý��� : ���°�");		
		setVisible(true);
		setSize(1100, 790);
		// Frame â ������ ��� ���� (dimen�Ƚᵵ ��)
		setLocationRelativeTo(null);
		
	}
}
