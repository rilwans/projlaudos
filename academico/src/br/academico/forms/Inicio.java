package br.academico.forms;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Inicio {

	public JFrame frame;
	public JDesktopPane desktop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
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
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Aluno");
		frame.setBounds(100, 100, 625, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.inactiveCaption);
		frame.setContentPane(desktop);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmAluno = new JMenuItem("Aluno");
		mntmAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				chamaALuno();

			}
		});
		mnCadastro.add(mntmAluno);

		JMenuItem mntmProfessor = new JMenuItem("Professor");
		mnCadastro.add(mntmProfessor);

		JMenuItem mntmDisciplina = new JMenuItem("Disciplina");
		mnCadastro.add(mntmDisciplina);

		JMenuItem mntmCurso = new JMenuItem("Curso");
		mnCadastro.add(mntmCurso);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setMaximumSize(new Dimension(100, 100));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem mntmMatricula = new JMenuItem("Matricula");
		mntmMatricula.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mntmMatricula);
		menuBar.add(mntmSair);

	}

	private void chamaALuno() {
		CadAluno cadaluno = new CadAluno();
		cadaluno.setSize(450, 300);
		cadaluno.setVisible(true);
		cadaluno.setTitle("Aluno - CET - A Pioneira no programa de qualidade!!! - Veeeenha!");
		desktop.add(cadaluno);
	}

}
