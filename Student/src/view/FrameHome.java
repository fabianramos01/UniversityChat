package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Command;
import controller.ConstantList;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelMessage;
	private PanelMessage panelMessage;

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
	}

	private void loadPanelMessage(ActionListener listener) {
		JPanel panelMessage = new JPanel(new GridLayout(1, 2));
		labelMessage = new JLabel();
		labelMessage.setFont(ConstantList.AGENCY_FB);
		panelMessage.add(labelMessage);
		panelMessage.add(UtilityList.createJButtonText(Command.COMMAND_MESSAGE.getCommand(),
				Command.COMMAND_MESSAGE.getTitle(), Color.BLACK, Color.WHITE, ConstantList.AGENCY_FB, listener));
		add(panelMessage, BorderLayout.NORTH);
	}
	
	public void loadMessages(ArrayList<String> messages) {
		panelMessage.loadMessages(messages);
		revalidate();
	}

	public String getMessage() {
		return labelMessage.getText();
	}
}