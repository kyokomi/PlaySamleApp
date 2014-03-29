package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyokomi on 2014/03/29.
 */
@Entity
public class Member extends Model {

    @Id
    public Long id;

    @Required(message = "必須項目です")
    public String name;

    @Required(message = "メールアドレスを入力してください")
    public String mail;

    public String tel;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Message> messages = new ArrayList<>();

    public static Finder<Long, Member> find = new Finder<>(Long.class, Member.class);

    public String toString() {
        String ids = "{ids:";
        for (Message message : messages) {
            ids += " " + message.id;
        }
        ids += "}";
        return ("[id:" + id + ", message:" + ids + ", name:" + name + ", mail:" + mail + ", tel:" + tel + "]");
    }
    public static Member findByName(String input) {
        return Member.find.where().eq("name", input).findList().get(0);
    }
}
