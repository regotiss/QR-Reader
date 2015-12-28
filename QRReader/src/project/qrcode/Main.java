package project.qrcode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

class Main extends JWindow {
	
	private static final long serialVersionUID = 1L;
	static JFrame f;
	Timer timer1;
	private static int count;
	JProgressBar progressbar;
	JLabel lbl;
	
	Main() {
		setLayout(new BorderLayout());
		lbl=new JLabel(new ImageIcon(Main.class.getResource("/images/qrcode.png")));
		add(lbl);
		
		progressbar=new JProgressBar();
		progressbar.setMaximum(100);
		progressbar.setBackground(Color.white);
		progressbar.setForeground(Color.gray);
		progressbar.setPreferredSize(new Dimension(500,10));
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		int wid=500;
		int hei=300;
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		setSize(500,300);
		setLocation(w/2-wid/2,h/2-hei/2);
		add(progressbar,BorderLayout.SOUTH);
		setVisible(true);

		
		loadProgressBar();
		
	}


	
	private void loadProgressBar() {
        ActionListener al = new ActionListener() {


			public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;
                progressbar.setValue(count);
                if (count >= 100) {
                	progressbar.setValue(count);
                	count++;
                    QRCode.main(null);
                    setVisible(false);//swapped this around with timer1.stop()
                    timer1.stop();
                }

            }

        };
        timer1 = new Timer(45, al);
        timer1.start();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }


	public static void main(String[] args) {
		
		new Main();
	}

	
}

