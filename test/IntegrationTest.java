import models.Member;
import models.Message;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import java.util.ArrayList;
import java.util.Date;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {

        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("ロビー");
                // Messageのチェック
                Message msg = Message.find.all().get(0);
                assertThat(browser.pageSource()).contains(msg.name);
                assertThat(browser.pageSource()).contains(msg.message);
            }
        });
    }

    @Test
    public void checkModel() {
        FakeApplication fakeApplication = fakeApplication(inMemoryDatabase());
        try {
            start(fakeApplication);
            // Memberの用意
            Member member = new Member();
            member.name = "Kohaku";
            member.mail = "kohaku@dummymail.com";
            member.save();
            Member member2 = Member.find.byId(member.id);
            // Messageの用意
            Message message = new Message();
            message.id = 99999L;
            message.member = member2;
            message.name = member.name;
            message.message = "Kohaku san DEATH!!!";
            message.save();
            Message message2 = Message.find.byId(message.id);
            // 連携データ追加
            member2.messages.add(message2);
            member2.update();

            // Model1のチェック
            assertThat(member2.name).isEqualTo(message.name);
            assertThat(message2.message).isEqualTo(message.message);
            assertThat(message2.member).isEqualTo(member2);
            // nullチェック
            Member member3 = Member.find.byId(98765L);
            assertThat(member3).isNull();
            // 削除
            long id = message.id;
            Message message3 = Message.find.byId(id);
            message3.delete();
            assertThat(Message.find.byId(id)).isNull();

        } finally {
            stop(fakeApplication);
        }
    }

}
