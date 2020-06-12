package PT2019.Tema1;

import Controller.PolynomialController;
import Model.Monom;
import Model.Polinom;
import Model.PolynomialModel;
import View.PolynomialView;

public class App 
{
    public static void main( String[] args )
    {
    	PolynomialModel model = new PolynomialModel();
		PolynomialView view = new PolynomialView(model);
		PolynomialController controller = new PolynomialController(model, view);

		view.setVisible(true);
        
    }
}
