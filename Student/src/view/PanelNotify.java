package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Command;
import controller.ConstantList;
import model.Notify;

public class PanelNotify extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> wordList;
	private DefaultListModel<String> listModel;

	public PanelNotify(ActionListener listener) {
		setLayout(new BorderLayout());
		addButtonClose(listener);
		wordList = new JList<>();
		wordList.setFont(ConstantList.AGENCY_FB);
		wordList.setBackground(Color.BLACK);
		wordList.setForeground(ConstantList.APP_COLOR);
		add(new JScrollPane(wordList), BorderLayout.CENTER);
	}

	private void addButtonClose(ActionListener listener) {
		JPanel panelButton = new JPanel(new BorderLayout());
		panelButton.setBackground(Color.GRAY);
		panelButton.add(
				UtilityList.createJButton(Command.COMMAND_CLOSE_NOTIFY.getCommand(),
						Command.COMMAND_CLOSE_NOTIFY.getTitle(), Command.COMMAND_CLOSE_NOTIFY.getImg(), listener),
				BorderLayout.EAST);
		add(panelButton, BorderLayout.NORTH);
	}

	public void loadMessages(ArrayList<Notify> messages) {
		listModel = new DefaultListModel<>();
		for (Notify notify : messages) {
			listModel.addElement(notify.getMessage());
		}
		wordList.setModel(listModel);
		revalidate();
	}
}