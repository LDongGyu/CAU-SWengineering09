package menu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu implements ActionListener{
	//Menu class콜했을시 기본 틀과 패널과 버튼들
	JFrame f = new JFrame("Comparing Text");
	JButton Compare = new JButton("Compare");
	JButton Merge = new JButton("Merge");
	JButton EXIT = new JButton("EXIT");
	
	JButton LeftLoad = new JButton("Load");
	JButton LeftEdit = new JButton("Edit");
	JButton LeftSave = new JButton("Save");
	JPanel LeftPanel = new JPanel();
	
	JButton RightLoad = new JButton("Load");
	JButton RightEdit = new JButton("Edit");
	JButton RightSave = new JButton("Save");
	JPanel RightPanel = new JPanel();
	
	
	public Menu(){
		f.setSize(900,600);//화면의 크기를 구함
		f.setLayout(new BorderLayout());
		
		//오른쪽 Compare & Merge 부분
		JPanel menueastPanel = new JPanel();
		menueastPanel.setLayout(new GridLayout(3,1,4,20));
		f.add("East", menueastPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		Compare.setBackground(Color.pink);
		Merge.setBackground(Color.pink);
		EXIT.setBackground(Color.pink);
		*/
		menueastPanel.add("East",Compare);
		menueastPanel.add("East",Merge);
		menueastPanel.add("East",EXIT);

		//중앙 왼쪽 TextView 부분
		LeftPanel.setLayout(new BorderLayout());
		JPanel LeftNorthPanel = new JPanel();
		LeftNorthPanel.setLayout(new GridLayout(1,3,4,4));
		LeftPanel.add("North", LeftNorthPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		LeftLoad.setBackground(Color.pink);
		LeftEdit.setBackground(Color.pink);
		LeftSave.setBackground(Color.pink);
		*/
		LeftNorthPanel.add("North",LeftLoad);
		LeftNorthPanel.add("North",LeftEdit);
		LeftNorthPanel.add("North",LeftSave);
		
		//TextArea를 50행, 50열로 설정합니다.      
		JTextArea Lefttextfield = new JTextArea("", 50, 50); 
		LeftPanel.add("Center",new JScrollPane(Lefttextfield));

		
		//중앙 오른쪽 TextView 부분
		RightPanel.setLayout(new BorderLayout());
		JPanel RightNorthPanel = new JPanel();
		RightNorthPanel.setLayout(new GridLayout(1,3,4,4));
		RightPanel.add("North", RightNorthPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		RightLoad.setBackground(Color.pink);
		RightEdit.setBackground(Color.pink);
		RightSave.setBackground(Color.pink);
		*/
		RightNorthPanel.add("North",RightLoad);
		RightNorthPanel.add("North",RightEdit);
		RightNorthPanel.add("North",RightSave);
		
		//TextArea를 50행, 50열로 설정합니다.      
		JTextArea Righttextfield = new JTextArea("", 50, 50); 
		RightPanel.add("Center",new JScrollPane(Righttextfield));

		
		//왼쪽 오른쪽 Textview 나누어진거 중앙에 패널만들어서 추가 
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(1,2,4,4));
		CenterPanel.add("West",LeftPanel);
		CenterPanel.add("East",RightPanel);
		f.add("Center", CenterPanel);

		
		Toolkit tk = Toolkit.getDefaultToolkit(); //구현된 Toolkit객체를 얻는다.
		Dimension screenSize = tk.getScreenSize();
		
		f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);
		f.addWindowListener(new EventHandler());
		f.setVisible(true);//생성한 Frame을 화면에 보이도록 한다.
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == Merge){
			//Merge관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == Compare){
			//Compare관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == LeftLoad){
			//Load관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == LeftEdit){
			//Edit관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == LeftSave){
			//Save관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == RightLoad){
			//Load관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == RightEdit){
			//Edit관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == RightSave){
			//Save관련 action시 실행될것들 내용추가
		}
		else if(e.getSource() == EXIT){
			f.setVisible(false);
			f.dispose();
			System.exit(0);
		}
	}
	
	
}



class EventHandler implements WindowListener
{
	public void windowOpened(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}
