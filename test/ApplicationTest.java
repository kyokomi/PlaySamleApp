import java.util.*;

import models.Member;
import models.Message;

import org.junit.*;

import play.data.Form;
import play.mvc.*;
import scala.Tuple2;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
 * このクラスはテンプレートをチェックすることを限定。
 *
 * ModelやControllerはまた別で行う。
 *
 */
public class ApplicationTest {
    List<Member> dummy_mems = null;
    List<Message> dummy_msgs = null;

    public ApplicationTest() {
        initialData();
    }

    // ダミーデータの準備
    public void initialData() {
        dummy_mems = new ArrayList<>();
        Member mem = new Member();
        mem.id = 10001L;
        mem.name = "dummy name";
        mem.mail = "dummy@mail";
        mem.tel = "000000";
        dummy_mems.add(mem);

        dummy_msgs = new ArrayList<>();
        Message msg = new Message();
        msg.id = 10002L;
        msg.name = mem.name;
        msg.member = mem;
        msg.message = "dummy message.";
        msg.postdate = new Date();

        mem.messages = new ArrayList<>();
        mem.messages.add(msg);
        dummy_msgs.add(msg);
    }

    @Test
    public void renderTemplate1() {
        String msg = "テストメッセージ";
        Content add = views.html.add.render(msg, new Form<>(Message.class), new ArrayList<Tuple2<String, String>>());

        assertThat(contentType(add)).isEqualTo("text/html");
        assertThat(contentAsString(add)).contains(msg);
    }

    @Test
    public void renderTemplate2() {
        String msg = "テストメッセージ";
        Content index = views.html.index.render(msg, dummy_msgs, dummy_mems);
        assertThat(contentType(index)).isEqualTo("text/html");

        assertThat(contentAsString(index)).contains(msg);
        assertThat(contentAsString(index)).contains(dummy_msgs.get(0).message);
    }

}
