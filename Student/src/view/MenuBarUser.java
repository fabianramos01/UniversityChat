package view;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.Command;
import controller.ConstantList;

public class MenuBarUser extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	private JMenu userOption;
	private JMenuItem newUser;
	
	public MenuBarUser(ActionListener listener) {
		userOption = new JMenu(ConstantList.CONNECTION_MENU);
		newUser = new JMenuItem(Command.CHANGE_IP.getTitle());
		newUser.addActionListener(listener);
		newUser.setActionCommand(Command.CHANGE_IP.getCommand());
		newUser.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		userOption.add(newUser);
		add(userOption);
	}
}