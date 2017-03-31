/**
 * 
 */
package org.ElectroLight.Explorer.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.ElectroLight.Explorer.Main;

/**
 * @author Vincent
 *
 */
@SuppressWarnings("serial")
public class FileView extends JComponent implements ImageObserver {

	private ViewType viewType = ViewType.ICONS;

	private List<Icon> icons = new ArrayList<Icon>();

	private File folder;

	public FileView(File folder) throws IOException {
		super();
		this.setFolder(folder);
		if (!folder.isDirectory()) {
			throw new IOException("Given file isn't a folder.");
		}
		loadFiles();
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1)
					return;
				Logger.getLogger("Debug").fine("X: " + e.getX() + ", Y: " + e.getY());
			}
		});

	}

	public void loadFiles() throws IOException {
		if (!folder.isDirectory()) {
			throw new IOException("Given file isn't a folder.");
		}
		
		icons = new ArrayList<Icon>();
		for (File f : folder.listFiles()) {
			Icon i = new Icon();
			i.setName(f.getName());
			if (f.isDirectory()) {
				i.setIcon(Main.FOLDER_IMG);
			} else if (f.getName().endsWith(".png") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg")
					|| f.getName().endsWith(".bmp") || f.getName().endsWith(".raw")) {
				i.setIcon(Main.IMG_IMG);
			} else {
				i.setIcon(Main.NO_ICON_IMG);
			}
			icons.add(i);
		}
	}
	
	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 15;
		int y = 15;
		for (Icon i : icons) {
			Logger.getLogger("Debug").fine(
					"Drawing icon with image " + i.getIcon().toString() + " and with name \"" + i.getName() + "\"");
			g.setColor(Color.black);
			g.drawString(i.getName(), x + 16, y + 10);
			g.drawImage(i.getIcon().getImage(), x, y, 16, 16, Color.WHITE, this);
			x += 0;
			y += 20;
		}
		setSize(y, x);
	}

	public enum ViewType {
		DETAILS, ICONS
	}

	public class Icon {
		private String name;
		private ImageIcon icon;
		private HashMap<String, Object> properties = new HashMap<String, Object>();

		Icon() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ImageIcon getIcon() {
			return icon;
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

		public HashMap<String, Object> getProperties() {
			return properties;
		}
	}
}
