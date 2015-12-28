package project.qrcode;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.google.zxing.WriterException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

public class Generate extends JPanel {


	private static final long serialVersionUID = 1L;
	protected static JFrame frame;
	protected JTextArea textCode;
	protected JLabel lblQR;
	protected JFileChooser fc;
	protected JButton btnGen;
	protected JButton btnSave;
	public Generate() {
		setBackground(new Color(0, 102, 204));
		setLayout(null);
		
		
		fc = new JFileChooser();
		FileFilter  filter = new FileNameExtensionFilter("PNG","png");
		fc.setFileFilter(filter);
		
		btnGen = new JButton("Generate");
		btnGen.setIcon(new ImageIcon(Generate.class.getResource("/images/arr.png")));
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ZXingDemo1.createQRImage(textCode.getText(), 200);
					lblQR.setIcon(new ImageIcon(ZXingDemo1.getImage()));
					
				} catch (WriterException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnGen.setBounds(50, 362, 143, 38);
		btnGen.setFont(new Font("Agency FB", Font.BOLD, 22));
		add(btnGen);
		
		btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(Generate.class.getResource("/images/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc.showSaveDialog(Generate.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	 File file = fc.getSelectedFile();
	            	 String path=file.getAbsolutePath()+".png";
	           
	            	 file=new File(path);
	            	 ZXingDemo1.save(file,"png");
	            }
			}
		});
		btnSave.setBounds(212, 362, 134, 38);
		btnSave.setFont(new Font("Agency FB", Font.BOLD, 22));
		add(btnSave);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLUE);
		scrollPane.setBounds(17, 27, 224, 255);
		scrollPane.setPreferredSize(new Dimension(224,255));
		scrollPane.setBorder(new TitledBorder(null, "Enter Encoding String", TitledBorder.LEADING, TitledBorder.TOP,new Font("Agency FB", Font.BOLD, 20), Color.white));
		
		textCode = new JTextArea();
		scrollPane.setViewportView(textCode);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null,"QR Generated",TitledBorder.LEADING, TitledBorder.TOP,new Font("Agency FB", Font.BOLD, 20), Color.black));
		panel.setBounds(267, 27, 218, 245);
		
		JSplitPane jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,panel);
		panel.setLayout(null);
		
		lblQR = new JLabel("");
		lblQR.setBounds(6, 40, 202, 179);
		panel.add(lblQR);
		jsp.setBounds(17,27,500,312);
		add(jsp);
		
	}
	public static void main(String args[]){
		QRCode.setNimbus();
		create();
	}
	protected static void create() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Generate());
		frame.setVisible(true);
	}
}
