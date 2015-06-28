package MyGameColumns;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyGameColumns {
	protected static final Color[] COLORS = { Color.black, Color.cyan,
			Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.black };

	public static void main(String[] args) {

		Model model = new Model();

		final Controller controller = new Controller();

		model.addListener(controller);

		View view = new View();

		controller.setView(view);
		controller.setModel(model);

		JFrame frame = new JFrame("MyColumns");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(400, 750));

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					controller.moveLeft();

					break;
				case KeyEvent.VK_RIGHT:
					controller.moveRight();
					break;

				case KeyEvent.VK_UP:
					controller.cyclePositionUp();
					break;

				case KeyEvent.VK_DOWN:
					controller.cyclePositionDown();
					break;

				case KeyEvent.VK_SPACE:
					controller.dropDown();
					break;

				case KeyEvent.VK_P:
					// TODO
					break;

				default:
					break;
				}
			}
		});

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					controller.moveDown();
				}
			}
		});
		thread.setDaemon(true);
		thread.start();

		frame.add(panel);

		frame.pack();

		frame.setVisible(true);

		final Graphics2D graphics = (Graphics2D) panel.getGraphics();

		view.setGraphics(new Graphics() {

			@Override
			public void fillRect(int x, int y, int width, int height,
					int colorIndex) {
				graphics.setColor(COLORS[colorIndex]);
				graphics.fillRect(x, y, width, height);

			}

			@Override
			public void drawString(String string, int x, int y) {
				graphics.setColor(Color.RED);
				graphics.clearRect(x, y-20, 100, 20);
				graphics.setFont(new Font(Font.SERIF, Font.BOLD, 15));
				graphics.drawString(string, x, y);
				
			}
		});

	}
}
