package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import network.Student;
import view.FrameHome;

public class Controller implements ActionListener {

	private Student student;
	private FrameHome frameHome;
	private Timer timer;

	public Controller() {
		connect();
		frameHome = new FrameHome(this);
		startTimer();
	}

	private void startTimer() {
		timer = new Timer(ConstantList.TIMER_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameHome.setNotifyNum(student.notifications());
			}
		});
		timer.start();
	}

	private void connect() {
		String ip = JOptionPane.showInputDialog("Ingrese la IP del servidor");
		try {
			student = new Student(ip);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sendMessage() {
		try {
			String message = frameHome.getMessage();
			if (!message.equals("")) {
				student.sendMessage(message);
			}
			frameHome.loadMessages(student.getMessages());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showNotify() {
		frameHome.setNotifyNum(0);
		student.changeNotifyState();
		frameHome.panelNotifies(student.getNotifications());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case CHANGE_IP:
			connect();
			break;
		case COMMAND_MESSAGE:
			sendMessage();
			break;
		case COMMAND_SHOW_NOTIFY:
			showNotify();
			break;
		case COMMAND_CLOSE_NOTIFY:
			frameHome.removePanelNotify();
			break;
		}
	}
}