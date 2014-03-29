package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Date;
import java.util.List;

/**
 * Created by kyokomi on 2014/03/29.
 */
public class SampleController extends Controller {

    public static class SampleForm {
        public String input;
        public String pass;
        public boolean check;
        public String radio;
        public String sel;
        public String area;
        public Date date;
    }
    public static class Sample2Form {
        public List<String> inputs;
    }

    public static Result index() {
        SampleForm sf = new SampleForm();
        sf.radio = "windows";
        sf.check = true;
        sf.input = "default value";
        sf.sel = "uk";
        Form<SampleForm> form = new Form<>(SampleForm.class).fill(sf);

        return ok(sample.render("Formのサンプル部屋", form));
    }

    public static Result send() {
        Form<SampleForm> f = new Form<>(SampleForm.class).bindFromRequest();
        if (!f.hasErrors()) {
            SampleForm sf = f.get();
            String res = "value: ";
            res += "input=" + sf.input
                    + ", pass=" + sf.pass
                    + ", check=" + sf.check
                    + ", radio=" + sf.radio
                    + ", sel=" + sf.sel
                    + ", area=" + sf.area
                    + ", date=" + sf.date;
            return ok(sample.render(res, f));
        } else {
            return badRequest(sample.render("ERROR", f));
        }
    }

    public static Result sample2() {
        Form<Sample2Form> form = new Form<>(Sample2Form.class);
        return ok(sample2.render("Formのサンプル部屋", form));
    }

    public static Result send2() {
        Form<Sample2Form> f = new Form<>(Sample2Form.class).bindFromRequest();
        if (!f.hasErrors()) {
            Sample2Form sf = f.get();
            String res = "value :";
            for (String s : sf.inputs) {
                res += " " + s;
            }
            sf.inputs.add("");
            return ok(sample2.render(res, f));
        } else {
            return badRequest(sample2.render("ERROR", f));
        }
    }
}
