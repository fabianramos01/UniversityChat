package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ConstantList;

public class PanelMessage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> wordList;
	private DefaultListModel<String> listModel;

	public PanelMessage() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		wordList = new JList<>();
		add(new JScrollPane(wordList), BorderLayout.CENTER);
	}

	public void loadMessages(ArrayList<String> messages) {
		listModel = new DefaultListModel<>();
		for (String string : messages) {
			listModel.addElement(string);
		}
		wordList.setFont(ConstantList.AGENCY_FB);
		wordList.setModel(listModel);
		revalidate();
	}
}