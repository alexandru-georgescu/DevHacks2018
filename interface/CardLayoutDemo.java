package interface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;
import java.net.URI;

class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(500, 500);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}

public class CardLayoutDemo implements ItemListener {
	JPanel cards; //a panel that uses CardLayout
	final static String BUTTONPANEL = "Card with JButtons";
	final static String TEXTPANEL = "Card with JTextField";
	final static String REVIEWPANEL = "Card with Review";
	private JTextField card_id;
	private JTextField supplier;
	private JTextField star;
	private JTextArea review;

	public void addComponentToPane(Container pane) {

		//Create the "cards".
		JPanel card1 = new JPanel();
		JButton btnReview = new JButton("");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, REVIEWPANEL);
			}
		});
		
		ImagePanel panelIn = new ImagePanel(new ImageIcon("b.jpg").getImage());
		
		
		JButton exitReview = new JButton("");
		exitReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, BUTTONPANEL);
			}
		});
		
		JButton exitVisit = new JButton("X");
		exitVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, BUTTONPANEL);
			}
		});
		JButton btnVisit = new JButton("");
		btnVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, TEXTPANEL);
			}
		});
		
		btnReview.setBounds(50, 320, 200, 25);
		btnReview.setOpaque(false);
		btnReview.setContentAreaFilled(false);
		btnReview.setBorderPainted(false);
		
		btnVisit.setBounds(250, 320, 200, 25);
		btnVisit.setOpaque(false);
		btnVisit.setContentAreaFilled(false);
		btnVisit.setBorderPainted(false);
		panelIn.add(btnReview);
		panelIn.add(btnVisit);

		card1.add(panelIn);
		card1.setVisible(true);


		JPanel card2 = new JPanel();
		JLabel website = new JLabel();
		website.setText("<html> Website : <a href=\"\">http://www.google.com/</a></html>");
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		website.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.google.com/webhp?nomo=1&hl=fr"));
				} catch (URISyntaxException | IOException ex) {
					//It looks like there's a problem
				}
			}
		});
		exitVisit.setBounds(470, 10, 30, 30);
		
		card2.add(website);
		card2.add(exitVisit);
		

		JPanel card3 = new JPanel();
		
		card_id = new JTextField();
		card_id.setBounds(192, 171, 270, 29);
		card_id.setVisible(true);
		card_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		supplier = new JTextField();
		supplier.setBounds(192, 222, 270, 29);
		supplier.setVisible(true);
		supplier.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		star = new JTextField();
		star.setBounds(192, 277, 270, 29);
		star.setVisible(true);
		star.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
	
		review = new JTextArea();
		review.setBounds(192, 330, 270, 93);
		review.setVisible(true);
		review.setOpaque(false);
		
		
		
		ImagePanel panel = new ImagePanel(new ImageIcon("a.jpg").getImage());
		exitReview.setBounds(470, 10, 30, 30);
		exitReview.setOpaque(false);
		exitReview.setContentAreaFilled(false);
		exitReview.setBorderPainted(false);
		
		JButton submitBtn = new JButton("");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(card_id.getText().isEmpty()||(supplier.getText().isEmpty())||(star.getText().isEmpty())||(review.getText().isEmpty()))
					JOptionPane.showMessageDialog(null, "Data Missing");
				else		
					JOptionPane.showMessageDialog(null, "Data Submitted");
			}
		});
		
		
		
		submitBtn.setBounds(45, 453, 145, 30);
		submitBtn.setOpaque(false);
		submitBtn.setContentAreaFilled(false);
		submitBtn.setBorderPainted(false);
		
		JButton clearBtn = new JButton("");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card_id.setText(null);
				supplier.setText(null);
				star.setText(null);
				review.setText(null);
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});
		
		clearBtn.setBounds(315, 453, 145, 30);
		clearBtn.setOpaque(false);
		clearBtn.setContentAreaFilled(false);
		clearBtn.setBorderPainted(false);
		
		panel.add(exitReview);
		panel.add(card_id);
		panel.add(supplier);
		panel.add(star);
		panel.add(review);
		panel.add(submitBtn);
		panel.add(clearBtn);
		card3.add(panel);
		card3.setVisible(true);

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, BUTTONPANEL);
		cards.add(card2, TEXTPANEL);
		cards.add(card3, REVIEWPANEL);

		//pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
		System.out.println("aaa" + (String)evt.getItem());
		cl.show(cards, (String)evt.getItem());
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(new Dimension(500, 540));

		//Create and set up the content pane.
		CardLayoutDemo demo = new CardLayoutDemo();
		demo.addComponentToPane(frame.getContentPane());
		//frame.getContentPane().setLayout();

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
