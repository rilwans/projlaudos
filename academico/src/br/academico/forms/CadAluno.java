package br.academico.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.academico.conexao.Banco;

public class CadAluno extends JInternalFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 5812452345019911922L;
	private JTextField txtnome;
	private JTextField txtendereco;
	private JTextField txtCidade;
	private JTextField txtmae;
	private JTextField txtcodigo;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSexo;
	@SuppressWarnings("rawtypes")
	private JComboBox cbestado;
	private JFormattedTextField txtCEP;
	private JFormattedTextField txtcpf;

	private JFormattedTextField txtfone;
	private JFormattedTextField txtnascimento;
	private int idAluno;

	/**
	 * Create the frame.
	 *
	 * @throws ParseException
	 */
	public CadAluno() {
		setBounds(100, 100, 450, 303);
		getContentPane().setLayout(null);

		try {
			JLabel lblNome = new JLabel("Nome");
			lblNome.setBounds(25, 41, 96, 14);
			getContentPane().add(lblNome);

			txtnome = new JTextField("");
			txtnome.setBounds(131, 38, 257, 20);
			getContentPane().add(txtnome);
			txtnome.setColumns(10);

			JButton btnSair = new JButton("Sair");
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					fechar();

				}
			});
			btnSair.setBounds(335, 237, 89, 23);
			getContentPane().add(btnSair);

			txtendereco = new JTextField();
			txtendereco.setColumns(10);
			txtendereco.setBounds(131, 116, 257, 20);
			getContentPane().add(txtendereco);

			JLabel lblEndereo = new JLabel("Endere\u00E7o");
			lblEndereo.setBounds(25, 119, 96, 14);
			getContentPane().add(lblEndereo);

			txtCidade = new JTextField();
			txtCidade.setColumns(10);
			txtCidade.setBounds(131, 147, 116, 20);
			getContentPane().add(txtCidade);

			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(25, 150, 96, 14);
			getContentPane().add(lblCidade);

			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(267, 150, 46, 14);
			getContentPane().add(lblEstado);

			cbestado = new JComboBox();
			cbestado.setBounds(314, 147, 74, 20);

			cbestado.addItem("");
			cbestado.addItem("PI");
			cbestado.addItem("MA");
			cbestado.addItem("RN");
			cbestado.addItem("BA");
			cbestado.addItem("PE");
			cbestado.addItem("CE");

			getContentPane().add(cbestado);

			JLabel lblCep = new JLabel("CEP");
			lblCep.setBounds(25, 178, 96, 14);
			getContentPane().add(lblCep);

			MaskFormatter mask;

			mask = new MaskFormatter("##.###-###");
			mask.setValueContainsLiteralCharacters(false);
			mask.setPlaceholderCharacter('_');

			txtCEP = new JFormattedTextField(mask);
			txtCEP.setBounds(131, 175, 83, 20);
			getContentPane().add(txtCEP);

			mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			mask.setPlaceholderCharacter('_');

			txtcpf = new JFormattedTextField(mask);
			txtcpf.setBounds(277, 175, 111, 20);
			getContentPane().add(txtcpf);

			JLabel lblCpf = new JLabel("CPF");
			lblCpf.setBounds(249, 178, 46, 14);
			getContentPane().add(lblCpf);

			JLabel lblNomeMe = new JLabel("Nome M\u00E3e");
			lblNomeMe.setBounds(25, 66, 96, 14);
			getContentPane().add(lblNomeMe);

			txtmae = new JTextField();
			txtmae.setColumns(10);
			txtmae.setBounds(131, 63, 257, 20);
			getContentPane().add(txtmae);

			JLabel lblFone = new JLabel("Fone");
			lblFone.setBounds(249, 91, 46, 14);
			getContentPane().add(lblFone);

			mask = new MaskFormatter("(##) ####-####");
			mask.setValueContainsLiteralCharacters(false);
			mask.setPlaceholderCharacter('_');

			txtfone = new JFormattedTextField(mask);
			txtfone.setBounds(305, 88, 83, 20);
			getContentPane().add(txtfone);

			JLabel lblDtNascimento = new JLabel("Dt Nascimento");
			lblDtNascimento.setBounds(25, 92, 96, 14);
			getContentPane().add(lblDtNascimento);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			mask = new MaskFormatter("##/##/####");
			mask.setAllowsInvalid(false);
			mask.setOverwriteMode(true);

			txtnascimento = new JFormattedTextField(format);
			txtnascimento.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {

					validarData(txtnascimento.getText());

				}
			});
			mask.install(txtnascimento);
			txtnascimento.setBounds(131, 89, 83, 20);
			getContentPane().add(txtnascimento);

			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(25, 209, 96, 14);
			getContentPane().add(lblSexo);

			cbSexo = new JComboBox();
			cbSexo.setBounds(131, 206, 74, 20);

			cbSexo.addItem("");
			cbSexo.addItem("Masculino");
			cbSexo.addItem("Feminino");

			getContentPane().add(cbSexo);

			JButton btnLocaliza = new JButton("Localizar");
			btnLocaliza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					localizarAluno();

				}
			});
			btnLocaliza.setBounds(186, 12, 89, 23);
			getContentPane().add(btnLocaliza);

			JButton btnsalvar = new JButton("Salvar");
			btnsalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					salvaAluno();

				}
			});
			btnsalvar.setBounds(98, 237, 89, 23);
			getContentPane().add(btnsalvar);

			JButton BtnExcluir = new JButton("Excluir");
			BtnExcluir.setBounds(186, 237, 89, 23);
			getContentPane().add(BtnExcluir);

			JLabel lblCodigo = new JLabel("Codigo");
			lblCodigo.setBounds(25, 16, 96, 14);
			getContentPane().add(lblCodigo);

			txtcodigo = new JTextField();
			txtcodigo.setEnabled(false);
			txtcodigo.setColumns(10);
			txtcodigo.setBounds(131, 13, 53, 20);
			getContentPane().add(txtcodigo);

			JButton btnNovo = new JButton("Novo");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					idAluno = 0;
					txtCEP.setText("");
					txtCEP.setText("");
					txtCidade.setText("");
					txtcpf.setText("");
					txtendereco.setText("");
					txtfone.setText("");
					txtnascimento.setText("");
					txtnome.setText("");
					txtmae.setText("");
					txtcodigo.setText("");

				}
			});
			btnNovo.setBounds(10, 237, 89, 23);
			getContentPane().add(btnNovo);

		} catch (ParseException e1) {
			// // TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validarData(String data) {
		if (!data.equals("") && !data.equals("  /  /    "))

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				sdf.setLenient(false);
				sdf.parse(data);

			} catch (ParseException x) {
				JOptionPane.showMessageDialog(null, "Data Invalida", "Atenção",
						JOptionPane.ERROR_MESSAGE);
			}

	}

	private void fechar() {
		this.dispose();
	}

	public void localizarAluno() {

		LocAluno locAluno = new LocAluno(this);

		this.getDesktopPane().add(locAluno);
		locAluno.setVisible(true);

	}

	private void salvaAluno() {

		if (idAluno == 0) {
			String sql = "INSERT INTO aluno (nmAluno,endereco,cidade,estado,CEP,dtNascimento,CPF,nmMae,"
					+ "telefone,SEXO) VALUES (? ,? , ? , ? , ? , ? , ? , ? , ? , ? )";
			Banco banco;
			try {
				banco = new Banco();
				PreparedStatement ps = banco.getConexao().prepareStatement(sql);

				ps.setString(1, txtnome.getText());
				ps.setString(2, txtendereco.getText());
				ps.setString(3, txtCidade.getText());
				ps.setString(4, (String) cbestado.getSelectedItem());
				ps.setString(5, txtCEP.getValue().toString());

				Date data = new SimpleDateFormat("dd/MM/yyyy")
						.parse(txtnascimento.getText());
				String dataBanco = new SimpleDateFormat("yyyy-MM-dd")
						.format(data);

				ps.setString(6, dataBanco);
				ps.setString(7, txtcpf.getValue().toString());
				ps.setString(8, txtmae.getText());
				ps.setString(9, txtfone.getValue().toString());
				ps.setString(10, (String) cbSexo.getSelectedItem().toString()
						.substring(0, 1));

				ps.executeUpdate();

				ps.close();

				JOptionPane.showMessageDialog(null, "Dados Salvos com sucesso");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}

	}

	/**
	 * @return the idAluno
	 */
	public int getIdAluno() {
		return idAluno;
	}

	/**
	 * @param idAluno
	 *            the idAluno to set
	 */
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
		this.txtcodigo.setText(String.valueOf(idAluno));
		try {
			Banco banco = new Banco();
			String sql = "select * from aluno where idAluno = "
					+ String.valueOf(idAluno);
			ResultSet rs = banco.getStatement().executeQuery(sql);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			while (rs.next()) {
				txtCEP.setText(rs.getString("CEP"));
				txtCidade.setText(rs.getString("Cidade"));
				txtcpf.setText(rs.getString("cpf"));
				txtendereco.setText(rs.getString("endereco"));
				txtfone.setText(rs.getString("telefone"));
				txtnascimento.setText(format.format(rs.getDate("CEP")));
				txtnome.setText(rs.getString("nmAluno"));
				txtmae.setText(rs.getString("nmMae"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
