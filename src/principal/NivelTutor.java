package principal;

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
