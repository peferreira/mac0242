package mv;

public class Operacao {
	private int codigoOperacao;
	private String codigoArgumento;

	public Operacao(int op) {
		codigoOperacao = op;
		codigoArgumento = null;
	}

	public Operacao(int op, String dir) {
		codigoOperacao = op;
		codigoArgumento = dir;
	}

	public String getArgumento() {
		return codigoArgumento;
	}

	public int getCodigo() {
		return codigoOperacao;
	}
}
