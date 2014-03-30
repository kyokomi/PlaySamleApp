import com.avaje.ebean.Ebean;
import models.Member;
import models.Message;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Created by kyokomi on 2014/03/30.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        // H2以外のときは実行したらダメ
        if (application.configuration().getString("db.default.driver").equals("org.h2.Driver")) {
            insert(application);
        }
    }

    public void insert(Application app) {
        Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("test-data.yml");
        Ebean.save(all.get("members"));
        Ebean.save(all.get("messages"));
        for (Object message : all.get("messages")) {
            Message target = Message.find.byId(((Message)message).id);
            target.member = Member.findByName(target.name);
            target.update();
        }
    }
}
