package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class FenetreSerpent extends JFrame {
	/** Serial UUID. */
	private static final long serialVersionUID = 1L;

	/** Le serpent affich�. */
	private Serpent serpent;

	/** Le panel. */
	private PanelSerpent panel = new PanelSerpent();
	
	/** La timer. */
	private Timer timer;
	
	/** L'action d'avancement. */
	private ActionListener action = (e) -> {
		timer.setDelay(serpent.getDelay());
		serpent.avancer();
		panel.repaint();
	};

	/** Constructeur de la fen�tre. */
	public FenetreSerpent() {
		serpent = new Serpent(20, 20);
		setJMenuBar(new MenuSerpent());
		add(panel);
		setMinimumSize(new Dimension(700, 700));
		addActionListener();
		addTimer();
	}

	private void addTimer() {
		timer = new Timer(serpent.getDelay(), action);
		action = (e) -> {
			timer.setDelay(serpent.getDelay());
			serpent.avancer();
			panel.repaint();
		};
		timer.start();
	}

	private void addActionListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					serpent.changerDirection(Direction.Gauche);
					break;
				case KeyEvent.VK_RIGHT:
					serpent.changerDirection(Direction.Droite);
					break;
				case KeyEvent.VK_UP:
					serpent.changerDirection(Direction.Haut);
					break;
				case KeyEvent.VK_DOWN:
					serpent.changerDirection(Direction.Bas);
					break;
				}
			}
		});
	}

	private class PanelSerpent extends JPanel {
		/** Serial UUID. */
		private static final long serialVersionUID = 1L;

		public PanelSerpent() {
		}

		@Override
		public void paint(Graphics g) {
			SerpentUtilitaires.dessiner(serpent, g, getWidth(), getHeight());
		}
	}

	private class MenuSerpent extends JMenuBar {
		/** Serial UUID. */
		private static final long serialVersionUID = 1L;

		public MenuSerpent() {
			add(construireMenuNouvellePartie());
		}

		private JMenuItem construireMenuNouvellePartie() {
			JMenuItem res = new JMenuItem("Nouvelle partie");
			res.addActionListener((e) -> {
				serpent = new Serpent(20, 20);
				SwingUtilities.invokeLater(() -> {
					panel.repaint();
				});
			});
			return res;
		}
	}
}
