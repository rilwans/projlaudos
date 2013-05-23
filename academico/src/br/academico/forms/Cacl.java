package br.academico.forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Cacl extends JFrame {

	private JPanel contentPane;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JTextField txtresult;
	private JRadioButton rdAdicao;
	private JRadioButton rdSubtracao;
	private JRadioButton rdMulti;
	private JRadioButton rdDivisao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cacl frame = new Cacl();
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
	public Cacl() {
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNum1 = new JTextField();
		txtNum1.setBounds(51, 33, 138, 20);
		contentPane.add(txtNum1);
		txtNum1.setColumns(10);

		txtNum2 = new JTextField();
		txtNum2.setBounds(217, 33, 138, 20);
		contentPane.add(txtNum2);
		txtNum2.setColumns(10);

		JButton btnNewButton = new JButton("Calcular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				efetuarCalculo();
			}
		});
		btnNewButton.setBounds(142, 105, 121, 23);
		contentPane.add(btnNewButton);

		JLabel lblNum = new JLabel("1\u00BA Num.");
		lblNum.setBounds(51, 21, 46, 14);
		contentPane.add(lblNum);

		JLabel lblNum_1 = new JLabel("2\u00BA Num.");
		lblNum_1.setBounds(217, 21, 46, 14);
		contentPane.add(lblNum_1);

		rdAdicao = new JRadioButton("Adi\u00E7\u00E3o");
		rdAdicao.setBounds(30, 75, 65, 23);
		contentPane.add(rdAdicao);

		rdSubtracao = new JRadioButton("Subtra\u00E7\u00E3o");
		rdSubtracao.setBounds(95, 75, 94, 23);
		contentPane.add(rdSubtracao);

		rdMulti = new JRadioButton("Multiplica\u00E7\u00E3o");
		rdMulti.setBounds(191, 75, 104, 23);
		contentPane.add(rdMulti);

		rdDivisao = new JRadioButton("Divis\u00E3o");
		rdDivisao.setBounds(304, 75, 83, 23);
		contentPane.add(rdDivisao);

		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblResultado.setBounds(30, 143, 94, 35);
		contentPane.add(lblResultado);

		txtresult = new JTextField();
		txtresult.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtresult.setBounds(157, 146, 198, 33);
		contentPane.add(txtresult);
		txtresult.setColumns(10);
	}

	private void efetuarCalculo() {
		System.out.println("sada");
		if (rdAdicao.isSelected()) {
			txtresult
					.setText(String.valueOf(Integer.parseInt(txtNum1.getText()) + Integer.parseInt(txtNum2.getText())));
		}
		if (rdSubtracao.isSelected()) {
			txtresult
					.setText(String.valueOf(Integer.parseInt(txtNum1.getText()) - Integer.parseInt(txtNum2.getText())));
		}
		if (rdMulti.isSelected()) {
			txtresult
					.setText(String.valueOf(Integer.parseInt(txtNum1.getText()) * Integer.parseInt(txtNum2.getText())));
		}
		if (rdDivisao.isSelected()) {
			txtresult
					.setText(String.valueOf(Integer.parseInt(txtNum1.getText()) / Integer.parseInt(txtNum2.getText())));
		}

	}

}
