package br.com.veridistec.izbio.sample;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.com.veridistec.izbio.gui.VrIzBio;
import br.com.veridistec.izbio.gui.forms.CapturaDialog;
import br.com.veridistec.izbio.gui.listener.VrIzBioCadastraListener;
import br.com.veridistec.izbio.gui.listener.VrIzBioVerificaListener;
import br.com.veridistec.izbio.sample.model.Cadastro;
import br.com.veridistec.izbio.sample.service.ServicoCadastro;

public class VrIzBioSample extends JFrame {

	private static final long serialVersionUID = -8880923725033672249L;

	private JButton btnCadastro;

	private JButton btnRemover;

	private JButton btnVerificar;

	private JTextField txtNomeCadastro;

	private JLabel lblVeridisLogo;

	private JLabel lblVeridisTecnologia;

	private JList listCadastros;

	private ServicoCadastro servico;

	private CRUDListModel<Cadastro, Long> listModel;

	private Cadastro cad;

	private JButton btnLimparBD;

	private JButton btnAtualizar;

	public VrIzBioSample() {
		Container conteiner = getContentPane();
		conteiner.setLayout(null);
		setSize(480, 290);
		txtNomeCadastro = new JTextField();
		txtNomeCadastro.setBounds(10, 10, 280, 30);
		conteiner.add(txtNomeCadastro);
		btnCadastro = new JButton("Novo cadastro");
		btnCadastro.setBounds(300, 10, 165, 30);
		conteiner.add(btnCadastro);
		btnRemover = new JButton("Remover cadastro");
		btnRemover.setBounds(300, 50, 165, 30);
		conteiner.add(btnRemover);
		btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(300, 90, 165, 30);
		conteiner.add(btnVerificar);
		btnLimparBD = new JButton("Limpar Banco");
		btnLimparBD.setBounds(155, 220, 135, 30);
		conteiner.add(btnLimparBD);
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(10, 220, 135, 30);
		conteiner.add(btnAtualizar);

		// String[] d = { "Cadastro 1", "Cadastro 2" };
		listCadastros = new JList();

		JScrollPane scroll = new JScrollPane(listCadastros, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(10, 50, 280, 160);
		conteiner.add(scroll);

		lblVeridisLogo = new JLabel();
		try {
			lblVeridisLogo.setIcon(new ImageIcon(ImageIO.read(VrIzBioSample.class.getResource("veridis.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblVeridisLogo.setBounds(330, 130, 100, 100);
		lblVeridisLogo.setBackground(Color.RED);
		conteiner.add(lblVeridisLogo);
		lblVeridisTecnologia = new JLabel("Veridis Tecnologia");
		lblVeridisTecnologia.setHorizontalAlignment(JLabel.CENTER);
		lblVeridisTecnologia.setBounds(300, 230, 165, 30);
		conteiner.add(lblVeridisTecnologia);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(VrIzBio.getVeridisIcon());
		setTitle("Veridis Sample");
		setVisible(true);
	}

	@SuppressWarnings("serial")
	public void startSample() {
		centerScreen();
		servico = new ServicoCadastro();
		//List<Cadastro> list = servico.findAll(null, null, null, null);
		listModel = new CRUDListModel<Cadastro, Long>(servico) {
			@Override
			public Object getElementAt(int index) {
				return ((Cadastro) super.getElementAt(index)).getNome();
			}

		};
		listCadastros.setModel(listModel);

		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCadastroAction();
			}
		});

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnRemoverAction();
			}
		});
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnVerificaAction();
			}
		});
		btnLimparBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<Cadastro> cs = servico.findAll(null, null, null, null);
				Iterator<Cadastro> it = cs.iterator();
				while (it.hasNext()) {
					Cadastro tod = it.next();
					servico.remove(tod);
					it.remove();
				}
				refreshList();
			}
		});
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshList();
			}
		});
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
			}

		});
	}

	public void centerScreen() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
		// super.setVisible(true);
		// requestFocus();
	}

	protected void btnRemoverAction() {
		Cadastro c = getSelectedCadastro();
		if (c != null) {
			servico.remove(c);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um cadastro para remover.", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
		}
		refreshList();
	}

	protected void btnVerificaAction() {
		Cadastro c = getSelectedCadastro();
		if (c != null) {
			List<byte[]> l = new ArrayList<byte[]>();
			if (c.getTemplate1() != null && c.getTemplate1().length > 0) {
				l.add(c.getTemplate1());
			}
			if (c.getTemplate2() != null && c.getTemplate2().length > 0) {
				l.add(c.getTemplate2());
			}
			if (c.getTemplate3() != null && c.getTemplate3().length > 0) {
				l.add(c.getTemplate3());
			}
			if (c.getTemplate4() != null && c.getTemplate4().length > 0) {
				l.add(c.getTemplate4());
			}
			VrIzBio.verifica(this, new VrIzBioVerificaListener() {

				public void onVerificaSucesso(JDialog source, int score) {
					System.out.println("Yeah! Score = " + score);
				}

				public void onCancelaVerifica(JDialog source) {
					System.out.println("Cancelou");
				}

				public void onDispose(JDialog source) {
				}
			}, l);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um cadastro para realizar a comparação.", "Erro de Verificação", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCadastroAction() {
		if (txtNomeCadastro.getText() == null || txtNomeCadastro.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Forneça um nome para o cadastro", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		cad = servico.create();
		cad.setNome(txtNomeCadastro.getText());
		txtNomeCadastro.setText("");
		// cad = servico.save(cad);
		JDialog dialog = VrIzBio.cadastra(this, new VrIzBioCadastraListener() {

			public void onCancelaCadastro(JDialog source) {
				servico.remove(cad);
				cad = null;
			}

			public void onCadastroDedo(JDialog source, byte[][] datas, int qtdData, int dedo) {
				if (qtdData >= 1) {
					cad.setTemplate1(datas[0]);
				}
				if (qtdData >= 2) {
					cad.setTemplate2(datas[1]);
				}
				if (qtdData >= 3) {
					cad.setTemplate3(datas[2]);
				}
				if (qtdData >= 4) {
					cad.setTemplate4(datas[3]);
				}
				// cad = servico.save(cad);
				// refreshList();
			}

			public void onDispose(JDialog source) {
				if (cad.getTemplate1() != null && cad.getTemplate1().length > 0 && cad.getTemplate2() != null && cad.getTemplate2().length > 0
						&& cad.getTemplate3() != null && cad.getTemplate3().length > 0 && cad.getTemplate4() != null && cad.getTemplate4().length > 0) {
					cad = servico.save(cad);
				} else {
					cad = null;
				}
			}
		});
		((CapturaDialog) dialog).setQtdeCapFinger(1);
	}

	public Cadastro getSelectedCadastro() {
		int t = listCadastros.getSelectedIndex();
		if (t >= 0) {
			return listModel.getCrudElement(t);
		}
		return null;
	}

	public void refreshList() {
		listCadastros.repaint();
		// listCadastros.invalidate();
		// listCadastros.validate();
	}

	public static void main(String[] args) {

//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//
//		}
		VrIzBioSample sample = new VrIzBioSample();
		sample.startSample();
	}
}
