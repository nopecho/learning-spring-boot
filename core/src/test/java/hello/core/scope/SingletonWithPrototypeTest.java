package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        assertThat(clientBean1.logic()).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        assertThat((clientBean2.logic())).isEqualTo(2);


    }

    @Scope("singleton")
    @Configuration
    static class ClientBean{
        private final PrototypeBean prototypeBean; //생성 시점에 주입 받음 생성자 주입 @Autowired

        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
            System.out.println("ClientBean.ClientBean = "+this.prototypeBean);
        }

        public int logic(){
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    @Configuration
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            this.count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.inti = " + this);
        }

        @PreDestroy
        public void close(){
            System.out.println("PrototypeBean.close");
        }
    }
}
