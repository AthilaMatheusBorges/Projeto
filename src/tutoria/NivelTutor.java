package tutoria;

/**
 * Enum para representar o nivel do tutor.
 * Calcula a taxa do tutor de acordo com o nivel.
 * Retorna a string do nivel do tutor de acordo com o nivel.
 * 
 * @author Roundhouse Kick Group 
 *
 */
public enum NivelTutor implements Nivel{

	TOP {
		@Override
		public double calculaTaxa(double nota) {
			 return (90 + ((nota - 4.5)* 10)) / 100.0;
		}
		@Override
		public String pegarNivel() {
			return "TOP";
		}
	}, 
	
	TUTOR{
		@Override
		public double calculaTaxa(double nota) {
			return  0.8;
		}
		@Override
		public String pegarNivel() {
			return "Tutor";
		}
	},
	
	APRENDIZ{
		@Override
		public double calculaTaxa(double nota) {
			return (0.4 - ((3.0 - nota)*10)/100);
		}
		@Override
		public String pegarNivel() {
			return "Aprendiz";
		}
	}
}
