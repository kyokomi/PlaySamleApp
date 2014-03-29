package controllers;

import java.util.List;

import models.Member;
import models.Message;
import play.*;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import views.html.*;
import views.html.helper.input;
import static play.data.Form.*;

public class Application extends Controller {

	// Form用の内部クラス
	public static class SampleForm {
		public String message;
	}
	
	// Finder用の内部クラス
	public static class FindForm {
		@Required
		public String input;
	}
	
	/**
	 * indexは「/」アクセス時に呼ばれる
	 * @return
	 */
    public static Result index() {
    	List<Message> dataList = Message.find.all();
        List<Member> data2List = Member.find.all();
        return ok(index.render("ロビー", dataList,data2List));
    }

    // Message Action =======================

    public static Result add() {
    	Form<Message> f = new Form<>(Message.class);
    	return ok(add.render("投稿フォーム", f));
    }
    public static Result create() {
    	Form<Message> f = new Form<>(Message.class).bindFromRequest();
    	if (!f.hasErrors()) {
    		Message data = f.get();
            data.member = Member.findByName(data.name);
    		data.save();
    		return redirect("/");
    	} else {
    		return badRequest(add.render("ERROR", f));
    	}
    }

    // Member Action =======================

    // メンバー作成フォームのAction
    public static Result add2() {
        Form<Member> f = new Form<>(Member.class);
        return ok(add2.render("メンバー登録フォーム", f));
    }

    public static Result create2() {
        Form<Member> f = new Form<>(Member.class).bindFromRequest();
        if (!f.hasErrors()) {
            Member data = f.get();
            data.save();
            return redirect("/");
        } else {
            return badRequest(add2.render("ERROR", f));
        }
    }




    // =====================================

    public static Result setitem() {
    	Form<Message> f = new Form<>(Message.class);
    	return ok(item.render("ID番号を入力。", f));
    }
    
    public static Result edit() {
    	Form<Message> f = new Form<>(Message.class).bindFromRequest();
    	if (!f.hasErrors()) {
    		Message data = f.get();
    		Long id = data.id;
    		data = Message.find.byId(id);
    		if (data != null) {
    			f = new Form<>(Message.class).fill(data);
    			return ok(edit.render("ID=" + id + "の投稿を編集。", f));
    		} else {
    			return ok(item.render("ERROR:IDの投稿が見つかりません。", f));
    		}
    	} else {
    		return ok(item.render("ERROR:入力に問題があります。", f));
    	}
    }
    
    public static Result update() {
    	Form<Message> f = new Form<>(Message.class).bindFromRequest();
    	if (!f.hasErrors()) {
    		Message data = f.get();
    		data.update();
    		return redirect("/");
    	} else {
    		return ok(edit.render("ERROR:再度入力してください。", f));
    	}
    }
    
    public static Result delete() {
    	Form<Message> f = new Form<>(Message.class);
    	return ok(delete.render("削除するID番号", f));
    }
    
    public static Result remove() {
    	Form<Message> f = new Form<>(Message.class).bindFromRequest();
    	if (!f.hasErrors()) {
    		Message data = f.get();
    		Long id = data.id;
    		data = Message.find.byId(id);
    		if (data != null) {
    			data.delete();
    			return redirect("/");
    		} else {
    			return ok(item.render("ERROR:IDの投稿が見つかりません。", f));
    		}
    	} else {
    		return ok(item.render("ERROR:入力に問題があります。", f));
    	}
    }
    
    public static Result find() {
    	Form<FindForm> f = new Form<>(FindForm.class).bindFromRequest();
    	List<Message> datas = null;
    	if (!f.hasErrors()) {
    		String input = f.get().input;
    		datas = Message.find.where().eq("name", input).findList();
    	}
    	return ok(find.render("投稿の検索", f, datas));
    }
}
