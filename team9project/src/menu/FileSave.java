package menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSave {
	private int isSave;
	
	FileSave(){}
	FileSave(File f,JTextArea TA){
		fileSave(f,TA);
	}
	
	public void fileSave(File f,JTextArea TA) {
		
		JFileChooser fileSearch = new JFileChooser(f.getAbsolutePath()); // Ž���� ��ü ����
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt"); // txt ����
		fileSearch.setFileFilter(filter); // ���� ����
		fileSearch.setDialogTitle("���������ϱ�");
		fileSearch.setSelectedFile(f);

		isSave = fileSearch.showSaveDialog(null); // Ž���� ����

		
		if(isSave == JFileChooser.APPROVE_OPTION) { // ������ Ŭ��
			textSave(f,TA);
			f = fileSearch.getSelectedFile();
		}
		else if(isSave == JFileChooser.CANCEL_OPTION) { // ��Ҹ� Ŭ��
			
		}
	}
	
	private void textSave(File f,JTextArea TA){
		try{
			FileWriter FW = new FileWriter(f);
			BufferedWriter BW = new BufferedWriter(FW);
			String str = TA.getText();
			for(int i =0; i <str.length();i++)
			{
				if(str.charAt(i) == '\n')
				{
					BW.newLine();
				}
				else
					BW.write(str.charAt(i));
			}
			BW.close();
			FW.close();
		}catch(Exception e1){}
	}
}
