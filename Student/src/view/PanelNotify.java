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
import model.Notify;

public class PanelNotify extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> wordList;
	private DefaultListModel<String> listModel;

	public PanelNotify() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		wordList = new JList<>();
		wordList.setBackground(Color.GRAY);
		add(new JScrollPane(wordList), BorderLayout.CENTER);
	}

	public void loadMessages(ArrayList<Notify> messages) {
		listModel = new DefaultListModel<>();
		for (Notify notify : messages) {
			listModel.addElement(notify.getMessage());
		}
		wordList.setFont(ConstantList.AGENCY_FB);
		wordList.setModel(listModel);
		revalidate();
	}
}