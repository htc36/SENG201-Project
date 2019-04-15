package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;

public class CrewStatus {

	private JFrame frmCrewStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrewStatus window = new CrewStatus();
					window.frmCrewStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrewStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrewStatus = new JFrame();
		frmCrewStatus.setTitle("Crew Status");
		frmCrewStatus.setBounds(100, 100, 800, 600);
		frmCrewStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrewStatus.getContentPane().setLayout(new BoxLayout(frmCrewStatus.getContentPane(), BoxLayout.X_AXIS));
	}

}
