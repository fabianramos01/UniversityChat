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
	private JTextField fieldMessage;
	private PanelMessage panelMessage;
	private PanelNotify panelNotifies;
	private ButtonNotify buttonNotify;

	public FrameHome(ActionListener listener) {
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		init(listener);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void init(ActionListener listener) {
		setJMenuBar(new MenuBarUser(listener));
		loadPanelMessage(listener);
		panelMessage = new PanelMessage();
		add(panelMessage, BorderLayout.CENTER);
		buttonNotify = new ButtonNotify(listener);
		add(buttonNotify, BorderLayout.SOUTH);
	}

	private void loadPanelMessage(ActionListener listener) {
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
		revalidate();
	}

	public void panelNotifies(ArrayList<Notify> messages) {
		panelNotifies = new PanelNotify();
		panelNotifies.loadMessages(messages);
		add(panelNotifies, BorderLayout.EAST);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public String getMessage() {
		return fieldMessage.getText();
	}
}