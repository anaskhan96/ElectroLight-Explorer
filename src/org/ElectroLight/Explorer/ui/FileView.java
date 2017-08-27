/**
 * 
 */
package org.ElectroLight.Explorer.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
		setFocusable(true);
		
		this.setFolder(folder);
		if (!folder.isDirectory()) {
			throw new IOException("Given file isn't a folder.");
		}
		loadFiles();
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				Logger.getLogger("Debug").info("Key typed: '" + e.getKeyChar() + "'");
				if (e.getKeyChar() == 'd') {
					JPopupMenu pop = new JPopupMenu();
					JMenuItem itmSizesStats = new JMenuItem("Files sizes statistics");
					itmSizesStats.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							new StatsView(getFolder().toURI(), StatsViewType.FILE_SIZES);
						}
					});
					pop.add(itmSizesStats);
					
					JMenuItem itmExtStats = new JMenuItem("Extentions statistics");
					itmExtStats.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							new StatsView(getFolder().toURI(), StatsViewType.EXTENSIONS);
						}
					});
					pop.add(itmExtStats);
					
					pop.show(e.getComponent(), e.getComponent().getX(), e.getComponent().getY());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Logger.getLogger("Debug").info("mousePressed | BTN: " + e.getButton() + "X,Y" + e.getX() + "," + e.getY());
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
				Logger.getLogger("Debug").info("mouseClicked | BTN: " + e.getButton() + "X,Y" + e.getX() + "," + e.getY());
				if (e.getButton() == MouseEvent.BUTTON2) {
					JPopupMenu pop = new JPopupMenu();
					JMenuItem itmStats = new JMenuItem("Statistics");
					itmStats.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							new StatsView(getFolder().toURI(), StatsViewType.FILE_SIZES);
						}
					});
					pop.add(itmStats);
					pop.show(e.getComponent(), e.getXOnScreen(), e.getYOnScreen());
				}
				Logger.getLogger("Debug").fine("X: " + e.getX() + ", Y: " + e.getY());
			}
		});

	}

	public void loadFiles() throws IOException {
		Logger.getLogger("Debug").info("(Re)loading files");
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
