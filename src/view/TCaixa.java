package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.Pagamento;
import control.Produto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TCaixa extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final int ERROR_MESSAGE = 0;
	private JPanel contentPane;
	protected JTextField txtSubtotal;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtCodigo;
	private JTextField txtQuantidade;
	protected JTextArea txtLista;
	private JTextField txtMedida;
	static TCaixa frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TCaixa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ArrayList<String> lista = new ArrayList<String>();
	String caminho = "C:\\Users\\USER\\Desktop\\Cadastro\\BDProdutos.txt";
	static String pagamento = "C:\\Users\\USER\\Desktop\\Cadastro\\BDPagamento.txt";
	public int aux = 0;
	double sub = 0;

	public void limparTela() {
		txtDescricao.setText("");
		txtValor.setText("");
		txtCodigo.setText("");
		txtQuantidade.setText("");
		txtMedida.setText("");
	}

	public void limparPagamento() {
		txtLista.setText("");
		lista.clear();
		txtSubtotal.setText("");
		sub = 0;
	}

	public TCaixa() {
		setTitle("Caixa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotal.setBounds(265, 0, 70, 25);
		panel.add(lblSubtotal);

		txtSubtotal = new JTextField();
		txtSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(265, 25, 253, 34);
		panel.add(txtSubtotal);
		txtSubtotal.setColumns(10);

		JLabel lblListaDeCompras = new JLabel("Lista de Compras");
		lblListaDeCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeCompras.setBounds(265, 80, 100, 14);
		panel.add(lblListaDeCompras);

		txtLista = new JTextArea();
		txtLista.setEditable(false);
		txtLista.setFont(new Font("Monospaced", Font.PLAIN, 10));
		txtLista.setBounds(265, 95, 253, 313);
		panel.add(txtLista);

		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
		lblDescrioDoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrioDoProduto.setBounds(10, 5, 126, 14);
		panel.add(lblDescrioDoProduto);

		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		txtDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricao.setEditable(false);
		txtDescricao.setBounds(10, 25, 173, 69);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(73, 170, 46, 14);
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txtValor.setEditable(false);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setBounds(52, 185, 88, 41);
		panel.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblCodigoProduto = new JLabel("Codigo produto");
		lblCodigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCodigoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoProduto.setBounds(30, 105, 88, 14);
		panel.add(lblCodigoProduto);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtCodigo.getText().length() >= 3) {
					Produto p = new Produto();
					if (p.localizar(txtCodigo.getText(), caminho)) {
						txtDescricao.setText(p.getDescricao());
						txtCodigo.setText(p.getCodigo());
						txtValor.setText(p.getValor());
						txtMedida.setText(p.getMedida());
					}
				}
			}
		});
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(40, 120, 76, 25);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(150, 185, 22, 54);
		panel.add(lblX);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// ***************************************************
				if (!txtCodigo.getText().isEmpty()) {
					Produto p = new Produto();
					if (p.localizar(txtCodigo.getText(), caminho)) {
						txtDescricao.setText(p.getDescricao());
						txtCodigo.setText(p.getCodigo());
						txtValor.setText(p.getValor());
						txtMedida.setText(p.getMedida());
					} else {
						JOptionPane.showMessageDialog(null, "Produto não localizado!", null, ERROR_MESSAGE);
						limparTela();
					}
				}
// ***************************************************
			}
		});
		btnLocalizar.setBounds(121, 105, 70, 46);
		panel.add(btnLocalizar);

		txtQuantidade = new JTextField();
		txtQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// ***************************************************
					Pagamento p = new Pagamento();
					DecimalFormat s = new DecimalFormat("0.00");
					if (!txtCodigo.getText().isEmpty() && !txtQuantidade.getText().isEmpty()
							&& p.verificaNumero(txtQuantidade.getText())) {
						if (txtLista.getText().isEmpty()) {
							aux = 0;
						}
						aux++;
						String linha;
						linha = p.incluir(aux, txtValor.getText(), txtQuantidade.getText(), txtDescricao.getText(),
								txtMedida.getText());
						lista.add(linha);
						txtLista.append(linha + "\n");
						sub = p.subtotal(sub, txtValor.getText(), txtQuantidade.getText());
						txtSubtotal.setText(s.format(sub));
						limparTela();
					}

					// ***************************************************
				}
			}
		});
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setBounds(176, 185, 70, 41);
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setBounds(174, 170, 65, 14);
		panel.add(lblQuantidade);

		JButton btnIncluir = new JButton("Incluir na Lista ");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// ***************************************************
				Pagamento p = new Pagamento();
				DecimalFormat s = new DecimalFormat("0.00");
				if (!txtCodigo.getText().isEmpty() && !txtQuantidade.getText().isEmpty()
						&& p.verificaNumero(txtQuantidade.getText())) {
					if (txtLista.getText().isEmpty()) {
						aux = 0;
					}
					aux++;
					String linha;
					linha = p.incluir(aux, txtValor.getText(), txtQuantidade.getText(), txtDescricao.getText(),
							txtMedida.getText());
					lista.add(linha);
					txtLista.append(linha + "\n");
					sub = p.subtotal(sub, txtValor.getText(), txtQuantidade.getText());
					txtSubtotal.setText(s.format(sub));
					limparTela();
				}
// ***************************************************
			}
		});
		btnIncluir.setForeground(new Color(0, 0, 0));
		btnIncluir.setBounds(121, 250, 134, 41);
		panel.add(btnIncluir);

		JButton btnCancelarCompras = new JButton("Cancelar Compras");
		btnCancelarCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
// ***************************************************
				String senha = JOptionPane.showInputDialog(null, "Digite a senha :");
				if (senha.equals("12345")) {
					limparPagamento();
					aux = 0;
				} else {
					JOptionPane.showMessageDialog(null, "Senha incorreta!", null, ERROR_MESSAGE);
				}
// ***************************************************
			}
		});
		btnCancelarCompras.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCancelarCompras.setBounds(0, 289, 111, 34);
		panel.add(btnCancelarCompras);

		JButton btnCancelarItem = new JButton("Cancelar Item");
		btnCancelarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// ***************************************************
				DecimalFormat s = new DecimalFormat("0.00");
				String senha = JOptionPane.showInputDialog(null, "Digite a senha :");
				if (senha.equals("12345")) {
					String codigo = JOptionPane.showInputDialog(null, "Digite codigo do produto a ser cancelado :");
					Pagamento p = new Pagamento();
					sub = p.alteraSubtotal(sub, codigo, lista);
					p.cancelarItem(codigo, lista);
					txtLista.setText("");
					for (int i = 0; i < lista.size(); i++) {
						txtLista.append(lista.get(i) + "\n");
					}
					txtSubtotal.setText(s.format(sub));
				} else {
					JOptionPane.showMessageDialog(null, "Senha incorreta!", null, ERROR_MESSAGE);
				}
// ***************************************************
			}
		});
		btnCancelarItem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCancelarItem.setBounds(0, 334, 111, 34);
		panel.add(btnCancelarItem);

		JButton btnLimparTela = new JButton("Limpar Tela");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLimparTela.setBounds(0, 378, 111, 41);
		panel.add(btnLimparTela);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ******************************************
				if (!txtLista.getText().isEmpty() && !txtSubtotal.getText().isEmpty()) {
					DecimalFormat s = new DecimalFormat("0.00");
					Pagamento p = new Pagamento();
					p.salvarCompras(pagamento, s.format(sub), lista);
					TPagamento tela = new TPagamento(pagamento, txtSubtotal.getText(), frame);
					tela.setVisible(true);
					setVisible(false);
				}
				// *****************************************
			}
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnFinalizarCompra.setBounds(145, 302, 110, 35);
		panel.add(btnFinalizarCompra);

		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// *****************************************
				String senha = JOptionPane.showInputDialog(null, "Digite a senha :");
				if (senha.equals("12345")) {
					TCadastro tela = new TCadastro(caminho, frame);
					tela.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Senha incorreta!", null, ERROR_MESSAGE);
				}
// *****************************************
			}
		});
		btnCadastrarProduto.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCadastrarProduto.setBounds(145, 340, 110, 34);
		panel.add(btnCadastrarProduto);

		JLabel lblMedida = new JLabel("Medida");
		lblMedida.setBounds(193, 35, 46, 14);
		panel.add(lblMedida);

		txtMedida = new JTextField();
		txtMedida.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txtMedida.setHorizontalAlignment(SwingConstants.CENTER);
		txtMedida.setEditable(false);
		txtMedida.setBounds(187, 50, 59, 34);
		panel.add(txtMedida);
		txtMedida.setColumns(10);
	}
}
