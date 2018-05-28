package menu;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Menu {
	//Menu class콜했을시 기본 틀과 패널과 버튼들
	JFrame f = new JFrame("Comparing Text");
	JButton Compare = new JButton("Compare");
	JButton LeftMerge = new JButton("LeftMerge");
	JButton RightMerge = new JButton("RightMerge");
	JButton EXIT = new JButton("EXIT");
	
	JButton LeftLoad = new JButton("Load");
	JButton LeftEdit = new JButton("Edit");
	JButton LeftSave = new JButton("Save");
	JPanel LeftPanel = new JPanel();
	
	JButton RightLoad = new JButton("Load");
	JButton RightEdit = new JButton("Edit");
	JButton RightSave = new JButton("Save");
	JPanel RightPanel = new JPanel();
	
	//TextPane으로 설정바꾸었음 그래야지 줄별로 색깔변경가능해서      
	JTextPane Righttextfield = new JTextPane(); 		
	JTextPane Lefttextfield = new JTextPane(); 
	
	//SimpleAttributeSet 생성 
	SimpleAttributeSet attribute = new SimpleAttributeSet();
    	
	//EDIT수정 가능 불가능 용 boolean
	private boolean LeftEditonoff = false;
	private boolean RightEditonoff = false;
	
	//위쪽에 파일명 추가
	JLabel LeftName = new JLabel("파일명 : ");
	JLabel RightName = new JLabel("파일명 : ");
	
	public Menu(){
		f.setSize(900,600);//화면의 크기를 구함
		f.setLayout(new BorderLayout());
		
		//SimpleAttributeSet에서 생성한거고 성질은 넣어주는거 (글자다르게하는부분추가하려고 이 부분 추가 -빨간색진하게밑줄)
		StyleConstants.setForeground(attribute, Color.red);
	    StyleConstants.setBold(attribute, true);
	    StyleConstants.setUnderline(attribute, true);
		
		//오른쪽 Compare & Merge 부분
		JPanel menueastPanel = new JPanel();
		menueastPanel.setLayout(new GridLayout(4,1,4,20));
		f.add("East", menueastPanel);
		menueastPanel.add("East",Compare);
		menueastPanel.add("East",LeftMerge);
		menueastPanel.add("East",RightMerge);
		menueastPanel.add("East",EXIT);

		//중앙 왼쪽 TextView 부분
		LeftPanel.setLayout(new BorderLayout());
		JPanel LeftNorthPanel = new JPanel();
		JPanel LeftNorthPanel2 = new JPanel();
		LeftNorthPanel.setLayout(new BorderLayout(4,1));
		LeftNorthPanel2.setLayout(new GridLayout(1,3,4,4));
		
		LeftNorthPanel.add("South", LeftName);
		LeftNorthPanel2.add(LeftLoad);
		LeftNorthPanel2.add(LeftEdit);
		LeftNorthPanel2.add(LeftSave);
		LeftNorthPanel.add("North", LeftNorthPanel2);
		LeftPanel.add("North", LeftNorthPanel);
		
		//TextPane를 왼쪽에 추가, textfield는 기본 false로 잠겨있는상태 
		Lefttextfield.setEditable(LeftEditonoff);
		LeftPanel.add("Center",new JScrollPane(Lefttextfield));

		
		//중앙 오른쪽 TextView 부분
		RightPanel.setLayout(new BorderLayout());
		JPanel RightNorthPanel = new JPanel();
		JPanel RightNorthPanel2 = new JPanel();
		RightNorthPanel.setLayout(new BorderLayout(4,1));
		RightNorthPanel2.setLayout(new GridLayout(1,3,4,4));
				
		RightNorthPanel.add("South", RightName);
		RightNorthPanel2.add("North",RightLoad);
		RightNorthPanel2.add("North",RightEdit);
		RightNorthPanel2.add("North",RightSave);
		RightNorthPanel.add("North", RightNorthPanel2);
		RightPanel.add("North", RightNorthPanel);
				
		//TextPane를 오른쪽에 추가, textfield는 기본 false로 잠겨있는상태       
		Righttextfield.setEditable(RightEditonoff);
		RightPanel.add("Center",new JScrollPane(Righttextfield));

		
		//왼쪽 오른쪽 Textview 나누어진거 중앙에 패널만들어서 추가 
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(1,2,4,4));
		CenterPanel.add("West",LeftPanel);
		CenterPanel.add("East",RightPanel);
		f.add("Center", CenterPanel);

		
		Toolkit tk = Toolkit.getDefaultToolkit(); //구현된 Toolkit객체를 얻는다.
		Dimension screenSize = tk.getScreenSize();
		
		//버튼을 눌렀을시 아래에 class에 정의한내용대로 실행하도록 내용추가
		ButtonListener listener = new ButtonListener();
		Compare.addActionListener(listener);
		LeftMerge.addActionListener(listener);
		RightMerge.addActionListener(listener);
		EXIT.addActionListener(listener);
		
		LeftLoad.addActionListener(listener);
		LeftEdit.addActionListener(listener);
		LeftSave.addActionListener(listener);

		RightLoad.addActionListener(listener);
		RightEdit.addActionListener(listener);
		RightSave.addActionListener(listener);
		
		//처음 시작시 프로그램 위치 및 사이즈 설정
		f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);

		//오른쪽위 닫기버튼 실행시 프로그램종료되도록설정
		f.addWindowListener(new EventHandler());
		
		//생성한 Frame을 화면에 보이도록 한다.
		f.setVisible(true);
	}
	public class ButtonListener implements ActionListener{
		
		ArrayList<String> leftTXT;  // 왼쪽 파일
		ArrayList<String> rightTXT; // 오른쪽 파일
		ArrayList<Integer> differ_index = null;	// 다른 부분 인덱스 저장배열
		File leftfile = null;//왼쪽 파일 처음에 비어있도록설정 이쪽 패널에 로드시 파일에관한 내용이들어가도록해서 Save에 영향을줌
		File rightfile = null;	//오른쪽 파일 처음에 비어있도록설정 이쪽 패널에 로드시 파일에관한 내용이들어가도록해서 Save에 영향을줌
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == LeftMerge){ // right to left
				//Merge관련 action시 실행될것들 내용추가
				String[] sychright = Righttextfield.getText().split("\r\n",100000);
				String[] sychleft = Lefttextfield.getText().split("\r\n",100000);
				ArrayList<String> left = new ArrayList<String>();
				ArrayList<String> right = new ArrayList<String>();
					
				for(int i = 0; i < sychright.length ; i++) {
					right.add(sychright[i]);
				}
				for(int i = 0; i < sychleft.length ; i++) {
					left.add(sychleft[i]);
				}
				
				for(int i = 0; i < differ_index.size(); i++) {
					if(right.get(differ_index.get(i)).equals("")) {
							continue;
					}
					left.set(differ_index.get(i), right.get(differ_index.get(i)));
				}
				Lefttextfield.setText("");
				String lText = new String();
				for(int i = 0; i < left.size(); i++) { // 텍스트필드에 저장
					lText = lText + left.get(i) + "\r\n";
				}
				Lefttextfield.setText(lText);
			}
			else if(e.getSource() == RightMerge){ // left to right
				//Merge관련 action시 실행될것들 내용추가
				String[] sychright = Righttextfield.getText().split("\r\n",100000);
				String[] sychleft = Lefttextfield.getText().split("\r\n",100000);
				ArrayList<String> left = new ArrayList<String>();
				ArrayList<String> right = new ArrayList<String>();
					
				for(int i = 0; i < sychright.length ; i++) {
					right.add(sychright[i]);
				}
				for(int i = 0; i < sychleft.length ; i++) {
					left.add(sychleft[i]);
				}
				
				for(int i = 0; i < differ_index.size(); i++) {
					if(!left.get(differ_index.get(i)).equals("")) {
						right.set(differ_index.get(i), left.get(differ_index.get(i)));	
					}
					
				}
				Righttextfield.setText("");
				String rText = new String();
				for(int i = 0; i < right.size(); i++) { // 텍스트필드에 저장
					rText = rText + right.get(i) + "\r\n";
				}
				Righttextfield.setText(rText);
			}
			else if(e.getSource() == Compare){
				//미완성코드임 예시로 처음0에서4까지만변경하도록해보았음 둘중하나로 바꾸는듯?
				Lefttextfield.getStyledDocument().setCharacterAttributes(4, 8, attribute, false);
			//	Lefttextfield.getStyledDocument().setParagraphAttributes(4, 8, attribute, false);
				//Compare관련 action시 실행될것들 내용추가
				
				FileCompare compare = new FileCompare();
				int max = 10000000;
				
				// table 만들기 위해 textfield에서 문자열 가져오기
				String str_tmp = Lefttextfield.getText();
				
				str_tmp = "0" + "\r\n" + str_tmp;	// 테이블 특징상 문자열 앞에 0 처리
				String[] left = str_tmp.split("\r\n", max);
				
				
				str_tmp = Righttextfield.getText();
				str_tmp = "0" + "\r\n" + str_tmp;	// 테이블 특징상 문자열 앞에 0 처리
				String[] right = str_tmp.split("\r\n", max);
				
				// table 만들기
				int[][] table = compare.makeLCSTable(left, right);
				int lcsLength = compare.getLcsLength();	// LCS_Length 값을 저장
				
				// convert to array -> List
				// initialize
				leftTXT = new ArrayList<String>();	
				for(int i = 1 ; i < left.length ; i++) // copy array to List
					leftTXT.add(left[i]);
				
				// initialize
				rightTXT = new ArrayList<String>();
				for(int i = 1 ; i < right.length ; i++) // copy array to List
					rightTXT.add(right[i]);
				
				ArrayList<String> lcs = compare.makeLCSString(left.length, right.length, lcsLength, table, right);	// lcs 문자열을 구하는 함수가 또 필요하다.
				compare.synchronizingTextContent(leftTXT, rightTXT, lcs);
				
				
				String lText = new String();
				Lefttextfield.setText(""); // 텍스트필드 초기화 후 출력
				for(int i = 0; i < leftTXT.size(); i++) { // 텍스트필드에 출력
					if(i == leftTXT.size() - 1)
						lText = lText + leftTXT.get(i);
					else
						lText = lText + leftTXT.get(i) + "\r\n";
				}
				Lefttextfield.setText(lText);
				
				String rText = new String();
				Righttextfield.setText(""); // 텍스트필드 초기화 후 출력
				for(int i = 0; i < rightTXT.size(); i++) { // 텍스트필드에 출력
					if(i == rightTXT.size() - 1)
						rText = rText + rightTXT.get(i);
					else
						rText = rText + rightTXT.get(i) + "\r\n";
				}
				Righttextfield.setText(rText);
				
				// 이부분은 서로 다른 부분의 인덱스를 얻어준다
				differ_index = compare.getDifferentLineNumberIndex(leftTXT, rightTXT);
			}
			else if(e.getSource() == LeftLoad){
				//Load관련 action시 실행될것들 내용추가
				FileLoader load = new FileLoader(); // 탐색기
				leftTXT = load.fileRead(); // 파일 가져오기
				leftfile = load.fileLoad;
				String lText = new String();
				Lefttextfield.setText(""); // 텍스트필드 초기화 후 출력
				for(int i = 0; i < leftTXT.size(); i++) { // 텍스트필드에 출력
					lText = lText + leftTXT.get(i) + "\r\n";
				}
				Lefttextfield.setText(lText);
				LeftName.setText("파일명 : "+leftfile.getName());
			}
			else if(e.getSource() == LeftEdit){
				//Edit관련 action시 실행될것들 내용추가
				if(LeftEditonoff == false){
					LeftEdit.setText("EDIT ON");
					LeftEdit.setFont(new Font("고딕",Font.ITALIC,12));
					LeftEditonoff = true;
				}
				else if(LeftEditonoff = true){
					LeftEdit.setText("Edit");
					LeftEdit.setFont(new Font("Dialog",Font.BOLD,12));
					LeftEditonoff = false;
				}
				Lefttextfield.setEditable(LeftEditonoff);
			}
			else if(e.getSource() == LeftSave){
				FileSave filesave = new FileSave(leftfile,Lefttextfield);
				leftfile = filesave.savefile;
				//Save관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == RightLoad){
				//Load관련 action시 실행될것들 내용추가
				FileLoader load = new FileLoader(); // 탐색기
				rightTXT = load.fileRead(); // 파일 가져오기
				rightfile = load.fileLoad;
				String rText = new String();
				Righttextfield.setText("");
				for(int i = 0; i < rightTXT.size(); i++) { // 텍스트필드에 저장
					rText = rText + rightTXT.get(i) + "\r\n";
				}
				Righttextfield.setText(rText);
				RightName.setText("파일명 : "+rightfile.getName());
			}
			else if(e.getSource() == RightEdit){
				//Edit관련 action시 실행될것들 내용추가
				if(RightEditonoff == false){
					RightEdit.setText("EDIT ON");
					RightEdit.setFont(new Font("고딕",Font.ITALIC,12));
					RightEditonoff = true;
				}
				else if(RightEditonoff = true){
					RightEdit.setText("Edit");
					RightEdit.setFont(new Font("Dialog",Font.BOLD,12));
					RightEditonoff = false;
				}
				Righttextfield.setEditable(RightEditonoff);
			}
			else if(e.getSource() == RightSave){
				FileSave filesave = new FileSave(rightfile,Righttextfield);
				rightfile = filesave.savefile;
				//Save관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == EXIT){
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
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
