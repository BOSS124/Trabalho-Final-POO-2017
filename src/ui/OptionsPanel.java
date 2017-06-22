package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**Representa o menu com as seguinte opções: Carregar labirinto de arquivo,
 * salvar labirinto em arquivo, limpar o quadro, 
 * @author Igor Trevelin
 * @author Alex Sander
 * @author Rodrigo Anes
 */
@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	private JButton loadBoardBtn;
	private JButton saveBoardBtn;
	private JButton clearBoardBtn;
	private JButton runBtn;
	private JFileChooser fileChooser;
	
	private FileNameExtensionFilter labFilter;
	private FileNameExtensionFilter bmpFilter;
	private FileNameExtensionFilter	allValidFilter;
	
	/**Construtor da classe OptionsPanel.
	 * 
	 */
	public OptionsPanel() {
		setLayout(new GridLayout(4, 1, 5, 5));
		setBackground(MainFrame.DEFAULT_BACKGROUND_COLOR);
		
		loadBoardBtn = new JButton("Abrir");
		saveBoardBtn = new JButton("Salvar");
		clearBoardBtn = new JButton("Limpar");
		runBtn = new JButton("Executar");
		
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		labFilter = new FileNameExtensionFilter("LAB Files", "lab");
		bmpFilter = new FileNameExtensionFilter("BMP Files", "bmp");
		allValidFilter = new FileNameExtensionFilter("LAB Files or BMP FILES", "lab", "bmp");
		
		
		loadBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Load labyrinth...");
				fileChooser.setDialogType(JFileChooser. OPEN_DIALOG);
				fileChooser.resetChoosableFileFilters();
				fileChooser.addChoosableFileFilter(labFilter);
				fileChooser.addChoosableFileFilter(bmpFilter);
				fileChooser.addChoosableFileFilter(allValidFilter);
				
				int result = fileChooser.showOpenDialog(MainFrame.optionsPanel);	
				if(result == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getName();
					MainFrame.board.clear();
					boolean operationResult = false;
					
					if(fileName.endsWith(".lab"))
						operationResult = MainFrame.board.loadBoardFromFile(fileChooser.getSelectedFile());
					else if(fileName.endsWith(".bmp"))
						operationResult = MainFrame.board.loadBMP(fileChooser.getSelectedFile());
					
					if(!operationResult) {
						JOptionPane.showMessageDialog(null, "Não foi possível carregar o arquivo!",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		saveBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Save labyrinth...");
				fileChooser.setDialogType(JFileChooser. SAVE_DIALOG);
				fileChooser.resetChoosableFileFilters();
				fileChooser.addChoosableFileFilter(labFilter);
				
				int result = fileChooser.showSaveDialog(MainFrame.optionsPanel);
				if(result == JFileChooser.APPROVE_OPTION) {
					String str = fileChooser.getSelectedFile().toString();
					if(!str.endsWith(".lab"))
						str = str.concat(".lab");
					if(!MainFrame.board.saveBoardToFile(str)) {
						JOptionPane.showMessageDialog(null, "Não foi possível salvar o labirinto!",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		clearBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.board.clear();
			}
		});
		
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = MainFrame.board.isValidMaze();
				
				if(result == 1) {
					new Thread(MainFrame.board).start();
				}
				else if(result == 2) {
					JOptionPane.showMessageDialog(null, "O labirinto deve ter exatamente 1 entrada!",
						"Labirinto Inválido",
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});
		
		add(loadBoardBtn);
		add(saveBoardBtn);
		add(clearBoardBtn);
		add(runBtn);
	}
}
