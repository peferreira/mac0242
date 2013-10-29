package mv;

public class Operacao {
		private int codigoOperacao;
		private int codigoArgumento;
		
		public Operacao (int op) {
			codigoOperacao = op; 
			codigoArgumento = -1;
		}
		
		public Operacao (int op, int dir) {
			codigoOperacao = op; 
			codigoArgumento = dir;
		}
		
		public int getArgumento(){
			return codigoArgumento;
		}
		
		public int getCodigo(){
			return codigoOperacao;
		}
}
