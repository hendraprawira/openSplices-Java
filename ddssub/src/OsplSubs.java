import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.Duration;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.Durability;
import org.omg.dds.core.policy.Partition;
import org.omg.dds.core.policy.PolicyFactory;
import org.omg.dds.core.policy.Reliability;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.sub.*;
import org.omg.dds.topic.Topic;
import HelloWorldData.Msg;


public class OsplSubs extends Thread {
    @Override
    public void run(){
        System.setProperty(
                ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY,
                "org.opensplice.dds.core.OsplServiceEnvironment");

        // Instantiate a DDS ServiceEnvironment
        ServiceEnvironment env = ServiceEnvironment
                .createInstance(Main.class.getClassLoader());

        DomainParticipantFactory dpf = DomainParticipantFactory
                .getInstance(env);

        DomainParticipant p = dpf.createParticipant();

        Reliability r = PolicyFactory.getPolicyFactory(env).Reliability()
                .withReliable();
        Durability d = PolicyFactory.getPolicyFactory(env).Durability()
                .withPersistent();

        Collection<Class<? extends Status>> statuses = new HashSet<Class<? extends Status>>();

        Partition partition = PolicyFactory.getPolicyFactory(env).Partition()
                .withName("HelloWorld example");

        Topic<Msg> topic = p.createTopic("HelloWorldData_Msg12", Msg.class, p.getDefaultTopicQos()
                .withPolicies(r, d), null, statuses);

        Subscriber sub = p.createSubscriber(p.getDefaultSubscriberQos()
                .withPolicy(partition));

        DataReaderQos drQos = sub.copyFromTopicQos(sub.getDefaultDataReaderQos(), topic.getQos());

        DataReader<Msg> reader = sub.createDataReader(topic, drQos);
        Duration wait_timeout = Duration.newDuration(15, TimeUnit.SECONDS, env);
        try {
            reader.waitForHistoricalData(wait_timeout);
        } catch (TimeoutException e) {
            System.out.println("Ended");
        }

        System.out.println("=== [Subscriber] Ready...");
        boolean closed = false;
        while (!closed) {
            Iterator<Sample<Msg>> samples = reader.take();
            while (samples.hasNext()) {
                Sample<Msg> sample = samples.next();
                Msg msg = sample.getData();
                if (msg != null) {
                    System.out.println(msg.message + "\n");
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                closed = true;
            }
        }
        topic.close();
        reader.close();
        sub.close();
        p.close();
    }
}
