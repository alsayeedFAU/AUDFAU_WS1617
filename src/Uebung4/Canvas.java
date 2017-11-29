import java.awt.Color;

public class Canvas {
	public final Color[][] array;
	private int totalSteps;
	private Runnable repaint;

	// no need to understand this code - it creates your canvas
	public Canvas(final int x, final int y, int totalSteps) {
		this.totalSteps = totalSteps;
		this.array = new Color[x][y];
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					final double pSize = 1;
					final String title = new String("Canvas");
					// create window
					final javax.swing.JFrame mainFrame = new javax.swing.JFrame();

					// create panel that displays the array as colored areas
					final javax.swing.JPanel colorPanel = new javax.swing.JPanel() {
						private static final long serialVersionUID = 1L;

						@Override
						protected void paintComponent(java.awt.Graphics g) {
							super.paintComponent(g);

							final double xFac = ((double) getSize().width) / x;
							final double yFac = ((double) getSize().height) / y;
							for (int i = 0; i < x; i++) {
								for (int j = 0; j < y; j++) {
									boolean unset = true;
									unset = (array[i][j] == null);
									g.setColor(unset ? Color.WHITE : array[i][j]);

									// paint area
									g.fillPolygon(new int[] { (int) (i * xFac), (int) (i * xFac), (int) ((i + 1) * xFac), (int) ((i + 1) * xFac) }, new int[] { (int) (j * yFac), (int) ((j + 1) * yFac), (int) ((j + 1) * yFac), (int) (j * yFac) }, 4);
								}
							}
						}
					};
					colorPanel.setPreferredSize(new java.awt.Dimension((int) (pSize * x), (int) (pSize * y)));
					mainFrame.setContentPane(colorPanel);
					mainFrame.setTitle(title);
					mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

					// display window
					mainFrame.pack();
					mainFrame.setLocationRelativeTo(null);
					mainFrame.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
					mainFrame.setVisible(true);

					// repaint window
					repaint = new Runnable() {
						public void run() {
							mainFrame.repaint();
						}
					};
				}
			});
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/* draws a pixel at pos x,y in color color */
	private boolean colorize(final int x, final int y, final Color color) {
		if (x < 0 || x >= array.length || y < 0 || y >= array[0].length) {
			// don't draw outside of canvas
			return false;
		}
		array[x][array[0].length - 1 - y] = color;
		javax.swing.SwingUtilities.invokeLater(repaint);
		return true;
	}

	private Color curColor = Color.BLUE;

	// draws a line from (x,y) to (x2,y2)
	// <steps> is used to draw with a nice color
	public void drawLine(int x, int y, int x2, int y2, int steps) {
		// Bresenham
		int dx = Math.abs(x2 - x);
		int dy = Math.abs(y2 - y);
		int sx, sy;

		if (x < x2) {
			sx = 1;
		} else {
			sx = -1;
		}
		if (y < y2) {
			sy = 1;
		} else {
			sy = -1;
		}

		int err = dx - dy;

		int bright = (int) ((255.0 * (totalSteps - steps)) / totalSteps);
		bright = Math.min(bright, 230);
		curColor = new Color(bright, bright, 255);

		while (true) {
			if (!colorize(x, y, curColor)) {
				break;
			}
			if (x == x2 && y == y2) {
				break;
			}

			int e2 = 2 * err;
			if (e2 > -dy) {
				err -= dy;
				x += sx;
			}
			if (e2 < dx) {
				err += dx;
				y += sy;
			}
		}
	}

	public String toString() {
		int cnt = 0;
		int what = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				int next = (array[i][j] != null && !array[i][j].equals(Color.WHITE)) ? 1 : 0;
				if (next != what) {
					sb.append(cnt);
					// sb.append(",");
					what = next;
					cnt = 0;
				}
				cnt++;
			}
		}
		sb.append(cnt);
		return sb.toString();
	}
}
