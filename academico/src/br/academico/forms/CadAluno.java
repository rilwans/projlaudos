package br.academico.forms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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
	private JTextField txtnome;
	private JTextField txtendereco;
	private JTextField txtCidade;
	private JTextField txtmae;
	private JTextField txtcodigo;
	private JComboBox cbSexo;
	private JComboBox cbestado;
	private JFormattedTextField txtCEP;
	private JFormattedTextField txtcpf;

	private JFormattedTextField txtfone;
	private JFormattedTextField txtnascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAluno frame = new CadAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
			lblNome.setBounds(41, 44, 80, 14);
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
			lblEndereo.setBounds(41, 122, 80, 14);
			getContentPane().add(lblEndereo);

			txtCidade = new JTextField();
			txtCidade.setColumns(10);
			txtCidade.setBounds(131, 147, 116, 20);
			getContentPane().add(txtCidade);

			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(41, 153, 80, 14);
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
			lblCep.setBounds(41, 181, 80, 14);
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
			lblCpf.setBounds(249, 181, 46, 14);
			getContentPane().add(lblCpf);

			JLabel lblNomeMe = new JLabel("Nome M\u00E3e");
			lblNomeMe.setBounds(41, 69, 80, 14);
			getContentPane().add(lblNomeMe);

			txtmae = new JTextField();
			txtmae.setColumns(10);
			txtmae.setBounds(131, 63, 257, 20);
			getContentPane().add(txtmae);

			JLabel lblFone = new JLabel("Fone");
			lblFone.setBounds(249, 94, 46, 14);
			getContentPane().add(lblFone);

			mask = new MaskFormatter("(##) ####-####");
			mask.setValueContainsLiteralCharacters(false);
			mask.setPlaceholderCharacter('_');

			txtfone = new JFormattedTextField(mask);
			txtfone.setBounds(305, 88, 83, 20);
			getContentPane().add(txtfone);

			JLabel lblDtNascimento = new JLabel("Dt Nascimento");
			lblDtNascimento.setBounds(41, 95, 80, 14);
			getContentPane().add(lblDtNascimento);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			mask = new MaskFormatter("##/##/####");
			format.setLenient(false);
			mask.setAllowsInvalid(false);
			mask.setOverwriteMode(true);

			txtnascimento = new JFormattedTextField(format);
			mask.install(txtnascimento);
			txtnascimento.setBounds(131, 89, 83, 20);
			getContentPane().add(txtnascimento);

			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(41, 209, 80, 14);
			getContentPane().add(lblSexo);

			cbSexo = new JComboBox();
			cbSexo.setBounds(131, 206, 74, 20);

			cbSexo.addItem("");
			cbSexo.addItem("Masculino");
			cbSexo.addItem("Feminino");

			getContentPane().add(cbSexo);

			JButton btnLocaliza = new JButton("Localizar");
			btnLocaliza.setBounds(10, 237, 89, 23);
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
			lblCodigo.setBounds(41, 19, 80, 14);
			getContentPane().add(lblCodigo);

			txtcodigo = new JTextField();
			txtcodigo.setEnabled(false);
			txtcodigo.setColumns(10);
			txtcodigo.setBounds(131, 13, 53, 20);
			getContentPane().add(txtcodigo);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void fechar() {
		this.dispose();
	}

	private void salvaAluno() {

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

			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(txtnascimento
					.getText());
			String dataBanco = new SimpleDateFormat("yyyy-MM-dd").format(data);

			ps.setString(6, dataBanco);
			ps.setString(7, txtcpf.getValue().toString());
			ps.setString(8, txtmae.getText());
			ps.setString(9, txtfone.getValue().toString());
			ps.setString(10, (String) cbSexo.getSelectedItem().toString().substring(0, 1));

			ps.executeUpdate();

			ps.close();

			JOptionPane.showMessageDialog(null,"Dados Salvos com sucesso");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
