package org.ElectroLight.Explorer.ui;

import java.io.File;
import java.net.URI;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class StatsView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2014740153079511776L;
	private URI uri;
	private StatsViewType type;

	public StatsView(URI uri, StatsViewType type) {
		this.uri = uri;
		this.type = type;
		if (this.type == StatsViewType.FILE_SIZES) {
			DefaultPieDataset data = new DefaultPieDataset();
			for (File f : new File(this.uri).listFiles()) {
				data.setValue(f.getName(), f.length());
			}
			JFreeChart chart = ChartFactory.createPieChart3D("File sizes in folder \"" + uri.getPath() + "\"",
					(PieDataset) data, true, true, Locale.getDefault());
			add(new ChartPanel(chart));
			pack();
			setVisible(true);
		} else if (this.type == StatsViewType.EXTENSIONS) {
			Hashtable<String, Integer> tbl = new Hashtable<String, Integer>();
			DefaultPieDataset data = new DefaultPieDataset();
			for (File f : new File(this.uri).listFiles()) {
				try {
					tbl.put(f.getName().substring(f.getName().lastIndexOf(".")),
							tbl.get(f.getName().substring(f.getName().lastIndexOf("."))) + 1);
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("StringIndexOutOfBoundsException caught");
				}
			}

			for (String str : tbl.keySet()) {
				data.setValue(str, tbl.get(str));
			}
			JFreeChart chart = ChartFactory.createPieChart3D("File extensions in folder \"" + uri.getPath() + "\"",
					(PieDataset) data, true, true, Locale.getDefault());
			add(new ChartPanel(chart));
			pack();
			setVisible(true);
		}
	}
}
