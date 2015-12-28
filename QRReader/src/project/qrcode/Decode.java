package project.qrcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Decode extends JPanel {


	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JTextArea textCode;
	private JLabel lblQR;
	private JFileChooser fc;
	private JTextField textField;
	private JButton btnSearch;
	public Decode() {
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		
		fc = new JFileChooser();
		FileFilter  filter = new FileNameExtensionFilter("PNG","png");
		fc.setFileFilter(filter);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLUE);
		scrollPane.setBounds(17, 27, 224, 255);
		scrollPane.setPreferredSize(new Dimension(224,255));
		scrollPane.setBorder(new TitledBorder(null, "Encoding String", TitledBorder.LEADING, TitledBorder.TOP, new Font("Agency FB", Font.BOLD, 20), new Color(59, 59, 59)));
		
		textCode = new JTextArea();
		scrollPane.setViewportView(textCode);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "QR ", TitledBorder.LEADING, TitledBorder.TOP, new Font("Agency FB", Font.BOLD, 20), new Color(59, 59, 59)));
		panel.setBounds(267, 27, 218, 245);
		
		JSplitPane jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,panel);
		panel.setLayout(null);
		
		lblQR = new JLabel("");
		lblQR.setBounds(6, 40, 202, 179);
		panel.add(lblQR);
		jsp.setBounds(21,29,492,315);
		add(jsp);
		
		btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setIcon(new ImageIcon(Decode.class.getResource("/images/edit_find.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(Decode.this);
				 
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                lblQR.setIcon(new ImageIcon(file.getAbsolutePath()));
	                textField.setText(file.getAbsolutePath());
	                
	                textCode.setText(ZXingDemo1.readQRImage(file));
	            }
			}
		});
		btnSearch.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnSearch.setBounds(32, 373, 122, 37);
		add(btnSearch);
		
		textField = new JTextField();
		textField.setBounds(166, 375, 343, 28);
		add(textField);
		textField.setColumns(10);
		
	}
	public static void main(String args[]){
		QRCode.setNimbus();
		create();
	}
	protected static void create() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Decode());
		frame.setVisible(true);
	}
}
