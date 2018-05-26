package menu;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class Menu {
	//Menu class�������� �⺻ Ʋ�� �гΰ� ��ư��
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
	
	//TextArea�� 50��, 50���� �����մϴ�.      
	JTextArea Righttextfield = new JTextArea("", 50, 50); 		
	JTextArea Lefttextfield = new JTextArea("", 50, 50); 
	
	//EDIT���� ���� �Ұ��� �� boolean
	private boolean LeftEditonoff = false;
	private boolean RightEditonoff = false;

	//Dialogâ���� �����ϱ�
	FileDialog  SaveText = new FileDialog(f,"����",FileDialog.SAVE);
	
	public Menu(){
		f.setSize(900,600);//ȭ���� ũ�⸦ ����
		f.setLayout(new BorderLayout());
		
		//������ Compare & Merge �κ�
		JPanel menueastPanel = new JPanel();
		menueastPanel.setLayout(new GridLayout(4,1,4,20));
		f.add("East", menueastPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		Compare.setBackground(Color.pink);
		Merge.setBackground(Color.pink);
		EXIT.setBackground(Color.pink);
		*/
		menueastPanel.add("East",Compare);
		menueastPanel.add("East",LeftMerge);
		menueastPanel.add("East",RightMerge);
		menueastPanel.add("East",EXIT);

		//�߾� ���� TextView �κ�
		LeftPanel.setLayout(new BorderLayout());
		JPanel LeftNorthPanel = new JPanel();
		LeftNorthPanel.setLayout(new GridLayout(1,3,4,4));
		
		LeftPanel.add("North", LeftNorthPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		LeftLoad.setBackground(Color.pink);
		LeftEdit.setBackground(Color.pink);
		LeftSave.setBackground(Color.pink);
		*/
		LeftNorthPanel.add("North",LeftLoad);
		LeftNorthPanel.add("North",LeftEdit);
		LeftNorthPanel.add("North",LeftSave);
		
		//TextArea�� ���ʿ� �߰�, textfield�� �⺻ false�� ����ִ»���    
		Lefttextfield.setEditable(LeftEditonoff);
		LeftPanel.add("Center",new JScrollPane(Lefttextfield));

		
		//�߾� ������ TextView �κ�
		RightPanel.setLayout(new BorderLayout());
		JPanel RightNorthPanel = new JPanel();
		RightNorthPanel.setLayout(new GridLayout(1,3,4,4));
		RightPanel.add("North", RightNorthPanel);
		/*��ư�������ҷ��� ���⼭ ��������
		RightLoad.setBackground(Color.pink);
		RightEdit.setBackground(Color.pink);
		RightSave.setBackground(Color.pink);
		*/
		RightNorthPanel.add("North",RightLoad);
		RightNorthPanel.add("North",RightEdit);
		RightNorthPanel.add("North",RightSave);
		
		//TextArea�� �����ʿ� �߰�, textfield�� �⺻ false�� ����ִ»���      
		Righttextfield.setEditable(RightEditonoff);
		RightPanel.add("Center",new JScrollPane(Righttextfield));

		
		//���� ������ Textview ���������� �߾ӿ� �гθ��� �߰� 
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(1,2,4,4));
		CenterPanel.add("West",LeftPanel);
		CenterPanel.add("East",RightPanel);
		f.add("Center", CenterPanel);

		
		Toolkit tk = Toolkit.getDefaultToolkit(); //������ Toolkit��ü�� ��´�.
		Dimension screenSize = tk.getScreenSize();
		
		//��ư�� �������� �Ʒ��� class�� �����ѳ����� �����ϵ��� �����߰�
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
		
		//ó�� ���۽� ���α׷� ��ġ �� ������ ����
		f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);

		//�������� �ݱ��ư ����� ���α׷�����ǵ��ϼ���
		f.addWindowListener(new EventHandler());
		
		//������ Frame�� ȭ�鿡 ���̵��� �Ѵ�.
		f.setVisible(true);
	}
	public class ButtonListener implements ActionListener{
		
		ArrayList<String> leftTXT;  // ���� ����
		ArrayList<String> rightTXT; // ������ ����
		File leftfile = new File("left");//���� ����
		File rightfile = new File("right");	//������ ����
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == LeftMerge){
				//Merge���� action�� ����ɰ͵� �����߰�
			}
			else if(e.getSource() == RightMerge){
				//Merge���� action�� ����ɰ͵� �����߰�
			}
			else if(e.getSource() == Compare){
				//Compare���� action�� ����ɰ͵� �����߰�
			}
			else if(e.getSource() == LeftLoad){
				//Load���� action�� ����ɰ͵� �����߰�
				FileLoader load = new FileLoader(); // Ž����
				leftTXT = load.fileRead(); // ���� ��������
				leftfile = load.fileLoad;
				Lefttextfield.setText(""); // �ؽ�Ʈ�ʵ� �ʱ�ȭ �� ���
				for(int i = 0; i < leftTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ���
					Lefttextfield.append(leftTXT.get(i)+"\n");
				}
			}
			else if(e.getSource() == LeftEdit){
				//Edit���� action�� ����ɰ͵� �����߰�
				if(LeftEditonoff == false){
					LeftEdit.setText("EDIT ON");
					LeftEdit.setFont(new Font("���",Font.ITALIC,12));
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
				new FileSave(leftfile,Lefttextfield);
				//Save���� action�� ����ɰ͵� �����߰�
			}
			else if(e.getSource() == RightLoad){
				//Load���� action�� ����ɰ͵� �����߰�
				FileLoader load = new FileLoader(); // Ž����
				rightTXT = load.fileRead(); // ���� ��������
				rightfile = load.fileLoad;
				Righttextfield.setText("");
				for(int i = 0; i < rightTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
					Righttextfield.append(rightTXT.get(i)+"\n");
				}
			}
			else if(e.getSource() == RightEdit){
				//Edit���� action�� ����ɰ͵� �����߰�
				if(RightEditonoff == false){
					RightEdit.setText("EDIT ON");
					RightEdit.setFont(new Font("���",Font.ITALIC,12));
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
				new FileSave(rightfile,Righttextfield);
				//Save���� action�� ����ɰ͵� �����߰�
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
