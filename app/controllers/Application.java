package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	/**
	 * indexは「/」アクセス時に呼ばれる
	 * @return
	 */
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
