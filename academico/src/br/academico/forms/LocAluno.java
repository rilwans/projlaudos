package br.academico.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.academico.conexao.Banco;

public class LocAluno extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6669595276623750232L;
	private JTable tbLocAluno;
	private JTextField txtLocAluno;
	private CadAluno chamador;
	

	/**
	 * Create the frame.
	 */
	public LocAluno(JInternalFrame origem) {
		this.chamador=(CadAluno) origem;
		setBounds(100, 100, 436, 249);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 400, 137);

		JLabel lblNomeDoAluno = new JLabel("Nome do Aluno");
		lblNomeDoAluno.setBounds(10, 11, 93, 14);
		getContentPane().add(lblNomeDoAluno);

		txtLocAluno = new JTextField();
		txtLocAluno.setBounds(113, 8, 166, 20);
		getContentPane().add(txtLocAluno);
		txtLocAluno.setColumns(10);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				localizaPorNome();
			}
		});
		btnLocalizar.setBounds(317, 7, 93, 23);
		getContentPane().add(btnLocalizar);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaAluno();
				
			}
		});
		btnSelecionar.setBounds(14, 184, 115, 23);
		getContentPane().add(btnSelecionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fecha();
			}
		});
		btnCancelar.setBounds(295, 184, 115, 23);
		getContentPane().add(btnCancelar);

		JScrollPane rolagem = new JScrollPane();
		rolagem.setBounds(10, 39, 400, 137);
		getContentPane().add(rolagem);

		tbLocAluno = new JTable();
		tbLocAluno.setBounds(10, 11, 380, 115);
		tbLocAluno.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbLocAluno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbLocAluno.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome", "Nascimento" }));
		tbLocAluno.getColumnModel().getColumn(0).setPreferredWidth(89);
		tbLocAluno.getColumnModel().getColumn(1).setPreferredWidth(194);
		tbLocAluno.getColumnModel().getColumn(2).setPreferredWidth(113);
		rolagem.setViewportView(tbLocAluno);

	}

	private void selecionaAluno(){
		int[] l = tbLocAluno.getSelectedRows();
		int idAluno =(int) tbLocAluno.getValueAt(l[0], 0);
		chamador.setIdAluno(idAluno);
		this.dispose();
	}
	
	private void localizaPorNome() {
		if (txtLocAluno.getText().equals("") || txtLocAluno.getText() == null) {
			JOptionPane.showMessageDialog(null, "Nenhum Nome Digitado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				Banco banco = new Banco();
				String sql = "select idAluno,nmAluno,dtNascimento from aluno where nmAluno like '"
						+ txtLocAluno.getText().trim() + "%' order by nmAluno";
				DefaultTableModel tbModelo = (DefaultTableModel) tbLocAluno.getModel();
				tbModelo.setNumRows(0);
				ResultSet rs = (ResultSet) banco.getStatement().executeQuery(sql);
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				while (rs.next()) {
					tbModelo.addRow(new Object[] { rs.getInt("idAluno"), rs.getString("nmAluno"),
							format.format(rs.getDate("dtNascimento")),

					});
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void fecha() {
		this.dispose();
	}
}
