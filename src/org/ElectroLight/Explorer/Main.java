/**
 * 
 */
package org.ElectroLight.Explorer;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.ElectroLight.Explorer.ui.FileView;

/**
 * @author Vincent
 *
 */
public class Main {
	public static final ImageIcon FOLDER_IMG = new ImageIcon("./famfamfam_silk_icolns_v013/icons/folder.png");
	public static final ImageIcon NO_ICON_IMG = new ImageIcon("./famfamfam_silk_icons_v013/icons/page_white_text.png");
	public static final ImageIcon IMG_IMG = new ImageIcon("./famfamfam_silk_icons_v013/icons/photo.png");

	public static final Properties SETTINGS = new Properties();
	
	private static JTextField addressBar;
	private static FileView fileView;
	
	public static void main(String[] args) {
		try {
			SETTINGS.load(new FileInputStream(new File("./settings")));
			if (SETTINGS.getProperty("debug").equals("true")) {
				Logger.getLogger("Debug").setLevel(Level.ALL);
				if (!Logger.getLogger("Debug").isLoggable(Level.ALL)) {
					System.err.println("Debug is enabled but logger isn't.");
					System.exit(-1);
				}
			}

			JFrame f = new JFrame("ElectroLight Explorer");
			JScrollPane scroll = new JScrollPane();
			fileView = new FileView(File.listRoots()[0]);
			fileView.setFocusable(true);
			fileView.setFocusCycleRoot(true);
			addressBar = new JTextField(File.listRoots()[0].getPath());
			addressBar.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {

				}

				@Override
				public void keyReleased(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							Logger.getLogger("Debug").fine("Enter pressed in adr bar");
							fileView.setFolder(new File(addressBar.getText()));
							fileView.loadFiles();
							fileView.repaint();
						}
					} catch (IOException exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getName(),
								JOptionPane.ERROR_MESSAGE);
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub

				}
			});
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			f.setLayout(new BorderLayout());
			f.add(scroll, BorderLayout.CENTER);
			f.add(addressBar, BorderLayout.NORTH);
			scroll.setViewportView(fileView);
			f.setSize(1000, 1000);
			f.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
				}

				@Override
				public void windowIconified(WindowEvent e) {
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
				}

				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}

				@Override
				public void windowClosed(WindowEvent e) {
				}

				@Override
				public void windowActivated(WindowEvent e) {
				}
			});
			f.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
