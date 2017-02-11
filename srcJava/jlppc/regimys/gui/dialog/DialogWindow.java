package jlppc.regimys.gui.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import jlppc.regimys.core.GameState;
import jlppc.utils.Fonts;
import jlppc.utils.Log;
import jlppc.utils.Log.Entry;
import jlppc.utils.Fonts.Police;
import jlppc.utils.FormattedString;

public final class DialogWindow extends JInternalFrame {
	JLabel text;
	static JPanel defaultPanel;
	public DialogWindow() {
		super();
		Log.writeT(Entry.INFO, "Création de la fenetre de dialogue.");
		defaultPanel = new JPanel();
		defaultPanel.setLayout(null);
		this.setContentPane(defaultPanel);
		text = new JLabel("");
		text.setBounds(10, 11, 476, 98);
		defaultPanel.add(text);
		text.setFont(Fonts.font(Police.ARIAL, 20));
		text.setVerticalTextPosition(JLabel.TOP);
		text.setHorizontalTextPosition(JLabel.LEADING);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 496, 120);
		defaultPanel.add(background);
		BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
		bi.setNorthPane(null);
		
		
	}
	
	public static JPanel getDefaultPanel(){
		return defaultPanel;
	}
	
	public void continuer(){
		if(GameState.state == GameState.DIALOGUE){
			text.setText("");
			GameState.state = GameState.MARCHE;	
			n();
		}
		
	}
	
	public synchronized void n(){
		notify();
	}
	
	public synchronized void printText(String text){
		setContentPane(defaultPanel);
		repaint();
		this.text.setText("<HTML>" + text + "</HTML>");
		GameState.state = GameState.DIALOGUE;
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public synchronized void printText(String text, Object...objects){
		printText(new FormattedString(text, objects).toString());
	}
	
	public synchronized int printQuestion(String question, String choix1, String choix2, String choix3){
		ChoicePanel cp = new ChoicePanel("<HTML>" + question + "</HTML>", choix1, choix2, choix3, (choix3 != null));
		setContentPane(cp);
		repaint();
		GameState.state = GameState.QUESTION;
		while(cp.getChoice() == -1){
			System.out.print("");
		}
		return cp.getChoice();
	}
	
	
}