package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import static play.data.Form.*;

public class Application extends Controller {

	// Form用の内部クラス
	public static class SampleForm {
		public String message;
	}
	
	/**
	 * indexは「/」アクセス時に呼ばれる
	 * @return
	 */
    public static Result index() {
        return ok(index.render("何か書いて", new Form<>(SampleForm.class)));
    }
    
    public static Result send() {
    	Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
    	if (!f.hasErrors()) {
    		SampleForm data = f.get();
    		String msg = "you typed: " + data.message;
    		return ok(index.render(msg, f));
    	} else {
    		return badRequest(index.render("ERROR", form(SampleForm.class)));
    	}
    }

}
