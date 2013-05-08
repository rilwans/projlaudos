package br.academico.forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JPanel;

public class Disciplina extends JInternalFrame {
	private JTextField txtNmDisciplina;
	private JTextField txtCargaHr;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Disciplina frame = new Disciplina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Disciplina() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 22, 46, 14);
		getContentPane().add(lblDisciplina);
		
		txtNmDisciplina = new JTextField();
		txtNmDisciplina.setBounds(58, 19, 323, 20);
		getContentPane().add(txtNmDisciplina);
		txtNmDisciplina.setColumns(10);
		
		JLabel lblCargaHr = new JLabel("Carga Hr");
		lblCargaHr.setBounds(10, 58, 46, 14);
		getContentPane().add(lblCargaHr);
		
		txtCargaHr = new JTextField();
		txtCargaHr.setBounds(58, 55, 46, 20);
		getContentPane().add(txtCargaHr);
		txtCargaHr.setColumns(10);
		
		JLabel lblBloco = new JLabel("Bloco");
		lblBloco.setBounds(118, 58, 37, 14);
		getContentPane().add(lblBloco);
		
		JSpinner spbloco = new JSpinner();
		spbloco.setBounds(151, 55, 46, 20);
		getContentPane().add(spbloco);
		
		JLabel lblProfessores = new JLabel("Professores");
		lblProfessores.setBounds(10, 86, 73, 14);
		getContentPane().add(lblProfessores);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 111, 371, 126);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 351, 104);
		panel.add(table);

	}
}
