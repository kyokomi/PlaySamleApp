package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    public static Finder<Long, Member> find = new Finder<>(Long.class, Member.class);

    public String toString() {
        return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", tel:" + tel + "]");
    }
    public static Member findByName(String input) {
        return Member.find.where().eq("name", input).findList().get(0);
    }
}
