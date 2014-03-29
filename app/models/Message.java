package models;

import java.util.Date;

import javax.persistence.*;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	public Long id;
	@Required
	public String name;
	@Required
	public String message;
	@CreatedTimestamp
	public Date postdate;

    // Cascadeの指定は必ず必要なわけではない
    // 場合によっては手作業で更新したほうが良い場合もあるので、必要に応じて使うとよい。
    @ManyToOne(cascade = CascadeType.ALL)
    public Member member;

	public static Finder<Long, Message> find = new Finder<>(Long.class, Message.class);
	
	@Override
	public String toString() {
		return "[id:" + id + ", member:<" + member.toString() +
				">, message:" + message + ", postdata:" + postdate +  "]";
	}

    public static Message findByName(String input) {
        return Message.find.where().eq("name", input).findList().get(0);
    }
}
