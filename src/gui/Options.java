package gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Main.SaveLoad;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Options extends JFrame {
	private static final long serialVersionUID = 1L;
	private ImageIcon         iconSoundEnable  = new ImageIcon(getClass().getResource("/Resources/Images/SoundEnable.PNG"));
	private ImageIcon         iconSoundMute    = new ImageIcon(getClass().getResource("/Resources/Images/SoundMute.PNG"));
	private boolean           soundEnabled     = true;
	private JPanel            contentPane;
	private JLabel            lblSound;
	private JButton           btnSound, btnMusic1, btnMusic2;
	public int                autoCloseDelay   = 3;
	private static int		  selectedFile = 0;
	public static JButton fileOne,fileTwo,fileThree,fileFour;
	public static String fileOneName,fileTwoName,fileThreeName,fileFourName;

	public Options() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlSaveLoad = new JPanel();
		pnlSaveLoad
		.setBorder(new TitledBorder(null, "Save & Load", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSaveLoad.setBounds(10, 16, 252, 71);
		contentPane.add(pnlSaveLoad);
		pnlSaveLoad.setLayout(null);

		JLabel lblLoadProtection = new JLabel("<html> After loading a game <br> "
				+ "there will be a  <br>  4 round of LoadProtection </html>");
		lblLoadProtection.setHorizontalAlignment(SwingConstants.CENTER);
		if (Main.Main.loadProtection) lblLoadProtection.setText(" Load Protection ");
		lblLoadProtection.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoadProtection.setBounds(272, 16, 153, 71);
		contentPane.add(lblLoadProtection);
		setBounds(100, 100, 451, 480);



		// Save Load

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 16, 112, 45);
		btnSave.addActionListener(al -> {
			SaveLoad.save();
		});
		pnlSaveLoad.add(btnSave);

		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(130, 16, 112, 45);
		btnLoad.addActionListener(al -> {
			SaveLoad.load();
			btnLoad.setEnabled(false);
		});
		btnLoad.setEnabled(!Main.Main.loadProtection);
		pnlSaveLoad.add(btnLoad);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 98, 415, 2);
		contentPane.add(separator);



		// Sound

		btnSound = new JButton(iconSoundEnable);
		btnSound.setBounds(25, 111, 60, 70);
		contentPane.add(btnSound);
		btnSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sounds silenced.");
				if (soundEnabled) {
					Main.Main.stopMusic();
					muteMode();
				}
				else {
					Main.Main.startMusic();
					playMode();
				}
			}
		});
		btnSound.setContentAreaFilled(false);
		btnSound.setFocusable(false);
		btnSound.setOpaque(false);

		lblSound = new JLabel("");
		lblSound.setHorizontalAlignment(SwingConstants.CENTER);
		lblSound.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSound.setBounds(105, 111, 137, 70);
		contentPane.add(lblSound);

		btnMusic1 = new JButton("Music 1");
		btnMusic1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Main.changeMusic(1);
				playMode();
			}
		});
		btnMusic1.setBounds(272, 111, 89, 33);
		btnMusic1.setVisible(false);
		contentPane.add(btnMusic1);

		btnMusic2 = new JButton("Music 2");
		btnMusic2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.changeMusic(2);
				playMode();
			}
		});
		btnMusic2.setBounds(272, 148, 89, 33);
		btnMusic2.setVisible(false);
		contentPane.add(btnMusic2);



		// file select
		JPanel filePanel = new JPanel();
		filePanel
		.setBorder(new TitledBorder(null, "Select file to load", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filePanel.setBounds(10, 230, 300, 180);
		contentPane.add(filePanel);
		filePanel.setLayout(null);

		if(fileOneName == null) 
			fileOneName = "File 1";
		fileOne = new JButton(fileOneName);
		fileOne.setBounds(10, 16, 100, 30);
		fileOne.addActionListener(al -> {
			selectedFile = 0;
		});

		if(fileTwoName == null) 
			fileTwoName = "File 2";
		fileTwo = new JButton(fileTwoName);
		fileTwo.setBounds(10, 56, 100, 30);
		fileTwo.addActionListener(al -> {
			selectedFile = 1;
		});

		if(fileThreeName == null) 
			fileThreeName = "File 3";
		fileThree = new JButton(fileThreeName);
		fileThree.setBounds(10, 96, 100, 30);
		fileThree.addActionListener(al -> {
			selectedFile = 2;
		});

		if(fileFourName == null) 
			fileFourName = "File 4";
		fileFour = new JButton(fileFourName);
		fileFour.setBounds(10, 136, 100, 30);
		fileFour.addActionListener(al -> {
			selectedFile = 3;
		});

		filePanel.add(fileOne);
		filePanel.add(fileTwo);
		filePanel.add(fileThree);
		filePanel.add(fileFour);

	}

	public static int getFile(){
		return selectedFile;
	}

	public void playMode() {
		soundEnabled = true;
		lblSound.setText("Music playing. Enjoy :)");
		btnSound.setIcon(iconSoundEnable);
		btnMusic1.setVisible(false);
		btnMusic2.setVisible(false);
	}

	public void muteMode() {
		soundEnabled = false;
		lblSound.setText("Muted");
		btnSound.setIcon(iconSoundMute);
		btnMusic1.setVisible(true);
		btnMusic2.setVisible(true);
	}
}
