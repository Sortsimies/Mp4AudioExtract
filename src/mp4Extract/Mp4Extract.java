package mp4Extract;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Mp4Extract {

	private JFrame frame;
	private String mp4path;
	private String mp4file;
	private String m4apath;
	private String m4afile;
	JLabel lblFile;
	JLabel lblFile_1;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mp4Extract window = new Mp4Extract();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mp4Extract() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MP4 to M4A");
		
		JButton btnMpSource = new JButton("mp4 Source");
		btnMpSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
//				fd.setDirectory("F:\\Files\\Music\\YouTube");
				fd.setFile("*.mp4");
				fd.setMultipleMode(true);
				fd.setVisible(true);
				
				if (fd.getFiles().length>1){
					File[] files = fd.getFiles();
					int n = JOptionPane.showConfirmDialog(
						    frame,
						    "Convert all (DANGEROUSLY DANGEROUS)?",
						    "?",
						    JOptionPane.YES_NO_OPTION);
					if (n==JOptionPane.YES_OPTION) {
						for(int i=0;i<files.length;i++){
							String eka = files[i].getPath();
							String toka = eka.replace(".mp4", ".m4a");
							convert(eka, toka);
						}
						JOptionPane.showMessageDialog(frame, "Done", ":)", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} else if (fd.getDirectory()==null||fd.getFile()==null){
					mp4path = null;
					mp4file = null;
					lblFile.setText("File: No File");
					m4apath = null;
					m4afile = null;
					lblFile_1.setText("File: No File");
				} else {
					mp4path = fd.getDirectory();
					mp4file = fd.getFile();
					lblFile.setText("File: "+mp4path+mp4file);
					m4apath = mp4path;
					m4afile = mp4file.replace(".mp4", ".m4a");
					lblFile_1.setText("File: "+m4apath+m4afile);
				}
			}
		});
		
		JButton btnMaDestination = new JButton("m4a Destination");
		btnMaDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
				if(mp4path!=null){
					fd.setDirectory(mp4path);
					if(mp4file!=null){
						String asd = mp4file.replace(".mp4", ".m4a");
						fd.setFile(asd);
					}
				} else {
					fd.setDirectory("F:\\Files\\Music\\YouTube");
					fd.setFile(".m4a");
				}
				fd.setVisible(true);
				if(fd.getDirectory()==null||fd.getFile()==null){
					lblFile_1.setText("File: No File");
					m4apath=null;
					m4afile=null;
				} else {
					m4apath=fd.getDirectory();
					m4afile=fd.getFile();
					lblFile_1.setText("File: "+m4apath+m4afile);
				}
			}
		});
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new File(m4apath+m4afile).isFile()){
					int n = JOptionPane.showConfirmDialog(
						    frame,
						    "File "+m4afile+" already exists, overwrite?",
						    "!",
						    JOptionPane.YES_NO_OPTION);
					if (n==JOptionPane.YES_OPTION) {
						if(mp4path!=null && mp4file!=null && m4apath!=null && m4afile!=null){
							new File(m4apath+m4afile).delete();
							convert();
						}
					}
				} else {
					if(mp4path!=null && mp4file!=null && m4apath!=null && m4afile!=null){
						convert();
					}
				}
			}
		});
		
		lblFile = new JLabel("File: No File");
		
		lblFile_1 = new JLabel("File: No File");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnConvert, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnMpSource, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnMaDestination, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFile_1, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
								.addComponent(lblFile, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMpSource)
						.addComponent(lblFile))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMaDestination)
						.addComponent(lblFile_1))
					.addGap(18)
					.addComponent(btnConvert)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void convert(){
		textArea.setText("Working...");
		String mp4 = mp4path+mp4file;
		String m4a = m4apath+m4afile;
		try {
			ProcessBuilder pb = new ProcessBuilder("F:\\Files\\Music\\Stuff\\ffmpeg-20150802-git-336822e-win64-static\\ffmpeg-20150802-git-336822e-win64-static\\bin\\ffmpeg.exe", "-i",  mp4, "-c:a", "copy", "-vn", "-sn", m4a);
			pb.redirectErrorStream(true);

			/* Start the process */
			Process proc = pb.start();

			/* Read the process's output */
			String line;             
			BufferedReader in = new BufferedReader(new InputStreamReader(
			        proc.getInputStream()));             
			while ((line = in.readLine()) != null) {
			    textArea.append("\n"+line);
			    textArea.update(textArea.getGraphics());
			}

			/* Clean-up */
			proc.destroy();
			int n = JOptionPane.showConfirmDialog(
				    frame,
				    "Delete mp4 source file? ("+mp4file+")",
				    "?",
				    JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION){
				new File(mp4path+mp4file).delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			textArea.setText(e.getMessage());
		}
	}
	private void convert(String eka, String toka){
		textArea.setText("Working...");
		String mp4 = eka;
		String m4a = toka;
		try {
			ProcessBuilder pb = new ProcessBuilder("F:\\Files\\Music\\Stuff\\ffmpeg-20150802-git-336822e-win64-static\\ffmpeg-20150802-git-336822e-win64-static\\bin\\ffmpeg.exe", "-i",  mp4, "-c:a", "copy", "-vn", "-sn", m4a);
			pb.redirectErrorStream(true);

			/* Start the process */
			Process proc = pb.start();

			/* Read the process's output */
			String line;             
			BufferedReader in = new BufferedReader(new InputStreamReader(
			        proc.getInputStream()));             
			while ((line = in.readLine()) != null) {
			    textArea.append("\n"+line);
			    textArea.update(textArea.getGraphics());
			}

			/* Clean-up */
			proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			textArea.setText(e.getMessage());
		}
	}
}
