package mv.empilhaveis;

public class CadeiaDeCaracteres  implements Empilhavel {

    private String cadeiaC;
    
    public CadeiaDeCaracteres (String cadeiaC) {
	this.cadeiaC = cadeiaC;
    }

    public String getCadeiaC () {
	return cadeiaC;
    }
    
    public CadeiaDeCaracteres clona () {
	return (new CadeiaDeCaracteres (cadeiaC));
    }
    
    public void print() {
	System.out.println(cadeiaC);
    }

    public boolean equals (Object obj) {
	if (obj instanceof CadeiaDeCaracteres) {
	    return cadeiaC.equals(((CadeiaDeCaracteres)obj).getCadeiaC());
	}
	return false;
    }
    
}
