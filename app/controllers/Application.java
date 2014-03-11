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
        return ok(index.render(
        		"はじめ！",
        		"Your new application is ready.",
        		"宇宙エロ本争奪ゲームVer2",
        		"宇宙エロ本争奪ゲームVer3"));
    }

}
