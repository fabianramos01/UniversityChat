package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Command;
import controller.ConstantList;

public class ButtonNotify extends JButton {

	private static final long serialVersionUID = 1L;
	private int notifyNum;

	public ButtonNotify(ActionListener listener) {
		addActionListener(listener);
		setFont(ConstantList.AGENCY_FB);
		setOpaque(false);
		setContentAreaFilled(false);
		setText(Command.COMMAND_SHOW_NOTIFY.getTitle());
		setActionCommand(Command.COMMAND_SHOW_NOTIFY.getCommand());
	}
	
	public void setNotifyNum(int notifyNum) {
		this.notifyNum = notifyNum;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(ConstantList.APP_COLOR);
		g.fillOval(60, 0, ConstantList.SIZE_CIRCLE, ConstantList.SIZE_CIRCLE);
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(notifyNum), 77, 38);
	}
}