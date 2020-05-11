package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Produto {

	private String codigo;
	private String descricao;
	private String medida;
	private String valor;

	public boolean salvar(String codigo, String telaDescricao, String medidaTela, String valorTela, String caminho) {
		if (codigo.isEmpty()) {
			String descricao = telaDescricao;
			String medida = medidaTela;
			String valor = valorTela;
			File f = new File("C:\\Users\\Aluno\\Desktop\\Cadastro");
			f.mkdirs();
			File cadastro = new File(caminho);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(cadastro, true));
				int numeroCodigo = numeroCadastro(caminho);
				codigo = Integer.toString(numeroCodigo);
				bw.write(descricao + "@" + medida + "@" + valor + "@" + codigo);
				bw.newLine();
				bw.close();
			} catch (IOException e1) {

			}
			return true;
		} else {
			return false;
		}
	}

	public int numeroCadastro(String caminho) {
		int compara = 1001;  // ************
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.isEmpty()) {
					compara = Integer.parseInt(linha.substring(linha.lastIndexOf("@") + 1));
					compara++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compara;
	}

	public boolean localizar(String codigoProcurado, String caminho) {
		int aux = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			String linha = null;
			while (br.ready()) {
				linha = br.readLine();
				String produto[] = linha.split("@");
				if (produto[3].endsWith(codigoProcurado)) {
					String vetor[] = linha.split("@");
					setDescricao(vetor[0]);
					setMedida(vetor[1]);
					setValor(vetor[2]);
					setCodigo(vetor[3]);
					aux++;
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (aux == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean alterar(String caminho, String codigoTela, String descricaoTela, String medidaTela,
			String valorTela) {
		try {
			String codigo = codigoTela;
			String descricao = descricaoTela;
			String medida = medidaTela;
			String valor = valorTela;
			String temporario = "C:\\Users\\USER\\Desktop\\Cadastro\\temporario.txt";
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(f));
			while (brNovo.ready()) {
				String novaLinha = brNovo.readLine();
				if (novaLinha.endsWith(codigoTela)) {
					bwNovo.write(descricao + "@" + medida + "@" + valor + "@" + codigo);
					bwNovo.newLine();
				} else {
					bwNovo.write(novaLinha);
					bwNovo.newLine();
				}
			}
			brNovo.close();
			bwNovo.close();
			File renomear = new File(caminho);
			renomear.delete();
			f.renameTo(renomear);
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public int excluir(String codigoProcurado, String caminho) {
		String temporario = "C:\\Users\\USER\\Desktop\\Cadastro\\temporario.txt";
		int aux = 0;
		try {
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(f));
			while (brNovo.ready()) {
				String novaLinha = brNovo.readLine();
				if (!novaLinha.endsWith(codigoProcurado)) {
					bwNovo.write(novaLinha);
					bwNovo.newLine();
					aux++;
				}
			}
			brNovo.close();
			bwNovo.close();
			File renomear = new File(caminho);
			renomear.delete();
			f.renameTo(renomear);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return aux;
	}

	public double subtotal(double sub, String valor, String quantidade) {
		double totalLinha = Double.parseDouble(valor) * Double.parseDouble(quantidade);
		sub += totalLinha;
		return sub;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
