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
		frameHome = new FrameHome(this);
		connect();
		
		startTimer();
	}

	private void startTimer() {
		timer = new Timer(ConstantList.TIMER_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (student != null) {
					frameHome.setNotifyNum(student.notifications());					
				}
			}
		});
		timer.start();
	}

	private void connect() {
		frameHome.setVisible(false);
		String ip = JOptionPane.showInputDialog(ConstantList.GET_IP);
		String port = JOptionPane.showInputDialog(ConstantList.GET_PORT);
		if (!port.equals("")) {
			newStudent(ip, Integer.parseInt(port));
		} else {
			frameHome.withoutConn();
			JOptionPane.showMessageDialog(null, ConstantList.PORT_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);			
		}	
		frameHome.setVisible(true);
	}
	
	private void newStudent(String ip, int port) {
		try {
			student = new Student(ip, port);
			frameHome.whitConn();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
			frameHome.withoutConn();
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