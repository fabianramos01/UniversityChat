package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.Command;
import controller.ConstantList;
import model.Notify;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private ActionListener listener;
	private JTextField fieldMessage;
	private PanelMessage panelMessage;
	private PanelNotify panelNotifies;
	private ButtonNotify buttonNotify;

	public FrameHome(ActionListener listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void init() {
		setJMenuBar(new MenuBarUser(listener));
		loadPanelMessage();
		panelMessage = new PanelMessage();
		add(panelMessage, BorderLayout.CENTER);
		buttonNotify = new ButtonNotify(listener);
		add(buttonNotify, BorderLayout.SOUTH);
	}

	private void loadPanelMessage() {
		JPanel panelMessage = new JPanel(new GridLayout(1, 2));
		fieldMessage = new JTextField();
		fieldMessage.setFont(ConstantList.AGENCY_FB);
		panelMessage.add(fieldMessage);
		panelMessage.add(
				UtilityList.createJButtonText(Command.COMMAND_MESSAGE.getCommand(), Command.COMMAND_MESSAGE.getTitle(),
						Color.BLACK, ConstantList.APP_COLOR, ConstantList.AGENCY_FB, listener));
		add(panelMessage, BorderLayout.NORTH);
	}

	public void setNotifyNum(int num) {
		buttonNotify.setNotifyNum(num);
	}

	public void loadMessages(ArrayList<String> messages) {
		panelMessage.loadMessages(messages);
		fieldMessage.setText("");
		revalidate();
	}

	public void panelNotifies(ArrayList<Notify> messages) {
		if (panelNotifies != null) {
			this.remove(panelNotifies);
		}
		panelNotifies = new PanelNotify(listener);
		panelNotifies.loadMessages(messages);
		add(panelNotifies, BorderLayout.EAST);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void withoutConn() {
		fieldMessage.setText(ConstantList.OUT_CONN);
		fieldMessage.setForeground(Color.RED);
		fieldMessage.setEditable(false);
	}
	
	public void whitConn() {
		fieldMessage.setText("");
		fieldMessage.setForeground(Color.BLACK);
		fieldMessage.setEditable(true);
	}
	
	public void removePanelNotify() {
		remove(panelNotifies);
		SwingUtilities.updateComponentTreeUI(this);		
	}

	public String getMessage() {
		return fieldMessage.getText();
	}
}