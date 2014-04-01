package id.ac.ui.edoocatia.util;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.model.Karakter;

public enum KarakterEnum {
	PLAYER {
        protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("player");
        }
    },
	
	PROFESSOR {
        protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("professor");
        }
    },
    
    ALTA {
    	protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("alta");
        }
    },
    
    AZMO {
        protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("azmo");
        }
    },
    
    MOMO {
        protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("momo");
        }
    },
    
    TSARINA {
        protected Karakter getKarakterInstance(Edoocatia app) {
            return new Karakter("tsarina");
        }
    };
    
    
    
    
}
