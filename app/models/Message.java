package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	public static Finder<Long, Message> find = new Finder<>(Long.class, Message.class);
	
	@Override
	public String toString() {
		return "[id:" + id + ", name:" + name +
				",message:" + message + ", postdata:" + postdate +  "]";
	}

    public static Message findByName(String input) {
        return Message.find.where().eq("name", input).findList().get(0);
    }
}
