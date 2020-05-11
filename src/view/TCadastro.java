package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import control.Pagamento;
import control.Produto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TCadastro extends TCaixa {

	private static final long serialVersionUID = 1L;
	protected static final int ERROR_MESSAGE = 0;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JComboBox<String> cmbBox;
	MaskFormatter mcodigo;

	public void limparTela() {
		txtCodigo.setText("");
		txtDescricao.setText("");
		cmbBox.setSelectedItem(-1);
		txtValor.setText("");
	}

	public TCadastro(String caminho,JFrame TCaixa) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Cadastro Produtos");
		setBounds(100, 100, 544, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblCadastroDeProduto = new JLabel("Cadastro de Produtos");
		lblCadastroDeProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblCadastroDeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProduto.setBounds(122, 29, 269, 44);
		panel.add(lblCadastroDeProduto);

		JLabel lblInformaesDoProduto = new JLabel("Informa\u00E7\u00F5es do produto");
		lblInformaesDoProduto.setBounds(35, 81, 128, 14);
		panel.add(lblInformaesDoProduto);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(45, 106, 46, 14);
		panel.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(85, 103, 52, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescrio.setBounds(35, 139, 46, 14);
		panel.add(lblDescrio);

		txtDescricao = new JTextField();
		txtDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// *****************************************************************************************
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Pagamento pay = new Pagamento();
					if (txtCodigo.getText().isEmpty()) {
						String descricao = txtDescricao.getText();
						String valor = txtValor.getText();
						boolean op = pay.verifica(txtDescricao.getText(), txtValor.getText()); // ************
						if (descricao.isEmpty() || valor.isEmpty() || op == false) {
							JOptionPane.showMessageDialog(null,
									"Preencha os campos 'Valor' , 'Descrição' e 'Medida' para efetuar o cadastro", null,
									ERROR_MESSAGE);
						} else {
							Produto p = new Produto();
							boolean aux;
							aux = p.salvar(txtCodigo.getText(), txtDescricao.getText(), (String) cmbBox.getSelectedItem(),
									txtValor.getText(), caminho);
							if (aux != true) {
								JOptionPane.showMessageDialog(null, "Erro ao salvar dados", null, ERROR_MESSAGE);
								limparTela();
							} else {
								limparTela();
							}
						}
					} else {
						Produto p = new Produto();
						boolean aux;
						aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
								(String) cmbBox.getSelectedItem(), txtValor.getText());
						if (aux == true) {
							limparTela();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", null, ERROR_MESSAGE);
							limparTela();
						}
					}
				}
				// *****************************************************************************************
			}
		});
		txtDescricao.setBounds(96, 136, 398, 20);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblUnidDeMedida = new JLabel("Medida");
		lblUnidDeMedida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUnidDeMedida.setBounds(71, 184, 41, 14);
		panel.add(lblUnidDeMedida);

		cmbBox = new JComboBox<String>();
		cmbBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Kg", "Lt", "Unid", "Pct" }));
		cmbBox.setBounds(122, 181, 67, 20);
		cmbBox.setSelectedItem(-1);
		panel.add(cmbBox);

		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio");
		lblValorUnitrio.setBounds(27, 221, 69, 14);
		panel.add(lblValorUnitrio);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// *****************************************************************************************
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Pagamento pay = new Pagamento();
					if (txtCodigo.getText().isEmpty()) {
						String descricao = txtDescricao.getText();
						String valor = txtValor.getText();
						boolean op = pay.verifica(txtDescricao.getText(), txtValor.getText()); // ************
						if (descricao.isEmpty() || valor.isEmpty() || op == false) {
							JOptionPane.showMessageDialog(null,
									"Preencha os campos 'Valor' , 'Descrição' e 'Medida' para efetuar o cadastro", null,
									ERROR_MESSAGE);
						} else {
							Produto p = new Produto();
							boolean aux;
							aux = p.salvar(txtCodigo.getText(), txtDescricao.getText(), (String) cmbBox.getSelectedItem(),
									txtValor.getText(), caminho);
							if (aux != true) {
								JOptionPane.showMessageDialog(null, "Erro ao salvar dados", null, ERROR_MESSAGE);
								limparTela();
							} else {
								limparTela();
							}
						}
					} else {
						Produto p = new Produto();
						boolean aux;
						aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
								(String) cmbBox.getSelectedItem(), txtValor.getText());
						if (aux == true) {
							limparTela();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", null, ERROR_MESSAGE);
							limparTela();
						}
					}
				}
				// *****************************************************************************************
			}
		});
		txtValor.setBounds(103, 218, 101, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// *****************************************************************************************
				Pagamento pay = new Pagamento();
				if (txtCodigo.getText().isEmpty()) {
					String descricao = txtDescricao.getText();
					String valor = txtValor.getText();
					boolean op = pay.verifica(txtDescricao.getText(), txtValor.getText()); 
					if (descricao.isEmpty() || valor.isEmpty() || op == false) {
						JOptionPane.showMessageDialog(null,
								"Preencha os campos 'Valor' , 'Descrição' e 'Medida' para efetuar o cadastro", null,
								ERROR_MESSAGE);
					} else {
						Produto p = new Produto();
						boolean aux;
						aux = p.salvar(txtCodigo.getText(), txtDescricao.getText(), (String) cmbBox.getSelectedItem(),
								txtValor.getText(), caminho);
						if (aux != true) {
							JOptionPane.showMessageDialog(null, "Erro ao salvar dados", null, ERROR_MESSAGE);
							limparTela();
						} else {
							limparTela();
						}
					}
				} else {
					Produto p = new Produto();
					boolean aux;
					aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
							(String) cmbBox.getSelectedItem(), txtValor.getText());
					if (aux == true) {
						limparTela();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", null, ERROR_MESSAGE);
						limparTela();
					}
				}
// ******************************************************************************************
			}
		});
		btnSalvar.setBounds(15, 307, 89, 23);
		panel.add(btnSalvar);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigoProcurado = JOptionPane.showInputDialog(null,
						"Digite código do produto a ser localizado :");
				Produto p = new Produto();
				if (p.localizar(codigoProcurado, caminho)) {
					txtDescricao.setText(p.getDescricao());
					txtCodigo.setText(p.getCodigo());
					txtValor.setText(p.getValor());
					cmbBox.setSelectedItem(p.getMedida());
				} else {
					JOptionPane.showMessageDialog(null, "Produto não localizado!", null, ERROR_MESSAGE);
				}
			}
		});
		btnLocalizar.setBounds(141, 307, 89, 23);
		panel.add(btnLocalizar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
// ******************************************************************************************
				if (!txtCodigo.getText().isEmpty()) {
					Produto p = new Produto();
					boolean aux;
					aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
							(String) cmbBox.getSelectedItem(), txtValor.getText());
					if (aux == true) {
						limparTela();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", null, ERROR_MESSAGE);
						limparTela();
					}
				} else {
					String codigoProcurado = JOptionPane.showInputDialog(null,
							"Digite código do produto a ser localizado :");
					Produto p = new Produto();
					if (p.localizar(codigoProcurado, caminho)) {
						txtDescricao.setText(p.getDescricao());
						txtCodigo.setText(p.getCodigo());
						txtValor.setText(p.getValor());
						cmbBox.setSelectedItem(p.getMedida());
					} else {
						JOptionPane.showMessageDialog(null, "Produto não localizado!", null, ERROR_MESSAGE);
					}
				}
// ******************************************************************************************
			}
		});
		btnEditar.setBounds(276, 307, 89, 23);
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// ******************************************************************************************
				if (txtCodigo.getText().isEmpty()) {
					String codigoProcurado = JOptionPane.showInputDialog(null,
							"Digite código do produto a ser excluido :");
					Produto p = new Produto();
					int aux;
					aux = p.excluir(codigoProcurado, caminho);
					if (aux < Integer.parseInt(codigoProcurado)) {
						JOptionPane.showMessageDialog(null, "Cadastro deletado!");
						limparTela();
					} else {
						JOptionPane.showMessageDialog(null, "Produto não localizado!", null, ERROR_MESSAGE);
						limparTela();
					}
				} else {
					Produto p = new Produto();
					int aux;
					aux = p.excluir(txtCodigo.getText(), caminho);
					if (aux < Integer.parseInt(txtCodigo.getText())) {
						JOptionPane.showMessageDialog(null, "Cadastro deletado!");
						limparTela();
					}
				}
// ******************************************************************************************
			}
		});
		btnExcluir.setBounds(405, 307, 89, 23);
		panel.add(btnExcluir);

		JButton button = new JButton("Voltar para Caixa");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCaixa.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button.setBounds(388, 229, 106, 44);
		panel.add(button);

		JButton btnLimpar = new JButton("Limpar Tela");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLimpar.setBounds(259, 229, 106, 44);
		panel.add(btnLimpar);
	}
}
