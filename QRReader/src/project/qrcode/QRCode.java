//Author : Sujata Regoti
//Date : 4 July 2015

package project.qrcode;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JTabbedPane;
import java.awt.Font;

public class QRCode extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JTabbedPane tabbedPane;

	/**
	 * Create the panel.
	 */
	public static void main(String args[]){
		setNimbus();
		create();
	}
	public QRCode() {
		
		
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Agency FB", Font.BOLD, 25));
		add(tabbedPane, BorderLayout.CENTER);
		
		Generate g=new Generate();
		g.textCode.setFont(new Font("Agency FB", Font.BOLD, 22));
		tabbedPane.addTab("Encode", g);
		tabbedPane.addTab("Decode", new Decode());

		
	}
	protected static void create() {
		
		frame = new JFrame("QRCODE");
		int wid=550;
		int hei=500;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		frame.setBounds(w/2-wid/2, h/2-hei/2, wid, hei);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new QRCode());
		frame.setVisible(true);
	}
	public static void setNimbus() {

		try 
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
			{
                if ("Nimbus".equals(info.getName())) 
				{
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
		catch (ClassNotFoundException ex) 
		{
            java.util.logging.Logger.getLogger(QRCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
	}

}
