/**
 * 
 */
package org.ElectroLight.Explorer;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.ElectroLight.Explorer.ui.FileView;

/**
 * @author Vincent
 *
 */
public class Main {
	public static final ImageIcon FOLDER_IMG = new ImageIcon("./famfamfam_silk_icons_v013/icons/folder.png");
	public static final ImageIcon NO_ICON_IMG = new ImageIcon("./famfamfam_silk_icons_v013/icons/page_white_text.png");
	public static final ImageIcon IMG_IMG = new ImageIcon("./famfamfam_silk_icons_v013/icons/photo.png");
	
	public static final Properties SETTINGS = new Properties();

	public static void main(String[] args) {
		try {
			SETTINGS.load(new FileInputStream(new File("./settings")));
			
			JFrame f = new JFrame("ElectroLight Explorer");
			JScrollPane scroll = new JScrollPane();
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			f.add(scroll);
			scroll.setViewportView(new FileView(new File("C:/")));
			f.pack();
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
